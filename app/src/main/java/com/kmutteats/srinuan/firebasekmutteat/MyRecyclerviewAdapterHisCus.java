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

public class MyRecyclerviewAdapterHisCus extends RecyclerView.Adapter<MyRecyclerviewHolderHisCus>
{
    private static final String TAG = "RecycleviewLOG";
    HistoryFragment recyclerview;
    ArrayList<HisCus> userArrayList;

    public MyRecyclerviewAdapterHisCus(HistoryFragment recyclerview, ArrayList<HisCus> userArrayList)
    {
        this.recyclerview = recyclerview;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public MyRecyclerviewHolderHisCus onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(recyclerview.getActivity().getApplicationContext());
        View view =layoutInflater.inflate(R.layout.single_row_history_customer,parent,false );
        return new MyRecyclerviewHolderHisCus(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerviewHolderHisCus holder, int position)
    {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        holder.FoodNameHisCus.setText(userArrayList.get(position).getFoodnameHisCus());
        holder.NameResHisCus.setText(userArrayList.get(position).getNameresHisCus());
        holder.PriceHisCus.setText(userArrayList.get(position).getPriceHisCus());
        Glide.with(recyclerview.getActivity().getApplicationContext()).load(userArrayList.get(position).getUrlHisCus()).apply(requestOptions.centerCrop().override(200,200)).into(holder.mPicMenuHisCus);
    }

    @Override
    public int getItemCount()
    {
        return userArrayList.size();
    }
}
