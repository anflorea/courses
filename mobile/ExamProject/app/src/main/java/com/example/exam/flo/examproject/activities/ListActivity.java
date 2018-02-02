package com.example.exam.flo.examproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.exam.flo.examproject.R;
import com.example.exam.flo.examproject.adapters.CustomAdapter;

public class ListActivity extends AppCompatActivity {
    public static final String TAG = ListActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private CustomAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new CustomAdapter(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setAdapter(mAdapter);


    }
}
