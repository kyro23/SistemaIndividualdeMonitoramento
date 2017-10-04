package br.com.senai.sistemaindividualdemonitoramento.model.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.senai.sistemaindividualdemonitoramento.model.ServiceOrder;

/**
 * Created by OC on 04/10/2017.
 */

public class ServiceOrderDAO extends SQLiteOpenHelper {


    public ServiceOrderDAO(Context context) {
        super(context, "sim", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE serviceorder(" +
                "id INTEGER PRIMARY KEY, " +
                "nome TEXT NOT NULL, " +
                "videoInstrucao TEXT, " +
                "fotoInstrucao TEXT);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long create(ServiceOrder os){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues data = getContentValues(os);
        return db.insert("serviceorder", null, data);
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
        SQLiteDatabase db = getReadableDatabase();

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
        return serviceOrders;
    }

    public void update(ServiceOrder os){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues data = getContentValues(os);
        String[] params = {os.getId().toString()};

        db.update("serviceorder", data, "id = ?", params);
    }

    public void delete(ServiceOrder os){
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {os.getId().toString()};

        db.delete("serviceorder", "id = ?", params);
    }
}
