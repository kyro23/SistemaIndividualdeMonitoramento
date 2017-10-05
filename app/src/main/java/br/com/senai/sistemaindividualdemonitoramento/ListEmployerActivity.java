package br.com.senai.sistemaindividualdemonitoramento;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.senai.sistemaindividualdemonitoramento.R;
import br.com.senai.sistemaindividualdemonitoramento.model.Employer;
import br.com.senai.sistemaindividualdemonitoramento.model.model.dao.EmployerDAO;

public class ListEmployerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_employer);

        EmployerDAO employerDAO = new EmployerDAO(this);
        List<Employer> employers = employerDAO.read();

       List<String> strEmp = new ArrayList<>();
        for(Employer emp : employers){
            strEmp.add("Matricula: "+emp.getMatricula()+" Senha:"+emp.getSenha()+" Tipo: "+emp.getTipo()+" Nome"+emp.getNome());
        }


        ListView listView = (ListView) findViewById(R.id.list_employers);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, strEmp);

        listView.setAdapter(adapter);

    }
}
