package com.k_duskplayer.my;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by K_Dusk on 29/03/2018.
 */

public class Tab2 extends android.support.v4.app.Fragment implements View.OnClickListener{

    View v;
    private RecyclerView nyrecyclerview;
    private List<UserInfo> lstContact;
    private Recyclerview_Adapter2 recyclerAdapter;
    FirebaseAuth mAuth;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nyrecyclerview = (RecyclerView) getView().findViewById(R.id.recycle1);

        recyclerAdapter = new Recyclerview_Adapter2(getContext(), lstContact);
        nyrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        nyrecyclerview.setAdapter(recyclerAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.tab2,container,false);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        @SuppressLint("RestrictedApi") DatabaseReference myRef = database.getReference("claimrequests/"+mAuth.getUid());
        lstContact = new ArrayList<>();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                lstContact.clear();
                for(DataSnapshot i : dataSnapshot.getChildren()){
                    lstContact.add(new UserInfo(i.child("reqItemName").getValue().toString(),i.child("reqItemLoc").getValue().toString(), i.child("response").getValue().toString()));
                }
                recyclerAdapter.notifyDataSetChanged();
            }



            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

//        @SuppressLint("RestrictedApi") DatabaseReference myRf = database.getReference("lostreports/"+mAuth.getUid());
//        lstContact = new ArrayList<>();
//
//        myRf.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                lstContact.clear();
//                for(DataSnapshot i : dataSnapshot.getChildren()){
//                    lstContact.add(new UserInfo(i.child("reqItemName").getValue().toString(),i.child("reqItemLoc").getValue().toString(), i.child("response").getValue().toString()));
//                }
//                recyclerAdapter.notifyDataSetChanged();
//            }
//
//
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });








    }

    @Override
    public void onClick(View v) {

    }


}
