package com.example.leandro.dbx_house;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class ListaUsuarios extends AppCompatActivity {
    //ListView listaDeUsuarios = (ListView) findViewById(R.id.userview);
    //
    //EditText txtSenha = (EditText) findViewById(R.id.textViewGravaPasswd);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //listaDeUsuarios.setVisibility(View.INVISIBLE);
        setContentView(R.layout.activity_lista_usuarios);

        exibeListView();

        EditText txtEdit = (EditText) findViewById(R.id.textViewGravaLogin);
        txtEdit.setOnFocusChangeListener(new OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                mensagem("deu certo");
            }
        });

    }

    private void exibeListView() {
        String[] dados = new String[] { "Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread",
                "Honeycomb", "Ice Cream Sandwich", "Jelly Bean",
                "KitKat", "Lollipop", "Marshmallow", "Nougat" };

        List<String> usuarios = Arrays.asList(dados);
        ListView listview = (ListView) findViewById(R.id.userview);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, dados);
        listview.setAdapter(adapter);
        listview.requestFocus();
        listview.setVisibility(View.VISIBLE);
    }

    private void mensagem(String msg) {
        Toast toast = Toast.makeText(getApplicationContext(), msg,Toast.LENGTH_SHORT);
        toast.show();
    }
}
