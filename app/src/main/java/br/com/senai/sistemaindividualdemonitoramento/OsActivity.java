package br.com.senai.sistemaindividualdemonitoramento;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.senai.sistemaindividualdemonitoramento.model.ServiceOrder;
import br.com.senai.sistemaindividualdemonitoramento.model.model.dao.ServiceOrderDAO;

public class OsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_os);

        List<String> strOs = new ArrayList<>();

        ServiceOrderDAO dao = new ServiceOrderDAO(this);
        List<ServiceOrder> servicesOrders = dao.read();
        for(ServiceOrder os : servicesOrders){
            strOs.add("Id: "+os.getId()+" Nome: "+os.getNome()+" CaminhoFoto: "+os.getFotoInstrucao()+"" +
                    " CaminhoVideo: "+os.getVideoInstrucao()+" Meta por hora: "+os.getMetaPorHora());
        }


        ListView listOs = (ListView) findViewById(R.id.list_serviceOrders);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, strOs);
        listOs.setAdapter(adapter);
    }
}
