package br.com.senai.sistemaindividualdemonitoramento;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import br.com.senai.sistemaindividualdemonitoramento.model.Employer;

public class EmployerInitialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_initial);



        String[] defaultSpnText = {"Dobra", "Cola", "Outra coisa sei la", "Não sou da grafica kkkjj"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, defaultSpnText);

        Spinner spn = (Spinner) findViewById(R.id.spn_activity);
        spn.setAdapter(adapter);

        final AlertDialog.Builder activityGoal = new AlertDialog.Builder(this);
        activityGoal.setTitle("Meta para a atividade: 200");
        String[] options = {"Ok"};
        activityGoal.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                switch (i){
                    case(0):
                        finish();
                        break;
                }
            }
        });

        Button btnStart = (Button) findViewById(R.id.funcionario_button_start);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityGoal.show();
            }
        });

        Button btnPause = (Button) findViewById(R.id.funcionario_button_pause);
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goPauseScreen = new Intent(EmployerInitialActivity.this, EmployerPauseActivity.class);
                startActivity(goPauseScreen);
            }
        });

        Button btnEnd = (Button) findViewById(R.id.funcionario_button_end);
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goFinish = new Intent(EmployerInitialActivity.this, EmployerFinishActivity.class);
                startActivity(goFinish);
                finish();
            }
        });

        Intent intent = getIntent();
        Employer employer = (Employer) intent.getSerializableExtra("employer");

        InitSidebar.fillSidebar(this, employer);
    }
}
