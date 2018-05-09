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
import com.yinglan.scc.dialog.VIPPermissionsDialog;
import com.yinglan.scc.entity.UploadImageBean;
import com.yinglan.scc.entity.UserInfoBean;
import com.yinglan.scc.homepage.addressselection.AddressSelectionActivity;
import com.yinglan.scc.loginregister.LoginActivity;
import com.yinglan.scc.mine.personaldata.dialog.PictureSourceDialog;
import com.yinglan.scc.mine.personaldata.setnickname.SetNickNameActivity;
import com.yinglan.scc.mine.personaldata.setsex.SetSexActivity;
import com.yinglan.scc.mine.personaldata.setsignature.SetSignatureActivity;
import com.yinglan.scc.mine.personaldata.setsillycode.SetSillyCodeActivity;
import com.yinglan.scc.utils.GlideImageLoader;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.yinglan.scc.constant.NumericConstants.RESULT_CODE_BASKET_ADD;
import static com.yinglan.scc.constant.NumericConstants.RESULT_CODE_BASKET_MINUS;
import static com.yinglan.scc.constant.NumericConstants.RESULT_CODE_BASKET_MINUSALL;
import static com.yinglan.scc.constant.NumericConstants.RESULT_CODE_GET;
import static com.yinglan.scc.constant.NumericConstants.STATUS;

/**
 * 个人资料
 * Created by Administrator on 2017/9/2.
 */

public class PersonalDataActivity extends BaseActivity implements PersonalDataContract.View {
    private UserInfoBean.ResultBean resultBean;
    private PersonalDataContract.Presenter mPresenter;

    @BindView(id = R.id.ll_personaldatatx, click = true)
    private LinearLayout ll_personaldatatx;

    @BindView(id = R.id.iv_personaltx)
    private ImageView iv_personaltx;

    @BindView(id = R.id.ll_personaldatanc, click = true)
    private LinearLayout ll_personaldatanc;

    @BindView(id = R.id.tv_personalnickname)
    private TextView tv_personalnickname;

    @BindView(id = R.id.ll_personaldataxb, click = true)
    private LinearLayout ll_personaldataxb;

    @BindView(id = R.id.tv_personalsex)
    private TextView tv_personalsex;

    @BindView(id = R.id.ll_personaldatasr, click = true)
    private LinearLayout ll_personaldatasr;

    @BindView(id = R.id.tv_personalbirthday)
    private TextView tv_personalbirthday;

    @BindView(id = R.id.ll_personaldatadq, click = true)
    private LinearLayout ll_personaldatadq;

    @BindView(id = R.id.tv_personaldiqu)
    private TextView tv_personaldiqu;

    @BindView(id = R.id.ll_personaldatagxqm, click = true)
    private LinearLayout ll_personaldatagxqm;

    private PictureSourceDialog pictureSourceDialog;

    private TimePickerView pvTime;

    private OptionsPickerView pvOptions;
    private SimpleDateFormat dateformat;
    private String fixbirthday;//修改的生日
    private String altersex;//修改的性别
    public static final int REQUEST_CODE_SELECT = 100;
    private ImagePicker imagePicker;
    private String touxiangpath;//所更换的头像的路径
    private UploadImageBean uploadimagebean;

    private long birthday;//生日
    private Calendar birthdaycalendar;//生日

    private String pickeraddress;//选择器返回的地址

    private boolean issend;//发出了修改傻孩子号的标识
    private String shzcode;//傻孩子号

