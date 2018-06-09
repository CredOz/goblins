package com.example.rajit.shopforme;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class available_orders extends AppCompatActivity {
    Button buttonOrder;
    LinearLayout layout;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_orders);
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
                    buttonOrder = new Button(available_orders.this);
                    buttonOrder.setId(i+1);
                    buttonOrder.setText(mm);
                    buttonOrder.setTag(i);
                    layout.addView(buttonOrder);
                    final TextView ll = (TextView) findViewById(R.id.textView);
                    final ArrayList<String> det = p.get(mm);
                    buttonOrder.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d("dd", det.toString());
                            Intent goToMainActivity = new Intent(available_orders.this, OrderDetails.class);
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
}
