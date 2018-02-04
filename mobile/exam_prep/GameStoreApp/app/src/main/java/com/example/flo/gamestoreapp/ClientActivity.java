package com.example.flo.gamestoreapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.flo.gamestoreapp.adapters.ItemListAdapter;
import com.example.flo.gamestoreapp.components.GsonComponent;
import com.example.flo.gamestoreapp.domain.Item;
import com.example.flo.gamestoreapp.services.NetworkService;
import com.example.flo.gamestoreapp.utils.NetworkUtils;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientActivity extends AppCompatActivity {
    public static final String TAG = ClientActivity.class.getSimpleName();

    private NetworkService.NetworkServiceInterface networkService = NetworkService.getInstance();

    private RecyclerView mListView;
    private ItemListAdapter mAdapter;
    private LinearLayout mLayoutError;
    private List<Item> mItems = new ArrayList<>();

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        mListView = (RecyclerView) findViewById(R.id.lv_client_items);
        mProgressBar = (ProgressBar) findViewById(R.id.pb_client);
        mLayoutError = (LinearLayout) findViewById(R.id.ll_error);

        mLayoutError.setVisibility(View.INVISIBLE);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mListView.setLayoutManager(layoutManager);

        mListView.setHasFixedSize(true);

        mAdapter = new ItemListAdapter(this, false);
        mListView.setAdapter(mAdapter);

        makeQuery();
    }

    void makeQuery() {
        Call<List<Item>> call = networkService.getAllGames();
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(
                    final Call<List<Item>> call,
                    final Response<List<Item>> response) {
                mProgressBar.setVisibility(View.INVISIBLE);
                mLayoutError.setVisibility(View.INVISIBLE);

                final List<Item> items = response.body();
                if (items != null && !items.isEmpty()) {
                    mItems = items;
                    mAdapter.setData(mItems);
                    Log.d(TAG, "******************onResponse: Items found as list with size: " + items.size());
                } else {
                    Log.d(TAG, "******************onResponse: No items found");
                }
            }

            @Override
            public void onFailure(
                    final Call<List<Item>> call,
                    final Throwable t) {
                mProgressBar.setVisibility(View.INVISIBLE);
                mLayoutError.setVisibility(View.VISIBLE);

                Log.d(TAG, "Error! ");
                t.printStackTrace();
                Toast.makeText(ClientActivity.this, "Error! " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
