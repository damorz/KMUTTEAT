package com.kmutteats.srinuan.firebasekmutteat;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerviewHolderHisMer extends RecyclerView.ViewHolder
{
    public TextView FoodNameHisMer,PriceHisMer,EmailHisMer,CountHisMer;
    public ImageView mPicMenuHisMer;

    public MyRecyclerviewHolderHisMer(@NonNull View itemView) {
        super(itemView);
        FoodNameHisMer = itemView.findViewById(R.id.mNamefoodHisMer);
        PriceHisMer = itemView.findViewById(R.id.mPriceHisMer);
        EmailHisMer = itemView.findViewById(R.id.EmailHisMer);
        CountHisMer = itemView.findViewById(R.id.countfoodHisMer);
        mPicMenuHisMer = itemView.findViewById(R.id.mPicresHisMer);
    }
}
