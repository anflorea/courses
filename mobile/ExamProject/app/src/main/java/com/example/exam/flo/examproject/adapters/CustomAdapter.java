package com.example.exam.flo.examproject.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.exam.flo.examproject.R;
import com.example.exam.flo.examproject.domain.Patient;

import java.util.List;

/**
 * Created by flo on 01/02/2018.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomAdapterViewHolder> {
    public static final String TAG = CustomAdapter.class.getSimpleName();

    private Context mContext;
    private List<Patient> mData;

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
        Patient patient = mData.get(position);

        holder.mNameView.setText(patient.getName());
        holder.mIdView.setText(String.valueOf(patient.getId()));

        holder.itemView.setTag(patient.getId());
    }

    @Override
    public int getItemCount() {
        if (mData == null)
            return 0;
        return mData.size();
    }

    public class CustomAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView mNameView;
        private TextView mIdView;

        public CustomAdapterViewHolder(View itemView) {
            super(itemView);

            mNameView = (TextView) itemView.findViewById(R.id.name_tv);
            mIdView = (TextView) itemView.findViewById(R.id.id_tv);
        }
    }

    public void setData(List<Patient> data) {
        mData = data;
        notifyDataSetChanged();
    }
}
