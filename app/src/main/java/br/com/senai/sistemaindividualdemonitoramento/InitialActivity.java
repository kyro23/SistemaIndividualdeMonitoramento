package br.com.senai.sistemaindividualdemonitoramento;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.senai.sistemaindividualdemonitoramento.model.Employer;
import br.com.senai.sistemaindividualdemonitoramento.model.model.dao.EmployerDAO;
import br.com.senai.sistemaindividualdemonitoramento.model.model.dao.ListEmployerActivity;

public class InitialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction tx = fragmentManager.beginTransaction();

        final SidebarFragment sidebarFragment = new SidebarFragment();

        tx.replace(R.id.frame_sidebar, sidebarFragment);
        tx.commit();


        Button btnViewFunc = (Button) findViewById(R.id.visualizarFunc);
        btnViewFunc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewEmp = new Intent(InitialActivity.this, ListEmployerActivity.class);
                startActivity(viewEmp);
            }
        });

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
//                alertBuilder.show();

                String os = sidebarFragment.getOs();
                String matricula = sidebarFragment.getMatricula();
                String password = sidebarFragment.getPassword();

                if(!(os.isEmpty() && matricula.isEmpty() && password.isEmpty())){
                    Employer employer = new Employer();

                    employer.setMatricula(Long.parseLong(matricula));
                    employer.setSenha(password);

                    EmployerDAO dao = new EmployerDAO(InitialActivity.this);

                    Employer finded = dao.login(employer);
                    if(finded.getNome() != null){
                        Toast.makeText(InitialActivity.this, "Logado!", Toast.LENGTH_SHORT).show();

                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction tx = fragmentManager.beginTransaction();

                        final SidebarFragment sidebarFragment = new SidebarFragment();

                        tx.replace(R.id.frame_sidebar, sidebarFragment);
                        tx.commit();

                        switch (finded.getTipo()) {
                            case "Funcionario":
                                Intent goEmployerInitial = new Intent(InitialActivity.this, EmployerInitialActivity.class);
                                goEmployerInitial.putExtra("employer", finded);

                                startActivity(goEmployerInitial);

                                break;
                            case "Gestor":
                                Intent goMananger = new Intent(InitialActivity.this, ManangerActivity.class);

                                goMananger.putExtra("employer", finded);
                                startActivity(goMananger);

                                break;
                            case "Encarregado":

                                Intent goEncarregado = new Intent(InitialActivity.this, EncarregadoActivity.class);
                                goEncarregado.putExtra("employer", finded);
                                startActivity(goEncarregado);
                                break;
                            default:
                                break;
                        }

                    }else{
                        Toast.makeText(InitialActivity.this, "Não Logado!", Toast.LENGTH_SHORT).show();
                    }
                    dao.close();
                }else{
                    Toast.makeText(InitialActivity.this, "Preencha os campos!", Toast.LENGTH_SHORT).show();
                }


            }
        });

        Button btnCadastrar = (Button) findViewById(R.id.cadastro);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cadastro = new Intent(InitialActivity.this, CadastroActivity.class);
                startActivity(cadastro);
            }
        });
    }
}
