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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    FirebaseFirestore db ;
    String TAG = "RecyclerviewLog";
    RecyclerView mRecyclerView;
    MyRecyclerviewAdapterHisCus adapter;
    MyRecyclerviewHolderHisCus holder;
    Button updatabtn;
    ArrayList<HisCus> userArrayList;

    public HistoryFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((Homecustumer)getActivity()).setActionBarTitle("History");
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        // Inflate the layout for this fragment
        userArrayList = new ArrayList<>();
        //setUpRecycleView();
        mRecyclerView = view.findViewById(R.id.mRecyclerviewHisCus) ;
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //setUpFirebase();
        db = FirebaseFirestore.getInstance();
        loadDataFromFirebase();

        updatabtn = view.findViewById(R.id.mUpdatebtHisCus);
        updatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadDataFromFirebase();
            }
        });




        return view;
    }


    public void loadDataFromFirebase() {

        SharedPreferences prefs3 = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        String email = prefs3.getString("Emailtest3", "no id");

        if(userArrayList.size()>0)
            userArrayList.clear();

            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle("Loading Restaurant");
            progressDialog.setMessage("Loading . . .");
            progressDialog.show();
            db.collection("History").document("Customer").collection(email)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for (DocumentSnapshot querySnapshot : task.getResult()) {

                                HisCus hisCus = new HisCus(querySnapshot.getId(),
                                        querySnapshot.getString("Food"),
                                        querySnapshot.getString("Restaurant name"),
                                        querySnapshot.getString("Price"),
                                        querySnapshot.getString("URL")); //status test
                                userArrayList.add(hisCus);
                            }

                            adapter = new MyRecyclerviewAdapterHisCus(HistoryFragment.this, userArrayList);
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
