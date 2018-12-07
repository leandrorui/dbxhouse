package com.example.leandro.DBXHouse;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DBXHouse.db";
    private static final int DATABASE_VERSION = 1;
    private final String CREATE_TABLE =
            new StringBuilder()
                    .append("CREATE TABLE IF NOT EXISTS usuario (	")
                    .append("	 	id_usuario  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 	")
                    .append("	 	login varchar(20), 	")
                    .append("	 	senha INTEGER, 	")
                    .append("	 	nome_usuario  varchar(30) NOT NULL, 	")
                    .append("	 	sobrenome  varchar(50) NOT NULL, 	")
                    .append("	 	dataNasc  varchar(20) NOT NULL, 	")
                    .append("	 	isAdmin  BOOLEAN NOT NULL, 	")
                    .append("	 	isMobile  BOOLEAN NOT NULL, 	")
                    .append("	 	isVisitante  BOOLEAN NOT NULL); 	")
                    .append("CREATE TABLE IF NOT EXISTS tipo_sensor (	")
                    .append("	 	id_tipo_sensor  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 	")
                    .append("	 	nome_tipo_sensor  varchar(30) NOT NULL); 	")
                    .append("CREATE TABLE IF NOT EXISTS sensor (	")
                    .append("	 	id_sensor  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 	")
                    .append("	 	nome_sensor  INTEGER NOT NULL, 	")
                    .append("	 	estado_sensor  BOOLEAN NOT NULL, 	")
                    .append("	 	funcao_sensor  varchar(30) NOT NULL, 	")
                    .append("	 	FOREIGN KEY(nome_sensor) REFERENCES tipo_sensor(id_tipo_sensor)); 	")
                    .append("CREATE TABLE IF NOT EXISTS registro_sensor (	")
                    .append("	 	id_registro_sensor  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 	")
                    .append("	 	sensor  INTEGER NOT NULL, 	")
                    .append("	 	valor_registro  varchar(30) NOT NULL, 	")
                    .append("	 	FOREIGN KEY(sensor) REFERENCES sensor(id_sensor)); 	")
                    .append("CREATE TABLE IF NOT EXISTS comodo (	")
                    .append("	 	id_comodo  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 	")
                    .append("	 	nome_comodo  varchar(30) NOT NULL, 	")
                    .append("	 	metros_quadrados  INTEGER NOT NULL); 	")
                    .append("CREATE TABLE IF NOT EXISTS dispositivo (	")
                    .append("	 	id_dispositivo  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 	")
                    .append("	 	nome_dispositivo  varchar(30) NOT NULL, 	")
                    .append("	 	comodo  INTEGER NOT NULL, 	")
                    .append("	 	estado_dispositivo  BOOLEAN NOT NULL, 	")
                    .append("	 	FOREIGN KEY(comodo) REFERENCES comodo(id_comodo)); 	")
                    .append("CREATE TABLE IF NOT EXISTS alerta (	")
                    .append("	 	id_alerta  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 	")
                    .append("	 	dispositivo  INTEGER NOT NULL, 	")
                    .append("	 	data_registro  varchar(30) NOT NULL, 	")
                    .append("	 	FOREIGN KEY(dispositivo) REFERENCES dispositivo(id_dispositivo)); 	")
                    .toString();

    public final String INSERT_INTO =
            new StringBuilder()
                    .append("INSERT INTO usuario	")
                    .append("	VALUES ('1', 'leandro.rui', '12378', 'Leandro', 'Rui', '15111983', '1', '1', '0');")
                    .append("INSERT INTO tipo_sensor (nome_tipo_sensor)	")
                    .append("		VALUES 	('Luminosidade'),")
                    .append("				('Temperatura'),")
                    .append("				('Umidade'),")
                    .append("				('Proximidade');")
                    .append("INSERT INTO comodo 	(nome_comodo, metros_quadrados)")
                    .append("	VALUES 	('Garagem', '12'),")
                    .append("				('Sala', '10'),")
                    .append("				('Quarto', '7'),")
                    .append("				('Cozinha', '11');")
                    .append("INSERT INTO dispositivo (nome_dispositivo, comodo, estado_dispositivo)	")
                    .append("	VALUES 	('Poste', '1', '0'),")
                    .append("				('Sistema de segurança', '1', '0'),")
                    .append("				('Portão da garagem', '1', '0'),")
                    .append("				('Alarme', '1', '0'),")
                    .append("				('Eletrodoméstico', '2', '0'),")
                    .append("				('Campainha', '4', '0'),")
                    .append("				('LCD', '3', '0'),")
                    .append("				('Cartão SD', '3', '0');")
                    .toString();



    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(INSERT_INTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}