package com.ruitukeji.scc.homepage.chartercustom.personaltailor;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.myview.WebViewLayout;
import com.kymjs.common.StringUtils;
import com.ruitukeji.scc.R;
import com.ruitukeji.scc.entity.ScheduleDetailsBean;
import com.ruitukeji.scc.loginregister.LoginActivity;

import static com.ruitukeji.scc.constant.URLConstants.GETPRIVATEDETAIL1;

/**
 * 行程详情
 * Created by Admin on 2017/11/15.
 */

public class ScheduleDetailsActivity extends BaseActivity implements ScheduleDetailsContract.View {

    @BindView(id = R.id.web_viewlayout)
    private WebViewLayout webViewLayout;

    @BindView(id = R.id.tv_submit, click = true)
    private TextView tv_submit;
    private String air_id;
    private String customer_name = "";
    private String customer_phone = "";
    private String use_car_adult = "";
    private String use_car_children = "";
    private String req_car_level = "";
    private String req_car_seat_num = "";
    private String cost_statement = "";
    private String cost_compensation = "";
    private String tour_time = "";
    private String total_price = "";

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_formationdetails);
    }

    @Override
    public void initData() {
        super.initData();
        air_id = getIntent().getStringExtra("air_id");
        mPresenter = new ScheduleDetailsPresenter(this);
        showLoadingDialog(getString(R.string.dataLoad));
        ((ScheduleDetailsContract.Presenter) mPresenter).getPrivateDetail(air_id);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initView();
    }

    public void initView() {
        tv_submit.setText(getString(R.string.confirmOrder));
        webViewLayout.setTitleText(getString(R.string.scheduleDetails));
        webViewLayout.setBackImgResource(R.mipmap.back);
        webViewLayout.setTitleVisibility(true);
        webViewLayout.setWebViewCallBack(new WebViewLayout.WebViewCallBack() {
            @Override
            public void backOnclick() {
                ScheduleDetailsActivity.this.finish();
            }

            @Override
            public void loadFailedError() {
            }
        });
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_submit:
                Intent intent = new Intent(this, CustomizationConfirmOrderActivity.class);
                intent.putExtra("air_id", air_id);
                intent.putExtra("tour_time", tour_time);
                intent.putExtra("customer_name", customer_name);
                intent.putExtra("customer_phone", customer_phone);
                intent.putExtra("use_car_adult", use_car_adult);
                intent.putExtra("use_car_children", use_car_children);
                intent.putExtra("req_car_level", req_car_level);
                intent.putExtra("req_car_seat_num", req_car_seat_num);
                intent.putExtra("cost_statement", cost_statement);
                intent.putExtra("cost_compensation", cost_compensation);
                intent.putExtra("total_price", total_price);
                showActivity(this, intent);
                break;
        }
    }

    @Override
    public void setPresenter(ScheduleDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        ScheduleDetailsBean scheduleDetailsBean = (ScheduleDetailsBean) JsonUtil.getInstance().json2Obj(success, ScheduleDetailsBean.class);
        tour_time = scheduleDetailsBean.getResult().getTour_time();
        total_price = scheduleDetailsBean.getResult().getTotal_price();
        customer_name = scheduleDetailsBean.getResult().getCustomer_name();
        if (StringUtils.isEmpty(customer_name)) {
            customer_name = "";
        }
        customer_phone = scheduleDetailsBean.getResult().getCustomer_phone();
        if (StringUtils.isEmpty(customer_phone)) {
            customer_phone = "";
        }
        use_car_adult = scheduleDetailsBean.getResult().getUse_car_adult();
        use_car_children = scheduleDetailsBean.getResult().getUse_car_children();
        req_car_level = scheduleDetailsBean.getResult().getReq_car_level();
        if (StringUtils.isEmpty(req_car_level)) {
            req_car_level = "";
        }
        req_car_seat_num = scheduleDetailsBean.getResult().getReq_car_seat_num();
        cost_statement = scheduleDetailsBean.getResult().getCost_statement();
        cost_compensation = scheduleDetailsBean.getResult().getCost_compensation();
        String url = GETPRIVATEDETAIL1 + air_id;
        if (!StringUtils.isEmpty(url)) {
            webViewLayout.loadUrl(url);
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }
}
