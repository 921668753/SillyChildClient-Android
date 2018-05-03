package com.ruitukeji.scc.mine.setup.feedback;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.BitmapCoreUtil;
import com.common.cklibrary.utils.GlideCatchUtil;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.myview.NoScrollGridView;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.ruitukeji.scc.R;
import com.ruitukeji.scc.adapter.FeedBackTypeAdapter;
import com.ruitukeji.scc.adapter.ImagePickerAdapter;
import com.ruitukeji.scc.constant.NumericConstants;
import com.ruitukeji.scc.dialog.ImagePopupWindow;
import com.ruitukeji.scc.dialog.VIPPermissionsDialog;
import com.ruitukeji.scc.entity.CharterOrderBean;
import com.ruitukeji.scc.entity.FeedBackTypeBean;
import com.ruitukeji.scc.entity.UploadImageBean;
import com.ruitukeji.scc.loginregister.LoginActivity;
import com.ruitukeji.scc.mine.myorder.orderDetails.OrderDetailsActivity;
import com.ruitukeji.scc.mine.myorder.orderevaluation.PostEvaluationActivity;
import com.ruitukeji.scc.utils.GlideImageLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 设置中的意见反馈
 * Created by Administrator on 2017/9/2.
 */

public class FeedbackActivity extends BaseActivity implements TextWatcher,ImagePickerAdapter.OnRecyclerViewItemClickListener,FeedbackContract.View,AdapterView.OnItemClickListener{
    @BindView(id = R.id.ll_allactivity)
    private LinearLayout ll_allactivity;

    @BindView(id = R.id.noscrollgridview)
    private NoScrollGridView noscrollgridview;

    @BindView(id = R.id.tv_feed)
    private EditText tv_feed;
    @BindView(id = R.id.tv_currentwords)
    private TextView tv_currentwords;
    @BindView(id = R.id.tv_wordLimit)
    private TextView tv_wordLimit;

    @BindView(id = R.id.recyclerView)
    private RecyclerView recyclerView;

    @BindView(id = R.id.tvbtn_submit , click = true)
    private TextView tvbtn_submit;
    
    private String feedcontent;
    private int wordLimit;
    private List<ImageItem> selImageList;
    private List<ImageItem> images;
    private List<String> urllist;
    private ImagePickerAdapter adapter;
    private File imagefile;
    private ImagePopupWindow imagePopupWindow;
    private UploadImageBean uploadimagebean;
    private String urls;
    private FeedBackTypeAdapter feedBackTypeAdapter;
    private int currentposition=-1;
    private int FLAGSUBMIT=9;
    private int FLAGTYPE=10;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_feedback);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter=new FeedbackPresenter(this);
        wordLimit=StringUtils.toInt(tv_wordLimit.getText().toString(),500);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        tv_feed.addTextChangedListener(this);
        tv_feed.setMovementMethod(ScrollingMovementMethod.getInstance());

        initImagePicker();
        selImageList = new ArrayList<>();
        urllist=new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, NumericConstants.MAXPICTURE,R.mipmap.mine_xiangjiaddxxx);
        adapter.setOnItemClickListener(this);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        feedBackTypeAdapter=new FeedBackTypeAdapter(this);
        noscrollgridview.setAdapter(feedBackTypeAdapter);
        noscrollgridview.setOnItemClickListener(this);
        //获取意见反馈类型
        showLoadingDialog(getString(R.string.dataLoad));
        ((FeedbackPresenter)mPresenter).getTypes(FLAGTYPE);

    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()){
            case R.id.tvbtn_submit:
                if (currentposition==-1){
                    ViewInject.toast(getString(R.string.selectorType));
                }else{
                    pullEvaluation();
                }
                break;
        }
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.feedback), true, R.id.titlebar);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
        //text  输入框中改变前的字符串信息
        //start 输入框中改变前的字符串的起始位置
        //count 输入框中改变前后的字符串改变数量一般为0
        //after 输入框中改变后的字符串与起始位置的偏移量

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        //text  输入框中改变后的字符串信息
        //start 输入框中改变后的字符串的起始位置
        //before 输入框中改变前的字符串的位置 默认为0
        //count 输入框中改变后的一共输入字符串的数量
        if ((start+count)>wordLimit){
            tv_currentwords.setText(wordLimit+"/");
        }else{
            tv_currentwords.setText(start+count+"/");
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        //edit  输入结束呈现在输入框中的信息
        feedcontent=editable.toString();
        if (feedcontent!=null&&feedcontent.length()>wordLimit){
            tv_feed.setText(feedcontent.substring(0,wordLimit));
            tv_feed.setSelection(wordLimit);
        }
    }

//    /**
//     * 清除颜色，并添加颜色
//     */
//    public void cleanColors(TextView currenttv) {
//        tvbtn_dysfunction.setTextColor(getResources().getColor(R.color.textColor));
//        tvbtn_dysfunction.setBackgroundResource(R.drawable.shape_addshoppingcarte);
//        tvbtn_experienceMatters.setTextColor(getResources().getColor(R.color.textColor));
//        tvbtn_experienceMatters.setBackgroundResource(R.drawable.shape_addshoppingcarte);
//        tvbtn_newFeature.setTextColor(getResources().getColor(R.color.textColor));
//        tvbtn_newFeature.setBackgroundResource(R.drawable.shape_addshoppingcarte);
//        tvbtn_otherthing.setTextColor(getResources().getColor(R.color.textColor));
//        tvbtn_otherthing.setBackgroundResource(R.drawable.shape_addshoppingcarte);
//
//        currenttv.setTextColor(getResources().getColor(R.color.greenColors));
//        currenttv.setBackgroundResource(R.drawable.shape_code);
//    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        GlideImageLoader glideImageLoader=new GlideImageLoader();
        imagePicker.setImageLoader(glideImageLoader);   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(NumericConstants.MAXPICTURE);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
