package com.yinglan.scc.homepage.goodslist.goodsdetails.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseDialog;
import com.common.cklibrary.common.ViewInject;
import com.yinglan.scc.R;

/**
 * 商品详情--------规格弹框
 * Created by Administrator on 2017/8/21.
 */

public abstract class SpecificationsBouncedDialog extends BaseDialog implements View.OnClickListener, SpecificationsBouncedContract.View {

    private int goodsid = 0;

    public SpecificationsBouncedDialog(@NonNull Context context, int goodsid) {
        super(context, R.style.MyDialog);
        this.goodsid = goodsid;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_specificationsbounced);
        initView();
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
    }

    private void initView() {
        ImageView img_cancel = (ImageView) findViewById(R.id.img_cancel);
        img_cancel.setOnClickListener(this);
        ImageView img_good = (ImageView) findViewById(R.id.img_good);

        TextView tv_inventoryEnough = (TextView) findViewById(R.id.tv_inventoryEnough);

        ListView lv_specifications = (ListView) findViewById(R.id.lv_specifications);
        // lv_specifications.setAdapter();
        TextView tv_determine = (TextView) findViewById(R.id.tv_determine);
        tv_determine.setOnClickListener(this);
        mPresenter = new SpecificationsBouncedPresenter(this);
        showLoadingDialog(mContext.getString(R.string.dataLoad));
        ((SpecificationsBouncedContract.Presenter) mPresenter).getGoodsSpec(mContext, goodsid);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_cancel:
                dismiss();
                break;



        }
    }

    public abstract void share(String platform);


    @Override
    public void setPresenter(SpecificationsBouncedContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }
}
