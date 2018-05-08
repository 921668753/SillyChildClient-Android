package com.yinglan.scc.mine.personaldata.setsex;

import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.kymjs.common.PreferenceHelper;
import com.yinglan.scc.R;
import com.yinglan.scc.loginregister.LoginActivity;
import com.yinglan.scc.main.MainActivity;

import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 设置性别
 */
public class SetSexActivity extends BaseActivity implements SetSexContract.View {

    private SetSexContract.Presenter mPresenter;

    @BindView(id = R.id.titlebar)
    private BGATitleBar titlebar;

    @BindView(id = R.id.img_nan)
    private ImageView img_nan;

    @BindView(id = R.id.img_nv)
    private ImageView img_nv;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_setsex);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new SetSexPresenter(this);
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
        titlebar.setTitleText(R.string.setSex);
        titlebar.setRightText(R.string.complete);
        titlebar.getRightCtv().setTextColor(getResources().getColor(R.color.greenColors));
        titlebar.getRightCtv().setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        BGATitleBar.SimpleDelegate simpleDelegate = new BGATitleBar.SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                super.onClickLeftCtv();
                aty.finish();
            }

            @Override
            public void onClickRightCtv() {
                super.onClickRightCtv();
                showLoadingDialog(getString(R.string.saveLoad));
                //  mPresenter.setupInfo(et_nickname.getText().toString());
            }
        };
        titlebar.setDelegate(simpleDelegate);
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.img_nan:


                break;
            case R.id.img_nv:


                break;
        }


    }

    @Override
    public void setPresenter(SetSexContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        //  setResult(0, getIntent().putExtra("nickname", et_nickname.getText().toString()));
        finish();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            ViewInject.toast(getString(R.string.reloginPrompting));
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", false);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isReLogin", true);
            finish();
            KJActivityStack.create().finishToThis(LoginActivity.class, MainActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }


}
