package com.zdl.tabtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by zdl on 2017/10/18.
 */

public class PageFragment extends Fragment {

    public static final String ARGS_PAGE = "args_page";

    private int mPage;

    private List<String> datas;

    private TextView tv2;

    /**
     * 单例模式防止重复创建
     *
     * @param page
     * @return
     */
    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARGS_PAGE, page);
        PageFragment pageFragment = new PageFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARGS_PAGE);
        datas = new ArrayList<>();

        for (int i = 0; i < 200; i++) {
            datas.add("aaaaa" + i);
        }

    }


    private RecyclerView recycle;
    private boolean show;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragmnt_layout, null, false);
        tv2 = view.findViewById(R.id.tv2);
        TextView textView = (TextView) view.findViewById(R.id.tv);
        textView.setText("第" + mPage + "页");
        recycle = view.findViewById(R.id.recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recycle.setLayoutManager(linearLayoutManager);
        recycle.setHasFixedSize(true);

        final MyadApter myadApter = new MyadApter(datas, tv2);
        myadApter.setOnItemClickListener(new MyadApter.onItemClick() {
            @Override
            public void onItemClick(int tag, View view, int position) {
                switch (tag) {
                    case -55:
                        Toast.makeText(getActivity(), "aaaaaa" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case -66:
                        Toast.makeText(getActivity(), "bbbb" + position, Toast.LENGTH_SHORT).show();

                        show = !show;
                        myadApter.setdata(show);

                        break;
//
//                    case -88:
//
//                        break;
                }


                Toast.makeText(getActivity(), "aaaaaa", Toast.LENGTH_SHORT).show();
            }


        });
        recycle.setAdapter(myadApter);

        myadApter.getData();


        return view;

    }


}
