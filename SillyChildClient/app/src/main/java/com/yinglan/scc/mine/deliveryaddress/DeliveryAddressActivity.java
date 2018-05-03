package com.yinglan.scc.mine.deliveryaddress;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.kymjs.common.PreferenceHelper;
import com.yinglan.scc.R;
import com.yinglan.scc.adapter.MineAddressAdapter;
import com.yinglan.scc.loginregister.LoginActivity;

import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 收货地址
 * Created by Administrator on 2017/9/2.
 */

public class DeliveryAddressActivity extends BaseActivity implements MineAddressContract.View,BGARefreshLayout.BGARefreshLayoutDelegate,AdapterView.OnItemClickListener,BGAOnItemChildClickListener{

    private MineAddressContract.Presenter mPresenter;

    @BindView(id=R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    @BindView(id=R.id.lv_address)
    private ListView lv_address;

    @BindView(id=R.id.tv_newaddress,click = true)
    private TextView tv_newaddress;
    private MineAddressAdapter mAdapter;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_deliveryaddress);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter=new MineAddressPresenter(this);
        mAdapter=new MineAddressAdapter(this);
        mAdapter.setOnItemChildClickListener(this);

    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        lv_address.setAdapter(mAdapter);
        lv_address.setOnItemClickListener(this);
        mAdapter.setOnItemChildClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        showActivity(this,NewAddressActivity.class);
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.deliveryAddress), true, R.id.titlebar);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //刷新地址列表
    }

    @Override
    public void setPresenter(MineAddressContract.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {

    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)){
            ViewInject.toast(getString(R.string.reloginPrompting));
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", false);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isReLogin", true);
            showActivity(this,LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position) {
        switch (childView.getId()){
            case R.id.ll_setdefault:
                //设置默认地址
                ViewInject.toast("345455");
                break;
            case R.id.ll_deliveryaddressedit:
                //编辑地址
                ViewInject.toast("345455");
                break;
            case R.id.ll_deliveryaddressdelete:
                //删除地址
                ViewInject.toast("345455");
                break;

        }
    }
}