//        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
//        imagePicker.setFocusHeight(400);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
//        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
//        imagePicker.setOutPutY(500);                         //保存文件的高度。单位像素
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
        imagePicker.setMultiMode(false);//设置为单选模式，默认多选
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case NumericConstants.IMAGE_ITEM_ADD:
                //打开选择,本次允许选择的数量
                Intent intent1 = new Intent(this, ImageGridActivity.class);
                                /* 如果需要进入选择的时候显示已经选中的图片，
                                 * 详情请查看ImagePickerActivity
                                 * */
//                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
                startActivityForResult(intent1, NumericConstants.REQUEST_CODE_SELECT);
                break;
            default:
                if (view.getId()==R.id.iv_delete){
                    if (selImageList != null&&selImageList.size()>position) {
                        selImageList.remove(position);
                        urllist.remove(position);
                        adapter.setImages(selImageList);
                    }
                }else{
                    //打开预览
                    imagePopupWindow = new ImagePopupWindow(this, getWindow(), urllist.get(position));
                    imagePopupWindow.showAtLocation(ll_allactivity, Gravity.CENTER, 0, 0);
                    //打开预览
//                    Intent intentPreview = new Intent(PostEvaluationActivity.this, ImagePreviewDelActivity.class);
//                    intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
//                    intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
//                    intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
//                    startActivityForResult(intentPreview, NumericConstants.REQUEST_CODE_PREVIEW);
                }

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == NumericConstants.REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images!=null){
                    imagefile=new File(images.get(0).path);
                    imagefile = BitmapCoreUtil.customCompression(imagefile);
                    showLoadingDialog(getString(R.string.crossLoad));
                    ((FeedbackPresenter)mPresenter).upPictures("file",imagefile,0);
                }

            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == NumericConstants.REQUEST_CODE_PREVIEW) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        }
    }

    @Override
    public void setPresenter(FeedbackContract.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag==FLAGSUBMIT){
            dismissLoadingDialog();
            ViewInject.toast(getString(R.string.submitSuccess));
            finish();
        }else if (flag==FLAGTYPE){
            FeedBackTypeBean uploadimagebean = (FeedBackTypeBean) JsonUtil.getInstance().json2Obj(success, FeedBackTypeBean.class);
            if (uploadimagebean!=null&&uploadimagebean.getResult()!=null&&uploadimagebean.getResult().getList()!=null&&uploadimagebean.getResult().getList().size()>0){
                feedBackTypeAdapter.addNewData(uploadimagebean.getResult().getList());
                dismissLoadingDialog();
            }else{
                dismissLoadingDialog();
                initDialog(getString(R.string.noHaveFeedBackType));
            }
        }else {
            GlideCatchUtil.getInstance().cleanImageDisk();
            uploadimagebean = (UploadImageBean) JsonUtil.getInstance().json2Obj(success, UploadImageBean.class);
            if (uploadimagebean != null && uploadimagebean.getResult() != null && uploadimagebean.getResult().getFile() != null && !TextUtils.isEmpty(uploadimagebean.getResult().getFile().getUrl())) {
                urllist.add(uploadimagebean.getResult().getFile().getUrl());
                selImageList.addAll(images);
                adapter.setImages(selImageList);
                dismissLoadingDialog();
            } else {
                ViewInject.toast("图片上传失败！");
                dismissLoadingDialog();
            }
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        GlideCatchUtil.getInstance().cleanImageDisk();
        Log.e("图片",msg);
        if (isLogin(msg)){
            ViewInject.toast(getString(R.string.reloginPrompting));
            PreferenceHelper.write(this, StringConstants.FILENAME, "isRefreshMineFragment", false);
            PreferenceHelper.write(this, StringConstants.FILENAME, "isReLogin", true);
            showActivity(this,LoginActivity.class);
            finish();
            return;
        }
        if (flag==FLAGTYPE){
            dismissLoadingDialog();
            initDialog(msg+getString(R.string.getFeedBackTypeError));
            return;
        }
        dismissLoadingDialog();
        ViewInject.toast(this,msg);
    }

    /**
     * 上传评价
     */
    private void pullEvaluation(){
        urls="";
        if (urllist.size()>0){
            for (String s:urllist){
                if (!TextUtils.isEmpty(s)) {
                    urls += "|" + s;
                }
            }
        }
        showLoadingDialog(getString(R.string.submissionLoad));
        ((FeedbackPresenter)mPresenter).submitFeed(feedBackTypeAdapter.getItem(currentposition).getId(),urls,tv_feed.getText().toString(),FLAGSUBMIT);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (currentposition!=-1){
            feedBackTypeAdapter.getItem(currentposition).setSelector(false);
        }
        feedBackTypeAdapter.getItem(i).setSelector(true);
        feedBackTypeAdapter.notifyDataSetChanged();
        currentposition=i;
    }

    private void initDialog(String content){
        VIPPermissionsDialog typedialog=new VIPPermissionsDialog(this) {
            @Override
            public void doAction() {
                finish();
            }
        };
        typedialog.setCancelable(false);
        typedialog.show();
        typedialog.setContent(content);
    }

}