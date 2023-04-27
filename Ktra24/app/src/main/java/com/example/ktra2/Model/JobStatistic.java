package com.example.ktra2.Model;

import java.io.Serializable;

public class JobStatistic implements Serializable {

    private int total;
    private String status;

    public JobStatistic() {
    }

    public JobStatistic(int total, String status) {
        this.total = total;
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
