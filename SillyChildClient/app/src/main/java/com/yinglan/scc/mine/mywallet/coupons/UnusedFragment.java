package com.yinglan.scc.mine.mywallet.coupons;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.yinglan.scc.R;
import com.yinglan.scc.adapter.CouponsAdapter;
import com.yinglan.scc.constant.NumericConstants;
import com.yinglan.scc.constant.StringNewConstants;
import com.yinglan.scc.entity.CouponsBean;
import com.yinglan.scc.loginregister.LoginActivity;

import static android.app.Activity.RESULT_OK;

/**
 * 优惠券中的未使用
 * Created by Administrator on 2017/9/2.
 */

public class UnusedFragment extends BaseFragment implements CouponsContract.View, AdapterView.OnItemClickListener {
    private CouponsActivity aty;
    private CouponsContract.Presenter mPresenter;

    @BindView(id = R.id.lv_coupons)
    private ListView lv_coupons;
    private CouponsAdapter couponsAdapter;
    private CouponsBean couponsBean;

    /**
     * 错误提示页
     */
    @BindView(id = R.id.ll_commonError)
    private LinearLayout ll_commonError;
    @BindView(id = R.id.tv_hintText, click = true)
    private TextView tv_hintText;
    private String type;
    private String total_price;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (CouponsActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_unused, null);
    }

    @Override
    protected void initData() {
        super.initData();
        type = aty.getIntent().getStringExtra("type");
        total_price = aty.getIntent().getStringExtra("total_price");
        mPresenter = new CouponsPresenter(this);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        couponsAdapter = new CouponsAdapter(aty,NumericConstants.couponUnuse);
        lv_coupons.setAdapter(couponsAdapter);
        if (!TextUtils.isEmpty(type)){
            lv_coupons.setOnItemClickListener(this);
        }
        showLoadingDialog(getString(R.string.dataLoad));
        mPresenter.getCoupons(StringNewConstants.AllXX, NumericConstants.couponUnuse + "", 0);

    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_hintText:
                if (tv_hintText.getText().toString().equals(getString(R.string.login1))) {
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "id", 0);
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "accessToken", "");
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "refreshToken", "");
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "expireTime", "0");
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "timeBefore", "0");
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", false);
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "isReLogin", true);
                    //   PreferenceHelper.write(aty, StringConstants.FILENAME, "refreshName", "getCompanyGuideMessageFragment");
                    Intent intent = new Intent(aty, LoginActivity.class);
                    aty.showActivity(aty, intent);
                    tv_hintText.setText(getString(R.string.clickRefresh));
                    break;
                }
                showLoadingDialog(getString(R.string.dataLoad));
                mPresenter.getCoupons(StringNewConstants.AllXX, NumericConstants.couponUnuse + "", 0);
                break;
        }
    }

    @Override
    public void setPresenter(CouponsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        ll_commonError.setVisibility(View.GONE);
        lv_coupons.setVisibility(View.VISIBLE);
        Log.e("优惠", "结果：" + success);
        couponsBean = (CouponsBean) JsonUtil.getInstance().json2Obj(success, CouponsBean.class);
        if (couponsBean == null) {
            errorMsg(getString(R.string.otherError), 0);
            return;
        }
        if (couponsBean.getResult() == null || couponsBean.getResult().size() == 0) {
            errorMsg(getString(R.string.serverReturnsDataNull), 0);
            return;
        }
        couponsAdapter.clear();
        couponsAdapter.addNewData(couponsBean.getResult());
        dismissLoadingDialog();

    }

    @Override
    public void errorMsg(String msg, int flag) {
        lv_coupons.setVisibility(View.GONE);
        ll_commonError.setVisibility(View.VISIBLE);
        if (msg != null && msg.equals("" + NumericConstants.TOLINGIN)) {
            dismissLoadingDialog();
            tv_hintText.setText(getString(R.string.login1));
            //       aty.showActivity(aty, LoginActivity.class);
            return;
        }
        tv_hintText.setText(msg + getString(R.string.clickRefresh));
        dismissLoadingDialog();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        CouponsBean.ResultBean resultBean = couponsAdapter.getItem(i);
        Intent intent = new Intent();
        if (type.equals("confirmOrder") && resultBean.getModel_type() == 0 && StringUtils.toDouble(total_price) >= StringUtils.toDouble(resultBean.getCondition())) {
            // 获取内容
            intent.putExtra("money", resultBean.getMoney());
            intent.putExtra("id", resultBean.getId());
        } else {
            ViewInject.toast(getString(R.string.couponCannotUsed));
            return;
        }
        // 设置结果 结果码，一个数据
        aty.setResult(RESULT_OK, intent);
        // 结束该activity 结束之后，前面的activity才可以处理结果
        aty.finish();

    }


}
