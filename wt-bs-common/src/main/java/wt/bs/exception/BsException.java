package wt.bs.exception;

public class BsException extends RuntimeException {

    private String code;
    private String msg;
    private Throwable e;
    private Object data;

    public BsException(DoorExceptionEnum doorExceptionEnum){
        this.code = doorExceptionEnum.getCode();
        this.msg = doorExceptionEnum.getName();
    }

    public BsException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BsException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BsException(String code, String msg, Object data) {
        super(msg);
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BsException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.e = e;
    }

    public BsException(String code, String msg, Throwable e) {
        super(msg, e);
        this.code = code;
        this.msg = msg;
        this.e = e;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public Throwable getE() {
        return this.e;
    }

    public Object getData() {
        return this.data;
    }
}
