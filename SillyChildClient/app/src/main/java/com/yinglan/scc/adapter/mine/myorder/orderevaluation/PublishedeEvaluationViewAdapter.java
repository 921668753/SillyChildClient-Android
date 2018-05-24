package com.yinglan.scc.adapter.mine.myorder.orderevaluation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.kymjs.common.Log;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.yinglan.scc.R;
import com.yinglan.scc.adapter.ImagePickerAdapter;
import com.yinglan.scc.constant.NumericConstants;
import com.yinglan.scc.entity.MyOrderPicturesBean.ResultBean;
import com.yinglan.scc.utils.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 发表评价 适配器
 * https://github.com/WKaKa/Assess/tree/master/Assess/app
 * Created by Admin on 2017/8/15.
 */

public class PublishedeEvaluationViewAdapter extends BGAAdapterViewAdapter<ResultBean> implements ImagePickerAdapter.OnRecyclerViewItemClickListener {


    //用于退出 Activity,避免 Countdown，造成资源浪费。
    private SparseArray<ImagePickerAdapter> imagePickerAdapterCounters;
    private SparseArray<List<ImageItem>> selImageListCounters;

    public PublishedeEvaluationViewAdapter(Context context) {
        super(context, R.layout.item_publishedeevaluation);
        this.imagePickerAdapterCounters = new SparseArray<>();
        this.selImageListCounters = new SparseArray<>();
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper helper) {
        super.setItemChildListener(helper);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ResultBean listBean) {
        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getOrderString(), (ImageView) viewHolderHelper.getView(R.id.img_good), R.mipmap.placeholderfigure1);
        viewHolderHelper.setText(R.id.tv_goodName, listBean.getOrderString());
        viewHolderHelper.setText(R.id.tv_goodDescribe, listBean.getOrderString());
        viewHolderHelper.setText(R.id.tv_money, mContext.getString(R.string.renminbi) + listBean.getOrderString());
        EditText et_goodsSatisfactory = (EditText) viewHolderHelper.getView(R.id.et_goodsSatisfactory);
        et_goodsSatisfactory.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                listBean.setOrderString(editable + "");
            }
        });
        RecyclerView recyclerView = (RecyclerView) viewHolderHelper.getView(R.id.recyclerView);
        List<ImageItem> selImageList = new ArrayList<>();
        ImagePickerAdapter adapter = new ImagePickerAdapter(mContext, selImageList, NumericConstants.MAXPICTURE, R.mipmap.feedback_add_pictures);
        adapter.setOnItemClickListener(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 5);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        selImageListCounters.put(recyclerView.hashCode(), selImageList);
        imagePickerAdapterCounters.put(recyclerView.hashCode(), adapter);
    }


    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case NumericConstants.IMAGE_ITEM_ADD:
                //打开选择,本次允许选择的数量
                Intent intent1 = new Intent(mContext, ImageGridActivity.class);
                /* 如果需要进入选择的时候显示已经选中的图片，
                 * 详情请查看ImagePickerActivity
                 * */
//                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
                ((Activity) mContext).startActivityForResult(intent1, NumericConstants.REQUEST_CODE_SELECT);
                break;
            default:
                ImagePickerAdapter adapter = imagePickerAdapterCounters.get(((RecyclerView) view.getParent().getParent()).getRootView().hashCode());
                List<ImageItem> selImageList = selImageListCounters.get(((RecyclerView) view.getParent().getParent()).getRootView().hashCode());
                if (view.getId() == R.id.iv_delete) {
                    if (selImageList != null && selImageList.size() > position) {
                        selImageList.remove(position);
                        adapter.setImages(selImageList);
                    }
                } else {
                    //打开预览
                    Intent intentPreview = new Intent(mContext, ImagePreviewDelActivity.class);
                    intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                    intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                    intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                    ((Activity) mContext).startActivityForResult(intentPreview, NumericConstants.REQUEST_CODE_PREVIEW);
                }
                break;
        }
    }


    @Override
    public void clear() {
        if (imagePickerAdapterCounters != null && selImageListCounters == null) {
            Log.e("TAG", "size :  " + imagePickerAdapterCounters.size());
            for (int i = 0, length = imagePickerAdapterCounters.size(); i < length; i++) {
                ImagePickerAdapter cdt = imagePickerAdapterCounters.get(imagePickerAdapterCounters.keyAt(i));
                if (cdt != null) {
                    cdt.setImages(null);
                    cdt = null;
                }
            }
        }
        if (imagePickerAdapterCounters == null && selImageListCounters != null) {
            Log.e("TAG", "size :  " + selImageListCounters.size());
            for (int i = 0, length = selImageListCounters.size(); i < length; i++) {
                List<ImageItem> cdt = selImageListCounters.get(selImageListCounters.keyAt(i));
                if (cdt != null) {
                    cdt.clear();
                    cdt = null;
                }
            }
        }
        if (imagePickerAdapterCounters != null && selImageListCounters != null) {
            Log.e("TAG", "size :  " + imagePickerAdapterCounters.size());
            for (int i = 0, length = imagePickerAdapterCounters.size(); i < length; i++) {
                ImagePickerAdapter cdt = imagePickerAdapterCounters.get(imagePickerAdapterCounters.keyAt(i));
                if (cdt != null) {
                    cdt.setImages(null);
                    cdt = null;
                }
            }
            for (int i = 0, length = selImageListCounters.size(); i < length; i++) {
                List<ImageItem> cdt = selImageListCounters.get(selImageListCounters.keyAt(i));
                if (cdt != null) {
                    cdt.clear();
                    cdt = null;
                }
            }
        }
        super.clear();
    }


}