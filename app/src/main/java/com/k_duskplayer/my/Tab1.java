package com.k_duskplayer.my;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.TextView;

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

public class Tab1 extends android.support.v4.app.Fragment implements View.OnClickListener{

    View v;
    private RecyclerView nyrecyclerview;
    private List<Items> lstContact;
    private Recyclerview_Adapter recyclerAdapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nyrecyclerview = (RecyclerView) getView().findViewById(R.id.recycle);


        recyclerAdapter = new Recyclerview_Adapter(getContext(), lstContact);
        nyrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        nyrecyclerview.setAdapter(recyclerAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.tab1,container,false);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("items");
        lstContact = new ArrayList<>();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                    lstContact.clear();
                for(DataSnapshot i : dataSnapshot.getChildren()){
                    lstContact.add(new Items(i.child("Name").getValue().toString(),i.child("Location").getValue().toString()));
                }
                    recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });







    }

    @Override
    public void onClick(View v) {


    }


}
