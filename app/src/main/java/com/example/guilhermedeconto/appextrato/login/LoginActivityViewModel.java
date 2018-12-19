package com.example.guilhermedeconto.appextrato.login;

public class LoginActivityViewModel {
    private LoginActivityInteraction interaction;

    public LoginActivityViewModel(LoginActivityInteraction interaction) {
        this.interaction = interaction;
    }

    public void doLogin(String name, String password) {
        if (!name.isEmpty() && !password.isEmpty() && name.equals(password))
            interaction.login();
        else interaction.onError("Campos inválidos, tente novamente");
    }

}
