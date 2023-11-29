package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AddActiviry extends AppCompatActivity {
    private Button btnAddTask;
    private EditText edtName;
    private EditText edtAuthor;
    private Spinner spnStetus;

    ArrayList<Task> list=new ArrayList<>();

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_activiry);

        btnAddTask = findViewById(R.id.btnSaveTask);
        edtName = findViewById(R.id.edtName);
        edtAuthor = findViewById(R.id.edtAuthor);
        spnStetus = findViewById(R.id.spnStetus);

        setupSharedPrefs();
        loadTasks();

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtName.getText().toString().isEmpty() && !edtAuthor.getText().toString().isEmpty()) {
                    Task task = new Task(edtName.getText().toString(), edtAuthor.getText().toString(), spnStetus.getSelectedItem().toString());
                    list.add(task);
                    saveTasks();
                    edtName.getText().clear();
                    edtAuthor.getText().clear();
                }
            }
        });
    }

    private void setupSharedPrefs() {
        prefs= PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }

    private void loadTasks() {
        Gson gson = new Gson();
        String json = prefs.getString("Tasks", null);
        Type type = new TypeToken<List<Task>>() {}.getType();
        list = gson.fromJson(json, type);
        if (list == null) {
            list = new ArrayList<>();
        }
    }

    private void saveTasks() {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString("Tasks", json);
        editor.apply();
    }
}