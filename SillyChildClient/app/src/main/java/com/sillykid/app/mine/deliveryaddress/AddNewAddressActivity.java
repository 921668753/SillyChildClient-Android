package com.sillykid.app.mine.deliveryaddress;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.mine.deliveryaddress.AddNewAddressBean;
import com.sillykid.app.entity.mine.deliveryaddress.RegionListBean;
import com.sillykid.app.entity.mine.deliveryaddress.RegionListBean.DataBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.SoftKeyboardUtils;

import java.util.ArrayList;
import java.util.List;

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
    private String province = "";
    private String city = "";
    private String region = "";
    private int town_id = 0;
    private OptionsPickerView pvNoLinkOptions = null;
    private List<DataBean> provinceList = null;
    private List<DataBean> cityList = null;
    private List<DataBean> areaList = null;
    private int areaOptions3 = 0;
    private int cityOptions2 = 0;
    private int provinceOptions1 = 0;


    private OptionsPickerView pvLinkOptions = null;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_newaddress);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new AddNewAddressPresenter(this);
        provinceList = new ArrayList<DataBean>();
        cityList = new ArrayList<DataBean>();
        areaList = new ArrayList<DataBean>();
        initNoLinkOptionsPicker();
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
            getSuccess("", 0);
        }
        ((AddNewAddressContract.Presenter) mPresenter).getAddress(0);
        ((AddNewAddressContract.Presenter) mPresenter).getRegionList(0, 3);
    }

    /**
     * 初始化地区选择
     */
    private void initNoLinkOptionsPicker() {// 不联动的多级选项
        pvNoLinkOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                province_id = provinceList.get(options1).getRegion_id();
                city_id = cityList.get(options2).getRegion_id();
                region_id = areaList.get(options3).getRegion_id();
                ((TextView) v).setText(provinceList.get(options1).getLocal_name() + cityList.get(options2).getLocal_name() + areaList.get(options3).getLocal_name());
            }
        })
                .setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
                    @Override
                    public void onOptionsSelectChanged(int options1, int options2, int options3) {
                        if (provinceOptions1 == options1 && cityOptions2 == options2) {
                            areaOptions3 = options3;
                            return;
                        }
                        if (provinceOptions1 == options1 && cityOptions2 != options2) {
                            cityOptions2 = options2;
                            areaOptions3 = 0;
                            getRegionList(cityList, cityList.get(options2).getLocal_name(), 5);
                            return;
                        }
                        provinceOptions1 = options1;
                        cityOptions2 = 0;
                        areaOptions3 = 0;
                        getRegionList(provinceList, provinceList.get(options1).getLocal_name(), 4);
                    }
                })
                .build();
    }

    /**
     * 联动地区选择
     */
    private void initLinkOptionsPicker() {
        pvLinkOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                province_id = provinceList.get(options1).getRegion_id();
                city_id = cityList.get(options2).getRegion_id();
                region_id = areaList.get(options3).getRegion_id();
                ((TextView) v).setText(provinceList.get(options1).getLocal_name() + cityList.get(options2).getLocal_name() + areaList.get(options3).getLocal_name());
            }
        }).build();
      //  pvLinkOptions.setPicker()

    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_select:
                SoftKeyboardUtils.packUpKeyboard(aty);
                pvNoLinkOptions.show(tv_selectAddress);
                break;
            case R.id.tv_addAddress:
                SoftKeyboardUtils.packUpKeyboard(aty);
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
                SoftKeyboardUtils.packUpKeyboard(aty);
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
//            AddressBean addressBean = (AddressBean) JsonUtil.json2Obj(success, AddressBean.class);
//            if (addressBean.getData() == null || addressBean.getData().getAddr_id() <= 0) {
//                errorMsg(getString(R.string.serverReturnsDataError), 0);
//                return;
//            }
            Intent intent = getIntent();
            et_name.setText(intent.getStringExtra("name"));
            et_phone.setText(intent.getStringExtra("mobile"));
            province = intent.getStringExtra("province");
            city = intent.getStringExtra("city");
            region = intent.getStringExtra("region");
            tv_selectAddress.setText(province + city + region);
            province_id = intent.getIntExtra("province_id", 0);
            city_id = intent.getIntExtra("city_id", 0);
            region_id = intent.getIntExtra("region_id", 0);
            // town_id = addressBean.getData().getTown_id();
            et_detailedAddress.setText(intent.getStringExtra("addr"));
            if (intent.getIntExtra("def_addr", 0) == 1) {
                iv_defaultAddress.setImageResource(R.mipmap.address_set_as_default_selected);
            } else {
                iv_defaultAddress.setImageResource(R.mipmap.address_set_as_default_unselected);
            }
        } else if (flag == 1) {
            Intent intent = getIntent();
            setResult(RESULT_OK, intent);
            finish();
        } else if (flag == 2) {
            AddNewAddressBean addNewAddressBean = (AddNewAddressBean) JsonUtil.getInstance().json2Obj(success, AddNewAddressBean.class);
            Intent intent = getIntent();
            if (addNewAddressBean.getData() != null && addNewAddressBean.getData().getAddr_id() > 0) {
                intent.putExtra("addr_id", addNewAddressBean.getData().getAddr_id());
                intent.putExtra("name", addNewAddressBean.getData().getName());
                intent.putExtra("mobile", addNewAddressBean.getData().getMobile());
                intent.putExtra("provinceRegion", addNewAddressBean.getData().getProvince() + "  " + addNewAddressBean.getData().getCity() + "  " + addNewAddressBean.getData().getRegion());
                intent.putExtra("addr", addNewAddressBean.getData().getAddr());
            }
            setResult(RESULT_OK, intent);
            finish();
        } else if (flag == 3) {
            RegionListBean regionListBean = (RegionListBean) JsonUtil.json2Obj(success, RegionListBean.class);
            if (regionListBean.getData() != null && regionListBean.getData().size() > 0) {
                provinceList.addAll(regionListBean.getData());
                getRegionList(provinceList, province, 4);
            }
        } else if (flag == 4) {
            RegionListBean regionListBean = (RegionListBean) JsonUtil.json2Obj(success, RegionListBean.class);
            if (regionListBean.getData() != null && regionListBean.getData().size() > 0) {
                cityList.clear();
                cityList.addAll(regionListBean.getData());
                getRegionList(cityList, city, 5);
            }
        } else if (flag == 5) {
            RegionListBean regionListBean = (RegionListBean) JsonUtil.json2Obj(success, RegionListBean.class);
            if (regionListBean.getData() != null && regionListBean.getData().size() > 0) {
                areaList.clear();
                areaList.addAll(regionListBean.getData());
                getRegionList(areaList, region, 6);
            } else {
                areaList.clear();
                DataBean dataBean = new DataBean();
                dataBean.setLocal_name("");
                areaList.add(dataBean);
            }
            pvNoLinkOptions.setNPicker(provinceList, cityList, areaList);
            pvNoLinkOptions.setSelectOptions(provinceOptions1, cityOptions2, areaOptions3);
        }
    }

    /**
     * 获取地区二级列表
     */
    private void getRegionList(List<DataBean> list, String positionName, int flag) {
        for (int i = 0; i < list.size(); i++) {
            if (positionName.contains(list.get(i).getLocal_name()) && flag == 4 || StringUtils.isEmpty(positionName) && i == 0 && flag == 4) {
                provinceOptions1 = i;
                ((AddNewAddressContract.Presenter) mPresenter).getRegionList(list.get(i).getRegion_id(), flag);
                return;
            } else if (positionName.contains(list.get(i).getLocal_name()) && flag == 5 || StringUtils.isEmpty(positionName) && i == 0 && flag == 5) {
                cityOptions2 = i;
                ((AddNewAddressContract.Presenter) mPresenter).getRegionList(list.get(i).getRegion_id(), flag);
                return;
            } else if (positionName.contains(list.get(i).getLocal_name()) && flag == 6 || StringUtils.isEmpty(positionName) && i == 0 && flag == 6) {
                areaOptions3 = i;
                return;
            }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (pvNoLinkOptions != null && pvNoLinkOptions.isShowing()) {
            pvNoLinkOptions.dismiss();
        }
        pvNoLinkOptions = null;
        provinceList.clear();
        provinceList = null;
        cityList.clear();
        cityList = null;
        areaList.clear();
        areaList = null;
    }
}
