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

    //�ù�����ķ�����������ɱ��������ռ�����(���԰ѱ�ת����Ӧ���κ�һ��ʵ����T����)
    public static <T> T request2Bean(HttpServletRequest request, Class<T> clazz) {

        T t = null;
        try {
            //Student stu=new Student();
            t = clazz.getDeclaredConstructor().newInstance();//��clazz�������ֽ��������������Ӧ�Ķ�������
            //��ȡ��clazz��Ӧ��������Ҫ�������ֶ�
            Field[] fields = clazz.getDeclaredFields();
            //���бȶԣ���Ϊ���е��������ƺͱ��ж�Ӧ���ֶ�name����ֵ��һ�µģ�
            //���������ֶ�
            for (Field f : fields) {
                //��ȡ�ֶε�����
                String fieldName = f.getName();
                //��ȡ��Ӧ���и��ֶζ�Ӧ��ֵ
                String value = array2String(request.getParameterValues(fieldName));
				//�Ѹ�value��ֵ���õ������Զ�Ӧ�Ķ�������
				//������֮ǰ���ǻ�Ҫ��������ת������Ϊ���е����ݶ����ַ������ͣ���ʵ������е����Ի��и�������
				Class<?> fieldType = f.getType();//��ʾ���ֶε�����
				f.setAccessible(true);//��˽����������Ϊ�ɼ��ɲ���
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
					f.set(t, value);//�÷����������ǽ�valueֵ���õ�t�����Ӧ��f��������
				}
			}

        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException |
				ParseException | InvocationTargetException | NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

		return t;
    }

    //�ù����෽����������һ���ַ�����������ת����һ���ַ�������
    public static String array2String(String[] array) {
        //String,StringBuffer,StringBuilder����֮�������
        StringBuilder sb = new StringBuilder();
        for (int i = 0; array != null && i < array.length; i++) {
            sb.append(array[i]);
            sb.append(i < array.length - 1 ? "," : "");//������Ԫ�������Ҳ�����ʺű��ʽ    �﷨��ʽ��    ���ʽ?:value1:value2�����ú�if..else..��������
        }
        return sb.toString();
    }

    public static String getUserUid() {
        StringBuilder uid = new StringBuilder();
        //���һ��0-26�������
        for (int i = 1; i <= 6; i++) {
            int n = (int) (Math.random() * 26);
            uid.append((char) (65 + n));
        }
        String str = System.currentTimeMillis() + (int) (Math.random() * 1000) + "";
        str = str.substring(str.length() - 5);
        uid.append(str);
        return uid.toString();
    }

    public static String getOrderOid() {//����һ���������
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
                errors.put(name, name + "�ֶβ���Ϊ��");
            }
        }
        return errors;
    }

    public static void main(String[] args) {
        System.out.println((char) (65 + 25));
    }
}
