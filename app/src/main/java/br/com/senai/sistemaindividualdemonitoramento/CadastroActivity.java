package br.com.senai.sistemaindividualdemonitoramento;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import br.com.senai.sistemaindividualdemonitoramento.model.Employer;
import br.com.senai.sistemaindividualdemonitoramento.model.model.dao.EmployerDAO;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        String[] defaultSpnText = {"Funcionario", "Gestor"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, defaultSpnText);

        Spinner spn = (Spinner) findViewById(R.id.cadastro_tipo);
        spn.setAdapter(adapter);

        Button btnCadastrar = (Button) findViewById(R.id.cadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText campoMatricula = (EditText) findViewById(R.id.cadastro_matricula);
                EditText campoNome = (EditText) findViewById(R.id.cadastro_nome);
                EditText campoSenha = (EditText) findViewById(R.id.cadastro_senha);
                Spinner campoTipo = (Spinner) findViewById(R.id.cadastro_tipo);

                String matricula = String.valueOf(campoMatricula.getText());
                String nome = String.valueOf(campoNome.getText());
                String senha = String.valueOf(campoSenha.getText());
                String tipo = (String) campoTipo.getSelectedItem();

                if(!(nome.isEmpty() && senha.isEmpty() && tipo.isEmpty())){
                    Employer employer = new Employer();
                    employer.setNome(nome);
                    employer.setSenha(senha);
                    employer.setTipo(tipo);
                    employer.setMatricula(Long.parseLong(matricula));

                    EmployerDAO dao = new EmployerDAO(CadastroActivity.this);
                    if(dao.create(employer) != -1){
                        Toast.makeText(CadastroActivity.this, "Cadastrado!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(CadastroActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                    }

                    dao.close();
                }else{
                    Toast.makeText(CadastroActivity.this, "Digite todos os campos!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
