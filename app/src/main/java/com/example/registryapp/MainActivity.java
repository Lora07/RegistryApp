package com.example.registryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.INotificationSideChannel;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Initialize variable
    DrawerLayout drawerLayout;
    RecyclerView recyclerView;
    MyAdapter adapter;
    FloatingActionButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assign variables
        drawerLayout=findViewById(R.id.drawer_layout);
        recyclerView=findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter= new MyAdapter(this, getMyList());
        recyclerView.setAdapter(adapter);

        button=findViewById(R.id.floatingActionButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AddCompany.class));
            }
        });
    }
    private ArrayList<Model> getMyList() {
        ArrayList<Model> models = new ArrayList<>();

        Model m=new Model();
        // instead of manually setting it somehow create a add method
        m.setCompanyName("MBA Legal Services");
        models.add(m);

        Model m2=new Model();
        m2.setCompanyName("MBA Info Tech");
        models.add(m2);
       // m.setDropdown_list(drop_list_adapter);


        return models;
    }

    // Navigation drawer { the right side hamburger icon}
    public void ClickMenu(View view){
        //open drawer
        openDrawer(drawerLayout);
    }
    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }
    public void ClickLogo(View view){
        closeDrawer(drawerLayout);
    }
    public static void closeDrawer(DrawerLayout drawerLayout){
        //check condition
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            //if true drawer was active then close drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    public void ClickHome(View view){
        recreate();
    }
    public void ClickDashboard(View view){
        //setting option where user reads policies,
        // upgrade any information
        redirectActivity(this,Settings.class);
    }
    public void ClickAboutUs(View view){
        redirectActivity(this,AboutUS.class);
    }
    public void ClickLogout(View view){
        logout(this);
    }

    public static void logout(Activity mainActivity) {
        //close app
        AlertDialog.Builder builder=new AlertDialog.Builder(mainActivity);
        //set Title
        builder.setTitle("Logout");
        //set message
        builder.setMessage("Are you sure you want to logout?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mainActivity.finishAffinity();
                //exit app
                System.exit(0);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Dismiss dialog
                dialog.dismiss();
            }
        });
        //show dialog
        builder.show();
    }

    public static void redirectActivity(Activity activity, Class aclass){
        //intialize intent
        Intent intent=new Intent(activity,aclass);
        //set flag
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        //start settings activity
        activity.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //close drawer
        closeDrawer(drawerLayout);
    }
}