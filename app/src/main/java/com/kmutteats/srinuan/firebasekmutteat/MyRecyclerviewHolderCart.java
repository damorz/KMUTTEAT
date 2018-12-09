package com.kmutteats.srinuan.firebasekmutteat;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerviewHolderCart extends RecyclerView.ViewHolder{

    public TextView mNamemenuC,mPriceC,mNameresC,countfood,mUrl;
    public Button increase,decrease;
    public ImageView mPicMenuC;
    //public Button mDelM;

    public MyRecyclerviewHolderCart(View itemView) {
        super( itemView );
        mNamemenuC = itemView.findViewById(R.id.mNamemenuC);
        mPriceC = itemView.findViewById(R.id.mPriceC);
        mNameresC = itemView.findViewById(R.id.mNameresC);
        //mUrl = itemView.findViewById(R.id.mUrl);
        mPicMenuC = itemView.findViewById(R.id.mPicmenuC);
        increase = itemView.findViewById( R.id.addfood );
        decrease = itemView.findViewById( R.id.minusfood );
        countfood = itemView.findViewById( R.id.countfood );
        //mDelM = itemView.findViewById(R.id.Delbtmenu);

    }
}
