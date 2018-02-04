package com.example.flo.gamestoreapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.flo.gamestoreapp.components.GsonComponent;
import com.example.flo.gamestoreapp.domain.Item;
import com.example.flo.gamestoreapp.services.NetworkService;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditItemActivity extends AppCompatActivity {
    public static final String TAG = EditItemActivity.class.getSimpleName();

    private NetworkService.NetworkServiceInterface networkService = NetworkService.getInstance();

    EditText mNameEditText;
    EditText mQuantityEditText;
    EditText mTypeEditText;
    EditText mStatusEditText;

    private Item mItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        Gson gson = GsonComponent.getInstance().getGson();
        mItem = gson.fromJson(getIntent().getStringExtra(Item.class.getSimpleName()), Item.class);

        mNameEditText = (EditText) findViewById(R.id.name_edit);
        mQuantityEditText = (EditText) findViewById(R.id.quantity_edit);
        mTypeEditText = (EditText) findViewById(R.id.type_edit);
        mStatusEditText = (EditText) findViewById(R.id.status_edit);

        mNameEditText.setText(mItem.getName());
        mQuantityEditText.setTag(String.valueOf(mItem.getQuantity()));
        mTypeEditText.setText(mItem.getType());
        mStatusEditText.setText(mItem.getStatus());
    }

    public void editItem(View view) {
        String name = mNameEditText.getText().toString();
        String quantityString = mQuantityEditText.getText().toString();
        String type = mTypeEditText.getText().toString();
        String status = mStatusEditText.getText().toString();

        if (name.isEmpty() || quantityString.isEmpty() || type.isEmpty() || status.isEmpty()) {
            Toast.makeText(this, "Please fill all the fields!", Toast.LENGTH_LONG).show();
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(quantityString);
        } catch (Exception e) {
            Toast.makeText(this, "Please enter a valid quantity!", Toast.LENGTH_LONG).show();
            return;
        }

        mItem.setName(name);
        mItem.setQuantity(quantity);
        mItem.setType(type);
        mItem.setStatus(status);

        Call<Item> call = networkService.updateItem(mItem);
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(
                    final Call<Item> call,
                    final Response<Item> response) {

                Log.d(TAG, "Success!!!!! ");

                finish();
            }

            @Override
            public void onFailure(
                    final Call<Item> call,
                    final Throwable t) {


                Log.d(TAG, "Error! ");
                t.printStackTrace();
                Toast.makeText(EditItemActivity.this, "Error! " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
