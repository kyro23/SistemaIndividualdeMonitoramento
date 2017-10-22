package br.com.senai.sistemaindividualdemonitoramento.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Junior on 12/10/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE = "sim";
    private static int VERSION = 3;

    public DatabaseHelper(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tblEmployer = "CREATE TABLE employer(" +
                "matricula INTEGER PRIMARY KEY," +
                "nome TEXT NOT NULL," +
                "senha TEXT NOT NULL," +
                "tipo TEXT NOT NULL" +
                ");";

        sqLiteDatabase.execSQL(tblEmployer);

        String tblServiceOrder = "CREATE TABLE serviceorder(" +
                "id INTEGER PRIMARY KEY, " +
                "nome TEXT NOT NULL, " +
                "videoInstrucao TEXT, " +
                "fotoInstrucao TEXT," +
                "metaPorHora INTEGER" +
                ");";
        sqLiteDatabase.execSQL(tblServiceOrder);

        String tblActivity = "CREATE TABLE activity(" +
                "id INTEGER PRIMARY KEY, " +
                "horaFim TEXT, " +
                "perda INTEGER, " +
                "employer INTEGER NOT NULL," +
                "producao INTEGER," +
                "horaInicio TEXT, " +
                "os INTEGER, " +
                "FOREIGN KEY(employer) REFERENCES employer(matricula)," +
                "FOREIGN KEY(os) REFERENCES serviceorder(id)" +
                ");";

        sqLiteDatabase.execSQL(tblActivity);

        insertDefaultUser(sqLiteDatabase);

    }

    private void insertDefaultUser(SQLiteDatabase db) {
        ContentValues data = new ContentValues();
        data.put("matricula", 1);
        data.put("nome", "Gestor");
        data.put("senha", "1234");
        data.put("tipo", "Gestor");

        db.insert("employer", null, data);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newViersion) {
        switch (oldVersion){
            case 1:
                String tblActivity = "CREATE TABLE activity(" +
                        "id INTEGER PRIMARY KEY, " +
                        "horaFim TEXT, " +
                        "perda INTEGER, " +
                        "employer INTEGER NOT NULL," +
                        "producao INTEGER," +
                        "horaInicio TEXT, " +
                        "os INTEGER, " +
                        "FOREIGN KEY(employer) REFERENCES employer(matricula)," +
                        "FOREIGN KEY(os) REFERENCES serviceorder(id)" +
                        ");";

                sqLiteDatabase.execSQL(tblActivity);

            case 2:
                insertDefaultUser(sqLiteDatabase);
            default:

                break;
        }
    }
}
