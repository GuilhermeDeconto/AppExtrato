package com.example.guilhermedeconto.appextrato.list;

import android.app.ProgressDialog;

import com.example.guilhermedeconto.appextrato.model.Response;
import com.example.guilhermedeconto.appextrato.service.StatementService;

import retrofit2.Call;
import retrofit2.Callback;

public class StatementListViewModel {

    private StatementListInteraction interaction;

    public StatementListViewModel(StatementListInteraction interaction) {
        this.interaction = interaction;
    }

    public void initService(String user) {

        StatementService kotlin = new StatementService();
        kotlin.getExtract(user, new Callback<Response>() {

            //verifica se teve resposta positiva do servidor
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                //caso tenha resposta positiva, passa as informaçoes recebidas para os metodos
                if (response.isSuccessful() && response.body().getData() != null) {
                    interaction.onSucess(response.body().getData());
                } else {
                    interaction.onError(response.body().getStatus());
                }
            }

            //verifica se teve resposta negativa do servidor
            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                interaction.onFailure(t.getMessage());
            }
        });
    }
}
