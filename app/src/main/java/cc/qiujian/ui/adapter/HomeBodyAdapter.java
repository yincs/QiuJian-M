package cc.qiujian.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cc.qiujian.R;

/**
 * Created by Administrator on 2015/12/16.
 */
public class HomeBodyAdapter extends RecyclerView.Adapter<HomeBodyAdapter.ViewHolder> {

    private final LayoutInflater mInflater;
    private final Context mContext;

    public HomeBodyAdapter(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_home_hall_body, null, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
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
        }

        public ViewHolder(View itemView) {
            super(itemView);
            assignViews(itemView);
        }
    }
}
