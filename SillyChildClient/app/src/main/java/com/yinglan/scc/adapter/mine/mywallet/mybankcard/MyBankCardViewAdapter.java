package com.yinglan.scc.adapter.mine.mywallet.mybankcard;

import android.content.Context;

import com.yinglan.scc.R;
import com.yinglan.scc.entity.mine.mywallet.mybankcard.MyBankCardBean.ResultBean;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;


/**
 * 我的银行卡列表适配器
 * Created by Administrator on 2017/12/22.
 */

public class MyBankCardViewAdapter extends BGAAdapterViewAdapter<ResultBean> {

    public MyBankCardViewAdapter(Context context) {
        super(context, R.layout.item_bankcard);
    }

    @Override
    public void fillData(BGAViewHolderHelper helper, int position, ResultBean resultBean) {
        helper.setText(R.id.tv_bankCardName, resultBean.getBank());
        helper.setText(R.id.tv_tail, resultBean.getBank_card());
    }
}
