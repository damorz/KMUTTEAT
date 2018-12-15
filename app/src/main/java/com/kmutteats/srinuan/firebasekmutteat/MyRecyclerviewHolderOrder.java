package com.kmutteats.srinuan.firebasekmutteat;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerviewHolderOrder extends RecyclerView.ViewHolder
{
    public TextView FoodNameOrder,PriceOrder,EmailOrder;
    public ImageView mPicMenuOrder;
    public MyRecyclerviewHolderOrder(@NonNull View itemView) {

        super(itemView);
        FoodNameOrder = itemView.findViewById(R.id.mNamefoodOrder);
        PriceOrder = itemView.findViewById(R.id.mPriceOrder);
        EmailOrder = itemView.findViewById(R.id.emailOrder);
        mPicMenuOrder = itemView.findViewById(R.id.mPicresOrder);
    }
}
