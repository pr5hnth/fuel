package com.example.fuelapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import me.itangqi.waveloadingview.WaveLoadingView;

public class MainActivity extends AppCompatActivity {

    WaveLoadingView wv;
    String fuel;
    TextView txt;
    Button fl,mil,fp,fs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(MainActivity.this);



        wv= findViewById(R.id.waveLoadingView);
        wv.setProgressValue(0);

        fs=findViewById(R.id.fs);
        fp=findViewById(R.id.fp);
        mil=findViewById(R.id.mil);
        fl=findViewById(R.id.fl);



        FirebaseDatabase firebase = FirebaseDatabase.getInstance("https://fuelapp-156e1-default-rtdb.asia-southeast1.firebasedatabase.app/");
        final DatabaseReference databaseReference = firebase.getReference();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                fuel=dataSnapshot.child("admin").child("fuel").getValue().toString();
                wv.setProgressValue(Integer.parseInt(fuel));
                wv.setCenterTitle(fuel);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        fs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, fuels_station.class));
            }
        });

        fp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, fuel_price.class));
            }
        });


        mil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, mila.class));
            }
        });


        fl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, log.class));
            }
        });




    }
}