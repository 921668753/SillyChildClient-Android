package com.sillykid.app.homepage.goodslist.goodsdetails;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.MathUtil;
import com.common.cklibrary.utils.myview.WebViewLayout1;
import com.kymjs.common.StringUtils;
import com.sillykid.app.mine.sharingceremony.dialog.ShareBouncedDialog;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.sillykid.app.R;
import com.sillykid.app.entity.homepage.goodslist.goodsdetails.GoodsDetailsBean;
import com.sillykid.app.homepage.goodslist.goodsdetails.comments.CommentsActivity;
import com.sillykid.app.homepage.goodslist.goodsdetails.dialog.SpecificationsBouncedDialog;
import com.sillykid.app.homepage.goodslist.shop.ShopActivity;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.mine.myshoppingcart.makesureorder.MakeSureOrderActivity;
import com.sillykid.app.utils.SoftKeyboardUtils;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import cn.bingoogolapple.titlebar.BGATitleBar;

import static com.sillykid.app.constant.NumericConstants.REQUEST_CODE;

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

    private ShareBouncedDialog shareBouncedDialog = null;

    private String smallImg = "";

    private String brief = "";
    private String price = "";
    private int num = 0;

    private String have_spec = "0";

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
        initShareBouncedDialog();
    }

    /**
     * 商品规格
     */
    private void initDialog() {
        specificationsBouncedDialog = new SpecificationsBouncedDialog(this, goodsid) {
            @Override
            public void toDo(int goodId, int flag, int num1) {
                num = num1;
                if (flag == 0) {
                    ((GoodsDetailsContract.Presenter) mPresenter).postAddCartGood(goodId, num1);
                } else if (flag == 1) {
                    ((GoodsDetailsContract.Presenter) mPresenter).postOrderBuyNow(goodId, num1);
                }
            }
        };
    }

    /**
     * 分享
     */
    private void initShareBouncedDialog() {
        shareBouncedDialog = new ShareBouncedDialog(this) {
            @Override
            public void share(SHARE_MEDIA platform) {
                umShare(platform);
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
                if (shareBouncedDialog == null) {
                    initShareBouncedDialog();
                }
                if (shareBouncedDialog != null & !shareBouncedDialog.isShowing()) {
                    shareBouncedDialog.show();
                }
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
                Intent intent = new Intent(aty, CommentsActivity.class);
                intent.putExtra("goodsid", goodsid);
                intent.putExtra("favorited", favorited);
                intent.putExtra("price", price);
                intent.putExtra("have_spec", have_spec);
                intent.putExtra("store_id", store_id);
                startActivityForResult(intent, REQUEST_CODE);
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
                specificationsBouncedDialog.setFlag(0, StringUtils.toInt(have_spec));
                break;
            case R.id.tv_buyNow:
                if (specificationsBouncedDialog == null) {
                    initDialog();
                }
                specificationsBouncedDialog.show();
                specificationsBouncedDialog.setFlag(1, StringUtils.toInt(have_spec));
                break;
        }
    }

    @Override
    public void setPresenter(GoodsDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        if (flag == 0) {
            GoodsDetailsBean goodsDetailsBean = (GoodsDetailsBean) JsonUtil.json2Obj(success, GoodsDetailsBean.class);
            if (goodsDetailsBean != null && goodsDetailsBean.getData() != null && goodsDetailsBean.getData().getGoods_id() > 0) {
                goodsid = goodsDetailsBean.getData().getGoods_id();
                store_id = goodsDetailsBean.getData().getStore_id();
                favorited = goodsDetailsBean.getData().isFavorited();
                have_spec = goodsDetailsBean.getData().getHave_spec();
                price = goodsDetailsBean.getData().getPrice();
                goodName = goodsDetailsBean.getData().getName();
                smallImg = goodsDetailsBean.getData().getSmall();
                brief = goodsDetailsBean.getData().getBrief();
                if (favorited) {
                    ll_follow.setBackgroundResource(R.mipmap.mall_collect);
                } else {
                    ll_follow.setBackgroundResource(R.mipmap.mall_uncollect);
                }
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
        } else if (flag == 3) {
            Intent buyNowIntent = new Intent(aty, MakeSureOrderActivity.class);
            buyNowIntent.putExtra("goodslistBean", success);
            buyNowIntent.putExtra("totalPrice", MathUtil.keepTwo(StringUtils.toDouble(price) * num));
            buyNowIntent.putExtra("type", 1);
            showActivity(aty, buyNowIntent);
        } else if (flag == 4) {
            ViewInject.toast(getString(R.string.addCartSuccess));
        }
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
            intent.putExtra("good_id", goodsid);
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
     * 返回
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


    /**
     * 直接分享
     * SHARE_MEDIA.QQ
     */
    public void umShare(SHARE_MEDIA platform) {
        UMImage thumb = new UMImage(this, smallImg);
        UMWeb web = new UMWeb("" + "?code=" + "");
        web.setTitle(goodName);//标题
        web.setThumb(thumb);  //缩略图
        web.setDescription(brief);//描述
        new ShareAction(aty).setPlatform(platform)
//                .withText("hello")
//                .withMedia(thumb)
                .withMedia(web)
                .setCallback(umShareListener)
                .share();
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
            showLoadingDialog(getString(R.string.shareJumpLoad));
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            dismissLoadingDialog();
            Log.d("throw", "platform" + platform);
            ViewInject.toast(getString(R.string.shareSuccess));
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            dismissLoadingDialog();
            if (t.getMessage().contains(getString(R.string.authoriseErr3))) {
                ViewInject.toast(getString(R.string.authoriseErr2));
                return;
            }
            ViewInject.toast(getString(R.string.shareError));
            //   ViewInject.toast(t.getMessage());
            Log.d("throw", "throw:" + t.getMessage());
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            Log.d("throw", "throw:" + "onCancel");
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            favorited = data.getBooleanExtra("favorited", false);
            if (favorited) {
                ll_follow.setBackgroundResource(R.mipmap.mall_collect);
            } else {
                ll_follow.setBackgroundResource(R.mipmap.mall_uncollect);
            }
            isRefresh = 1;
        } else {
            UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        UMShareAPI.get(this).onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
        if (specificationsBouncedDialog != null) {
            specificationsBouncedDialog.cancel();
        }
        specificationsBouncedDialog = null;
        if (shareBouncedDialog != null) {
            shareBouncedDialog.cancel();
        }
        shareBouncedDialog = null;
        webViewLayout.removeAllViews();
        webViewLayout = null;
    }

}
