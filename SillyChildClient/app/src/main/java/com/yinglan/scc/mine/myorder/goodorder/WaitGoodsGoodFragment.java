package com.yinglan.scc.mine.myorder.goodorder;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.kymjs.common.PreferenceHelper;
import com.yinglan.scc.R;
import com.yinglan.scc.adapter.CharterOrderAdapter;
import com.yinglan.scc.adapter.mine.myorder.GoodsOrderAdapter;
import com.yinglan.scc.constant.NumericConstants;
import com.yinglan.scc.entity.CharterOrderBean;
import com.yinglan.scc.loginregister.LoginActivity;
import com.yinglan.scc.mine.myorder.MyOrderActivity;
import com.yinglan.scc.mine.myorder.charterorder.CharterOrderContract;
import com.yinglan.scc.mine.myorder.charterorder.CharterOrderPresenter;

import java.util.List;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 我的订单----商品订单---待收货
 * Created by Administrator on 2017/9/2.
 */

public class WaitGoodsGoodFragment extends BaseFragment implements AdapterView.OnItemClickListener, BGARefreshLayout.BGARefreshLayoutDelegate, CharterOrderContract.View {

    private MyOrderActivity aty;

    private GoodsOrderAdapter mAdapter;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    @BindView(id = R.id.lv_order)
    private ListView lv_order;

    /**
     * 错误提示页
     */
    @BindView(id = R.id.ll_commonError)
    private LinearLayout ll_commonError;

    @BindView(id = R.id.img_err)
    private ImageView img_err;

    @BindView(id = R.id.tv_hintText)
    private TextView tv_hintText;

    @BindView(id = R.id.tv_button, click = true)
    private TextView tv_button;

    /**
     * 当前页码
     */
    private int mMorePageNumber = NumericConstants.START_PAGE_NUMBER;

    /**
     * 是否加载更多
     */
    private boolean isShowLoadingMore = false;

    private CharterOrderBean charterOrderBean;

    private List<CharterOrderBean.ResultBean.ListBean> databean;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MyOrderActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_allgood, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new CharterOrderPresenter(this);
        mAdapter = new GoodsOrderAdapter(aty);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        lv_order.setAdapter(mAdapter);
        lv_order.setOnItemClickListener(this);
//        showLoadingDialog(getString(R.string.dataLoad));
//        mPresenter.getChartOrder(NumericConstants.NoPay+"");
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_button:
                if (tv_button.getText().toString().contains(getString(R.string.retry))) {
                    mRefreshLayout.beginRefreshing();
                    return;
                }
                aty.showActivity(aty, LoginActivity.class);
                break;
        }
    }


    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        //  ((MyCollectionContract.Presenter) mPresenter).getFavoriteGoodList(mMorePageNumber);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endLoadingMore();
        if (!isShowLoadingMore) {
            ViewInject.toast(getString(R.string.noMoreData));
            return false;
        }
        mMorePageNumber++;
        showLoadingDialog(getString(R.string.dataLoad));
        // ((MyCollectionContract.Presenter) mPresenter).getFavoriteGoodList(mMorePageNumber);
        return true;
    }

    @Override
    public void setPresenter(CharterOrderContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        Intent intent = new Intent(aty, GoodsDetailsActivity.class);
//        intent.putExtra("good_id", mAdapter.getItem(position).getGoods_id());
//        showActivity(aty, intent);
    }

    @Override
    public void getSuccess(String success, int flag) {
        isShowLoadingMore = true;
        mRefreshLayout.setPullDownRefreshEnable(true);
        ll_commonError.setVisibility(View.GONE);
        mRefreshLayout.setVisibility(View.VISIBLE);
//        MyCollectionBean myCollectionBean = (MyCollectionBean) JsonUtil.getInstance().json2Obj(success, MyCollectionBean.class);
//        if (myCollectionBean.getData() == null && mMorePageNumber == NumericConstants.START_PAGE_NUMBER ||
//                myCollectionBean.getData().size() <= 0 && mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
//            errorMsg(getString(R.string.noCollectedGoods), 1);
//            return;
//        } else if (myCollectionBean.getData() == null && mMorePageNumber > NumericConstants.START_PAGE_NUMBER ||
//                myCollectionBean.getData().size() <= 0 && mMorePageNumber > NumericConstants.START_PAGE_NUMBER) {
//            ViewInject.toast(getString(R.string.noMoreData));
//            isShowLoadingMore = false;
//            dismissLoadingDialog();
//            mRefreshLayout.endLoadingMore();
//            return;
//        }
//        if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
//            mRefreshLayout.endRefreshing();
//            mAdapter.clear();
//            mAdapter.addNewData(myCollectionBean.getData());
//        } else {
//            mRefreshLayout.endLoadingMore();
//            mAdapter.addMoreData(myCollectionBean.getData());
//        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        //  if (flag == 0) {
        isShowLoadingMore = false;
        if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
            mRefreshLayout.endRefreshing();
        } else {
            mRefreshLayout.endLoadingMore();
        }
        mRefreshLayout.setPullDownRefreshEnable(false);
        mRefreshLayout.setVisibility(View.GONE);
        ll_commonError.setVisibility(View.VISIBLE);
        tv_hintText.setVisibility(View.VISIBLE);
        tv_button.setVisibility(View.VISIBLE);
        if (isLogin(msg)) {
            img_err.setImageResource(R.mipmap.no_login);
            tv_hintText.setVisibility(View.GONE);
            tv_button.setText(getString(R.string.login));
            // ViewInject.toast(getString(R.string.reloginPrompting));
            aty.showActivity(aty, LoginActivity.class);
            return;
        } else if (msg.contains(getString(R.string.checkNetwork))) {
            img_err.setImageResource(R.mipmap.no_network);
            tv_hintText.setText(msg);
            tv_button.setText(getString(R.string.retry));
        } else if (msg.contains(getString(R.string.noAddress))) {
            img_err.setImageResource(R.mipmap.no_data);
            tv_hintText.setText(msg);
            tv_button.setVisibility(View.GONE);
        } else {
            img_err.setImageResource(R.mipmap.no_data);
            tv_hintText.setText(msg);
            tv_button.setText(getString(R.string.retry));
        }
    }
}
