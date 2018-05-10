package com.yinglan.scc.homepage.goodslist.goodsdetails.comments.evaluation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.yinglan.scc.R;
import com.yinglan.scc.adapter.homepage.goodslist.evaluation.CommentsViewAdapter;
import com.yinglan.scc.constant.NumericConstants;
import com.yinglan.scc.homepage.goodslist.goodsdetails.comments.CommentsActivity;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 全部评论
 * Created by Admin on 2017/8/10.
 */
public class AllCommentsFragment extends BaseFragment implements CommentsContract.View, AdapterView.OnItemClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    private CommentsActivity aty;

    @BindView(id = R.id.lv_comments)
    private ListView lv_comments;

    /**
     * 1为已支付 0为未支付
     */
    private int is_pay = 1;

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

    private CommentsViewAdapter mAdapter = null;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (CommentsActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_comments, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new CommentsPresenter(this);
        mAdapter = new CommentsViewAdapter(getActivity());
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        //  lv_comments.setAdapter(mAdapter);
        lv_comments.setOnItemClickListener(this);
        showLoadingDialog(getString(R.string.dataLoad));
        //((CommentsContract.Presenter) mPresenter).getBill(mMorePageNumber, is_pay);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        Intent intent = new Intent(aty, OrderDetailsActivity.class);
//        intent.putExtra("order_id", mAdapter.getItem(i).getOrder_id());
//        aty.showActivity(aty, intent);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        // ((CommentsContract.Presenter) mPresenter).getBill(mMorePageNumber, is_pay);
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
        //  ((CommentsContract.Presenter) mPresenter).getBill(mMorePageNumber, is_pay);
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

    /**
     * 当通过changeFragment()显示时会被调用(类似于onResume)
     */
    @Override
    public void onChange() {
        super.onChange();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.cancelAllComments();
        mAdapter.clear();
        mAdapter = null;
    }

    @Override
    public void setPresenter(CommentsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        isShowLoadingMore = true;
        ll_commonError.setVisibility(View.GONE);
        mRefreshLayout.setVisibility(View.VISIBLE);
//        BillBean billBean = (BillBean) JsonUtil.getInstance().json2Obj(s, BillBean.class);
//        mMorePageNumber = billBean.getResult().getPage();
//        totalPageNumber = billBean.getResult().getPageTotal();
//        if (billBean.getResult().getList() == null || billBean.getResult().getList().size() == 0) {
//            error(getString(R.string.serverReturnsDataNull));
//            return;
//        }
//        if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
//            mRefreshLayout.endRefreshing();
//            mAdapter.clear();
//            mAdapter.addNewData(billBean.getResult().getList());
//            mRefreshLayout.endRefreshing();
//        } else {
//            mRefreshLayout.endLoadingMore();
//            mAdapter.addMoreData(billBean.getResult().getList());
//        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        //    toLigon(msg);
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
}
