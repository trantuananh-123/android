package com.example.ktra2.Model;

import java.io.Serializable;

public class Job implements Serializable {

    private Integer id;
    private String name;
    private String content;
    private String date;
    private String status;
    private Boolean type;

    public Job() {
    }

    public Job(Integer id, String name, String content, String date, String status, Boolean type) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.date = date;
        this.status = status;
        this.type = type;
    }

    public Job(String name, String content, String date, String status, Boolean type) {
        this.name = name;
        this.content = content;
        this.date = date;
        this.status = status;
        this.type = type;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }
}
