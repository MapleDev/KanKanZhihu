package com.xznn.app_v2.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xznn.app_v2.R;
import com.xznn.app_v2.activities.AnswersActivity;
import com.xznn.app_v2.bean.AnswerBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author MapleDev
 * @time 16/07/29  16:46
 * @desc ${TODD}
 */
public class AnswersAdapter extends RecyclerView.Adapter<AnswersAdapter.VH> {


    private static ViewHolder sViewHolder;
    private List<AnswerBean.AnswersBean> mAnswersBeen;
    private Context mContext;


    public AnswersAdapter(List<AnswerBean.AnswersBean> answersBeen, Context context) {
        mAnswersBeen = answersBeen;
        mContext = context;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VH(View.inflate(mContext, R.layout.item_recycler_view_post, null));
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {

        AnswerBean.AnswersBean answersBean = mAnswersBeen.get(position);
        Glide.with(mContext).load(answersBean.getAvatar()).into(sViewHolder.mIvPic);
        sViewHolder.mTvExcerpt.setText(answersBean.getSummary());
        sViewHolder.mTvCount.setText("答主：" + answersBean.getAuthorname());
        sViewHolder.mTvDate.setText("发布时间：" + answersBean.getTime());

        sViewHolder.mRlContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AnswersActivity.class);
//                intent.putExtra("name", postsBean.getName());
//                intent.putExtra("date", postsBean.getDate());
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mAnswersBeen == null ? 0 : mAnswersBeen.size();
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
