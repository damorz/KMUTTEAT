package com.kmutteats.srinuan.firebasekmutteat;


import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeMFragment extends Fragment {


    FirebaseFirestore db ;
    String TAG = "RecyclerviewLog";
    RecyclerView mRecyclerView;
    MyRecyclerviewAdapterMenu adapter;
    MyRecyclerviewHolderMenu holder;
    Button updatabtn;
    private FloatingActionButton BT;
    ArrayList<Menu> userArrayList;



    public HomeMFragment() {


        // Required empty public constructor
    }

    public void setname(String text){
        System.out.println(text);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_m, container, false);
        BT = (FloatingActionButton)view.findViewById(R.id.addmenuFG) ;
        Bundle bundle = getArguments();
        if (bundle!=null) {
            String nameres = bundle.getString("res");
            //Toast.makeText(getActivity().getApplicationContext(), "Home M !!!!!!!!!!! ! : " + nameres, Toast.LENGTH_SHORT).show();
        }
        userArrayList = new ArrayList<>();
        //setUpRecycleView();
        mRecyclerView = view.findViewById(R.id.mRecyclerviewmenu) ;
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //setUpFirebase();
        db = FirebaseFirestore.getInstance();
        loadDataFromFirebase();

        //setupUpdateButton();
        updatabtn = view.findViewById(R.id.mUpdatebtmenu);
        updatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadDataFromFirebase();
            }
        });
        /*Intent getnameres = getIntent();
        final String nameres = getnameres.getStringExtra( "nr" );*/
        BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getArguments();
                if (bundle!=null) {
                    String nameres = bundle.getString("res");
                    //Toast.makeText(getActivity().getApplicationContext(), "Home M !!!!!!!!!!! ! : " + nameres, Toast.LENGTH_SHORT).show();
                    Intent k = new Intent(getActivity().getApplicationContext(),addmenu.class);
                    k.putExtra("nr2",nameres);
                    startActivity(k);
                }
            }
        });

        return view;
    }

    public void loadDataFromFirebase() {


        if(userArrayList.size()>0)
            userArrayList.clear();


        Bundle bundle = getArguments();
        if (bundle!=null) {
            String nameres = bundle.getString("res");
            //Toast.makeText(getActivity().getApplicationContext(), "Home M !!!!!!!!!!! ! : " + nameres, Toast.LENGTH_SHORT).show();

            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle("Loading Restaurant");
            progressDialog.setMessage("Loading . . .");
            progressDialog.show();
            db.collection("Menu").document("Link").collection(nameres)
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

                            adapter = new MyRecyclerviewAdapterMenu(HomeMFragment.this, userArrayList);
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
