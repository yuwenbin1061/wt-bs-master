package wt.bs.utils;

public class RedisKeyConstant {

    public static String loginToken(String token){
        return "TOKEN:" + token ;
    }
}