package com.kmutteats.srinuan.firebasekmutteat;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {


    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((Homecustumer)getActivity()).setActionBarTitle("History");
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        // Inflate the layout for this fragment


        return view;
    }

}
