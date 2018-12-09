package com.kmutteats.srinuan.firebasekmutteat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerviewAdapterMenuEachRes extends RecyclerView.Adapter<MyRecyclerviewHolderMenuEachRes>
{
    private static final String TAG = "RecycleviewLOG";
    MenuEachResFragment recyclerview;
    ArrayList<Menu> userArrayList;


    public MyRecyclerviewAdapterMenuEachRes(MenuEachResFragment recyclerview, ArrayList<Menu> userArrayList)
    {
        this.recyclerview = recyclerview;
        this.userArrayList = userArrayList;
        //this.URL = url;
        //this.iconadapter = icon;
    }

    @NonNull
    @Override
    public MyRecyclerviewHolderMenuEachRes onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(recyclerview.getActivity().getApplicationContext());
        View view =layoutInflater.inflate(R.layout.singlerow_each_res,parent,false );
        return new MyRecyclerviewHolderMenuEachRes(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerviewHolderMenuEachRes holder, int position)
    {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        holder.mNamemenu.setText(userArrayList.get(position).getNamemenu());
        holder.mDescription.setText(userArrayList.get(position).getDescrip());
        holder.mPrice.setText(userArrayList.get(position).getPrice());
        Glide.with(recyclerview.getActivity().getApplicationContext()).load(userArrayList.get(position).getUrl()).apply(requestOptions.centerCrop().override(200,200)).into(holder.mPicMenu);


    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }
}
