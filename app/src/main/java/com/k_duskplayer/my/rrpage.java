package com.k_duskplayer.my;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class rrpage extends AppCompatActivity implements View.OnClickListener {


    private EditText textViewName;
    private EditText textViewSap;
    private EditText textViewItem;
    private EditText textViewRoom;
    private EditText textViewLocation;
    private EditText textViewIdentificaton;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private Button buttonRequest;
    String displayreqItemLoc, displayreqItemName, displayItemId, displayResponse, displayuserName, displayuserSap, displayuserRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rrpage);

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        buttonRequest = (Button) findViewById(R.id.buttonReq);
        textViewName = (EditText) findViewById(R.id.editText);
        textViewSap = (EditText) findViewById(R.id.editText2);
        textViewItem = (EditText) findViewById(R.id.editText5);
        textViewRoom = (EditText) findViewById(R.id.editText10);
        textViewLocation = (EditText) findViewById(R.id.editText6);
        textViewIdentificaton = (EditText) findViewById(R.id.editText11);
        buttonRequest.setOnClickListener(this);

        loadUserInformation();

        @SuppressLint("RestrictedApi") DatabaseReference databaseReference = firebaseDatabase.getReference("users/" + mAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                if (userProfile != null) {
                    textViewName.setText(userProfile.getUserName());
                    textViewRoom.setText(userProfile.getUserRoom());
                    textViewSap.setText(userProfile.getUserSap());
                    textViewName.setEnabled(false);
                    textViewRoom.setEnabled(false);
                    textViewSap.setEnabled(false);
                } else {
                    Toast.makeText(rrpage.this, "enter user details to proceed OR", Toast.LENGTH_SHORT).show();
                    Toast.makeText(rrpage.this, "Enter user info for future reference.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
    }

    private void saveuserinfo() {

        displayItemId=textViewIdentificaton.getText().toString();
        displayreqItemLoc=textViewItem.getText().toString();
        displayreqItemName=textViewLocation.getText().toString();
        displayResponse="(Report Awaiting Reply...)";
        displayuserName=textViewName.getText().toString();
        displayuserRoom=textViewRoom.getText().toString();
        displayuserSap=textViewSap.getText().toString();
        String s = "_";




        @SuppressLint("RestrictedApi") DatabaseReference myRe = firebaseDatabase.getReference("claimrequests/"+ mAuth.getUid()+"/"+"(LOST ITEM REPORT)"+textViewItem.getText().toString()+s+textViewLocation.getText().toString());
        UserInfo userInfo= new UserInfo(displayItemId,displayResponse,displayuserName,displayuserRoom,displayuserSap, displayreqItemLoc, displayreqItemName);
        myRe.setValue(userInfo);

    }

    private void loadUserInformation() {

        FirebaseUser user = mAuth.getCurrentUser();

        //String displayName = user.get;

    }

        public void onClick (View v){
            if(v == buttonRequest)
            {
                saveuserinfo();

                Toast.makeText(this, "Request sent", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, lnf.class));
            }

        }
    }
