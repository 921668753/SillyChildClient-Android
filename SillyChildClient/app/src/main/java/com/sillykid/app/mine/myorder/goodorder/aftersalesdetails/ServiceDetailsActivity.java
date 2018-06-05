package com.sillykid.app.mine.myorder.goodorder.aftersalesdetails;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.sillykid.app.R;

/**
 * 服务详情
 */
public class ServiceDetailsActivity extends BaseActivity implements ServiceDetailsContract.View {


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_servicedetails);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new ServiceDetailsPresenter(this);
//        order_id = getIntent().getStringExtra("order_id");
//        good_id = getIntent().getStringExtra("good_id");
        showLoadingDialog(getString(R.string.dataLoad));
        //  ((ApplyAfterSalesContract.Presenter)mPresenter).getMyWallet();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
    }


    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.serviceDetails), true, R.id.titlebar);
    }


    @Override
    public void setPresenter(ServiceDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {

    }

    @Override
    public void errorMsg(String msg, int flag) {

    }
}
