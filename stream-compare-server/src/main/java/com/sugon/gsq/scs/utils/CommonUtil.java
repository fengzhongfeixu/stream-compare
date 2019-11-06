package com.sugon.gsq.scs.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/*
 * ClassName: CommonUtil
 * Author: Administrator
 * Date: 2019/5/26 19:42
 */
public class CommonUtil {

    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    public static Map<String, Object> getFieldValue(Object... exs) throws Exception {
        if(exs == null) return null;
        Map result = new HashMap<String, String>();
        for(Object ex : exs){
            Field[] fields = ex.getClass().getDeclaredFields();
            field:
            for(Field field : fields){
                String fieldName = field.getName();
                if(fieldName.equals("serialVersionUID")) continue field;
                String firstLetter = fieldName.substring(0, 1).toUpperCase();
                String getter = "get" + firstLetter + fieldName.substring(1);
                Method method = ex.getClass().getMethod(getter, new Class[]{});
                Object value = method.invoke(ex, new Object[] {});
                result.put(fieldName, value);
            }
        }

        return result.keySet().size()==0 ? null : result;
    }

}
