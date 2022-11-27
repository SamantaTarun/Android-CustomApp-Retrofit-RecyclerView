package com.example.usermanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText email,password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //hide action bar
        getSupportActionBar().hide();
        //hide staus bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        email=findViewById(R.id.etemail);
        password=findViewById(R.id.etpassword);
        login=findViewById(R.id.btnlogin);
        login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                if(isEmpty(email)){
                   email.setError("Email is required");
                   Toast t= Toast.makeText(getBaseContext(),"Field can't be empty",Toast.LENGTH_SHORT);
                   t.show();
                }
                if(isEmpty(password))
                {
                    password.setError("Password is required");
                    Toast t= Toast.makeText(getBaseContext(),"Field can't be empty",Toast.LENGTH_SHORT);
                    t.show();
                }
                else if(!isEmail(email)){
                    email.setError("Enter Valid Email id");
                    Toast t= Toast.makeText(getBaseContext(),"you must enter valid email to login",Toast.LENGTH_SHORT);
                    t.show();
                }
                else{
                    startActivity(new Intent(MainActivity.this, DashBoardActivity.class));
                }

            }
        });

    }
    boolean isEmail(EditText text){
        CharSequence email=text.getText().toString();
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    boolean isEmpty(EditText text){
        CharSequence str=text.getText().toString();
        return TextUtils.isEmpty(str);
    }

}