package com.example.guilhermedeconto.appextrato.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("total_overdue")
    @Expose
    private String totalOverdue;
    @SerializedName("total_due")
    @Expose
    private String totalDue;
    @SerializedName("installments")
    @Expose
    private List<Statement> statementList;
    @SerializedName("limits")
    @Expose
    private Limits limits;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotalOverdue() {
        return totalOverdue;
    }

    public void setTotalOverdue(String totalOverdue) {
        this.totalOverdue = totalOverdue;
    }

    public String getTotalDue() {
        return totalDue;
    }

    public void setTotalDue(String totalDue) {
        this.totalDue = totalDue;
    }

    public List<Statement> getStatementList() {
        return statementList;
    }

    public void setStatementList(List<Statement> statementList) {
        this.statementList = statementList;
    }

    public Limits getLimits() {
        return limits;
    }

    public void setLimits(Limits limits) {
        this.limits = limits;
    }
}
