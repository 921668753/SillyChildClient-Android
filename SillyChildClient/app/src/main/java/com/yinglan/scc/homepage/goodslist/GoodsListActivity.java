package com.yinglan.scc.homepage.goodslist;


import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.common.cklibrary.utils.myview.BGANormalRefreshViewHolder;
import com.yinglan.scc.R;
import com.yinglan.scc.adapter.homepage.goodslist.GoodsListViewAdapter;
import com.yinglan.scc.constant.NumericConstants;
import com.yinglan.scc.entity.homepage.goodslist.GoodsListBean;
import com.yinglan.scc.homepage.goodslist.goodsdetails.GoodsDetailsActivity;
import com.yinglan.scc.loginregister.LoginActivity;
import com.yinglan.scc.utils.SpacesItemDecoration;

import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 商品列表
 */
public class GoodsListActivity extends BaseActivity implements GoodsListContract.View, BGARefreshLayout.BGARefreshLayoutDelegate, BGAOnRVItemClickListener {

    @BindView(id = R.id.iv_back, click = true)
    private ImageView iv_back;

    @BindView(id = R.id.et_search)
    private EditText et_search;

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

    private int cat = 0;
    private String sort = "def_desc";

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
        cat = getIntent().getIntExtra("cat", 0);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        initRecyclerView();
        mRefreshLayout.beginRefreshing();
    }

    /**
     * 设置RecyclerView控件部分属性
     */
    private void initRecyclerView() {
        recyclerview.setLayoutManager(layoutManager);
        //设置item之间的间隔
        recyclerview.addItemDecoration(spacesItemDecoration);
        recyclerview.setAdapter(goodsListAdapter);
        goodsListAdapter.setOnRVItemClickListener(this);
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_comprehensiveRanking:
                if (sort.equals("def_desc")) {
                    sort = "def_asc";
                } else {
                    sort = "def_desc";
                }
                mRefreshLayout.beginRefreshing();
                break;
            case R.id.ll_salesPreferred:
                if (sort.equals("buynum_desc")) {
                    sort = "buynum_asc";
                } else {
                    sort = "buynum_desc";
                }
                mRefreshLayout.beginRefreshing();
                break;
            case R.id.ll_pricePriority:
                if (sort.equals("price_desc")) {
                    sort = "price_asc";
                } else {
                    sort = "price_desc";
                }
                mRefreshLayout.beginRefreshing();
                break;
            case R.id.tv_button:
                if (tv_button.getText().toString().contains(getString(R.string.retry))) {
                    mRefreshLayout.beginRefreshing();
                    return;
                }
                showActivity(this, LoginActivity.class);
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
        GoodsListBean goodsListBean = (GoodsListBean) JsonUtil.getInstance().json2Obj(success, GoodsListBean.class);
        if (goodsListBean.getData() == null && mMorePageNumber == NumericConstants.START_PAGE_NUMBER || goodsListBean.getData().size() <= 0 &&
                mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
            errorMsg(getString(R.string.noale), 1);
            return;
        } else if (goodsListBean.getData() == null && mMorePageNumber > NumericConstants.START_PAGE_NUMBER ||
                goodsListBean.getData().size() <= 0 && mMorePageNumber > NumericConstants.START_PAGE_NUMBER) {
            ViewInject.toast(getString(R.string.noMoreData));
            isShowLoadingMore = false;
            dismissLoadingDialog();
            mRefreshLayout.endLoadingMore();
            return;
        }
        if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
            mRefreshLayout.endRefreshing();
            goodsListAdapter.clear();
            goodsListAdapter.addNewData(goodsListBean.getData());
        } else {
            mRefreshLayout.endLoadingMore();
            goodsListAdapter.addMoreData(goodsListBean.getData());
        }
        dismissLoadingDialog();
    }

    @Override
    public void onRVItemClick(ViewGroup parent, View itemView, int position) {
        Intent intent = new Intent(aty, GoodsDetailsActivity.class);
        intent.putExtra("good_id", goodsListAdapter.getItem(position).getGoods_id());
        showActivity(aty, intent);
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
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
            //  ViewInject.toast(getString(R.string.reloginPrompting));
            showActivity(this, LoginActivity.class);
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
        //   ViewInject.toast(msg);
        dismissLoadingDialog();
    }


    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((GoodsListContract.Presenter) mPresenter).getGoodsList(mMorePageNumber, cat, sort, et_search.getText().toString().trim());
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
        ((GoodsListContract.Presenter) mPresenter).getGoodsList(mMorePageNumber, cat, sort, et_search.getText().toString().trim());
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        goodsListAdapter.clear();
        goodsListAdapter = null;
    }


}