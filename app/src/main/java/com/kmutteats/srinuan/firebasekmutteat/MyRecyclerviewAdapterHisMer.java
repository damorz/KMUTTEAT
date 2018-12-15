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

public class MyRecyclerviewAdapterHisMer extends RecyclerView.Adapter<MyRecyclerviewHolderHisMer>
{
    private static final String TAG = "RecycleviewLOG";
    HistoryMFragment recyclerview;
    ArrayList<HisMer> userArrayList;

    public MyRecyclerviewAdapterHisMer(HistoryMFragment recyclerview, ArrayList<HisMer> userArrayList)
    {
        this.recyclerview = recyclerview;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public MyRecyclerviewHolderHisMer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(recyclerview.getActivity().getApplicationContext());
        View view =layoutInflater.inflate(R.layout.single_row_history_merchant,parent,false );
        return new MyRecyclerviewHolderHisMer(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerviewHolderHisMer holder, int position) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        holder.FoodNameHisMer.setText(userArrayList.get(position).getFoodnameHisMer());
        holder.CountHisMer.setText(userArrayList.get(position).getCountHisMer());
        holder.PriceHisMer.setText(userArrayList.get(position).getPriceHisMer());
        holder.EmailHisMer.setText(userArrayList.get(position).getEmailHisMer());
        Glide.with(recyclerview.getActivity().getApplicationContext()).load(userArrayList.get(position).getUrlHisMer()).apply(requestOptions.centerCrop().override(200,200)).into(holder.mPicMenuHisMer);

    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }
}
