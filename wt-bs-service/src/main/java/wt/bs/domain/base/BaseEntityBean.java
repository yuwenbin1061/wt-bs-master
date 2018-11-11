package wt.bs.domain.base;



import java.util.Date;

public class BaseEntityBean extends Criteria {
    private static final long serialVersionUID = -7793739903799136331L;
    private Integer sysVersion;
    private Date createTime;
    private Date updateTime;
    private String createUser;
    private String updateUser;
    private Integer yn = Integer.valueOf(1);

    private String createTimeStr;
    private String updateTimeStr;

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getUpdateTimeStr() {
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }

    public BaseEntityBean() {
    }

    public Integer getSysVersion() {
        return this.sysVersion;
    }

    public void setSysVersion(Integer sysVersion) {
        this.sysVersion = sysVersion;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getYn() {
        return this.yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }

    public BaseEntityBean init() {
        this.createUser = "";
        this.updateUser = "";
        this.yn = Integer.valueOf(1);
        return this;
    }
}
