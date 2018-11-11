package wt.bs.enums;

public enum ResultCodeEnum {

    /**
     * 返回code标识
     */
    NO_LOGIN("0001", "尚未登录"),
    LOGIN_TIMEOUT("0002", "登陆失效"),
    PROCESSING_FAILURE("0003", "操作失败"),
    NO_POWER("0004", "无权限访问");

    private String code;
    private String msg;
    ResultCodeEnum(String code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public String getCode()
    {
        return this.code;
    }

    public static ResultCodeEnum valueOf(int code)
    {
        for (ResultCodeEnum enums:ResultCodeEnum.values()){
            if (enums.getCode().equals(code)){
                return enums;
            }
        }
        return null;
    }
}