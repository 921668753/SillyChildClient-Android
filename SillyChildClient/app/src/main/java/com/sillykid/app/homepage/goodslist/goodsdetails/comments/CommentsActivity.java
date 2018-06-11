package com.sillykid.app.homepage.goodslist.goodsdetails.comments;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.MathUtil;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.homepage.goodslist.goodsdetails.GoodsDetailsContract;
import com.sillykid.app.homepage.goodslist.goodsdetails.GoodsDetailsPresenter;
import com.sillykid.app.homepage.goodslist.goodsdetails.dialog.SpecificationsBouncedDialog;
import com.sillykid.app.homepage.goodslist.shop.ShopActivity;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.mine.myshoppingcart.makesureorder.MakeSureOrderActivity;
import com.sillykid.app.utils.SoftKeyboardUtils;

import cn.bingoogolapple.titlebar.BGATitleBar;
import io.rong.imkit.RongIM;

/**
 * 评论
 * Created by Admin on 2017/8/21.
 */

public class CommentsActivity extends BaseActivity implements GoodsDetailsContract.View {

    @BindView(id = R.id.tv_all, click = true)
    private TextView tv_all;

    @BindView(id = R.id.tv_havePictures, click = true)
    private TextView tv_havePictures;

    @BindView(id = R.id.tv_additionalReview, click = true)
    private TextView tv_additionalReview;

    private BaseFragment contentFragment;
    private BaseFragment contentFragment1;
    private BaseFragment contentFragment2;

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

    private SpecificationsBouncedDialog specificationsBouncedDialog = null;

    private boolean favorited = false;

    private int isRefresh = 0;

    private String price = "";

    private int num = 0;

    private String have_spec = "0";

    private int goodsid = 0;

