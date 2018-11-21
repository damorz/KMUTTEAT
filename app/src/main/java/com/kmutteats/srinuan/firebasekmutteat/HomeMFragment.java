package com.kmutteats.srinuan.firebasekmutteat;


import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeMFragment extends Fragment {


    private FloatingActionButton addmenu;

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
        addmenu = (FloatingActionButton)view.findViewById(R.id.addmenuFG);
        if(getArguments()!=null){
            String nameres = getArguments().getString("edttext");
            Toast.makeText(getActivity().getApplicationContext(),nameres,Toast.LENGTH_SHORT).show();
        }



        /*Intent getnameres = getIntent();
        final String nameres = getnameres.getStringExtra( "nr" );*/

        addmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameres = getArguments().getString("edttext");
                Intent i = new Intent(getActivity(),addmenu.class);
                i.putExtra("nr2",nameres);
                startActivity(i);
            }
        });
        return view;
    }

}
