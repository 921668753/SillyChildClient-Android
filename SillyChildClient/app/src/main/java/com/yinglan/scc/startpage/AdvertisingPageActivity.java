package com.yinglan.scc.startpage;

import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;


import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.yinglan.scc.R;
import com.yinglan.scc.main.MainActivity;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * 广告页
 * Created by Administrator on 2017/2/15.
 */

public class AdvertisingPageActivity extends BaseActivity {

    @BindView(id = R.id.banner_guide_foreground)
    private BGABanner mForegroundBanner;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_cuideview);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        processLogic();
    }

    private void processLogic() {

        mForegroundBanner.setAutoPlayAble(true);
        mForegroundBanner.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mForegroundBanner.setAllowUserScrollable(true);
        mForegroundBanner.setAutoPlayInterval(2000);
        // 初始化方式1：通过传入数据模型并结合Adapter的方式初始化
//        mForegroundBanner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
//            @Override
//            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
//                Glide.with(MainActivity.this)
//                        .load(model)
//                        .placeholder(R.drawable.holder)
//                        .error(R.drawable.holder)
//                        .centerCrop()
//                        .dontAnimate()
//                        .into(itemView);
//            }
//        });
//
//        mForegroundBanner.setData(Arrays.asList("网络图片路径1", "网络图片路径2", "网络图片路径3"), Arrays.asList("提示文字1", "提示文字2", "提示文字3"));
//        List<View> views = new ArrayList<>();
//        views.add(BGABannerUtil.getItemImageView(this, R.drawable.ic_guide_1));
//        views.add(BGABannerUtil.getItemImageView(this, R.drawable.ic_guide_2));
//        views.add(BGABannerUtil.getItemImageView(this, R.drawable.ic_guide_3));
//        mContentBanner.setData(views);
//        配置数据源的方式3：通过传入图片资源 id 的方式配置数据源，主要用于引导页每一页都是只显示图片的情况
//        mContentBanner.setData(R.drawable.uoko_guide_foreground_1, R.drawable.uoko_guide_foreground_2, R.drawable.uoko_guide_foreground_3);
        mForegroundBanner.setData(R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        //  监听广告 item 的单击事件
        mForegroundBanner.setDelegate(new BGABanner.Delegate<ImageView, String>() {
            @Override
            public void onBannerItemClick(BGABanner banner, ImageView itemView, String model, int position) {
                ViewInject.toast("点击了第" + (position + 1) + "页");
            }
        });
        // 监听页码切换事件，控制「跳过按钮」和「进入主界面的显示与隐藏」
        mForegroundBanner.setEnterSkipViewIdAndDelegate(0, R.id.tv_guide_skip, new BGABanner.GuideDelegate() {
            @Override
            public void onClickEnterOrSkip() {
                mForegroundBanner.setAutoPlayAble(false);
                skipActivity(aty, MainActivity.class);
            }
        });
        mForegroundBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position + 1 == mForegroundBanner.getItemCount()) {
                    mForegroundBanner.setAutoPlayAble(false);
                    mForegroundBanner.removePlaceholder();
                    mForegroundBanner.stopAutoPlay();
                    skipActivity(aty, MainActivity.class);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    /**
     * 禁止退出应用
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyUp(keyCode, event);
    }
}