package com.yinglan.scc.mine.deliveryaddress;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.view.OptionsPickerView;
import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.StringUtils;
import com.yinglan.scc.R;
import com.yinglan.scc.entity.mine.deliveryaddress.AddressBean;
import com.yinglan.scc.loginregister.LoginActivity;

/**
 * 新添加收货地址/编辑收货地址
 * Created by Administrator on 2017/9/2.
 */

public class AddNewAddressActivity extends BaseActivity implements AddNewAddressContract.View {

    @BindView(id = R.id.et_name)
    private EditText et_name;

    @BindView(id = R.id.et_phone)
    private EditText et_phone;

    @BindView(id = R.id.ll_select, click = true)
    private LinearLayout ll_select;

    @BindView(id = R.id.tv_selectAddress)
    private TextView tv_selectAddress;

    @BindView(id = R.id.et_detailedAddress)
    private EditText et_detailedAddress;

    @BindView(id = R.id.iv_defaultAddress, click = true)
    private ImageView iv_defaultAddress;

    @BindView(id = R.id.tv_addAddress, click = true)
    private TextView tv_addAddress;

    private int def_addr = 0;//0:不作为默认地址。1：作为默认地址
    private int province_id = 0;
    private int city_id = 0;
    private int region_id = 0;
    private int town_id = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_newaddress);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new AddNewAddressPresenter(this);
        initPicker();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        String title = getIntent().getStringExtra("title");
        if (StringUtils.isEmpty(title)) {
            ActivityTitleUtils.initToolbar(aty, getString(R.string.addAddress), true, R.id.titlebar);
            tv_addAddress.setText(getString(R.string.addAddress));
        } else {
            ActivityTitleUtils.initToolbar(aty, title, true, R.id.titlebar);
            tv_addAddress.setText(getString(R.string.save));
            ((AddNewAddressContract.Presenter) mPresenter).getAddress(getIntent().getIntExtra("addr_id", 0));
        }
    }

    /**
     * 初始化地区选择
     */
    private void initPicker() {
        // OptionsPickerView optionsPickerView=new OptionsPickerView(new )


    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_selectAddress:


                break;
            case R.id.tv_addAddress:
                if (StringUtils.isEmpty(getIntent().getStringExtra("title"))) {
                    showLoadingDialog(getString(R.string.saveLoad));
                    ((AddNewAddressContract.Presenter) mPresenter).postAddAddress(et_name.getText().toString().trim(), et_phone.getText().toString().trim(),
                            province_id, city_id, region_id, town_id, et_detailedAddress.getText().toString().trim(), def_addr);
                } else {
                    showLoadingDialog(getString(R.string.saveLoad));
                    ((AddNewAddressContract.Presenter) mPresenter).postEditAddress(getIntent().getIntExtra("addr_id", 0), et_name.getText().toString().trim(), et_phone.getText().toString().trim(),
                            province_id, city_id, region_id, town_id, et_detailedAddress.getText().toString().trim(), def_addr);

                }
                break;
            case R.id.iv_defaultAddress:
                if (def_addr == 0) {
                    def_addr = 1;
                    iv_defaultAddress.setImageResource(R.mipmap.address_set_as_default_selected);
                    //     tv_default.setTextColor(getResources().getColor(R.color.greenColors));
                } else {
                    def_addr = 0;
                    iv_defaultAddress.setImageResource(R.mipmap.address_set_as_default_unselected);
                }
                break;
        }
    }


    @Override
    public void setPresenter(AddNewAddressContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        if (flag == 0) {
            AddressBean addressBean = (AddressBean) JsonUtil.json2Obj(success, AddressBean.class);
            if (addressBean.getData() == null || addressBean.getData().getAddr_id() <= 0) {
                errorMsg(getString(R.string.serverReturnsDataError), 0);
                return;
            }
            et_name.setText(addressBean.getData().getName());
            et_phone.setText(addressBean.getData().getMobile());
            tv_selectAddress.setText(addressBean.getData().getProvince() + addressBean.getData().getCity() + addressBean.getData().getRegion() + addressBean.getData().getTown());
            province_id = addressBean.getData().getProvince_id();
            city_id = addressBean.getData().getCity_id();
            region_id = addressBean.getData().getRegion_id();
            town_id = addressBean.getData().getTown_id();
            et_detailedAddress.setText(addressBean.getData().getAddr());
            if (addressBean.getData().getDef_addr() == 1) {
                iv_defaultAddress.setImageResource(R.mipmap.address_set_as_default_selected);
            } else {
                iv_defaultAddress.setImageResource(R.mipmap.address_set_as_default_unselected);
            }
        } else if (flag == 1) {


        } else if (flag == 2 || flag == 3) {
            Intent intent = getIntent();
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            showActivity(this, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }
}
