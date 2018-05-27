package com.yinglan.scc.adapter.mine.mywallet;

import android.content.Context;

import com.yinglan.scc.R;
import com.yinglan.scc.entity.mine.mywallet.accountdetails.AccountDetailsBean.ResultBean.ListBean;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 我的  我的钱包 账户明细  适配器
 * Created by Admin on 2017/9/8.
 */

public class AccountDetailsAdapter extends BGAAdapterViewAdapter<ListBean> {


    public AccountDetailsAdapter(Context context) {
        super(context, R.layout.item_accountdetails);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, ListBean model) {

        /**
         * 明细类型
         */
        helper.setText(R.id.tv_detailstype, model.getRemark());
        /**
         * 明细类型
         */
        helper.setText(R.id.tv_changemoney, model.getChangeMoney());
        /**
         * 明细类型
         */
        helper.setText(R.id.tv_timefmt, model.getTimeFmt());
        /**
         * 明细类型
         */
        helper.setText(R.id.tv_userbalance, model.getUserBalance());
    }
}
