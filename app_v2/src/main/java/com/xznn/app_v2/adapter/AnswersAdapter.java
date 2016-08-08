package com.xznn.app_v2.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.orhanobut.logger.Logger;
import com.xznn.app_v2.R;
import com.xznn.app_v2.activities.ArticalActivity;
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

    private List<AnswerBean.AnswersBean> mAnswersBeen;
    private Context mContext;


    public AnswersAdapter(List<AnswerBean.AnswersBean> answersBeen, Context context) {
        mAnswersBeen = answersBeen;
        mContext = context;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
//        return new VH(View.inflate(mContext, R.layout.item_recycler_view_answer, null));
        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view_answer, parent, false));
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {

        final AnswerBean.AnswersBean answersBean = mAnswersBeen.get(position);

//        AppController.getInstance().getImageLoader().get(answersBean.getAvatar(), ImageLoader.getImageListener(sViewHolder.mIvPic, R.drawable.ic_loading, R.mipmap.ic_launcher));
//        Glide.with(mContext).load(answersBean.getAvatar()).placeholder(R.drawable.ic_loading).crossFade().into(sViewHolder.mIvPic);
//        Glide.with(mContext).load(answersBean.getAvatar()).asBitmap().centerCrop().into(new BitmapImageViewTarget(sViewHolder.mIvPic) {
//            @Override
//            protected void setResource(Bitmap resource) {
//                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
//                circularBitmapDrawable.setCircular(true);
//                sViewHolder.mIvPic.setImageDrawable(circularBitmapDrawable);
//            }
//        });

        Glide.with(mContext).load(answersBean.getAvatar()).transform(new BitmapTransformation(mContext) {

            @Override protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
                return circleCrop(pool, toTransform);
            }

            private  Bitmap circleCrop(BitmapPool pool, Bitmap source) {
                if (source == null) return null;

                int size = Math.min(source.getWidth(), source.getHeight());
                int x = (source.getWidth() - size) / 2;
                int y = (source.getHeight() - size) / 2;

                // TODO this could be acquired from the pool too
                Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

                Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
                if (result == null) {
                    result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
                }

                Canvas canvas = new Canvas(result);
                Paint paint = new Paint();
                paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
                paint.setAntiAlias(true);
                float r = size / 2f;
                canvas.drawCircle(r, r, r, paint);
                return result;
            }

            @Override public String getId() {
                return getClass().getName();
            }
        }).placeholder(R.drawable.ic_loading).crossFade().into(holder.mIvPic);

//        sViewHolder.mIvPic.setTag(position);

        holder.mTvExcerpt.setText(answersBean.getSummary());
        holder.mTvCount.setText("答主：" + answersBean.getAuthorname());
        holder.mTvDate.setText("发布时间：" + answersBean.getTime().substring(answersBean.getTime().indexOf(" ")));

        holder.mRlContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ArticalActivity.class);
                Logger.w("=== 变量：answersBean = " + answersBean);
                intent.putExtra("questionid", answersBean.getQuestionid());
                intent.putExtra("answerid", answersBean.getAnswerid());
                intent.putExtra("authorhash", answersBean.getAuthorhash());
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mAnswersBeen == null ? 0 : mAnswersBeen.size();
    }


    static class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_pic)
        ImageView mIvPic;
        @BindView(R.id.tv_excerpt)
        TextView mTvExcerpt;
        @BindView(R.id.tv_count)
        TextView mTvCount;
        @BindView(R.id.tv_date)
        TextView mTvDate;
        @BindView(R.id.card_item)
        RelativeLayout mRlContainer;

        public VH(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
