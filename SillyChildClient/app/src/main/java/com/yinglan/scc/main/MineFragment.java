package com.yinglan.scc.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.yinglan.scc.R;
import com.yinglan.scc.constant.NumericConstants;
import com.yinglan.scc.dialog.VIPPermissionsDialog;
import com.yinglan.scc.entity.UserInfoBean;
import com.yinglan.scc.loginregister.LoginActivity;
import com.yinglan.scc.mine.mycollection.MyCollectionActivity;
import com.yinglan.scc.mine.myorder.MyOrderActivity;
import com.yinglan.scc.mine.mywallet.MyWalletActivity;
import com.yinglan.scc.mine.personaldata.PersonalDataActivity;
import com.yinglan.scc.mine.setup.SetUpActivity;
import com.yinglan.scc.mine.sharingceremony.SharingCeremonyActivity;
import com.yinglan.scc.mine.vipemergencycall.VipEmergencyCallActivity;
import com.yinglan.scc.utils.GlideImageLoader;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

import static android.app.Activity.RESULT_OK;
import static com.yinglan.scc.constant.NumericConstants.REQUEST_CODE;
import static com.yinglan.scc.constant.NumericConstants.STATUS;

/**
 * 个人中心
 * Created by Admin on 2017/8/10.
 */
@SuppressLint("NewApi")
public class MineFragment extends BaseFragment implements MineContract.View, View.OnScrollChangeListener, BGARefreshLayout.BGARefreshLayoutDelegate {

    private MainActivity aty;

    @BindView(id = R.id.mRefreshLayout, click = true)
    private BGARefreshLayout mRefreshLayout;


    @BindView(id = R.id.sv_mine)
    private ScrollView sv_mine;

    @BindView(id = R.id.rl_title)
    private RelativeLayout rl_title;

    @BindView(id = R.id.tv_title)
    private TextView tv_title;

    @BindView(id = R.id.tv_editData1, click = true)
    private TextView tv_editData1;

    @BindView(id = R.id.tv_divider)
    private TextView tv_divider;

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

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_mine, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new MinePresenter(this);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, false);
        mRefreshLayout.beginRefreshing();
        sv_mine.setOnScrollChangeListener(this);
    }


    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_editData:
                Intent intent = new Intent(aty, PersonalDataActivity.class);
                // 获取内容
//                intent.putExtra("selectCity", cityName.getName());
//                intent.putExtra("selectCityId", cityName.getId());
//                intent.putExtra("selectCountry", getString(R.string.china));
//                intent.putExtra("selectCountryId", cityName.getCountry_id());
                // 设置结果 结果码，一个数据
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.tv_editData1:
                Intent personalDataIntent = new Intent(aty, PersonalDataActivity.class);
                // 获取内容
//                intent.putExtra("selectCity", cityName.getName());
//                intent.putExtra("selectCityId", cityName.getId());
//                intent.putExtra("selectCountry", getString(R.string.china));
//                intent.putExtra("selectCountryId", cityName.getCountry_id());
                // 设置结果 结果码，一个数据
                startActivityForResult(personalDataIntent, REQUEST_CODE);
                break;
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
        mPresenter = presenter;
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
        dismissLoadingDialog();
        if (isLogin(msg)) {
            initDefaultInfo();
            return;
        }
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
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((MinePresenter) mPresenter).getInfo();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    @Override
    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if (scrollY <= 0) {
            rl_title.setBackgroundColor(Color.TRANSPARENT);
            //                          设置文字颜色，黑色，加透明度
            tv_title.setTextColor(Color.TRANSPARENT);
            tv_editData1.setTextColor(Color.TRANSPARENT);
            tv_divider.setBackgroundColor(Color.TRANSPARENT);
            Log.e("111", "y <= 0");
        } else if (scrollY > 0 && scrollY <= 200) {
            float scale = (float) scrollY / 200;
            float alpha = (255 * scale);
            // 只是layout背景透明(仿知乎滑动效果)白色透明
            rl_title.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
            //                          设置文字颜色，黑色，加透明度
            tv_title.setTextColor(Color.argb((int) alpha, 0, 0, 0));
            tv_editData1.setTextColor(Color.argb((int) alpha, 0, 0, 0));
            tv_divider.setBackgroundColor(Color.argb((int) alpha, 0, 0, 0));
            Log.e("111", "y > 0 && y <= imageHeight");
        } else {
//                          白色不透明
            rl_title.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
            //                          设置文字颜色
            //黑色
            tv_title.setTextColor(Color.argb((int) 255, 0, 0, 0));
            tv_editData1.setTextColor(Color.argb((int) 255, 0, 0, 0));
            tv_divider.setBackgroundColor(getResources().getColor(R.color.dividercolors2));
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {// 如果等于1
            String selectCity = data.getStringExtra("selectCity");
            int selectCityId = data.getIntExtra("selectCityId", 0);
            String selectCountry = data.getStringExtra("selectCountry");
            int selectCountryId = data.getIntExtra("selectCountryId", 0);
//            Intent intent = new Intent();
//            // 获取内容
//            intent.putExtra("selectCity", selectCity);
//            intent.putExtra("selectCityId", selectCityId);
//            intent.putExtra("selectCountry", selectCountry);
//            intent.putExtra("selectCountryId", selectCountryId);
//            // 设置结果 结果码，一个数据
//            setResult(RESULT_OK, intent);
            // 结束该activity 结束之后，前面的activity才可以处理结果
        }
    }

    /**
     * 在接收消息的时候，选择性接收消息：
     */
    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusLoginEvent")) {
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    sv_mine.scrollTo(0, 1);
//                    mRefreshLayout.beginRefreshing();
//                }
//            }, 600);
        } else if (((String) msgEvent.getData()).equals("RxBusAvatarEvent")) {
            String avatar = PreferenceHelper.readString(aty, StringConstants.FILENAME, "avatar", "");
            if (!StringUtils.isEmpty(avatar)) {
//                GlideImageLoader.glideLoader(this, avatar + "?imageView2/1/w/70/h/70", img_headPortrait, 0);
//                GlideImageLoader.glideLoader(this, avatar + "?imageView2/1/w/70/h/70", img_headPortrait1, 0);
            }
        }
    }

}
