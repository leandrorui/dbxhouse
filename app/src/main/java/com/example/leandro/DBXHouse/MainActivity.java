package com.example.leandro.DBXHouse;

import android.content.Intent;
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
import android.widget.RadioGroup;
import android.widget.Spinner;

import android.widget.TextView;
import android.widget.Toast;

import com.example.leandro.DBXHouse.model.Cliente;

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
                findViewById(R.id.includemain).setVisibility(View.VISIBLE);
                findViewById(R.id.includecadastrousuario).setVisibility(View.INVISIBLE);
                findViewById(R.id.fab).setVisibility(View.VISIBLE);
            }
        });

        Button btnLogin = (Button)findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.includelogin).setVisibility(View.VISIBLE);
                findViewById(R.id.includelogin).setVisibility(View.INVISIBLE);
                findViewById(R.id.includemain).setVisibility(View.INVISIBLE);
                findViewById(R.id.novoUsuario).setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, "Logando... abestado", Toast.LENGTH_SHORT).show();
                configurarRecycler();
            }
        });

        TextView txtInserirUsuario = (TextView) findViewById(R.id.link_signup) ;
        txtInserirUsuario.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                //Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                //startActivityForResult(intent, REQUEST_SIGNUP);
                Toast.makeText(MainActivity.this, "Apertou, abestado", Toast.LENGTH_SHORT).show();
                findViewById(R.id.includelogin).setVisibility(View.INVISIBLE);
                findViewById(R.id.includenovousuario).setVisibility(View.VISIBLE);
                //findViewById(R.id.fab).setVisibility(View.INVISIBLE);
            }
        });

        Button btnSalvar = (Button)findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //carregando os campos
                //EditText txtNome = (EditText)findViewById(R.id.txtNome);
                //Spinner spnEstado = (Spinner)findViewById(R.id.spnEstado);
                //RadioGroup rgSexo = (RadioGroup)findViewById(R.id.rgSexo);
                //CheckBox chkVip = (CheckBox)findViewById(R.id.chkVip);

                //pegando os valores
                //String nome = txtNome.getText().toString();
                //String uf = spnEstado.getSelectedItem().toString();
                //boolean vip = chkVip.isChecked();
               // String sexo = rgSexo.getCheckedRadioButtonId() == R.id.rbMasculino ? "M" : "F";

                //salvando os dados
                //ClienteDAO dao = new ClienteDAO(getBaseContext());
                //boolean sucesso = dao.salvar(nome, sexo, uf, vip);
//                if(sucesso) {
//
//                    Cliente cliente = dao.retornarUltimo();
//                    adapter.adicionarCliente(cliente);
//
//                    //limpa os campos
//                    txtNome.setText("");
//                    rgSexo.setSelected(false);
//                    spnEstado.setSelection(0);
//                    chkVip.setChecked(false);
//
//                    Snackbar.make(view, "Salvou!", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
//                    findViewById(R.id.includemain).setVisibility(View.VISIBLE);
//                    findViewById(R.id.includecadastro).setVisibility(View.INVISIBLE);
//                    findViewById(R.id.fab).setVisibility(View.VISIBLE);
//                }else{
//                    Snackbar.make(view, "Erro ao salvar, consulte os logs!", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
//                }
//
//
            }
        });
//
    }

    RecyclerView recyclerView;
    ClienteAdapter adapter;

    private void configurarRecycler() {
        // Configurando o gerenciador de layout para ser uma lista.
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
        ClienteDAO dao = new ClienteDAO(this);
        adapter = new ClienteAdapter(dao.retornarTodos());
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
}
