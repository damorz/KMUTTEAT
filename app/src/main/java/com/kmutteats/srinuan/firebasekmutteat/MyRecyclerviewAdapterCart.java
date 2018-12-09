package com.kmutteats.srinuan.firebasekmutteat;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerviewAdapterCart extends RecyclerView.Adapter<MyRecyclerviewHolderCart>{
    private static final String TAG = "RecycleviewLOG";

    CartFragment recyclerview;
    ArrayList<Cart> userArrayList;

    public MyRecyclerviewAdapterCart(CartFragment recyclerview, ArrayList<Cart> userArrayList)
    {
        this.recyclerview = recyclerview;
        this.userArrayList = userArrayList;
        //this.URL = url;
        //this.iconadapter = icon;
    }

    @NonNull
    @Override
    public MyRecyclerviewHolderCart onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(recyclerview.getActivity().getApplicationContext());
        View view =layoutInflater.inflate(R.layout.single_row_cart,parent,false );
        return new MyRecyclerviewHolderCart(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyRecyclerviewHolderCart holder, final int position) {

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        holder.mNamemenuC.setText(userArrayList.get(position).getNamemenu());
        holder.mNameresC.setText(userArrayList.get(position).getNameresmenu());
        holder.mPriceC.setText(userArrayList.get(position).getPrice());


        /*String close = "closed";
        if(userArrayList.get(position).getOnOff()==close)
        {
            holder.mOnOff.setTextColor(Color.parseColor("#f5220d"));
            holder.mOnOff.setText(userArrayList.get(position).getOnOff());

        }
        else
        {
            holder.mOnOff.setTextColor(Color.parseColor("#4baf11"));
            holder.mOnOff.setText(userArrayList.get(position).getOnOff());
        }*/

        //holder.mUrl.setText(userArrayList.get(position).getUrl());
        //Picasso.get().load(userArrayList.get(position).getUrl()).resize(250,250).centerCrop().into(holder.mPicres);
        Glide.with(recyclerview.getActivity().getApplicationContext()).load(userArrayList.get(position).getUrl()).apply(requestOptions.centerCrop().override(200,200)).into(holder.mPicMenuC);


        holder.increase.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                holder.countfood.setText( "1" );
                Toast.makeText( recyclerview.getActivity().getApplicationContext(), "add : ", Toast.LENGTH_SHORT ).show();
            }
        });
        holder.decrease.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                    //deleteSelectRow(position);
                holder.countfood.setText( "1000" );
                Toast.makeText( recyclerview.getActivity().getApplicationContext(), "Minus : ", Toast.LENGTH_SHORT ).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    private void deleteSelectRow(int position) {
        recyclerview.db.collection("Cart")
                .document(userArrayList.get(position).getNamemenu())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(recyclerview.getActivity().getApplicationContext(),"Delete Successfully",Toast.LENGTH_SHORT).show();
                        recyclerview.loadDataFromFirebase();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(recyclerview.getActivity().getApplicationContext(),"Unable to delete --- 3 ---",Toast.LENGTH_SHORT).show();
                        Log.w(TAG, "----- 3 -----",e.getCause());
                    }
                });
    }

}
