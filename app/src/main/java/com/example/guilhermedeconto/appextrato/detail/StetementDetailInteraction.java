package com.example.guilhermedeconto.appextrato.detail;

import com.example.guilhermedeconto.appextrato.model.User;

public interface StetementDetailInteraction {
    void onSucess(User user);

    void onFailure(String error);

    void onError(String error);
}
