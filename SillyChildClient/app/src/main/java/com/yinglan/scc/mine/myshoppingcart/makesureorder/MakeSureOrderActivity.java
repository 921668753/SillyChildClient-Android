package com.yinglan.scc.mine.myshoppingcart.makesureorder;

import android.view.View;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.myview.ChildListView;
import com.yinglan.scc.R;
import com.yinglan.scc.adapter.mine.myshoppingcart.MakeSureOrderViewAdaper;
import com.yinglan.scc.entity.mine.myshoppingcart.MyShoppingCartBean.DataBean.StorelistBean.GoodslistBean;

import java.util.ArrayList;

/**
 * 我的购物车中的确认订单
 * Created by Administrator on 2017/9/2.
 */

public class MakeSureOrderActivity extends BaseActivity {


    /**
     * 商品列表
     */
    @BindView(id = R.id.lv_makeSureOrder)
    private ChildListView lv_makeSureOrder;


    /**
     * 价格
     */
    @BindView(id = R.id.tv_money)
    private TextView tv_money;

    private String totalPrice = "";

    private MakeSureOrderViewAdaper makeSureOrderViewAdaper = null;
    private ArrayList<GoodslistBean> list = null;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_makesureorder);
    }

    @Override
    public void initData() {
        super.initData();
        list = (ArrayList<GoodslistBean>) getIntent().getSerializableExtra("goodslistBean");
        totalPrice = getIntent().getStringExtra("totalPrice");
        makeSureOrderViewAdaper = new MakeSureOrderViewAdaper(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        tv_money.setText(totalPrice);
        lv_makeSureOrder.setAdapter(makeSureOrderViewAdaper);
        if (list.isEmpty() || list.size() <= 0) {
            return;
        }
        makeSureOrderViewAdaper.clear();
        makeSureOrderViewAdaper.addMoreData(list);
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.confirmOrder), true, R.id.titlebar);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
    }
}
