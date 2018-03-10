package com.zdl.testapp;

import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zdl on 2017/10/22.
 */

public class MyadApter extends RecyclerView.Adapter<MyadApter.MyvViewholder> {

    private List<String> datas;
    private TextView tv2;
    int temp = 0;
    private boolean isShow;

    public MyadApter(List<String> datas, TextView tv2) {
        this.datas = datas;
        this.tv2 = tv2;
    }


    /**
     * 声明接口变量
     */
    private onItemClick onItemClick;

    /**
     * 定义监听接口
     */
    public static interface onItemClick {

        void onItemClick(int tag, View view, int position);
    }

    /**
     * 声明给外界的方法
     *
     * @param listener
     */
    public void setOnItemClickListener(onItemClick listener) {
        this.onItemClick = listener;
    }

    /**
     * 绑定视图
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyvViewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null, false);
        MyvViewholder myvViewholder = new MyvViewholder(view);
//        view.setOnClickListener(this);
        myvViewholder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onItemClick(-55, view, (Integer) view.getTag());
            }
        });
        myvViewholder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onItemClick(-66, view, (Integer) view.getTag());
            }
        });


        myvViewholder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {
                    temp++;
                } else {
                    temp--;
                }

                if (temp < 0) {
                    temp = 0;
                }
                tv2.setText(temp + "");

            }
        });


        return myvViewholder;
    }

    /**
     * 填充数据
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MyvViewholder holder, int position) {
        holder.textView.setText(datas.get(position));
        holder.itemView.setTag(position);
        holder.textView.setTag(position);
        holder.imageView.setTag(position);
        if (isShow)
            holder.checkBox.setVisibility(View.VISIBLE);
        else
            holder.checkBox.setVisibility(View.GONE);


    }


    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyvViewholder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;
        private CheckBox checkBox;

        public MyvViewholder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.iv);
            checkBox = itemView.findViewById(R.id.cb);

        }
    }


    public int getData() {

        int size = datas.size();
        return size;
    }

    public void setdata(boolean isShow) {
        this.isShow = isShow;
        notifyDataSetChanged();
    }

}