    private int chageIcon = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_comments);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new GoodsDetailsPresenter(this);
        contentFragment = new AllCommentsFragment();
        contentFragment1 = new HavePicturesCommentsFragment();
        contentFragment2 = new AddCommentsFragment();
        chageIcon = getIntent().getIntExtra("chageIcon", 0);
        goodsid = getIntent().getIntExtra("goodsid", 0);
        price = getIntent().getStringExtra("price");
        have_spec = getIntent().getStringExtra("have_spec");
        favorited = getIntent().getBooleanExtra("favorited", false);
        initDialog();
    }

    /**
     * 商品规格
     */
    private void initDialog() {
        specificationsBouncedDialog = new SpecificationsBouncedDialog(this) {
            @Override
            public void toDo(int goodId, int flag, int num1, int product_id) {
                num = num1;
                if (flag == 0) {
                    ((GoodsDetailsContract.Presenter) mPresenter).postAddCartGood(goodId, num1, product_id);
                } else if (flag == 1) {
                    ((GoodsDetailsContract.Presenter) mPresenter).postOrderBuyNow(goodId, num1, product_id);
                }
            }
        };
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        if (favorited) {
            ll_follow.setBackgroundResource(R.mipmap.mall_collect);
        } else {
            ll_follow.setBackgroundResource(R.mipmap.mall_uncollect);
        }
        if (chageIcon == 0) {
            cleanColors(0);
            changeFragment(contentFragment);
            chageIcon = 0;
        } else if (chageIcon == 1) {
            cleanColors(1);
            changeFragment(contentFragment1);
            chageIcon = 1;
        } else if (chageIcon == 2) {
            cleanColors(2);
            changeFragment(contentFragment2);
            chageIcon = 2;
        }
    }

    private void initTitle() {
        BGATitleBar.SimpleDelegate simpleDelegate = new BGATitleBar.SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                super.onClickLeftCtv();
                SoftKeyboardUtils.packUpKeyboard(aty);
                if (isRefresh == 1) {
                    Intent intent = getIntent();
                    intent.putExtra("favorited", favorited);
                    setResult(RESULT_OK, intent);
                }
                aty.finish();
            }

            @Override
            public void onClickRightCtv() {
                super.onClickRightCtv();
            }
        };
        ActivityTitleUtils.initToolbar(aty, getString(R.string.comment), "", true, R.id.titlebar, simpleDelegate);
    }


    public void changeFragment(BaseFragment targetFragment) {
        super.changeFragment(R.id.main_content, targetFragment);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_all:
                cleanColors(0);
                changeFragment(contentFragment);
                chageIcon = 0;
                break;
            case R.id.tv_havePictures:
                cleanColors(1);
                changeFragment(contentFragment1);
                chageIcon = 1;
                break;
            case R.id.tv_additionalReview:
                cleanColors(2);
                changeFragment(contentFragment2);
                chageIcon = 2;
                break;
            case R.id.ll_shop:
                Intent shopIntent = new Intent(aty, ShopActivity.class);
                shopIntent.putExtra("storeid", getIntent().getIntExtra("store_id", 0));
                showActivity(aty, shopIntent);
                break;
            case R.id.ll_customerService:
/**
 * <p>启动会话界面。</p>
 * <p>使用时，可以传入多种会话类型 {@link io.rong.imlib.model.Conversation.ConversationType} 对应不同的会话类型，开启不同的会话界面。
 * 如果传入的是 {@link io.rong.imlib.model.Conversation.ConversationType#CHATROOM}，sdk 会默认调用
 * {@link RongIMClient#joinChatRoom(String, int, RongIMClient.OperationCallback)} 加入聊天室。
 * 如果你的逻辑是，只允许加入已存在的聊天室，请使用接口 {@link #startChatRoomChat(Context, String, boolean)} 并且第三个参数为 true</p>
 *
 * @param context          应用上下文。
 * @param conversationType 会话类型。
 * @param targetId         根据不同的 conversationType，可能是用户 Id、群组 Id 或聊天室 Id。
 * @param title            聊天的标题，开发者可以在聊天界面通过 intent.getData().getQueryParameter("title") 获取该值, 再手动设置为标题。
 */
                //    RongIM.getInstance().startConversation(Context context, Conversation.ConversationType conversationType, String targetId, String title);
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
                specificationsBouncedDialog.setFlag(0, goodsid, StringUtils.toInt(have_spec));
                break;
            case R.id.tv_buyNow:
                if (specificationsBouncedDialog == null) {
                    initDialog();
                }
                specificationsBouncedDialog.show();
                specificationsBouncedDialog.setFlag(1, goodsid, StringUtils.toInt(have_spec));
                break;
            default:
                break;
        }
    }


    /**
     * 清除颜色，并添加颜色
     */
    @SuppressWarnings("deprecation")
    public void cleanColors(int chageIcon) {
        tv_all.setTextColor(getResources().getColor(R.color.titletextcolors));
        tv_all.setBackground(getResources().getDrawable(R.drawable.shape_comments1));
        tv_havePictures.setTextColor(getResources().getColor(R.color.titletextcolors));
        tv_havePictures.setBackground(getResources().getDrawable(R.drawable.shape_comments1));
        tv_additionalReview.setTextColor(getResources().getColor(R.color.titletextcolors));
        tv_additionalReview.setBackground(getResources().getDrawable(R.drawable.shape_comments1));
        if (chageIcon == 0) {
            tv_all.setTextColor(getResources().getColor(R.color.whiteColors));
            tv_all.setBackground(getResources().getDrawable(R.drawable.shape_comments));
        } else if (chageIcon == 1) {
            tv_havePictures.setTextColor(getResources().getColor(R.color.whiteColors));
            tv_havePictures.setBackground(getResources().getDrawable(R.drawable.shape_comments));
        } else if (chageIcon == 2) {
            tv_additionalReview.setTextColor(getResources().getColor(R.color.whiteColors));
            tv_additionalReview.setBackground(getResources().getDrawable(R.drawable.shape_comments));
        } else {
            tv_all.setTextColor(getResources().getColor(R.color.whiteColors));
            tv_all.setBackground(getResources().getDrawable(R.drawable.shape_comments));
        }
    }

    @Override
    public void setPresenter(GoodsDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        if (flag == 1) {
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


    public void setAll(String text) {
        tv_all.setText(getString(R.string.allAeservationNumber) + text);
    }

    public void setHavePictures(String text) {
        tv_havePictures.setText(getString(R.string.figure) + text);
    }

    public void setAdditionalReview(String text) {
        tv_additionalReview.setText(getString(R.string.afterReview) + text);
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
                    intent.putExtra("favorited", favorited);
                    setResult(RESULT_OK, intent);
                }
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (specificationsBouncedDialog != null) {
            specificationsBouncedDialog.cancel();
        }
        specificationsBouncedDialog = null;
    }
}
