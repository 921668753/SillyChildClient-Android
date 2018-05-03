package com.yinglan.scc.mine.mywallet.coupons;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.PreferenceHelper;
import com.yinglan.scc.R;
import com.yinglan.scc.adapter.CouponsAdapter;
import com.yinglan.scc.constant.NumericConstants;
import com.yinglan.scc.constant.StringNewConstants;
import com.yinglan.scc.entity.CouponsBean;
import com.yinglan.scc.loginregister.LoginActivity;

/**优惠券中的已过期
 * Created by Administrator on 2017/9/2.
 */

public class ExpiredFragment extends BaseFragment implements CouponsContract.View{
    private CouponsActivity aty;
    private CouponsContract.Presenter mPresenter;

    @BindView(id=R.id.lv_coupons)
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

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (CouponsActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_unused, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter=new CouponsPresenter(this);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        couponsAdapter=new CouponsAdapter(aty,NumericConstants.couponExpored);
        lv_coupons.setAdapter(couponsAdapter);
        showLoadingDialog(getString(R.string.dataLoad));
        mPresenter.getCoupons(StringNewConstants.AllXX,NumericConstants.couponExpored+"",0);

    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()){
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
                mPresenter.getCoupons(StringNewConstants.AllXX,NumericConstants.couponExpored+"",0);
                break;
        }
    }

    @Override
    public void setPresenter(CouponsContract.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        ll_commonError.setVisibility(View.GONE);
        lv_coupons.setVisibility(View.VISIBLE);
        Log.e("优惠","结果："+success);
        couponsBean=(CouponsBean)JsonUtil.getInstance().json2Obj(success, CouponsBean.class);
        if (couponsBean==null){
            errorMsg(getString(R.string.otherError), 0);
            return;
        }
        if (couponsBean.getResult()== null || couponsBean.getResult().size() == 0) {
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
        tv_hintText.setText(msg+getString(R.string.clickRefresh));
        dismissLoadingDialog();
    }
}
