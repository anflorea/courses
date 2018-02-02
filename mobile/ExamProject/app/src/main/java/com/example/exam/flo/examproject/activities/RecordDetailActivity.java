package com.example.exam.flo.examproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exam.flo.examproject.R;
import com.example.exam.flo.examproject.domain.Record;
import com.example.exam.flo.examproject.services.NetworkService;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecordDetailActivity extends AppCompatActivity {
    public static final String TAG = RecordDetailActivity.class.getSimpleName();

    private NetworkService.NetworkServiceInterface networkService = NetworkService.getInstance();


    private TextView mId;
    private TextView mName;
    private TextView mPatientId;
    private TextView mType;
    private TextView mStatus;
    private TextView mDate;

    private int mSelectedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_detail);

        mId = (TextView) findViewById(R.id.id_tv);
        mPatientId = (TextView) findViewById(R.id.patient_id_tv);
        mName = (TextView) findViewById(R.id.name_tv);
        mType = (TextView) findViewById(R.id.type_tv);
        mStatus = (TextView) findViewById(R.id.status_tv);
        mDate = (TextView) findViewById(R.id.date_tv);

        mSelectedId = getIntent().getIntExtra("id", -1);
        mName.setText(getIntent().getStringExtra("name"));

    }

    @Override
    protected void onResume() {
        super.onResume();

        makeQuery();
    }

    private void makeQuery() {
        Call<Record> call = networkService.getRecord(mSelectedId);
        call.enqueue(new Callback<Record>() {
            @Override
            public void onResponse(Call<Record> call, Response<Record> response) {
                Record record = response.body();

                if (record == null) {
                    Toast.makeText(RecordDetailActivity.this, "Error! ", Toast.LENGTH_LONG).show();
                } else {
                    mId.setText(String.valueOf(record.getId()));
                    mPatientId.setText(String.valueOf(record.getPatientId()));
                    mDate.setText(String.valueOf(record.getDate()));
                    mType.setText(record.getType());
                    mStatus.setText(record.getStatus());
                }
            }

            @Override
            public void onFailure(Call<Record> call, Throwable t) {
                Log.d(TAG, "Error! ");
                t.printStackTrace();
                Toast.makeText(RecordDetailActivity.this, "Error! " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
