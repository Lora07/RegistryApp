package com.example.registryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    ArrayList <Model>models;

    public MyAdapter(Context c, ArrayList<Model> models) {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflates the card view rows
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_rows, null);

        //returns our view to the holder class
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.companyName.setText(models.get(position).getCompanyName());


        /*ArrayAdapter<CharSequence> adapter;
        adapter = ArrayAdapter.createFromResource(c, R.array.dropdown_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);*/
        holder.drop_List.setAdapter(models.get(position).getDropdown_list());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
