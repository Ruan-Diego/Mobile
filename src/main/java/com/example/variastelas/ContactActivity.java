package com.example.variastelas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class ContactActivity extends AppCompatActivity {


    EditText edtNome;
    EditText edtTipo;

    boolean edit;
    int idContatoEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        edtNome = findViewById(R.id.edtNome);
        edtTipo = findViewById(R.id.edtTipo);

        edit = false;

        if(getIntent().getExtras() != null){
            idContatoEditar = (int) getIntent().getExtras().get("id");
            String nome = (String) getIntent().getExtras().get("nome");
            String tipo = (String) getIntent().getExtras().get("tipo");
            edtNome.setText(nome);
            edtTipo.setText(tipo);
            edit = true;
        }

    }

    public void cancelar(View view){
        setResult(Constants.RESULT_CANCEL);
        finish();
    }

    public void adicionar(View view){

        Intent intent = new Intent();

        String nome = edtNome.getText().toString();
        String tipo = edtTipo.getText().toString();
        intent.putExtra("Nome", nome);
        intent.putExtra("Tipo", tipo);
        if(edit) {
            intent.putExtra("id", idContatoEditar);
        }

        setResult(Constants.RESULT_ADD, intent);
        finish();
    }
}