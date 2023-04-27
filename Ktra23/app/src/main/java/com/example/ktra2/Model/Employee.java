package com.example.ktra2.Model;

import java.io.Serializable;

public class Employee implements Serializable {

    private Integer id;
    private String name;
    private String phone;
    private Integer year;
    private String gender;
    private String skill;

    public Employee() {
    }

    public Employee(Integer id, String name, String phone, Integer year, String gender, String skill) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.year = year;
        this.gender = gender;
        this.skill = skill;
    }

    public Employee(String name, String phone, Integer year, String gender, String skill) {
        this.name = name;
        this.phone = phone;
        this.year = year;
        this.gender = gender;
        this.skill = skill;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
