package com.example.exam.flo.examproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.exam.flo.examproject.activities.AdminActivity;
import com.example.exam.flo.examproject.activities.ListActivity;
import com.example.exam.flo.examproject.activities.PatientActivity;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onPatientClicked(View view) {
        Intent intent = new Intent(this, PatientActivity.class);
        startActivity(intent);
    }

    public void onEmployeeClicked(View view) {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }

    public void onAdminClicked(View view) {
        Intent intent = new Intent(this, AdminActivity.class);
        startActivity(intent);
    }
}
