package com.ruitukeji.scc.mine.myorder;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.ruitukeji.scc.R;
import com.ruitukeji.scc.mine.myorder.charterorder.CharterOrderFragment;
import com.ruitukeji.scc.mine.myorder.goodorder.GoodOrderFragment;
import com.ruitukeji.scc.mine.myorder.homestayorder.HomestayOrderFragment;

/**
 * 我的订单
 * Created by Administrator on 2017/9/2.
 */

public class MyOrderActivity extends BaseActivity {

    @BindView(id = R.id.iv_back, click = true)
    private ImageView iv_back;

    @BindView(id = R.id.fl_myorder)
    private FrameLayout fl_myorder;

    @BindView(id = R.id.tv_goodorder)
    private TextView tv_goodorder;

    @BindView(id = R.id.v_goodorder)
    private View v_goodorder;

    @BindView(id = R.id.tv_charterorder)
    private TextView tv_charterorder;

    @BindView(id = R.id.v_charterorder)
    private View v_charterorder;

    @BindView(id = R.id.tv_homestayorder)
    private TextView tv_homestayorder;

    @BindView(id = R.id.v_homestayorder)
    private View v_homestayorder;

    @BindView(id = R.id.ll_order_good, click = true)
    private LinearLayout ll_order_good;

    @BindView(id = R.id.ll_order_charter, click = true)
    private LinearLayout ll_order_charter;

    @BindView(id = R.id.ll_order_homestay, click = true)
    private LinearLayout ll_order_homestay;

    private CharterOrderFragment charterOrderFragment;
    private GoodOrderFragment goodOrderFragment;
    private HomestayOrderFragment homestayOrderFragment;
    private int chageIcon;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_myorder);
    }

    @Override
    public void initData() {
        super.initData();
        charterOrderFragment = new CharterOrderFragment();
        homestayOrderFragment = new HomestayOrderFragment();
        goodOrderFragment = new GoodOrderFragment();
        chageIcon = getIntent().getIntExtra("chageIcon", 0);

    }

    @Override
    public void initWidget() {
        super.initWidget();
        cleanColors();
        changeFragment(charterOrderFragment);

    }

    public void changeFragment(BaseFragment targetFragment) {
        super.changeFragment(R.id.fl_myorder, targetFragment);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_order_charter:
                chageIcon = 0;
                cleanColors();
                changeFragment(charterOrderFragment);

                break;
            case R.id.ll_order_homestay:
                ViewInject.toast(getString(R.string.noDevelopment));
//                chageIcon = 1;
//                cleanColors();
//                changeFragment(homestayOrderFragment);
                break;
            case R.id.ll_order_good:
                ViewInject.toast(getString(R.string.noDevelopment));
//                chageIcon = 2;
//                cleanColors();
//                changeFragment(goodOrderFragment);

                break;
            case R.id.iv_back:
                finish();
                break;
        }

    }

    /**
     * 清除颜色，并添加颜色
     */
    @SuppressWarnings("deprecation")
    public void cleanColors() {
        tv_charterorder.setTextColor(getResources().getColor(R.color.hintColors));
        v_charterorder.setBackgroundResource(android.R.color.transparent);
        tv_homestayorder.setTextColor(getResources().getColor(R.color.hintColors));
        v_homestayorder.setBackgroundResource(android.R.color.transparent);
        tv_goodorder.setTextColor(getResources().getColor(R.color.hintColors));
        v_goodorder.setBackgroundResource(android.R.color.transparent);
        if (chageIcon == 0) {
            tv_charterorder.setTextColor(getResources().getColor(R.color.text_color));
            v_charterorder.setBackgroundResource(R.color.text_color);
        } else if (chageIcon == 1) {
            tv_homestayorder.setTextColor(getResources().getColor(R.color.text_color));
            v_homestayorder.setBackgroundResource(R.color.text_color);
        } else if (chageIcon == 2) {
            tv_goodorder.setTextColor(getResources().getColor(R.color.text_color));
            v_goodorder.setBackgroundResource(R.color.text_color);
        }
    }


}
