package com.kmutteats.srinuan.firebasekmutteat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class menulist extends AppCompatActivity {
    private FloatingActionButton addmenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menulist);
        addmenu = (FloatingActionButton)findViewById(R.id.addmenu);

        Intent getnameres = getIntent();
        final String nameres = getnameres.getStringExtra( "nr" );

        addmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(menulist.this,addmenu.class);
                i.putExtra("nr2",nameres);
                startActivity(i);
            }
        });

    }
}
