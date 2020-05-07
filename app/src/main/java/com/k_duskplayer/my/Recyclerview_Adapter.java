package com.k_duskplayer.my;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by K_Dusk on 30/03/2018.
 */

public class Recyclerview_Adapter extends RecyclerView.Adapter<Recyclerview_Adapter.MyViewHolder> {

    Context mContext;
    List<Items> mData;
    Dialog myDialog;
    Bundle b = new Bundle();

    public Recyclerview_Adapter(Context mContext, List<Items> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        View v;
        v= LayoutInflater.from(mContext).inflate(R.layout.custum_row, parent,false);
        final MyViewHolder vHolder = new MyViewHolder(v);



        //dialog ini
        myDialog = new Dialog(mContext);
        myDialog.setContentView(R.layout.dialog_request);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button dialogbuttonreq = (Button) myDialog.findViewById(R.id.dialog_buttonRequest);
        Button dialogbuttoncancel = (Button) myDialog.findViewById(R.id.dialog_buttonCancel);

        dialogbuttonreq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, requestpage.class);
                intent.putExtras(b);
                mContext.startActivity(intent);
                myDialog.dismiss();
            }
        });

        dialogbuttoncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        return vHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_location.setText(mData.get(position).getLocation());

        holder.itemview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, mData.get(position).getName(), Toast.LENGTH_SHORT).show();
                b.putString("itemName", mData.get(position).getName());
                b.putString("itemLoc", mData.get(position).getLocation());
                myDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        private LinearLayout itemview;
        private TextView tv_name;
        private TextView tv_location;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemview = (LinearLayout) itemView.findViewById(R.id.itemview);
            tv_name = (TextView) itemView.findViewById(R.id.item1);
            tv_location = (TextView) itemView.findViewById(R.id.location1);


        }
    }
}
