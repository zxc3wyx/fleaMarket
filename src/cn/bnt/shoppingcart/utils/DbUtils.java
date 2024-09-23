package cn.bnt.shoppingcart.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DbUtils {

    private static String driverClass;
    private static String user;
    private static String pwd;
    private static String dburl;

    static {
        //该静态代码块的作用是读取配置文件jdbc.properties中的值，并给上面4个静态的成员变量赋值
        InputStream in = DbUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        //把该输入流对象包装成一个Properties
        Properties prop = new Properties();
        try {
            prop.load(in);
            driverClass = prop.getProperty("jdbc.driverclass");
            user = prop.getProperty("jdbc.user");
            dburl = prop.getProperty("jdbc.dburl");
            pwd = prop.getProperty("jdbc.password");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 功能：获取数据库的连接
     *
     * @return
     */
    public static Connection getConnection() {
        Connection con = null;
        //加载驱动
        try {
            Class.forName(driverClass);
            //通过驱动管理器类DriverManager来获取数据库的连接
            con = DriverManager.getConnection(dburl, user, pwd);
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return con;
    }

    //释放连接及其操作资源(主要是针对更新操作的资源释放,插入,删除，修改操作）
    public static void close(Connection con, Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    //数据库连接资源释放（主要针对查询操作,select操作）
    public static void close(Connection con, Statement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        close(con, st);
    }

    /**
     * 该方法的功能：针对更新操作我们来写一个通用的方法，能够处理我们的insert,update,delete操作
     *
     * @param sql:表示要执行更新的sql语句
     * @param args              :表示所执行的sql中的动态传递的参数
     * @return
     */
    public static Boolean update(String sql, Object[] args) {
        boolean result = false;
        //获取数据库的连接
        Connection con = null;
        PreparedStatement ps = null;
        con = getConnection();
        if (con != null) {
            try {
                ps = con.prepareStatement(sql);
                //要给sql语句中的问号位置赋值
                for (int i = 0; args != null && i < args.length; i++) {
                    //注意对于日期类型的转换处理，在实体类中日期通常为java.util.Date,在数据库中日期通常为java.sql.Date,java.util.Date是java.sql.Date的一个父类
                    if (args[i] instanceof java.util.Date date) {
						ps.setDate(i + 1, new java.sql.Date(date.getTime()));
                    } else {
                        ps.setObject(i + 1, args[i]);
                    }
                }
                int n = ps.executeUpdate();
				result = n > 0;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                //释放资源
                close(con, ps);
            }
        }
        return result;
    }
}
