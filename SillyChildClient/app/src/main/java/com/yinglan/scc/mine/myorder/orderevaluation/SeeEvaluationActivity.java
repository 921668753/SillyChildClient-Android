package com.yinglan.scc.mine.myorder.orderevaluation;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.myview.NoScrollGridView;
import com.kymjs.common.PreferenceHelper;
import com.yinglan.scc.R;
import com.yinglan.scc.adapter.CharterListGridViewAdapter;
import com.yinglan.scc.entity.SeeEvaluationBean;
import com.yinglan.scc.entity.SeeEvaluationBean.ResultBean.UserCommBean.OwnerBean;
import com.yinglan.scc.loginregister.LoginActivity;
import com.yinglan.scc.utils.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/9/27.
 */

public class SeeEvaluationActivity extends BaseActivity implements PostEvaluationContract.View,AdapterView.OnItemClickListener{

    @BindView(id = R.id.ll_allactivity)
    private LinearLayout ll_allactivity;

    @BindView(id = R.id.sv_evaluation)
    private ScrollView sv_evaluation;

    @BindView(id = R.id.ll_userevaluation)
    private LinearLayout ll_userevaluation;
    @BindView(id = R.id.iv_headicon)
    private ImageView iv_headicon;
    @BindView(id = R.id.tv_name)
    private TextView tv_name;
    @BindView(id = R.id.tv_time)
    private TextView tv_time;
    @BindView(id = R.id.rl_toguidescoring)
    private RelativeLayout rl_toguidescoring;
    @BindView(id = R.id.iv_heartnumguide)
    private ImageView iv_heartnumguide;
    @BindView(id = R.id.rl_togoodscoring)
    private RelativeLayout rl_togoodscoring;
    @BindView(id = R.id.iv_heartnumgoods)
    private ImageView iv_heartnumgoods;
    @BindView(id = R.id.noscrollgridview)
    private NoScrollGridView noscrollgridview;
    @BindView(id = R.id.tv_content)
    private TextView tv_content;

    @BindView(id = R.id.ll_sillychildreply)
    private LinearLayout ll_sillychildreply;
    @BindView(id = R.id.tv_time_silly)
    private TextView tv_time_silly;
    @BindView(id = R.id.tv_sillychildreply)
    private TextView tv_sillychildreply;
    @BindView(id = R.id.noscrollgridview_silly)
    private NoScrollGridView noscrollgridview_silly;

    @BindView(id = R.id.ll_guideevaluation)
    private LinearLayout ll_guideevaluation;
    @BindView(id = R.id.iv_guideheadicon)
    private ImageView iv_guideheadicon;
    @BindView(id = R.id.tv_guidename)
    private TextView tv_guidename;
    @BindView(id = R.id.tv_guidetime)
    private TextView tv_guidetime;
    @BindView(id = R.id.rl_guidscoring)
    private RelativeLayout rl_guidscoring;
    @BindView(id = R.id.iv_heartnumg)
    private ImageView iv_heartnumg;
    @BindView(id = R.id.noscrollgridview_guide)
    private NoScrollGridView noscrollgridview_guide;
    @BindView(id = R.id.tv_guideevaluation)
    private TextView tv_guideevaluation;

    /**
     * 错误提示页
     */
    @BindView(id = R.id.ll_commonError, click = true)
    private LinearLayout ll_commonError;
    @BindView(id = R.id.img_err)
    private ImageView img_err;
    @BindView(id = R.id.tv_hintText)
    private TextView tv_hintText;

