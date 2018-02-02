package com.example.exam.flo.examproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.exam.flo.examproject.R;
import com.example.exam.flo.examproject.adapters.CustomAdapter;
import com.example.exam.flo.examproject.domain.Patient;
import com.example.exam.flo.examproject.services.NetworkService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {
    public static final String TAG = ListActivity.class.getSimpleName();

    private NetworkService.NetworkServiceInterface networkService = NetworkService.getInstance();

    private RecyclerView mRecyclerView;
    private CustomAdapter mAdapter;

    private LinearLayout mLayoutError;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mProgressBar = (ProgressBar) findViewById(R.id.pb_emplyee);
        mLayoutError = (LinearLayout) findViewById(R.id.ll_error);

        mLayoutError.setVisibility(View.INVISIBLE);

        mAdapter = new CustomAdapter(this);

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

    public void tryAgain(View view) {
        makeQuery();
    }

    private void makeQuery() {

        mProgressBar.setVisibility(View.VISIBLE);
        mLayoutError.setVisibility(View.INVISIBLE);

        Call<List<Patient>> call = networkService.getAll();
        call.enqueue(new Callback<List<Patient>>() {
            @Override
            public void onResponse(Call<List<Patient>> call, Response<List<Patient>> response) {
                mLayoutError.setVisibility(View.INVISIBLE);
                mProgressBar.setVisibility(View.INVISIBLE);

                final List<Patient> data = response.body();
                if (data != null && !data.isEmpty()) {
                    mAdapter.setData(data);
                    Log.d(TAG, "onResponse: Patients found as list with size: " + data.size());
                    Log.d(TAG, data.toString());
                } else {
                    Log.d(TAG, "onResponse: No items found");
                }
            }

            @Override
            public void onFailure(Call<List<Patient>> call, Throwable t) {
                mProgressBar.setVisibility(View.INVISIBLE);
                mLayoutError.setVisibility(View.VISIBLE);
                Log.d(TAG, "Error! ");
                t.printStackTrace();
                Toast.makeText(ListActivity.this, "Error! " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
