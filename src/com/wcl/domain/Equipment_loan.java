package com.wcl.domain;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class Equipment_loan {
    private Integer id;
    private String equip_id;
    private String equip_name;
    private String equip_state;
    private String equip_manufacturer;
    private String loan_state;

    @Override
    public String toString() {
        return "Equipment_loan{" +
                "id=" + id +
                ", equip_id='" + equip_id + '\'' +
                ", equip_name='" + equip_name + '\'' +
                ", equip_state='" + equip_state + '\'' +
                ", equip_manufacturer='" + equip_manufacturer + '\'' +
                ", loan_state='" + loan_state + '\'' +
                '}';
    }
}
