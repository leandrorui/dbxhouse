package com.example.leandro.dbx_house;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    boolean vazio, erro;
    EditText editLogin, editSenha;
    String login, senha;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                verificaLogin();
            }
        });



    }

    private void verificaLogin() {
        editLogin = (EditText)findViewById(R.id.textViewLogin);
        login = editLogin.getText().toString();
        editSenha = (EditText)findViewById(R.id.textViewPasswd);
        senha = editSenha.getText().toString();

        vazio = (login.isEmpty() || senha.isEmpty()) ? true : false;
        erro = (login.equalsIgnoreCase("admin") || senha.equals("123")) ? false : true;

        if(!vazio){
            if(!erro){
                fazLogin();
            }
            else mensagem("Falha no login");
        }
        else mensagem("Há campos vazios");
    }

    private void fazLogin() {
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }

    private void mensagem(String msg) {
        Toast toast = Toast.makeText(getApplicationContext(), msg,Toast.LENGTH_SHORT);
        toast.show();
    }
}


