package cn.bnt.shoppingcart.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class WebUtils {

    //该工具类的方法是用来完成表单的数据收集功能(可以把表单转换对应的任何一个实体类T上来)
    public static <T> T request2Bean(HttpServletRequest request, Class<T> clazz) {

        T t = null;
        try {
            //Student stu=new Student();
            t = clazz.getDeclaredConstructor().newInstance();//由clazz二进制字节码对象来创建对应的对象类型
            //获取该clazz对应的类中所要的属性字段
            Field[] fields = clazz.getDeclaredFields();
            //进行比对（因为类中的属性名称和表单中对应的字段name属性值是一致的）
            //遍历属性字段
            for (Field f : fields) {
                //获取字段的名称
                String fieldName = f.getName();
                //获取对应表单中该字段对应的值
                String value = array2String(request.getParameterValues(fieldName));
				//把该value的值设置到该属性对应的对象上来
				//在设置之前我们还要进行类型转换，因为表单中的数据都是字符串类型，而实体对象中的属性会有各种类型
				Class<?> fieldType = f.getType();//表示该字段的类型
				f.setAccessible(true);//把私有属性设置为可见可操作
				if (fieldType == Integer.class || fieldType == int.class) {
					f.set(t, Integer.parseInt(value));
				} else if (fieldType == Long.class || fieldType == long.class) {
					f.set(t, Long.parseLong(value));
				} else if (fieldType == Double.class || fieldType == double.class) {
					f.set(t, Double.parseDouble(value));
				} else if (fieldType == Float.class || fieldType == float.class) {
					f.set(t, Float.parseFloat(value));
				} else if (fieldType == Boolean.class || fieldType == boolean.class) {
					f.set(t, Boolean.parseBoolean(value));
				} else if (fieldType == Date.class) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					f.set(t, sdf.parse(value));
				} else if (fieldType == String.class) {
					f.set(t, value);//该方法的作用是将value值设置到t对象对应的f属性上来
				}
			}

        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException |
				ParseException | InvocationTargetException | NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

		return t;
    }

    //该工具类方法是用来把一个字符串数组类型转换成一个字符串类型
    public static String array2String(String[] array) {
        //String,StringBuffer,StringBuilder三者之间的区别
        StringBuilder sb = new StringBuilder();
        for (int i = 0; array != null && i < array.length; i++) {
            sb.append(array[i]);
            sb.append(i < array.length - 1 ? "," : "");//采用三元运算符，也就是问号表达式    语法格式：    表达式?:value1:value2，作用和if..else..作用类似
        }
        return sb.toString();
    }

    public static String getUserUid() {
        StringBuilder uid = new StringBuilder();
        //随机一个0-26的随机数
        for (int i = 1; i <= 6; i++) {
            int n = (int) (Math.random() * 26);
            uid.append((char) (65 + n));
        }
        String str = System.currentTimeMillis() + (int) (Math.random() * 1000) + "";
        str = str.substring(str.length() - 5);
        uid.append(str);
        return uid.toString();
    }

    public static String getOrderOid() {//产生一个订单编号
        String order = "";
        order = System.currentTimeMillis() + (int) (Math.random() * 1000) + "";
        order = order.substring(order.length() - 5);
        return order;
    }

    public static Map<String, String> validate(HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String value = request.getParameter(name);
            if (value == null || "".equals(value.trim())) {
                errors.put(name, name + "字段不能为空");
            }
        }
        return errors;
    }

    public static void main(String[] args) {
        System.out.println((char) (65 + 25));
    }
}
