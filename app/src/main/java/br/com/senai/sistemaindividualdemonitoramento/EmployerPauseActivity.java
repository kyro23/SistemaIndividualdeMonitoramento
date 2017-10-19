package br.com.senai.sistemaindividualdemonitoramento;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.senai.sistemaindividualdemonitoramento.model.Employer;
import br.com.senai.sistemaindividualdemonitoramento.model.ServiceOrder;

public class EmployerPauseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_pause);

        Intent intent = getIntent();
        Employer employer = (Employer) intent.getSerializableExtra("employer");
        ServiceOrder os = (ServiceOrder) intent.getSerializableExtra("os");

        InitSidebar.fillSidebar(this, employer, os);

    }
}
