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
import com.common.cklibrary.utils.MathUtil;
import com.common.cklibrary.utils.myview.ChildListView;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.adapter.homepage.goodslist.goodsdetails.dialog.SpecificationsBouncedViewAdapter;
import com.sillykid.app.adapter.homepage.goodslist.goodsdetails.dialog.SpecificationsCvBouncedViewAdapter;
import com.sillykid.app.entity.homepage.goodslist.goodsdetails.dialog.SpecificationsBean;
import com.sillykid.app.entity.homepage.goodslist.goodsdetails.dialog.SpecificationsBouncedBean;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 商品详情--------规格弹框
 * Created by Administrator on 2017/8/21.
 */

public abstract class SpecificationsBouncedDialog extends BaseDialog implements View.OnClickListener, SpecificationsBouncedContract.View, SpecificationsBouncedViewAdapter.OnStatusListener {

    private SweetAlertDialog mLoadingDialog = null;

    private int goodsid = 0;

    private int flag = 0;

    private SpecificationsBouncedContract.Presenter mPresenter;

    private TextView tv_goodNumber;

    private ChildListView clv_specifications;

    private SpecificationsBouncedViewAdapter specificationsBouncedViewAdapter = null;

    private TextView tv_divider;

    private TextView tv_inventoryEnough;

    private TextView tv_specifications;

    private TextView tv_money;

    private int product_id = 0;

    private int specificationsSize = 0;


