package com.kmutteats.srinuan.firebasekmutteat;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


/**
 * A simple {@link Fragment} subclass.
 */
public class CoinMFragment extends Fragment {

    FirebaseFirestore db;
    TextView coinM;

    public CoinMFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((Homemerchant)getActivity()).setActionBarTitle("Coin");
        View view = inflater.inflate( R.layout.fragment_coin_m, container, false );
        // Inflate the layout for this fragment


        coinM = view.findViewById(R.id.bigcoinM);
        SharedPreferences prefs3 = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        String email = prefs3.getString("Emailtest4", "no id");

        db=FirebaseFirestore.getInstance();
        db.collection("account").document("MERCHANT").collection(email).document("data account")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            final String coinn = documentSnapshot.getString("Coin");
                            coinM.setText(coinn);
                        }
                    }
                });

        return view;
    }

}
