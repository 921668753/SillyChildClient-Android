package com.yinglan.scc.mine.mycollection;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.yinglan.scc.R;
import com.yinglan.scc.adapter.mine.mycollection.MyCollectionViewAdapter;
import com.yinglan.scc.constant.NumericConstants;
import com.yinglan.scc.entity.mine.mycollection.MyCollectionBean;
import com.yinglan.scc.homepage.goodslist.goodsdetails.GoodsDetailsActivity;
import com.yinglan.scc.loginregister.LoginActivity;
import com.yinglan.scc.mine.mycollection.dialog.DeleteCollectionDialog;

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


    private DeleteCollectionDialog deleteCollectionDialog = null;

    private DeleteCollectionDialog addCartGoodDialog = null;

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
        initDeleteCollectionDialog();
        initAddCartGoodDialog();
    }


    /**
     * 弹框
     */
    public void initDeleteCollectionDialog() {
        deleteCollectionDialog = new DeleteCollectionDialog(this, getString(R.string.determineDeleteCollection)) {
            @Override
            public void deleteCollectionDo(int addressId) {
                showLoadingDialog(getString(R.string.deleteLoad));
                ((MyCollectionContract.Presenter) mPresenter).postUnFavoriteGood(addressId);
            }
        };
    }

    public void initAddCartGoodDialog() {
        addCartGoodDialog = new DeleteCollectionDialog(this, getString(R.string.makeSureAddShoppingCart)) {
            @Override
            public void deleteCollectionDo(int addressId) {
                showLoadingDialog(getString(R.string.addLoad));
                ((MyCollectionContract.Presenter) mPresenter).postAddCartGood(addressId);
            }
        };
    }


    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.myCollection), true, R.id.titlebar);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        lv_myCollection.setAdapter(mAdapter);
        lv_myCollection.setOnItemClickListener(this);
        mAdapter.setOnItemChildClickListener(this);
        mRefreshLayout.beginRefreshing();
    }


    /**
     * 控件监听事件
     */
    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
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
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((MyCollectionContract.Presenter) mPresenter).getFavoriteGoodList(mMorePageNumber);
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
        ((MyCollectionContract.Presenter) mPresenter).getFavoriteGoodList(mMorePageNumber);
        return true;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent = new Intent(aty, GoodsDetailsActivity.class);
        intent.putExtra("good_id", mAdapter.getItem(position).getGoods_id());
        showActivity(aty, intent);
    }

    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position) {
        if (childView.getId() == R.id.img_delete) {
            if (deleteCollectionDialog == null) {
                initDeleteCollectionDialog();
            }
            if (deleteCollectionDialog != null && !deleteCollectionDialog.isShowing()) {
                deleteCollectionDialog.show();
                deleteCollectionDialog.setCollectionId(mAdapter.getItem(position).getGoods_id());
            }
        } else if (childView.getId() == R.id.img_shoppingCart) {
            if (addCartGoodDialog == null) {
                initAddCartGoodDialog();
            }
            if (addCartGoodDialog != null && !addCartGoodDialog.isShowing()) {
                addCartGoodDialog.show();
                addCartGoodDialog.setCollectionId(mAdapter.getItem(position).getGoods_id());
            }
        }
    }


    @Override
    public void setPresenter(MyCollectionContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            isShowLoadingMore = true;
            mRefreshLayout.setPullDownRefreshEnable(true);
            ll_commonError.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            MyCollectionBean myCollectionBean = (MyCollectionBean) JsonUtil.getInstance().json2Obj(success, MyCollectionBean.class);
            if (myCollectionBean.getData() == null && mMorePageNumber == NumericConstants.START_PAGE_NUMBER ||
                    myCollectionBean.getData().size() <= 0 && mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                errorMsg(getString(R.string.noCollectedGoods), 1);
                return;
            } else if (myCollectionBean.getData() == null && mMorePageNumber > NumericConstants.START_PAGE_NUMBER ||
                    myCollectionBean.getData().size() <= 0 && mMorePageNumber > NumericConstants.START_PAGE_NUMBER) {
                ViewInject.toast(getString(R.string.noMoreData));
                isShowLoadingMore = false;
                dismissLoadingDialog();
                mRefreshLayout.endLoadingMore();
                return;
            }
            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                mRefreshLayout.endRefreshing();
                mAdapter.clear();
                mAdapter.addNewData(myCollectionBean.getData());
            } else {
                mRefreshLayout.endLoadingMore();
                mAdapter.addMoreData(myCollectionBean.getData());
            }
        } else if (flag == 1) {
            mAdapter.removeItem(positionItem);
        } else if (flag == 2) {
            ViewInject.toast(getString(R.string.addCartSuccess));
        }
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
//        } else {
//            ViewInject.toast(msg);
//        }
    }


    /**
     * 在接收消息的时候，选择性接收消息：
     */
    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusMyCollectionEvent")) {
            mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
            ((MyCollectionContract.Presenter) mPresenter).getFavoriteGoodList(mMorePageNumber);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (deleteCollectionDialog != null) {
            deleteCollectionDialog.cancel();
        }
        deleteCollectionDialog = null;

        if (addCartGoodDialog != null) {
            addCartGoodDialog.cancel();
        }
        addCartGoodDialog = null;
        mAdapter.clear();
        mAdapter = null;
    }

}
