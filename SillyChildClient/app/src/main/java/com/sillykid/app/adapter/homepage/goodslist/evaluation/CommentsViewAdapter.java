package com.sillykid.app.adapter.homepage.goodslist.evaluation;

import android.content.Context;
import android.util.SparseArray;

import com.common.cklibrary.utils.myview.NoScrollGridView;
import com.kymjs.common.Log;
import com.sillykid.app.R;
import com.sillykid.app.entity.IndexCityBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

public class CommentsViewAdapter extends BGAAdapterViewAdapter<IndexCityBean.ResultBean> {


    //用于退出 Activity,避免 Countdown，造成资源浪费。
    private SparseArray<CommentsGridViewAdapter> commentsCounters;
    private SparseArray<AddCommentsGridViewAdapter> addCommentsCounters;


    public CommentsViewAdapter(Context context) {
        super(context, R.layout.item_comments);
        this.commentsCounters = new SparseArray<>();
        this.addCommentsCounters = new SparseArray<>();
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper helper) {
        super.setItemChildListener(helper);
        helper.setItemChildClickListener(R.id.ll_revert);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, IndexCityBean.ResultBean model) {

        /**
         * 头像
         */
        GlideImageLoader.glideOrdinaryLoader(mContext, model.getArea_code(), helper.getImageView(R.id.img_head), R.mipmap.placeholderfigure);

        /**
         * 名字
         */
        helper.setText(R.id.tv_name, model.getId());

        /**
         * 时间
         */
        helper.setText(R.id.tv_time, model.getId());

        /**
         * 评论内容
         */
        helper.setText(R.id.tv_commentscContent, model.getId());

        /**
         * 图片
         */

        NoScrollGridView gv_imgComments = (NoScrollGridView) helper.getView(R.id.gv_imgComments);
        CommentsGridViewAdapter commentsGridViewAdapter = new CommentsGridViewAdapter(mContext);
        gv_imgComments.setAdapter(commentsGridViewAdapter);
        commentsCounters.put(gv_imgComments.hashCode(), commentsGridViewAdapter);
        // commentsGridViewAdapter.addNewData(model.getImgs());
//        gv_imgComments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(mContext, CharterDetailsActivity.class);
//                intent.putExtra("id", model.getId());
//                intent.putExtra("type", model.getType());
//                mContext.startActivity(intent);
//            }
//        });
//        gv_imgComments.setOnTouchInvalidPositionListener(new NoScrollGridView.OnTouchInvalidPositionListener() {
//            @Override
//            public boolean onTouchInvalidPosition(int motionEvent) {
//                Intent intent = new Intent(mContext, CharterDetailsActivity.class);
//                intent.putExtra("id", model.getId());
//                intent.putExtra("type", model.getType());
//                mContext.startActivity(intent);
//                return false;
//            }
//        });


        /**
         * 追评
         */
        helper.setText(R.id.tv_additionalReviewContent, model.getId());

        /**
         * 图片
         */
        NoScrollGridView gv_imgAddComments = (NoScrollGridView) helper.getView(R.id.gv_imgAddComments);
        AddCommentsGridViewAdapter addCommentsGridViewAdapter = new AddCommentsGridViewAdapter(mContext);
        gv_imgAddComments.setAdapter(addCommentsGridViewAdapter);
        addCommentsCounters.put(gv_imgAddComments.hashCode(), addCommentsGridViewAdapter);
        // addCommentsGridViewAdapter.addNewData(model.getImgs());
//        gv_imgComments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(mContext, CharterDetailsActivity.class);
//                intent.putExtra("id", model.getId());
//                intent.putExtra("type", model.getType());
//                mContext.startActivity(intent);
//            }
//        });
//        gv_imgComments.setOnTouchInvalidPositionListener(new NoScrollGridView.OnTouchInvalidPositionListener() {
//            @Override
//            public boolean onTouchInvalidPosition(int motionEvent) {
//                Intent intent = new Intent(mContext, CharterDetailsActivity.class);
//                intent.putExtra("id", model.getId());
//                intent.putExtra("type", model.getType());
//                mContext.startActivity(intent);
//                return false;
//            }
//        });
    }


    /**
     * 清空当前 CountTimeDown 资源
     */
    public void cancelAllComments() {
        if (commentsCounters == null) {
            return;
        }
        Log.e("TAG", "size :  " + commentsCounters.size());
        for (int i = 0, length = commentsCounters.size(); i < length; i++) {
            CommentsGridViewAdapter cdt = commentsCounters.get(commentsCounters.keyAt(i));
            if (cdt != null) {
                cdt.clear();
                cdt = null;
            }
        }
        if (addCommentsCounters == null) {
            return;
        }
        Log.e("TAG", "size :  " + addCommentsCounters.size());
        for (int i = 0, length = addCommentsCounters.size(); i < length; i++) {
            AddCommentsGridViewAdapter cdt = addCommentsCounters.get(addCommentsCounters.keyAt(i));
            if (cdt != null) {
                cdt.clear();
                cdt = null;
            }
        }
    }


}
