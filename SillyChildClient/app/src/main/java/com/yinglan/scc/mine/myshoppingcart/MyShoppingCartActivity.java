package com.yinglan.scc.mine.myshoppingcart;

import android.content.Intent;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.yinglan.scc.R;
import com.yinglan.scc.adapter.mine.myshoppingcart.MyShoppingCartViewAdapter;
import com.yinglan.scc.entity.mine.myshoppingcart.MyShoppingCartBean;
import com.yinglan.scc.entity.mine.myshoppingcart.MyShoppingCartBean.DataBean.StorelistBean.GoodslistBean;
import com.yinglan.scc.homepage.goodslist.goodsdetails.GoodsDetailsActivity;
import com.yinglan.scc.loginregister.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 我的购物车
 * Created by Administrator on 2017/9/2.
 */

public class MyShoppingCartActivity extends BaseActivity implements MyShoppingCartContract.View, AbsListView.OnScrollListener, AdapterView.OnItemClickListener, BGAOnItemChildClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(id = R.id.titlebar)
    private BGATitleBar titlebar;

    @BindView(id = R.id.tv_delete, click = true)
    private TextView tv_delete;


    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    /**
     * 商品列表
     */
    @BindView(id = R.id.lv_myShoppingCart)
    private ListView lv_myShoppingCart;

    /**
     * 是否编辑
     */
    private int isEdit = 0;

    /**
     * 是否是编辑
     */
    private int isSelected = 0;

    private MyShoppingCartViewAdapter mAdapter = null;

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

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_myshoppingcart);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new MyShoppingCartPresenter(this);
        mAdapter = new MyShoppingCartViewAdapter(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, this, false);
        lv_myShoppingCart.setAdapter(mAdapter);
        lv_myShoppingCart.setOnItemClickListener(this);
        mAdapter.setOnItemChildClickListener(this);
        lv_myShoppingCart.setOnScrollListener(this);
        mRefreshLayout.beginRefreshing();
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        titlebar.setTitleText(R.string.shoppingCart);
        titlebar.setRightText(R.string.edit);
        titlebar.getRightCtv().setTextColor(getResources().getColor(R.color.textColor));
        titlebar.getRightCtv().setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        tv_delete.setVisibility(View.GONE);
        BGATitleBar.SimpleDelegate simpleDelegate = new BGATitleBar.SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                super.onClickLeftCtv();
                aty.finish();
            }

            @Override
            public void onClickRightCtv() {
                super.onClickRightCtv();
                if (isEdit == 0) {
                    completeStatus();
                } else {
                    editStatus();
                }
            }
        };
        titlebar.setDelegate(simpleDelegate);
    }

    /**
     * 编辑
     */
    private void editStatus() {
        isEdit = 0;
        isSelected = 0;
        visibilityImg(isEdit, mAdapter.getData());
        mAdapter.notifyDataSetChanged();
        titlebar.getRightCtv().setTextColor(getResources().getColor(R.color.textColor));
        titlebar.setRightText(R.string.edit);
        tv_delete.setVisibility(View.GONE);
    }


    /**
     * 完成
     */
    private void completeStatus() {
        isEdit = 1;
        visibilityImg(isEdit, mAdapter.getData());
        mAdapter.notifyDataSetChanged();
        titlebar.getRightCtv().setTextColor(getResources().getColor(R.color.hintColors));
        titlebar.setRightText(R.string.complete);
        tv_delete.setVisibility(View.VISIBLE);
    }


    /**
     * 是否显示
     */
    private void visibilityImg(int isEdit, List<GoodslistBean> list) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setIsSelected(isSelected);
            list.get(i).setIsEdit(isEdit);
        }
    }


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
    public void setPresenter(MyShoppingCartContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        mRefreshLayout.setPullDownRefreshEnable(true);
        if (flag == 0) {
            ll_commonError.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            MyShoppingCartBean myShoppingCartBean = (MyShoppingCartBean) JsonUtil.getInstance().json2Obj(success, MyShoppingCartBean.class);
            mAdapter.closeOpenedSwipeItemLayoutWithAnim();
            if (myShoppingCartBean.getData().getStorelist() == null || myShoppingCartBean.getData().getStorelist().size() <= 0) {
                errorMsg(getString(R.string.notAnythingAdd), 0);
                editStatus();
                return;
            }
            List<GoodslistBean> list = new ArrayList<GoodslistBean>();
            for (int i = 0; i < myShoppingCartBean.getData().getStorelist().size(); i++) {
                if (myShoppingCartBean.getData().getStorelist().get(i) != null && myShoppingCartBean.getData().getStorelist().get(i).getGoodslist() != null
                        && myShoppingCartBean.getData().getStorelist().get(i).getGoodslist().size() > 0) {
                    list.addAll(myShoppingCartBean.getData().getStorelist().get(i).getGoodslist());
                }
            }
            visibilityImg(isEdit, list);
            mAdapter.clear();
            mAdapter.addNewData(list);
            mRefreshLayout.endRefreshing();
            dismissLoadingDialog();
        } else if (flag == 1) {
            for (int i = 0; i < mAdapter.getData().size(); i++) {
                if (mAdapter.getData().get(i).getIsSelected() == 1) {
                    mAdapter.removeItem(i);
                }
            }
            dismissLoadingDialog();
        } else if (flag == 2) {
            isSelected = 0;
            mRefreshLayout.beginRefreshing();
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        mRefreshLayout.setPullDownRefreshEnable(false);
        mRefreshLayout.setVisibility(View.GONE);
        ll_commonError.setVisibility(View.VISIBLE);
        tv_hintText.setVisibility(View.VISIBLE);
        tv_button.setVisibility(View.VISIBLE);
        if (isLogin(msg)) {
            img_err.setImageResource(R.mipmap.no_login);
            tv_hintText.setVisibility(View.GONE);
            tv_button.setText(getString(R.string.login));
            //   ViewInject.toast(getString(R.string.reloginPrompting));
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
        ViewInject.toast(msg);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (isEdit == 1) {
            ImageView img_checkbox = (ImageView) view.findViewById(R.id.img_checkbox);
            if (mAdapter.getItem(position).getIsSelected() == 0) {
                img_checkbox.setImageResource(R.mipmap.shopping_cart_selected);
                mAdapter.getItem(position).setIsSelected(1);
                return;
            }
            img_checkbox.setImageResource(R.mipmap.shopping_cart_unselected);
            mAdapter.getItem(position).setIsSelected(0);
            return;
        }
        Intent intent = new Intent(aty, GoodsDetailsActivity.class);
        intent.putExtra("messageId", mAdapter.getItem(position).getId());
        showActivity(aty, intent);
    }

    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position) {
        if (childView.getId() == R.id.img_checkbox) {
            ImageView img_checkbox = (ImageView) childView.findViewById(R.id.img_checkbox);
            if (mAdapter.getItem(position).getIsSelected() == 0) {
                img_checkbox.setImageResource(R.mipmap.shopping_cart_selected);
                mAdapter.getItem(position).setIsSelected(1);
                return;
            }
            img_checkbox.setImageResource(R.mipmap.shopping_cart_unselected);
            mAdapter.getItem(position).setIsSelected(0);
        } else if (childView.getId() == R.id.tv_delete) {
            //   showLoadingDialog(getString(R.string.dataLoad));
            mAdapter.getItem(position).setIsSelected(1);
            ((MyShoppingCartContract.Presenter) mPresenter).postDeleteGood(mAdapter.getData());
        } else if (childView.getId() == R.id.tv_subtract) {
            //   showLoadingDialog(getString(R.string.dataLoad));
            // ((MyShoppingCartContract.Presenter) mPresenter).postCartUpdate(mAdapter.getItem(position).getId());
        } else if (childView.getId() == R.id.tv_add) {
            //   showLoadingDialog(getString(R.string.dataLoad));
            //  ((MyShoppingCartContract.Presenter) mPresenter).postAddGood(mAdapter.getItem(position).getId());
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((MyShoppingCartContract.Presenter) mPresenter).getMyShoppingCartList();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL == scrollState) {
            mAdapter.closeOpenedSwipeItemLayoutWithAnim();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.clear();
        mAdapter = null;
    }

}
