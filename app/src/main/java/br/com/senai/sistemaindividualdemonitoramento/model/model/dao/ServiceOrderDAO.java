package br.com.senai.sistemaindividualdemonitoramento.model.model.dao;

import android.app.Service;
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
        data.put("metaPorHora", os.getMetaPorHora());

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
            os.setMetaPorHora(c.getInt(c.getColumnIndex("metaPorHora")));

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

    public ServiceOrder findById(ServiceOrder os){
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] params = {os.getId().toString()};
        ServiceOrder serviceOrder = new ServiceOrder();

        String sql = "SELECT * FROM serviceorder WHERE id = ?";
        Cursor c = db.rawQuery(sql, params);
        if(c.moveToFirst()){
            serviceOrder.setNome(c.getString(c.getColumnIndex("nome")));
            serviceOrder.setId(c.getLong(c.getColumnIndex("id")));
            serviceOrder.setVideoInstrucao(c.getString(c.getColumnIndex("videoInstrucao")));
            serviceOrder.setFotoInstrucao(c.getString(c.getColumnIndex("fotoInstrucao")));
            serviceOrder.setMetaPorHora(c.getInt(c.getColumnIndex("metaPorHora")));

        }
        helper.close();
        c.close();
        return serviceOrder;
    }
}
