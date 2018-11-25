package wt.bs.domain.entity;

import lombok.Data;

@Data
public class AnswerDetialEntity {
    /**
     * 方言
     */
    private String dialect;
    /**
     * 汉字
     */
    private String  kanji;
    /**
     * 解释
     */
    private String translation;
    /**
     * 词性
     */
    private String nominal;
}
