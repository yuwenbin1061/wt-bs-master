package wt.bs.param;


import lombok.Data;

@Data
public class ProblemParams {

    /**
     * 问题级别
     */
    private Integer levels=1;
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
     * 批次号
     */
    private String batchNo;

    /**
     * 试卷名称
     */
    private String paperName;
}
