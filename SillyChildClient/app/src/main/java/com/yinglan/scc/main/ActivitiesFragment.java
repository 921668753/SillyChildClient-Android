package com.yinglan.scc.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.common.cklibrary.utils.myview.HorizontalListView;
import com.kymjs.common.StringUtils;
import com.yinglan.scc.R;
import com.yinglan.scc.adapter.main.activities.BargainViewAdapter;
import com.yinglan.scc.adapter.main.activities.ProductlViewAdapter;
import com.yinglan.scc.constant.NumericConstants;
import com.yinglan.scc.entity.main.ActivitiesBean.ResultBean.AdBean;
import com.yinglan.scc.homepage.BannerDetailsActivity;
import com.yinglan.scc.homepage.goodslist.goodsdetails.GoodsDetailsActivity;
import com.yinglan.scc.utils.GlideImageLoader;
import com.yinglan.scc.utils.SpacesItemDecoration;

import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 活动
 */
public class ActivitiesFragment extends BaseFragment implements ActivitiesContract.View, BGABanner.Delegate<ImageView, AdBean>, BGABanner.Adapter<ImageView, AdBean>, BGARefreshLayout.BGARefreshLayoutDelegate, AdapterView.OnItemClickListener, BGAOnRVItemClickListener {

    private MainActivity aty;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    /**
     * 轮播图
     */
    @BindView(id = R.id.banner_ad)
    private BGABanner mForegroundBanner;

    /**
     * 特价商品
     */
    @BindView(id = R.id.hlv_bargain)
    private HorizontalListView hlv_bargain;

    /**
     * 当季热销
     */
    @BindView(id = R.id.rv)
    private RecyclerView recyclerview;

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

    private SpacesItemDecoration spacesItemDecoration;

    private StaggeredGridLayoutManager layoutManager;

    private ProductlViewAdapter productlViewAdapter;
    private BargainViewAdapter bargainViewAdapter;


    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_activities, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new ActivitiesPresenter(this);
        bargainViewAdapter = new BargainViewAdapter(aty, hlv_bargain);
        spacesItemDecoration = new SpacesItemDecoration(5, 10);
        productlViewAdapter = new ProductlViewAdapter(recyclerview);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, false);
        initBanner();
        hlv_bargain.setAdapter(bargainViewAdapter);
        hlv_bargain.setOnItemClickListener(this);
        initRecyclerView();
    }

    /**
     * 初始化轮播图
     */
    public void initBanner() {
        mForegroundBanner.setAutoPlayAble(true);
        mForegroundBanner.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mForegroundBanner.setAllowUserScrollable(true);
        mForegroundBanner.setAutoPlayInterval(3000);
        // 初始化方式1：配置数据源的方式1：通过传入数据模型并结合 Adapter 的方式配置数据源。这种方式主要用于加载网络图片，以及实现少于3页时的无限轮播
        mForegroundBanner.setAdapter(this);
        mForegroundBanner.setDelegate(this);
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
        recyclerview.setAdapter(productlViewAdapter);
        productlViewAdapter.setOnRVItemClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (aty.getChageIcon() == 0) {
            mForegroundBanner.startAutoPlay();
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        if (aty.getChageIcon() == 0) {
            mForegroundBanner.stopAutoPlay();
        }
    }

    @Override
    public void setPresenter(ActivitiesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        isShowLoadingMore = true;
        //  ll_commonError.setVisibility(View.GONE);
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
//        if (isLogin(msg)) {
//            showActivity(aty, LoginActivity.class);
//            return;
//        }
//        isShowLoadingMore = false;
//        mRefreshLayout.setVisibility(View.GONE);
//        ll_commonError.setVisibility(View.VISIBLE);
//        tv_hintText.setText(msg + getString(R.string.clickRefresh));
//        if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
//            mRefreshLayout.endRefreshing();
//        } else {
//            mRefreshLayout.endLoadingMore();
//        }
//        dismissLoadingDialog();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent = new Intent(aty, GoodsDetailsActivity.class);
        // intent.putExtra("good_id", listbean.get(postion));
        aty.showActivity(aty, intent);
    }

    @Override
    public void onRVItemClick(ViewGroup parent, View itemView, int position) {
        Intent intent = new Intent(aty, GoodsDetailsActivity.class);
        // intent.putExtra("good_id", listbean.get(postion));
        aty.showActivity(aty, intent);
    }

    @Override
    public void fillBannerItem(BGABanner banner, ImageView itemView, AdBean model, int position) {
        GlideImageLoader.glideOrdinaryLoader(aty, model.getAd_code(), itemView, R.mipmap.placeholderfigure2);
    }

    @Override
    public void onBannerItemClick(BGABanner banner, ImageView itemView, AdBean model, int position) {
        if (StringUtils.isEmpty(model.getAd_link())) {
            return;
        }
        Intent bannerDetails = new Intent(aty, BannerDetailsActivity.class);
        bannerDetails.putExtra("url", model.getAd_link());
        bannerDetails.putExtra("title", model.getAd_name());
        aty.showActivity(aty, bannerDetails);
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
    public void onDestroy() {
        super.onDestroy();
        bargainViewAdapter.clear();
        bargainViewAdapter = null;
        productlViewAdapter.clear();
        productlViewAdapter = null;
    }


}
