package com.yinglan.scc.homepage.goodslist;


import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.yinglan.scc.R;
import com.yinglan.scc.adapter.homepage.goodslist.GoodsListViewAdapter;
import com.yinglan.scc.constant.NumericConstants;
import com.yinglan.scc.homepage.goodslist.goodsdetails.GoodsDetailsActivity;
import com.yinglan.scc.loginregister.LoginActivity;
import com.yinglan.scc.utils.SpacesItemDecoration;

import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 商品列表
 */
public class GoodsListActivity extends BaseActivity implements GoodsListContract.View, BGARefreshLayout.BGARefreshLayoutDelegate, BGAOnRVItemClickListener {

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    @BindView(id = R.id.ll_comprehensiveRanking, click = true)
    private LinearLayout ll_comprehensiveRanking;

    @BindView(id = R.id.ll_salesPreferred, click = true)
    private LinearLayout ll_salesPreferred;

    @BindView(id = R.id.ll_pricePriority, click = true)
    private LinearLayout ll_pricePriority;


    @BindView(id = R.id.rv)
    private RecyclerView recyclerview;

    private GoodsListViewAdapter goodsListAdapter = null;

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

    private SpacesItemDecoration spacesItemDecoration = null;

    private StaggeredGridLayoutManager layoutManager;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_goodslist);
    }


    @Override
    public void initData() {
        super.initData();
        mPresenter = new GoodsListPresenter(this);
        spacesItemDecoration = new SpacesItemDecoration(5, 10);
        goodsListAdapter = new GoodsListViewAdapter(recyclerview);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, false);
        initRecyclerView();
    }

    /**
     * 设置RecyclerView控件部分属性
     */
    private void initRecyclerView() {
        layoutManager.setAutoMeasureEnabled(true);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
        recyclerview.setNestedScrollingEnabled(false);
        //设置item之间的间隔
        recyclerview.addItemDecoration(spacesItemDecoration);
        recyclerview.setAdapter(goodsListAdapter);
        goodsListAdapter.setOnRVItemClickListener(this);
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_comprehensiveRanking:

                break;
            case R.id.ll_salesPreferred:

                break;
            case R.id.ll_pricePriority:

                break;
        }
    }


    @Override
    public void setPresenter(GoodsListContract.Presenter presenter) {
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
    public void onRVItemClick(ViewGroup parent, View itemView, int position) {
        Intent intent = new Intent(aty, GoodsDetailsActivity.class);
        // intent.putExtra("good_id", listbean.get(postion));
        showActivity(aty, intent);
    }

    @Override
    public void errorMsg(String msg, int flag) {
        if (isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
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
    protected void onDestroy() {
        super.onDestroy();
        goodsListAdapter.clear();
    }


}