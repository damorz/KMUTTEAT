package com.kmutteats.srinuan.firebasekmutteat;
//HBD JA
//Hi proy err
//Ku Ro nan reaw
//dai suk tee weiiii
//Dai ging mai
//Dai si weiiii
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class addmenu extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ImageView imageView, btnChoose;
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;
    private Button addnow;
    private String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmenu);
        btnChoose = (ImageView) findViewById(R.id.imageView3);
        imageView = (ImageView) findViewById(R.id.imgView);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        Intent getnameres = getIntent();
        final String nameres = getnameres.getStringExtra( "nr2" );
        addnow = (Button)findViewById(R.id.cfadd);

        final EditText name ,price,dct;
        name = (EditText)findViewById(R.id.editText);
        price = (EditText)findViewById(R.id.editText2);
        dct = (EditText)findViewById(R.id.editText3);

        addnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();

            }

        });
    }
    FirebaseStorage storage;
    StorageReference storageReference;

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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

    private void uploadImage() {

        if (filePath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            Intent getnameres = getIntent();
            final String nameres = getnameres.getStringExtra( "nr2" );
            final EditText name ,price,dct;
            name = (EditText)findViewById(R.id.editText);
            price = (EditText)findViewById(R.id.editText2);
            dct = (EditText)findViewById(R.id.editText3);
            final String foodname = name.getText().toString();
            final String prize = price.getText().toString();
            final String description = dct.getText().toString();

            StorageReference ref = storageReference.child("images/" + nameres + "/" + "picmenu/" + foodname);
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(addmenu.this, "Uploaded", Toast.LENGTH_SHORT).show();
                            Task<Uri> result = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                            result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    link = uri.toString();

                                    Map<String,Object> menu = new HashMap<>();
                                    menu.put("Price",prize);
                                    menu.put("Description",description);
                                    menu.put("URL",link);

                                    db.collection("Menu").document(nameres).collection(foodname).document("Menu(link)")
                                            .set(menu);

                                    finish();

                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(addmenu.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}



