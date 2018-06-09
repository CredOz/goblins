package com.example.rajit.shopforme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if( user != null ){
            Intent intent = new Intent(MainActivity.this, MainPage.class);
            startActivity(intent);
            finish();
        }
        else
            setContentView(R.layout.activity_main);
    }

    public void OpenSignUp(View v){
        Intent i = new Intent(MainActivity.this,SignUp.class);
        startActivity(i);
    }
    public void OpenSignIn(View v){
        Intent i = new Intent(MainActivity.this,SignIn.class);
        startActivity(i);
    }
}