    private String airid;
    private List<String> imgslist;
    private ArrayList imageItems;
    private List<String> imgslistsyscomm;
    private List<String> imgslistguide;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_seeevaluation);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter=new PostEvaluationPresenter(this);
        airid=getIntent().getStringExtra("airid");

    }

    @Override
    public void initWidget() {
        super.initWidget();
        sv_evaluation.setVisibility(View.GONE);
        initTitle();
        showLoadingDialog(getString(R.string.dataLoad));
        ((PostEvaluationContract.Presenter)mPresenter).seeEvaluation(airid);

    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()){
            case R.id.ll_commonError:
                if (tv_hintText.getText().toString().equals(getString(R.string.login1))) {
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "id", 0);
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "accessToken", "");
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "refreshToken", "");
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "expireTime", "0");
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "timeBefore", "0");
                    //   PreferenceHelper.write(aty, StringConstants.FILENAME, "refreshName", "getCompanyGuideMessageFragment");
                    Intent intent = new Intent(this, LoginActivity.class);
                    showActivity(aty, intent);
                    break;
                }
                //  ViewInject.toast("onBGARefreshLayoutBeginRefreshing");
                break;
        }
    }

    /**
     * 设置标题
     */
    private void initTitle(){
        ActivityTitleUtils.initToolbar(aty, getString(R.string.seeEvaluation), true, R.id.titlebar);
    }

    @Override
    public void setPresenter(PostEvaluationContract.Presenter presenter) {
        mPresenter= presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        Log.e("调试","查看评论"+success);
        SeeEvaluationBean seeEvaluationBean=(SeeEvaluationBean)JsonUtil.getInstance().json2Obj(success,SeeEvaluationBean.class);
        fillData(seeEvaluationBean);
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        sv_evaluation.setVisibility(View.GONE);
        ll_commonError.setVisibility(View.VISIBLE);
        if (isLogin(msg)) {
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", false);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isReLogin", true);
            dismissLoadingDialog();
            tv_hintText.setText(getString(R.string.login1));
            //       aty.showActivity(aty, LoginActivity.class);
            return;
        }
        tv_hintText.setText(msg);
    }

    private void fillData(SeeEvaluationBean seeEvaluationBean){
        if (seeEvaluationBean!=null&&seeEvaluationBean.getResult()!=null){
            sv_evaluation.setVisibility(View.VISIBLE);
            //用户评价
            if (seeEvaluationBean.getResult().getUserComm()!=null){
                OwnerBean ownerBean=seeEvaluationBean.getResult().getUserComm().getOwner();
                GlideImageLoader.glideLoader(this,ownerBean.getHead_pic(),iv_headicon,0);
                if (!TextUtils.isEmpty(ownerBean.getNickname())){
                    tv_name.setText(ownerBean.getNickname());
                }
                switch (seeEvaluationBean.getResult().getUserComm().getDrv_rank()){
                    case 5:
                        GlideImageLoader.heartIconLoader(this,R.mipmap.heart_full,iv_heartnumguide);
                        break;
                    case 4:
                        GlideImageLoader.heartIconLoader(this,R.mipmap.heart_four,iv_heartnumguide);
                        break;
                    case 3:
                        GlideImageLoader.heartIconLoader(this,R.mipmap.heart_three,iv_heartnumguide);
                        break;
                    case 2:
                        GlideImageLoader.heartIconLoader(this,R.mipmap.heart_two,iv_heartnumguide);
                        break;
                    case 1:
                        GlideImageLoader.heartIconLoader(this,R.mipmap.heart_one,iv_heartnumguide);
                        break;
                    default:
                        rl_toguidescoring.setVisibility(View.GONE);
                        break;
                }
                switch (seeEvaluationBean.getResult().getUserComm().getScore()){
                    case 5:
                        GlideImageLoader.heartIconLoader(this,R.mipmap.heart_full,iv_heartnumgoods);
                        break;
                    case 4:
                        GlideImageLoader.heartIconLoader(this,R.mipmap.heart_four,iv_heartnumgoods);
                        break;
                    case 3:
                        GlideImageLoader.heartIconLoader(this,R.mipmap.heart_three,iv_heartnumgoods);
                        break;
                    case 2:
                        GlideImageLoader.heartIconLoader(this,R.mipmap.heart_two,iv_heartnumgoods);
                        break;
                    case 1:
                        GlideImageLoader.heartIconLoader(this,R.mipmap.heart_one,iv_heartnumgoods);
                        break;
                    default:
                        rl_togoodscoring.setVisibility(View.GONE);
                        break;
                }
                tv_time.setText(seeEvaluationBean.getResult().getUserComm().getCommentTimeFmt());
                tv_content.setText(seeEvaluationBean.getResult().getUserComm().getContent());
                imgslist=seeEvaluationBean.getResult().getUserComm().getImgs();
                if (imgslist!=null&&imgslist.size()>0){
                    if (imgslist.size()==1&&TextUtils.isEmpty(imgslist.get(0))){
                        noscrollgridview.setVisibility(View.GONE);
                    }else{
                        CharterListGridViewAdapter charterListGridViewAdapter = new CharterListGridViewAdapter(this);
                        charterListGridViewAdapter.addNewData(imgslist);
                        noscrollgridview.setAdapter(charterListGridViewAdapter);
                        noscrollgridview.setOnItemClickListener(this);
                    }
                }else{
                    noscrollgridview.setVisibility(View.GONE);
                }
                //平台回复
                if (seeEvaluationBean.getResult().getSysComm()!=null){
                    tv_time_silly.setText(seeEvaluationBean.getResult().getSysComm().getCommentTimeFmt());
                    if (TextUtils.isEmpty(seeEvaluationBean.getResult().getSysComm().getContent())){
                        tv_sillychildreply.setVisibility(View.GONE);
                    }else{
                        tv_sillychildreply.setText(seeEvaluationBean.getResult().getSysComm().getContent());
                    }
                    imgslistsyscomm=seeEvaluationBean.getResult().getSysComm().getImgs();
                    if (imgslistsyscomm!=null&&imgslistsyscomm.size()>0){
                        if (imgslistsyscomm.size()==1&&TextUtils.isEmpty(imgslistsyscomm.get(0))){
                            noscrollgridview_silly.setVisibility(View.GONE);
                        }else{
                            CharterListGridViewAdapter adaptersyscomm = new CharterListGridViewAdapter(this);
                            adaptersyscomm.addNewData(imgslistsyscomm);
                            noscrollgridview_silly.setAdapter(adaptersyscomm);
                            noscrollgridview_silly.setOnItemClickListener(this);
                        }
                    }else if (TextUtils.isEmpty(seeEvaluationBean.getResult().getSysComm().getContent())){
                        ll_sillychildreply.setVisibility(View.GONE);
                    }
                }else{
                    ll_sillychildreply.setVisibility(View.GONE);
                }
            }else{
                ll_userevaluation.setVisibility(View.GONE);
            }

            //司导评价
            if (seeEvaluationBean.getResult().getDrvComm()!=null){
                if (TextUtils.isEmpty(seeEvaluationBean.getResult().getDrvComm().getContent())){
                    tv_guideevaluation.setVisibility(View.GONE);
                }else{
                    tv_guideevaluation.setText(seeEvaluationBean.getResult().getDrvComm().getContent());
                }
                GlideImageLoader.glideLoader(this,seeEvaluationBean.getResult().getDrvComm().getHead_pic(),iv_guideheadicon,0);
                if (TextUtils.isEmpty(seeEvaluationBean.getResult().getDrvComm().getNickname())){
                    tv_guidename.setText(null);
                }else{
                    tv_guidename.setText(seeEvaluationBean.getResult().getDrvComm().getNickname());
                }
                switch (seeEvaluationBean.getResult().getDrvComm().getScore()){
                    case 5:
                        GlideImageLoader.heartIconLoader(this,R.mipmap.heart_full,iv_heartnumg);
                        break;
                    case 4:
                        GlideImageLoader.heartIconLoader(this,R.mipmap.heart_four,iv_heartnumg);
                        break;
                    case 3:
                        GlideImageLoader.heartIconLoader(this,R.mipmap.heart_three,iv_heartnumg);
                        break;
                    case 2:
                        GlideImageLoader.heartIconLoader(this,R.mipmap.heart_two,iv_heartnumg);
                        break;
                    case 1:
                        GlideImageLoader.heartIconLoader(this,R.mipmap.heart_one,iv_heartnumg);
                        break;
                    default:
                        rl_guidscoring.setVisibility(View.GONE);
                        break;
                }
                tv_guidetime.setText(seeEvaluationBean.getResult().getDrvComm().getCommentTimeFmt());
                imgslistguide=seeEvaluationBean.getResult().getDrvComm().getImgs();
                if (imgslistguide!=null&&imgslistguide.size()>0){
                    if (imgslistguide.size()==1&&TextUtils.isEmpty(imgslistguide.get(0))){
                        noscrollgridview_guide.setVisibility(View.GONE);
                    }else{
                        CharterListGridViewAdapter adapterguide = new CharterListGridViewAdapter(this);
                        adapterguide.addNewData(imgslistguide);
                        noscrollgridview_guide.setAdapter(adapterguide);
                        noscrollgridview_guide.setOnItemClickListener(this);
                    }
                }else if (TextUtils.isEmpty(seeEvaluationBean.getResult().getDrvComm().getContent())){
                    ll_guideevaluation.setVisibility(View.GONE);
                }
            }else{
                ll_guideevaluation.setVisibility(View.GONE);
            }

        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (((View)view.getParent()).getId()){
            case R.id.noscrollgridview:
                if (imgslist!=null&&imgslist.size()>0){
                    Intent jumpintent=new Intent(this,ImagePagerActivity.class);
                    jumpintent.putStringArrayListExtra("urls",(ArrayList<String>) imgslist);
                    jumpintent.putExtra("position",i);
                    showActivity(this,jumpintent);
                }
                break;
            case R.id.noscrollgridview_silly:
                if (imgslistsyscomm!=null&&imgslistsyscomm.size()>0){
                    Intent jumpintent=new Intent(this,ImagePagerActivity.class);
                    jumpintent.putStringArrayListExtra("urls",(ArrayList<String>) imgslistsyscomm);
                    jumpintent.putExtra("position",i);
                    showActivity(this,jumpintent);
                }
                break;
            case R.id.noscrollgridview_guide:
                if (imgslistguide!=null&&imgslistguide.size()>0){
                    Intent jumpintent=new Intent(this,ImagePagerActivity.class);
                    jumpintent.putStringArrayListExtra("urls",(ArrayList<String>) imgslistguide);
                    jumpintent.putExtra("position",i);
                    showActivity(this,jumpintent);
                }
                break;
        }

    }
}
