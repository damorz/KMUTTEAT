package com.kmutteats.srinuan.firebasekmutteat;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeMFragment extends Fragment {


    public HomeMFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((Homemerchant)getActivity()).setActionBarTitle("Home");
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_home_m, container, false );
    }

}
