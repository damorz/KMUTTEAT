package com.kmutteats.srinuan.firebasekmutteat;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import static java.lang.Thread.sleep;

public class MyRecyclerviewAdapterCart extends RecyclerView.Adapter<MyRecyclerviewHolderCart>{
    private static final String TAG = "RecycleviewLOG";
    FirebaseFirestore db ;
    CartFragment recyclerview;
    HomeCFragment intenttoHomeC;
    ArrayList<Cart> userArrayList;

    public MyRecyclerviewAdapterCart(CartFragment recyclerview, ArrayList<Cart> userArrayList)
    {
        this.recyclerview = recyclerview;
        this.userArrayList = userArrayList;
        //this.URL = url;
        //this.iconadapter = icon;
    }

    @NonNull
    @Override
    public MyRecyclerviewHolderCart onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(recyclerview.getActivity().getApplicationContext());
        View view =layoutInflater.inflate(R.layout.single_row_cart,parent,false );
        return new MyRecyclerviewHolderCart(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyRecyclerviewHolderCart holder, final int position)
    {
        db = FirebaseFirestore.getInstance();
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        holder.mNamemenuC.setText(userArrayList.get(position).getNamemenu());
        holder.mNameresC.setText(userArrayList.get(position).getNameresmenu());
        holder.mPriceC.setText(userArrayList.get(position).getPrice());
        //Toast.makeText( recyclerview.getActivity().getApplicationContext(), "add : " +userArrayList.get(position).getCountfood() , Toast.LENGTH_SHORT ).show();


        /*String close = "closed";
        if(userArrayList.get(position).getOnOff()==close)
        {
            holder.mOnOff.setTextColor(Color.parseColor("#f5220d"));
            holder.mOnOff.setText(userArrayList.get(position).getOnOff());

        }
        else
        {
            holder.mOnOff.setTextColor(Color.parseColor("#4baf11"));
            holder.mOnOff.setText(userArrayList.get(position).getOnOff());
        }*/

        //holder.mUrl.setText(userArrayList.get(position).getUrl());
        //Picasso.get().load(userArrayList.get(position).getUrl()).resize(250,250).centerCrop().into(holder.mPicres);
        Glide.with(recyclerview.getActivity().getApplicationContext()).load(userArrayList.get(position).getUrl()).apply(requestOptions.centerCrop().override(200,200)).into(holder.mPicMenuC);



        final int[] countfoodInt = {Integer.valueOf(userArrayList.get(position).getCountfood())};
        final int[] priceInt = {Integer.valueOf(userArrayList.get(position).getPrice())};
        final int[] i = new int[3];
        final double[] totalprice = {0};
        double eachmenu = 0;
        holder.countfood.setText(""+ countfoodInt[0]);
        for(i[0] =0; i[0] <getItemCount(); i[0]++)
        {
            eachmenu = Integer.valueOf(userArrayList.get(i[0]).getCountfood()) * Integer.valueOf(userArrayList.get(i[0]).getPrice());
            totalprice[0] = totalprice[0] + eachmenu;
        }
        recyclerview.total.setText("Total : " + totalprice[0] +"Baht");

        holder.increase.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                countfoodInt[0] = countfoodInt[0] + 1;
                holder.countfood.setText(""+ countfoodInt[0]);
                totalprice[0] = totalprice[0] + priceInt[0];
                recyclerview.total.setText("Total : " + totalprice[0] +"Baht");
                //Toast.makeText( recyclerview.getActivity().getApplicationContext(), "add : "  , Toast.LENGTH_SHORT ).show();
            }
        });
        holder.decrease.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (countfoodInt[0]==1)
                {
                    deleteSelectRow(position);
                    holder.countfood.setText(""+ countfoodInt[0]);
                    totalprice[0] = totalprice[0] - priceInt[0];
                    recyclerview.total.setText("Total : " + totalprice[0] +"Baht");
                }
                else
                {
                    countfoodInt[0] = countfoodInt[0] - 1;
                    holder.countfood.setText(""+ countfoodInt[0]);
                    totalprice[0] = totalprice[0] - priceInt[0];
                    recyclerview.total.setText("Total : " + totalprice[0] +"Baht");
                }

                //Toast.makeText( recyclerview.getActivity().getApplicationContext(), "Minus : "  , Toast.LENGTH_SHORT ).show();

            }
        });
        recyclerview.buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View v) {
                SharedPreferences prefs2 = PreferenceManager.getDefaultSharedPreferences(recyclerview.getActivity().getApplicationContext());
                String email = prefs2.getString("Emailtest2", "no id");
                for (i[0] = 0; i[0] < getItemCount(); i[0]++) {
                    Map<String, Object> infoc = new HashMap<>();
                    infoc.put("Restaurant name", userArrayList.get(i[0]).getNameresmenu());
                    infoc.put("Price", userArrayList.get(i[0]).getPrice());
                    infoc.put("Food", userArrayList.get(i[0]).getNamemenu());
                    infoc.put("URL",userArrayList.get(i[0]).getUrl());
                    db.collection("History").document("Customer").collection(email).document(userArrayList.get(i[0]).getNamemenu())
                            .set(infoc);

                    Map<String, Object> infom = new HashMap<>();
                    infom.put("Restaurant name", email);
                    infom.put("Price", userArrayList.get(i[0]).getPrice());
                    infom.put("Food", userArrayList.get(i[0]).getNamemenu());
                    infoc.put("URL",userArrayList.get(i[0]).getUrl());
                    infom.put("Count food", "" + countfoodInt[0]);
                    infom.put("Customer e-mail", email);
                    db.collection("Order list").document("Link").collection(userArrayList.get(i[0]).getNameresmenu()).document(userArrayList.get(i[0]).getNamemenu())
                            .set(infom);
                }
                db.collection("account").document("CUSTOMER").collection(email).document("data account")
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>()
                        {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot)
                            {
                                if (documentSnapshot.exists())
                                {
                                    final String coin = documentSnapshot.getString("Coin");
                                    double coinInt = Double.valueOf(coin);
                                    if(coinInt<totalprice[0])
                                    {
                                        Toast.makeText(recyclerview.getActivity().getApplicationContext(), "Not enough coin.", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        SharedPreferences prefs2 = PreferenceManager.getDefaultSharedPreferences(recyclerview.getActivity().getApplicationContext());
                                        String email = prefs2.getString("Emailtest2", "no id");
                                        coinInt = coinInt - totalprice[0];
                                        Toast.makeText(recyclerview.getActivity().getApplicationContext(), "Buy successful, remain coin : "+coinInt, Toast.LENGTH_SHORT).show();
                                        Map<String, Object> sentcoin = new HashMap<>();
                                        sentcoin.put("Coin", ""+coinInt);
                                        db.collection("account").document("CUSTOMER").collection(email).document("data account")
                                                .set(sentcoin,SetOptions.merge());
                                        for(i[0] =0; i[0] <getItemCount(); i[0]++)
                                        {

                                                deleteSelectRow(i[0]);
                                            try {
                                                sleep(1000);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }


                                        }

                                        FragmentTransaction ft = (recyclerview.getActivity()).getSupportFragmentManager().beginTransaction();
                                        ft.replace(R.id.Flmain, new HomeCFragment());
                                        ft.commit();
                                    }

                                }
                            }
                        });


            }
        });





    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    private void deleteSelectRow(int position) {
        SharedPreferences prefs2 = PreferenceManager.getDefaultSharedPreferences(recyclerview.getActivity().getApplicationContext());
        String email = prefs2.getString("Emailtest2", "no id");
        recyclerview.db.collection("Cart")
                .document("link")
                .collection(email)
                .document(userArrayList.get(position).getUserId())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //Toast.makeText(recyclerview.getActivity().getApplicationContext(),"Delete Successfully",Toast.LENGTH_SHORT).show();
                        recyclerview.loadDataFromFirebase();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(recyclerview.getActivity().getApplicationContext(),"Unable to delete --- 3 ---",Toast.LENGTH_SHORT).show();
                        Log.w(TAG, "----- 3 -----",e.getCause());
                    }
                });
    }

}
