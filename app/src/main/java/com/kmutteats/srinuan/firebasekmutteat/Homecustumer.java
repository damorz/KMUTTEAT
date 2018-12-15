package com.kmutteats.srinuan.firebasekmutteat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.preference.PreferenceManager;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class Homecustumer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseAuth mAuth;
    FirebaseFirestore db;
    TextView usernameTop,emailTop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homecustumer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent _recievemail = getIntent();
        final String email = _recievemail.getStringExtra("mail");
        Intent _recievestatus = getIntent();
        final String statuscheck = _recievestatus.getStringExtra("statuscheck");

        db=FirebaseFirestore.getInstance();
        db.collection("account").document("CUSTOMER").collection(email).document("data account")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                          @Override
                                          public void onSuccess(DocumentSnapshot documentSnapshot) {
                                              if (documentSnapshot.exists()) {
                                                  final String usernamepull = documentSnapshot.getString("Username");
                                                  usernameTop = (TextView) findViewById(R.id.usernameTop);
                                                  emailTop = (TextView) findViewById(R.id.emailTop);
                                                  usernameTop.setText(usernamepull);
                                                  emailTop.setText(email);
                                              }
                                          }
                                      });


        //Toast.makeText( this, "mail homecus : "+email, Toast.LENGTH_SHORT ).show();
        //deflat fragment for home

        SharedPreferences prefs2 = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor2 = prefs2.edit();
        editor2.putString("Emailtest2", email); //InputString: from the EditText
        editor2.commit();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("Emailtest", email); //InputString: from the EditText
        editor.commit();

        SharedPreferences prefs3 = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor3 = prefs3.edit();
        editor3.putString("Emailtest3", email); //InputString: from the EditText
        editor3.commit();

        Bundle bundle = new Bundle();
        bundle.putString("email", email);
        HomeCFragment homeCFragment = new HomeCFragment();
        homeCFragment.setArguments(bundle);


        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.Flmain, homeCFragment);
        ft.commit();

        navigationView.setCheckedItem(R.id.nav_home);
    }

    public void setActionBarTitle(String title) { getSupportActionBar().setTitle(title);}

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.homecustumer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_addcart) {
            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.Flmain,new CartFragment());
            ft.commit();
            return true;
        }

        return super.onOptionsItemSelected( item );
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        mAuth = FirebaseAuth.getInstance();
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.Flmain,new HomeCFragment());
            ft.commit();
        }
        else if (id == R.id.nav_history) {
            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.Flmain,new HistoryFragment());
            ft.commit();
        }
        else if (id == R.id.nav_coin) {
            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.Flmain,new CoinFragment());
            ft.commit();
        }
        else if (id == R.id.nav_logout) {
            mAuth.signOut();
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
