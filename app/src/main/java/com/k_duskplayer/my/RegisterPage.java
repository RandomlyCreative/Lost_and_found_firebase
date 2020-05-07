package com.k_duskplayer.my;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterPage extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    FirebaseAuth mAuth;
    ProgressBar progressBar;
    private EditText editTextEmail;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.textViewRegister).setOnClickListener(this);
        findViewById(R.id.buttonLogin).setOnClickListener(this);
        ((EditText) findViewById(R.id.editTextEmail)).addTextChangedListener(this);
        ((EditText) findViewById(R.id.editTextPassword)).addTextChangedListener(this);


    }

    private void userLogin(){

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

        else {
            progressBar.setVisibility(View.VISIBLE);

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        finish();

                        //navigate to diff activity
                        Intent intent = new Intent(RegisterPage.this, lnf.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        progressBar.setVisibility(View.INVISIBLE);
                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }
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
            Toast.makeText(RegisterPage.this, "LOGIN USER", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onClick(View view) {

        Button button = (Button) findViewById(R.id.buttonLogin);
        //button.setEnabled(true);
        switch (view.getId())
        {
            case R.id.textViewRegister:

                finish();
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.buttonLogin:
                button.setEnabled(false);
                userLogin();
                break;

        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Button button = (Button) findViewById(R.id.buttonLogin);
                //button.setEnabled(false);


    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        //if(editTextEmail.getText().toString().length() > 0 && editTextPassword.getText().toString().length() > 0)
        //{
            Button button = (Button) findViewById(R.id.buttonLogin);
            button.setEnabled(true);
        //}
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
