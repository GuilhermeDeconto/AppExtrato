package com.example.guilhermedeconto.appextrato.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Statement {
    @SerializedName("past_due")
    @Expose
    private String pastDue;
    @SerializedName("carnet")
    @Expose
    private String carnet;
    @SerializedName("installment")
    @Expose
    private String installment;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("detail")
    @Expose
    private Detail detail;

    public String getPastDue() {
        return pastDue;
    }

    public void setPastDue(String pastDue) {
        this.pastDue = pastDue;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getInstallment() {
        return installment;
    }

    public void setInstallment(String installment) {
        this.installment = installment;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }
}
