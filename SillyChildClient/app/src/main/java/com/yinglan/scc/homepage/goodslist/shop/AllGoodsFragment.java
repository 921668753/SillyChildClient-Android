package com.yinglan.scc.homepage.goodslist.shop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.common.cklibrary.common.BaseFragment;
import com.yinglan.scc.R;

/**
 * 全部商品
 * Created by Admin on 2017/8/21.
 */

public class AllGoodsFragment extends BaseFragment {

    private ShopActivity aty;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (ShopActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_allgoods, null);
    }














}
