package com.xznn.kankanzhihu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.xznn.kankanzhihu.bean.PostBean;

import java.util.List;

/**
 * @author MapleDev
 * @time 16/07/29  16:46
 * @desc ${TODD}
 */
public class PostsAdapterWithHeader_Foot extends HeaderAndFooterRecyclerViewWrapper {


    private static ViewHolder sViewHolder;
    private List<PostBean.PostsBean> mPostsBeen;
    private Context mContext;

    public PostsAdapterWithHeader_Foot(RecyclerView.Adapter adapter) {
        super(adapter);
    }
}


/*

    static class VH extends RecyclerView.ViewHolder {
        ImageView mIvPic;
        TextView mTvExcerpt;
        TextView mTvCount;
        TextView mTvDate;
        RelativeLayout mRlContainer;

        public VH(View view) {
            super(view);
            mIvPic = (ImageView) view.findViewById(R.id.iv_pic);
            mTvExcerpt = (TextView) view.findViewById(R.id.tv_excerpt);
            mTvCount = (TextView) view.findViewById(R.id.tv_count);
            mTvDate = (TextView) view.findViewById(R.id.tv_date);
            mRlContainer= (RelativeLayout) view.findViewById(R.id.rl_container);
        }
    }

* */