package com.k_duskplayer.my;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by K_Dusk on 30/03/2018.
 */

public class Recyclerview_Adapter2 extends RecyclerView.Adapter<Recyclerview_Adapter2.MyViewHolder> {

    Context mContext;
    List<UserInfo> mData;


    public Recyclerview_Adapter2(Context mContext, List<UserInfo> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        View v;
        v= LayoutInflater.from(mContext).inflate(R.layout.list_layout, parent,false);
        final MyViewHolder vHolder = new MyViewHolder(v);





        return vHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tv_name.setText(mData.get(position).getReqItemName());
        holder.tv_location.setText(mData.get(position).getReqItemLoc());
        holder.tv_response.setText(mData.get(position).getResponse());

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
        private TextView tv_response;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemview = (LinearLayout) itemView.findViewById(R.id.reqitemview);
            tv_name = (TextView) itemView.findViewById(R.id.textViewit);
            tv_location = (TextView) itemView.findViewById(R.id.textViewloc);
            tv_response = (TextView) itemView.findViewById(R.id.textViewres);


        }
    }
}
