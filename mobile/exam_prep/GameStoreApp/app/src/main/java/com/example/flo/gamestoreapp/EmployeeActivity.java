package com.example.flo.gamestoreapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flo.gamestoreapp.adapters.ItemListAdapter;
import com.example.flo.gamestoreapp.components.GsonComponent;
import com.example.flo.gamestoreapp.domain.Item;
import com.example.flo.gamestoreapp.services.NetworkService;
import com.example.flo.gamestoreapp.utils.NetworkUtils;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeActivity extends AppCompatActivity {
    public static final String TAG = EmployeeActivity.class.getSimpleName();

    private NetworkService.NetworkServiceInterface networkService = NetworkService.getInstance();

    private RecyclerView mListView;
    private ItemListAdapter mAdapter;
    private LinearLayout mLayoutError;
    private List<Item> mItems = new ArrayList<>();

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        mListView = (RecyclerView) findViewById(R.id.lv_items);
        mProgressBar = (ProgressBar) findViewById(R.id.pb_employee);
        mLayoutError = (LinearLayout) findViewById(R.id.ll_error);

        mLayoutError.setVisibility(View.INVISIBLE);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mListView.setLayoutManager(layoutManager);

        mListView.setHasFixedSize(true);

        mAdapter = new ItemListAdapter(this);
        mListView.setAdapter(mAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int id = (int) viewHolder.itemView.getTag();
                deleteItem(id);
            }
        }).attachToRecyclerView(mListView);

    }

    void deleteItem(int id) {
        Item item = new Item();
        item.setId(id);
        Call<Item> call = networkService.removeItem(item);
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                makeQuery();
                Log.d(TAG, "Removal successful!");
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                Log.d(TAG, "Error!");
                t.printStackTrace();
                Toast.makeText(EmployeeActivity.this, "Error! " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        makeQuery();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void tryAgain(View view) {
        mProgressBar.setVisibility(View.VISIBLE);
        mLayoutError.setVisibility(View.INVISIBLE);
        makeQuery();
    }

    public void addItem(View view) {
        startActivity(new Intent(this, AddItemActivity.class));
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
                Toast.makeText(EmployeeActivity.this, "Error! " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
