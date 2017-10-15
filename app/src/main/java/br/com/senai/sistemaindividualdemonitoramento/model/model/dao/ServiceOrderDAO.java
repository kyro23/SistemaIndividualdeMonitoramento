package br.com.senai.sistemaindividualdemonitoramento.model.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.senai.sistemaindividualdemonitoramento.helper.DatabaseHelper;
import br.com.senai.sistemaindividualdemonitoramento.model.ServiceOrder;

/**
 * Created by OC on 04/10/2017.
 */

public class ServiceOrderDAO {

    private DatabaseHelper helper;

    public ServiceOrderDAO(Context context){
        helper = new DatabaseHelper(context);
    }

    public long create(ServiceOrder os){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues data = getContentValues(os);

        Long idInsert = db.insert("serviceorder", null, data);

        helper.close();

        return idInsert;
    }

    private ContentValues getContentValues(ServiceOrder os){
        ContentValues data = new ContentValues();
        data.put("id", os.getId());
        data.put("nome", os.getNome());
        data.put("videoInstrucao", os.getVideoInstrucao());
        data.put("fotoInstrucao", os.getFotoInstrucao());

        return data;
    }

    public List<ServiceOrder> read() {
        String sql = "SELECT * FROM serviceorder";
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<ServiceOrder> serviceOrders = new ArrayList<>();
        while (c.moveToNext()) {
            ServiceOrder os = new ServiceOrder();
            os.setId(c.getLong(c.getColumnIndex("id")));
            os.setNome(c.getString(c.getColumnIndex("nome")));
            os.setFotoInstrucao(c.getString(c.getColumnIndex("fotoInstrucao")));
            os.setVideoInstrucao(c.getString(c.getColumnIndex("videoInstrucao")));

            serviceOrders.add(os);
        }

        c.close();
        helper.close();
        return serviceOrders;
    }

    public void update(ServiceOrder os){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues data = getContentValues(os);
        String[] params = {os.getId().toString()};

        db.update("serviceorder", data, "id = ?", params);
        helper.close();
    }

    public void delete(ServiceOrder os){
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] params = {os.getId().toString()};

        db.delete("serviceorder", "id = ?", params);
        helper.close();
    }
}
