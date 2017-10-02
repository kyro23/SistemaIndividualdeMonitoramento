package br.com.senai.sistemaindividualdemonitoramento.model.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.senai.sistemaindividualdemonitoramento.model.Employer;

/**
 * Created by OC on 25/09/2017.
 */

public class EmployerDAO extends SQLiteOpenHelper{
    public EmployerDAO(Context context) {
        super(context, "sim", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE employer(" +
                "matricula INTEGER PRIMARY KEY," +
                "nome TEXT NOT NULL," +
                "senha TEXT NOT NULL," +
                "tipo TEXT NOT NULL);";

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public long create(Employer employer){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues data = getContentValues(employer);

       return db.insert("employer", null, data);
    }

    private ContentValues getContentValues(Employer employer){
        ContentValues data = new ContentValues();
        data.put("matricula", employer.getMatricula());
        data.put("nome", employer.getNome());
        data.put("senha", employer.getSenha());
        data.put("tipo", employer.getTipo());
        return data;
    }

    public List<Employer> read(){
        String sql = "SELECT * FROM employer";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Employer> employers = new ArrayList<Employer>();
        while (c.moveToNext()){
            Employer employer = new Employer();
            employer.setMatricula(c.getLong(c.getColumnIndex("matricula")));
            employer.setNome(c.getString(c.getColumnIndex("nome")));
            employer.setSenha(c.getString(c.getColumnIndex("senha")));
            employer.setTipo(c.getString(c.getColumnIndex("tipo")));

            employers.add(employer);
        }

        c.close();

        return employers;
    }

    public void update(Employer employer){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues data = getContentValues(employer);
        String[] params = {employer.getMatricula().toString()};
        db.update("employer", data, "matricula = ?", params);
    }

    public void delete(Employer employer){
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {employer.getMatricula().toString()};

        db.delete("employer", "matricula = ?", params);
    }

    public Employer login(Employer employer){
        SQLiteDatabase db = getReadableDatabase();
        System.out.println(employer.getMatricula());
        System.out.println(employer.getSenha());
        Cursor c = db.rawQuery("SELECT * FROM employer WHERE matricula = ? AND senha = ?", new String[]{employer.getMatricula().toString(), employer.getSenha()});
        Employer employerFind = new Employer();
       if(c.moveToFirst()){

           employerFind.setMatricula(c.getLong(c.getColumnIndex("matricula")));
           employerFind.setNome(c.getString(c.getColumnIndex("nome")));
           employerFind.setSenha(c.getString(c.getColumnIndex("senha")));
           employerFind.setTipo(c.getString(c.getColumnIndex("tipo")));
       }
       return  employerFind;
    }
}
