package br.com.senai.sistemaindividualdemonitoramento.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Junior on 12/10/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE = "sim";
    private static int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tblEmployer = "CREATE TABLE employer(" +
                "matricula INTEGER PRIMARY KEY," +
                "nome TEXT NOT NULL," +
                "senha TEXT NOT NULL," +
                "tipo TEXT NOT NULL);";

        sqLiteDatabase.execSQL(tblEmployer);

        String tblServiceOrder = "CREATE TABLE serviceorder(" +
                "id INTEGER PRIMARY KEY, " +
                "nome TEXT NOT NULL, " +
                "videoInstrucao TEXT, " +
                "fotoInstrucao TEXT," +
                "metaPorHora INTEGER);";
        sqLiteDatabase.execSQL(tblServiceOrder);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
