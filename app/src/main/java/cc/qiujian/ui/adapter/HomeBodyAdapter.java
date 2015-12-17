package cc.qiujian.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cc.qiujian.R;

/**
 * Created by Administrator on 2015/12/16.
 */
public class HomeBodyAdapter extends RecyclerView.Adapter<HomeBodyAdapter.ViewHolder> {
    private static final String TAG = HomeBodyAdapter.class.getSimpleName();

    private static final int IS_NORMAL = 0;
    private static final int IS_HEADER = 1;
    private static final int IS_FOOTER = 2;

    private final LayoutInflater mInflater;
    private final Context mContext;
    private List<String> mList;
    private List<ImageView> mHeaderImages;
    private OnItemClickListener mOnItemClickListener;


    public HomeBodyAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;
        this.mInflater = LayoutInflater.from(context);
        this.mHeaderImages = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ImageView iv = new ImageView(context);
            iv.setImageResource(R.drawable.icon_thumb_default);
            this.mHeaderImages.add(iv);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case IS_HEADER:
                Log.e(TAG, "onCreateViewHolder IS_HEADER");
                return new ViewHolder(mInflater.inflate(R.layout.item_home_hall_header, null), viewType);
            case IS_FOOTER:
                Log.e(TAG, "onCreateViewHolder IS_FOOTER");
                return new ViewHolder(mInflater.inflate(R.layout.item_home_hall_footer, null), viewType);
            default:
                Log.e(TAG, "onCreateViewHolder default");
                return new ViewHolder(mInflater.inflate(R.layout.item_home_hall_body, null), viewType);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (holder.mViewType) {
            case IS_HEADER:
                Log.e(TAG, "onBindViewHolder IS_HEADER");
                break;
            case IS_FOOTER:
                Log.e(TAG, "onBindViewHolder IS_FOOTER");
                break;
            default:
                Log.e(TAG, "onBindViewHolder default");
                holder.mTvTaskTheme.setText(mList.get(position - 1));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size() + 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return IS_HEADER;
        } else if (position == mList.size() + 1) {
            return IS_FOOTER;
        } else {
            return IS_NORMAL;
        }
    }


    public static interface OnItemClickListener {
        void onItemClick(RecyclerView.ViewHolder viewHolder, int position, long id);
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        int mViewType;

        LinearLayout mRlHead;
        ImageView mIvHead;
        TextView mTvNickName;
        ImageView mIvThumb;
        TextView mTvTaskTheme;
        TextView mTvTaskDes;

        private void assignViews(View v) {
            mRlHead = (LinearLayout) v.findViewById(R.id.rl_head);
            mIvHead = (ImageView) v.findViewById(R.id.iv_head);
            mTvNickName = (TextView) v.findViewById(R.id.tv_nickName);
            mIvThumb = (ImageView) v.findViewById(R.id.iv_thumb);
            mTvTaskTheme = (TextView) v.findViewById(R.id.tv_taskTheme);
            mTvTaskDes = (TextView) v.findViewById(R.id.tv_taskDes);

            if (null != mOnItemClickListener)
                v.setOnClickListener(this);
        }

        ViewPager mViewPager;

        private void assignHeaderViews(View v) {
            mViewPager = (ViewPager) v.findViewById(R.id.viewPager);
            mViewPager.setAdapter(new PagerAdapter() {

                @Override
                public Object instantiateItem(ViewGroup container, int position) {
                    Log.e(TAG, "pagerAdapter instantiateItem");
                    ImageView imageView = mHeaderImages.get(position);
                    container.addView(imageView);
                    return imageView;
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    Log.e(TAG, "pagerAdapter destroyItem");
                    container.removeView(mHeaderImages.get(position));
                }

                @Override
                public int getCount() {
                    Log.e(TAG, "pagerAdapter getCount");
                    return mHeaderImages.size();
                }

                @Override
                public boolean isViewFromObject(View view, Object object) {
                    Log.e(TAG, "pagerAdapter isViewFromObject");
                    return view == object;
                }
            });
        }

        public ViewHolder(View itemView, int viewType) {
            super(itemView);
            this.mViewType = viewType;
            switch (viewType) {
                case IS_HEADER:
                    assignHeaderViews(itemView);
                    break;

                case IS_FOOTER:

                    break;

                default:
                    assignViews(itemView);
                    break;
            }
        }

        @Override
        public void onClick(View v) {
            final int adapterPosition = getAdapterPosition();
            Log.e(TAG, adapterPosition + "adapterPosition");
            mOnItemClickListener.onItemClick(this, getAdapterPosition(), getItemId());
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
