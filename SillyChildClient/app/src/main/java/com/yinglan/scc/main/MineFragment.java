package com.yinglan.scc.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.yinglan.scc.R;
import com.yinglan.scc.dialog.VIPPermissionsDialog;
import com.yinglan.scc.entity.UserInfoBean;
import com.yinglan.scc.loginregister.LoginActivity;
import com.yinglan.scc.mine.fansattention.FansAttentionActivity;
import com.yinglan.scc.mine.mycollection.MyCollectionActivity;
import com.yinglan.scc.mine.myorder.MyOrderActivity;
import com.yinglan.scc.mine.myrelease.MyReleaseActivity;
import com.yinglan.scc.mine.mywallet.MyWalletActivity;
import com.yinglan.scc.mine.personaldata.PersonalDataActivity;
import com.yinglan.scc.mine.setup.SetUpActivity;
import com.yinglan.scc.mine.sharingceremony.SharingCeremonyActivity;
import com.yinglan.scc.mine.vipemergencycall.VipEmergencyCallActivity;
import com.yinglan.scc.utils.GlideImageLoader;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 个人中心
 * Created by Admin on 2017/8/10.
 */

public class MineFragment extends BaseFragment implements MineContract.View, BGARefreshLayout.BGARefreshLayoutDelegate {

    private MainActivity aty;

    @BindView(id = R.id.mRefreshLayout, click = true)
    private BGARefreshLayout mRefreshLayout;

    @BindView(id = R.id.tv_editData, click = true)
    private TextView tv_editData;

    @BindView(id = R.id.iv_minetouxiang, click = true)
    private ImageView iv_minetouxiang;

    @BindView(id = R.id.tv_nickname, click = true)
    private TextView tv_nickname;


    @BindView(id = R.id.tv_serialNumber)
    private TextView tv_serialNumber;

    @BindView(id = R.id.tv_synopsis)
    private TextView tv_synopsis;

    @BindView(id = R.id.ll_mineshopping, click = true)
    private LinearLayout ll_mineshopping;

    @BindView(id = R.id.ll_mineorder, click = true)
    private LinearLayout ll_mineorder;

    @BindView(id = R.id.ll_minewallet, click = true)
    private LinearLayout ll_minewallet;

    @BindView(id = R.id.ll_minecollection, click = true)
    private LinearLayout ll_minecollection;

    @BindView(id = R.id.ll_mineshare, click = true)
    private LinearLayout ll_mineshare;

    @BindView(id = R.id.ll_mineaddress, click = true)
    private LinearLayout ll_mineaddress;

    @BindView(id = R.id.ll_minesetup, click = true)
    private LinearLayout ll_minesetup;

    @BindView(id = R.id.tv_vipEmergencyCall, click = true)
    private TextView tv_vipEmergencyCall;

