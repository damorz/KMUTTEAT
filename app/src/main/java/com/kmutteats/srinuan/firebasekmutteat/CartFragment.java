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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {


    FirebaseFirestore db ;
    RecyclerView mRecyclerView;
    ArrayList<Cart> userArrayList;
    MyRecyclerviewAdapterCart adapter;
    MyRecyclerviewHolderCart holder;
    Button updatabtn,buynow;
    TextView total;
    EditText descriCart;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((Homecustumer)getActivity()).setActionBarTitle("Cart");
        View view = inflater.inflate( R.layout.fragment_cart, container, false );

        SharedPreferences prefs2 = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        final String email = prefs2.getString("Emailtest2", "no id");
        //Toast.makeText(getActivity().getApplicationContext(), "cart ! mail !: " + email, Toast.LENGTH_SHORT).show();

        userArrayList = new ArrayList<>();
        //setUpRecycleView();
        mRecyclerView = view.findViewById(R.id.mRecyclerviewCart) ;
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        db = FirebaseFirestore.getInstance();
        loadDataFromFirebase();

        //setupUpdateButton();
        descriCart = view.findViewById(R.id.descriCart);
        total = view.findViewById(R.id.total);
        buynow = view.findViewById(R.id.buynow);
        updatabtn = view.findViewById(R.id.mUpdatebtC);
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

        SharedPreferences prefs2 = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        String email = prefs2.getString("Emailtest2", "no id");
        Toast.makeText(getActivity().getApplicationContext(), "cart ! mail !: " + email, Toast.LENGTH_SHORT).show();

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Loading Cart");
        progressDialog.setMessage("Loading . . .");
        progressDialog.show();
        db.collection("Cart").document("link").collection(email)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        for (DocumentSnapshot querySnapshot : task.getResult()) {
                            Cart cart = new Cart(querySnapshot.getId(),
                                    querySnapshot.getString("Food name"),
                                    querySnapshot.getString("Price"),
                                    querySnapshot.getString("URL"),
                                    querySnapshot.getString("Restaurant name"),
                                    querySnapshot.getString("Count food")); //count test
                            userArrayList.add(cart);
                        }
                        adapter = new MyRecyclerviewAdapterCart(CartFragment.this, userArrayList);
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
