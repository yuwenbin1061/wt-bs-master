package wt.bs.utils;

import java.util.UUID;

public class TokenUtils {

    public static String token(String str){
        return MD5Utils.md5(str+ UUID.randomUUID().toString());
    }
}
