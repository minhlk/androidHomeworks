package com.assignment.minhlk.cookingrecipes;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by minhlk on 3/13/17.
 */

public class DetailsDialog extends Dialog {
    private final Items item;
    Context mContext;
    TextView tDialog,theadDialog;
    ImageView im;
    Button btn;
    public DetailsDialog(@NonNull Context context, Items item) {
        super(context);
        mContext = context;
        this.item = item;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.detailsdialog);
        btn = (Button) findViewById(R.id.bDialog);
        tDialog = (TextView) findViewById(R.id.tDialog);
        theadDialog = (TextView) findViewById(R.id.theadDialog);
        im = (ImageView) findViewById(R.id.imDialog);
        tDialog.setText(Html.fromHtml(item.getCaption()));
        theadDialog.setText(item.getTitle());
        Glide.with(mContext).load(item.getImage()).into(im);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext,WebviewActivity.class);
                i.putExtra("url", item.getLink());
                mContext.startActivity(i);
            }
        });
    }

}
