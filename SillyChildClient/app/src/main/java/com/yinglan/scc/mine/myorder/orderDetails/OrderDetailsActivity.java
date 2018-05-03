package com.yinglan.scc.mine.myorder.orderDetails;

import android.view.View;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.myview.ChildLiistView;
import com.kymjs.common.PreferenceHelper;
import com.yinglan.scc.R;
import com.yinglan.scc.adapter.OrderDetailGoodAdapter;
import com.yinglan.scc.entity.OrderDetailBean;
import com.yinglan.scc.entity.UserInfoBean;
import com.yinglan.scc.loginregister.LoginActivity;

/**
 * 我的订单---订单详情
 * Created by Administrator on 2017/9/2.
 */

public class OrderDetailsActivity extends BaseActivity implements OrderDetailsContract.View{

    private OrderDetailsContract.Presenter mPersenter;

    @BindView(id=R.id.tv_ordercode)
    private TextView tv_ordercode;

    @BindView(id=R.id.tv_ordertype)
    private TextView tv_ordertype;

    @BindView(id=R.id.tv_orderdatetime)
    private TextView tv_orderdatetime;

    @BindView(id=R.id.tv_tagpersonname)
    private TextView tv_tagpersonname;

    @BindView(id=R.id.tv_tagpersonphone)
    private TextView tv_tagpersonphone;

    @BindView(id=R.id.tv_tagpersonaddress)
    private TextView tv_tagpersonaddress;

    @BindView(id=R.id.lv_shopgoods)
    private ChildLiistView lv_shopgoods;

    @BindView(id=R.id.tv_paytype)
    private TextView tv_paytype;

    @BindView(id=R.id.tv_goodsmoney)
    private TextView tv_goodsmoney;

    @BindView(id=R.id.tv_freightmoney)
    private TextView tv_freightmoney;

    @BindView(id=R.id.tv_paymoney)
    private TextView tv_paymoney;

    @BindView(id=R.id.tv_leftbtn,click = true)
    private TextView tv_leftbtn;

    @BindView(id=R.id.tv_rightbtn,click = true)
    private TextView tv_rightbtn;
    private OrderDetailBean orderDetailBean;
    private OrderDetailGoodAdapter madapter;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_orderdetails);
    }

    @Override
    public void initData() {
        super.initData();
        mPersenter=new OrderDetailsPresenter(this);
        madapter=new OrderDetailGoodAdapter(this);
        lv_shopgoods.setAdapter(madapter);

    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        showLoadingDialog(getString(R.string.dataLoad));
        mPersenter.getOrderDetails();

    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.orderDetails), true, R.id.titlebar);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()){
            case R.id.tv_leftbtn:

                break;
            case R.id.tv_rightbtn:

                break;
        }
    }

    @Override
    public void setPresenter(OrderDetailsContract.Presenter presenter) {
        mPersenter=presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        orderDetailBean = (OrderDetailBean) JsonUtil.getInstance().json2Obj(success, OrderDetailBean.class);
        dismissLoadingDialog();

    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)){
            ViewInject.toast(getString(R.string.reloginPrompting));
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", false);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isReLogin", true);
            showActivity(this,LoginActivity.class);
            finish();
            return;
        }
        ViewInject.toast(msg);
    }
}