    private int updatanum = 0;

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
            UserInfoBean userInfoBean = (UserInfoBean) getIntent().getExtras().get("userinfo");
            resultBean = userInfoBean.getResult();
        } catch (Exception e) {

        }
        mPresenter = new PersonalDataPresenter(this);
        initImagePicker();
    }

    /**
     * 初始化图片选择器
     */
    private void initImagePicker() {
        imagePicker = ImagePicker.getInstance();
        GlideImageLoader glideImageLoader = new GlideImageLoader();
        imagePicker.setImageLoader(glideImageLoader);   //设置图片加载器
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(NumericConstants.MAXPICTURE);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.CIRCLE);  //裁剪框的形状
        imagePicker.setFocusWidth(600);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(600);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setMultiMode(false);//设置为单选模式，默认多选
        imagePicker.setShowCamera(false);//设置显示相机，默认显示
    }

    /**
     * 渲染控件
     */
    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        if (resultBean == null) {
            ViewInject.toast(getString(R.string.getDataError));
//            finish();
        } else {
            tv_personalnickname.setText(resultBean.getNickname());
            shzcode = resultBean.getShz_code();
            //et_personalcode.setText(resultBean.getShz_code());
            updatanum = StringUtils.toInt(resultBean.getShz_update(), 0);
            switch (resultBean.getSex()) {
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
            birthday = resultBean.getBirthday();
            tv_personalbirthday.setText(formatData());
            String address = "";
            if (!TextUtils.isEmpty(resultBean.getCountry())) {
                address += resultBean.getCountry() + "•";
            }
            if (!TextUtils.isEmpty(resultBean.getCity())) {
                address += resultBean.getCity();
            }
            tv_personaldiqu.setText(address);

            String headpic = resultBean.getHead_pic();
            if (TextUtils.isEmpty(headpic)) {
                iv_personaltx.setImageResource(R.mipmap.avatar);
            } else {
                GlideImageLoader.glideLoader(aty, headpic, iv_personaltx, 0, R.mipmap.avatar);
            }
        }
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.personaData), true, R.id.titlebar);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.et_personalcode:
                if (updatanum < 2) {
                    Intent jumpintent = new Intent(this, SetSillyCodeActivity.class);
//                    jumpintent.putExtra("nickname", et_personalcode.getText().toString());
                    showActivityForResult(this, jumpintent, RESULT_CODE_GET);
                } else {
                    ViewInject.toast(getString(R.string.sillyChildNumberNoChange));
                }
                break;
            case R.id.ll_personaldatatx:
                PictureDialog();
                break;
            case R.id.ll_personaldatanc:
                Intent setNickNameIntent = new Intent(this, SetNickNameActivity.class);
                setNickNameIntent.putExtra("nickname", tv_personalnickname.getText().toString());
                showActivityForResult(this, setNickNameIntent, RESULT_CODE_BASKET_ADD);
                break;
            case R.id.ll_personaldataxb:
                Intent setSexIntent = new Intent(this, SetSexActivity.class);
                setSexIntent.putExtra("sex", true);
                startActivityForResult(setSexIntent, RESULT_CODE_BASKET_MINUS);
                break;
            case R.id.ll_personaldatasr:
                departureTime();
                break;
            case R.id.ll_personaldatadq:
