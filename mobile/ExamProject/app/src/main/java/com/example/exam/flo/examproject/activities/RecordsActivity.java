package com.example.exam.flo.examproject.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.exam.flo.examproject.R;
import com.example.exam.flo.examproject.adapters.CustomAdapter;
import com.example.exam.flo.examproject.adapters.RecordAdapter;
import com.example.exam.flo.examproject.domain.Patient;
import com.example.exam.flo.examproject.domain.Record;
import com.example.exam.flo.examproject.services.NetworkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecordsActivity extends AppCompatActivity implements RecordAdapter.RecordAdapterOnClickListener {
    public static final String TAG = RecordsActivity.class.getSimpleName();

    private NetworkService.NetworkServiceInterface networkService = NetworkService.getInstance();

    private int mCurrentId;

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    private RecordAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        mCurrentId = sharedPreferences.getInt("id", -1);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mProgressBar = (ProgressBar) findViewById(R.id.pb_records);

        mAdapter = new RecordAdapter(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        makeQuery();
    }

    @Override
    public void onClick(Record record) {
        Intent intent = new Intent(this, RecordDetailActivity.class);

        intent.putExtra("id", record.getId());

        startActivity(intent);
    }

    private void makeQuery() {
        mProgressBar.setVisibility(View.VISIBLE);
        Call<List<Record>> call = networkService.getRecordsForId(mCurrentId);
        call.enqueue(new Callback<List<Record>>() {
            @Override
            public void onResponse(Call<List<Record>> call, Response<List<Record>> response) {
                final List<Record> data = response.body();
                if (data != null && !data.isEmpty()) {
                    mProgressBar.setVisibility(View.INVISIBLE);

                    mAdapter.setData(data);
                    Log.d(TAG, "onResponse: Records found as list with size: " + data.size());
                    Log.d(TAG, data.toString());
                } else {
                    Log.d(TAG, "onResponse: No records found");
                    Toast.makeText(RecordsActivity.this, "No patients found!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Record>> call, Throwable t) {
                mProgressBar.setVisibility(View.INVISIBLE);
                Log.d(TAG, "Error! ");
                t.printStackTrace();
                Toast.makeText(RecordsActivity.this, "Error! " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
