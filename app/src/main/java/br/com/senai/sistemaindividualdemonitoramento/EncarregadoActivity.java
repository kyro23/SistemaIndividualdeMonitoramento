package br.com.senai.sistemaindividualdemonitoramento;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class EncarregadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encarregado);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction tx = fragmentManager.beginTransaction();
        tx.replace(R.id.frame_sidebar, new SidebarFragment());
        tx.commit();

        String[] defaultSpnText = {"Dobra", "Cola", "Outra coisa sei la", "Não sou da grafica kkkjj"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, defaultSpnText);

        Spinner spn = (Spinner) findViewById(R.id.spn_activity);
        spn.setAdapter(adapter);

        Button btnSalvar = (Button) findViewById(R.id.encarregado_salvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
