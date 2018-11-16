//Login Page
package com.kmutteats.srinuan.firebasekmutteat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG="MainActivity";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    // UI references.
    private EditText mEmail, mPassword;
    private CardView btnSignIn,creatwaccbtn;
    private TextView forgerpassbtn;
    private Button checkbtn;
    public static final String STATUS_KEY = "Status";
    public static final String USERNAME_KEY = "Username";
    public static final String PHONE_KEY = "Phone";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //declare buttons and edit texts in oncreate
        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        btnSignIn = (CardView) findViewById(R.id.signin1);
        creatwaccbtn = (CardView) findViewById(R.id.createacc);
        forgerpassbtn = (TextView) findViewById(R.id.forgetpass);
        checkbtn = (Button) findViewById(R.id.CheckFGbt);


        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener()
        {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null)
                {
                    // User is signed in
                    Log.d(TAG, "=======SIGN IN=======" + user.getUid());
                    /*toastMessage("Successfully signed in with " + user.getEmail());
                    Intent x = new Intent(MainActivity.this , Testsignincomplete.class);
                    startActivity(x);*/
                }
                else
                {
                    // User is signed out
                    Log.d(TAG, "======SIGN OUT=======");
                    //toastMessage("Successfully signed out.");
                }
            }
        };
        btnSignIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString();
                String pass = mPassword.getText().toString();
                if (!email.equals("") && !pass.equals(""))
                {
                    final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setTitle("Sign-in");
                    progressDialog.setMessage("Loading . . .");
                    progressDialog.show();
                    mAuth.signInWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>()
                            {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task)
                                {
                                    if(!task.isSuccessful())
                                    {
                                        progressDialog.dismiss();
                                        Toast.makeText(MainActivity.this,"E-mail or Password is incorrect",Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        progressDialog.dismiss();
                                        checkIfemailVerified();
                                    }

                                }
                            });

                }
                else
                {
                    toastMessage("You didn't fill in all the fields.");

                }
            }
        });

        forgerpassbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent p = new Intent(MainActivity.this , Forgetpass.class);
                startActivity(p);
            }
        });

        creatwaccbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(MainActivity.this , register.class);
                startActivity(i);
            }


        });
        checkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent y = new Intent(MainActivity.this,Homemerchant.class);  ///////////////////////////////////////////// !!!!!!! อันนี้เป็นปุ่มเทสที่อยู่บนสุดของหน้าแรก
                startActivity(y);
            }
        });

    }
    @Override
    public void onStart()
    {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop()
    {
        super.onStop();
        if (mAuthListener != null)
        {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    //Create toastmessage function
    private void toastMessage(String message)
    {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    private void checkIfemailVerified()
    {

        String email = mEmail.getText().toString();
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

                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG,"Document fail to loaded");
                    }
                });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        boolean emailVerified = user.isEmailVerified();
        if(!emailVerified)
        {
            Toast.makeText(MainActivity.this, "Verify your E-mail!", Toast.LENGTH_SHORT).show();
            mAuth.signOut();
        }
        else
        {
            /*--------------------------------------------------------------------------------------------------------*/
            db.collection("account").document("MERCHANT").collection(email).document("data account")
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>()
                    {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot)
                        {
                            if(documentSnapshot.exists())
                            {
                                String email = mEmail.getText().toString();
                                final String username = documentSnapshot.getString(USERNAME_KEY);
                                final String phonenum = documentSnapshot.getString(PHONE_KEY);
                                final String statuscheck = documentSnapshot.getString(STATUS_KEY);
                                if(statuscheck.equals("Merchant0"))
                                {
                                    Intent i = new Intent(MainActivity.this,CreateRes.class);
                                    i.putExtra("mail",email);
                                    //i.putExtra("statuscheck",statuscheck);
                                    startActivity(i);
                                }
                                else if(statuscheck.equals("Customer0"))
                                {
                                    Intent j = new Intent(MainActivity.this,Homecustumer.class);
                                    j.putExtra("mail",email);
                                    j.putExtra("statuscheck",statuscheck);
                                    startActivity(j);
                                }
                                else if (statuscheck.equals("Merchant"))
                                {
                                    Intent k = new Intent(MainActivity.this,Homemerchant.class); ////////////////////////////////////!!!!!! อันนี้ถ้าเป็นคนที่มีร้านแล้วจะเด้งไปหน้าหลักของแม่ค้า
                                    k.putExtra("mail",email);
                                    k.putExtra("statuscheck",statuscheck);
                                    startActivity(k);
                                }

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
                                String email = mEmail.getText().toString();
                                final String username = documentSnapshot.getString(USERNAME_KEY);
                                final String phonenum = documentSnapshot.getString(PHONE_KEY);
                                final String statuscheck = documentSnapshot.getString(STATUS_KEY);
                                if(statuscheck.equals("Merchant0"))
                                {
                                    Intent i = new Intent(MainActivity.this,CreateRes.class);
                                    i.putExtra("mail",email);
                                    i.putExtra("statuscheck",statuscheck);
                                    startActivity(i);
                                }
                                else if(statuscheck.equals("Customer0"))
                                {
                                    Intent j = new Intent(MainActivity.this,Homecustumer.class);
                                    j.putExtra("mail",email);
                                    j.putExtra("statuscheck",statuscheck);
                                    startActivity(j);
                                }
                                else if (statuscheck.equals("Merchant"))
                                {
                                    Intent k = new Intent(MainActivity.this,Homemerchant.class);////////////////////////////////////!!!!!! อันนี้ถ้าเป็นคนที่มีร้านแล้วจะเด้งไปหน้าหลักของแม่ค้า
                                    k.putExtra("mail",email);
                                    k.putExtra("statuscheck",statuscheck);
                                    startActivity(k);
                                }

                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG,"Document fail to loaded");
                        }
                    });
            /*----------------------------------------------------------------------------------------------------------------------*/
            //Intent i = new Intent(MainActivity.this,Testsignincomplete.class);
            //i.putExtra("mail",email);
            //startActivity(i);
        }
    }
}

