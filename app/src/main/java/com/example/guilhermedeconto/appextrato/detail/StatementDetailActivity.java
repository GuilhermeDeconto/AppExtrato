package com.example.guilhermedeconto.appextrato.detail;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.guilhermedeconto.appextrato.R;
import com.example.guilhermedeconto.appextrato.adapter.StatementAdapter;
import com.example.guilhermedeconto.appextrato.list.StatementListInteraction;
import com.example.guilhermedeconto.appextrato.list.StatementListViewModel;
import com.example.guilhermedeconto.appextrato.model.Limits;
import com.example.guilhermedeconto.appextrato.model.Response;
import com.example.guilhermedeconto.appextrato.model.Statement;
import com.example.guilhermedeconto.appextrato.model.User;
import com.example.guilhermedeconto.appextrato.service.StatementService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class StatementDetailActivity extends AppCompatActivity implements StetementDetailInteraction {
    private TextView tvPastDue;
    private TextView tvCarnet;
    private TextView tvinstallmentdetail;
    private TextView tvTotalDue;
    private TextView tvNomeLogin;
    private ImageButton btnButton;
    private TextView tvAvailable;
    private TextView tvUsed;
    private TextView tvLimitTotal;
    private ProgressDialog progressDialog;
    private StatementDetailViewModel viewModel;
    private String user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement_details);
        viewModel = new StatementDetailViewModel(this);
        user = getIntent().getStringExtra("user");
        progressDialog = ProgressDialog.show(this, "Aguarde", "Carregando dados...");
        viewModel.initService(user);
        tvPastDue = findViewById(R.id.tvPastDue);
        tvCarnet = findViewById(R.id.tvCarnet);
        tvinstallmentdetail = findViewById(R.id.tvinstallmentdetail);
        tvTotalDue = findViewById(R.id.tvTotalDue);
        tvNomeLogin = findViewById(R.id.tvNomeLogin);
        tvAvailable = findViewById(R.id.tvAvailable);
        tvLimitTotal = findViewById(R.id.tvLimitTotal);
        tvUsed = findViewById(R.id.tvUsed);

        //cria a função da backarrow
        btnButton = findViewById(R.id.ibtnBackArrow2);
        //caso o usuário clique na back arrow, ele será redirecionado a tela anterior
        btnButton.setOnClickListener(v -> {

            super.onBackPressed();
        });
        loadDetails();
    }


    //recebe os dados da intent e coloca nas textview
    private void loadDetails() {
        tvPastDue.setText(getIntent().getStringExtra("PastDue"));
        tvTotalDue.setText(getIntent().getStringExtra("TotalDue"));
        tvinstallmentdetail.setText(getIntent().getStringExtra("installment"));
        tvCarnet.setText(getIntent().getStringExtra("carnet"));
    }

    //carrega as informaçoes do usuário ( nome )
    private void loadUser(User user) {
        tvNomeLogin.setText(user.getName());
    }

    //carrega as informaçoes da conta do usuário
    private void loadLimits(Limits limits) {
        tvLimitTotal.setText(limits.getTotal());
        tvUsed.setText(limits.getExpent());
        tvAvailable.setText(limits.getAvailable());
    }

    @Override
    public void onSucess(User user) {
        loadUser(user);
        loadLimits(user.getLimits());
        //Esconde o loader
        progressDialog.dismiss();
        progressDialog = null;
    }

    @Override
    public void onError(String error) {
        //Esconde o loader
        progressDialog.dismiss();
        progressDialog = null;

        setResult(RESULT_CANCELED, new Intent().putExtra("message", error));
        finish();
        //onError(response.body().getStatus());
    }

    @Override
    public void onFailure(String error) {
        //Esconde o loader
        progressDialog.dismiss();
        progressDialog = null;
    }
}
