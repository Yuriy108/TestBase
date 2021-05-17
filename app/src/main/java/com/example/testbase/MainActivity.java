package com.example.testbase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    EditText email;
    EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=findViewById(R.id.editTextTextName);
        pass=findViewById(R.id.editTextTextPass);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();

            }
        };

    }

    public void sign(View view) {
        mAuth.signInWithEmailAndPassword(email.getText().toString(),pass.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Toast.makeText(MainActivity.this,"Вход успешный",Toast.LENGTH_LONG).show();

            }
        });
    }

    public void reges(View view) {
        mAuth.createUserWithEmailAndPassword(email.getText().toString(),pass.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isComplete()){
                    Toast.makeText(MainActivity.this,"New user is created",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(MainActivity.this,"Process down",Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}