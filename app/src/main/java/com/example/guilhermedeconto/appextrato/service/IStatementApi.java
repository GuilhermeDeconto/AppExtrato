package com.example.guilhermedeconto.appextrato.service;

import com.example.guilhermedeconto.appextrato.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IStatementApi {
    @GET("extract.php")
    Call<Response> getExtract(@Query("pwd")String pwd);


}
