package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnAdd;

    private Button btnedt;

    ArrayList<Task> list=new ArrayList<>();

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        Gson gson = new Gson();
        String json = prefs.getString("Tasks", null);
        Type type = new TypeToken<List<Task>>() {}.getType();
        list = gson.fromJson(json, type);
        if (list == null) {
            list = new ArrayList<>();
        }

        ArrayAdapter<Task> listAdapter = new ArrayAdapter<Task>(this,
                android.R.layout.simple_list_item_1,
               list);

        ListView listView = (ListView)findViewById(R.id.txtTasks);
        listView.setAdapter(listAdapter);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(MainActivity.this, EditActivity.class);
//                startActivity(intent);
//            }
//        });

        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActiviry.class);
                startActivity(intent);
            }
        });

        btnedt=findViewById(R.id.btnedt);
        btnedt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        refreshList();
    }

    private void refreshList() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        Gson gson = new Gson();
        String json = prefs.getString("Tasks", null);
        Type type = new TypeToken<List<Task>>() {}.getType();
        list = gson.fromJson(json, type);
        if (list == null) {
            list = new ArrayList<>();
        }
        ArrayAdapter<Task> listAdapter = new ArrayAdapter<Task>(this,
                android.R.layout.simple_list_item_1,
                list);
        ListView listView = (ListView) findViewById(R.id.txtTasks);
        listView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
    }

}