//                Intent addressIntent = new Intent(this, AddressSelectionActivity.class);
//                addressIntent.putExtra("showinland", true);
//                startActivityForResult(addressIntent, STATUS);
                break;
            case R.id.ll_personaldatagxqm:
                Intent setSignatureIntent = new Intent(this, SetSignatureActivity.class);
                //    jumpintent.putExtra("signature",tv_personalqianming.getText().toString());
                showActivityForResult(this, setSignatureIntent, RESULT_CODE_BASKET_MINUSALL);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case RESULT_CODE_GET:
                    shzcode = data.getStringExtra("nickname");
                    updatanum++;
                    //    et_personalcode.setText(shzcode);
                    break;
                case RESULT_CODE_BASKET_ADD:
                    tv_personalnickname.setText(data.getStringExtra("nickname"));
                    break;
                case RESULT_CODE_BASKET_MINUS:
                    tv_personalnickname.setText(data.getStringExtra("nickname"));
                    break;
                case RESULT_CODE_BASKET_MINUSALL:


                    break;
                case REQUEST_CODE_SELECT:
                    if (resultCode == ImagePicker.RESULT_CODE_ITEMS && data != null) {
                        ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                        if (images == null || images.size() == 0) {
                            ViewInject.toast(getString(R.string.noData));
                            return;
                        }
                        touxiangpath = images.get(0).path;
                        showLoadingDialog(getString(R.string.saveLoad));
                        mPresenter.upPictures(images.get(0).path, 0);
                    } else {
                        ViewInject.toast(getString(R.string.noData));
                    }
                    break;
            }
        }

    }


    /**
     * 选择更换头像的弹窗
     */
    public void PictureDialog() {
        if (pictureSourceDialog == null) {
            pictureSourceDialog = new PictureSourceDialog(aty) {
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


    private void takeTouxiang() {
        Intent intent = new Intent(this, ImageGridActivity.class);
        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
        startActivityForResult(intent, REQUEST_CODE_SELECT);
    }

    private void selectPicture() {
        //打开选择,本次允许选择的数量
        ImagePicker.getInstance().setSelectLimit(1);
        Intent intent1 = new Intent(this, ImageGridActivity.class);
        /* 如果需要进入选择的时候显示已经选中的图片，
         * 详情请查看ImagePickerActivity
         * */
//      intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
        startActivityForResult(intent1, REQUEST_CODE_SELECT);
    }


    /**
     * 选择时间的控件
     */
    private void departureTime() {
        if (pvTime == null) {
            //TimePicker
            //控制时间范围
            boolean[] type = new boolean[]{true, true, true, false, false, false};
            pvTime = new TimePickerView.Builder(aty, new TimePickerView.OnTimeSelectListener() {

                @Override
                public void onTimeSelect(Date date, View v) {//选中事件回调
                    birthday = date.getTime();
                    if (dateformat == null) dateformat = new SimpleDateFormat("yyyy-MM-dd");
                    fixbirthday = dateformat.format(date);
                    showLoadingDialog(getString(R.string.saveLoad));
                    mPresenter.setupInfo("birthday", fixbirthday, 1);
                }
            }).setType(type).build();
        }
        if (birthday != 0) {
            if (birthdaycalendar == null) birthdaycalendar = Calendar.getInstance();
            birthdaycalendar.setTimeInMillis(birthday);
            pvTime.setDate(birthdaycalendar);
        }
        pvTime.show();
    }

    /**
     * unix时间戳需要处理之后才可以被转换为日期
     *
     * @return
     */
    private String formatData() {
        if (birthday == 0) {
            return "";
        }
        birthday = birthday * 1000;
        if (dateformat == null) dateformat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateformat.format(birthday);
        } catch (Exception e) {
            birthday = 0;
            return "";
        }
    }


    @Override
    public void setPresenter(PersonalDataContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        switch (flag) {
            case 0:
                GlideCatchUtil.getInstance().cleanImageDisk();
                uploadimagebean = (UploadImageBean) JsonUtil.getInstance().json2Obj(success, UploadImageBean.class);
                if (uploadimagebean != null && uploadimagebean.getResult() != null && uploadimagebean.getResult().getFile() != null && !TextUtils.isEmpty(uploadimagebean.getResult().getFile().getUrl())) {
                    mPresenter.setupInfo("head_pic", uploadimagebean.getResult().getFile().getUrl(), 3);
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
                GlideImageLoader.glideLoader(this, touxiangpath, iv_personaltx, 0);
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
        if (isLogin(msg)) {
            ViewInject.toast(getString(R.string.reloginPrompting));
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", false);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isReLogin", true);
            showActivity(this, LoginActivity.class);
            finish();
            return;
        }
        ViewInject.toast(msg);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        pvTime = null;
        if (pictureSourceDialog != null) {
            pictureSourceDialog.cancel();
        }
        pictureSourceDialog = null;
        GlideCatchUtil.getInstance().cleanImageDisk();
        GlideCatchUtil.getInstance().cleanCatchDisk();
    }

}