    private UserInfoBean userInfoBean;
    private boolean isRefreshMineFragment;
    private boolean isReLogin;
    private String headpic;
    private String address;
    private Intent intentjump;


    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_mine, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new MinePresenter(this);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, false);
        mRefreshLayout.beginRefreshing();
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);

    }


    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_mineshopping:
                ViewInject.toast(getActivity().getString(R.string.noDevelopment));
                //   aty.showActivity(aty, MyShoppingCartActivity.class);
                break;
            case R.id.ll_minewallet:
                aty.showActivity(aty, MyWalletActivity.class);
                break;
            case R.id.ll_mineorder:
                aty.showActivity(aty, MyOrderActivity.class);
                break;

            case R.id.ll_minecollection:
                aty.showActivity(aty, MyCollectionActivity.class);
                break;
            case R.id.ll_mineshare:
                aty.showActivity(aty, SharingCeremonyActivity.class);
                break;
            case R.id.ll_mineaddress:
                ViewInject.toast(getActivity().getString(R.string.noDevelopment));
                //   aty.showActivity(aty, DeliveryAddressActivity.class);
                break;
            case R.id.ll_minesetup:
                aty.showActivity(aty, SetUpActivity.class);
                break;
            case R.id.tv_vipEmergencyCall:
                if (userInfoBean == null) {
                    ViewInject.toast(getString(R.string.reloginPrompting));
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", false);
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "isReLogin", true);
                    aty.showActivity(aty, LoginActivity.class);
                } else {
                    if (userInfoBean.getResult().getLevel() > 3) {
                        aty.showActivity(aty, VipEmergencyCallActivity.class);
                    } else {
                        VIPPermissionsDialog vipPermissionsDialog = new VIPPermissionsDialog(aty) {
                            @Override
                            public void doAction() {

                            }
                        };
                        vipPermissionsDialog.show();
                    }
                }
                break;
            case R.id.iv_minetouxiang:
                if (userInfoBean != null && userInfoBean.getResult() != null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("userinfo", userInfoBean);
                    aty.showActivity(aty, PersonalDataActivity.class, bundle);
                } else {
                    ViewInject.toast(aty.getResources().getString(R.string.login1));
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", false);
                    aty.showActivity(aty, LoginActivity.class);
                }
                break;
            case R.id.tv_nickname:
                if (!TextUtils.isEmpty(tv_nickname.getText().toString()) && tv_nickname.getText().toString().equals(getString(R.string.loginOrRegister))) {
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", false);
                    aty.showActivity(aty, LoginActivity.class);
                }
                break;
        }
    }

    /**
     * 用户信息本地化
     */
    private void saveUserInfo() {
        PreferenceHelper.write(aty, StringConstants.FILENAME, "apply_code", userInfoBean.getResult().getApply_code());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "userId", userInfoBean.getResult().getUser_id());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "email", userInfoBean.getResult().getEmail());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "sex", userInfoBean.getResult().getSex());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "birthday", userInfoBean.getResult().getBirthday() + "");
        PreferenceHelper.write(aty, StringConstants.FILENAME, "user_money", userInfoBean.getResult().getUser_money());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "user_money_fmt", userInfoBean.getResult().getUser_money_fmt());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "countroy_code", userInfoBean.getResult().getCountroy_code());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "mobile", userInfoBean.getResult().getMobile());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "head_pic", userInfoBean.getResult().getHead_pic());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "nickname", userInfoBean.getResult().getNickname());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "level", userInfoBean.getResult().getLevel());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "shz_code", userInfoBean.getResult().getShz_code());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "personalized_signature", userInfoBean.getResult().getPersonalized_signature());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "fans_num", userInfoBean.getResult().getFans_num());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "attention_num", userInfoBean.getResult().getAttention_num());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "good_num", userInfoBean.getResult().getGood_num());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "collection_num", userInfoBean.getResult().getCollection_num());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "country", userInfoBean.getResult().getCountry());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "city", userInfoBean.getResult().getCity());
    }

    @Override
    public void onResume() {
        super.onResume();
        if (aty.getChageIcon() == 4) {
            isReLogin = PreferenceHelper.readBoolean(aty, StringConstants.FILENAME, "isReLogin", false);
            if (isReLogin) {
                initDefaultInfo();
                return;
            }
            isRefreshMineFragment = PreferenceHelper.readBoolean(aty, StringConstants.FILENAME, "isRefreshMineFragment", false);
            if (isRefreshMineFragment) {
                mRefreshLayout.beginRefreshing();
                return;
            }
//        boolean isRefreshMineFragment1 = PreferenceHelper.readBoolean(aty, StringConstants.FILENAME, "isRefreshMineFragment1", false);
//        if (isRefreshMineFragment1) {
//            String loginBean = PreferenceHelper.readString(aty, StringConstants.FILENAME, "loginBean");
//            getSuccess(loginBean, 0);
//            return;
//        }
            boolean isRefreshMineFragmentUserMoney = PreferenceHelper.readBoolean(aty, StringConstants.FILENAME, "isRefreshMineFragmentUserMoney", false);
            if (isRefreshMineFragmentUserMoney) {
                String user_money_fmt = PreferenceHelper.readString(aty, StringConstants.FILENAME, "user_money_fmt");
                String user_money = PreferenceHelper.readString(aty, StringConstants.FILENAME, "user_money");
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragmentUserMoney", false);
            }

//        else{
//            isRePersondata=PreferenceHelper.readBoolean(aty, StringConstants.FILENAME, "isRePersondata", false);
//            if (isRePersondata){
//                initDefaultInfo();
//                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRePersondata", false);
//            }
//        }
        }
    }

    @Override
    public void setPresenter(MineContract.Presenter presenter) {
        mPresenter = (MinePresenter) presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        mRefreshLayout.endRefreshing();
        Log.e("用户信息", "结果：" + success);
        PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", false);
        PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment1", false);
        userInfoBean = (UserInfoBean) JsonUtil.getInstance().json2Obj(success, UserInfoBean.class);
        if (userInfoBean != null && userInfoBean.getResult() != null) {
            saveUserInfo();
            tv_nickname.setText(userInfoBean.getResult().getNickname());
            address = "";
            if (!TextUtils.isEmpty(userInfoBean.getResult().getCountry())) {
                address += userInfoBean.getResult().getCountry() + "•";
            }
            if (!TextUtils.isEmpty(userInfoBean.getResult().getCity())) {
                address += userInfoBean.getResult().getCity();
            }

            if (TextUtils.isEmpty(userInfoBean.getResult().getHead_pic())) {
                iv_minetouxiang.setImageResource(R.mipmap.avatar);
                headpic = null;
            } else if (!userInfoBean.getResult().getHead_pic().equals(headpic)) {
                headpic = userInfoBean.getResult().getHead_pic();
                GlideImageLoader.glideLoader(aty, headpic, iv_minetouxiang, 0, R.mipmap.avatar);
            }

        } else {
            ViewInject.toast(getString(R.string.noHaveUserInfo));
            initDefaultInfo();
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        mRefreshLayout.endRefreshing();
        dismissLoadingDialog();
        if (isLogin(msg)) {
            initDefaultInfo();
            ViewInject.toast(getString(R.string.reloginPrompting));
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", false);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isReLogin", true);
            aty.showActivity(aty, LoginActivity.class);
            return;
        }
        initDefaultInfo();
        ViewInject.toast(msg);
    }

    /**
     * 将显示的个人信息设置到默认状态
     */
    private void initDefaultInfo() {
        PreferenceHelper.write(aty, StringConstants.FILENAME, "userId", 0);
        PreferenceHelper.write(aty, StringConstants.FILENAME, "accessToken", "");
//            PreferenceHelper.write(context, StringConstants.FILENAME, "refreshToken", "");
        PreferenceHelper.write(aty, StringConstants.FILENAME, "expireTime", "0");
        PreferenceHelper.write(aty, StringConstants.FILENAME, "timeBefore", "0");
        tv_nickname.setText(getString(R.string.loginOrRegister));
        tv_serialNumber.setVisibility(View.GONE);
        iv_minetouxiang.setImageResource(R.mipmap.avatar);
        headpic = null;
        userInfoBean = null;
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        //    PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", false);
        showLoadingDialog(getString(R.string.dataLoad));
        ((MinePresenter) mPresenter).getInfo();
        mRefreshLayout.endRefreshing();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }
}
