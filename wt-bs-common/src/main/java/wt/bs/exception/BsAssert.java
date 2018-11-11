package wt.bs.exception;


import org.springframework.util.CollectionUtils;

import java.util.Collection;

public class BsAssert {

    public static void notNull(Object object, String message) {
        if(object == null) {
            throw new BsException("0003",message);
        }
    }
    public static void isNull(Object object, String message) {
        if(object != null) {
            throw new BsException("0003",message);
        }
    }
    public static void notNull(String code, Object object, String message) {
        if(object == null) {
            throw new BsException(code,message);
        }
    }
    public static void isBlank(String string, String message) {
        if (string == null || string.length() == 0)
            throw new BsException("0003",message);
    }

    public static void isBlank(String string, String code ,String message) {
        if (string == null || string.length() == 0)
            throw new BsException(code,message);
    }

    public static void notEqual(String str1, String str2, String message) {
        if(!str1.equals(str2)){
            throw new BsException("0003",message);
        }
    }

    public static void notEqual(Integer i, Integer i2, String message) {
        if(!i.equals(i2)){
            throw new BsException("0003",message);
        }
    }

    public static void notEqual(String code, String str1, String str2, String message) {
        if(!str1.equals(str2)){
            throw new BsException(code,message);
        }
    }

    public static void notEmpty(Collection c, String message){
        if(CollectionUtils.isEmpty(c)){
            throw new BsException("0003",message);
        }
    }

    public static void isEmpty(Collection c, String message){
        if(!CollectionUtils.isEmpty(c)){
            throw new BsException("0003",message);
        }
    }

    public static void isAllBlank(String string, String string2, String message) {
        if ((string == null || string.length() == 0) && (string2 == null || string2.length() == 0))
            throw new BsException("0003",message);
    }
}
