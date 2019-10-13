package com.example.modelpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Database.DBHandler;
import Model.User;

public class Home extends AppCompatActivity {

    private DBHandler db;
    private Button  login, register , update;
    private EditText username , password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        db = new DBHandler(this);
        register = findViewById(R.id.edit);
        update = findViewById(R.id.update);
        login = findViewById(R.id.login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password );

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user  = db.validateUser( username.getText().toString().trim() , password.getText().toString().trim() );
                if( user.getID() == 0 ){
                    Toast.makeText( Home.this , "No User" , Toast.LENGTH_LONG ).show();
                }else{

                    if( user.getGender().equals( "Male")){
                        Intent intent = new Intent( Home.this , MaleAct.class );
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent( Home.this , FemaleAct.class );
                        startActivity(intent);
                    }

                }
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Home.this , ProfileManagement.class );
                startActivity(intent);
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Home.this , EditProfile.class );
                startActivity(intent);
            }
        });



    }
}
