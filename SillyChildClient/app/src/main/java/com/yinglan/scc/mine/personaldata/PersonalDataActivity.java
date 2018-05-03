package com.yinglan.scc.mine.personaldata;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.BitmapCoreUtil;
import com.common.cklibrary.utils.DataCleanManager;
import com.common.cklibrary.utils.GlideCatchUtil;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.nanchen.compresshelper.FileUtil;
import com.yinglan.scc.R;
import com.yinglan.scc.constant.NumericConstants;
import com.yinglan.scc.dialog.PictureSourceDialog;
import com.yinglan.scc.dialog.ServicePhoneDialog;
import com.yinglan.scc.dialog.ShareBouncedDialog;
import com.yinglan.scc.dialog.VIPPermissionsDialog;
import com.yinglan.scc.entity.UploadImageBean;
import com.yinglan.scc.entity.UserInfoBean;
import com.yinglan.scc.homepage.addressselection.AddressSelectionActivity;
import com.yinglan.scc.loginregister.LoginActivity;
import com.yinglan.scc.loginregister.forgotpassword.ForgotPasswordActivity;
import com.yinglan.scc.mine.personaldata.bindemail.BindEmailActivity;
import com.yinglan.scc.mine.personaldata.setnickname.SetNickNameActivity;
import com.yinglan.scc.mine.personaldata.setsignature.SetSignatureActivity;
import com.yinglan.scc.mine.personaldata.setsillycode.SetSillyCodeActivity;
import com.yinglan.scc.utils.GlideImageLoader;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.yinglan.scc.constant.NumericConstants.STATUS;

/**
 * 个人资料
 * Created by Administrator on 2017/9/2.
 */

public class PersonalDataActivity extends BaseActivity implements PersonalDataContract.View {
    private UserInfoBean.ResultBean  resultBean;
    private PersonalDataContract.Presenter mPresenter;

    @BindView(id = R.id.ll_personaldatatx,click = true)
    private LinearLayout ll_personaldatatx;

    @BindView(id = R.id.ll_personaldatanc,click = true)
    private LinearLayout ll_personaldatanc;

    @BindView(id = R.id.ll_personaldataxb,click = true)
    private LinearLayout ll_personaldataxb;

    @BindView(id = R.id.ll_personaldatasr,click = true)
    private LinearLayout ll_personaldatasr;

    @BindView(id = R.id.ll_personaldatadq,click = true)
    private LinearLayout ll_personaldatadq;

    @BindView(id = R.id.ll_personaldatabdyx,click = true)
    private LinearLayout ll_personaldatabdyx;

    @BindView(id = R.id.ll_personaldatagxqm,click = true)
    private LinearLayout ll_personaldatagxqm;

    @BindView(id = R.id.ll_personaldataxgmm,click = true)
    private LinearLayout ll_personaldataxgmm;

    @BindView(id = R.id.iv_personaltx,click = true)
    private ImageView iv_personaltx;

    @BindView(id = R.id.tv_personalnickname,click = true)
    private TextView tv_personalnickname;

    @BindView(id = R.id.et_personalcode , click = true)
    private TextView et_personalcode;

    @BindView(id = R.id.tv_personalsex,click = true)
    private TextView tv_personalsex;

    @BindView(id = R.id.tv_personalbirthday,click = true)
    private TextView tv_personalbirthday;

    @BindView(id = R.id.tv_personaldiqu,click = true)
    private TextView tv_personaldiqu;

    @BindView(id = R.id.tv_personalemail,click = true)
    private TextView tv_personalemail;

    @BindView(id = R.id.tv_personalqianming,click = true)
    private TextView tv_personalqianming;
    private PictureSourceDialog pictureSourceDialog;
    private ServicePhoneDialog servicephonedialog;
    private VIPPermissionsDialog vippermissionsdialog;
    private ShareBouncedDialog shardebounceddialog;
    private TimePickerView pvTime;
    private OptionsPickerView pvOptions;
    private SimpleDateFormat dateformat;
    private String fixbirthday;//修改的生日
    private String altersex;//修改的性别
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    private boolean isOrigin;
    private ImagePicker imagePicker;
    private String touxiangpath;//所更换的头像的路径
    private UploadImageBean uploadimagebean;
    private long birthday;//生日
    private Calendar birthdaycalendar;//生日
    private String pickeraddress;//选择器返回的地址
    private boolean issend;//发出了修改傻孩子号的标识
    private String shzcode;//傻孩子号
    private InputMethodManager inputmanager;
    private Intent jumpintent;
    private int updatanum=0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_personaldata);
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        super.initData();
        try {
            UserInfoBean userInfoBean=(UserInfoBean)getIntent().getExtras().get("userinfo");
            resultBean=userInfoBean.getResult();
        }catch (Exception e){

        }
        mPresenter = new PersonalDataPresenter(this);

        initImagePicker();
    }

    /**
     * 渲染控件
     */
    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
