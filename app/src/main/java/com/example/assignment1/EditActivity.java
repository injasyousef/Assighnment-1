package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class EditActivity extends AppCompatActivity {

    ArrayList<Task> list=new ArrayList<>();

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    private EditText taskName;
    private EditText author;
    private Spinner stetus;

    private Button save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        taskName=findViewById(R.id.edtName);
        author=findViewById(R.id.edtAuthor);
        stetus=findViewById(R.id.spnStetus);
        save=findViewById(R.id.btnSaveTask);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        Gson gson = new Gson();
        String json = prefs.getString("Tasks", null);
        Type type = new TypeToken<List<Task>>() {}.getType();
        list = gson.fromJson(json, type);
        if (list == null) {
            list = new ArrayList<>();
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskName = EditActivity.this.taskName.getText().toString();
                String author = EditActivity.this.author.getText().toString();
                String status = stetus.getSelectedItem().toString();

                for(Task t:list){
                    if(t.getName().equals(taskName)){
                        t.setAuthor(author);
                        t.setStatus(status);
                        break;
                    }
                }

                Gson gson = new Gson();
                String json = gson.toJson(list);

                editor = prefs.edit();
                editor.putString("Tasks", json);
                editor.apply();

                finish();
            }
        });

    }

}