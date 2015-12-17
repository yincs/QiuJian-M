package cc.qiujian.ui.activity;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import cc.qiujian.R;
import cc.qiujian.ui.adapter.HomeBodyAdapter;
import cc.qiujian.ui.base.BaseActivity;

@EActivity(R.layout.activity_home_hall)
public class HomeHallActivity extends BaseActivity {
    private static final String TAG = "HomeHallActivity";
    @ViewById(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private HomeBodyAdapter mAdapter;
    private List<String> mList;


    @AfterViews
    void afterViews() {
        mList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mList.add("发布的视频" + i);
        }

        final LinearLayoutManager layout = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layout);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new HomeBodyAdapter(this, mList);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new HomeBodyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder, int position, long id) {
                Log.e(TAG, position + ":::" + id);
            }
        });
    }


}
