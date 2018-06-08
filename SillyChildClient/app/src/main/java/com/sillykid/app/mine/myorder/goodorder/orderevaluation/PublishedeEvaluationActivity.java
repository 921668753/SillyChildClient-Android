package com.sillykid.app.mine.myorder.goodorder.orderevaluation;

import android.content.Intent;
import android.widget.TextView;

import com.azhong.ratingbar.RatingBar;
import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.myview.ChildListView;
import com.lzy.imagepicker.ImagePicker;
import com.sillykid.app.R;
import com.sillykid.app.entity.mine.myorder.goodorder.orderevaluation.PublishedeEvaluationBean;
import com.sillykid.app.entity.mine.myorder.goodorder.orderevaluation.PublishedeEvaluationBean.DataBean.CommentVoBean.MemberCommentExtsBean;
import com.sillykid.app.adapter.mine.myorder.orderevaluation.PublishedeEvaluationViewAdapter;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.entity.mine.myorder.OrderDetailBean;
import com.sillykid.app.entity.mine.myorder.OrderDetailBean.DataBean.ItemListBean;
import com.sillykid.app.loginregister.LoginActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 发表评价
 */
public class PublishedeEvaluationActivity extends BaseActivity implements PublishedeEvaluationContract.View {

    @BindView(id = R.id.clv_publishedeEvaluation)
    private ChildListView clv_publishedeEvaluation;

    @BindView(id = R.id.rb_descriptionConsistent)
    private RatingBar rb_descriptionConsistent;

    @BindView(id = R.id.rb_logisticsService)
    private RatingBar rb_logisticsService;

    @BindView(id = R.id.rb_serviceAttitude)
    private RatingBar rb_serviceAttitude;

    @BindView(id = R.id.tv_release, click = true)
    private TextView tv_release;


    private String order_id = "";

    private PublishedeEvaluationViewAdapter mAdapter = null;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_publishedeevaluation);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new PublishedeEvaluationPresenter(this);
        mAdapter = new PublishedeEvaluationViewAdapter(this);
        order_id = getIntent().getStringExtra("order_id");
        showLoadingDialog(getString(R.string.dataLoad));
        ((PublishedeEvaluationContract.Presenter) mPresenter).getOrderDetails(order_id);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        clv_publishedeEvaluation.setAdapter(mAdapter);
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.publishedeEvaluation), true, R.id.titlebar);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == NumericConstants.REQUEST_CODE_SELECT) {
//                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
//                if (images != null) {
//                    imagefile = new File(images.get(0).path);
//                    imagefile = BitmapCoreUtil.customCompression(imagefile);
//                    showLoadingDialog(getString(R.string.crossLoad));
//                    ((FeedbackPresenter) mPresenter).upPictures("file");
//                }

            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == NumericConstants.REQUEST_CODE_PREVIEW) {
//                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
//                if (images != null) {
//                    selImageList.clear();
//                    selImageList.addAll(images);
//                    adapter.setImages(selImageList);
//                }
            }
        }
    }

    @Override
    public void setPresenter(PublishedeEvaluationContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        if (flag == 0) {
            OrderDetailBean orderDetailBean = (OrderDetailBean) JsonUtil.getInstance().json2Obj(success, OrderDetailBean.class);
            if (orderDetailBean.getData().getItemList() != null && orderDetailBean.getData().getItemList().size() > 0) {
                mAdapter.clear();
                List<MemberCommentExtsBean> memberCommentExtsBeanList = new ArrayList<MemberCommentExtsBean>();
                for (int i = 0; i < orderDetailBean.getData().getItemList().size(); i++) {
                    ItemListBean itemListBean = orderDetailBean.getData().getItemList().get(i);
                    MemberCommentExtsBean memberCommentExtsBean = new MemberCommentExtsBean();
                    memberCommentExtsBean.setImage(itemListBean.getImage());
                    memberCommentExtsBean.setGoods_id(itemListBean.getGoods_id());
                    memberCommentExtsBean.setName(itemListBean.getName());
                    memberCommentExtsBean.setPrice(itemListBean.getPrice());
                    memberCommentExtsBean.setSpecs(itemListBean.getSpecs());
                    memberCommentExtsBeanList.add(memberCommentExtsBean);
                }
                mAdapter.addMoreData(memberCommentExtsBeanList);
            }
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            //  ViewInject.toast(getString(R.string.reloginPrompting));
            showActivity(this, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }
}
