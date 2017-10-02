package br.com.senai.sistemaindividualdemonitoramento;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.senai.sistemaindividualdemonitoramento.model.Employer;

public class ManangerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mananger);

        Intent intent = getIntent();
        Employer employer = (Employer) intent.getSerializableExtra("employer");

        InitSidebar.fillSidebar(this, employer);
    }
}
