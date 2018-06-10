package com.example.rajit.shopforme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        Intent intent = getIntent();
        //String[] m = getIntent().getStringArrayExtra("Key2");
        //ArrayList<String> myList = (ArrayList<String>) getIntent().getSerializableExtra("Key2");
        HashMap<String, Object> myList = (HashMap<String, Object>)intent.getSerializableExtra("Key2");

        //String m = getIntent().getStringExtra("Key");
        Log.d("lll", myList.get("latitude").toString());
        //System.out.println("myList.get(\"latitude\") = " + myList.get("latitude"));

        ListView listView = (ListView) findViewById(R.id.orderList);
        ArrayAdapter array = new ArrayAdapter(this, android.R.layout.simple_list_item_1, myList.keySet().toArray());
        listView.setAdapter(array);
    }
}
