package com.assignment.minhlk.cookingrecipes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by minhlk on 3/13/17.
 */

public class RcAdapter extends RecyclerView.Adapter<RcAdapter.ViewHolder> {
    ArrayList<Items> mItems;
    Context mContext;

    public RcAdapter(ArrayList<Items> mItems, Context mContext) {
        this.mItems = mItems;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tItem.setText(mItems.get(position).getTitle());
//        holder.imItem glide library to quick set image
        Glide.with(mContext).load(mItems.get(position).getImage()).into(holder.imItem);
        holder.bItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext, mItems.get(position).getLink(), Toast.LENGTH_SHORT).show();
                DetailsDialog dialog = new DetailsDialog(mContext,mItems.get(position));
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button bItem;
        TextView tItem;
        ImageView imItem;
        public ViewHolder(View itemView) {
            super(itemView);
            bItem = (Button) itemView.findViewById(R.id.bItem);
            tItem = (TextView) itemView.findViewById(R.id.tItem);
            imItem = (ImageView) itemView.findViewById(R.id.imItem);

        }
    }
}
