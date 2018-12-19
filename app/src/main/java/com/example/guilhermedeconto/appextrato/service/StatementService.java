package com.example.guilhermedeconto.appextrato.service;

import com.example.guilhermedeconto.appextrato.model.Response;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StatementService {
    private IStatementApi api;
    private final String baseUrl = "http://www.icoded.com.br/api/";

    public StatementService() {
        // Faz o logcat
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                //converte o objeto em gson e vice versa
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //contem todas as rotas
        api = retrofit.create(IStatementApi.class);
    }

    /**
     * Pega toda a lista de extratos e pede a descrição dos parametros
     * @param pwd variavel q define o usuario
     * @param callback oq ele deve fazer depois de receber informacao do servidor
     */
    public void getExtract(String pwd, Callback<Response> callback){
        api.getExtract(pwd).enqueue(callback);
    }
}
