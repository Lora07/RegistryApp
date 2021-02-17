package com.example.registryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Signup extends AppCompatActivity {

    TextInputEditText location, companyName, password, email;
    Button btn_sign_up;
    TextView logintxt;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        location=findViewById(R.id.location);
        companyName=findViewById(R.id.companyName);
        password=findViewById(R.id.password);
        email=findViewById(R.id.email);
        progressBar=findViewById(R.id.progress);

        logintxt=findViewById(R.id.loginText);
        logintxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),LoginPage.class);
                startActivity(intent);
                finish();
            }
        });

        btn_sign_up=findViewById(R.id.buttonSignUp);
        btn_sign_up.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                // get what user typed in the fields
                String loc, cname, mail,pass;
                loc=String.valueOf(location.getText());
                cname=String.valueOf(companyName.getText());
                mail=String.valueOf(email.getText());
                pass=String.valueOf(password.getText());

                // first check if fields are left empty
                // short way check if they are not left empty then post data to mysql data
                if(!loc.equals(" ")&& !cname.equals(" ")&& !mail.equals("")&& !pass.equals(" ")){
                    //Start ProgressBar first (Set visibility VISIBLE)
                    progressBar.setVisibility(View.VISIBLE);

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for URL parameters
                            String[] field = new String[4];
                            field[0] = "location";
                            field[1] = "company name";
                            field[2] = "email";
                            field[3] = "Password";
                            //Creating array for data
                            // pass the data fields to the array
                            String[] data = new String[4];
                            data[0] = loc;
                            data[1] = cname;
                            data[2] = mail;
                            data[3] = pass;
                            //for now am using my ip address as a localhost
                            PutData putData = new PutData("http://10.0.122.106/LoginRegister/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    //End ProgressBar (Set visibility to GONE)
                                    progressBar.setVisibility(View.GONE);
                                    Log.i("PutData", result);
                                    if(result=="Sign Up Success"){
                                        //display login page once sign up is complete
                                        Intent intent=new Intent(getApplicationContext(),LoginPage.class);
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