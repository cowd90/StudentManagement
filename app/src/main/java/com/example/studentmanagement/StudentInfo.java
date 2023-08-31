package com.example.studentmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class StudentInfo extends AppCompatActivity {
    private TextView stText;
    private static final String KEY_INFO = "INFO_KEY";
    private static final String SHARED_PREFERENCES = "shared preferences";
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_student_info);

        stText = (TextView) findViewById(R.id.studentInfo);

        ArrayList<Student> list = (ArrayList<Student>) getIntent().getSerializableExtra(MainActivity.STUDENTS_KEY);

        String output = "";

        for (Student o : list) {
            output += o.toString();
            output += "\n";
        }

        stText.setText(output);
    }


    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        String stateSaved = savedInstanceState.getString(KEY_INFO);

        if (stateSaved != null) {
            stText.setText(stateSaved);
        } else {
            Toast.makeText(this, "New Entry", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        String stateToSave = stText.getText().toString();
        savedInstanceState.putString(KEY_INFO, stateToSave);

    }
}