    public SpecificationsBouncedDialog(@NonNull Context context) {
        super(context, R.style.MyDialog);
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
        tv_specifications = (TextView) findViewById(R.id.tv_specifications);
        tv_money = (TextView) findViewById(R.id.tv_money);
        tv_inventoryEnough = (TextView) findViewById(R.id.tv_inventoryEnough);
        tv_divider = (TextView) findViewById(R.id.tv_divider);
        clv_specifications = (ChildListView) findViewById(R.id.clv_specifications);
        specificationsBouncedViewAdapter = new SpecificationsBouncedViewAdapter(mContext);
        clv_specifications.setAdapter(specificationsBouncedViewAdapter);
        specificationsBouncedViewAdapter.setOnStatusListener(this);
        TextView tv_subtract = (TextView) findViewById(R.id.tv_subtract);
        tv_subtract.setOnClickListener(this);
        tv_goodNumber = (TextView) findViewById(R.id.tv_goodNumber);
        TextView tv_add = (TextView) findViewById(R.id.tv_add);
        tv_add.setOnClickListener(this);
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
                toDo(goodsid, flag, StringUtils.toInt(tv_goodNumber.getText().toString()), product_id);
                break;
        }
    }

    public abstract void toDo(int goodId, int flag, int num, int product_id);

    public void setFlag(int flag, int goodId, int have_spec) {
        this.flag = flag;
        this.goodsid = goodId;
        tv_goodNumber.setText("1");
        if (have_spec != 0) {
            clv_specifications.setVisibility(View.VISIBLE);
            tv_divider.setVisibility(View.VISIBLE);
            tv_specifications.setVisibility(View.VISIBLE);
            mPresenter = new SpecificationsBouncedPresenter(this);
            showLoadingDialog(mContext.getString(R.string.dataLoad));
            mPresenter.getGoodsSpec(mContext, goodsid);
            return;
        }
        tv_specifications.setVisibility(View.GONE);
        tv_divider.setVisibility(View.GONE);
        clv_specifications.setVisibility(View.GONE);
    }


    @Override
    public void setPresenter(SpecificationsBouncedContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        if (flag == 0) {
            SpecificationsBouncedBean specificationsBean = (SpecificationsBouncedBean) JsonUtil.getInstance().json2Obj(success, SpecificationsBouncedBean.class);
            if (specificationsBean.getData() == null || specificationsBean.getData().size() <= 0) {
                tv_divider.setVisibility(View.GONE);
                tv_specifications.setVisibility(View.GONE);
                clv_specifications.setVisibility(View.GONE);
                errorMsg("", 0);
                return;
            }
            specificationsBouncedViewAdapter.clear();
            specificationsBouncedViewAdapter.addNewData(specificationsBean.getData());
            tv_specifications.setText(mContext.getString(R.string.pleaseSelect) + unselectedSpecification());
        } else if (flag == 1) {
            SpecificationsBean specificationsBean = (SpecificationsBean) JsonUtil.getInstance().json2Obj(success, SpecificationsBean.class);
            if (specificationsBean.getData() != null && StringUtils.toInt(specificationsBean.getData().getEnable_store(), 0) > 0 && specificationsBouncedViewAdapter.getData().size() == specificationsSize) {
                tv_specifications.setText(specificationsBean.getData().getSpecs());
                tv_money.setText(mContext.getString(R.string.renminbi) + MathUtil.keepTwo(StringUtils.toDouble(specificationsBean.getData().getPrice())));
                tv_inventoryEnough.setText(mContext.getString(R.string.inventory) + specificationsBean.getData().getEnable_store() + mContext.getString(R.string.jian));
            } else if (specificationsBean.getData() != null && StringUtils.toInt(specificationsBean.getData().getEnable_store(), 0) > 0) {
                tv_specifications.setText(specificationsBean.getData().getSpecs());
                tv_money.setText(mContext.getString(R.string.renminbi) + MathUtil.keepTwo(StringUtils.toDouble(specificationsBean.getData().getPrice())));
                tv_inventoryEnough.setText(mContext.getString(R.string.inventory) + specificationsBean.getData().getEnable_store() + mContext.getString(R.string.jian));
            } else {
                tv_money.setText(mContext.getString(R.string.renminbi) + MathUtil.keepTwo(StringUtils.toDouble(specificationsBean.getData().getPrice())));
                tv_specifications.setText("");
                tv_inventoryEnough.setText(mContext.getString(R.string.inventory) + mContext.getString(R.string.insufficient));
            }
            product_id = specificationsBean.getData().getProduct_id();
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }

    @Override
    public void onSetStatusListener(View view, SpecificationsCvBouncedViewAdapter adapter, int position, int position1) {
        int specifications = -1;
        for (int i = 0; i < adapter.getData().size(); i++) {
            if (position1 == i && adapter.getData().get(i).getIsSelected() == 0) {
                adapter.getData().get(i).setIsSelected(1);
                specifications = adapter.getData().get(position1).getSpec_value_id();
            } else if (position1 == i && adapter.getData().get(i).getIsSelected() == 1) {
                adapter.getData().get(i).setIsSelected(0);

            } else {
                adapter.getData().get(i).setIsSelected(0);
            }
        }
        adapter.notifyDataSetChanged();
        if (specifications <= 0) {

            return;
        }
        String specifications1 = "";
        specificationsSize = 0;
        for (int i = 0; i < specificationsBouncedViewAdapter.getData().size(); i++) {
            for (int j = 0; j < specificationsBouncedViewAdapter.getData().get(i).getSpecValueIds().size(); j++) {
                if (specificationsBouncedViewAdapter.getData().get(i).getSpecValueIds().get(j).getIsSelected() == 1) {
                    specificationsSize++;
                    specifications1 = specifications1 + "," + specificationsBouncedViewAdapter.getData().get(i).getSpecValueIds().get(j).getSpec_value_id();
                }
            }
        }
        if (StringUtils.isEmpty(specifications1) || StringUtils.isEmpty(specifications1.substring(1)) || specifications1.substring(1).length() <= 0) {
            return;
        }
        ((SpecificationsBouncedContract.Presenter) mPresenter).getGoodsProductSpec(mContext, goodsid, specifications1.substring(1));
    }

    /**
     * 获取未选择规格
     */
    private String unselectedSpecification() {
        String specifications = "";
        int color = 0;
        int size = 0;
        int capacity = 0;
        for (int i = 0; i < specificationsBouncedViewAdapter.getData().size(); i++) {
            for (int j = 0; j < specificationsBouncedViewAdapter.getData().get(i).getSpecValueIds().size(); j++) {
                if (specificationsBouncedViewAdapter.getData().get(i).getSpecValueIds().get(j).getIsSelected() == 0 && specificationsBouncedViewAdapter.getData().get(i).getSpec_id() == 1 && color == 0) {
                    color = 1;
                    specifications = specifications + "、" + mContext.getString(R.string.color);
                } else if (specificationsBouncedViewAdapter.getData().get(i).getSpecValueIds().get(j).getIsSelected() == 0 && specificationsBouncedViewAdapter.getData().get(i).getSpec_id() == 2 && size == 0) {
                    size = 1;
                    specifications = specifications + "、" + mContext.getString(R.string.size);
                } else if (specificationsBouncedViewAdapter.getData().get(i).getSpecValueIds().get(j).getIsSelected() == 0 && specificationsBouncedViewAdapter.getData().get(i).getSpec_id() == 3 && capacity == 0) {
                    capacity = 1;
                    specifications = specifications + "、" + mContext.getString(R.string.capacity);
                }
            }
        }
        if (StringUtils.isEmpty(specifications) || StringUtils.isEmpty(specifications.substring(1))) {
            return "";
        }
        return specifications.substring(1);
    }


    /**
     * @param title show Dialog
     */
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
