package wt.bs.domain.base.utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;

public class BeanHelper extends BeanUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(BeanHelper.class);

    public BeanHelper() {
    }

    public static <T> Collection<T> copyTo(Collection<?> sourceList, Class<T> target) {
        if(CollectionUtils.isEmpty(sourceList)) {
            return Collections.emptyList();
        } else {
            ArrayList list = new ArrayList();

            Iterator i$ = sourceList.iterator();

            while(i$.hasNext()) {
                Object o = i$.next();
                list.add(copyTo(o, target));
            }
            return list;

        }
    }

    public static <T> T copyTo(Object sourceObj, Class<T> target) {
        if(sourceObj == null) {
            return null;
        } else {
            T e = null;
            try {
                e = target.newInstance();
            } catch (InstantiationException | IllegalAccessException e1) {
                LOGGER.info(e1.toString());
            }
            assert e != null;
            BeanUtils.copyProperties(sourceObj, e);
            return e;
        }
    }

    public static Map<String, Object> modelToMap(Object obj, String prefix, String suffix) {
        Map<String, Object> result = new HashMap();

        for(Class clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            Field[] fs = clazz.getDeclaredFields();
            StringBuffer sb = null;

            for(int i = 0; i < fs.length; ++i) {
                if(!Modifier.isStatic(fs[i].getModifiers())) {
                    fs[i].setAccessible(true);

                    Object value = null;
                    try {
                        value = fs[i].get(obj);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    if(value != null) {
                        sb = (new StringBuffer(StringUtils.isBlank(prefix)?"":prefix)).append(fs[i].getName()).append(StringUtils.isBlank(suffix)?"":suffix);
                        result.put(sb.toString(), value);
                    }
                }
            }
        }

        return result;
    }

    public static Map<String, String> modelToMap(Object obj) {
        Map<String, String> result = new HashMap();

        for(Class clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            Field[] fs = clazz.getDeclaredFields();
            StringBuffer sb = null;

            for(int i = 0; i < fs.length; ++i) {
                if(!Modifier.isStatic(fs[i].getModifiers())) {
                    fs[i].setAccessible(true);
                    Object value = null;
                    try {
                        value = fs[i].get(obj);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    if(value != null) {
                        result.put(fs[i].getName(), String.valueOf(value));
                    }
                }
            }
        }

        return result;
    }

    public static Object mapToModel(Map<String, String> map, Class<?> clazz) {
        Object result = null;
        Set keySet = map.keySet();

        try {
            for(result = clazz.newInstance(); clazz != Object.class; clazz = clazz.getSuperclass()) {
                Iterator i$ = keySet.iterator();

                while(i$.hasNext()) {
                    String attr = (String)i$.next();
                    Field field = null;

                    try {
                        field = clazz.getDeclaredField(attr);
                    } catch (NoSuchFieldException var12) {
                        continue;
                    }

                    field.setAccessible(true);
                    Class<?> typeClazz = field.getType();
                    Object o = map.get(attr);
                    if(o != null) {
                        String dateType;
                        if(typeClazz.equals(String.class)) {
                            dateType = o.toString();
                            field.set(result, dateType);
                        } else if(typeClazz.equals(Integer.class)) {
                            field.set(result, Integer.valueOf(Integer.parseInt(o.toString())));
                        } else if(typeClazz.equals(Long.class)) {
                            field.set(result, Long.valueOf(Long.parseLong(o.toString())));
                        } else if(typeClazz.equals(Date.class)) {
                            dateType = DateFormatUtils.getFormat(o.toString());
                            Date date;
                            if(dateType != null) {
                                SimpleDateFormat sdf = new SimpleDateFormat(dateType);
                                date = sdf.parse(o.toString());
                                field.set(result, date);
                            } else {
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTimeInMillis(Long.parseLong(o.toString()));
                                date = calendar.getTime();
                                field.set(result, date);
                            }
                        } else if(typeClazz.equals(Float.class)) {
                            field.set(result, Float.valueOf(Float.parseFloat(o.toString())));
                        } else if(typeClazz.equals(Double.class)) {
                            field.set(result, Double.valueOf(Double.parseDouble(o.toString())));
                        }
                    }
                }
            }

            return result;
        } catch (Exception var13) {
            LOGGER.error("map转对象出现错误source=[{}],targetType=[{}],errMsg=[{}]", new Object[]{map, clazz, var13.getMessage()});
            throw new RuntimeException(var13.getMessage());
        }
    }

    public static <T> Class<T> findParameterizedType(Class<?> clazz, int index) {
        Type parameterizedType = clazz.getGenericSuperclass();
        if(!(parameterizedType instanceof ParameterizedType)) {
            parameterizedType = clazz.getSuperclass().getGenericSuperclass();
        }

        if(!(parameterizedType instanceof ParameterizedType)) {
            return null;
        } else {
            Type[] actualTypeArguments = ((ParameterizedType)parameterizedType).getActualTypeArguments();
            return actualTypeArguments != null && actualTypeArguments.length != 0 && index <= actualTypeArguments.length?(Class)actualTypeArguments[index]:null;
        }
    }

    public static <T extends Serializable> T clone(T obj) {
        Serializable clonedObj = null;

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            oos.close();
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            clonedObj = (Serializable)ois.readObject();
            ois.close();
            return (T) clonedObj;
        } catch (Exception var6) {
            LOGGER.error("数据复制出现错误source=[{}],errMsg=[{}]", new Object[]{obj, var6.getMessage()});
            throw new RuntimeException(var6.getMessage());
        }
    }
}
