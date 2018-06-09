package com.example.rajit.shopforme;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {

    EditText signIn_email,signIn_password;
    FirebaseAuth auth;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signIn_email = (EditText)findViewById(R.id.signin_email);
        signIn_password = (EditText)findViewById(R.id.signIn_password);
        auth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);
    }

    public void SignInUser(View v) {
        dialog.setMessage("Signing in. Please Wiat...");
        dialog.show();
        if (signIn_email.getText().toString().equals("") || signIn_password.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Fields cannot be empty!", Toast.LENGTH_SHORT).show();
        } else {
            auth.signInWithEmailAndPassword(signIn_email.getText().toString(), signIn_password.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if( task.isSuccessful() ){
                                dialog.hide();
                                Toast.makeText(getApplicationContext(), "Signed in Successfully!", Toast.LENGTH_SHORT).show();
                                Intent gotToMainPage = new Intent(SignIn.this, MainPage.class);
                                startActivity(gotToMainPage);
                                finish();
                            }
                            else{
                                dialog.hide();
                                Toast.makeText(getApplicationContext(),"User not found!",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }
    }
}
