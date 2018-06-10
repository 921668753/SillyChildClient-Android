package com.sillykid.app.homepage.goodslist.goodsdetails.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseDialog;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.myview.ChildListView;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.homepage.goodslist.goodsdetails.dialog.SpecificationsBean;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 商品详情--------规格弹框
 * Created by Administrator on 2017/8/21.
 */

public abstract class SpecificationsBouncedDialog extends BaseDialog implements View.OnClickListener, SpecificationsBouncedContract.View {

    private SweetAlertDialog mLoadingDialog = null;

    private int goodsid = 0;

    private int flag = 0;


    private SpecificationsBouncedContract.Presenter mPresenter;

    private TextView tv_goodNumber;

    private TextView tv_color;

    private ChildListView clv_specifications;


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
        tv_color = (TextView) findViewById(R.id.tv_color);

        clv_specifications = (ChildListView) findViewById(R.id.clv_specifications);

        TextView tv_subtract = (TextView) findViewById(R.id.tv_subtract);
        tv_subtract.setOnClickListener(this);
        tv_goodNumber = (TextView) findViewById(R.id.tv_goodNumber);

        TextView tv_add = (TextView) findViewById(R.id.tv_add);
        tv_add.setOnClickListener(this);
        //  ListView lv_specifications = (ListView) findViewById(R.id.lv_specifications);
        // lv_specifications.setAdapter();
        TextView tv_determine = (TextView) findViewById(R.id.tv_determine);
        tv_determine.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_cancel:
                dismiss();
                break;
            case R.id.tv_subtract:
                int num = StringUtils.toInt(tv_goodNumber.getText().toString());
                if (num == 1) {
                    ViewInject.toast(mContext.getString(R.string.babyNotReduced));
                    return;
                }
                tv_goodNumber.setText(String.valueOf(num - 1));
                break;
            case R.id.tv_add:
                tv_goodNumber.setText(String.valueOf(StringUtils.toInt(tv_goodNumber.getText().toString()) + 1));
                break;
            case R.id.tv_determine:
                dismiss();
                mPresenter = null;
                toDo(goodsid, flag, StringUtils.toInt(tv_goodNumber.getText().toString()));
                break;
        }
    }

    public abstract void toDo(int goodId, int flag, int num);

    public void setFlag(int flag, int have_spec) {
        this.flag = flag;
        tv_goodNumber.setText("1");
        if (have_spec != 0) {
            tv_color.setVisibility(View.VISIBLE);
            clv_specifications.setVisibility(View.VISIBLE);
            mPresenter = new SpecificationsBouncedPresenter(this);
            showLoadingDialog(mContext.getString(R.string.dataLoad));
            mPresenter.getGoodsSpec(mContext, goodsid);
            return;
        }
        tv_color.setVisibility(View.GONE);
        clv_specifications.setVisibility(View.GONE);
    }


    @Override
    public void setPresenter(SpecificationsBouncedContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        SpecificationsBean specificationsBean = (SpecificationsBean) JsonUtil.getInstance().json2Obj(success, SpecificationsBean.class);
        if (specificationsBean.getData() == null || specificationsBean.getData().size() <= 0) {
            tv_color.setVisibility(View.GONE);
            clv_specifications.setVisibility(View.GONE);
            errorMsg("", 0);
            return;
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }

    /**
     * @param title show Dialog
     */
    @SuppressWarnings("deprecation")
    @Override
    public void showLoadingDialog(String title) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE);
            mLoadingDialog.getProgressHelper().setBarColor(mContext.getResources().getColor(com.common.cklibrary.R.color.dialogLoadingColor));
            mLoadingDialog.setCancelable(false);
        }
        mLoadingDialog.setTitleText(title);
        mLoadingDialog.show();
        ((View) mLoadingDialog.getButton(SweetAlertDialog.BUTTON_CONFIRM).getParent()).setVisibility(View.GONE);
    }

    /**
     * 关闭 Dialog
     */
    @Override
    public void dismissLoadingDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            try {
                mLoadingDialog.dismiss();
            } catch (Exception e) {
                mLoadingDialog = null;
            }
        }
        mLoadingDialog = null;
    }


    @Override
    public void dismiss() {
        super.dismiss();
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            try {
                mLoadingDialog.dismiss();
            } catch (Exception e) {
                mLoadingDialog = null;
            }
        }
        mLoadingDialog = null;
        mPresenter = null;
    }
}
