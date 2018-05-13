package com.yinglan.scc.adapter.main;

import android.content.Context;
import android.text.TextUtils;

import com.yinglan.scc.R;
import com.yinglan.scc.adapter.addressselection.CommonAdapter;
import com.yinglan.scc.adapter.addressselection.ViewHolder;
import com.yinglan.scc.entity.main.MallHomePageBean.ResultBean.ListBean;

import java.util.List;

/**
 * 首页——商品列表  适配器
 * Created by Administrator on 2017/9/6.
 */

public class MallHomePageGood1Adapter extends CommonAdapter<ListBean> {

    //  private List<ListBean> list;
    private Context mcontext;

    public MallHomePageGood1Adapter(Context context, int layoutId, List<ListBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, ListBean listBean) {
        holder.setText(R.id.tv_goodName, listBean.getTitle());
        holder.setText(R.id.tv_goodSynopsis, listBean.getTitle());
        holder.setText(R.id.tv_goodMoney, listBean.getTitle());
        holder.setText(R.id.tv_brand, listBean.getTitle());
      //  GlideImageLoader.glideOrdinaryLoader(mcontext, listBean.getImgs(), holder.getView(R.id.img_good), R.mipmap.placeholderfigure);
//        goodsListView.tv_goodName.setText(list.get(position).getTitle());
//        goodsListView.tv_goodSynopsis.setText(list.get(position).getPriceFmt());
//        goodsListView.tv_goodMoney.setText(list.get(position).getTitle());
//        goodsListView.tv_brand.setText(list.get(position).getTitle());

        if (TextUtils.isEmpty(listBean.getTitle())) {
            holder.setVisible(R.id.ll_bottomLabel, true);
            holder.setVisible(R.id.tv_proprietary, false);
            holder.setVisible(R.id.tv_timedSpecials, true);
        } else {
            holder.setVisible(R.id.ll_bottomLabel, false);
            holder.setVisible(R.id.tv_proprietary, false);
            holder.setVisible(R.id.tv_timedSpecials, true);
        }
    }







    //   private GoodsListItemOnClickListener listener;

//    public MallHomePageGoodAdapter(Context mcontext, List<ListBean> list, GoodsListItemOnClickListener listener) {
//        this.list = list;
//        this.mcontext = mcontext;
//        this.listener = listener;
//    }
//    public MallHomePageGoodAdapter(Context context, int layoutId, List<ListBean> datas) {
//        super(context, layoutId, datas);
//    }
//    @Override
//    public GoodsListView onCreateViewHolder(ViewGroup viewGroup, int i) {
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_mallhomepage, viewGroup, false);
//        GoodsListView goodsListView = new GoodsListView(view, listener);
//        return goodsListView;
//    }

//    @Override
//    public void convert(ViewHolder holder, GoodsListView goodsListView) {
//
//    }
//
////    @Override
////    public void onBindViewHolder(GoodsListView goodsListView, int position) {
////
////    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    public class GoodsListView extends RecyclerView.ViewHolder implements View.OnClickListener {
//
//        ImageView img_good;
//        TextView tv_goodName;
//        TextView tv_goodSynopsis;
//        TextView tv_goodMoney;
//        TextView tv_brand;
//        LinearLayout ll_bottomLabel;
//        TextView tv_proprietary;
//        TextView tv_timedSpecials;
//
//        private GoodsListItemOnClickListener itemlistener;
//
//        public GoodsListView(View itemView, GoodsListItemOnClickListener listener) {
//            super(itemView);
//            img_good = (ImageView) itemView.findViewById(R.id.img_good);
//            tv_goodName = (TextView) itemView.findViewById(R.id.tv_goodName);
//            tv_goodSynopsis = (TextView) itemView.findViewById(R.id.tv_goodSynopsis);
//            tv_goodMoney = (TextView) itemView.findViewById(R.id.tv_goodMoney);
//            tv_brand = (TextView) itemView.findViewById(R.id.tv_brand);
//            ll_bottomLabel = (LinearLayout) itemView.findViewById(R.id.ll_bottomLabel);
//            tv_proprietary = (TextView) itemView.findViewById(R.id.tv_proprietary);
//            tv_timedSpecials = (TextView) itemView.findViewById(R.id.tv_timedSpecials);
//            this.itemlistener = listener;
//            itemView.setOnClickListener(this);
//        }
//
//
//        @Override
//        public void onClick(View view) {
//            if (itemlistener != null) itemlistener.goodsListOnItemClick(view, getPosition());
//        }
//    }
//
//    public interface GoodsListItemOnClickListener {
//        void goodsListOnItemClick(View view, int postion);
//
//    }

}