//        et_personalcode.setOnTouchListener(this);
//        et_personalcode.setCursorVisible(false);
//        et_personalcode.setOnEditorActionListener(this);
        if (resultBean==null){
            ViewInject.toast(getString(R.string.getDataError));
//            finish();
        }else{
            tv_personalnickname.setText(resultBean.getNickname());
            shzcode=resultBean.getShz_code();
            et_personalcode.setText(resultBean.getShz_code());
            updatanum=StringUtils.toInt(resultBean.getShz_update(),0);
            switch (resultBean.getSex()){
                case 1:
                    tv_personalsex.setText(getString(R.string.nan));
                    break;
                case 2:
                    tv_personalsex.setText(getString(R.string.nv));
                    break;
                case 0:
                    tv_personalsex.setText(getString(R.string.secret));
                    break;
            }
            birthday=resultBean.getBirthday();
            tv_personalbirthday.setText(formatData());
            String address="";
            if (!TextUtils.isEmpty(resultBean.getCountry())){
                address+=resultBean.getCountry()+"•";
            }
            if (!TextUtils.isEmpty(resultBean.getCity())){
                address+=resultBean.getCity();
            }
            tv_personaldiqu.setText(address);
            tv_personalemail.setText(resultBean.getEmail());
            tv_personalqianming.setText(resultBean.getPersonalized_signature());

            String headpic=resultBean.getHead_pic();
            if (TextUtils.isEmpty(headpic)){
                iv_personaltx.setImageResource(R.mipmap.avatar);
            }else{
                GlideImageLoader.glideLoader(aty,headpic,iv_personaltx,0,R.mipmap.avatar);
            }
        }
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()){
            case R.id.iv_personaltx:
            case R.id.ll_personaldatatx:
                updataSHZCode();
                PictureDialog();
                break;
            case R.id.tv_personalnickname:
            case R.id.ll_personaldatanc:
                updataSHZCode();
                jumpintent=new Intent(this,SetNickNameActivity.class);
                jumpintent.putExtra("nickname",tv_personalnickname.getText().toString());
                showActivityForResult(this, jumpintent,0);
                break;
            case R.id.et_personalcode:
                if (updatanum<2){
                    updataSHZCode();
                    jumpintent=new Intent(this,SetSillyCodeActivity.class);
                    jumpintent.putExtra("nickname",et_personalcode.getText().toString());
                    showActivityForResult(this, jumpintent,3);
                }else{
                    ViewInject.toast(getString(R.string.sillyChildNumberNoChange));
                }
                break;
            case R.id.tv_personalsex:
            case R.id.ll_personaldataxb:
                updataSHZCode();
                selectModels();
                break;
            case R.id.tv_personalbirthday:
            case R.id.ll_personaldatasr:
                updataSHZCode();
                departureTime();
                break;
            case R.id.tv_personaldiqu:
            case R.id.ll_personaldatadq:
                updataSHZCode();
                jumpintent = new Intent(this, AddressSelectionActivity.class);
                jumpintent.putExtra("showinland",true);
                startActivityForResult(jumpintent, STATUS);
                break;
            case R.id.tv_personalemail:
            case R.id.ll_personaldatabdyx:
                updataSHZCode();
                showActivityForResult(this, BindEmailActivity.class,1);
                break;
            case R.id.tv_personalqianming:
            case R.id.ll_personaldatagxqm:
                updataSHZCode();
                jumpintent=new Intent(this,SetSignatureActivity.class);
                jumpintent.putExtra("signature",tv_personalqianming.getText().toString());
                showActivityForResult(this, jumpintent,2);
                break;
            case R.id.ll_personaldataxgmm:
                updataSHZCode();
                jumpintent=new Intent(this,ForgotPasswordActivity.class);
                jumpintent.putExtra("title",getString(R.string.changepassword));
                showActivity(this,jumpintent);
                break;
        }
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.personaData), true, R.id.titlebar);
    }

    /**
     * 选择更换头像的弹窗
     */
    public void PictureDialog() {

        if (pictureSourceDialog==null){
            pictureSourceDialog= new PictureSourceDialog(aty) {
                @Override
                public void takePhoto() {
                    takeTouxiang();
                }

                @Override
                public void chooseFromAlbum() {
                    selectPicture();
                }
            };
        }
        pictureSourceDialog.show();

    }

    /**
     * 初始化图片选择器
     */
    private void initImagePicker(){
        imagePicker = ImagePicker.getInstance();
        GlideImageLoader glideImageLoader=new GlideImageLoader();
        imagePicker.setImageLoader(glideImageLoader);   //设置图片加载器
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(NumericConstants.MAXPICTURE);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.CIRCLE);  //裁剪框的形状
        imagePicker.setFocusWidth(600);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(600);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
