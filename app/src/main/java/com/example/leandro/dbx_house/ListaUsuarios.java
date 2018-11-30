package com.example.leandro.dbx_house;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.leandro.dbx_house.repository.UsuarioRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListaUsuarios extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        UsuarioRepository user = new UsuarioRepository(this);
        setContentView(R.layout.activity_lista_usuarios);
        String[] dados = new String[] { "Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread",
                "Honeycomb", "Ice Cream Sandwich", "Jelly Bean",
                "KitKat", "Lollipop", "Marshmallow", "Nougat" };

        //String[] dados = user.SelecionarTodos();
        //Object[] usuarios = user.SelecionarTodos().toArray();
        //List<String> usuarios = Arrays.asList(dados);
        mensagem(user.SelecionarTodos().toString());

        final ListView listview = (ListView) findViewById(R.id.userview);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, dados);
        listview.setAdapter(adapter);
        listview.requestFocus();
        //listview.setVisibility(View.VISIBLE);

        EditText edittxt= (EditText) findViewById(R.id.textViewGravaLogin);
        edittxt.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // code to execute when EditText loses focus
                    listview.setVisibility(View.INVISIBLE);
                }
            }
        });

        final EditText editpass= (EditText) findViewById(R.id.textViewGravaPasswd);
        editpass.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // code to execute when EditText loses focus
                    listview.setVisibility(View.INVISIBLE);
                }
                if (!hasFocus) {
                    InputMethodManager imm =  (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    listview.setVisibility(View.VISIBLE);
                }
            }
        });

        final Button button = (Button)findViewById(R.id.buttonGrava);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //verificaLogin();
                listview.setVisibility(View.VISIBLE);
                listview.requestFocus();
            }
        });


    }


    private void mensagem(String msg) {
        Toast toast = Toast.makeText(getApplicationContext(), msg,Toast.LENGTH_SHORT);
        toast.show();
    }
}
