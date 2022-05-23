package com.example.variastelas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class ContactActivity extends AppCompatActivity {

    public static int RESULT_ADD = 1;
    public static int RESULT_CANCEL = 2;

    EditText edtNome;
    EditText edtTel;
    EditText edtBairro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        edtNome = findViewById(R.id.edtNome);
        edtTel = findViewById(R.id.edtTel);
        edtBairro = findViewById(R.id.edtBairro);


    }

    public void cancelar(View view){
        setResult(RESULT_CANCEL);
        finish();
    }

    public void adicionar(View view){

        Intent intent = new Intent();

        String nome = edtNome.getText().toString();
        String tel = edtTel.getText().toString();
        String bairro = edtBairro.getText().toString();
        intent.putExtra("Nome", nome);
        intent.putExtra("Telefone", tel);
        intent.putExtra("Bairro", bairro);

        setResult(RESULT_ADD);
        finish();
    }
}