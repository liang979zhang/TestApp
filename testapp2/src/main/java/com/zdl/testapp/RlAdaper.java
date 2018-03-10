package com.zdl.testapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by zdl on 2017/11/14.
 */

public class RlAdaper extends RecyclerView.Adapter<RlAdaper.MyvViewholder> {

    @Override
    public MyvViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MyvViewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MyvViewholder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;

        public MyvViewholder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.iv);
        }
    }

}
