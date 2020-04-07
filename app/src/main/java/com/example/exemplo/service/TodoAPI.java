package com.example.exemplo.service;

import android.util.Log;

import com.example.exemplo.model.Todo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class TodoAPI {


    public Todo buscarPorId(Long id) throws IOException {
        URL url = new URL("https://test-rest-ifms.herokuapp.com/todo" + id);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() == 200) {
            InputStreamReader reader = new InputStreamReader(conn.getInputStream());
            BufferedReader bf = new BufferedReader(reader);

            String body = bf.readLine();

            Gson gson = new Gson();
            Todo todo = gson.fromJson(body, Todo.class);
            return todo;
        }
        return null;
    }

    public List<Todo> ListarTodos() throws IOException {
        URL url = new URL("https://test-rest-ifms.herokuapp.com/todo");
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() == 200) {
            InputStreamReader reader = new InputStreamReader(conn.getInputStream());
            BufferedReader bf = new BufferedReader(reader);

            String body = bf.readLine();

            Gson gson = new Gson();

            Type type = new TypeToken<ArrayList<Todo>>() {}.getType();
            List<Todo> todos = gson.fromJson(body, type);
            conn.disconnect();
            return todos;
        }
       conn.disconnect();
        return null;
    }

    public void inserirTodo(Todo todo) throws IOException {
        URL url = new URL("https://test-rest-ifms.herokuapp.com/todo");
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("content-type","application/json");
        Gson gson  = new Gson();
        String body = gson.toJson(todo);
        OutputStream os = conn.getOutputStream();
        os.write(body.getBytes());
        os.flush();
        os.close();

        if(conn.getResponseCode() == 200){
            Log.d("TodoService","Todo adicionada com sucesso");
        }else{
            Log.d("TodoService","Falha ao adicionar nova Todo");
        }

       conn.disconnect();


    }

}