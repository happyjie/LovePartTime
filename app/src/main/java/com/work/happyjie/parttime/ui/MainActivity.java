package com.work.happyjie.parttime.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.lib.llj.utils.SingleClickListener;
import com.lib.llj.utils.StatusBarUtil;
import com.lib.llj.widget.MenuItemView;
import com.orhanobut.logger.Logger;
import com.work.happyjie.parttime.R;
import com.work.happyjie.parttime.base.BaseActivity;
import com.work.happyjie.parttime.databinding.ActivityMainBinding;
import com.work.happyjie.parttime.databinding.LayoutSlideMenuBinding;
import com.work.happyjie.parttime.ui.adapter.MyFragmentPagerAdapter;
import com.work.happyjie.parttime.ui.douban.DoubanFragment;
import com.work.happyjie.parttime.ui.gank.GankFragment;
import com.work.happyjie.parttime.ui.news.NewsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by llj on 2017/12/7.
 */

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private LayoutSlideMenuBinding slideMenuBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        super.initView();
        showContentView();
        initViewId();
        initDrawerLayout();
        initContentView();

        StatusBarUtil.setColorNoTranslucentForDrawerLayout(MainActivity.this, drawerLayout,
                getResources().getColor(R.color.colorTheme));
    }

    @Override
    protected void initListener() {
        super.initListener();

    }

    private void initViewId() {
        drawerLayout = mViewBinding.drawerLayout;
        navigationView = mViewBinding.navView;
    }

    @Override
    protected int getToolBarLeftIcon() {
        return R.drawable.titlebar_menu;
    }

    @Override
    protected View.OnClickListener getToolBarLeftIconClickListener() {
        return v -> drawerLayout.openDrawer(GravityCompat.START);
    }

    private void initContentView() {

    }


    /**
     * inflateHeaderView 进来的布局要宽一些
     */
    private void initDrawerLayout() {
        navigationView.inflateHeaderView(R.layout.layout_slide_menu);
        View headerView = navigationView.getHeaderView(0);
        slideMenuBinding = DataBindingUtil.bind(headerView);

//        GlideUtils.showAvatar(slideMenuBinding.ivAvatar, "");
//        slideMenuBinding.ivAvatar.setOnClickListener(listener);
        slideMenuBinding.menuGoHome.setOnClickListener(listener);
        slideMenuBinding.menuAppRecommend.setOnClickListener(listener);
        slideMenuBinding.menuFeedback.setOnClickListener(listener);
        slideMenuBinding.menuAboutUs.setOnClickListener(listener);
        slideMenuBinding.menuExit.setOnClickListener(listener);
    }

    private SingleClickListener listener = new SingleClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            drawerLayout.closeDrawer(GravityCompat.START);
            drawerLayout.postDelayed(() -> {
                switch (v.getId()) {
                    case R.id.menu_go_home:
                        break;
                    case R.id.menu_app_recommend:
                        break;
                    case R.id.menu_feedback:
                        break;
                    case R.id.menu_about_us:
                        break;
                    case R.id.menu_exit:
                        break;
                }

                if (v instanceof MenuItemView) {
                    Toast.makeText(MainActivity.this, ((MenuItemView) v).getMenuTitle(), Toast.LENGTH_SHORT).show();
                }
            }, 200);
        }
    };


    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                // 不退出程序，进入后台
                moveTaskToBack(true);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
