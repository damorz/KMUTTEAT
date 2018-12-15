package com.kmutteats.srinuan.firebasekmutteat;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerviewHolderOrder extends RecyclerView.ViewHolder
{
    public TextView FoodNameOrder,PriceOrder,EmailOrder,CountOrder;
    public ImageView mPicMenuOrder;
    public Button mcheck;
    public MyRecyclerviewHolderOrder(@NonNull View itemView) {

        super(itemView);
        FoodNameOrder = itemView.findViewById(R.id.mNamefoodOrder);
        PriceOrder = itemView.findViewById(R.id.mPriceOrder);
        EmailOrder = itemView.findViewById(R.id.emailOrder);
        mPicMenuOrder = itemView.findViewById(R.id.mPicresOrder);
        mcheck = itemView.findViewById(R.id.Checked);
        CountOrder = itemView.findViewById(R.id.countOrder);

    }
}
