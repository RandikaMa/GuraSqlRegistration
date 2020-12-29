package com.example.gurasqlregistration;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
    EditText fullName,email,password,phone;
    Button registerBtn,gotoLogin;
    boolean valid =true;


    LoginDataBaseAdapter loginDataBaseAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED,WindowManager.LayoutParams.FLAGS_CHANGED);


        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        fullName = findViewById(R.id.registerName);
        email = findViewById(R.id.registerEmail);
        password = findViewById(R.id.registerPassword);
        phone = findViewById(R.id.registerPhone);
        registerBtn = findViewById(R.id.registerBtn);


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //validation

                String emai = email.getText().toString().trim();
                String pass  = password.getText().toString().trim();
                String pho  = phone.getText().toString().trim();

                //checking if email and passwords are empty
                if(TextUtils.isEmpty(emai)){
                    Toast.makeText(Register.this,"Enter Email",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(Register.this,"Please enter password",Toast.LENGTH_SHORT).show();
                    return;
                }

                else
                {
                    // Save the Data in Database
                    loginDataBaseAdapter.insertEntry(emai, pass,pho);

                    // reg_btn.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                    Log.d("PASSWORD",pass);
                    Log.d("Email",emai);
                    Log.d("Phone",pho);
                    Intent i=new Intent(Register.this,MainActivity.class);
                    startActivity(i);
                }





            }

        });

/*
       gotoLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(getApplicationContext(),Login.class));
           }
       });
    }

*/


    }
}