package com.ruitukeji.scc.mall.shophomepageallgoods;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.common.cklibrary.common.BaseFragment;
import com.ruitukeji.scc.R;
import com.ruitukeji.scc.mall.ShopHomepageAllGoodsActivity;

/**
 * 店铺首页
 * Created by Admin on 2017/8/21.
 */

public class ShopHomepageFragment extends BaseFragment {

    private ShopHomepageAllGoodsActivity aty;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (ShopHomepageAllGoodsActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_shophomepage, null);
    }












}
