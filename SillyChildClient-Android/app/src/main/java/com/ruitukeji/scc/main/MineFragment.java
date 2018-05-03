package com.ruitukeji.scc.main;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.ruitukeji.scc.R;
import com.ruitukeji.scc.dialog.VIPPermissionsDialog;
import com.ruitukeji.scc.entity.UserInfoBean;
import com.ruitukeji.scc.loginregister.LoginActivity;
import com.ruitukeji.scc.mine.deliveryaddress.DeliveryAddressActivity;
import com.ruitukeji.scc.mine.fansattention.FansAttentionActivity;
import com.ruitukeji.scc.mine.mycollection.MyCollectionActivity;
import com.ruitukeji.scc.mine.myorder.MyOrderActivity;
import com.ruitukeji.scc.mine.myorder.orderevaluation.PostEvaluationActivity;
import com.ruitukeji.scc.mine.myrelease.MyReleaseActivity;
import com.ruitukeji.scc.mine.myshoppingcart.MyShoppingCartActivity;
import com.ruitukeji.scc.mine.mywallet.MyWalletActivity;
import com.ruitukeji.scc.mine.personaldata.PersonalDataActivity;
import com.ruitukeji.scc.mine.setup.SetUpActivity;
import com.ruitukeji.scc.mine.sharingceremony.SharingCeremonyActivity;
import com.ruitukeji.scc.mine.vipemergencycall.VipEmergencyCallActivity;
import com.ruitukeji.scc.utils.GlideImageLoader;

import java.net.URL;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by Admin on 2017/8/10.
 */

public class MineFragment extends BaseFragment implements MineContract.View, BGARefreshLayout.BGARefreshLayoutDelegate {

    private MainActivity aty;

    @BindView(id = R.id.mRefreshLayout, click = true)
    private BGARefreshLayout mRefreshLayout;

    @BindView(id = R.id.iv_minetouxiang, click = true)
    private ImageView iv_minetouxiang;

    @BindView(id = R.id.iv_minetype)
    private ImageView iv_minetype;

    @BindView(id = R.id.tv_mineusername, click = true)
    private TextView tv_mineusername;

    @BindView(id = R.id.iv_minesexicon)
    private ImageView iv_minesexicon;

    @BindView(id = R.id.tv_address)
    private TextView tv_address;

    @BindView(id = R.id.ll_fensi, click = true)
    private LinearLayout ll_fensi;
    @BindView(id = R.id.tv_minefsnum)
    private TextView tv_minefsnum;

    @BindView(id = R.id.ll_guanzhu, click = true)
    private LinearLayout ll_guanzhu;
    @BindView(id = R.id.tv_minegznum)
    private TextView tv_minegznum;

    @BindView(id = R.id.tv_minebznum)
    private TextView tv_minebznum;

    @BindView(id = R.id.tv_minebscnum)
    private TextView tv_minebscnum;

    @BindView(id = R.id.tv_minemoney, click = true)
    private TextView tv_minemoney;

    @BindView(id = R.id.ll_minewallet, click = true)
    private LinearLayout ll_minewallet;

    @BindView(id = R.id.ll_mineshopping, click = true)
    private LinearLayout ll_mineshopping;

    @BindView(id = R.id.ll_mineorder, click = true)
    private LinearLayout ll_mineorder;

    @BindView(id = R.id.ll_minerelease, click = true)
    private LinearLayout ll_minerelease;

    @BindView(id = R.id.ll_minecollection, click = true)
    private LinearLayout ll_minecollection;

    @BindView(id = R.id.ll_mineshare, click = true)
    private LinearLayout ll_mineshare;

    @BindView(id = R.id.ll_mineaddress, click = true)
    private LinearLayout ll_mineaddress;

    @BindView(id = R.id.ll_minesetup, click = true)
    private LinearLayout ll_minesetup;

