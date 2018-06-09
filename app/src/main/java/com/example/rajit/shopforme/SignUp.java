package com.example.rajit.shopforme;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    EditText name,email,password;
    FirebaseAuth auth;
    ProgressDialog dialog;

    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = (EditText)findViewById(R.id.signUp_name);
        email = (EditText)findViewById(R.id.signUp_email);
        password = (EditText)findViewById(R.id.signUp_password);
        auth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);

    }
    public String usrName;
    public String usrEmail;
    public String usrPassword;
    public void SignUpUser(View v) {

        dialog.setMessage("Registering, Please Wait...");
        dialog.show();

        usrName = name.getText().toString();
        usrEmail = email.getText().toString();
        usrPassword = password.getText().toString();

        if (usrName.equals("") || usrEmail.equals("") || usrPassword.equals("")) {
            Toast.makeText(getApplicationContext(), "Fields Cannot Be Empty", Toast.LENGTH_LONG).show();
        }
        else{
            auth.createUserWithEmailAndPassword(usrEmail, usrPassword)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                dialog.hide();
                                Toast.makeText(getApplicationContext(), "Registered Succesfully", Toast.LENGTH_SHORT).show();
                                databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
                                Users user = new Users(usrName, usrEmail, usrPassword);
                                FirebaseUser firebaseUser = auth.getCurrentUser();
                                databaseReference.child( firebaseUser.getUid() ).setValue(user)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if( task.isSuccessful() ){
                                                    Toast.makeText(getApplicationContext(),"User Data Saved", Toast.LENGTH_LONG).show();
                                                    Intent goToMainPage = new Intent(SignUp.this, MainPage.class);
                                                    startActivity(goToMainPage );
                                                    // finish();
                                                }
                                                else{
                                                    Toast.makeText(getApplicationContext(),"User Data Could Not Be Saved", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });

                            }else {
                                dialog.hide();
                                Toast.makeText(getApplicationContext(), "User Could Not Be Registered", Toast.LENGTH_LONG).show();

                            }
                        }
                    });


        }
    }
}
