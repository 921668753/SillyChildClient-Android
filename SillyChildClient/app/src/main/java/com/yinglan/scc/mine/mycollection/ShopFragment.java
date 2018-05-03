package com.yinglan.scc.mine.mycollection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.common.cklibrary.common.BaseFragment;
import com.yinglan.scc.R;

/**我的收藏中的店铺
 * Created by Administrator on 2017/9/2.
 */

public class ShopFragment extends BaseFragment {
    private MyCollectionActivity aty;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MyCollectionActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_shop, null);
    }
}
