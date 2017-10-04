package br.com.senai.sistemaindividualdemonitoramento;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import br.com.senai.sistemaindividualdemonitoramento.model.Employer;
import br.com.senai.sistemaindividualdemonitoramento.model.ServiceOrder;

public class EncarregadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encarregado);

        Intent intent = getIntent();
        Employer employer = (Employer) intent.getSerializableExtra("employer");

        InitSidebar.fillSidebar(this, employer);

        String[] defaultSpnText = {"Dobra", "Cola", "Outra coisa sei la", "Não sou da grafica kkkjj"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, defaultSpnText);

        Spinner spn = (Spinner) findViewById(R.id.spn_activity);
        spn.setAdapter(adapter);

        Button btnSalvar = (Button) findViewById(R.id.encarregado_button_salvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText goalField = (EditText) findViewById(R.id.txt_goal);
                Spinner spnActivity = (Spinner) findViewById(R.id.spn_activity_encarregado);

                String goal = goalField.getText().toString();
                String activity = spnActivity.getSelectedItem().toString();


                ServiceOrder os = new ServiceOrder();
//                os.set

                finish();
            }
        });
    }
}
