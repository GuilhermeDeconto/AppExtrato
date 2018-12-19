package com.example.guilhermedeconto.appextrato.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Limits {
    @SerializedName("total_due")
    @Expose
    private String totalDue;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("expent")
    @Expose
    private String expent;
    @SerializedName("available")
    @Expose
    private String available;

    public String getTotalDue() {
        return totalDue;
    }

    public void setTotalDue(String totalDue) {
        this.totalDue = totalDue;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getExpent() {
        return expent;
    }

    public void setExpent(String expent) {
        this.expent = expent;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }
}
