package com.kmutteats.srinuan.firebasekmutteat;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.instrumentation.stats.Tag;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerviewAdapter extends RecyclerView.Adapter<MyRecyclerviewHolder> {

    private static final String TAG = "RecycleviewLOG";
    HomeCFragment recyclerview;
    ArrayList<User> userArrayList;
    ArrayList<Integer> iconadapter;

    public MyRecyclerviewAdapter(HomeCFragment recyclerview, ArrayList<User> userArrayList,ArrayList<Integer> icon) {
        this.recyclerview = recyclerview;
        this.userArrayList = userArrayList;
        this.iconadapter = icon;
    }

    @NonNull
    @Override
    public MyRecyclerviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(recyclerview.getActivity().getApplicationContext());
        View view =layoutInflater.inflate(R.layout.singlerow,parent,false );
        return new MyRecyclerviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerviewHolder holder, final int position) {

        holder.mUsername.setText(userArrayList.get(position).getUsername());
        holder.mUserstatus.setText(userArrayList.get(position).getUserstatus());
        holder.mLocation.setText(userArrayList.get(position).getLocation());
        holder.mPhonenum.setText(userArrayList.get(position).getPhonenum());
        holder.mPicres.setImageResource(iconadapter.get(position));
        holder.mDel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                deleteSelectRow(position);
            }
        });
    }

    private void deleteSelectRow(int position) {
        recyclerview.db.collection("Restaurant")
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
