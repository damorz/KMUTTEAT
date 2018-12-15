package com.kmutteats.srinuan.firebasekmutteat;


import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderMFragment extends Fragment
{

    FirebaseFirestore db ;
    String TAG = "RecyclerviewLog";
    RecyclerView mRecyclerView;
    MyRecyclerviewAdapterOrder adapter;
    MyRecyclerviewHolderOrder holder;
    Button updatabtn;
    ArrayList<Orderlist> userArrayList;


    public OrderMFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((Homemerchant)getActivity()).setActionBarTitle("Orderlist");
        View view =inflater.inflate( R.layout.fragment_order_m, container, false );
        // Inflate the layout for this fragment

        userArrayList = new ArrayList<>();
        //setUpRecycleView();
        mRecyclerView = view.findViewById(R.id.mRecyclerviewOrder) ;
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        //setUpFirebase();
        db = FirebaseFirestore.getInstance();
        loadDataFromFirebase();

        updatabtn = view.findViewById(R.id.mUpdatebtOrder);
        updatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadDataFromFirebase();
            }
        });




        return view;
    }

    public void loadDataFromFirebase() {

        SharedPreferences prefs4 = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        String nameres = prefs4.getString("NameresToOrder", "no id");


        if(userArrayList.size()>0)
            userArrayList.clear();

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Loading Orderlist");
        progressDialog.setMessage("Loading . . .");
        progressDialog.show();
        db.collection("Order list").document("Link").collection(nameres)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot querySnapshot : task.getResult()) {

                            Orderlist orderlist = new Orderlist(querySnapshot.getId(),
                                    querySnapshot.getString("Food"),
                                    querySnapshot.getString("Price"),
                                    querySnapshot.getString("Customer e-mail"),
                                    querySnapshot.getString("URL"),
                                    querySnapshot.getString("Count food")); //status test
                            userArrayList.add(orderlist);
                        }

                        adapter = new MyRecyclerviewAdapterOrder(OrderMFragment.this, userArrayList);
                        mRecyclerView.setAdapter(adapter);
                        progressDialog.dismiss();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Problem ----1----", Toast.LENGTH_SHORT).show();
                        Log.w(TAG, "---- 1 ----", e.getCause());
                    }
                });
    }

}
