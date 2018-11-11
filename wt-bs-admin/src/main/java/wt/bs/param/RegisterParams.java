package wt.bs.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterParams implements Serializable{

    private String name;

    private String loginName;

    private Integer identity;

    private Integer sex;

    private String pwd;

    private String sPwd;
}
