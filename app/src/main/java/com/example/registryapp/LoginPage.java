package com.example.registryapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {

    TextInputEditText username,password;
    Button btn_login;
    TextView signUp;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        username=findViewById(R.id.username_log);
        password=findViewById(R.id.password_login);
        btn_login=findViewById(R.id.btnLogin);
        progressBar=findViewById(R.id.progress_log);

        signUp=findViewById(R.id.signupText);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Signup.class);
                startActivity(intent);
                finish();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                // get what user typed in the fields
                String cname, pass;
                cname=String.valueOf(username.getText());
                pass=String.valueOf(password.getText());

                // first check if fields are left empty
                // short way check if they are not left empty then post data to mysql data
                if(!cname.equals(" ")&& !pass.equals(" ")){
                    //Start ProgressBar first (Set visibility VISIBLE)
                    progressBar.setVisibility(View.VISIBLE);

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for URL parameters
                            String[] field = new String[2];
                            field[0] = "company name";
                            field[1] = "Password";
                            //Creating array for data
                            // pass the data fields to the array
                            String[] data = new String[2];
                            data[0] = cname;
                            data[1] = pass;
                            //for now am using my ip address as a localhost
                            PutData putData = new PutData("http://10.0.122.106/LoginRegister/login.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    //End ProgressBar (Set visibility to GONE)
                                    progressBar.setVisibility(View.GONE);
                                    Log.i("PutData", result);
                                    if(result=="Login Success"){
                                        //display login page once sign up is complete
                                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                }
                else{
                    //request for user for the required fields
                    //a toast helps display message then it disappears after sometime. by using length.long or short
                    Toast.makeText(getApplicationContext(),"All fields required",Toast.LENGTH_LONG).show();
                }
            }});
    }
}