package wt.bs.domain.entity;

import lombok.Data;
import wt.bs.domain.base.BaseEntityBean;

@Data
public class PapersEntity extends BaseEntityBean {

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
	 * 教师姓名
	 */
	private String teacherName;

	/**
	 * 试卷名
	 */
	private String paperName;
}