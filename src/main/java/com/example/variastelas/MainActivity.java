package com.example.variastelas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    ArrayList<Jogo> listaJogos;
    ArrayAdapter adapter;
    int selected;
    EditText descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selected = -1;

        listaJogos = new ArrayList<Jogo>();



        adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, listaJogos);

        descricao = (EditText) findViewById(R.id.descricao);
        lista = (ListView) findViewById(R.id.lista);
        lista.setAdapter(adapter);
        lista.setSelector(android.R.color.holo_blue_light);




        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3){
                Toast.makeText(MainActivity.this, ""+listaJogos.get(position).toString(),
                        Toast.LENGTH_SHORT).show();
                selected = position;
            }
        });
    }


    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_activity,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.add:
                AddJogo();
                break;
            case R.id.edit:
                EditJogo();
                break;
            case R.id.delete:
                apagarItemLista();
                break;
            case R.id.settings:
                break;
            case R.id.about:
                break;
        }
        return true;
    }

    private void apagarItemLista(){
        if(listaJogos.size() > 0) {
            if (selected >= 0) {
                listaJogos.remove(selected);
                adapter.notifyDataSetChanged();
            } else {
                selected = -1;
            }
        }
    }

    public void AddJogo(){
        Intent intent = new Intent(this, ContactActivity.class);
        startActivityForResult(intent, Constants.REQUEST_ADD);

    }

    public void EditJogo(){
        if(selected > -1) {
            Intent intent = new Intent(this, ContactActivity.class);
            Jogo jogo = listaJogos.get(selected);

            intent.putExtra("id", jogo.getId());
            intent.putExtra("nome", jogo.getNome());
            intent.putExtra("tipo", jogo.getTipo());

            startActivityForResult(intent, Constants.REQUEST_EDIT);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == Constants.REQUEST_ADD && resultCode == Constants.RESULT_ADD){
            String nome = (String) data.getExtras().get("Nome");
            String tipo = (String) data.getExtras().get("Tipo");

            Jogo jogo = new Jogo(nome,tipo);

            listaJogos.add(jogo);
            adapter.notifyDataSetChanged();
        } else if(requestCode == Constants.REQUEST_EDIT && resultCode == Constants.RESULT_ADD){
            String nome = (String) data.getExtras().get("Nome");
            String tipo = (String) data.getExtras().get("Tipo");
            int idEditar = (int) data.getExtras().get("id");

            for(Jogo jogo: listaJogos){
                if(jogo.getId() == idEditar){
                    jogo.setNome(nome);
                    jogo.setTipo(tipo);
                }
            }
            adapter.notifyDataSetChanged();
        } else if(resultCode == Constants.RESULT_CANCEL){
            Toast.makeText(this,"cancelado",
                    Toast.LENGTH_LONG).show();
        }

        if(resultCode == Constants.RESULT_ADD){
            String resultado = "Descrição";

            if(data.getExtras() != null){
                String nome = (String) data.getExtras().get("Nome");
                String tipo = (String) data.getExtras().get("Tipo");
                resultado += " - " + nome + " - " + tipo;
            }
            String conteudo = descricao.getText().toString();
            conteudo += resultado + "\n";
            descricao.setText(conteudo);
        }
    }
}