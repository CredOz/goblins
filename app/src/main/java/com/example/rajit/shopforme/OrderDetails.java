package com.example.rajit.shopforme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class OrderDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        //String[] m = getIntent().getStringArrayExtra("Key2");
        ArrayList<String> myList = (ArrayList<String>) getIntent().getSerializableExtra("Key2");
        //String m = getIntent().getStringExtra("Key");
        Log.d("lll", myList.toString());

        ListView listView = (ListView) findViewById(R.id.orderList);
        ArrayAdapter array = new ArrayAdapter(this, android.R.layout.simple_list_item_1, myList);
        listView.setAdapter(array);
    }
}
