package br.com.senai.sistemaindividualdemonitoramento;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.senai.sistemaindividualdemonitoramento.model.Employer;
import br.com.senai.sistemaindividualdemonitoramento.model.model.dao.EmployerDAO;

public class ListUsersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);

        Intent intent = getIntent();


        Employer employer = (Employer) intent.getSerializableExtra("employer");
        InitSidebar.fillSidebar(this, employer);

        EmployerDAO dao = new EmployerDAO(this);
        List<Employer> employers = dao.read();

        List<String> strEmployers = new ArrayList<>();
        for(Employer emp : employers){
            strEmployers.add("Matrícula: "+emp.getMatricula()+"  Nome: "+emp.getNome()+"  Tipo: "+emp.getTipo());
        }

        ListView listView = (ListView) findViewById(R.id.list_users);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, strEmployers);

        listView.setAdapter(adapter);
    }
}
