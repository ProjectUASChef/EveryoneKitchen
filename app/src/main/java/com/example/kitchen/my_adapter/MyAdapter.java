package com.example.kitchen.my_adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.kitchen.R;
import com.example.kitchen.my_model.MyModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends BaseQuickAdapter<MyModel, BaseViewHolder>{
    public MyAdapter(@Nullable List<MyModel> data) {
        super(R.layout.menu_model, data);

    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MyModel item) {
        helper.setText(R.id.title, item.getJudul())
                .setText(R.id.bahan, item.getBahan())
                .setText(R.id.langkah, item.getLangkah())
                .addOnClickListener(R.id.CardView);


    }
}
