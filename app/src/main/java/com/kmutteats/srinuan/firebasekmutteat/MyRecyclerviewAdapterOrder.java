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

public class MyRecyclerviewAdapterOrder extends RecyclerView.Adapter<MyRecyclerviewHolderOrder> {

    private static final String TAG = "RecycleviewLOG";
    OrderMFragment recyclerview;
    ArrayList<Orderlist> userArrayList;

    public MyRecyclerviewAdapterOrder(OrderMFragment recyclerview, ArrayList<Orderlist> userArrayList)
    {
        this.recyclerview = recyclerview;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public MyRecyclerviewHolderOrder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(recyclerview.getActivity().getApplicationContext());
        View view =layoutInflater.inflate(R.layout.single_row_orderlist,parent,false );
        return new MyRecyclerviewHolderOrder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerviewHolderOrder holder, int position)
    {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        holder.FoodNameOrder.setText(userArrayList.get(position).getFoodnameOrder());
        holder.EmailOrder.setText(userArrayList.get(position).getEmailOrder());
        holder.PriceOrder.setText(userArrayList.get(position).getPriceOrder());
        Glide.with(recyclerview.getActivity().getApplicationContext()).load(userArrayList.get(position).getUrlOrder()).apply(requestOptions.centerCrop().override(200,200)).into(holder.mPicMenuOrder);

    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }
}
