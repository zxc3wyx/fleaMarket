package cn.bnt.shoppingcart.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class BeanFactoryUtils {

    private static final Map<String, String> mapBeans = new HashMap<>();

	static {
        initial();
    }

	@SuppressWarnings("unchecked")
	static void initial() {
		InputStream in = BeanFactoryUtils.class.getClassLoader().getResourceAsStream("beans.properties");
		Properties prop = new Properties();
		try {
			prop.load(in);
			Enumeration<String> em = (Enumeration<String>) prop.propertyNames();

			while (em.hasMoreElements()) {
				String key = em.nextElement();
				String value = prop.getProperty(key);
				mapBeans.put(key, value);
			}
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

	@SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> clazz) {
        T bean = null;
        String className = clazz.getSimpleName();
        Set<String> keys = mapBeans.keySet();
        for (String key : keys) {
            if (key.equalsIgnoreCase(className)) {
                try {
					bean = (T) Class.forName(mapBeans.get(key)).getDeclaredConstructor().newInstance();
					break;
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException |
						InvocationTargetException | NoSuchMethodException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
			}
        }
        return bean;
    }
}
