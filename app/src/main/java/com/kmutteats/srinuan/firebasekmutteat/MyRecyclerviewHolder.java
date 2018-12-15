package com.kmutteats.srinuan.firebasekmutteat;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
        View.OnTouchListener,View.OnLongClickListener
{
    public TextView mUsername,mUserstatus,mLocation,mPhonenum,mUrl;
    public ImageView mPicres;
    public TextView mOnOff;
    private ItemClickRes itemclickres;

    public MyRecyclerviewHolder(View itemView)
    {
        super(itemView);
        mLocation = itemView.findViewById(R.id.mLocation);
        mUsername = itemView.findViewById(R.id.mUsername);
        mUserstatus = itemView.findViewById(R.id.mStatus);
        mPhonenum = itemView.findViewById(R.id.mPhonenum);
        mOnOff = itemView.findViewById(R.id.mOnOff);
        //mUrl = itemView.findViewById(R.id.mUrl);
        mPicres = itemView.findViewById(R.id.mPicres);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setOnClickListener(ItemClickRes itemclickres) {
        this.itemclickres = itemclickres;
    }

    @Override
    public void onClick(View v) {
        itemclickres.onClick(v, getAdapterPosition(), false, null);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        itemclickres.onClick(v, getAdapterPosition(), false, null);
        return true;
    }

    @Override
    public boolean onLongClick(View v) {
        itemclickres.onClick(v, getAdapterPosition(), true, null);
        return true;
    }
}

