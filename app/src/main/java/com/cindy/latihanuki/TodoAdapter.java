package com.cindy.latihanuki;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.MyViewHolder> {
    Context context;
    List<ToDo> todo;

    public TodoAdapter(Context context, List<ToDo> todo) {
        this.context = context;
        this.todo = todo;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.todo_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.titletodo.setText(todo.get(position).getTitletodo());
        holder.desctodo.setText(todo.get(position).getTitletodo());
        holder.datetodo.setText(todo.get(position).getTitletodo());

        String getTitleTodo = todo.get(position).getTitletodo();
        String getDescTodo = todo.get(position).getDesctodo();
        String getDateTodo = todo.get(position).getDatetodo();
        String getIdTodo = todo.get(position).getIdtodo();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditTodo.class);
                intent.putExtra("titletodo", getTitleTodo);
                intent.putExtra("desctodo", getDescTodo);
                intent.putExtra("datetodo", getDateTodo);
                intent.putExtra("idtodo", getIdTodo);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return todo.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titletodo, desctodo, datetodo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titletodo = (TextView)itemView.findViewById(R.id.titleTodo);
            desctodo = (TextView)itemView.findViewById(R.id.deskripsi);
            datetodo = (TextView)itemView.findViewById(R.id.datetodo);

        }
    }
}
