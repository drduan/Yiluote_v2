package com.yiluote.yiluote_v2;

import java.util.ArrayList;
import java.util.List;

import com.yiluote.fragment.ForumFragment;
import com.yiluote.fragment.InfoFragment;
import com.yiluote.fragment.RecommendFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements OnClickListener {

    private Intent intent;

    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments;

    private ImageView info;
    private ImageView recommend;
    private ImageView forum;
    private ImageView userHead;
    private ImageView search;
    private ImageView location;
    private ImageView message;
    private ImageView setting;
    private ImageView release;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_framework);

        initView();
        initEvent();
 //       setSelect(0);//设置启动界面
//		InfoFragment infoFragment = new InfoFragment();
//		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//		ft.add(R.id.infoContent, infoFragment);
//		ft.commit();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        userHead = (ImageView) findViewById(R.id.userHead);
        search = (ImageView) findViewById(R.id.search);
        setting = (ImageView) findViewById(R.id.setting);
        info = (ImageView) findViewById(R.id.info);
        message = (ImageView) findViewById(R.id.message);
        location = (ImageView) findViewById(R.id.location);
        recommend = (ImageView) findViewById(R.id.recommend);
        forum = (ImageView) findViewById(R.id.forum);
        release = (ImageView) findViewById(R.id.release);

        mFragments = new ArrayList<Fragment>();

        Fragment infoFragment = new InfoFragment();
        Fragment recommendFragment = new RecommendFragment();
        Fragment forumFragment = new ForumFragment();

        mFragments.add(infoFragment);
        mFragments.add(recommendFragment);
        mFragments.add(forumFragment);

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return mFragments.get(arg0);
            }
        };
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                int currentItem = mViewPager.getCurrentItem();
                //	setTab(currentItem);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    private void initEvent() {
        userHead.setOnClickListener(this);
        search.setOnClickListener(this);
        setting.setOnClickListener(this);
        info.setOnClickListener(this);
        message.setOnClickListener(this);
        location.setOnClickListener(this);
        recommend.setOnClickListener(this);
        forum.setOnClickListener(this);
        release.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.userHead:
                intent = new Intent();
                intent.setClass(MainActivity.this, UserActivity.class);
                startActivity(intent);
                break;

            case R.id.setting:
                intent = new Intent();
                intent.setClass(MainActivity.this, SettingActivity.class);
                startActivity(intent);
                break;

            case R.id.info:
                setSelect(0);
                break;

            case R.id.recommend:
                setSelect(1);
                break;

            case R.id.forum:
                setSelect(2);
                break;

            case R.id.search:
                intent = new Intent();
                intent.setClass(MainActivity.this, SearchActivity.class);
                startActivity(intent);
                break;

            case R.id.release:
                intent = new Intent();
                intent.setClass(MainActivity.this, ReleaseActivity1.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_bottom_in, R.anim.push_bottom_out);
                break;

            case R.id.message:
                Toast.makeText(this, "亲，消息功能还没上线哦~", Toast.LENGTH_SHORT).show();
                break;

            case R.id.location:
                Toast.makeText(this, "亲，周边功能还没上线哦~", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }

    /**
     * 设置图片为亮色
     * 切换内容区域
     *
     * @param i 页面编号
     */
    private void setSelect(int i) {
        setTab(i);
        mViewPager.setCurrentItem(i);
    }

    private void setTab(int i) {
        resetImages();
        switch (i) {
            case 0:

                info.setImageResource(R.drawable.zixun_blue);
                break;

            case 1:

                recommend.setImageResource(R.drawable.tuijian_blue);
                break;

            case 2:
                forum.setImageResource(R.drawable.forum_blue);
                break;

            default:
                break;
        }
    }

    /**
     * 切换图片为暗灰色
     */
    private void resetImages() {

        info.setImageResource(R.drawable.zixun_dark);

        recommend.setImageResource(R.drawable.tuijan_dark);
        forum.setImageResource(R.drawable.forum_dark);
    }
}
