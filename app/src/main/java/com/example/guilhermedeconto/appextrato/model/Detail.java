package com.example.guilhermedeconto.appextrato.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Detail {
    @SerializedName("overdue_days")
    @Expose
    private String overdueDays;
    @SerializedName("overdue_date")
    @Expose
    private String overdueDate;
    @SerializedName("original_value")
    @Expose
    private String originalValue;
    @SerializedName("value_diff")
    @Expose
    private String valueDiff;
    @SerializedName("total_value")
    @Expose
    private String totalValue;
    @SerializedName("store")
    @Expose
    private String store;

    public String getOverdueDays() {
        return overdueDays;
    }

    public void setOverdueDays(String overdueDays) {
        this.overdueDays = overdueDays;
    }

    public String getOverdueDate() {
        return overdueDate;
    }

    public void setOverdueDate(String overdueDate) {
        this.overdueDate = overdueDate;
    }

    public String getOriginalValue() {
        return originalValue;
    }

    public void setOriginalValue(String originalValue) {
        this.originalValue = originalValue;
    }

    public String getValueDiff() {
        return valueDiff;
    }

    public void setValueDiff(String valueDiff) {
        this.valueDiff = valueDiff;
    }

    public String getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(String totalValue) {
        this.totalValue = totalValue;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }
}
