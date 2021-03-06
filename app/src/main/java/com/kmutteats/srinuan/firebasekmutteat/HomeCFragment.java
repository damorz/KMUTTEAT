package com.kmutteats.srinuan.firebasekmutteat;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeCFragment extends Fragment {

    String TAG = "RecyclerviewLog";
    FirebaseFirestore db ;
    RecyclerView mRecyclerView;
    ArrayList<User> userArrayList;
    MyRecyclerviewAdapter adapter;
    Button updatabtn;

    public HomeCFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((Homecustumer)getActivity()).setActionBarTitle("Home");
        View view = inflater.inflate(R.layout.fragment_home_c, container, false);
        // Inflate the layout for this fragment





        userArrayList = new ArrayList<>();

        //setUpRecycleView();
        mRecyclerView = view.findViewById(R.id.mRecyclerview) ;
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));




        //setUpFirebase();
        db = FirebaseFirestore.getInstance();
        loadDataFromFirebase();



        //setupUpdateButton();
        updatabtn = view.findViewById(R.id.mUpdatebt);
        updatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadDataFromFirebase();
            }
        });



        return view;
    }

    public void loadDataFromFirebase() {


        if(userArrayList.size()>0)
            userArrayList.clear();


        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Loading Restaurant");
        progressDialog.setMessage("Loading . . .");
        progressDialog.show();
        db.collection("Restaurant")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(DocumentSnapshot querySnapshot: task.getResult())
                        {

                            User user = new User(querySnapshot.getId(),
                                    querySnapshot.getString("Restaurant name"),
                                    querySnapshot.getString("Description"),
                                    querySnapshot.getString("Location"),
                                    querySnapshot.getString("Phone number"),
                                    querySnapshot.getString("URL"),
                                    querySnapshot.getString("Status RES")); //status test
                            userArrayList.add(user);
                        }

                        adapter = new MyRecyclerviewAdapter(HomeCFragment.this,userArrayList);
                        mRecyclerView.setAdapter(adapter);
                        progressDialog.dismiss();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(),"Problem ----1----",Toast.LENGTH_SHORT).show();
                        Log.w(TAG, "---- 1 ----", e.getCause());
                    }
                });

    }
}
