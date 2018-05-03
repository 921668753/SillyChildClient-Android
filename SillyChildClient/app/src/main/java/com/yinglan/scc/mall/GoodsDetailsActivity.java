package com.yinglan.scc.mall;

import com.common.cklibrary.common.BaseActivity;
import com.yinglan.scc.R;

/**
 * 商品详情
 * Created by Admin on 2017/8/24.
 */

public class GoodsDetailsActivity extends BaseActivity implements GoodsDetailsContract.View {

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_goodsdetails);
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        super.initData();
        mPresenter = new GoodsDetailsPresenter(this);
    }

    /**
     * 初始化控件
     */
    @Override
    public void initWidget() {
        super.initWidget();
    }

    @Override
    public void setPresenter(GoodsDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {

    }

    @Override
    public void errorMsg(String msg, int flag) {

    }
}
