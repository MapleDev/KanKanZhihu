package com.xznn.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MapleDev
 * @time 16/08/13  15:26
 * @desc ${TODD}
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.VH> {

    Context mContext;
    List<String> mData;
    static int cnt = 0;

    public static final int HEADER_VIEW = 0;
    public static final int INNER_VIEW = 1;

    public RecyclerAdapter(Context context) {
        mContext = context;
        mData = new ArrayList<>();
        mData.add("1");
        mData.add("2");
        mData.add("3");
        mData.add("4");
        mData.add("5");
        mData.add("6");
        mData.add("7");
        mData.add("8");
        mData.add("9");
        mData.add("10");
        mData.add("11");
        mData.add("12");
        mData.add("13");
        mData.add("14");
        mData.add("15");
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("DDD", "====== 所在方法：onCreateViewHolder([parent, viewType]) ======");
        VH vh = null;
        switch (viewType) {
            case HEADER_VIEW:
                TextView textView = new TextView(mContext);
                textView.setText("Header");
                vh = new VH(textView);
                break;
            case INNER_VIEW:
                vh = new VH(View.inflate(parent.getContext(), android.R.layout.simple_list_item_1, null));
                break;
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        Log.d("DDD", "====== 所在方法：onBindViewHolder([holder, position]) ======");
        if (position  %3 ==  0) {
            return;
        } else {
            holder.textView.setText(mData.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position %3 == 0) {
            return HEADER_VIEW;
        } else {
            return INNER_VIEW;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView textView;

        public VH(View itemView) {
            super(itemView);
            Log.d("DDD", "====== new VH() cnt = " + ++cnt);
            textView = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }
}
