package com.kmutteats.srinuan.firebasekmutteat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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

public class MyRecyclerviewAdapterMenu extends RecyclerView.Adapter<MyRecyclerviewHolderMenu>
{
    private static final String TAG = "RecycleviewLOG";
    HomeMFragment recyclerview;
    ArrayList<Menu> userArrayList;

    public MyRecyclerviewAdapterMenu(HomeMFragment recyclerview, ArrayList<Menu> userArrayList)
    {
        this.recyclerview = recyclerview;
        this.userArrayList = userArrayList;
        //this.URL = url;
        //this.iconadapter = icon;
    }

    @NonNull
    @Override
    public MyRecyclerviewHolderMenu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(recyclerview.getActivity().getApplicationContext());
        View view =layoutInflater.inflate(R.layout.single_row_menu,parent,false );
        return new MyRecyclerviewHolderMenu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerviewHolderMenu holder, final int position) {

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        holder.mNamemenu.setText(userArrayList.get(position).getNamemenu());
        holder.mDescription.setText(userArrayList.get(position).getDescrip());
        holder.mPrice.setText(userArrayList.get(position).getPrice());

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
        Glide.with(recyclerview.getActivity().getApplicationContext()).load(userArrayList.get(position).getUrl()).apply(requestOptions.centerCrop().override(200,200)).into(holder.mPicMenu);

        holder.mDelM.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                deleteSelectRow(position);
            }
        });

        ///// Test Click



    }

    private void deleteSelectRow(int position)
    {
        String nameres = userArrayList.get(position).getNameresmenu();
        recyclerview.db.collection("Menu").document("Link").collection(nameres)
                .document(userArrayList.get(position).getUserId())
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

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

}
