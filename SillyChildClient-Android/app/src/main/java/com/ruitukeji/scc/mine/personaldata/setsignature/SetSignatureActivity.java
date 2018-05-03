package com.ruitukeji.scc.mine.personaldata.setsignature;

import android.text.TextUtils;
import android.util.TypedValue;
import android.widget.EditText;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.kymjs.common.PreferenceHelper;
import com.ruitukeji.scc.R;
import com.ruitukeji.scc.loginregister.LoginActivity;
import com.ruitukeji.scc.main.MainActivity;
import com.ruitukeji.scc.mine.personaldata.setnickname.SetNickNameContract;
import com.ruitukeji.scc.mine.personaldata.setnickname.SetNickNamePresenter;

import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 设置个性签名
 * Created by Administrator on 2017/9/2.
 */

public class SetSignatureActivity extends BaseActivity implements SetSignatureContract.View{
    private SetSignatureContract.Presenter mPresenter;

    @BindView(id = R.id.titlebar)
    private BGATitleBar titlebar;

    @BindView(id = R.id.et_signature)
    private EditText et_signature;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_setsignature);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter=new SetSignaturePresenter(this);
        String signature=getIntent().getStringExtra("signature");
        if (!TextUtils.isEmpty(signature)){
            et_signature.setText(signature);
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
        titlebar.setTitleText(R.string.personalizedSignature);
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
                mPresenter.setupInfo(et_signature.getText().toString());
            }
        };
        titlebar.setDelegate(simpleDelegate);
    }


    @Override
    public void setPresenter(SetSignatureContract.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        setResult(0,getIntent().putExtra("signature",et_signature.getText().toString()));
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
