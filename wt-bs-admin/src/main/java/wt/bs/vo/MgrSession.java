package wt.bs.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MgrSession {

    /**
     * 用户Id
     */
    private Long id;

    /**
     * 用户身份
     */
    private Integer identity;

    /**
     * 登录名
     */
    private String loginName;

}
