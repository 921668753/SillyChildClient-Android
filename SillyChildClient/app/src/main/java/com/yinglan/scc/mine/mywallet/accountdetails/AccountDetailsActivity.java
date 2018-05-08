package com.yinglan.scc.mine.mywallet.accountdetails;

import android.content.Intent;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.kymjs.common.PreferenceHelper;
import com.yinglan.scc.R;
import com.yinglan.scc.adapter.mine.mywallet.AccountDetailsAdapter;
import com.yinglan.scc.entity.mine.mywallet.AccountDetailsBean;
import com.yinglan.scc.entity.mine.mywallet.AccountDetailsBean.ResultBean.ListBean;
import com.yinglan.scc.loginregister.LoginActivity;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 个人中心中的账户明细
 * Created by Administrator on 2017/9/2.
 */

public class AccountDetailsActivity extends BaseActivity implements AdapterView.OnItemClickListener,AccountDetailsContract.View,BGARefreshLayout.BGARefreshLayoutDelegate{

    private AccountDetailsContract.Presenter mPresenter;

    @BindView(id = R.id.titlebar)
    private BGATitleBar titlebar;

    @BindView(id = R.id.lv_selecttime)
    private ListView lv_selecttime;
    private String[] chooses;//查询日期的数组
    private int chooseid=0;//查询日期的编号

    @BindView(id=R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;
    @BindView(id=R.id.lv_detail)
    private ListView lv_detail;

    /**
     * 错误提示页
     */
    @BindView(id = R.id.ll_commonError, click = true)
    private LinearLayout ll_commonError;
    @BindView(id = R.id.img_err)
    private ImageView img_err;
    @BindView(id = R.id.tv_hintText)
    private TextView tv_hintText;

    @BindView(id = R.id.ll_empty)
    private LinearLayout ll_empty;

    private String starttime;//明细起始时间
    private String endtime;//明细结束时间
    private Calendar currentcalendar;//当前时间
    private Date choosedate;//选择的设定日期，时间为零点
    private int yint;//年
    private int mint;//月
    private int dint;//日
    private int dayofweek;//一周的第几天，一周的第一天是星期日
    private int type;//明细类型：0全部 1充值 2提现 3消费 4退款
    private int pnumber=1;//页码，从1开始
    private int pagesize=60;//每页数据量
    private int totalpages=1;//总页数
    private boolean isloadmore;
    private AccountDetailsAdapter adadapter;
    private AccountDetailsBean adBean;
    private List<ListBean> datalist;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_accountdetails);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter=new AccountDetailsPresenter(this);
        chooses=getResources().getStringArray(R.array.chooseDetail);
        chooseid=1;
        ArrayAdapter arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,chooses);
        lv_selecttime.setAdapter(arrayAdapter);
        lv_selecttime.setOnItemClickListener(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        adadapter=new AccountDetailsAdapter(this);
        lv_detail.setAdapter(adadapter);
        mRefreshLayout.beginRefreshing();
    }

    /**
     * 设置标题
     */
    public void initTitle() {

        titlebar.setTitleText(R.string.accountDetails);
        titlebar.setRightDrawable(R.mipmap.mine_downxxx);
        titlebar.setRightSecondaryText(chooses[chooseid]);
        titlebar.getRightSecondaryCtv().setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
        titlebar.getRightSecondaryCtv().getPaint().setFakeBoldText(true);
        BGATitleBar.SimpleDelegate simpleDelegate = new BGATitleBar.SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                super.onClickLeftCtv();
                aty.finish();
            }

            @Override
            public void onClickRightCtv() {
                super.onClickRightCtv();
                if (lv_selecttime.getVisibility()==View.GONE){
                    lv_selecttime.setVisibility(View.VISIBLE);
                }else{
                    lv_selecttime.setVisibility(View.GONE);
                }
            }

            @Override
            public void onClickRightSecondaryCtv() {
                super.onClickRightSecondaryCtv();
                if (lv_selecttime.getVisibility()==View.GONE){
                    lv_selecttime.setVisibility(View.VISIBLE);
                }else{
                    lv_selecttime.setVisibility(View.GONE);
                }
            }
        };
        titlebar.setDelegate(simpleDelegate);

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
                    Intent intent = new Intent(aty, LoginActivity.class);
                    showActivity(aty, intent);
                    break;
                }
                //  ViewInject.toast("onBGARefreshLayoutBeginRefreshing");
                mRefreshLayout.beginRefreshing();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        lv_selecttime.setVisibility(View.GONE);
        if (chooseid!=i){
            chooseid=i;
            titlebar.setRightSecondaryText(chooses[i]);
            mRefreshLayout.beginRefreshing();
        }

    }

    @Override
    public void setPresenter(AccountDetailsContract.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        ll_commonError.setVisibility(View.GONE);
        mRefreshLayout.setVisibility(View.VISIBLE);
        adBean=(AccountDetailsBean) JsonUtil.json2Obj(success, AccountDetailsBean.class);
        if (adBean==null){
            if (!isloadmore){
                adadapter.clear();
            }
            controlRefresh();
            ViewInject.toast(getString(R.string.otherError));
            return;
        }
        if (adBean.getResult()==null||adBean.getResult().getList()==null||adBean.getResult().getList().size()==0){
            if (isloadmore){
                ViewInject.toast(getString(R.string.noMoreData));
            }else{
                adadapter.clear();
                ll_empty.setVisibility(View.VISIBLE);
//                ViewInject.toast(getString(R.string.serverReturnsDataNull));
            }
            controlRefresh();
            return;
        }
        ll_empty.setVisibility(View.GONE);
        datalist=adBean.getResult().getList();
        pnumber=adBean.getResult().getP();
        totalpages=adBean.getResult().getTotalPages();
        if (isloadmore){
            adadapter.addMoreData(datalist);
        }else{
            adadapter.clear();
            adadapter.addNewData(datalist);
        }
        controlRefresh();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        controlRefresh();
        if (isLogin(msg)){
            ViewInject.toast(getString(R.string.reloginPrompting));
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", false);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isReLogin", true);
            showActivity(this,LoginActivity.class);
            return;
        }
        mRefreshLayout.setVisibility(View.GONE);
        ll_commonError.setVisibility(View.VISIBLE);
        tv_hintText.setText(msg+getString(R.string.clickRefresh));
