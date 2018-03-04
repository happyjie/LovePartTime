package com.work.happyjie.parttime.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.lib.llj.utils.SingleClickListener;
import com.lib.llj.utils.StatusBarUtil;
import com.lib.llj.widget.MenuItemView;
import com.work.happyjie.parttime.R;
import com.work.happyjie.parttime.base.BaseActivity;
import com.work.happyjie.parttime.databinding.ActivityMainBinding;
import com.work.happyjie.parttime.databinding.LayoutSlideMenuBinding;
import com.work.happyjie.parttime.utils.ToastUtils;

/**
 * Created by llj on 2017/12/7.
 */

public class MainActivity extends BaseActivity<ActivityMainBinding> implements View.OnClickListener {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private LayoutSlideMenuBinding slideMenuBinding;

    private String AUTO_WORK_OPEN = "自动刷单功能已开启";
    private String AUTO_WORK_CLOSED = "自动刷单功能已关闭";

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

        setToolbarVisible(false);

        StatusBarUtil.setColorNoTranslucentForDrawerLayout(MainActivity.this, drawerLayout,
                getResources().getColor(R.color.colorTheme));
    }

    @Override
    protected void initListener() {
        super.initListener();
        mViewBinding.tvIncomingDetail.setOnClickListener(this);
        mViewBinding.tvFinanceInfo.setOnClickListener(this);
        mViewBinding.tvContactUs.setOnClickListener(this);
        mViewBinding.tvTaskList.setOnClickListener(this);
        mViewBinding.tvReadMe.setOnClickListener(this);
        mViewBinding.tvMore.setOnClickListener(this);

        mViewBinding.switchHome.setOnCheckedChangeListener((buttonView, isChecked)
                -> mViewBinding.tvSwitchContent.setText(isChecked ? AUTO_WORK_OPEN : AUTO_WORK_CLOSED));
    }


    private void initViewId() {
        drawerLayout = mViewBinding.drawerLayout;
        navigationView = mViewBinding.navView;
    }

    @Override
    protected int getToolBarLeftIcon() {
        return R.drawable.titlebar_menu;
    }

    private void initContentView() {

        initHomeTitleBar();
    }

    /**
     * 初始化首页标题栏
     * 由于首页使用了策划菜单，属于不能使用BaseActivity提供的标题栏，
     * 否则，侧滑菜单无法覆盖住标题栏，会很丑
     */
    private void initHomeTitleBar() {
        mViewBinding.toolBar.tvHomeTitle.setText(getString(R.string.app_name_chinese));

        setSupportActionBar(mViewBinding.toolBar.toolbarHome);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        mViewBinding.toolBar.toolbarHome.setNavigationOnClickListener(v ->
                drawerLayout.openDrawer(GravityCompat.START));
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_incoming_detail:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.tv_finance_info:
                break;
            case R.id.tv_task_list:
                break;
            case R.id.tv_read_me:
                break;
            case R.id.tv_contact_us:
                break;
            case R.id.tv_more:
                ToastUtils.showToast("暂未开放，敬请期待！");
                break;
        }
    }
}
