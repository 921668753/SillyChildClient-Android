package com.yinglan.scc.mine.deliveryaddress;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.kymjs.common.PreferenceHelper;
import com.yinglan.scc.R;
import com.yinglan.scc.loginregister.LoginActivity;

/**
 * 新添加收货地址
 * Created by Administrator on 2017/9/2.
 */

public class NewAddressActivity extends BaseActivity implements MineAddressContract.View,TextWatcher,View.OnTouchListener{

    private MineAddressContract.Presenter mPresenter;

    @BindView(id=R.id.et_name)
    private EditText et_name;

    @BindView(id=R.id.et_phone)
    private EditText et_phone;

    @BindView(id=R.id.ll_select,click = true)
    private LinearLayout ll_select;
    @BindView(id=R.id.tv_selectaddress,click = true)
    private TextView tv_selectaddress;

    @BindView(id=R.id.et_address)
    private EditText et_address;

    @BindView(id=R.id.ll_defaultaddress ,click = true)
    private LinearLayout ll_defaultaddress;
    @BindView(id=R.id.tv_default)
    private TextView tv_default;
    @BindView(id=R.id.iv_defaultaddress)
    private ImageView iv_defaultaddress;

    @BindView(id=R.id.tv_save ,click = true)
    private TextView tv_save;

    private boolean editname;
    private boolean nameover;
    private boolean editphone;
    private boolean phoneover;
    private boolean selectaddress;
    private boolean selectover;
    private boolean editaddress;
    private boolean addressover;
    private String[] addressall;//选择器选择的地区的数组
    private String provincestr;//选择器选择出的省
    private String citystr;//选择器选择出的市
    private String districtstr;//选择器选择出的区县
    private int isdefault=0;//0:不作为默认地址。1：作为默认地址
    private InputMethodManager inputmanager;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_newaddress);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter=new MineAddressPresenter(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        tv_save.setClickable(false);
        et_name.addTextChangedListener(this);
        et_name.setOnTouchListener(this);
        et_phone.addTextChangedListener(this);
        et_phone.setOnTouchListener(this);
        tv_selectaddress.addTextChangedListener(this);
        tv_selectaddress.setOnTouchListener(this);
        et_address.addTextChangedListener(this);
        et_address.setOnTouchListener(this);

    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()){
            case R.id.tv_selectaddress:
                setCursor();
                break;
            case R.id.tv_save:
                setCursor();
                ViewInject.toast("待开发，敬请期待");
                break;
            case R.id.ll_defaultaddress:
                setCursor();
                if (isdefault==0){
                    isdefault=1;
                    iv_defaultaddress.setImageResource(R.mipmap.mineaddress_selectxxx);
                    tv_default.setTextColor(getResources().getColor(R.color.greenColors));
                }else{
                    isdefault=0;
                    iv_defaultaddress.setImageResource(R.mipmap.mineaddress_unselectxxx);
                    tv_default.setTextColor(getResources().getColor(R.color.deliveryaddressdefault));
                }

                break;
        }
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.addAddress), true, R.id.titlebar);
    }

    @Override
    public void setPresenter(MineAddressContract.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {

    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)){
            ViewInject.toast(getString(R.string.reloginPrompting));
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", false);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isReLogin", true);
            showActivity(this,LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editname){
            if (TextUtils.isEmpty(editable.toString())){
                nameover=false;
            }else{
                nameover=true;
            }
            controlSave();
        }else if (editphone){
            if (TextUtils.isEmpty(editable.toString())){
                phoneover=false;
            }else{
                phoneover=true;
            }
            controlSave();
        }else if (selectaddress){
            if (TextUtils.isEmpty(editable.toString())){
                selectover=false;
            }else{
                selectover=true;
            }
            controlSave();
        }else if(editaddress){
            if (TextUtils.isEmpty(editable.toString())){
                addressover=false;
            }else{
                addressover=true;
            }
            controlSave();
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction()==MotionEvent.ACTION_DOWN){
            editname=false;
            editphone=false;
            selectaddress=false;
            editaddress=false;
            switch (view.getId()){
                case R.id.et_name:
                    et_name.setCursorVisible(true);
                    editname=true;
                    break;
                case R.id.et_phone:
                    et_phone.setCursorVisible(true);
                    editphone=true;
                    break;
                case R.id.tv_selectaddress:
                    selectaddress=true;
                    break;
                case R.id.et_address:
                    et_address.setCursorVisible(true);
                    editaddress=true;
                    break;
            }
        }
        return false;
    }

    /**
     * 修改所填写的内容是否填写完成，并控制保存按钮的状态
     */
    private void controlSave(){
        if (nameover&&phoneover&&selectover&&addressover){
            tv_save.setClickable(true);
            tv_save.setBackgroundResource(R.drawable.shape_login1);
        }else{
            tv_save.setClickable(false);
            tv_save.setBackgroundResource(R.drawable.shape_login);
        }
    }



    private void setCursor(){
        et_name.setCursorVisible(false);
        et_phone.setCursorVisible(false);
        et_address.setCursorVisible(false);
        if (inputmanager==null) inputmanager=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputmanager.hideSoftInputFromWindow(tv_default.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
