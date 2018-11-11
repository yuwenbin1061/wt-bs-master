package wt.bs.domain.entity;

import lombok.Data;
import wt.bs.domain.base.BaseEntityBean;

@Data
public class TeacherEntity extends BaseEntityBean {
	/**
	 * 用户id
	 */
	private Long id;

	private Long userId;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 登录名
	 */
	private String loginName;
	/**
	 * 登录密码
	 */
	private String pwd;
	/**
	 * 教师性别
	 */
	private Integer sex;
	/**
	 * 教师code
	 */
	private String code;
}