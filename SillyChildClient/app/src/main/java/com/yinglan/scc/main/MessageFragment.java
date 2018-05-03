package com.yinglan.scc.main;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.kymjs.common.PreferenceHelper;
import com.yinglan.scc.R;
import com.yinglan.scc.message.CompanyGuideMessageFragment;
import com.yinglan.scc.message.HomestayMessageFragment;
import com.yinglan.scc.message.ShopMessageFragment;
import com.yinglan.scc.message.SystemMessageFragment;

/**
 * 消息
 * Created by Admin on 2017/8/10.
 */

public class MessageFragment extends BaseFragment {

    private MainActivity aty;

    @BindView(id = R.id.ll_system, click = true)
    private LinearLayout ll_system;
    @BindView(id = R.id.tv_system)
    private TextView tv_system;
    @BindView(id = R.id.tv_system1)
    private TextView tv_system1;

    @BindView(id = R.id.ll_companyGuide, click = true)
    private LinearLayout ll_companyGuide;
    @BindView(id = R.id.tv_companyGuide)
    private TextView tv_companyGuide;
    @BindView(id = R.id.tv_companyGuide1)
    private TextView tv_companyGuide1;

    @BindView(id = R.id.ll_homestay, click = true)
    private LinearLayout ll_homestay;
    @BindView(id = R.id.tv_homestay)
    private TextView tv_homestay;
    @BindView(id = R.id.tv_homestay1)
    private TextView tv_homestay1;


    @BindView(id = R.id.ll_shop, click = true)
    private LinearLayout ll_shop;
    @BindView(id = R.id.tv_shop)
    private TextView tv_shop;
    @BindView(id = R.id.tv_shop1)
    private TextView tv_shop1;

    private BaseFragment contentFragment;
    private BaseFragment contentFragment1;
    private BaseFragment contentFragment2;
    private BaseFragment contentFragment3;


    /**
     * 用来表示移动的Fragment
     */
    private int chageIcon;


    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_message, null);
    }

    @Override
    protected void initData() {
        super.initData();
        contentFragment = new SystemMessageFragment();
        contentFragment1 = new CompanyGuideMessageFragment();
        contentFragment2 = new HomestayMessageFragment();
        contentFragment3 = new ShopMessageFragment();
        chageIcon = aty.getIntent().getIntExtra("chageIcon", 20);
    }


    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        if (chageIcon == 20) {
            chageIcon = 20;
            cleanColors(20);
            changeFragment(contentFragment);
        } else if (chageIcon == 21) {
            chageIcon = 21;
            cleanColors(21);
            changeFragment(contentFragment1);
        } else if (chageIcon == 22) {
            chageIcon = 22;
            cleanColors(22);
            changeFragment(contentFragment2);
        } else if (chageIcon == 23) {
            chageIcon = 23;
            cleanColors(23);
            changeFragment(contentFragment3);
        } else {
            chageIcon = 20;
            cleanColors(20);
            changeFragment(contentFragment);
        }
    }


    public void changeFragment(BaseFragment targetFragment) {
        super.changeFragment(R.id.fragment_content, targetFragment);
    }


    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_system:
                chageIcon = 20;
                cleanColors(20);
                changeFragment(contentFragment);
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMessageFragment", 20);
                // ViewInject.toast(chageIcon + "");
                break;
            case R.id.ll_companyGuide:
                chageIcon = 21;
                cleanColors(21);
                changeFragment(contentFragment1);
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMessageFragment", 21);
                break;
            case R.id.ll_homestay:
                chageIcon = 22;
                cleanColors(22);
                changeFragment(contentFragment2);
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMessageFragment", 22);
                break;
            case R.id.ll_shop:
                chageIcon = 23;
                cleanColors(23);
                changeFragment(contentFragment3);
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMessageFragment", 23);
                break;
            default:
                break;
        }
    }

    /**
     * 当通过changeFragment()显示时会被调用(类似于onResume)
     */
    @Override
    public void onChange() {
        super.onChange();

    }

    /**
     * 清除颜色，并添加颜色
     */
    @SuppressWarnings("deprecation")
    public void cleanColors(int chageIcon) {
        tv_system.setTextColor(getResources().getColor(R.color.messagetopbarColors));
        tv_system1.setBackgroundColor(Color.WHITE);
        tv_companyGuide.setTextColor(getResources().getColor(R.color.messagetopbarColors));
        tv_companyGuide1.setBackgroundColor(Color.WHITE);
        tv_homestay.setTextColor(getResources().getColor(R.color.messagetopbarColors));
        tv_homestay1.setBackgroundColor(Color.WHITE);
        tv_shop.setTextColor(getResources().getColor(R.color.messagetopbarColors));
        tv_shop1.setBackgroundColor(Color.WHITE);
        if (chageIcon == 20) {
            tv_system.setTextColor(getResources().getColor(R.color.greenColors));
            tv_system1.setBackgroundResource(R.color.greenColors);
        } else if (chageIcon == 21) {
            tv_companyGuide.setTextColor(getResources().getColor(R.color.greenColors));
            tv_companyGuide1.setBackgroundResource(R.color.greenColors);
        } else if (chageIcon == 22) {
            tv_homestay.setTextColor(getResources().getColor(R.color.greenColors));
            tv_homestay1.setBackgroundResource(R.color.greenColors);
        } else if (chageIcon == 23) {
            tv_shop.setTextColor(getResources().getColor(R.color.greenColors));
            tv_shop1.setBackgroundResource(R.color.greenColors);
        } else {
            tv_system.setTextColor(getResources().getColor(R.color.greenColors));
            tv_system1.setBackgroundResource(R.color.greenColors);
        }
    }

}
