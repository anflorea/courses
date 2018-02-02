package com.example.exam.flo.examproject.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.exam.flo.examproject.R;
import com.example.exam.flo.examproject.domain.Record;

import java.util.List;

/**
 * Created by flo on 02/02/2018.
 */

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder> {
    public static final String TAG = RecordAdapter.class.getSimpleName();

    public interface RecordAdapterOnClickListener {
        void onClick(Record record);
    }

    private List<Record> mData;
    private RecordAdapterOnClickListener mClickListener;

    public RecordAdapter(RecordAdapterOnClickListener click) {
        mClickListener = click;
    }

    @Override
    public RecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.record_item, parent, false);
        return new RecordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecordViewHolder holder, int position) {
        Record record = mData.get(position);

        holder.mType.setText(record.getType());
        holder.mStatus.setText(record.getStatus());
    }

    @Override
    public int getItemCount() {
        if (mData == null)
            return 0;
        return mData.size();
    }

    public void setData(List<Record> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public class RecordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mType;
        TextView mStatus;

        public RecordViewHolder(View itemView) {
            super(itemView);

            mType = (TextView) itemView.findViewById(R.id.type_tv);
            mStatus = (TextView) itemView.findViewById(R.id.status_tv);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mData == null)
                return;
            mClickListener.onClick(mData.get(getAdapterPosition()));
        }
    }
}
