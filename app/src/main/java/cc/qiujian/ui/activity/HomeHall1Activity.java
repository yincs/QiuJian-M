package cc.qiujian.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import cc.qiujian.R;
import cc.qiujian.ui.adapter.HomeBodyAdapter;

@EActivity(R.layout.activity_home_hall1)
public class HomeHall1Activity extends AppCompatActivity {

    private static final String TAG = "HomeHall1Activity";
    private static final String[] mTabMenu = {"最新", "附近", "关注"};
    private final TabViewFragment[] mTabFragments = new TabViewFragment[mTabMenu.length];

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;
    @ViewById(R.id.tabLayout)
    TabLayout mTabLayout;
    @ViewById(R.id.viewPager)
    ViewPager mViewPager;
    @ViewById(R.id.fab)
    FloatingActionButton mFab;


    @AfterViews
    void afterViews() {
        initToolbar();
    }

    private void initToolbar() {
        mToolbar.setNavigationIcon(R.drawable.icon_filtrate);

        for (int i = 0; i < mTabMenu.length; i++) {
            mTabFragments[i] = TabViewFragment.newInstance(mTabMenu[i]);
        }
        TabViewPagerAdapter adapter = new TabViewPagerAdapter();
        mViewPager.setAdapter(adapter);
        mTabLayout.setTabsFromPagerAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private class TabViewPagerAdapter extends FragmentPagerAdapter {

        public TabViewPagerAdapter() {
            super(HomeHall1Activity.this.getSupportFragmentManager());
        }

        @Override
        public Fragment getItem(int position) {
            return mTabFragments[position];
        }

        @Override
        public int getCount() {
            return mTabFragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabMenu[position];
        }
    }

    @EFragment(R.layout.frag_home_hall_body)
    public static class TabViewFragment extends Fragment {
        @ViewById(R.id.srLayout)
        SwipeRefreshLayout mSrLayout;
        @ViewById(R.id.rcView)
        RecyclerView mRcView;

        private static final String ARG_TYPE = "arg_type";
        private String mType;


        public static TabViewFragment newInstance(String type) {
            TabViewFragment fragment = new HomeHall1Activity_.TabViewFragment_();
            Bundle args = new Bundle();
            args.putString(ARG_TYPE, type);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                mType = getArguments().getString(ARG_TYPE);
            }
        }

        @AfterViews
        void afterViews() {
            mRcView.setLayoutManager(new LinearLayoutManager(getActivity()));
            List<String> list = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                list.add("第几个直播了？" + i);
            }
            mRcView.setAdapter(new HomeBodyAdapter(getActivity(), list));
        }

    }
}
