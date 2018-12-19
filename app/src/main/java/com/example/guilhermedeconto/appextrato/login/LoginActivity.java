package com.example.guilhermedeconto.appextrato.login;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guilhermedeconto.appextrato.R;
import com.example.guilhermedeconto.appextrato.list.StatementListActivity;

public class LoginActivity extends AppCompatActivity implements LoginActivityInteraction {
    private Button btnButton;
    private View vHeader;
    private TextView tvLogin;
    private TextView tvfacalogin;
    private TextView tvEmailMessage;
    private EditText etEmail;
    private TextView tvPasswordMessage;
    private EditText etPassword;
    private View vdivider;
    private LinearLayout lyFatherLogin;
    private ImageView icoded;
    Animation frombottom;
    Animation fromtop;
    Animation fromleft;
    Animation rotate;
    //Animation shake;
    private LoginActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        viewModel = new LoginActivityViewModel(this);
        //cria a funçao do botao login e redireciona o usuario para a proxima tela
        btnButton = findViewById(R.id.btnButton);
        vHeader = findViewById(R.id.vHeader);
        tvLogin = findViewById(R.id.tvLogin);
        tvfacalogin = findViewById(R.id.tvfacalogin);
        vdivider = findViewById(R.id.vdivider);
        icoded = findViewById(R.id.ivIcoded);

        //Animation shake = AnimationUtils.loadAnimation(this,R.anim.shake);
        etEmail = findViewById(R.id.etEmail);
        tvEmailMessage = findViewById(R.id.tvEmailMessage);
        etPassword = findViewById(R.id.etPassword);

        tvPasswordMessage = findViewById(R.id.tvPasswordMessage);
        lyFatherLogin = findViewById(R.id.lyFatherLogin);

        fromleft = AnimationUtils.loadAnimation(this, R.anim.fromleft);
        fromtop = AnimationUtils.loadAnimation(this, R.anim.fromtop);
        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);

        //lyFatherLogin.setAnimation(fromleft);
        etEmail.setAnimation(fromleft);
        tvEmailMessage.setAnimation(fromleft);
        etPassword.setAnimation(fromleft);
        tvPasswordMessage.setAnimation(fromleft);
        vdivider.setAnimation(fromtop);
        tvfacalogin.setAnimation(fromtop);
        tvLogin.setAnimation(fromtop);
        vHeader.setAnimation(fromtop);
        btnButton.setAnimation(frombottom);

        btnButton.setOnClickListener(v -> {
            viewModel.doLogin(etEmail.getText().toString(), etPassword.getText().toString());
        });
        //btnButton.startAnimation(shake);
        rotateAnimation(icoded);
    }

    private void rotateAnimation(View view) {
        //rotate = AnimationUtils.loadAnimation(this,R.anim.rotate);
        //icoded.startAnimation(rotate);
        view.animate().rotation(360f).setDuration(2000).setInterpolator(new OvershootInterpolator()).start();
    }

    @Override
    public void login() {
        startActivityForResult(new Intent(this, StatementListActivity.class)
                .putExtra("user", etEmail.getText().toString()), 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 10 && resultCode == 100)
            new AlertDialog.Builder(this).setTitle("Atenção").setMessage(data.getStringExtra("message")).show();

    }

    @Override
    public void onError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

}
