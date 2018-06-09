package com.example.rajit.shopforme;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class HomerDisplayActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Button buttonOrder;
    LinearLayout layout;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homer_display);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ref = FirebaseDatabase.getInstance().getReference().child("order");
        layout = (LinearLayout) findViewById(R.id.orderDetails);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String n = dataSnapshot.getKey();
                HashMap<String, ArrayList<String>> p = new HashMap<>();
                p = (HashMap<String, ArrayList<String>>) dataSnapshot.getValue();
                Set<String> name = p.keySet();
                int i=0;
                for(Iterator<String> it = name.iterator(); it.hasNext(); i++){
                    final String mm = it.next();
                    buttonOrder = new Button(HomerDisplayActivity.this);
                    buttonOrder.setId(i+1);
                    buttonOrder.setText(mm);
                    buttonOrder.setTag(i);
                    buttonOrder.setBackgroundResource(R.drawable.marker);
                    layout.addView(buttonOrder);
                    //final TextView ll = (TextView) findViewById(R.id.textView);
                    final ArrayList<String> det = p.get(mm);
                    buttonOrder.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d("dd", det.toString());
                            Intent goToMainActivity = new Intent(HomerDisplayActivity.this, OrderDetails.class);
                            goToMainActivity.putExtra("Key", mm);
                            goToMainActivity.putExtra("Key2", det);
                            startActivity(goToMainActivity);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.getCode());
            }
        });
    }

    private View.OnClickListener mOrderListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.v("dimuthu", "success");
        }
    };


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
