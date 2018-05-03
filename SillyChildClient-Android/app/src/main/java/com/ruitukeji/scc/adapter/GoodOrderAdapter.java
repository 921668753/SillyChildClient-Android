package com.ruitukeji.scc.adapter;

import android.content.Context;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ruitukeji.scc.R;
import com.ruitukeji.scc.entity.GoodOrderBean.ResultBean.ListBean;
import com.ruitukeji.scc.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 司导消息 适配器
 * Created by Admin on 2017/8/15.
 */

public class GoodOrderAdapter extends BGAAdapterViewAdapter<ListBean> {
    private Context context;

    public GoodOrderAdapter(Context context) {
        super(context, R.layout.item_goodsorder);
        this.context = context;
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ListBean listBean) {

        viewHolderHelper.setText(R.id.tv_good_code, listBean.getAvatar());
        viewHolderHelper.setText(R.id.tv_good_status, listBean.getAvatar());
        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getAvatar(), (ImageView) viewHolderHelper.getView(R.id.iv_goodsicon), R.mipmap.placeholderfigure1);
        viewHolderHelper.setText(R.id.tv_good_title, listBean.getAvatar());
        viewHolderHelper.setText(R.id.tv_good_datetime, listBean.getAvatar());
        viewHolderHelper.setText(R.id.tv_good_describe, listBean.getAvatar());
        viewHolderHelper.setText(R.id.tv_good_num, listBean.getAvatar());
        viewHolderHelper.setText(R.id.tv_good_paymoney, context.getResources().getString(R.string.moneySign) + listBean.getAvatar());
        TextView tv_leftbtn = (TextView) viewHolderHelper.getView(R.id.tv_leftbtn);
        TextView tv_rightbtn = (TextView) viewHolderHelper.getView(R.id.tv_rightbtn);
        HorizontalScrollView hsv_goodpic = (HorizontalScrollView) viewHolderHelper.getView(R.id.hsv_goodpic);
        ListView lv_goodpic = (ListView) viewHolderHelper.getView(R.id.lv_goodpic);
        GoodOrderPicturesAdapter adapter = new GoodOrderPicturesAdapter(context);
        lv_goodpic.setAdapter(adapter);
        adapter.clear();
//        adapter.addNewData(charterOrderBean.getResult().getList());

//        /**
//         * 图片
//         */
//        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getAvatar(), (ImageView) viewHolderHelper.getView(R.id.img_localTalent));
//
//        /**
//         * 姓名
//         */
//        viewHolderHelper.setText(R.id.tv_name, listBean.getName());
//
//        /**
//         * 地址
//         */
//        viewHolderHelper.setText(R.id.tv_address, "泰国·曼谷");
//
//        /**
//         * 类别
//         */
//        viewHolderHelper.setText(R.id.tv_sort, "泰国·曼谷");
//
//        /**
//         * 赞数
//         */
//        viewHolderHelper.setText(R.id.tv_greatNumber, "赞" + mContext.getString(R.string.zan));

    }

}