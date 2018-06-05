package com.sillykid.app.homepage.goodslist.goodsdetails;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.myview.WebViewLayout1;
import com.kymjs.common.StringUtils;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.sillykid.app.R;
import com.sillykid.app.entity.homepage.goodslist.goodsdetails.GoodsDetailsBean;
import com.sillykid.app.homepage.goodslist.goodsdetails.comments.CommentsActivity;
import com.sillykid.app.homepage.goodslist.goodsdetails.dialog.SpecificationsBouncedDialog;
import com.sillykid.app.homepage.goodslist.shop.ShopActivity;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.mine.myorder.goodorder.orderdetails.OrderDetailsActivity;
import com.sillykid.app.mine.myshoppingcart.makesureorder.MakeSureOrderActivity;
import com.sillykid.app.utils.SoftKeyboardUtils;

import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 商品详情
 * Created by Admin on 2017/8/24.
 */

public class GoodsDetailsActivity extends BaseActivity implements GoodsDetailsContract.View, WebViewLayout1.WebViewCallBack {

    @BindView(id = R.id.titlebar)
    private BGATitleBar titlebar;

    @BindView(id = R.id.web_content)
    private WebViewLayout1 webViewLayout;

    /**
     * 店铺
     */
    @BindView(id = R.id.ll_shop, click = true)
    private LinearLayout ll_shop;

    /**
     * 客服
     */
    @BindView(id = R.id.ll_customerService, click = true)
    private LinearLayout ll_customerService;

    /**
     * 收藏
     */
    @BindView(id = R.id.ll_follow, click = true)
    private LinearLayout ll_follow;

    @BindView(id = R.id.tv_buyNow, click = true)
    private TextView tv_buyNow;

    @BindView(id = R.id.tv_addShoppingCart, click = true)
    private TextView tv_addShoppingCart;

    private int goodsid = 0;

    private String goodName = "";
    private int store_id = 0;

    private SpecificationsBouncedDialog specificationsBouncedDialog = null;

    private boolean favorited = false;
    private int isRefresh = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_goodsdetails);
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        super.initData();
        mPresenter = new GoodsDetailsPresenter(this);
        goodName = getIntent().getStringExtra("goodName");
        goodsid = getIntent().getIntExtra("goodsid", 0);
        isRefresh = getIntent().getIntExtra("isRefresh", 0);
        ((GoodsDetailsContract.Presenter) mPresenter).getGoodDetail(goodsid);
        initDialog();
    }

    private void initDialog() {
        specificationsBouncedDialog = new SpecificationsBouncedDialog(this,goodsid) {
            @Override
            public void share(String platform) {

            }
        };
    }


    /**
     * 初始化控件
     */
    @Override

    public void initWidget() {
        super.initWidget();
        initTitle();
        initView();
    }


    /**
     * 设置标题
     */
    public void initTitle() {
        titlebar.setTitleText(goodName);
        titlebar.setRightDrawable(getResources().getDrawable(R.mipmap.product_details_share));
        BGATitleBar.SimpleDelegate simpleDelegate = new BGATitleBar.SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                super.onClickLeftCtv();
                SoftKeyboardUtils.packUpKeyboard(aty);
                if (isRefresh == 1) {
                    Intent intent = getIntent();
                    setResult(RESULT_OK, intent);
                }
                aty.finish();
            }

            @Override
            public void onClickRightCtv() {
                super.onClickRightCtv();
                //分享

            }
        };
        titlebar.setDelegate(simpleDelegate);
    }


    public void initView() {
        webViewLayout.setTitleVisibility(false);
        webViewLayout.setWebViewCallBack(this);
//        if (!StringUtils.isEmpty(url)) {
//            webViewLayout.loadUrl(url);
//        }
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_shop:
                Intent shopIntent = new Intent(aty, ShopActivity.class);
                shopIntent.putExtra("storeid", store_id);
                showActivity(aty, shopIntent);
                break;
            case R.id.ll_customerService:

                break;
            case R.id.ll_follow:
                showLoadingDialog(getString(R.string.dataLoad));
                if (!favorited) {
                    ((GoodsDetailsContract.Presenter) mPresenter).postFavoriteAdd(goodsid);
                } else {
                    ((GoodsDetailsContract.Presenter) mPresenter).postUnfavorite(goodsid);
                }
                break;
            case R.id.tv_addShoppingCart:
                if (specificationsBouncedDialog == null) {
                    initDialog();
                }
                specificationsBouncedDialog.show();
                break;
            case R.id.tv_buyNow:
                Intent buyNowIntent = new Intent(aty, MakeSureOrderActivity.class);
                buyNowIntent.putExtra("goodslistBean", "");
                buyNowIntent.putExtra("totalPrice", "");
                showActivity(aty, buyNowIntent);
                break;
        }
    }

    @Override
    public void setPresenter(GoodsDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            GoodsDetailsBean goodsDetailsBean = (GoodsDetailsBean) JsonUtil.json2Obj(success, GoodsDetailsBean.class);
            if (goodsDetailsBean != null && goodsDetailsBean.getData() != null && goodsDetailsBean.getData().getGoods_id() > 0) {
                goodsid = goodsDetailsBean.getData().getGoods_id();
                store_id = goodsDetailsBean.getData().getStore_id();
             //   favorited = goodsDetailsBean.getData().isFavorited();
               // price = goodsDetailsBean.getData().get();
            }
        } else if (flag == 1) {
            favorited = true;
            isRefresh = 1;
            ll_follow.setBackgroundResource(R.mipmap.mall_collect);
            ViewInject.toast(getString(R.string.collectionSuccess));
        } else if (flag == 2) {
            favorited = false;
            isRefresh = 1;
            ll_follow.setBackgroundResource(R.mipmap.mall_uncollect);
            ViewInject.toast(getString(R.string.uncollectible));
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
        } else {
            ViewInject.toast(msg);
        }
    }

    @Override
    public void backOnclick(String id) {
        if (StringUtils.isEmpty(id)) {
            Intent intent = new Intent(aty, CommentsActivity.class);
            intent.putExtra("", "");
            showActivity(aty, intent);
        } else {
            Intent intent = new Intent(aty, GoodsDetailsActivity.class);
            // intent.putExtra("good_id", listbean.get(postion));
            showActivity(aty, intent);
        }
    }

    @Override
    public void loadFailedError() {

    }

    /**
     * 退出应用
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (isRefresh == 1) {
                    Intent intent = getIntent();
                    setResult(RESULT_OK, intent);
                }
        }
        return super.onKeyUp(keyCode, event);
    }


}
