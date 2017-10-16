package br.com.senai.sistemaindividualdemonitoramento.model.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.senai.sistemaindividualdemonitoramento.helper.DatabaseHelper;
import br.com.senai.sistemaindividualdemonitoramento.model.Activity;

/**
 * Created by Junior on 15/10/2017.
 */

public class ActivityDAO {
    private DatabaseHelper helper;

    public ActivityDAO(Context context){
        helper = new DatabaseHelper(context);
    }

    public Long create(Activity activity){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues data = getContentValues(activity);

        Long insertId = db.insert("activity", null, data);

        helper.close();
        return insertId;
    }

    private ContentValues getContentValues(Activity activity){
        ContentValues data = new ContentValues();
        data.put("id", activity.getId());
        data.put("os", activity.getOs());
        data.put("producao", activity.getProducao());
        data.put("horaFim", activity.getHoraFim());
        data.put("perda", activity.getPerda());
        data.put("employer", activity.getFuncionario());
        data.put("horaInicio", activity.getHoraInicio());

        return data;
    }

    public List<Activity> read(){
        String sql = "SELECT * FROM activity";
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Activity> activities = new ArrayList<>();
        while(c.moveToNext()){
            Activity activity = new Activity();
            activity.setId(c.getLong(c.getColumnIndex("id")));
            activity.setHoraFim(c.getString(c.getColumnIndex("horaFim")));
            activity.setPerda(c.getInt(c.getColumnIndex("perda")));
            activity.setFuncionario(c.getInt(c.getColumnIndex("employer")));
            activity.setOs(c.getInt(c.getColumnIndex("os")));

            activities.add(activity);
        }
        helper.close();
        c.close();
        return activities;
    }

    public void update(Activity activity){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues data = getContentValues(activity);
        String[] params = {activity.getId().toString()};

        db.update("activity", data, "id = ?", params);
        helper.close();
    }

    public void delete(Activity activity){
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] params = {activity.getId().toString()};

        db.delete("activity", "id = ?", params);
        helper.close();
    }

    public Activity findById(Activity activity){
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] params = {activity.getId().toString()};
        Activity finded = new Activity();

        String sql = "SELECT * FROM activity WHERE id = ?";
        Cursor c = db.rawQuery(sql, params);
        if(c.moveToFirst()){
            finded.setId(c.getLong(c.getColumnIndex("id")));
            finded.setHoraInicio(c.getString(c.getColumnIndex("horaInicio")));
            finded.setOs(c.getInt(c.getColumnIndex("os")));
            finded.setHoraFim(c.getString(c.getColumnIndex("horaFim")));
            finded.setPerda(c.getInt(c.getColumnIndex("perda")));
            finded.setFuncionario(c.getInt(c.getColumnIndex("employer")));
            finded.setProducao(c.getInt(c.getColumnIndex("producao")));
        }

        helper.close();
        c.close();

        return finded;
    }
}
