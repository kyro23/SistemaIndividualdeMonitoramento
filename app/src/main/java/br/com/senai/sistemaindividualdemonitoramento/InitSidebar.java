package br.com.senai.sistemaindividualdemonitoramento;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import br.com.senai.sistemaindividualdemonitoramento.model.Employer;

/**
 * Created by OC on 02/10/2017.
 */

public class InitSidebar {

    public static void fillSidebar(AppCompatActivity appCompatActivity, Employer employer){

        FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
        FragmentTransaction tx = fragmentManager.beginTransaction();
        SidebarFragment sidebarFragment = new SidebarFragment();
        sidebarFragment.matricula = employer.getMatricula().toString();
        sidebarFragment.senha = employer.getSenha();
        sidebarFragment.os = "123";

        tx.replace(R.id.frame_sidebar, sidebarFragment);
        tx.commit();

    }
}
