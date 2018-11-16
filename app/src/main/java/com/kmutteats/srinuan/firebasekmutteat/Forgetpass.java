package com.kmutteats.srinuan.firebasekmutteat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgetpass extends AppCompatActivity {

    private EditText inputEmail;

    private CardView btnsend;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpass);
        inputEmail = findViewById(R.id.forgetsend);
        btnsend = findViewById(R.id.sendbtn);

        auth = FirebaseAuth.getInstance();

        btnsend.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String email = inputEmail.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplication(), "Enter your registered E-mail !", Toast.LENGTH_SHORT).show();
                    return;
                }


                final ProgressDialog progressDialog = new ProgressDialog(Forgetpass.this);
                progressDialog.setTitle("Sending E-mail");
                progressDialog.setMessage("Loading . . .");
                progressDialog.show();
                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Forgetpass.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(Forgetpass.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                                }
                                progressDialog.dismiss();
                            }
                        });
            }
        });
    }
}
