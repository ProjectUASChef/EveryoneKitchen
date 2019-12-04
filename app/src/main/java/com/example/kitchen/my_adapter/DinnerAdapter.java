package com.example.kitchen.my_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kitchen.R;
import com.example.kitchen.my_model.DinnerModel;

import java.util.ArrayList;

public class DinnerAdapter extends RecyclerView.Adapter<DinnerAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<DinnerModel> mlist;

    public DinnerAdapter(ArrayList<DinnerModel> list) {
        this.mlist = list;
    }

    @NonNull
    @Override
    public DinnerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.model_menu, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DinnerAdapter.ViewHolder holder, int position) {
        DinnerModel dinnerModel = mlist.get(position);
        TextView ttl;
        ttl = holder.dinnerTitle;
        ttl.setText(dinnerModel.title);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dinnerTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dinnerTitle = itemView.findViewById(R.id.title);
        }
    }
}
