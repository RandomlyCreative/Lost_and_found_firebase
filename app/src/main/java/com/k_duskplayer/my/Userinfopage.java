package com.k_duskplayer.my;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Userinfopage extends AppCompatActivity implements View.OnClickListener {

    private Button buttonSave;
    private EditText editTextName;
    private EditText editTextSap;
    private EditText editTextRoom;
    private TextView textViewCancel;
    ProgressBar progressBar;
    String displayName, displaySap, displayRoom;

    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;

    //private StorageReference mStorageRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfopage);

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        buttonSave = (Button) findViewById(R.id.buttonSave);
        editTextName = (EditText) findViewById(R.id.editText7);
        editTextSap = (EditText) findViewById(R.id.editText8);
        editTextRoom = (EditText) findViewById(R.id.editText9);
        textViewCancel = (TextView) findViewById(R.id.textViewCancel1);
        //progressBar = findViewById(R.id.);
        buttonSave.setOnClickListener(this);
        textViewCancel.setOnClickListener(this);

        loadUserInformation();


        @SuppressLint("RestrictedApi") DatabaseReference databaseReference = firebaseDatabase.getReference("users/" + mAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                if (userProfile != null) {
                    editTextName.setText(userProfile.getUserName());
                    editTextRoom.setText(userProfile.getUserRoom());
                    editTextSap.setText(userProfile.getUserSap());
                } else
                    Toast.makeText(Userinfopage.this, "Enter User Details.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(Userinfopage.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();

            }
        });


        //database = FirebaseDatabase.getInstance();
        //ref = database.getReference("");

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, RegisterPage.class));
        }


    }

    private void loadUserInformation() {

        FirebaseUser user = mAuth.getCurrentUser();

        //String displayName = user.get;

    }


    @Override
    public void onClick(View v) {


        if (v == buttonSave) {
            saveUserInformation();

            if (displayName.isEmpty()) {
                editTextName.setError("Name required");
                editTextName.requestFocus();
                return;
            }

            if (displayRoom.isEmpty()) {
                editTextRoom.setError("Enter Room no.");
                editTextRoom.requestFocus();
                return;
            }

            if (displaySap.isEmpty()) {
                editTextSap.setError("SAP Id required");
                editTextSap.requestFocus();
                return;
            }


            Toast.makeText(this, "User info saved", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, lnf.class));
        }
        if (v == textViewCancel) {
            startActivity(new Intent(this, lnf.class));
        }


    }

    private void saveUserInformation() {

        displayName = editTextName.getText().toString();
        displaySap = editTextSap.getText().toString();
        displayRoom = editTextRoom.getText().toString();

        //FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        @SuppressLint("RestrictedApi") DatabaseReference myRef = firebaseDatabase.getReference("users/" + mAuth.getUid());
        UserProfile userProfile = new UserProfile(displayName, displaySap, displayRoom);
        myRef.setValue(userProfile);

    }

}