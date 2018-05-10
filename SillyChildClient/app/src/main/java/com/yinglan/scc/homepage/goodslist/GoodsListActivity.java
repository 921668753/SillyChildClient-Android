package com.yinglan.scc.homepage.goodslist;


import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.yinglan.scc.R;
import com.yinglan.scc.adapter.homepage.goodslist.GoodsListAdapter;
import com.yinglan.scc.constant.NumericConstants;
import com.yinglan.scc.entity.DynamicStateBean;
import com.yinglan.scc.homepage.goodslist.goodsdetails.GoodsDetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品列表
 */
public class GoodsListActivity extends BaseActivity implements GoodsListContract.View, GoodsListAdapter.GoodsListItemOnClickListener, PullLoadMoreRecyclerView.PullLoadMoreListener {

    @BindView(id = R.id.ll_comprehensiveRanking, click = true)
    private LinearLayout ll_comprehensiveRanking;

    @BindView(id = R.id.ll_salesPreferred, click = true)
    private LinearLayout ll_salesPreferred;

    @BindView(id = R.id.ll_pricePriority, click = true)
    private LinearLayout ll_pricePriority;


    @BindView(id = R.id.pullLoadMoreRecyclerView)
    private PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private GoodsListAdapter goodsListAdapter = null;
    private ArrayList listbean = null;
    private boolean isLoadMore;
    private int pageNum = 1;
    private SpacesItemDecoration spacesItemDecoration = null;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_goodslist);
    }


    @Override
    public void initData() {
        super.initData();
        mPresenter = new GoodsListPresenter(this);
        mPullLoadMoreRecyclerView.setRefreshing(false);
        mPullLoadMoreRecyclerView.setStaggeredGridLayout(2);
        listbean = new ArrayList<>();
        spacesItemDecoration = new SpacesItemDecoration(5, 10);
        goodsListAdapter = new GoodsListAdapter(aty, listbean, this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        mPullLoadMoreRecyclerView.setAdapter(goodsListAdapter);
        //设置item之间的间隔
        mPullLoadMoreRecyclerView.addItemDecoration(spacesItemDecoration);
        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
//        mPullLoadMoreRecyclerView.setFooterViewText(getString(R.string.load_more_text));
//        mPullLoadMoreRecyclerView.setFooterViewTextColor(R.color.titletextcolors);
//        mPullLoadMoreRecyclerView.setFooterViewBackgroundColor(R.color.background);
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
//        if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
//            mAdapter.clear();
//            mAdapter.addNewData(helpCenterBean.getResult().getList());
//        } else {
//            mAdapter.addMoreData(helpCenterBean.getResult().getList());
//        }
        dismissLoadingDialog();
    }

    /**
     * 刷新数据
     */
    private void refreshListBean(List<DynamicStateBean.ResultBean.ListBean> addlist) {
        if (isLoadMore) {
            if (pageNum == 1) {
                listbean.clear();
                DynamicStateBean.ResultBean.ListBean initbean = new DynamicStateBean.ResultBean.ListBean();
                initbean.setTitle(getString(R.string.newStrategy));
                listbean.add(initbean);
                if (addlist != null && addlist.size() > 0) {
                    listbean.addAll(addlist);
                    if (addlist.size() < NumericConstants.LOADCOUNT) {
                        ViewInject.toast(getString(R.string.noMoreData));
                    }
                }
            } else {
                int listbeansize = listbean.size();
                for (int i = ((pageNum - 1) * NumericConstants.LOADCOUNT); i < listbeansize; i++) {
                    listbean.remove(i);
                }
                if (addlist != null && addlist.size() > 0) {
                    listbean.addAll(addlist);
                    if (addlist.size() < NumericConstants.LOADCOUNT) {
                        ViewInject.toast(getString(R.string.noMoreData));
                    }
                }
            }
        } else {
            listbean.clear();
            DynamicStateBean.ResultBean.ListBean initbean = new DynamicStateBean.ResultBean.ListBean();
            initbean.setTitle(getString(R.string.newStrategy));
            listbean.add(initbean);
            if (addlist != null && addlist.size() > 0) {
                listbean.addAll(addlist);
            }
        }
        goodsListAdapter.notifyDataSetChanged();
    }


    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();


    }

    @Override
    public void onRefresh() {
        //刷新
        isLoadMore = false;
        pageNum = NumericConstants.START_PAGE_NUMBER;
    }

    @Override
    public void onLoadMore() {
        //  ((StratePresenter)mPresenter).getStrateList(pagenum,NumericConstants.LOADCOUNT);
    }

    @Override
    public void goodsListOnItemClick(View view, int postion) {
        Intent intent = new Intent(aty, GoodsDetailsActivity.class);
        // intent.putExtra("good_id", listbean.get(postion));
        showActivity(aty, intent);
    }
}