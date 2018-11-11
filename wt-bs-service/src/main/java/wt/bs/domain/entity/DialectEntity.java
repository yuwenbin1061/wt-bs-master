package wt.bs.domain.entity;

import lombok.Data;
import wt.bs.domain.base.BaseEntityBean;

@Data
public class DialectEntity extends BaseEntityBean {
	private Long id;
	/**
	 * 地域
	 */
	private Integer region;
	/**
	 * 方言
	 */
	private String dialect;
	/**
	 * 汉字
	 */
	private String kanji;
	/**
	 * 译文
	 */
	private String translation;
	/**
	 * 词性
	 */
	private String nominal;
	/**
	 * 例句
	 */
	private String example;
}