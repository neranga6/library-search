package com.example.nylibrarysearch;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.method.ScrollingMovementMethod;
import android.view.InflateException;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.content_main);


        ScrollView searchView;
        ListView listView;
        View customView;
        ArrayList<String> list;
        ArrayAdapter<String> adapter;

        searchView = findViewById(R.id.scollview_bar);
        customView = findViewById(R.id.divider2);
        ScrollView finalSearchView = searchView;
        Map data = new HashMap<String, String>();
        data = (Map) getIntent().getSerializableExtra("hash");

                String TLE = data.get("data").toString();
                LinearLayout ll = findViewById(R.id.linlay);
                TextView tv = new TextView(ll.getContext());
                tv.setText(TLE);
                tv.setTextColor(Color.BLACK);
                tv.setMovementMethod(new ScrollingMovementMethod());
                tv.setTypeface(tv.getTypeface(), Typeface.BOLD);
                tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                ll.addView(tv);


        customView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        finalSearchView.requestDisallowInterceptTouchEvent(true);
                        return false;
                    case MotionEvent.ACTION_UP:
                        finalSearchView.requestDisallowInterceptTouchEvent(false);
                        return true;
                    default:
                        return true;
                }
            }
        });

    }
}