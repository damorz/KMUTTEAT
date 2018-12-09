package com.kmutteats.srinuan.firebasekmutteat;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerviewHolderMenuEachRes extends RecyclerView.ViewHolder
{

    public TextView mNamemenu,mPrice,mDescription,mUrl;
    public ImageView mPicMenu;
    public Button btaddcart;
    //public Button mDelM;


    public MyRecyclerviewHolderMenuEachRes(View itemView)
    {
        super(itemView);
        mNamemenu = itemView.findViewById(R.id.mNamemenuE);
        mPrice = itemView.findViewById(R.id.mPriceE);
        mDescription = itemView.findViewById(R.id.mDescriptionE);
        //mUrl = itemView.findViewById(R.id.mUrl);
        mPicMenu = itemView.findViewById(R.id.mPicmenuE);
        btaddcart = itemView.findViewById( R.id.btAddtocart );
        //mDelM = itemView.findViewById(R.id.Delbtmenu);

    }
}
