package com.example.guilhermedeconto.appextrato.list;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.guilhermedeconto.appextrato.R;
import com.example.guilhermedeconto.appextrato.adapter.StatementAdapter;
import com.example.guilhermedeconto.appextrato.detail.StatementDetailActivity;
import com.example.guilhermedeconto.appextrato.detail.StetementDetailInteraction;
import com.example.guilhermedeconto.appextrato.model.Limits;
import com.example.guilhermedeconto.appextrato.model.Response;
import com.example.guilhermedeconto.appextrato.model.Statement;
import com.example.guilhermedeconto.appextrato.model.User;
import com.example.guilhermedeconto.appextrato.service.StatementService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class StatementListActivity extends AppCompatActivity implements StatementAdapter.OnItemClickListner, StatementListInteraction {
    private RecyclerView rvStatement;
    private StatementAdapter statementAdapter;
    private TextView tvAvailable;
    private TextView tvUsed;
    private TextView tvLimitTotal;
    private TextView tvNomeLogin;

    private ProgressDialog progressDialog;
    private ImageButton btnButton;
    private StatementListViewModel viewModel;
    private String user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement_list);

        viewModel = new StatementListViewModel(this);
        user = getIntent().getStringExtra("user");
        progressDialog = ProgressDialog.show(this, "Aguarde", "Carregando dados...");
        viewModel.initService(user);
        rvStatement = findViewById(R.id.rvStatement);
        tvAvailable = findViewById(R.id.tvAvailable);
        tvLimitTotal = findViewById(R.id.tvLimitTotal);
        tvUsed = findViewById(R.id.tvUsed);
        tvNomeLogin = findViewById(R.id.tvNomeLogin);

        //cria a funçao da back arrow, se o usuario clicar nele, sera redirecionado a tela anterior
        btnButton = findViewById(R.id.btnBackArrow);
        btnButton.setOnClickListener(v -> {
            setResult(RESULT_OK);
            super.onBackPressed();
        });

       /* nao iremos mais utilizar esse laco pois as informaçoes serao passadas para o
       retrofit que ira gerenciar os dados
       for(int i =0; i< 10; i++){
            Statement s = new Statement();
            s.setCarnet("123456");
            s.setValue("12345");
            s.setPastDue("12/12/45");
            s.setInstallment("12356678");
            statements.add(s);
        }*/

        //layoutmanager faz a mesma coisa no layout
        // rvStatement.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    /**
     * Passa a lista para o recycler view
     *
     * @param list
     */
    private void onExtractedLoaded(List<Statement> list) {
        statementAdapter = new StatementAdapter(this, list);
        statementAdapter.setListener(this);
        rvStatement.setAdapter(statementAdapter);
    }

    //carrega a informação do usuário ( nome )
    private void loadUser(User user) {
        tvNomeLogin.setText(user.getName());
    }

    // carrega as informaçoes da conta do usuário
    private void loadLimits(Limits limits) {
        tvLimitTotal.setText(limits.getTotal());
        tvUsed.setText(limits.getExpent());
        tvAvailable.setText(limits.getAvailable());
    }

    //mostra uma mensagem de erro
    private void onAlertError(String erro) {
        new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setPositiveButton("Ok", null)
                .setMessage(erro)
                .show();
    }

    //Passa as informaçoes para a intent do extrato que foi acessado
    @Override
    public void onItemClick(Statement statement) {
        Intent tela = new Intent(this, StatementDetailActivity.class);
        putDataIntent(statement, tela);
        startActivity(tela);

        //cria um dialog passando a data da fatura selecionada, nao esta mais sendo utilizado
        /*new AlertDialog.Builder(this)
                .setTitle("Detalhes")
                .setMessage(statement.getPastDue())
                .setPositiveButton("Ok",null)
                .show();*/
    }

    private void putDataIntent(Statement statement, Intent tela) {
        tela.putExtra("PastDue", statement.getPastDue());
        tela.putExtra("carnet", statement.getCarnet());
        tela.putExtra("installment", statement.getInstallment());
        tela.putExtra("TotalDue", statement.getValue());
        tela.putExtra("user", user);
    }

    @Override
    public void onSucess(User user) {
        loadUser(user);
        loadLimits(user.getLimits());
        onExtractedLoaded(user.getStatementList());
        //Esconde o loader
        progressDialog.dismiss();
        progressDialog = null;
    }

    @Override
    public void onError(String error) {
        //Esconde o loader
        progressDialog.dismiss();
        progressDialog = null;

        setResult(100, new Intent().putExtra("message", error));
        finish();
        //onError(response.body().getStatus());
    }

    @Override
    public void onFailure(String error) {
        //Esconde o loader
        progressDialog.dismiss();
        progressDialog = null;
        onAlertError(error);
    }
}
