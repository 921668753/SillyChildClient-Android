package com.yinglan.scc.mine.personaldata.setnickname;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
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
import com.yinglan.scc.utils.SoftKeyboardUtils;

import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 设置昵称
 * Created by Administrator on 2017/9/2.
 */

public class SetNickNameActivity extends BaseActivity implements SetNickNameContract.View {

    private SetNickNameContract.Presenter mPresenter;

    @BindView(id = R.id.titlebar)
    private BGATitleBar titlebar;

    @BindView(id = R.id.et_nickname)
    private EditText et_nickname;


    @BindView(id = R.id.img_quxiao, click = true)
    private ImageView img_quxiao;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_setnickname);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new SetNickNamePresenter(this);
        String nickname = getIntent().getStringExtra("nickname");
        if (!TextUtils.isEmpty(nickname)) {
            et_nickname.setText(nickname);
        }
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        changeInputView(et_nickname, img_quxiao);
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        titlebar.setTitleText(R.string.setNickname);
        titlebar.setRightText(R.string.complete);
        titlebar.getRightCtv().setTextColor(getResources().getColor(R.color.greenColors));
        titlebar.getRightCtv().setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        BGATitleBar.SimpleDelegate simpleDelegate = new BGATitleBar.SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                super.onClickLeftCtv();
                SoftKeyboardUtils.packUpKeyboard(aty);
                aty.finish();
            }

            @Override
            public void onClickRightCtv() {
                super.onClickRightCtv();
                showLoadingDialog(getString(R.string.saveLoad));
                SoftKeyboardUtils.packUpKeyboard(aty);
                mPresenter.setupInfo(et_nickname.getText().toString());
            }
        };
        titlebar.setDelegate(simpleDelegate);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.img_quxiao:
                et_nickname.setText("");
                break;
        }
    }

    @Override
    public void setPresenter(SetNickNameContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        setResult(0, getIntent().putExtra("nickname", et_nickname.getText().toString()));
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


    /**
     * 监听EditText输入改变
     */
    @SuppressWarnings("deprecation")
    public void changeInputView(final EditText editText, final View view) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (editText.getText().toString().length() > 0 && view != null) {
                    view.setVisibility(View.VISIBLE);
                } else {
                    view.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


}
