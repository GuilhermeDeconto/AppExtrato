package com.example.guilhermedeconto.appextrato.list;

import com.example.guilhermedeconto.appextrato.model.User;

public interface StatementListInteraction {
    void onSucess(User user);

    void onFailure(String error);

    void onError(String error);
}
