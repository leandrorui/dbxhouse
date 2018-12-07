package com.example.leandro.DBXHouse;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Crud.db";
    private static final int DATABASE_VERSION = 1;
    private final String CREATE_TABLE =
            "	CREATE TABLE IF NOT EXISTS usuario (	" +
                    "	 	id_usuario  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 	" +
                    "	 	login varchar(20), 	" +
                    "	 	senha INTEGER, 	" +
                    "	 	nome_usuario  varchar(30) NOT NULL, 	" +
                    "	 	sobrenome  varchar(50) NOT NULL, 	" +
                    "	 	dataNasc  varchar(20) NOT NULL, 	" +
                    "	 	isAdmin  BOOLEAN NOT NULL, 	" +
                    "	 	isMobile  BOOLEAN NOT NULL, 	" +
                    "	 	isVisitante  BOOLEAN NOT NULL); 	" +

                    "	CREATE TABLE IF NOT EXISTS tipo_sensor (	" +
                    "	 	id_tipo_sensor  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 	" +
                    "	 	nome_tipo_sensor  varchar(30) NOT NULL); 	" +

                    "	CREATE TABLE IF NOT EXISTS sensor (	" +
                    "	 	id_sensor  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 	" +
                    "	 	nome_sensor  INTEGER NOT NULL, 	" +
                    "	 	estado_sensor  BOOLEAN NOT NULL, 	" +
                    "	 	funcao_sensor  varchar(30) NOT NULL, 	" +
                    "	 	FOREIGN KEY(nome_sensor) REFERENCES tipo_sensor(id_tipo_sensor)); 	" +

                    "	CREATE TABLE IF NOT EXISTS registro_sensor (	" +
                    "	 	id_registro_sensor  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 	" +
                    "	 	sensor  INTEGER NOT NULL, 	" +
                    "	 	valor_registro  varchar(30) NOT NULL, 	" +
                    "	 	FOREIGN KEY(sensor) REFERENCES sensor(id_sensor)); 	" +

                    "	CREATE TABLE IF NOT EXISTS comodo (	" +
                    "	 	id_comodo  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 	" +
                    "	 	nome_comodo  varchar(30) NOT NULL, 	" +
                    "	 	metros_quadrados  INTEGER NOT NULL); 	" +

                    "	CREATE TABLE IF NOT EXISTS dispositivo (	" +
                    "	 	id_dispositivo  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 	" +
                    "	 	nome_dispositivo  varchar(30) NOT NULL, 	" +
                    "	 	comodo  INTEGER NOT NULL, 	" +
                    "	 	estado_dispositivo  BOOLEAN NOT NULL, 	" +
                    "	 	FOREIGN KEY(comodo) REFERENCES comodo(id_comodo)); 	" +

                    "	CREATE TABLE IF NOT EXISTS alerta (	" +
                    "	 	id_alerta  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 	" +
                    "	 	dispositivo  INTEGER NOT NULL, 	" +
                    "	 	data_registro  varchar(30) NOT NULL, 	" +
                    "	 	FOREIGN KEY(dispositivo) REFERENCES dispositivo(id_dispositivo)); 	";



    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}