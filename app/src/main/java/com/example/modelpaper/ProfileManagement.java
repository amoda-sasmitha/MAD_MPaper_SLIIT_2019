package com.example.modelpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import Database.DBHandler;
import Model.User;

public class ProfileManagement extends AppCompatActivity {

    private EditText username , password , dob;
    private RadioButton male, female;
    private Button register;
    private DBHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        register = findViewById(R.id.edit);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        dob = findViewById(R.id.dob);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);

        db = new DBHandler(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                User user = new User();
                user.setUsername( username.getText().toString().trim() );
                user.setPassword( password.getText().toString().trim());
                user.setDOB( dob.getText().toString().trim() );

                if(male.isChecked()){
                    user.setGender( male.getText().toString().trim() );
                }else{
                    user.setGender( female.getText().toString().trim() );
                }

                boolean result = db.addInfo(user);
                if(result){
                    Toast.makeText( ProfileManagement.this , "User Successfully Added " , Toast.LENGTH_LONG ).show();
                }else{
                    Toast.makeText( ProfileManagement.this , "User Add Unsuccessfully" , Toast.LENGTH_LONG ).show();

                }


            }
        });


    }
}
