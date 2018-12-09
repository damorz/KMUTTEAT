package com.kmutteats.srinuan.firebasekmutteat;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuEachResFragment extends Fragment {



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
        Toast.makeText(getActivity().getApplicationContext(), "Name Res passed! : " + nameresE, Toast.LENGTH_SHORT).show();
        //////// Tedt recieve data




        return view;
    }

}
