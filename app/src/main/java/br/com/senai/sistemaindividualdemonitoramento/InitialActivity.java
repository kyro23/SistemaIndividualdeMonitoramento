package br.com.senai.sistemaindividualdemonitoramento;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class InitialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Logar como");
        String[] tipos = {"Funcionário", "Encarregado", "Gestor"};

        alertBuilder.setItems(tipos, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                switch (i){
                    case 0:
                        Intent goEmployerInitial = new Intent(InitialActivity.this, EmployerInitialActivity.class);
                        startActivity(goEmployerInitial);

                        break;
                    case 1:
                        Intent goEncarregado = new Intent(InitialActivity.this, EncarregadoActivity.class);
                        startActivity(goEncarregado);

                        break;
                    case 2:
                        Intent goMananger = new Intent(InitialActivity.this, ManangerActivity.class);
                        startActivity(goMananger);
                        break;
                    default:
                        break;
                }
            }
        });

        Button btnLogin = (Button) findViewById(R.id.button_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertBuilder.show();
            }
        });
    }
}
