package com.example.exam.flo.examproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.exam.flo.examproject.R;

public class AddRecordActivity extends AppCompatActivity {
    public static final String TAG = AddRecordActivity.class.getSimpleName();

    EditText mType;
    EditText mDetails;
    EditText mStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        mType = (EditText) findViewById(R.id.type_edit_text);
        mDetails = (EditText) findViewById(R.id.details_edit_text);
        mStatus = (EditText) findViewById(R.id.status_edit_text);
    }

    public void onAdd(View view) {

    }
}
