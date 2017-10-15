package br.com.senai.sistemaindividualdemonitoramento;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.senai.sistemaindividualdemonitoramento.model.Employer;
import br.com.senai.sistemaindividualdemonitoramento.model.ServiceOrder;
import br.com.senai.sistemaindividualdemonitoramento.model.model.dao.EmployerDAO;
import br.com.senai.sistemaindividualdemonitoramento.model.model.dao.ServiceOrderDAO;

public class InitialActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction tx;
    private SidebarFragment sidebarFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        fragmentManager = getSupportFragmentManager();
        tx = fragmentManager.beginTransaction();

        sidebarFragment = new SidebarFragment();

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

        Button btnLogin = (Button) findViewById(R.id.button_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String osNumber = sidebarFragment.getOs();
                String matricula = sidebarFragment.getMatricula();
                String password = sidebarFragment.getPassword();

                if(!(matricula.isEmpty() || password.isEmpty())){
                    Employer employer = new Employer();

                    employer.setMatricula(Long.parseLong(matricula));
                    employer.setSenha(password);

                    EmployerDAO dao = new EmployerDAO(InitialActivity.this);

                    Employer finded = dao.login(employer);

                    ServiceOrder os = new ServiceOrder();
                    if(!osNumber.isEmpty()) {
                        os.setId(Long.parseLong(osNumber));
                    }else {
                        os.setId(-1);
                    }
                    ServiceOrderDAO osDAO = new ServiceOrderDAO(InitialActivity.this);
                    os = osDAO.findById(os);

                    if(finded.getNome() != null){
                        Toast.makeText(InitialActivity.this, "Logado!", Toast.LENGTH_SHORT).show();

                        switch (finded.getTipo()) {
                            case "Funcionario":
                                Intent goEmployerInitial = new Intent(InitialActivity.this, EmployerInitialActivity.class);
                                goEmployerInitial.putExtra("employer", finded);
                                goEmployerInitial.putExtra("os", os);
                                startActivity(goEmployerInitial);

                                break;
                            case "Gestor":
                                Intent goMananger = new Intent(InitialActivity.this, ManangerActivity.class);

                                goMananger.putExtra("employer", finded);
                                goMananger.putExtra("os", os);
                                startActivity(goMananger);

                                break;
                            case "Encarregado":

                                Intent goEncarregado = new Intent(InitialActivity.this, EncarregadoActivity.class);
                                goEncarregado.putExtra("employer", finded);
                                goEncarregado.putExtra("os", os);

                                startActivity(goEncarregado);
                                break;
                            default:
                                break;
                        }

                        fragmentManager = getSupportFragmentManager();
                        tx = fragmentManager.beginTransaction();

                        sidebarFragment = new SidebarFragment();

                        tx.replace(R.id.fragment_sidebar, sidebarFragment);
                        tx.commit();
                    }else{
                        Toast.makeText(InitialActivity.this, "Não Logado!", Toast.LENGTH_SHORT).show();
                    }
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

        Button btnviewOs = (Button) findViewById(R.id.visualizarOs);
        btnviewOs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent viewOs = new Intent(InitialActivity.this, OsActivity.class);
                startActivity(viewOs);
            }
        });
    }
}
