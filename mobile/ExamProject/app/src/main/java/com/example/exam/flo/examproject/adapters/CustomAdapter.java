package com.example.exam.flo.examproject.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.exam.flo.examproject.R;

/**
 * Created by flo on 01/02/2018.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomAdapterViewHolder> {
    public static final String TAG = CustomAdapter.class.getSimpleName();

    Context mContext;

    public CustomAdapter(Context context) {
        mContext = context;
    }

    @Override
    public CustomAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.single_item, parent, false);
        return new CustomAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomAdapterViewHolder holder, int position) {
        // Populate ViewHolder properties for item at position
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CustomAdapterViewHolder extends RecyclerView.ViewHolder {

        // Insert here single item properties

        public CustomAdapterViewHolder(View itemView) {
            super(itemView);

            // Init single item properties
        }
    }
}
