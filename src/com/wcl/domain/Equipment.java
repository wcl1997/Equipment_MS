package com.wcl.domain;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class Equipment {
    private Integer id;
    private String equip_id;
    private String equip_name;
    private String equip_state;
    private String equip_manufacturer;

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", equip_id='" + equip_id + '\'' +
                ", equip_name='" + equip_name + '\'' +
                ", equip_state='" + equip_state + '\'' +
                ", equip_manufacturer='" + equip_manufacturer + '\'' +
                '}';
    }
}