//        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
//        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
        imagePicker.setMultiMode(false);//设置为单选模式，默认多选
        imagePicker.setShowCamera(false);//设置显示相机，默认显示
    }

    private void takeTouxiang(){
        Intent intent = new Intent(this, ImageGridActivity.class);
        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
        startActivityForResult(intent, REQUEST_CODE_SELECT);
    }

    private void selectPicture(){
        //打开选择,本次允许选择的数量
        ImagePicker.getInstance().setSelectLimit(1);
        Intent intent1 = new Intent(this, ImageGridActivity.class);
        /* 如果需要进入选择的时候显示已经选中的图片，
         * 详情请查看ImagePickerActivity
         * */
//      intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
        startActivityForResult(intent1, REQUEST_CODE_SELECT);
    }

    private void upTouXiang(){
        showLoadingDialog(getString(R.string.saveLoad));
        File oldFile = new File(touxiangpath);
        if (!(FileUtil.isFileExists(oldFile))) {
            ViewInject.toast(KJActivityStack.create().topActivity().getString(R.string.imagePathError));
            return;
        }

        long fileSize = 0;
        try {
            fileSize = DataCleanManager.getFileSize(oldFile);
        } catch (Exception e) {
            e.printStackTrace();
            fileSize = 0;
            return;
        }
        if (fileSize >= StringConstants.COMPRESSION_SIZE) {
            oldFile = BitmapCoreUtil.customCompression(oldFile);
        }

        mPresenter.upPictures("file",oldFile,0);

    }

    /**
     * 选择性别
     */
    @SuppressWarnings("unchecked")
    private void selectModels() {
        if (pvOptions==null){
            ArrayList<String> options1Items = new ArrayList<String>();
            options1Items.add(getString(R.string.nan));
            options1Items.add(getString(R.string.nv));
            options1Items.add(getString(R.string.secret));
            pvOptions = new OptionsPickerView.Builder(aty, new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
                    altersex = options1Items.get(options1).toString();
                    showLoadingDialog(getString(R.string.saveLoad));
                    switch (altersex){
                        case "男":
                            mPresenter.setupInfo("sex",1+"",2);
                            break;
                        case "女":
                            mPresenter.setupInfo("sex",2+"",2);
                            break;
                        case "保密":
                            mPresenter.setupInfo("sex",0+"",2);
                            break;
                    }
                }
            }).build();
            pvOptions.setPicker(options1Items);
        }
        pvOptions.show();

    }

    /**
     * 选择时间的控件
     */
    private void departureTime() {
        if (pvTime==null){
            //TimePicker
            //控制时间范围
            boolean[] type = new boolean[]{true, true, true, false, false, false};
            pvTime = new TimePickerView.Builder(aty, new TimePickerView.OnTimeSelectListener() {

                @Override
                public void onTimeSelect(Date date, View v) {//选中事件回调
                    birthday=date.getTime();
                    if (dateformat==null) dateformat = new SimpleDateFormat("yyyy-MM-dd");
                    fixbirthday=dateformat.format(date);
                    showLoadingDialog(getString(R.string.saveLoad));
                    mPresenter.setupInfo("birthday",fixbirthday,1);
                }
            }).setType(type).build();
        }
        if (birthday!=0){
            if (birthdaycalendar==null)birthdaycalendar=Calendar.getInstance();
            birthdaycalendar.setTimeInMillis(birthday);
            pvTime.setDate(birthdaycalendar);
        }
        pvTime.show();
    }

    /**
     * unix时间戳需要处理之后才可以被转换为日期
     * @return
     */
    private String formatData() {
        if (birthday == 0) {
            return "";
        }
        birthday = birthday * 1000;
        if (dateformat==null) dateformat = new SimpleDateFormat("yyyy-MM-dd");
        try{
            return dateformat.format(birthday);
        }catch (Exception e){
            birthday=0;
            return "";
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
            switch (requestCode){
                case 0:
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", true);
                    tv_personalnickname.setText(data.getStringExtra("nickname"));
                    break;
                case 1:
                    if (resultCode == RESULT_OK){
                        showLoadingDialog(getString(R.string.saveLoad));
                        if (!TextUtils.isEmpty(data.getStringExtra("selectCity"))&&!data.getStringExtra("selectCity").contains(getString(R.string.locateFailure))){
                            pickeraddress=data.getStringExtra("selectCountry")+"•" +data.getStringExtra("selectCity");
                            mPresenter.setupInfo("country",data.getStringExtra("selectCountry"),"city",data.getStringExtra("selectCity"),4);
                        }else{
                            pickeraddress="";
                            mPresenter.setupInfo("country","","city","",4);
                        }
                    }else{
                        PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", true);
                        tv_personalemail.setText(data.getStringExtra("email"));
                    }
                    break;
                case 2:
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", true);
                    tv_personalqianming.setText(data.getStringExtra("signature"));
                    break;
                case 3:
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", true);
                    shzcode=data.getStringExtra("nickname");
                    updatanum++;
                    et_personalcode.setText(shzcode);
                    break;
                case 100:
                    touxiangpath=((ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS)).get(0).path;
                    upTouXiang();
                    break;
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pvTime=null;
        GlideCatchUtil.getInstance().cleanImageDisk();
        GlideCatchUtil.getInstance().cleanCatchDisk();
    }

    @Override
    public void setPresenter(PersonalDataContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        switch (flag){
            case 0:
                GlideCatchUtil.getInstance().cleanImageDisk();
                uploadimagebean = (UploadImageBean) JsonUtil.getInstance().json2Obj(success, UploadImageBean.class);
                if (uploadimagebean!=null&&uploadimagebean.getResult()!=null&&uploadimagebean.getResult().getFile()!=null&&!TextUtils.isEmpty(uploadimagebean.getResult().getFile().getUrl())){
                    mPresenter.setupInfo("head_pic",uploadimagebean.getResult().getFile().getUrl(),3);
                    showLoadingDialog(getString(R.string.saveLoad));
                }
                break;
            case 1:
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", true);
                tv_personalbirthday.setText(fixbirthday);
                break;
            case 2:
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", true);
                tv_personalsex.setText(altersex);
                break;
            case 3:
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", true);
                GlideImageLoader.glideLoader(this,touxiangpath,iv_personaltx,0);
                break;
            case 4:
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", true);
                tv_personaldiqu.setText(pickeraddress);
                break;
//            case 5:
//                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", true);
//                shzcode=et_personalcode.getText().toString();
//                ViewInject.toast(getString(R.string.updateSuccess));
//                break;
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        GlideCatchUtil.getInstance().cleanCatchDisk();
        dismissLoadingDialog();
        if (isLogin(msg)){
            ViewInject.toast(getString(R.string.reloginPrompting));
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", false);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isReLogin", true);
            showActivity(this,LoginActivity.class);
            finish();
            return;
        }
        ViewInject.toast(msg);
    }

//    @Override
//    public boolean onTouch(View view, MotionEvent motionEvent) {
//        if (motionEvent.getAction()==MotionEvent.ACTION_DOWN){
//            if (inputmanager==null)inputmanager=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            et_personalcode.setCursorVisible(true);// 再次点击显示光标
//        }
//        return false;
//    }

//    @Override
//    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
//        if (i== EditorInfo.IME_ACTION_SEND){
//            inputmanager.hideSoftInputFromWindow(textView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//            showLoadingDialog(getString(R.string.saveLoad));
//            mPresenter.changeShzCode(et_personalcode.getText().toString());
//            issend=true;
//        }
//        return false;
//    }

    /**
     * 傻孩子号光标和修改成功与否之后的修改
     */
    private void updataSHZCode(){
//        et_personalcode.setCursorVisible(false);
        et_personalcode.setText(shzcode);
    }

}
