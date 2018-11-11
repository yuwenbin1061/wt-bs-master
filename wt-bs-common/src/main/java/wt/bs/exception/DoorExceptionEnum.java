package wt.bs.exception;

public enum DoorExceptionEnum {

    /**
     * 错误编码
     */
    TYPE1("0001", "错误1"),

    TYPE2("0002", "错误2");

    private String code;

    private String name;

    DoorExceptionEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 根据code查询枚举对象
     * @param code code
     * @return DoorExceptionEnum
     */
    public static DoorExceptionEnum fromCode(String code){
        if (null == code || code.length() <= 0) {
            return null;
        }
        for (DoorExceptionEnum doorExceptionEnum : values()) {
            if (code.equals(doorExceptionEnum.getCode())) {
                return doorExceptionEnum;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
