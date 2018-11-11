package wt.bs.result;

import wt.bs.exception.BsException;

import java.util.HashMap;
import java.util.Map;

public class BaseResult {

    Map<String, String> map = new HashMap<>();

    private String code;
    private String msg;
    private Object value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return value;
    }

    public void setData(Object data) {
        this.value = data;
    }

    public BaseResult() {
    }

    public BaseResult(String code, String result) {
        this.code = code;
        this.msg = result;
    }

    public BaseResult(String code, String result, Object value) {
        this.code = code;
        this.msg = result;
        this.value = value;
    }

    public static BaseResult success() {
        return success("操作成功!");
    }

    public static BaseResult success(String msg) {
        return new BaseResult("0000", msg, "success");
    }

    public static BaseResult success(Object obj) {
        if (obj == null) {
            return new BaseResult("0000", "success", null);
        }
        return new BaseResult("0000", "success", obj);
    }

    public static BaseResult success(String msg, Object obj) {
        return new BaseResult("0000", msg, obj);
    }

    public static BaseResult failure(String msg) {
        return new BaseResult("0001", msg, "null");
    }

    public static BaseResult failure(BsException ex) {
        return new BaseResult(ex.getCode(),ex.getMessage());
    }


}
