package com.xznn.kankanzhihu.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.xznn.kankanzhihu.AppController;
import com.xznn.kankanzhihu.R;
import com.xznn.kankanzhihu.activities.AnswersActivity;
import com.xznn.kankanzhihu.bean.PostBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author MapleDev
 * @time 16/07/29  16:46
 * @desc ${TODD}
 */
public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.VH> {


    private static ViewHolder sViewHolder;
    private List<PostBean.PostsBean> mData;
    private Context mContext;


    public PostsAdapter(Context context) {
        mContext = context;
    }
    public void setData(List<PostBean.PostsBean> data) {
        mData = data;
    }
    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VH(View.inflate(mContext, R.layout.item_recycler_view_post, null));
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {

        final PostBean.PostsBean postsBean = mData.get(position);
        AppController.getInstance().getImageLoader().get(postsBean.getPic(), ImageLoader.getImageListener(sViewHolder.mIvPic, R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        sViewHolder.mTvExcerpt.setShadowLayer(2,2,2, Color.BLACK);
        sViewHolder.mTvExcerpt.setText(postsBean.getExcerpt());
//        sViewHolder.mTvCount.setText("回复：" + postsBean.getCount());
//        sViewHolder.mTvDate.setText("发布时间：" + postsBean.getDate());

        sViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AnswersActivity.class);
                intent.putExtra("name", postsBean.getName());
                intent.putExtra("date", postsBean.getDate());
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }


    static class VH extends RecyclerView.ViewHolder {

        public VH(View view) {
            super(view);
            sViewHolder = new ViewHolder(view);
        }
    }

    static class ViewHolder {
        @BindView(R.id.iv_pic)
        ImageView mIvPic;
        @BindView(R.id.tv_excerpt)
        TextView mTvExcerpt;
        @BindView(R.id.card_item)
        CardView mCardView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
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