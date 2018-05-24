package com.yinglan.scc.mine.myorder.goodorder.orderevaluation;

import android.content.Intent;
import android.widget.TextView;

import com.azhong.ratingbar.RatingBar;
import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.utils.BitmapCoreUtil;
import com.common.cklibrary.utils.myview.ChildListView;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.yinglan.scc.R;
import com.yinglan.scc.constant.NumericConstants;
import com.yinglan.scc.mine.setup.feedback.FeedbackPresenter;

import java.io.File;
import java.util.ArrayList;


/**
 * 发表评价
 */
public class PublishedeEvaluationActivity extends BaseActivity {

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

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_publishedeevaluation);
    }

    @Override
    public void initWidget() {
        super.initWidget();
    }

    @Override
    public void initData() {
        super.initData();
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

}
