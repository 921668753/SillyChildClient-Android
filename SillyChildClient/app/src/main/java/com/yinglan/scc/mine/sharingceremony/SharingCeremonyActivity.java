package com.yinglan.scc.mine.sharingceremony;

import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.PreferenceHelper;
import com.yinglan.scc.R;
import com.yinglan.scc.constant.URLConstants;
import com.yinglan.scc.dialog.VIPPermissionsDialog;
import com.yinglan.scc.entity.main.UserInfoBean;
import com.yinglan.scc.loginregister.LoginActivity;
import com.yinglan.scc.main.MineContract;
import com.yinglan.scc.main.MinePresenter;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.utils.Log;

/**
 * 分享有礼
 * Created by Administrator on 2017/9/2.
 */

public class SharingCeremonyActivity extends BaseActivity implements MineContract.View {

//    @BindView(id = R.id.tv_shardecode)
//    private TextView tv_shardecode;
//
//    @BindView(id = R.id.tv_rules)
//    private TextView tv_rules;

//    @BindView(id = R.id.ll_loginweixin, click = true)
//    private LinearLayout ll_loginweixin;
    //    @BindView(id = R.id.ll_loginpengyouquan, click = true)
//    private LinearLayout ll_loginpengyouquan;
//    @BindView(id = R.id.ll_loginweibo, click = true)
//    private LinearLayout ll_loginweibo;
//    @BindView(id = R.id.ll_loginqq, click = true)
//    private LinearLayout ll_loginqq;

    private String apply_code;

    private VIPPermissionsDialog vipPermissionsDialog;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_sharingceremony);
    }

    @Override
    public void initData() {
        super.initData();
        apply_code = PreferenceHelper.readString(aty, StringConstants.FILENAME, "apply_code", null);
        if (TextUtils.isEmpty(apply_code)) {
            mPresenter = new MinePresenter(this);
            showLoadingDialog(getString(R.string.dataLoad));
        //    ((MinePresenter) mPresenter).getInfo();
        } else {
            // tv_shardecode.setText(apply_code);
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
        ActivityTitleUtils.initToolbar(aty, getString(R.string.sharingCeremony), true, R.id.titlebar);
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        if (TextUtils.isEmpty(apply_code)) {
            initDialog();
        } else {
            switch (v.getId()) {
//                case R.id.ll_loginweixin:
//                    umShare(SHARE_MEDIA.WEIXIN);
//                    break;
//                case R.id.ll_loginqq:
//                    umShare(SHARE_MEDIA.QQ);
//                    break;
            }
        }
    }

    /**
     * 直接分享
     */
    public void umShare(SHARE_MEDIA platform) {
        UMImage thumb;
        thumb = new UMImage(this, R.mipmap.android_template);
        UMWeb web = new UMWeb(URLConstants.ADDADDRESS + apply_code);
        web.setTitle(getString(R.string.sharingCeremony));//标题
        web.setThumb(thumb);  //缩略图
        web.setDescription(getString(R.string.welcomSilly));//描述
        new ShareAction(aty).setPlatform(platform)
//                .withText("hello")
                .withMedia(web)
                .setCallback(umShareListener)
                .share();
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
            showLoadingDialog(getString(R.string.shareJumpLoad));
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);
            dismissLoadingDialog();
            ViewInject.toast(getString(R.string.shareSuccess));
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            dismissLoadingDialog();
            if (t.getMessage().contains(getString(R.string.notInstalled))) {
                ViewInject.toast(getString(R.string.notInstalled1));
                return;
            }
            ViewInject.toast(getString(R.string.shareError));
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            dismissLoadingDialog();
            //  ViewInject.toast(getString(R.string.shareError));
        }
    };

    @Override
    public void setPresenter(MineContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        UserInfoBean userInfoBean = (UserInfoBean) JsonUtil.getInstance().json2Obj(success, UserInfoBean.class);
        if (userInfoBean != null) {
            //   tv_shardecode.setText(apply_code);
          //  PreferenceHelper.write(aty, StringConstants.FILENAME, "apply_code", userInfoBean.getData().getApply_code());
        } else {
            ViewInject.toast(getString(R.string.noHaveApplyCode));
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            ViewInject.toast(getString(R.string.reloginPrompting));
            PreferenceHelper.write(this, StringConstants.FILENAME, "isRefreshMineFragment", false);
            PreferenceHelper.write(this, StringConstants.FILENAME, "isReLogin", true);
            showActivity(this, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (TextUtils.isEmpty(apply_code)) {
            if (mPresenter == null) mPresenter = new MinePresenter(this);
            showLoadingDialog(getString(R.string.dataLoad));
           // ((MinePresenter) mPresenter).getInfo();
        }
    }

    private void initDialog() {
        if (vipPermissionsDialog == null) {
            vipPermissionsDialog = new VIPPermissionsDialog(aty) {
                @Override
                public void doAction() {

                }
            };
        }
        vipPermissionsDialog.show();
        vipPermissionsDialog.setContent(getString(R.string.noHaveApplyCode));
    }

}
