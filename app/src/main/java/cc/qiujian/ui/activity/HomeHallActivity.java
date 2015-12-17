package cc.qiujian.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import cc.qiujian.R;
import cc.qiujian.ui.adapter.HomeBodyAdapter;
import cc.qiujian.ui.base.BaseActivity;

@EActivity(R.layout.activity_home_hall)
public class HomeHallActivity extends BaseActivity {
    private static final String TAG = "HomeHallActivity";
    @ViewById(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private HomeBodyAdapter mAdapter;

    @AfterViews
    void afterViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new HomeBodyAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    ItemTouchHelper.Callback mCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT) {

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            int fromPosition = viewHolder.getAdapterPosition();//得到拖动ViewHolder的position
            int toPosition = target.getAdapterPosition();//得到目标ViewHolder的position
            if (fromPosition < toPosition) {
                //分别把中间所有的item的位置重新交换
                for (int i = fromPosition; i < toPosition; i++) {
//                    Collections.swap(datas, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
//                    Collections.swap(datas, i, i - 1);
                }
            }
            mAdapter.notifyItemMoved(fromPosition, toPosition);
            //返回true表示执行拖动
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            Log.e(TAG, "onSwiped");
        }
    };
}
