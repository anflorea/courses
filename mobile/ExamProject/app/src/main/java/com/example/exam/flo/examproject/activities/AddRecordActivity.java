package com.example.exam.flo.examproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.exam.flo.examproject.R;
import com.example.exam.flo.examproject.domain.Record;
import com.example.exam.flo.examproject.services.NetworkService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddRecordActivity extends AppCompatActivity {
    public static final String TAG = AddRecordActivity.class.getSimpleName();

    private NetworkService.NetworkServiceInterface networkService = NetworkService.getInstance();

    private EditText mType;
    private EditText mDetails;
    private EditText mStatus;

    private int mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        mType = (EditText) findViewById(R.id.type_edit_text);
        mDetails = (EditText) findViewById(R.id.details_edit_text);
        mStatus = (EditText) findViewById(R.id.status_edit_text);

        mId = getIntent().getIntExtra("id", -1);
    }

    public void onAdd(View view) {
        String type = mType.getText().toString();
        String details = mDetails.getText().toString();
        String status = mStatus.getText().toString();

        if (details.isEmpty() || type.isEmpty() || status.isEmpty()) {
            Toast.makeText(this, "Please fill all the fields!", Toast.LENGTH_LONG).show();
            return;
        }

        Record toAddRecord = new Record(-1, mId, type, details, status, 0);

        Call<Record> call = networkService.addRecord(toAddRecord);
        call.enqueue(new Callback<Record>() {
            @Override
            public void onResponse(Call<Record> call, Response<Record> response) {

                Log.d(TAG, "Record added successfully!");

                finish();
            }

            @Override
            public void onFailure(Call<Record> call, Throwable t) {
                Log.d(TAG, "Error! ");
                t.printStackTrace();
                Toast.makeText(AddRecordActivity.this, "Error! " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
