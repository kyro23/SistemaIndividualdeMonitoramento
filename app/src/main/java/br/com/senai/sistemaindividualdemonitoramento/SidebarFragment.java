package br.com.senai.sistemaindividualdemonitoramento;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


public class SidebarFragment extends Fragment {

    private View view;
    private EditText campoOs;
    private EditText campoMatricula;
    private EditText campoSenha;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sidebar, container, false);

        campoOs = view.findViewById(R.id.sidebar_op_text);
        campoMatricula = view.findViewById(R.id.sidebar_user_text);
        campoSenha = view.findViewById(R.id.sidebar_user_password);


        this.view = view;

        return view;

    }
    public String getOs(){

        return campoOs.getText().toString();
    }

    public String getMatricula(){
        return campoMatricula.getText().toString();
    }

    public String getPassword(){
        return campoSenha.getText().toString();
    }
}