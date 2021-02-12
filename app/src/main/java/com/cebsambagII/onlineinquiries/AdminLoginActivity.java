package com.cebsambagII.onlineinquiries;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminLoginActivity extends AppCompatActivity {
    private TextInputEditText emailId;
    private TextInputEditText password;
    private Button loginButton;

    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        mFirebaseAuth = FirebaseAuth.getInstance();

        emailId = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginButton =findViewById(R.id.loginButton);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser != null ) {
                    Toast.makeText(AdminLoginActivity.this, "You are Logged In", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AdminLoginActivity.this,DashboardActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(AdminLoginActivity.this, "Please Login", Toast.LENGTH_SHORT).show();
                }
            }
        };

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                if(email.isEmpty()){
                    emailId.setError("Invalid Email");
                    emailId.requestFocus();
                }
                else  if(pwd.isEmpty()){
                    password.setError("Invalid Password");
                    password.requestFocus();
                }
                else  if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(AdminLoginActivity.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();
                }
                else  if(!(email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(AdminLoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()) {
                                Toast.makeText(AdminLoginActivity.this, "Login Error, Please Login Again", Toast.LENGTH_SHORT).show();

                            }else {
                                Intent inToHome = new Intent (AdminLoginActivity.this,DashboardActivity.class);
                                startActivity(inToHome);
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(AdminLoginActivity.this,"Error Occurred!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}