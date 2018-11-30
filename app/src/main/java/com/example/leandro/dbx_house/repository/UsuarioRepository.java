package com.example.leandro.dbx_house.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import com.example.leandro.dbx_house.Uteis.DatabaseUtil;
import com.example.leandro.dbx_house.model.UsuarioModel;

public class UsuarioRepository {
    DatabaseUtil databaseUtil;

    /***
     * CONSTRUTOR
     * @param context
     */
    public UsuarioRepository(Context context){

        databaseUtil =  new DatabaseUtil(context);

    }

    /***
     * SALVA UM NOVO REGISTRO NA BASE DE DADOS
     * @param usuarioModel
     */
    public void Salvar(UsuarioModel usuarioModel){

        ContentValues contentValues = gravaDados(usuarioModel);

        /*EXECUTANDO INSERT DE UM NOVO REGISTRO*/
        databaseUtil.GetConexaoDataBase().insert("tb_usuario",null,contentValues);

    }

    @NonNull
    public ContentValues gravaDados(UsuarioModel usuarioModel) {
        ContentValues contentValues =  new ContentValues();
        /*MONTANDO OS PARAMETROS PARA SEREM SALVOS*/
        contentValues.put("id_usuario ",        usuarioModel.getIdUsuario());
        contentValues.put("ds_nomeusuario",     usuarioModel.getNomeUsuario());
        contentValues.put("ds_senha",           usuarioModel.getSenhaUsuario());
        contentValues.put("fl_admin",           booleanToInt(usuarioModel.isAdmin()));
        return contentValues;
    }

    public static int booleanToInt(boolean value) {
        // Convert true to 1 and false to 0.
        return value ? 1 : 0;
    }
    public static boolean intToBoolean(int value) {
        // Convert true to 1 and false to 0.
        return value == 1 ? true : false;
    }

    /***
     * ATUALIZA UM REGISTRO JÁ EXISTENTE NA BASE
     * @param usuarioModel
     */
    public void Atualizar(UsuarioModel usuarioModel){

        ContentValues contentValues = gravaDados(usuarioModel);

        /*REALIZANDO UPDATE PELA CHAVE DA TABELA*/
        databaseUtil.GetConexaoDataBase().update("tb_usuario", contentValues, "id_usuario = ?", new String[]{Integer.toString(usuarioModel.getIdUsuario())});
    }

    /***
     * EXCLUI UM REGISTRO PELO CÓDIGO
     * @param codigo
     * @return
     */
    public Integer Excluir(int codigo){

        //EXCLUINDO  REGISTRO E RETORNANDO O NÚMERO DE LINHAS AFETADAS
        return databaseUtil.GetConexaoDataBase().delete("tb_usuario","id_usuario = ?", new String[]{Integer.toString(codigo)});
    }

    /***
     * CONSULTA UMA PESSOA CADASTRADA PELO CÓDIGO
     * @param codigo
     * @return
     */
    public UsuarioModel GetUsuario(int codigo){


        Cursor cursor =  databaseUtil.GetConexaoDataBase().rawQuery("SELECT * FROM tb_usuario WHERE id_usuario= "+ codigo,null);

        cursor.moveToFirst();

        ///CRIANDO UM NOV USUARIO
        UsuarioModel usuarioModel =  new UsuarioModel();

        //ADICIONANDO OS DADOS DO USUARIO
        adicionarDadosUsuario(cursor, usuarioModel);

        //RETORNANDO A PESSOA
        return usuarioModel;

    }

    public void adicionarDadosUsuario(Cursor cursor, UsuarioModel usuarioModel) {
        usuarioModel.setIdUsuario(cursor.getInt(cursor.getColumnIndex("id_usuario")));
        usuarioModel.setNomeUsuario(cursor.getString(cursor.getColumnIndex("ds_nomeusuario")));
        usuarioModel.setSenhaUsuario(cursor.getString(cursor.getColumnIndex("ds_senha")));
        usuarioModel.setAdmin(intToBoolean(cursor.getInt(cursor.getColumnIndex("fl_admin"))));
    }

    /***
     * CONSULTA TODAS AS PESSOAS CADASTRADAS NA BASE
     * @return
     */
    public List<UsuarioModel> SelecionarTodos(){

        List<UsuarioModel> usuarios = new ArrayList<UsuarioModel>();


        //MONTA A QUERY A SER EXECUTADA
        StringBuilder stringBuilderQuery = new StringBuilder();
        stringBuilderQuery.append(" SELECT id_usuario,      ");
        stringBuilderQuery.append(" ds_nomeusuario,        ");
        stringBuilderQuery.append(" ds_senha,    ");
        stringBuilderQuery.append(" fl_admin,        ");
        stringBuilderQuery.append(" FROM  tb_usuario       ");
        stringBuilderQuery.append(" ORDER BY ds_nomeusuario       ");


        //CONSULTANDO OS REGISTROS CADASTRADOS
        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery(stringBuilderQuery.toString(), null);

        /*POSICIONA O CURSOR NO PRIMEIRO REGISTRO*/
        cursor.moveToFirst();


        UsuarioModel usuarioModel;

        //REALIZA A LEITURA DOS REGISTROS ENQUANTO NÃO FOR O FIM DO CURSOR
        while (!cursor.isAfterLast()){

            /* CRIANDO UMA NOVA USUÁRIOS */
            usuarioModel =  new UsuarioModel();

            //ADICIONANDO OS DADOS DO USUARIO
            adicionarDadosUsuario(cursor, usuarioModel);

            //ADICIONANDO UMA PESSOA NA LISTA
            usuarios.add(usuarioModel);

            //VAI PARA O PRÓXIMO REGISTRO
            cursor.moveToNext();
        }

        //RETORNANDO A LISTA DE USUÁRIOS
        return usuarios;

    }
}
