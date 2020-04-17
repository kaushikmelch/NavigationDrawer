package com.example.driverallot;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.lang.Math;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Allotsite extends AppCompatActivity {
    private static final String TAG = "allotscreen";

    private FirebaseDatabase mFirebasedatabase;
    private DatabaseReference myref;
    private FirebaseAuth mauth;
    private String UserID;
    private ListView mListview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allotsite_layout);
        mFirebasedatabase = FirebaseDatabase.getInstance();
        myref = mFirebasedatabase.getReference().child("UserId");


        myref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showdata(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void showdata(DataSnapshot dataSnapshot){
        String Lat = "0";
        String Lon = "0";
        String TS = "100";
        String L1;
        String L2;
        String L3;
        String F1="100";
        String F2="your";
        String F3="100";

        for(DataSnapshot ds: dataSnapshot.getChildren()){
            info uinfo = ds.getValue(info.class);
            //info xinfo = new info();

            L1=uinfo.getLatitude();
            L2=uinfo.getLongitude();
            L3=uinfo.getTS();
            //info uinfo = new info();

            // uinfo.setLatitude(dataSnapshot.child(UserID).getValue(info.class).getLatitude());

            //uinfo.setLongitude(dataSnapshot.child(UserID).getValue(info.class).getLongitude());

            //uinfo.setTS(dataSnapshot.child(UserID).getValue(info.class).getTS());

            Log.d(TAG, "showData:latitude"+L1);

            Log.d(TAG, "showData:longitude"+L2);

            Log.d(TAG, "showData:TS"+L3);
            if(Math.pow((Math.pow(Integer.parseInt(L1),2) + Math.pow(Integer.parseInt(L2),2)),0.5) < 60){
                if(Integer.parseInt(L3)<Integer.parseInt(F3)){
                    F1=L1;
                    F2=L2;
                    F3=L3;
                    Log.d(TAG, F1 + "finalinitial " + F2 + " " + F3);
                }
            }
        }
        Log.d(TAG, F1 + "final " + F2 + " " + F3);
        //ArrayList<String> array = new ArrayList<>();
        //array.add(F1);
        //array.add(F2);
        //array.add(F3);
        //ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);
        //mListview.setAdapter(adapter);
    }

}