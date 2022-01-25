package com.example.nylibrarysearch;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_main);


//        Button rollButton = (Button) findViewById(R.id.rollButton);
//        TextView resultsTextView = (TextView) findViewById(R.id.resultsTextView);
//        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
//
//        rollButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Random rand = new Random();
//                int rad = rand.nextInt(seekBar.getProgress());
//                String str = String.valueOf(rad);
//                resultsTextView.setText(str);
//
//            }
//        });


        NYCPLibrary nycplibrary = new NYCPLibrary();
        SearchView searchView;
        searchView = findViewById(R.id.search_bar);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                String librarySearch = query.replaceAll("\\s", "");
                if (!librarySearch.isEmpty()) {
                    try {

                        Map data = nycplibrary.search(librarySearch);

                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        intent.putExtra("hash", (Serializable) data);
                        startActivity(intent);

                    } catch (Exception e) {
                        System.out.println("++++++++++++++++++++ex" + e);

                    }

                } else {
                    Toast.makeText(MainActivity.this, "No Match found", Toast.LENGTH_LONG).show();
                }
                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //adapter.getFilter().filter(newText);
                return false;
            }
        });


    }

}