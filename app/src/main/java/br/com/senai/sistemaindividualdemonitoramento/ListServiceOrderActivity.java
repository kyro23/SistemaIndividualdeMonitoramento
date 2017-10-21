package br.com.senai.sistemaindividualdemonitoramento;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.senai.sistemaindividualdemonitoramento.model.Employer;
import br.com.senai.sistemaindividualdemonitoramento.model.ServiceOrder;
import br.com.senai.sistemaindividualdemonitoramento.model.model.dao.ServiceOrderDAO;

public class ListServiceOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_service_order);

        Intent intent = getIntent();
        Employer employer = (Employer) intent.getSerializableExtra("employer");

        InitSidebar.fillSidebar(this, employer);

        ServiceOrderDAO dao = new ServiceOrderDAO(this);
        List<ServiceOrder> serviceOrders = dao.read();

        List<String> strServiceOrders = new ArrayList<>();
        for(ServiceOrder os :serviceOrders){
            strServiceOrders.add("Numero da OS: "+os.getId()+" Atividade: "+os.getNome()+" Meta por Hora: "+os.getMetaPorHora());
        }

        ListView listView = (ListView) findViewById(R.id.list_service_orders);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, strServiceOrders);

        listView.setAdapter(adapter);
    }
}
