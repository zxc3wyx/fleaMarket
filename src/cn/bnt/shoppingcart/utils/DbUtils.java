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
        //�þ�̬�����������Ƕ�ȡ�����ļ�jdbc.properties�е�ֵ����������4����̬�ĳ�Ա������ֵ
        InputStream in = DbUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        //�Ѹ������������װ��һ��Properties
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
     * ���ܣ���ȡ���ݿ������
     *
     * @return
     */
    public static Connection getConnection() {
        Connection con = null;
        //��������
        try {
            Class.forName(driverClass);
            //ͨ��������������DriverManager����ȡ���ݿ������
            con = DriverManager.getConnection(dburl, user, pwd);
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return con;
    }

    //�ͷ����Ӽ��������Դ(��Ҫ����Ը��²�������Դ�ͷ�,����,ɾ�����޸Ĳ�����
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

    //���ݿ�������Դ�ͷţ���Ҫ��Բ�ѯ����,select������
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
     * �÷����Ĺ��ܣ���Ը��²���������дһ��ͨ�õķ������ܹ��������ǵ�insert,update,delete����
     *
     * @param sql:��ʾҪִ�и��µ�sql���
     * @param args              :��ʾ��ִ�е�sql�еĶ�̬���ݵĲ���
     * @return
     */
    public static Boolean update(String sql, Object[] args) {
        boolean result = false;
        //��ȡ���ݿ������
        Connection con = null;
        PreparedStatement ps = null;
        con = getConnection();
        if (con != null) {
            try {
                ps = con.prepareStatement(sql);
                //Ҫ��sql����е��ʺ�λ�ø�ֵ
                for (int i = 0; args != null && i < args.length; i++) {
                    //ע������������͵�ת��������ʵ����������ͨ��Ϊjava.util.Date,�����ݿ�������ͨ��Ϊjava.sql.Date,java.util.Date��java.sql.Date��һ������
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
                //�ͷ���Դ
                close(con, ps);
            }
        }
        return result;
    }
}
