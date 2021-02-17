package com.example.registryapp;


import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyHolder extends RecyclerView.ViewHolder {

    TextView companyName;
    Spinner drop_List;

    public MyHolder(@NonNull View itemView) {
        super(itemView);
        this.companyName=itemView.findViewById(R.id.companyName);
        this.drop_List=itemView.findViewById(R.id.dropdown_list);
    }
    //spinner listener here
    //https://stackoverflow.com/questions/42491520/android-spinner-inside-recyclerview-get-current-view-when-selected
    //http://www.devexchanges.info/2015/09/android-material-design-features.html
    // https://www.youtube.com/watch?v=oq_xGMN0mRE&t=206s
}
