package com.kmutteats.srinuan.firebasekmutteat;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerviewHolderHisCus extends RecyclerView.ViewHolder
{
    public TextView FoodNameHisCus,PriceHisCus,NameResHisCus;
    public ImageView mPicMenuHisCus;
    public MyRecyclerviewHolderHisCus(@NonNull View itemView)
    {
        super(itemView);
        FoodNameHisCus = itemView.findViewById(R.id.mNamefoodHisCus);
        PriceHisCus = itemView.findViewById(R.id.mPriceHisCus);
        NameResHisCus = itemView.findViewById(R.id.mNameresHisCus);
        mPicMenuHisCus = itemView.findViewById(R.id.mPicresHisCus);
    }
}
