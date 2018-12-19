package com.example.guilhermedeconto.appextrato.detail;

import android.app.ProgressDialog;

import com.example.guilhermedeconto.appextrato.model.Response;
import com.example.guilhermedeconto.appextrato.service.StatementService;

import retrofit2.Call;
import retrofit2.Callback;

public class StatementDetailViewModel {
    private StetementDetailInteraction interaction;

    public StatementDetailViewModel(StetementDetailInteraction interaction) {
        this.interaction = interaction;
    }

    public void initService(String user) {

        StatementService kotlin = new StatementService();
        kotlin.getExtract(user, new Callback<Response>() {
            //verifica se teve resposta positiva do servidor
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                //caso tenha resposta passa as informaçoes para os metodos loadUser e loadLimits
                if (response.isSuccessful()) {
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
