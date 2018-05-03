package com.ruitukeji.scc.mine.personaldata.setnickname;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.widget.EditText;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.kymjs.common.PreferenceHelper;
import com.ruitukeji.scc.R;
import com.ruitukeji.scc.loginregister.LoginActivity;
import com.ruitukeji.scc.main.MainActivity;

import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 设置昵称
 * Created by Administrator on 2017/9/2.
 */

public class SetNickNameActivity extends BaseActivity implements SetNickNameContract.View{

    private SetNickNameContract.Presenter mPresenter;

    @BindView(id = R.id.titlebar)
    private BGATitleBar titlebar;

    @BindView(id = R.id.et_nickname)
    private EditText et_nickname;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_setnickname);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter=new SetNickNamePresenter(this);
        String nickname=getIntent().getStringExtra("nickname");
        if (!TextUtils.isEmpty(nickname)){
            et_nickname.setText(nickname);
        }
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
        titlebar.setTitleText(R.string.nickname);
        titlebar.setRightText(R.string.save);
        titlebar.getRightCtv().setTextColor(getResources().getColor(R.color.greenColors));
        titlebar.getRightCtv().setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
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
                mPresenter.setupInfo(et_nickname.getText().toString());
            }
        };
        titlebar.setDelegate(simpleDelegate);
    }


    @Override
    public void setPresenter(SetNickNameContract.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        setResult(0,getIntent().putExtra("nickname",et_nickname.getText().toString()));
        finish();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)){
            ViewInject.toast(getString(R.string.reloginPrompting));
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", false);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isReLogin", true);
            finish();
            KJActivityStack.create().finishToThis(LoginActivity.class,MainActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }
}
