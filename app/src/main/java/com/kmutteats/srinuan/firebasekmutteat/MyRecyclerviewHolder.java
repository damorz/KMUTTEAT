package com.kmutteats.srinuan.firebasekmutteat;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerviewHolder extends RecyclerView.ViewHolder
{
    public TextView mUsername,mUserstatus,mLocation,mPhonenum,mUrl;
    public ImageView mPicres;
    public TextView mOnOff;
    public Button mDel;
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
        mDel = itemView.findViewById(R.id.Delbt);
    }
}
