package com.example.kitchen.my_adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.kitchen.R;
import com.example.kitchen.my_model.DinnerModel;

import java.util.List;

public class DinnerAdapter extends BaseQuickAdapter<DinnerModel, BaseViewHolder> {
    public DinnerAdapter(@Nullable List<DinnerModel> data) {
        super(R.layout.model_menu, data);

    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, DinnerModel item) {
        helper.setText(R.id.title, item.getBahan());
        helper.setText(R.id.deskripsi, item.getNama());


    }
}
