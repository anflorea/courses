package com.example.exam.flo.examproject.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
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

public class ListActivity extends AppCompatActivity implements CustomAdapter.CustomAdapterOnClickListener {
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

        mAdapter = new CustomAdapter(this, this, true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setAdapter(mAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int id = (int) viewHolder.itemView.getTag();

                deletePatient(id);
            }
        }).attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        makeQuery();
    }

    public void tryAgain(View view) {
        makeQuery();
    }

    private void deletePatient(final int id) {
        Call<Patient> call = networkService.deletePatient(id);
        call.enqueue(new Callback<Patient>() {
            @Override
            public void onResponse(Call<Patient> call, Response<Patient> response) {
                makeQuery();
                Log.d(TAG, "Removal of patient with id: " + String.valueOf(id) + " successful!");
            }

            @Override
            public void onFailure(Call<Patient> call, Throwable t) {
                Log.d(TAG, "Error!");
                t.printStackTrace();
                Toast.makeText(ListActivity.this, "Error! " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
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
                    Toast.makeText(ListActivity.this, "No patients found!", Toast.LENGTH_LONG).show();
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

    @Override
    public void onClick(Patient patient) {
        Log.d(TAG, "Patient clicked: " + patient.getName());
        Intent intent = new Intent(this, AddRecordActivity.class);

        intent.putExtra("id", patient.getId());

        startActivity(intent);
    }
}
