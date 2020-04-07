package com.example.exemplo.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.exemplo.R;
import com.example.exemplo.model.Todo;

import java.util.List;

public class TodoListAdapter extends BaseAdapter {

   private AppCompatActivity activity;

   private List<Todo> todos;


   public TodoListAdapter(AppCompatActivity activity, List<Todo>todos){
       this.activity = activity;
       this.todos = todos;
   }

    @Override
    public int getCount() {
        return todos.size();
    }

    @Override
    public Object getItem(int position) {
        return todos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return todos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       View view = activity.getLayoutInflater().inflate(R.layout.listview_todo_adapter,parent, false);

        TextView campoDescricao = view.findViewById(R.id.listview_todo_adapter_campo_descricao);
        TextView campoPrioridade = view.findViewById(R.id.listview_todo_adapter_campo_prioridade);

        Todo todo = todos.get(position);

        campoDescricao.setText(todo.getDescricao());
        campoPrioridade.setText(todo.getPrioridade());

        return view;
    }
}
