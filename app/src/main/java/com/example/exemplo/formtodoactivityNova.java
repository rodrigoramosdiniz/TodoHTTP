package com.example.exemplo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.exemplo.adapter.TodoListAdapter;
import com.example.exemplo.model.Todo;
import com.example.exemplo.service.TodoAPI;

import java.io.IOException;

public class formtodoactivityNova extends AppCompatActivity {

    Button botaoSalvar;
    EditText campoDescricao;
    EditText campoPrioridade;
    private TodoAPI Service;
    private TodoListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_activity);
        ReferenciasInicializadas();
        acoesBotoes();
    }

    private void ReferenciasInicializadas(){
        botaoSalvar = findViewById(R.id.activity_form_botao_salvar);
        campoDescricao = findViewById(R.id.activity_form_todo_campo_descricao);
        campoPrioridade = findViewById(R.id.activity_form_campo_Prioridade);
        Service = new TodoAPI();

    }

    private void acoesBotoes(){
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String descricao = campoDescricao.getText().toString();
                final String prioridade = campoPrioridade.getText().toString();
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        Todo todo = new Todo();
                        todo.setDescricao(descricao);
                        todo.setPrioridade(prioridade);



                        try {
                            Service.inserirTodo(todo);
                            adapter.notifyDataSetChanged();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}

