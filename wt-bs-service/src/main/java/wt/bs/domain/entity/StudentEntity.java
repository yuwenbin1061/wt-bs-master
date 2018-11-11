package wt.bs.domain.entity;

import lombok.Data;
import wt.bs.domain.base.BaseEntityBean;

@Data
public class StudentEntity extends BaseEntityBean {
	/**
	 * 用户id
	 */
	private Long id;
	private Long userId;
	/**
	 * 学生编号ST_10000开始
	 */
	private String code;
	/**
	 * 学生级别，对应用户级别
	 */
	private Integer levels;
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
}