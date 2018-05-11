package com.yinglan.scc.mine.mycollection;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.yinglan.scc.R;
import com.yinglan.scc.adapter.mine.mycollection.MyCollectionViewAdapter;
import com.yinglan.scc.constant.NumericConstants;
import com.yinglan.scc.homepage.goodslist.goodsdetails.GoodsDetailsActivity;
import com.yinglan.scc.loginregister.LoginActivity;

import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 我的收藏中的商品
 * Created by Administrator on 2017/9/2.
 */

public class MyCollectionActivity extends BaseActivity implements MyCollectionContract.View, AdapterView.OnItemClickListener, BGAOnItemChildClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    private MyCollectionViewAdapter mAdapter;

    @BindView(id = R.id.lv_myCollection)
    private ListView lv_myCollection;

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

    private int positionItem = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.fragment_good);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new MyCollectionPresenter(this);
        mAdapter = new MyCollectionViewAdapter(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.myCollection), true, R.id.titlebar);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        lv_myCollection.setAdapter(mAdapter);
        lv_myCollection.setOnItemClickListener(this);
        mAdapter.setOnItemChildClickListener(this);
        showLoadingDialog(getString(R.string.dataLoad));
        //((MyCollectionContract.Presenter) mPresenter).get(mMorePageNumber);

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

    /**
     * 控件监听事件
     */
    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_hintText:
                mRefreshLayout.beginRefreshing();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        // ViewInject.toast("1");
        Intent intent = new Intent(aty, GoodsDetailsActivity.class);
        //    intent.putExtra("messageId", mAdapter.getItem(position).getId());
        intent.putExtra("name", "RxBusOrderMessageDetailsEvent");
        showActivity(aty, intent);
    }

    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position) {
        positionItem = position;
        if (childView.getId() == R.id.img_delete) {
            mAdapter.removeItem(positionItem);
        } else if (childView.getId() == R.id.img_shoppingCart) {
            //   showLoadingDialog(getString(R.string.dataLoad));
            //   ((MyShoppingCartContract.Presenter) mPresenter).postDeleteGood(mAdapter.getData());
        }
    }


    @Override
    public void setPresenter(MyCollectionContract.Presenter presenter) {
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
    public void onDestroy() {
        super.onDestroy();
        mAdapter.clear();
        mAdapter = null;
    }

}
