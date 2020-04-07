package com.example.exemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ListView;

import com.example.exemplo.adapter.TodoListAdapter;
import com.example.exemplo.model.Todo;
import com.example.exemplo.service.TodoAPI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private TodoListAdapter adapter;
    private List<Todo> todos;
    private TodoAPI service;
    private FloatingActionButton botaoAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        service = new TodoAPI();
        inicializarListView();
        inicializarBotoes();
        inicializarAcoes();

        /*
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Todo todo = new Todo();
                todo.setDescricao("Ir na padaria");
                todo.setPrioridade("BAIXA");
                try {
                    todoService.adicionar(todo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }); */
    }

    public void inicializarListView(){
        listView = findViewById(R.id.activity_ListView);
        todos = new ArrayList<Todo>();
        adapter = new TodoListAdapter(this, todos);
        listView.setAdapter(adapter);
    }

    private void inicializarBotoes(){
         botaoAdd = findViewById(R.id.activity_Botao_nova_tarefa);
    }

    private void inicializarAcoes(){
        botaoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,formtodoactivityNova.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        AsyncTask.execute(new Runnable(){
            @Override
            public void run() {
                try {
                    todos.clear();
                    todos.addAll(service.ListarTodos());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        //t.start();
    }
}





