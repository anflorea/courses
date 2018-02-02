package com.example.exam.flo.examproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.exam.flo.examproject.R;
import com.example.exam.flo.examproject.domain.Patient;
import com.example.exam.flo.examproject.services.NetworkService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminActivity extends AppCompatActivity {
    public static final String TAG = AdminActivity.class.getSimpleName();

    private NetworkService.NetworkServiceInterface networkService = NetworkService.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public void onDeleteClicked(View view) {
        Call<Patient> call = networkService.nukeAll();
        call.enqueue(new Callback<Patient>() {
            @Override
            public void onResponse(Call<Patient> call, Response<Patient> response) {
                Log.d(TAG, "Nuke successful! We all are doomed :(");
            }

            @Override
            public void onFailure(Call<Patient> call, Throwable t) {
                Log.d(TAG, "Error!");
                t.printStackTrace();
                Toast.makeText(AdminActivity.this, "Error! " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
