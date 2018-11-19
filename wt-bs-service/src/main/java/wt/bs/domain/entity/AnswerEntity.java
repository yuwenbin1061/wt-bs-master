package wt.bs.domain.entity;

import lombok.Data;
import wt.bs.domain.base.BaseEntityBean;

@Data
public class AnswerEntity extends BaseEntityBean {
	/**
	 * 用户id
	 */
	private Long id;
	/**
	 * 问题批次号,默认test_001,平时练习题
	 */
	private String batchNo;
	/**
	 * 问题Id
	 */
	private Long problemId;
	/**
	 * 学生编号
	 */
	private String studentCode;
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
	 * 学生当前分数
	 */
	private Long currentScore;

	/**
	 * 未回答答案
	 */
	private String doingAnswer;
	/**
	 * 回答正确答案
	 */
	private String doneAnswer;
	/**
	 * 回答失败答案
	 */
	private String failAnswer;

	/**
	 * 起始时间
	 */
	private String fromDate;

	/**
	 * 终止时间
	 */
	private String toDate;

	/**
	 * 学生姓名
	 */
	private String stdName;
}