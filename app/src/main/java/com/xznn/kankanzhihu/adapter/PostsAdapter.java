package com.xznn.kankanzhihu.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xznn.kankanzhihu.R;
import com.xznn.kankanzhihu.activities.PostAnswersActivity;
import com.xznn.kankanzhihu.bean.PostsBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author MapleDev
 * @time 16/07/29  16:46
 * @desc ${TODD}
 */
public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.VH> {


    private List<PostsBean> mPostsBeen;
    private Context mContext;
    private static ViewHolder sViewHolder;


    public PostsAdapter(List<PostsBean> postsBeen, Context context) {
        mPostsBeen = postsBeen;
        mContext = context;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VH(View.inflate(mContext, R.layout.recycler_view_post, null));
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {

        final PostsBean postsBean = mPostsBeen.get(position);
        Glide.with(mContext).load(postsBean.getPic()).into(sViewHolder.mIvPic);
        sViewHolder.mTvExcerpt.setText(postsBean.getExcerpt());
        sViewHolder.mTvCount.setText("回复：" + postsBean.getCount());
        sViewHolder.mTvDate.setText("发布时间：" + postsBean.getDate());

        sViewHolder.mRlContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PostAnswersActivity.class);
                intent.putExtra("name", postsBean.getName());
                intent.putExtra("date", postsBean.getDate());
                mContext.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return mPostsBeen == null ? 0 : mPostsBeen.size();
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
        @BindView(R.id.tv_count)
        TextView mTvCount;
        @BindView(R.id.tv_date)
        TextView mTvDate;
        @BindView(R.id.rl_container)
        RelativeLayout mRlContainer;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