    @BindView(id = R.id.ll_minevipphone, click = true)
    private LinearLayout ll_minevipphone;

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
            case R.id.tv_minemoney:
            case R.id.ll_minewallet:
                aty.showActivity(aty, MyWalletActivity.class);
                break;
            case R.id.ll_mineorder:
                aty.showActivity(aty, MyOrderActivity.class);
                break;
            case R.id.ll_minerelease:
                aty.showActivity(aty, MyReleaseActivity.class);
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
            case R.id.ll_minevipphone:
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
            case R.id.tv_mineusername:
                if (!TextUtils.isEmpty(tv_mineusername.getText().toString()) && tv_mineusername.getText().toString().equals(getString(R.string.loginOrRegister))) {
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", false);
                    aty.showActivity(aty, LoginActivity.class);
                }
                break;
            case R.id.ll_fensi:
                intentjump = new Intent(aty, FansAttentionActivity.class);
                intentjump.putExtra("chageIcon", 0);
                aty.showActivity(aty, intentjump);
                break;
            case R.id.ll_guanzhu:
                intentjump = new Intent(aty, FansAttentionActivity.class);
                intentjump.putExtra("chageIcon", 1);
                aty.showActivity(aty, intentjump);
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
        if (aty.getChageIcon()==4){
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
                if (StringUtils.toDouble(user_money) != 0) {
                    tv_minemoney.setVisibility(View.VISIBLE);
                    tv_minemoney.setText(user_money_fmt);
                } else {
                    tv_minemoney.setVisibility(View.GONE);
                }
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
        mPresenter = (MinePresenter)presenter;
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
            tv_mineusername.setText(userInfoBean.getResult().getNickname());
            address = "";
            if (!TextUtils.isEmpty(userInfoBean.getResult().getCountry())) {
                address += userInfoBean.getResult().getCountry() + "•";
            }
            if (!TextUtils.isEmpty(userInfoBean.getResult().getCity())) {
                address += userInfoBean.getResult().getCity();
            }
            tv_address.setText(address);

            switch (userInfoBean.getResult().getSex()) {
                case 1:
                    iv_minesexicon.setVisibility(View.VISIBLE);
                    iv_minesexicon.setImageResource(R.mipmap.minenanxxx);
                    break;
                case 2:
                    iv_minesexicon.setVisibility(View.VISIBLE);
                    iv_minesexicon.setImageResource(R.mipmap.minenvxxx);
                    break;
                case 0:
                    iv_minesexicon.setVisibility(View.GONE);
                    break;
            }
            iv_minetype.setVisibility(View.VISIBLE);
            switch (userInfoBean.getResult().getLevel()) {
                case 1:
                    iv_minetype.setImageResource(R.mipmap.minepthyxxx);
                    break;
                case 2:
                    iv_minetype.setImageResource(R.mipmap.mineviphyxxx);
                    break;
                case 3:
                    iv_minetype.setImageResource(R.mipmap.minezjthyxxx);
                    break;
                case 4:
                    iv_minetype.setImageResource(R.mipmap.minegjhyxxx);
                    break;
                case 5:
                    iv_minetype.setImageResource(R.mipmap.minecjvipxxx);
                    break;
                case 6:
                    iv_minetype.setImageResource(R.mipmap.minezzvipxxx);
                    break;
                default:
                    iv_minetype.setVisibility(View.INVISIBLE);
                    break;
            }

            if (TextUtils.isEmpty(userInfoBean.getResult().getHead_pic())) {
                iv_minetouxiang.setImageResource(R.mipmap.avatar);
                headpic = null;
            } else if (!userInfoBean.getResult().getHead_pic().equals(headpic)) {
                headpic = userInfoBean.getResult().getHead_pic();
                GlideImageLoader.glideLoader(aty, headpic, iv_minetouxiang, 0, R.mipmap.avatar);
            }

            tv_minefsnum.setText(StringUtils.toInt(userInfoBean.getResult().getFans_num()) + "");
            tv_minegznum.setText(StringUtils.toInt(userInfoBean.getResult().getAttention_num()) + "");
            tv_minebznum.setText(StringUtils.toInt(userInfoBean.getResult().getGood_num()) + "");
            tv_minebscnum.setText(StringUtils.toInt(userInfoBean.getResult().getCollection_num()) + "");

            if (StringUtils.toDouble(userInfoBean.getResult().getUser_money()) != 0) {
                tv_minemoney.setVisibility(View.VISIBLE);
                tv_minemoney.setText(userInfoBean.getResult().getUser_money_fmt());
            } else {
                tv_minemoney.setVisibility(View.GONE);
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
        tv_mineusername.setText(getString(R.string.loginOrRegister));
        tv_address.setText("");
        iv_minesexicon.setVisibility(View.GONE);
        iv_minetype.setVisibility(View.INVISIBLE);
        iv_minetouxiang.setImageResource(R.mipmap.avatar);
        headpic = null;
        tv_minefsnum.setText("0");
        tv_minegznum.setText("0");
        tv_minebznum.setText("0");
        tv_minebscnum.setText("0");
        tv_minemoney.setVisibility(View.GONE);
        userInfoBean = null;
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        //    PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", false);
        showLoadingDialog(getString(R.string.dataLoad));
        ((MinePresenter)mPresenter).getInfo();
        mRefreshLayout.endRefreshing();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }
}
