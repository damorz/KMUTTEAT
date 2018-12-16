package com.kmutteats.srinuan.firebasekmutteat;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class CoinFragment extends Fragment {


    TextView coin;
    FirebaseFirestore db;
    CardView addcoin;
    public CoinFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((Homecustumer)getActivity()).setActionBarTitle("Coin");
        View view = inflater.inflate(R.layout.fragment_coin, container, false);
        // Inflate the layout for this fragment

        addcoin = view.findViewById(R.id.maddCoin);
        coin = view.findViewById(R.id.bigcoin);
        SharedPreferences prefs3 = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        final String email = prefs3.getString("Emailtest3", "no id");
        db=FirebaseFirestore.getInstance();
        db.collection("account").document("CUSTOMER").collection(email).document("data account")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String coinn = documentSnapshot.getString("Coin");
                            coin.setText(coinn);
                        }
                    }
                });
        addcoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity().getApplicationContext(), "เย้ ได้ละแง้", Toast.LENGTH_SHORT).show();
                db = FirebaseFirestore.getInstance();
                db.collection("account").document("CUSTOMER").collection(email).document("data account")
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                String coinCus = documentSnapshot.getString("Coin") ;
                                double coinCusInt  = Double.valueOf(coinCus);
                                coinCusInt = coinCusInt + 100;
                                Map<String , Object> coinCusreturn = new HashMap<>();
                                coinCusreturn.put("Coin",""+coinCusInt);
                                final double finalCoinCusInt = coinCusInt;
                                db.collection("account").document("CUSTOMER").collection(email).document("data account")
                                        .set(coinCusreturn)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                coin.setText(""+ finalCoinCusInt);
                                            }
                                        });

                            }
                        });



            }
        });


        return view;
    }

}
