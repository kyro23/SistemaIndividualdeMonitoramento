package br.com.senai.sistemaindividualdemonitoramento;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.senai.sistemaindividualdemonitoramento.model.Employer;
import br.com.senai.sistemaindividualdemonitoramento.model.ServiceOrder;

public class ManagerInitialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_initial);

        Intent intent = getIntent();
        final  Employer employer = (Employer) intent.getSerializableExtra("employer");
        final ServiceOrder os = (ServiceOrder) intent.getSerializableExtra("os");

        InitSidebar.fillSidebar(this, employer, os);

        Button btnRelatorios = (Button) findViewById(R.id.manager_btn_relatorios);
        btnRelatorios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent goRelatorio = new Intent(ManagerInitialActivity.this, ManagerRelatorioActivity.class);
              goRelatorio.putExtra("employer", employer);
              goRelatorio.putExtra("os", os);

              startActivity(goRelatorio);

            }
        });

        Button btnViewUsers = (Button) findViewById(R.id.manager_btn_gerenciarUsr);
        btnViewUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goViewUsers = new Intent(ManagerInitialActivity.this, ListUsersActivity.class);
                goViewUsers.putExtra("employer", employer);

                startActivity(goViewUsers);
            }
        });

        Button btnViewOs = (Button) findViewById(R.id.manager_btn_gerenciar_os);
        btnViewOs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goViewOs = new Intent(ManagerInitialActivity.this, ListServiceOrderActivity.class);
                goViewOs.putExtra("employer", employer);

                startActivity(goViewOs);
            }
        });

    }
}
