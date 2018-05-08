package com.yinglan.scc.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.kymjs.common.PreferenceHelper;
import com.yinglan.scc.R;
import com.yinglan.scc.message.InteractiveMessageFragment;
import com.yinglan.scc.message.SystemMessageFragment;

/**
 * 消息
 * Created by Admin on 2017/8/10.
 */

public class MessageFragment extends BaseFragment {

    private MainActivity aty;

    @BindView(id = R.id.tv_interactiveMessage, click = true)
    private TextView tv_interactiveMessage;

    @BindView(id = R.id.tv_systemMessage, click = true)
    private TextView tv_systemMessage;

    private BaseFragment contentFragment;
    private BaseFragment contentFragment1;


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
        contentFragment = new InteractiveMessageFragment();
        contentFragment1 = new SystemMessageFragment();
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
        } else {
            chageIcon = 20;
            cleanColors(20);
            changeFragment(contentFragment);
        }
    }


    public void changeFragment(BaseFragment targetFragment) {
        super.changeFragment(R.id.main_content, targetFragment);
    }


    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_interactiveMessage:
                chageIcon = 20;
                cleanColors(20);
                changeFragment(contentFragment);
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMessageFragment", 20);
                // ViewInject.toast(chageIcon + "");
                break;
            case R.id.tv_systemMessage:
                chageIcon = 21;
                cleanColors(21);
                changeFragment(contentFragment1);
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMessageFragment", 21);
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
        tv_interactiveMessage.setTextColor(getResources().getColor(R.color.titletextcolors));
        tv_systemMessage.setTextColor(getResources().getColor(R.color.titletextcolors));
        if (chageIcon == 20) {
            tv_interactiveMessage.setTextColor(getResources().getColor(R.color.loginBackgroundColors));
        } else if (chageIcon == 21) {
            tv_systemMessage.setTextColor(getResources().getColor(R.color.loginBackgroundColors));
        } else {
            tv_interactiveMessage.setTextColor(getResources().getColor(R.color.loginBackgroundColors));
        }
    }

}
