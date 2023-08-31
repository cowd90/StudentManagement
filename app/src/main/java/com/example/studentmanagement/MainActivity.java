package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentmanagement.R;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    final static protected String STUDENTS_KEY = "com.example.studentmanagement";
    final private static String TAG = "com.example.studentmanagement";
    private EditText stName;
    private TextView stBirthday;
    private RadioButton male, female;
    private RadioGroup genderRadioGroup;
    private Button createBtn, showBtn;
    private ArrayList<Student> listOfStudent = new ArrayList<>();
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stName = findViewById(R.id.nameInput);
        stBirthday = findViewById(R.id.birthInput);
        male = findViewById(R.id.maleBtn);
        female = findViewById(R.id.femaleBtn);
        genderRadioGroup = findViewById(R.id.groupRadioGender);
        createBtn = findViewById(R.id.createBtn);
        showBtn = findViewById(R.id.showBtn);

        stBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Birthday Input
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_DeviceDefault_Dialog,
                        mDateSetListener,
                        year, month, day);
                dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                mDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        stBirthday.setText(date);
                    }
                };
            }
        });
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Set up attributes
                String name = stName.getText().toString();
                String birthday = stBirthday.getText().toString();
                String genderObj = "";
                if(male.isChecked()) {
                    genderObj = "Male";
                } else if(female.isChecked()) {
                    genderObj = "Female";
                }

                if(name.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill the name!", Toast.LENGTH_SHORT).show();
                } else if(birthday.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill the birthday!", Toast.LENGTH_SHORT).show();
                } else {

                    // Initialize a Student object
                    Student st = new Student(name, birthday, genderObj);
                    Toast.makeText(MainActivity.this, "Create a student successfully!", Toast.LENGTH_SHORT).show();

                    // add student into a list
                    listOfStudent.add(st);

                    // Reset the input filling
                    stName.setText("");
                    stBirthday.setText("");
                    genderRadioGroup.clearCheck();
                }
            }
        });
        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Switch to the StudentInfo activity
                Intent i = new Intent(MainActivity.this, StudentInfo.class);
                i.putExtra(STUDENTS_KEY, listOfStudent);

                startActivity(i);

            }
        });


    }
}