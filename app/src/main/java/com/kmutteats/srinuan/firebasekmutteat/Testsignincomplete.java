package com.kmutteats.srinuan.firebasekmutteat;
//Test Page
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;



import java.util.HashMap;
import java.util.Map;

public class Testsignincomplete extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private CardView btnSignOut;
    private static final String TAG="Testsigncomplete";
    public static final String STATUS_KEY = "Status";
    public static final String USERNAME_KEY = "Username";
    public static final String PHONE_KEY = "Phone";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView texttest1,texttest2,texttest3,texttest4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testsignincomplete);
        btnSignOut = (CardView) findViewById(R.id.signouttest);
        texttest1 = (TextView) findViewById(R.id.test);
        texttest2 = (TextView) findViewById(R.id.textView3);
        texttest3 = (TextView) findViewById(R.id.textView4);
        texttest4 = (TextView)  findViewById(R.id.textView7);
        mAuth = FirebaseAuth.getInstance();
        Intent _recievemail = getIntent();
        final String email = _recievemail.getStringExtra("mail");
        final ProgressDialog progressDialog = new ProgressDialog(Testsignincomplete.this);
        progressDialog.setTitle("Loading Data");
        progressDialog.setMessage("Waiting . . .");
        progressDialog.show();
        db.collection("account").document("MERCHANT").collection(email).document("data account")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>()
                {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot)
                    {
                        if(documentSnapshot.exists())
                        {
                            final String username = documentSnapshot.getString(USERNAME_KEY);
                            final String phonenum = documentSnapshot.getString(PHONE_KEY);
                            final String statuscheck = documentSnapshot.getString(STATUS_KEY);
                            texttest1.setText(email);
                            texttest2.setText(phonenum);
                            texttest3.setText(statuscheck);
                            texttest4.setText(username);
                            progressDialog.dismiss();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG,"Document fail to loaded");
                    }
                });


        db.collection("account").document("CUSTOMER").collection(email).document("data account")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>()
                {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot)
                    {
                        if(documentSnapshot.exists())
                        {
                            final String username = documentSnapshot.getString(USERNAME_KEY);
                            final String phonenum = documentSnapshot.getString(PHONE_KEY);
                            final String statuscheck = documentSnapshot.getString(STATUS_KEY);
                            texttest1.setText(email);
                            texttest2.setText(phonenum);
                            texttest3.setText(statuscheck);
                            texttest4.setText(username);
                            progressDialog.dismiss();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG,"Document fail to loaded");
                    }
                });

        btnSignOut.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {
                mAuth.signOut();
                finish();
                toastMessage("Sign out complete!");
            }

        });

    }

    private void toastMessage(String message)
    {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }


}
