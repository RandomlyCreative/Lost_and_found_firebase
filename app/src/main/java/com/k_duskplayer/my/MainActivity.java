package com.k_duskplayer.my;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignin;

    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);

        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        textViewSignin = (TextView) findViewById(R.id.textViewSignin);

        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(email.isEmpty())
        {
            //email is empty
            editTextEmail.setError("Email required");
            editTextEmail.requestFocus();
            return;
        }

        // Enter method to check email using patterns.

        if (password.isEmpty()) {
            //password is empty
            editTextPassword.setError("Password required");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length()<6)
        {
            editTextPassword.setError("minimum password length is 6");
            editTextPassword.requestFocus();
            return;
        }

        progressDialog.setMessage("Registering....");
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "User Registered successfully", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();


                    Intent intent = new Intent(MainActivity.this, Userinfopage.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    finish();
                    startActivity(intent);
                }
                else
                {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                    {
                        Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(getApplicationContext(), "TRY AGAIN", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

            }
        });
        }


    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null)
        {
            finish();
            startActivity(new Intent(this, lnf.class));
        }
        else
        {
            Toast.makeText(this, "REGISTER USER", Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    public void onClick(View view) {
        if (view == buttonRegister)
        {
            registerUser();
        }
        if(view== textViewSignin)
        {
            Intent intent = new Intent(MainActivity.this, RegisterPage.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
            startActivity(intent);
        }
    }


}

