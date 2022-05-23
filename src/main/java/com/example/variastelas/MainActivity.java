package com.example.variastelas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static int REQUEST_ADD = 1;
    public static int REQUEST_EDIT = 2;
    EditText edtConteudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtConteudo = findViewById(R.id.edtConteudo);
    }

    public void AddJogo(View view){
        Intent intent = new Intent(this, ContactActivity.class);
        //intent.putExtra("nome", "Ruan");
        startActivityForResult(intent, REQUEST_ADD);
        //startActivity(intent);
    }

    public void x(View view){
        //Toast toast = new Toast()
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == ContactActivity.RESULT_ADD) {
            String resultado = "Resultado: " + requestCode + " - " + resultCode;

            if (data.getExtras() != null) {
                String nome = (String) data.getExtras().get("Nome");
                String tel = (String) data.getExtras().get("Tel");
                String bairro = (String) data.getExtras().get("Bairro");
                resultado += " - " + nome + " - " + tel + " - " + bairro;
            }

            Log.d("MainActivity", resultado);

            String conteudo = edtConteudo.getText().toString();
            conteudo += resultado + "\n";
            edtConteudo.setText(conteudo);

        } else if(resultCode == ContactActivity.RESULT_CANCEL){
            Toast.makeText(this,"cancelado",
                    Toast.LENGTH_LONG).show();
        }
    }
}