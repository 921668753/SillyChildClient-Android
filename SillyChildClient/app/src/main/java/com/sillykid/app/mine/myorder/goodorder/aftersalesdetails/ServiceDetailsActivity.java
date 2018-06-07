package com.sillykid.app.mine.myorder.goodorder.aftersalesdetails;

import android.widget.ImageView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.sillykid.app.R;
import com.sillykid.app.entity.mine.myorder.goodorder.aftersalesdetails.ServiceDetailsBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.GlideImageLoader;

/**
 * 服务详情
 */
public class ServiceDetailsActivity extends BaseActivity implements ServiceDetailsContract.View {

    /**
     * 平台
     */
    @BindView(id = R.id.img_platform)
    private ImageView img_platform;

    @BindView(id = R.id.tv_refundSuccess)
    private TextView tv_refundSuccess;

    @BindView(id = R.id.tv_refundSuccessTime)
    private TextView tv_refundSuccessTime;

    @BindView(id = R.id.tv_remark)
    private TextView tv_remark;

    /**
     * 商家端
     */
    @BindView(id = R.id.img_merchants)
    private ImageView img_merchants;

    @BindView(id = R.id.tv_merchantsApplyRefund)
    private TextView tv_merchantsApplyRefund;

    @BindView(id = R.id.tv_merchantsApplyRefundTime)
    private TextView tv_merchantsApplyRefundTime;

    @BindView(id = R.id.tv_merchantsRemark)
    private TextView tv_merchantsRemark;

    /**
     * 用户
     */
    @BindView(id = R.id.img_users)
    private ImageView img_users;

    @BindView(id = R.id.tv_usersApplyRefundGoods)
    private TextView tv_usersApplyRefundGoods;

    @BindView(id = R.id.tv_usersApplyRefundGoodsTime)
    private TextView tv_usersApplyRefundGoodsTime;

    @BindView(id = R.id.tv_refundAmount)
    private TextView tv_refundAmount;

    @BindView(id = R.id.tv_refundReason)
    private TextView tv_refundReason;

    @BindView(id = R.id.tv_problemDescription)
    private TextView tv_problemDescription;

    private String order_id = "";


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_servicedetails);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new ServiceDetailsPresenter(this);
        order_id = getIntent().getStringExtra("order_id");
//        good_id = getIntent().getStringExtra("good_id");
        showLoadingDialog(getString(R.string.dataLoad));
        ((ServiceDetailsContract.Presenter) mPresenter).getSellBackService(order_id);
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
        dismissLoadingDialog();
        ServiceDetailsBean serviceDetailsBean = (ServiceDetailsBean) JsonUtil.getInstance().json2Obj(success, ServiceDetailsBean.class);

        GlideImageLoader.glideOrdinaryLoader(this, serviceDetailsBean.getData().getSHZ().getStore_logo(), img_platform, R.mipmap.placeholderfigure1);

        if (serviceDetailsBean.getData().getSHZ().getRefund_status() == 0) {
            tv_refundSuccess.setText(serviceDetailsBean.getData().getMember().getRemark() + getString(R.string.applicationSuccessful));
        } else {
            tv_refundSuccess.setText(serviceDetailsBean.getData().getMember().getRemark() + getString(R.string.applyFailure));
        }

        tv_refundSuccessTime.setText(serviceDetailsBean.getData().getSHZ().getRefund_time());

        tv_remark.setText(serviceDetailsBean.getData().getSHZ().getRefund_remark());

        GlideImageLoader.glideOrdinaryLoader(this, serviceDetailsBean.getData().getStore().getStore_logo(), img_merchants, R.mipmap.placeholderfigure1);

        if (serviceDetailsBean.getData().getSHZ().getRefund_status() == 0) {
            tv_merchantsApplyRefund.setText(getString(R.string.merchantsApplyRefund) + serviceDetailsBean.getData().getMember().getRemark() + getString(R.string.apply));
        } else {
            tv_merchantsApplyRefund.setText(getString(R.string.merchantsApplyRefund1) + serviceDetailsBean.getData().getMember().getRemark() + getString(R.string.apply));
        }

        tv_merchantsApplyRefundTime.setText(serviceDetailsBean.getData().getStore().getCreate_time());

        tv_merchantsRemark.setText(serviceDetailsBean.getData().getStore().getCreate_remark());

        GlideImageLoader.glideOrdinaryLoader(this, serviceDetailsBean.getData().getMember().getFace(), img_users, R.mipmap.placeholderfigure1);

        if (serviceDetailsBean.getData().getSHZ().getRefund_status() == 0) {
            tv_usersApplyRefundGoods.setText(getString(R.string.merchantsApplyRefund) + serviceDetailsBean.getData().getMember().getRemark() + getString(R.string.apply));
        } else {
            tv_usersApplyRefundGoods.setText(getString(R.string.merchantsApplyRefund1) + serviceDetailsBean.getData().getMember().getRemark() + getString(R.string.apply));
        }

        tv_usersApplyRefundGoodsTime.setText(serviceDetailsBean.getData().getMember().getRegtime());

        tv_refundAmount.setText(serviceDetailsBean.getData().getMember().getApply_alltotal());

        tv_refundReason.setText(serviceDetailsBean.getData().getMember().getReason());

        tv_problemDescription.setText(serviceDetailsBean.getData().getMember().getRemark());

    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            //  ViewInject.toast(getString(R.string.reloginPrompting));
            skipActivity(this, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }
}
