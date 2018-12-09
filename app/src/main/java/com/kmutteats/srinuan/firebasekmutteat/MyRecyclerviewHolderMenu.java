package com.kmutteats.srinuan.firebasekmutteat;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerviewHolderMenu extends RecyclerView.ViewHolder
{

    public TextView mNamemenu,mPrice,mDescription,mUrl;
    public ImageView mPicMenu;
    public Button mDelM;


    public MyRecyclerviewHolderMenu(View itemView)
    {
        super(itemView);
        mNamemenu = itemView.findViewById(R.id.mNamemenu);
        mPrice = itemView.findViewById(R.id.mPrice);
        mDescription = itemView.findViewById(R.id.mDescription);
        //mUrl = itemView.findViewById(R.id.mUrl);
        mPicMenu = itemView.findViewById(R.id.mPicmenu);
        mDelM = itemView.findViewById(R.id.Delbtmenu);


    }



}
