package br.com.senai.sistemaindividualdemonitoramento;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import br.com.senai.sistemaindividualdemonitoramento.model.Employer;
import br.com.senai.sistemaindividualdemonitoramento.model.ServiceOrder;

/**
 * Created by OC on 02/10/2017.
 */

public class InitSidebar {

    public static void fillSidebar(AppCompatActivity appCompatActivity, Employer employer, ServiceOrder os){

        FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
        FragmentTransaction tx = fragmentManager.beginTransaction();
        SidebarFragment sidebarFragment = new SidebarFragment();
        sidebarFragment.matricula = employer.getMatricula().toString();
        sidebarFragment.senha = employer.getSenha();

        if(os.getId() != null) {
            sidebarFragment.os = os.getId().toString();
        }
        tx.replace(R.id.frame_sidebar, sidebarFragment);
        tx.commit();

    }
}
