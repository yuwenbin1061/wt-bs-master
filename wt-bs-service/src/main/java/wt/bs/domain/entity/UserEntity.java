package wt.bs.domain.entity;

import lombok.Data;
import wt.bs.domain.base.BaseEntityBean;

@Data
public class UserEntity extends BaseEntityBean {
    /**
     * 用户id
     */
    private Long    id;
    /**
     * 身份1 教师 2 学生
     */
    private Integer identity;
    /**
     * 登录名
     */
    private String  loginName;
    /**
     * 登录密码
     */
    private String  pwd;
}