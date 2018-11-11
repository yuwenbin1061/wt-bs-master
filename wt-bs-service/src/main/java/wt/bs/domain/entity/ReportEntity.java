package wt.bs.domain.entity;

import lombok.Data;

@Data
public class ReportEntity {

    /**
     * 答案
     */
    private String answer;

    /**
     * 次数
     */
    private Integer count;

}
