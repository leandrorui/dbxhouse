package com.example.leandro.DBXHouse.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.example.leandro.DBXHouse.MainActivity;
import com.example.leandro.DBXHouse.Uteis.DbGateway;
import com.example.leandro.DBXHouse.model.Usuario;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.CONTEXT_RESTRICTED;

public class UsuarioDAO {

    private final String TABLE_CLIENTES = "Usuario";
    private DbGateway gw;

    public UsuarioDAO(Context ctx){
        gw = DbGateway.getInstance(ctx);
    }

    public boolean salvar
        (String login, int senha,
         String nome, String sobrenome, String dataNasc,
         boolean isAdmin, boolean isMobile, boolean isVisitante) {

        ContentValues cv = new ContentValues();
        cv.put("login", login);
        cv.put("senha", senha);

        cv.put("nome_usuario", nome);
        cv.put("sobrenome", sobrenome);
        cv.put("dataNasc", dataNasc);

        cv.put("isAdmin", isAdmin ? 1 : 0);
        cv.put("isMobile", isMobile ? 1 : 0);
        cv.put("isVisitante", isVisitante ? 1 : 0);

        return gw.getDatabase().insert(TABLE_CLIENTES, null, cv) > 0;

    }
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

    public boolean retornaUsuario(String login, String senha) {

        return retornarUltimo(login, Integer.parseInt(senha)) != null;

    }

    public Usuario retornarUltimo(){
        String senha = "";
        return retornarUltimo(null, Integer.parseInt(senha));
    }

    public Usuario retornarUltimo(String login, int senha){
        boolean parametros = false;
        parametros = !login.isEmpty() || !login.equalsIgnoreCase("");
        String whereClause = parametros ? ("where login=\"" + login + "\" and senha=\"" + senha + "\"") : "";
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM usuario "+whereClause+"ORDER BY id_usuario DESC", null);
        if(cursor.moveToFirst()){

            int id_usuario = cursor.getInt(cursor.getColumnIndex("id_usuario"));
            //login = cursor.getString(cursor.getColumnIndex("login"));
            //senha = cursor.getInt(cursor.getColumnIndex("senha"));

            String nome_usuario = cursor.getString(cursor.getColumnIndex("nome_usuario"));
            String sobrenome = cursor.getString(cursor.getColumnIndex("sobrenome"));
            String dataNasc = cursor.getString(cursor.getColumnIndex("dataNasc"));

            boolean isAdmin = cursor.getInt((cursor.getColumnIndex("isAdmin"))) > 0;
            boolean isMobile = cursor.getInt((cursor.getColumnIndex("isMobile"))) > 0;
            boolean isVisitante = cursor.getInt((cursor.getColumnIndex("isVisitante"))) > 0;

            cursor.close();
            return new Usuario(id_usuario, login, senha, nome_usuario, sobrenome, dataNasc, isAdmin, isMobile, isVisitante);
        }

        return null;
    }
}