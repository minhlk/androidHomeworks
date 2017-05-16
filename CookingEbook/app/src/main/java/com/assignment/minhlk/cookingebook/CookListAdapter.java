package com.assignment.minhlk.cookingebook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by minhlk on 3/12/17.
 */

public class CookListAdapter extends RecyclerView.Adapter<CookListAdapter.ViewHolder>  {

    ArrayList<Cook> mCooks;
    Context mContext;
    RecyclerView mRc;


    public CookListAdapter(ArrayList<Cook> Cooks, Context context, RecyclerView rc){
        mCooks = Cooks;
        mContext = context;
        mRc = rc;
//        mockData();
    }
    @Override
    public CookListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cookitem, null, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CookListAdapter.ViewHolder holder, final int position) {

        holder.tTitle.setText(mCooks.get(position).getName());
//        holder.tContent.setImageDrawable(mCooks.get(position).getPicture()+"");
        holder.tContent.setImageDrawable(mContext.getResources().getDrawable(mCooks.get(position).getPicture()));
        holder.btnItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext, "Ab  "+ mCooks.get(position).getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, DetailsActivity.class);
                intent.putExtra("text" , position);
                mContext.startActivity(intent);
            }
        });
        holder.tContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailsActivity.class);
                intent.putExtra("image" , position);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCooks.size();
    }





    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tTitle;
        ImageView tContent;
        Button btnItem;
        public ViewHolder(View itemView) {
            super(itemView);
            tTitle = (TextView) itemView.findViewById(R.id.tTitle);
            tContent = (ImageView) itemView.findViewById(R.id.tContent);
            btnItem = (Button) itemView.findViewById(R.id.btnItem);



        }
    }
}
