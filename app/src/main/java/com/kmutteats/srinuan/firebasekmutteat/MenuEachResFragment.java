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
public class MenuEachResFragment extends Fragment {


    FirebaseFirestore db ;
    String TAG = "RecyclerviewLog";
    RecyclerView mRecyclerView;
    MyRecyclerviewAdapterMenuEachRes adapter;
    MyRecyclerviewHolderMenuEachRes holder;
    Button updatabtn;
    ArrayList<Menu> userArrayList;

    public MenuEachResFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View view  = inflater.inflate(R.layout.fragment_menu_each_res, container, false);
        // Inflate the layout for this fragment

        //////// Test revieve data
        Bundle bundle = getArguments();
        String nameresE = bundle.getString("nameresE");
        ((Homecustumer)getActivity()).setActionBarTitle(nameresE);


        //Toast.makeText(getActivity().getApplicationContext(), "menuEach mail is  : " + email, Toast.LENGTH_SHORT).show();
        //////// Tedt recieve data

        userArrayList = new ArrayList<>();
        //setUpRecycleView();
        mRecyclerView = view.findViewById(R.id.mRecyclerviewmenuE) ;
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        db = FirebaseFirestore.getInstance();
        loadDataFromFirebase();

        //setupUpdateButton();
        updatabtn = view.findViewById(R.id.mUpdatebtmenuE);
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


        Bundle bundle = getArguments();
        if (bundle!=null) {
            String nameresE = bundle.getString("nameresE");

            //Toast.makeText(getActivity().getApplicationContext(), "This is menu of : " + nameresE, Toast.LENGTH_SHORT).show();

            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle("Loading Restaurant");
            progressDialog.setMessage("Loading . . .");
            progressDialog.show();
            db.collection("Menu").document("Link").collection(nameresE)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for (DocumentSnapshot querySnapshot : task.getResult()) {

                                Menu menu = new Menu(querySnapshot.getId(),
                                        querySnapshot.getString("Food name"),
                                        querySnapshot.getString("Price"),
                                        querySnapshot.getString("Description"),
                                        querySnapshot.getString("URL"),
                                        querySnapshot.getString("Restaurant name")); //status test
                                userArrayList.add(menu);
                            }

                            adapter = new MyRecyclerviewAdapterMenuEachRes(MenuEachResFragment.this, userArrayList);
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

}
