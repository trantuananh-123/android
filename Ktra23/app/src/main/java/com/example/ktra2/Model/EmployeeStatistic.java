package com.example.ktra2.Model;

import java.io.Serializable;

public class EmployeeStatistic implements Serializable {

    private Integer total;
    private Integer totalBySkill;
    private String skill;

    public EmployeeStatistic() {
    }

    public EmployeeStatistic(Integer total, Integer totalBySkill, String skill) {
        this.total = total;
        this.totalBySkill = totalBySkill;
        this.skill = skill;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalBySkill() {
        return totalBySkill;
    }

    public void setTotalBySkill(Integer totalBySkill) {
        this.totalBySkill = totalBySkill;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
