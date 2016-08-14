package com.xznn.kankanzhihu.adapter;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author MapleDev
 * @time 16/08/13  0:48
 * @desc ${TODD}
 */
public class HeaderAndFooterRecyclerViewWrapper<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int HEADER_VIEW_BASE_KEY = 10000;
    public static final int FOOT_VIEW_BASE_KEY = 20000;
    private RecyclerView.Adapter mInnerAdapter;
    private SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();
    private SparseArrayCompat<View> mFootViews = new SparseArrayCompat<>();

    public HeaderAndFooterRecyclerViewWrapper(RecyclerView.Adapter adapter) {
        mInnerAdapter = adapter;
    }

    public void addHeaderView(View view) {
        mHeaderViews.put(HEADER_VIEW_BASE_KEY + mHeaderViews.size(), view);
    }

    public void removeHeaderView(View view) {
        mHeaderViews.remove(0);
    }

    public void addFootView(View view) {
        mFootViews.put(FOOT_VIEW_BASE_KEY + mFootViews.size(), view);
    }

    public boolean isHeaderPos(int position) {
        return position < getHeadersSize();
    }

    public boolean isFootPos(int position) {
        return position >= getHeadersSize() + getInnerItemCount();
    }

    public int getHeadersSize() {
        return mHeaderViews.size();
    }

    public int getFootsSize() {
        return mFootViews.size();
    }

    /**
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderViews.get(viewType) != null) {
            return ViewHolder.createViewHolder(parent.getContext(), mHeaderViews.get(viewType));
        } if (mFootViews.get(viewType) != null) {
            return ViewHolder.createViewHolder(parent.getContext(), mFootViews.get(viewType));
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isHeaderPos(position)) return;
        if (isFootPos(position)) return;
        mInnerAdapter.onBindViewHolder(holder, position);
    }

    /**
     * @return item 的总个数
     */
    @Override
    public int getItemCount() {
        return getHeadersSize() + getFootsSize() + getInnerItemCount();
    }

    public int getInnerItemCount() {
        return mInnerAdapter.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderPos(position)) {
            return mHeaderViews.keyAt(position);
        } else if (isFootPos(position)) {
            return mFootViews.keyAt(position - getInnerItemCount() - getHeadersSize());
        }
        return mInnerAdapter.getItemViewType(position - getHeadersSize());
    }


}
