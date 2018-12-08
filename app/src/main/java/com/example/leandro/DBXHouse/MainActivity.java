package com.example.leandro.DBXHouse;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import com.example.leandro.DBXHouse.View.UsuarioAdapter;
import com.example.leandro.DBXHouse.model.Usuario;
import com.example.leandro.DBXHouse.repository.UsuarioDAO;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       EditText edtPassword = (EditText)findViewById(R.id.input_password);
       EditText edtEmail = (EditText)findViewById(R.id.input_email);

        edtEmail.setFocusable(true);
        edtPassword.setFocusable(true);

        FloatingActionButton novoUsuario = (FloatingActionButton) findViewById(R.id.novoUsuario);
        novoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.includemain).setVisibility(View.INVISIBLE);
                findViewById(R.id.includecadastrousuario).setVisibility(View.VISIBLE);
                findViewById(R.id.novoUsuario).setVisibility(View.INVISIBLE);
                findViewById(R.id.includelistauduarios).setVisibility(View.INVISIBLE);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.includemain).setVisibility(View.INVISIBLE);
                findViewById(R.id.includecadastrousuario).setVisibility(View.VISIBLE);
                findViewById(R.id.fab).setVisibility(View.INVISIBLE);

            }
        });

        Button btnCancelar = (Button)findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpaCampos();
                listarUsuarios();
                //Toast.makeText(MainActivity.this, "cancelando", Toast.LENGTH_SHORT).show();
            }
        });

        Button btnLogin = (Button)findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                listarUsuarios();
                Toast.makeText(MainActivity.this, "Logando... abestado", Toast.LENGTH_SHORT).show();
            }
        });

        TextView txtInserirUsuario = (TextView) findViewById(R.id.link_signup) ;
        txtInserirUsuario.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "Apertou, abestado", Toast.LENGTH_SHORT).show();
                findViewById(R.id.includelogin).setVisibility(View.INVISIBLE);
                findViewById(R.id.includelistauduarios).setVisibility(View.VISIBLE);

            }
        });

        Button insereUsuario = (Button)findViewById(R.id.btnSalvar);
        insereUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //carregando os campos
                EditText edtLogin = (EditText)findViewById(R.id.txtNovoLogin);
                EditText edtSenha = (EditText)findViewById(R.id.txtNovoSenha);

                EditText edtNome = (EditText)findViewById(R.id.txtNovoNomeUsuario);
                EditText edtSobrenome = (EditText)findViewById(R.id.txtNovoSobrenome);
                EditText edtDataNasc = (EditText)findViewById(R.id.txtDataNasc);

                CheckBox chkAdmin = (CheckBox)findViewById(R.id.chkAdmin);
                CheckBox chkMobile = (CheckBox)findViewById(R.id.chkMobile);
                CheckBox chkVisitante= (CheckBox)findViewById(R.id.chkVisitante);

                //pegando os valores
                String login = edtLogin.getText().toString();
                int senha = Integer.parseInt(edtSenha.getText().toString());

                String nome = edtNome.getText().toString();
                String sobrenome = edtSobrenome.getText().toString();
                String dataNasc = edtDataNasc.getText().toString();

                boolean isAdmin = chkAdmin.isChecked();
                boolean isMobile = chkMobile.isChecked();
                boolean isVisitante = chkVisitante.isChecked();


                //salvando os dados
                UsuarioDAO dao = new UsuarioDAO(getBaseContext());
                boolean sucesso = dao.salvar(login, senha, nome, sobrenome, dataNasc, isAdmin, isMobile, isVisitante);
                if(sucesso) {

                    Usuario usuario = dao.retornarUltimo();
                    adapter.adicionarUsuario(usuario);

                    //limpa os campos
                    limpaCampos();

                    Snackbar.make(view, "O usuário foi adicionado!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    listarUsuarios();
                }else{
                    Snackbar.make(view, "Erro ao salvar, consulte os logs!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }


            }
        });

    }

    private void listarUsuarios() {
        listarUsuarios(View.VISIBLE);
    }

    private void limpaCampos() {
        CheckBox isAdmin = (CheckBox)findViewById(R.id.chkAdmin);
        isAdmin.setChecked(false);
    }

    private void  listarUsuarios(int visibilidade) {

        if(visibilidade == 0) {
            apagarTelas();
        }

        //findViewById(R.id.includecadastrousuario).setVisibility(visibilidade);
        findViewById(R.id.includelistauduarios).setVisibility(visibilidade);
        findViewById(R.id.novoUsuario).setVisibility(visibilidade);
        //findViewById(R.id.includelogin).setVisibility(visibilidade);
        findViewById(R.id.includemain).setVisibility(visibilidade);
        findViewById(R.id.novoUsuario).setVisibility(visibilidade);

        configurarRecycler();
    }

    private void cadastrarUsuarios(int visibilidade) {

        if(visibilidade == 0) {
            apagarTelas();
        }

        findViewById(R.id.includecadastrousuario).setVisibility(visibilidade);
        findViewById(R.id.includelistauduarios).setVisibility(visibilidade);
        findViewById(R.id.novoUsuario).setVisibility(visibilidade);
        findViewById(R.id.includelogin).setVisibility(visibilidade);
        findViewById(R.id.includemain).setVisibility(visibilidade);
        findViewById(R.id.novoUsuario).setVisibility(visibilidade);

    }

    private void listarDispositivos(int visibilidade) {
//
//        boolean acende = intToBoolean(visibilidade);
//        if(acende) {
//            apagarTelas();
//        }
//
//        findViewById(R.id.includecadastrousuario).setVisibility(visibilidade);
//        findViewById(R.id.includelistauduarios).setVisibility(visibilidade);
//        findViewById(R.id.novoUsuario).setVisibility(visibilidade);
//        findViewById(R.id.includelogin).setVisibility(visibilidade);
//        findViewById(R.id.includemain).setVisibility(visibilidade);
//        findViewById(R.id.novoUsuario).setVisibility(visibilidade);

    }

    private void cadastrarDispositivos(int visibilidade) {
//
//        boolean acende = intToBoolean(visibilidade);
//        if(acende) {
//            apagarTelas();
//        }
//
//        findViewById(R.id.includecadastrousuario).setVisibility(visibilidade);
//        findViewById(R.id.includelistauduarios).setVisibility(visibilidade);
//        findViewById(R.id.novoUsuario).setVisibility(visibilidade);
//        findViewById(R.id.includelogin).setVisibility(visibilidade);
//        findViewById(R.id.includemain).setVisibility(visibilidade);
//        findViewById(R.id.novoUsuario).setVisibility(visibilidade);

    }

    private void listarComodos(int visibilidade) {
//
//        boolean acende = intToBoolean(visibilidade);
//        if(acende) {
//            apagarTelas();
//        }
//        findViewById(R.id.includecadastrousuario).setVisibility(visibilidade);
//        findViewById(R.id.includelistauduarios).setVisibility(visibilidade);
//        findViewById(R.id.novoUsuario).setVisibility(visibilidade);
//        findViewById(R.id.includelogin).setVisibility(visibilidade);
//        findViewById(R.id.includemain).setVisibility(visibilidade);
//        findViewById(R.id.novoUsuario).setVisibility(visibilidade);

    }

    private void cadastrarComodos(int visibilidade) {
//
//        boolean acende = intToBoolean(visibilidade);
//        if(acende) {
//            apagarTelas();
//        }
//            findViewById(R.id.includecadastrousuario).setVisibility(visibilidade);
//            findViewById(R.id.includelistauduarios).setVisibility(visibilidade);
//            findViewById(R.id.novoUsuario).setVisibility(visibilidade);
//            findViewById(R.id.includelogin).setVisibility(visibilidade);
//            findViewById(R.id.includemain).setVisibility(visibilidade);
//            findViewById(R.id.novoUsuario).setVisibility(visibilidade);

    }

    private void listarSensores(int visibilidade) {
//
//        boolean acende = intToBoolean(visibilidade);
//        if(acende) {
//            apagarTelas();
//        }
//        findViewById(R.id.includecadastrousuario).setVisibility(visibilidade);
//        findViewById(R.id.includelistauduarios).setVisibility(visibilidade);
//        findViewById(R.id.novoUsuario).setVisibility(visibilidade);
//        findViewById(R.id.includelogin).setVisibility(visibilidade);
//        findViewById(R.id.includemain).setVisibility(visibilidade);
//        findViewById(R.id.novoUsuario).setVisibility(visibilidade);

    }


    private void cadastrarSensores(int visibilidade) {
//        Toast.makeText(this, "apagando sensores...", Toast.LENGTH_SHORT).show();
//        boolean acende = intToBoolean(visibilidade);
//        if(acende) {
//            apagarTelas();
//        }
//
//        findViewById(R.id.includecadastrousuario).setVisibility(visibilidade);
//        findViewById(R.id.includelistauduarios).setVisibility(visibilidade);
//        findViewById(R.id.novoUsuario).setVisibility(visibilidade);
//        findViewById(R.id.includelogin).setVisibility(visibilidade);
//        findViewById(R.id.includemain).setVisibility(visibilidade);
//        findViewById(R.id.novoUsuario).setVisibility(visibilidade);
//

    }

    private void apagarTelas() {
        findViewById(R.id.includelogin).setVisibility(View.INVISIBLE);
        cadastrarUsuarios(View.INVISIBLE);
        listarDispositivos(View.INVISIBLE);
        cadastrarDispositivos(View.INVISIBLE);
        listarComodos(View.INVISIBLE);
        cadastrarComodos(View.INVISIBLE);
        listarSensores(View.INVISIBLE);
        cadastrarSensores(View.INVISIBLE);
    }

    RecyclerView recyclerView;
    UsuarioAdapter adapter;
    private void configurarRecycler() {
        // Configurando o gerenciador de layout para ser uma lista.
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView_usuarios);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
        UsuarioDAO dao = new UsuarioDAO(this);
        adapter = new UsuarioAdapter(dao.retornarTodos());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void nada(){}
}
