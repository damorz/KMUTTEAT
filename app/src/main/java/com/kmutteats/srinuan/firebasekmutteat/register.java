package com.kmutteats.srinuan.firebasekmutteat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Map;
import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;


public class register extends AppCompatActivity
{
    EditText inputEmail, inputPassword,inputUsername,inputPhone;
    CardView btnRegister;
    ProgressBar ProgressBar;
    FirebaseAuth auth;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button bnttest;
    TextView TESTTEXT;
    public static final String STATUS_KEY = "Status";
    public static final String USERNAME_KEY = "Username";
    public static final String PHONE_KEY = "Phone";
    public static final String EMAIL_KEY = "E-mail";

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        auth = FirebaseAuth.getInstance();
        final String TAG="register";
        inputEmail = (EditText) findViewById(R.id.mailregis);
        inputPassword = (EditText) findViewById(R.id.passregis);
        inputUsername = (EditText) findViewById(R.id.userregis);
        inputPhone = (EditText) findViewById(R.id.phoneregis);
        btnRegister = (CardView) findViewById(R.id.btnregis);
        ProgressBar = (ProgressBar) findViewById(R.id.progressBarset);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup) ;


        ProgressBar.setVisibility(View.INVISIBLE);

        btnRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int radioID = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioID);
                String statuscheck = ("" + radioButton.getText() + "0");
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                String username = inputUsername.getText().toString().trim();
                String phonenum = inputPhone.getText().toString().trim();



                boolean isSelected = radioButton.isChecked ();
                if (TextUtils.isEmpty(email))
                {
                    Toast.makeText(getApplicationContext(), "Please input E-mail address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password))
                {
                    Toast.makeText(getApplicationContext(), "Please input password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(username))
                {
                    Toast.makeText(getApplicationContext(), "Please input username!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(phonenum))
                {
                    Toast.makeText(getApplicationContext(), "Please input phone number!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6)
                {
                    Toast.makeText(getApplicationContext(), "Minimum password must have 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (radioGroup==null)// ยังแก้ไม่ได้!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                {
                    Toast.makeText(getApplicationContext(), "please select your status!", Toast.LENGTH_SHORT).show();
                    return;
                }


                Map<String, Object> user = new HashMap<>();
                if(statuscheck.equals("Merchant0"))
                {
                    user.put(STATUS_KEY, statuscheck);
                    user.put(USERNAME_KEY, username);
                    user.put(PHONE_KEY,phonenum);
                    user.put(EMAIL_KEY,email);
                    user.put("Coin","0");
                    db.collection("account").document("MERCHANT").collection(email).document("data account")
                    .set(user)
                    .addOnSuccessListener(new OnSuccessListener<Void>()
                    {
                        @Override
                        public void onSuccess(Void aVoid)
                        {
                            Log.d(TAG, "Document has been saved");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener()
                    {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {
                            Log.w(TAG,"Document fail to saved",e);
                        }
                    });

                }
                if(statuscheck.equals("Customer0"))
                {
                    user.put(STATUS_KEY, statuscheck);
                    user.put(USERNAME_KEY, username);
                    user.put(PHONE_KEY,phonenum);
                    user.put(EMAIL_KEY,email);
                    user.put("Coin","500");
                    db.collection("account").document("CUSTOMER").collection(email).document("data account")
                    .set(user)
                    .addOnSuccessListener(new OnSuccessListener<Void>()
                    {
                        @Override
                        public void onSuccess(Void aVoid)
                        {
                            Log.d(TAG, "Document has been saved");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener()
                    {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {
                            Log.w(TAG,"Document fail to saved",e);
                        }
                    });
                }
                final ProgressDialog progressDialog = new ProgressDialog(register.this);
                progressDialog.setTitle("Register");
                progressDialog.setMessage("Loading . . .");
                progressDialog.show();
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {
                                Toast.makeText(register.this, "Create user : " + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                ProgressBar.setVisibility(View.GONE);

                                if (!task.isSuccessful())
                                {
                                    Toast.makeText(register.this, "Failed!" + task.getException(), Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                                else
                                {
                                    //Toast.makeText(register.this, "Success!", Toast.LENGTH_SHORT).show();
                                    sendVerificationMail();
                                    progressDialog.dismiss();
                                }
                            }
                        });

            }
        });

    }
    public  void checkButton (View view){
        int radioID = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioID);
        Toast.makeText(this, "Selected radio button :" + radioButton.getText() , Toast.LENGTH_SHORT).show();
    }

    private void sendVerificationMail()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null)
        {
            user.sendEmailVerification().addOnCompleteListener(register.this, new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(register.this, "Register successful. Verification E-mail sent!", Toast.LENGTH_SHORT).show();
                        FirebaseAuth.getInstance().signOut();
                        finish();
                    }
                }
            });
        }
    }
}
