package com.yinglan.scc.homepage.goodslist.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.yinglan.scc.R;
import com.yinglan.scc.adapter.homepage.goodslist.shop.AllGoodsViewAdapter;
import com.yinglan.scc.constant.NumericConstants;
import com.yinglan.scc.utils.SpacesItemDecoration;
import com.yinglan.scc.homepage.goodslist.goodsdetails.GoodsDetailsActivity;
import com.yinglan.scc.loginregister.LoginActivity;

import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 全部商品
 * Created by Admin on 2017/8/21.
 */

public class AllGoodsFragment extends BaseFragment implements AllGoodsContract.View, BGARefreshLayout.BGARefreshLayoutDelegate, BGAOnRVItemClickListener {

    private ShopActivity aty;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    /**
     * 商品列表
     */
    @BindView(id = R.id.rv)
    private RecyclerView recyclerView;

    /**
     * 错误提示页
     */
    @BindView(id = R.id.ll_commonError)
    private LinearLayout ll_commonError;
    @BindView(id = R.id.tv_hintText, click = true)
    private TextView tv_hintText;

    /**
     * 当前页码
     */
    private int mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
    /**
     * 总页码
     */
    private int totalPageNumber = NumericConstants.START_PAGE_NUMBER;

    /**
     * 是否加载更多
     */
    private boolean isShowLoadingMore = false;

    private AllGoodsViewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SpacesItemDecoration spacesItemDecoration;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (ShopActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_allgoods, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new AllGoodsPresenter(this);
        mAdapter = new AllGoodsViewAdapter(recyclerView);
        spacesItemDecoration = new SpacesItemDecoration(5, 10);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        initRecyclerView();
    }

    /**
     * 设置RecyclerView控件部分属性
     */
    private void initRecyclerView() {
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        //设置item之间的间隔
        recyclerView.addItemDecoration(spacesItemDecoration);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnRVItemClickListener(this);
    }

    @Override
    public void onRVItemClick(ViewGroup parent, View itemView, int position) {
        Intent intent = new Intent(aty, GoodsDetailsActivity.class);
        // intent.putExtra("good_id", listbean.get(postion));
        aty.showActivity(aty, intent);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        // ((MyCollectionContract.Presenter) mPresenter).getRecommendedRecord(mMorePageNumber);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endLoadingMore();
        if (!isShowLoadingMore) {
            return false;
        }
        mMorePageNumber++;
        if (mMorePageNumber > totalPageNumber) {
            ViewInject.toast(getString(R.string.noMoreData));
            return false;
        }
        showLoadingDialog(getString(R.string.dataLoad));
        //  ((MyCollectionContract.Presenter) mPresenter).getRecommendedRecord(mMorePageNumber);
        return true;
    }

    @Override
    public void setPresenter(AllGoodsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        isShowLoadingMore = true;
        ll_commonError.setVisibility(View.GONE);
        mRefreshLayout.setVisibility(View.VISIBLE);
//        RecommendedRecordBean recommendedRecordBean = (RecommendedRecordBean) JsonUtil.getInstance().json2Obj(s, RecommendedRecordBean.class);
//        mMorePageNumber = recommendedRecordBean.getResult().getPage();
//        totalPageNumber = recommendedRecordBean.getResult().getPageTotal();
//        if (recommendedRecordBean.getResult().getList() == null || recommendedRecordBean.getResult().getList().size() == 0) {
//            error(getString(R.string.serverReturnsDataNull));
//            return;
//        }
//        if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
//            mRefreshLayout.endRefreshing();
//            mAdapter.clear();
//            mAdapter.addNewData(recommendedRecordBean.getResult().getList());
//        } else {
//            mRefreshLayout.endLoadingMore();
//            mAdapter.addMoreData(recommendedRecordBean.getResult().getList());
//        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        if (isLogin(msg)) {
            aty.showActivity(aty, LoginActivity.class);
            return;
        }
        isShowLoadingMore = false;
        mRefreshLayout.setVisibility(View.GONE);
        ll_commonError.setVisibility(View.VISIBLE);
        tv_hintText.setText(msg + getString(R.string.clickRefresh));
        if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
            mRefreshLayout.endRefreshing();
        } else {
            mRefreshLayout.endLoadingMore();
        }
        dismissLoadingDialog();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.clear();
        mAdapter = null;
    }

}
