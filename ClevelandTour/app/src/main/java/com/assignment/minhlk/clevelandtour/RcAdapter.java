package com.assignment.minhlk.clevelandtour;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by minhlk on 5/11/17.
 */

public class RcAdapter extends RecyclerView.Adapter<RcAdapter.mViewHolder> {
    ArrayList<Location> arr = new ArrayList<>();
    Context c;
    public RcAdapter(ArrayList<Location> arr, Context c) {
        this.arr = arr;
        this.c = c;
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new mViewHolder(v);
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {
        holder.rcText.setText(arr.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    protected class mViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView rcText;
        public mViewHolder(View itemView) {
            super(itemView);
            rcText = (TextView)itemView.findViewById(R.id.rcText);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Bundle bd = new Bundle();
            bd.putString("title",arr.get(getAdapterPosition()).getTitle());
            bd.putString("lat",arr.get(getAdapterPosition()).getLat());
            bd.putString("log",arr.get(getAdapterPosition()).getLog());
            bd.putString("url",arr.get(getAdapterPosition()).getUrl());

            Intent i = new Intent(c.getApplicationContext(),Main2Activity.class);
            i.putExtra("bd",bd);
            c.startActivity(i);
        }
    }
}
