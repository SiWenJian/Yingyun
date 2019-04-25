package com.zhongping.yingyun.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.zhongping.yingyun.R;
import com.zhongping.yingyun.fragment.Fragment_Company;
import com.zhongping.yingyun.fragment.Fragment_Person;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Wenjian on 2019/4/23.
 */

public class RegisterActivity extends AppCompatActivity {
    @BindView(R.id.register_type)
    CommonTabLayout registerType;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.register_page)
    ViewPager registerPage;
    private String[] mTitles = {"个人注册", "公司注册"};
    private int[] mIconUnselectIds = {R.drawable.icon_zw, R.drawable.icon_mall_xl};
    private int[] mIconSelectIds = {R.drawable.icon_zw, R.drawable.icon_mall_xl};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private MyFragmentPagerAdapter adapter;
    List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT > 18) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        registerType.setTabData(mTabEntities);
        registerType.setIconVisible(false);
        fragments.add(new Fragment_Person());
        fragments.add(new Fragment_Company());
        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragments);
        registerPage.setAdapter(adapter);
        registerPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                registerType.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        registerType.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                registerPage.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    public class TabEntity implements CustomTabEntity {
        public String title;
        public int selectedIcon;
        public int unSelectedIcon;

        public TabEntity(String title, int selectedIcon, int unSelectedIcon) {
            this.title = title;
            this.selectedIcon = selectedIcon;
            this.unSelectedIcon = unSelectedIcon;
        }

        @Override
        public String getTabTitle() {
            return title;
        }

        @Override
        public int getTabSelectedIcon() {
            return selectedIcon;
        }

        @Override
        public int getTabUnselectedIcon() {
            return unSelectedIcon;
        }
    }

    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;

        public MyFragmentPagerAdapter(FragmentManager fm , List<Fragment> fragmentList) {
            super(fm);
            fragments = fragmentList;
        }
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
        //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }
    }
}
