package com.ruitukeji.scc.mall;

import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.ruitukeji.scc.R;
import com.ruitukeji.scc.mall.shophomepageallgoods.AllGoodsFragment;
import com.ruitukeji.scc.mall.shophomepageallgoods.ShopHomepageFragment;

/**
 * 店铺首页  全部商品
 * Created by Admin on 2017/8/21.
 */

public class ShopHomepageAllGoodsActivity extends BaseActivity {


    @BindView(id = R.id.ll_shopHomepage, click = true)
    private LinearLayout ll_shopHomepage;

    @BindView(id = R.id.tv_shopHomepage)
    private TextView tv_shopHomepage;

    @BindView(id = R.id.tv_shopHomepage1)
    private TextView tv_shopHomepage1;

    @BindView(id = R.id.ll_allGoods, click = true)
    private LinearLayout ll_allGoods;

    @BindView(id = R.id.tv_allGoods)
    private TextView tv_allGoods;

    @BindView(id = R.id.tv_allGoods1)
    private TextView tv_allGoods1;

    private BaseFragment contentFragment;
    private BaseFragment contentFragment1;

    private int chageIcon = 0;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_shophomepageallgoods);
    }

    @Override
    public void initData() {
        super.initData();
        contentFragment = new ShopHomepageFragment();
        contentFragment1 = new AllGoodsFragment();
        chageIcon = getIntent().getIntExtra("chageIcon", 0);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        if (chageIcon == 0) {
            cleanColors(0);
            changeFragment(contentFragment);
            chageIcon = 0;
        } else if (chageIcon == 1) {
            cleanColors(1);
            changeFragment(contentFragment1);
            chageIcon = 1;
        }
    }

    public void changeFragment(BaseFragment targetFragment) {
        super.changeFragment(R.id.main_content, targetFragment);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_shopHomepage:
                cleanColors(0);
                changeFragment(contentFragment);
                chageIcon = 0;
                break;
            case R.id.ll_allGoods:
                cleanColors(1);
                changeFragment(contentFragment1);
                chageIcon = 1;
                break;
            default:
                break;
        }
    }


    /**
     * 清除颜色，并添加颜色
     */
    @SuppressWarnings("deprecation")
    public void cleanColors(int chageIcon) {
        tv_shopHomepage.setTextColor(getResources().getColor(R.color.textColor));
        tv_shopHomepage1.setBackgroundColor(Color.WHITE);
        tv_allGoods.setTextColor(getResources().getColor(R.color.textColor));
        tv_allGoods1.setBackgroundColor(Color.WHITE);
        if (chageIcon == 0) {
            tv_shopHomepage.setTextColor(getResources().getColor(R.color.blueColors));
            tv_shopHomepage1.setBackgroundColor(getResources().getColor(R.color.blueColors));
        } else if (chageIcon == 1) {
            tv_allGoods.setTextColor(getResources().getColor(R.color.blueColors));
            tv_allGoods1.setBackgroundColor(getResources().getColor(R.color.blueColors));
        } else {
            tv_shopHomepage.setTextColor(getResources().getColor(R.color.blueColors));
            tv_shopHomepage1.setBackgroundColor(getResources().getColor(R.color.blueColors));
        }
    }
}
