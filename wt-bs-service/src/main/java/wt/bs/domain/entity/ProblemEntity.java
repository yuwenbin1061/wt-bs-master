package wt.bs.domain.entity;

import lombok.Data;
import wt.bs.domain.base.BaseEntityBean;

@Data
public class ProblemEntity extends BaseEntityBean {
	/**
	 * 用户id
	 */
	private Long id;
	/**
	 * 问题批次号,默认test_001,平时练习题
	 */
	private String batchNo;
	/**
	 * 教师编号T_1000开始
	 */
	private String teacherCode;
	/**
	 * 问题级别
	 */
	private Integer levels;
	/**
	 * 问题描述
	 */
	private String descs;
	/**
	 * 问题答案
	 */
	private String answer;
	/**
	 * 分数
	 */
	private Long score;
	/**
	 * 答对部分答案分数
	 */
	private Long partScore;
	/**
	 * 是否展示答案1 是 0 否
	 */
	private Integer isShow;

	/**
	 * 是否已回答
	 * 0: 未回答，1 : 已回答
	 */
	private Integer answered;
}