package com.sillykid.app.adapter.mine.myorder.orderevaluation;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.common.cklibrary.utils.MathUtil;
import com.kymjs.common.Log;
import com.kymjs.common.StringUtils;
import com.lzy.imagepicker.bean.ImageItem;
import com.sillykid.app.R;
import com.sillykid.app.adapter.ImagePickerAdapter;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.entity.mine.myorder.goodorder.orderevaluation.PublishedeEvaluationBean.DataBean.CommentVoBean.MemberCommentExtsBean;
import com.sillykid.app.utils.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

public class PublishedeEvaluationAdapter extends BGARecyclerViewAdapter<MemberCommentExtsBean> {


    //用于退出 Activity,避免 Countdown，造成资源浪费。
    private SparseArray<ImagePickerAdapter> imagePickerAdapterCounters;
    private SparseArray<List<ImageItem>> selImageListCounters;

    private OnStatusListener onStatusListener;

    public PublishedeEvaluationAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_publishedeevaluation);
        this.imagePickerAdapterCounters = new SparseArray<>();
        this.selImageListCounters = new SparseArray<>();
    }

    @Override
    protected void fillData(BGAViewHolderHelper viewHolderHelper, int position, MemberCommentExtsBean model) {
        GlideImageLoader.glideOrdinaryLoader(mContext, model.getImage(), (ImageView) viewHolderHelper.getView(R.id.img_good), R.mipmap.placeholderfigure1);
        viewHolderHelper.setText(R.id.tv_goodName, model.getName());
        viewHolderHelper.setText(R.id.tv_goodDescribe, model.getSpecs());
        viewHolderHelper.setText(R.id.tv_money, mContext.getString(R.string.renminbi) + MathUtil.keepTwo(StringUtils.toDouble(model.getPrice())));
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
                model.setContent(editable + "");
            }
        });
        List<ImageItem> selImageList;
        RecyclerView recyclerView = (RecyclerView) viewHolderHelper.getView(R.id.recyclerView);
        if (selImageListCounters.get(recyclerView.hashCode()) != null) {
            selImageListCounters.get(recyclerView.hashCode()).clear();
            selImageList = selImageListCounters.get(recyclerView.hashCode());
        } else {
            selImageList = new ArrayList<>();
            selImageListCounters.put(recyclerView.hashCode(), selImageList);
        }
        if (model.getCommentImgs() != null && model.getCommentImgs().size() > 0) {
            for (int i = 0; i < model.getCommentImgs().size(); i++) {
                ImageItem imageItem = new ImageItem();
                imageItem.path = model.getCommentImgs().get(i);
                selImageList.add(imageItem);
            }
        }
        ImagePickerAdapter adapter;
        if (imagePickerAdapterCounters.get(recyclerView.hashCode()) != null) {
            adapter = imagePickerAdapterCounters.get(recyclerView.hashCode());
            adapter.setImages(selImageList);
        } else {
            adapter = new ImagePickerAdapter(mContext, selImageList, NumericConstants.MAXPICTURE, R.mipmap.feedback_add_pictures);
            adapter.setOnItemClickListener(new ImagePickerAdapter.OnRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(View view, int position1) {
                    onStatusListener.onSetStatusListener(view, adapter, position, position1);
                }
            });
            GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 5);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);
            imagePickerAdapterCounters.put(recyclerView.hashCode(), adapter);
        }
    }


    public void setOnStatusListener(OnStatusListener onStatusListener) {
        this.onStatusListener = onStatusListener;
    }

    public interface OnStatusListener {
        void onSetStatusListener(View view, ImagePickerAdapter adapter, int position, int position1);
        //  void onDeleteListener(int pos, int tagPos);
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
