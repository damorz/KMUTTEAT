package com.kmutteats.srinuan.firebasekmutteat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.kmutteats.srinuan.firebasekmutteat.register.EMAIL_KEY;
import static com.kmutteats.srinuan.firebasekmutteat.register.STATUS_KEY;

public class CreateRes extends AppCompatActivity
{

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseFirestore db2 = FirebaseFirestore.getInstance();
    private ImageView imageView, btnChoose;
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;
    private Spinner locationSpinner;
    private ArrayList<String> location = new ArrayList<String>();
    private String link;
    private EditText nameres, tel, des;
    Button confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_res);
        Intent _recievemail = getIntent();
        final String email = _recievemail.getStringExtra("mail");
        Intent _recievestatus = getIntent();
        final String statuscheck = _recievestatus.getStringExtra("statuscheck");


        btnChoose = (ImageView) findViewById(R.id.imageView3);
        imageView = (ImageView) findViewById(R.id.imgView);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        confirm = (Button) this.findViewById(R.id.confirm);

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });
        locationSpinner = (Spinner) findViewById(R.id.location);
        selectlocation();

        ArrayAdapter<String> adapterlocation = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, location);
        locationSpinner.setAdapter(adapterlocation);
        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String lct = "" + location.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Spinner locationSpinner = (Spinner) CreateRes.this.findViewById(R.id.location);
                Object selectedItem = locationSpinner.getSelectedItem();
                String selectedText = selectedItem.toString();
                uploadImage();

            }
        });
    }

        FirebaseStorage storage;
        StorageReference storageReference;

        private void chooseImage()
        {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data)
        {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                    && data != null && data.getData() != null) {
                filePath = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                    imageView.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        private void uploadImage()
        {
            if (filePath != null) {
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("Uploading...");
                progressDialog.show();
                final String nr;
                final EditText nameres;
                nameres = (EditText) findViewById(R.id.resname);
                nr = nameres.getText().toString();
                tel = (EditText) findViewById(R.id.phonenum);
                des = (EditText) findViewById(R.id.editText11);
                final String pnb = tel.getText().toString();
                final String dct = des.getText().toString();
                Spinner locationSpinner = (Spinner) CreateRes.this.findViewById(R.id.location);
                Object selectedItem = locationSpinner.getSelectedItem();
                final String selectedText = selectedItem.toString();
                StorageReference ref = storageReference.child("images/" + nr + "/" + "picres");
                ref.putFile(filePath)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                progressDialog.dismiss();
                                Toast.makeText(CreateRes.this, "Uploaded", Toast.LENGTH_SHORT).show();
                                Task<Uri> result = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                                result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        link = uri.toString();

                                        Map<String, Object> info = new HashMap<>();
                                        info.put("Restaurant name", nr);
                                        info.put("Phone number", pnb);
                                        info.put("Description", dct);
                                        info.put("Location", selectedText);
                                        info.put("URL", link);
                                        //info.put("Status RES","closed");
                                        db.collection("Restaurant").document(nr)
                                                .set(info);

                                        Intent _recievemail = getIntent();
                                        final String email = _recievemail.getStringExtra("mail");
                                        Map<String, Object> info2 = new HashMap<>();
                                        info2.put("Restaurant name",nr);
                                        info2.put("Status","Merchant");
                                        db2.collection("account").document("MERCHANT").collection(email).document("data account")
                                                .set(info2,SetOptions.merge());


                                        Intent intent = new Intent(CreateRes.this, menulist.class);
                                        intent.putExtra("nr", nameres.getText().toString());
                                        startActivity(intent);

                                    }
                                });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.dismiss();
                                Toast.makeText(CreateRes.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }

        private void selectlocation()
        {

            location.add("KFC");
            location.add("Dormitory For Women");
            location.add("Dormitory For Men");
            location.add("CB1");
        }
}



