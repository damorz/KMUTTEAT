package com.kmutteats.srinuan.firebasekmutteat;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerviewAdapterOrder extends RecyclerView.Adapter<MyRecyclerviewHolderOrder> {

    private static final String TAG = "RecycleviewLOG";
    OrderMFragment recyclerview;
    ArrayList<Orderlist> userArrayList;
    FirebaseFirestore db;

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
    public void onBindViewHolder(@NonNull MyRecyclerviewHolderOrder holder, final int position)
    {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        holder.FoodNameOrder.setText(userArrayList.get(position).getFoodnameOrder());
        holder.EmailOrder.setText(userArrayList.get(position).getEmailOrder());
        holder.PriceOrder.setText(userArrayList.get(position).getPriceOrder());
        holder.CountOrder.setText(userArrayList.get(position).getCountfood());
        holder.DescriptionOrder.setText(userArrayList.get(position).getDescrip());
        Glide.with(recyclerview.getActivity().getApplicationContext()).load(userArrayList.get(position).getUrlOrder()).apply(requestOptions.centerCrop().override(200,200)).into(holder.mPicMenuOrder);
        holder.mcheck.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(recyclerview.getActivity().getApplicationContext(), "Please wait . . .", Toast.LENGTH_SHORT).show();
                SharedPreferences prefs6 = PreferenceManager.getDefaultSharedPreferences(recyclerview.getActivity().getApplicationContext());
                String nameres = prefs6.getString("Nameres", "no id");
                db = FirebaseFirestore.getInstance();
                Map<String,Object> info = new HashMap<>();
                info.put("Customer E-mail",userArrayList.get(position).getEmailOrder());
                info.put("Food name",userArrayList.get(position).getFoodnameOrder());
                info.put("Price",userArrayList.get(position).getPriceOrder());
                info.put("Count food",userArrayList.get(position).getCountfood());
                info.put("URL",userArrayList.get(position).getUrlOrder());
                db.collection("History").document("Merchant").collection(nameres).document(userArrayList.get(position).getFoodnameOrder())
                .set(info);
                deleteSelectRow(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    private void deleteSelectRow(int position)
    {
        SharedPreferences prefs6 = PreferenceManager.getDefaultSharedPreferences(recyclerview.getActivity().getApplicationContext());
        String nameres = prefs6.getString("Nameres", "no id");
        //Toast.makeText(recyclerview.getActivity().getApplicationContext(), "แงง" + userArrayList.get(position).getFoodnameOrder(), Toast.LENGTH_SHORT).show();

        recyclerview.db.collection("Order list").document("Link").collection(nameres).document(userArrayList.get(position).getFoodnameOrder())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //Toast.makeText(recyclerview.getActivity().getApplicationContext(),"Delete Successfully",Toast.LENGTH_SHORT).show();
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