//        ViewInject.toast(msg);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        isloadmore=false;
        computationTime();
        pnumber=1;
        mPresenter.getAccountDetail(starttime,endtime,0,pnumber,pagesize);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        isloadmore=true;
        if (!(pnumber>totalpages)){
            pnumber++;
        }
        mPresenter.getAccountDetail(starttime,endtime,0,pnumber,pagesize);
        return false;
    }

    /**
     *     根据时间段，获取起止时间戳
     */
    private void computationTime(){
        currentcalendar=Calendar.getInstance();
        endtime=currentcalendar.getTimeInMillis()/1000+"";
        switch (chooses[chooseid]){
            case "今天":
                yint=currentcalendar.get(Calendar.YEAR);
                mint=currentcalendar.get(Calendar.MONTH);
                dint=currentcalendar.get(Calendar.DATE);
                break;
            case "本周":
                dayofweek=currentcalendar.get(Calendar.DAY_OF_WEEK)-1;//Calendar.DAY_OF_WEEK是表示一周中的第几天，一周中的第1天是星期日。
                if (dayofweek==0)dayofweek=7;
                currentcalendar.add(Calendar.DATE,-dayofweek+1);
                yint=currentcalendar.get(Calendar.YEAR);
                mint=currentcalendar.get(Calendar.MONTH);
                dint=currentcalendar.get(Calendar.DATE);
                break;
            case "本月":
                currentcalendar.set(Calendar.DAY_OF_MONTH,1);
                yint=currentcalendar.get(Calendar.YEAR);
                mint=currentcalendar.get(Calendar.MONTH);
                dint=currentcalendar.get(Calendar.DATE);
                break;
            case "近一个月":
                currentcalendar.add(Calendar.MONTH,-1);
                yint=currentcalendar.get(Calendar.YEAR);
                mint=currentcalendar.get(Calendar.MONTH);
                dint=currentcalendar.get(Calendar.DATE);
                break;
            case "近两个月":
                currentcalendar.add(Calendar.MONTH,-2);
                yint=currentcalendar.get(Calendar.YEAR);
                mint=currentcalendar.get(Calendar.MONTH);
                dint=currentcalendar.get(Calendar.DATE);
                break;
            case "近三个月":
                currentcalendar.add(Calendar.MONTH,-3);
                yint=currentcalendar.get(Calendar.YEAR);
                mint=currentcalendar.get(Calendar.MONTH);
                dint=currentcalendar.get(Calendar.DATE);
                break;
            case "近一年":
                currentcalendar.add(Calendar.YEAR,-1);
                yint=currentcalendar.get(Calendar.YEAR);
                mint=currentcalendar.get(Calendar.MONTH);
                dint=currentcalendar.get(Calendar.DATE);
                break;
            case "近两年":
                currentcalendar.add(Calendar.YEAR,-2);
                yint=currentcalendar.get(Calendar.YEAR);
                mint=currentcalendar.get(Calendar.MONTH);
                dint=currentcalendar.get(Calendar.DATE);
                break;
        }
        choosedate=new Date(yint-1900,mint,dint);
        starttime=choosedate.getTime()/1000+"";
    }

    /**
     * 控制刷新和加载控件显示与否
     */
    private void controlRefresh(){
        if (isloadmore){
            mRefreshLayout.endLoadingMore();
        }else{
            mRefreshLayout.endRefreshing();
        }
        isloadmore=false;
    }

}
