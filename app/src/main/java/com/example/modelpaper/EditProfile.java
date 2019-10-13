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

public class EditProfile extends AppCompatActivity {

    private EditText username , password , dob;
    private RadioButton male, female;
    private Button edit, search , delete;
    private DBHandler db;
    private int UserID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        edit = findViewById(R.id.edit);
        search = findViewById(R.id.search);
        delete = findViewById(R.id.delete);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        dob = findViewById(R.id.dob);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);

        db = new DBHandler(this);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = db.readAllInfo( username.getText().toString().trim() );


                if( user.getID() == 0  ){
                    Toast.makeText( EditProfile.this , "There is no user in the system" , Toast.LENGTH_LONG).show();
                    UserID = 0;
                    password.setText("");
                    dob.setText("");
                    male.setChecked(false);
                    female.setChecked(false);

                }else {
                    UserID = user.getID();
                    password.setText(user.getPassword());
                    dob.setText(user.getDOB());

                    if (user.getGender().equals("Male")) {
                        male.setChecked(true);
                    } else {
                        female.setChecked(true);
                    }
                }
            }
        });


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                user.setID(UserID);
                user.setUsername( username.getText().toString().trim() );
                user.setPassword( password.getText().toString().trim());
                user.setDOB( dob.getText().toString().trim() );

                if(male.isChecked()){
                    user.setGender( male.getText().toString().trim() );
                }else{
                    user.setGender( female.getText().toString().trim() );
                }


                boolean result =  db.UpdateInfo( user );
                if(result){
                    Toast.makeText( EditProfile.this , "User Successfully Updated " , Toast.LENGTH_LONG ).show();
                }else{
                    Toast.makeText( EditProfile.this , "User Update Unsuccessfully" , Toast.LENGTH_LONG ).show();

                }
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               boolean result = db.DeleteInfo( UserID );
                if(result){
                    Toast.makeText( EditProfile.this , "User Successfully Deleted" , Toast.LENGTH_LONG ).show();
                }else{
                    Toast.makeText( EditProfile.this , "User Delete Unsuccessfully" , Toast.LENGTH_LONG ).show();

                }
            }
        });



    }
}
