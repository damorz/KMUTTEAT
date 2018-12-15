package com.kmutteats.srinuan.firebasekmutteat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Placeholder;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import static android.os.SystemClock.sleep;

public class Homemerchant extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "LOGHomeMerchant";
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    TextView emailTopM,usernameTopM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_homemerchant );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.addDrawerListener( toggle );
        toggle.syncState();

        NavigationView navigationView2 = (NavigationView) findViewById( R.id.nav_view ); //no2
        navigationView2.setNavigationItemSelectedListener( this ); //no2

        Intent _recievemail = getIntent();
        final String email = _recievemail.getStringExtra("mail");
        Intent _recievenameres = getIntent();
        final String nameres = _recievenameres.getStringExtra("nr");
        Intent _recievestatus = getIntent();
        final String statuscheck = _recievestatus.getStringExtra("statuscheck");
        SharedPreferences prefs3 = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor3 = prefs3.edit();
        editor3.putString("Emailtest4", email); //InputString: from the EditText
        editor3.commit();

        db=FirebaseFirestore.getInstance();
        db.collection("account").document("MERCHANT").collection(email).document("data account")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            final String usernamepull = documentSnapshot.getString("Username");
                            usernameTopM = (TextView) findViewById(R.id.usernameTopM);
                            emailTopM = (TextView) findViewById(R.id.emailTopM);
                            usernameTopM.setText(usernamepull);
                            emailTopM.setText(email);
                        }
                    }
                });




        //Toast.makeText(Homemerchant.this,"Homemerchant : "+nameres,Toast.LENGTH_SHORT).show();

        //defalt fragment for homeM

        Intent k = new Intent(Homemerchant.this,MyRecyclerviewAdapterMenu.class);
        k.putExtra("NameResMenu",nameres);

        Bundle bundle = new Bundle();
        bundle.putString("res", nameres);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        HomeMFragment homeMFragment = new HomeMFragment();
        homeMFragment.setArguments(bundle);
        ft.replace(R.id.Flmain2,homeMFragment);
        ft.commit();

        navigationView2.setCheckedItem(R.id.nav_homeM); //no2

        statusOF();

        /*db.collection("account").document("MERCHANT").collection(email).document("data account")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>()
                {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot)
                    {
                        if(documentSnapshot.exists())
                        {
                            final String _nameres = documentSnapshot.getString("Restaurant name");*/




                        /*}
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG,"Document fail to loaded");
                    }
                });*/
//        HomeMFragment m = new HomeMFragment();
//        m.setname("Test");


    }
    public void setActionBarTitle(String title) { getSupportActionBar().setTitle(title);}

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        if (drawer.isDrawerOpen( GravityCompat.START )) {
            drawer.closeDrawer( GravityCompat.START );
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.homemerchant, menu );
        statusOF();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected( item );
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        mAuth = FirebaseAuth.getInstance();
        int id = item.getItemId();

        Intent _recievenameres = getIntent();
        final String nameres = _recievenameres.getStringExtra("nr");

        if (id == R.id.nav_homeM) {
            // Handle the camera action

            Bundle bundle = new Bundle();
            bundle.putString("res", nameres);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            HomeMFragment homeMFragment = new HomeMFragment();
            homeMFragment.setArguments(bundle);
            ft.replace(R.id.Flmain2,homeMFragment);
            ft.commit();
        } else if (id == R.id.nav_orderM) {
            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.Flmain2,new OrderMFragment());
            ft.commit();
        } else if (id == R.id.nav_historyM) {
            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.Flmain2,new HistoryMFragment());
            ft.commit();
        } else if (id == R.id.nav_coinM) {
            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.Flmain2,new CoinMFragment());
            ft.commit();
        }
        else if (id == R.id.nav_logout2) {
            mAuth.signOut();
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        drawer.closeDrawer( GravityCompat.START );

        return true;
    }

    public void statusOF()
    {
        db = FirebaseFirestore.getInstance();
        Intent _recievemail = getIntent();
        final String email = _recievemail.getStringExtra("mail");
        Intent _recievestatus = getIntent();
        final String statuscheck = _recievestatus.getStringExtra("statuscheck");
        db.collection("account").document("MERCHANT").collection(email).document("data account")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>()
                {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot)
                    {
                        if(documentSnapshot.exists())
                        {
                            final String nameres = documentSnapshot.getString("Restaurant name");
                            final Switch SWstatusOF=(Switch)findViewById(R.id.SWstatusOF);
                            SWstatusOF.setOnClickListener( new View.OnClickListener()
                            {
                                @Override
                                public void onClick(View v)
                                {
                                    Map<String, Object> info = new HashMap<>();
                                    if (SWstatusOF.isChecked()==true)
                                    {
                                        String statusOF ="now open";
                                        info.put("Status RES",statusOF);
                                    }
                                    else
                                    {
                                        String statusOF = "closed";
                                        info.put("Status RES",statusOF);
                                    }
                                    db.collection("Restaurant").document(nameres)
                                            .set(info,SetOptions.merge());
                                }


                            });

                            //Status.setText();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG,"Document fail to loaded");
                    }
                });


    }
}
