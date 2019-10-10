package com.example.exam1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import Database.DBHandler;


public class MainActivity extends AppCompatActivity {

    TextView username, password;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.txtName);
        password = findViewById(R.id.txtPassword);
        dbHandler = new DBHandler(this);

    }

    public void addInfo (View view){
        String user = username.getText().toString();
        String pass = password.getText().toString();


        dbHandler.addInfo(user,pass);

        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
    }




    public  void listAll(View view){
        List usersList = dbHandler.readAllInfo();
        for(int i = 0; i < usersList.size(); i++){
            Log.i("listAll",  usersList.get(i).toString());
        }
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
    }


    public  void getInfo(View view){
        List reslut = dbHandler.readAllInfo();
        for(int i = 0; i < reslut.size(); i++)
        {
            Log.i("getAllusers()",  reslut.get(i).toString());
        }
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

    }

    public  void deleateuser(View view){

        int reslut= dbHandler.deleatInfo(username.getText().toString());

        if( reslut == 1) {
            Toast.makeText(this,"delete csuccessfully",Toast.LENGTH_LONG).show();
        }

        else{

            Toast.makeText(this,"error in delete",Toast.LENGTH_LONG).show();

        }


    }

    public void updateInfo(View view) {
        boolean reslut = dbHandler.updateInfo(username.getText().toString(), password.getText().toString());

        if (reslut == true) {
            Toast.makeText(this, "Update csuccessfully", Toast.LENGTH_LONG).show();
        } else {

            Toast.makeText(this, "error in update", Toast.LENGTH_LONG).show();

        }

    }
}
