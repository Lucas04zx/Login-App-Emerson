package br.edu.ifsulminas.mch.pdm.login;

import android.view.View;
import android.widget.Toast;

public class RegisterClickListener implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(), R.string.button_cadastrar_clicked, Toast.LENGTH_LONG).show();
    }
}
