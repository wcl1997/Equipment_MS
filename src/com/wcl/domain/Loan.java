package com.wcl.domain;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Loan {
    private Integer id;
    private String loan_eid;
    private String loan_use;
    private String loan_time;
    private Long loan_tel;
    private String loan_ename;
    private String loan_state = "待审核";

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", loan_eid='" + loan_eid + '\'' +
                ", loan_use='" + loan_use + '\'' +
                ", loan_time='" + loan_time + '\'' +
                ", loan_tel=" + loan_tel +
                ", loan_ename='" + loan_ename + '\'' +
                ", loan_state='" + loan_state + '\'' +
                '}';
    }
}
