package com.wcl.domain;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class Purchase {
    private Integer id;
    private String p_ename;
    private Double p_price;
    private String p_manufacture;
    private Integer p_amount;
    private String p_state = "待审核";

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", p_ename='" + p_ename + '\'' +
                ", p_price=" + p_price +
                ", p_manufacture='" + p_manufacture + '\'' +
                ", p_amount=" + p_amount +
                '}';
    }
}
