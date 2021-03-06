package com.example.leandro.DBXHouse;

import android.app.Activity;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import com.example.leandro.DBXHouse.View.UsuarioAdapter;
import com.example.leandro.DBXHouse.model.Usuario;
import com.example.leandro.DBXHouse.repository.UsuarioDAO;
import com.example.leandro.DBXHouse.controler.SigninActivity;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText usernameField,passwordField;
    private TextView status,role,method;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Essa porcaria deveria implementar um botão "voltar" na barra de navegação
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
//        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
//        getSupportActionBar().setTitle("Seu titulo aqui");


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


        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View viewById = findViewById(R.id.includemenu);
                if (viewById.getVisibility() == View.VISIBLE) {
                    Toast.makeText(MainActivity.this, "toolbar", Toast.LENGTH_SHORT).show();

                }

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

        Button btnListarUsuarios = (Button)findViewById(R.id.btnListarUsuarios);
        btnListarUsuarios.setOnClickListener(new Button.OnClickListener() {
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
                //if(verificaLogin())listarUsuarios();
                if(verificaLogin())exibirMenu();
            }
        });

        Button btnGaragem = (Button)findViewById(R.id.btnVerGaragem);
        btnGaragem.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //visibilidade é um int: zero para true ou quatro para false.
                exibirGaragem(0);
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

    public void loginPost(View view){
        String username = "Teste";
        String password = "Teste";
        method.setText("Post Method");
        new SigninActivity(this).execute(username,password);
    }

    private boolean verificaLogin() {

        EditText edtEmail = (EditText)findViewById(R.id.input_email);
        EditText edtPassword = (EditText)findViewById(R.id.input_password);

        String login = edtEmail.getText().toString();
        String senha = edtPassword.getText().toString();

        List<String> listaDeStrings = Arrays.asList(login, senha);

        if(!verificaVazios(listaDeStrings)){
            UsuarioDAO dao = new UsuarioDAO(this);
            if(!dao.retornaUsuario(login, senha))
                Toast.makeText(MainActivity.this, "falha no login", Toast.LENGTH_SHORT).show();
            else return true;
        }
        else Toast.makeText(MainActivity.this, "Há campos vazios", Toast.LENGTH_SHORT).show();


        /*
        * Não tem a responsabilidade de fazer login. essa responsabilidade é do botão login ou
        * do método fazLogin();
        * O método verificaLogin apenas responde se pode ou não (boolean) fazer login.
        * */
        //listarUsuarios(View.VISIBLE);
        return false;
    }

    private boolean verificaVazios(List<String> listaDeStrings) {
        //True para "há campos vazios" e false para "Tudo certo, pode ser feliz"

        for(String lista : listaDeStrings) {
            if(lista.isEmpty() || lista.equalsIgnoreCase("")) return true;
        }

        return false;
    }

    private void listarUsuarios() {
        listarUsuarios(View.VISIBLE);
    }
    private void exibirMenu() {
        exibirMenu(View.VISIBLE);
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
    private void  exibirMenu(int visibilidade) {

        if(visibilidade == 0) {
            apagarTelas();
        }

        findViewById(R.id.includemenu).setVisibility(visibilidade);
        hideKeyboard(this);

        //configurarRecycler();
    }

    private void  exibirGaragem(int visibilidade) {

        if(visibilidade == 0) {
            apagarTelas();
        }

        findViewById(R.id.includegaragem).setVisibility(visibilidade);
        hideKeyboard(this);

        //configurarRecycler();
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
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
        exibirMenu(View.INVISIBLE);
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
