package com.example.leandro.DBXHouse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.leandro.DBXHouse.model.Cliente;
import com.example.leandro.DBXHouse.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private final String TABLE_CLIENTES = "Usuario";
    private DbGateway gw;

    public UsuarioDAO(Context ctx){
        gw = DbGateway.getInstance(ctx);
    }

/*    public boolean salvar(String nome, String sexo, String uf, boolean vip){
        ContentValues cv = new ContentValues();
        cv.put("Nome", nome);
        cv.put("Sexo", sexo);
        cv.put("UF", uf);
        cv.put("Vip", vip ? 1 : 0);
        return gw.getDatabase().insert(TABLE_CLIENTES, null, cv) > 0;
    }*/

    public List<Usuario> retornarTodos(){
        List<Usuario> usuarios = new ArrayList<>();
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM usuario", null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id_usuario"));
            String login = cursor.getString(cursor.getColumnIndex("login"));
            int senha = cursor.getInt(cursor.getColumnIndex("senha"));
            String nome = cursor.getString(cursor.getColumnIndex("nome_usuario"));
            String sobrenome = cursor.getString(cursor.getColumnIndex("sobrenome"));
            String dataNasc = cursor.getString(cursor.getColumnIndex("dataNasc"));
            boolean isAdmin = cursor.getInt(cursor.getColumnIndex("isAdmin")) > 0;
            boolean isMobile = cursor.getInt(cursor.getColumnIndex("isMobile")) > 0;
            boolean isVisitante = cursor.getInt(cursor.getColumnIndex("isVisitante")) > 0;
            usuarios.add(new Usuario(id, login, senha, nome, sobrenome, dataNasc, isAdmin, isMobile, isVisitante));
        }
        cursor.close();
        return usuarios;
    }

    /*public Cliente retornarUltimo(){
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Clientes ORDER BY ID DESC", null);
        if(cursor.moveToFirst()){
            int id = cursor.getInt(cursor.getColumnIndex("ID"));
            String nome = cursor.getString(cursor.getColumnIndex("Nome"));
            String sexo = cursor.getString(cursor.getColumnIndex("Sexo"));
            String uf = cursor.getString(cursor.getColumnIndex("UF"));
            boolean vip = cursor.getInt(cursor.getColumnIndex("Vip")) > 0;
            cursor.close();
            return new Cliente(id, nome, sexo, uf, vip);
        }

        return null;
    }*/
}