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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerviewAdapterMenuEachRes extends RecyclerView.Adapter<MyRecyclerviewHolderMenuEachRes>
{
    private static final String TAG = "RecycleviewLOG";
    MenuEachResFragment recyclerview;
    ArrayList<Menu> userArrayList;
    FirebaseFirestore db;


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
    public void onBindViewHolder(@NonNull MyRecyclerviewHolderMenuEachRes holder, final int position)
    {
        db = FirebaseFirestore.getInstance();
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        holder.mNamemenu.setText(userArrayList.get(position).getNamemenu());
        holder.mDescription.setText(userArrayList.get(position).getDescrip());
        holder.mPrice.setText(userArrayList.get(position).getPrice());
        Glide.with(recyclerview.getActivity().getApplicationContext()).load(userArrayList.get(position).getUrl()).apply(requestOptions.centerCrop().override(200,200)).into(holder.mPicMenu);

        holder.btaddcart.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(recyclerview.getActivity().getApplicationContext());
                String email = prefs.getString("Emailtest", "no id");

                //Toast.makeText( recyclerview.getActivity().getApplicationContext(),"mail adapter : " +email,Toast.LENGTH_SHORT ).show();
                Map<String, Object> info = new HashMap<>();
                info.put( "Food name",userArrayList.get( position ).getNamemenu() );
                info.put( "Price",userArrayList.get( position ).getPrice() );
                info.put( "Description",userArrayList.get( position ).getDescrip() );
                info.put( "Restaurant name",userArrayList.get( position ).getNameresmenu() );
                info.put( "URL",userArrayList.get( position ).getUrl() );
                db.collection( "Cart" ).document("link").collection( email ).document(userArrayList.get( position ).getNamemenu())
                        .set(info);
            }
        } );

    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }
}
