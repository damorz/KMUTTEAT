package com.kmutteats.srinuan.firebasekmutteat;


import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeMFragment extends Fragment {


    private FloatingActionButton addmenu;
    private TextView testtext;

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
        testtext = (TextView) view.findViewById(R.id.testnameres);




        /*Intent getnameres = getIntent();
        final String nameres = getnameres.getStringExtra( "nr" );*/

        addmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getArguments();
                if (bundle!=null) {
                    String nameres = bundle.getString("res");
                    //Toast.makeText(getActivity().getApplicationContext(), "Home M !!!!!!!!!!! ! : " + nameres, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getActivity(),addmenu.class);
                    i.putExtra("nr2",nameres);
                    startActivity(i);
                }

            }
        });

        return view;
    }

}
