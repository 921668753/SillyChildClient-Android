package com.yinglan.scc.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.common.cklibrary.utils.myview.HorizontalListView;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.yinglan.scc.BuildConfig;
import com.yinglan.scc.R;
import com.yinglan.scc.adapter.LocalTalentHomePageViewAdapter;
import com.yinglan.scc.adapter.PopularStrategyHomePageViewAdapter;
import com.yinglan.scc.adapter.RecentNewsHomePageViewAdapter;
import com.yinglan.scc.constant.NumericConstants;
import com.yinglan.scc.entity.HomePageBean;
import com.yinglan.scc.entity.HomePageBean.ResultBean.AdBean;
import com.yinglan.scc.homepage.BannerDetailsActivity;
import com.yinglan.scc.homepage.addressselection.AddressSelectionActivity;
import com.yinglan.scc.homepage.chartercustom.CharterCustomActivity;
import com.yinglan.scc.homepage.customerservice.OverleafActivity;
import com.yinglan.scc.homepage.dynamics.AllDynamicsActivity;
import com.yinglan.scc.homepage.dynamics.DynamicsDetailsActivity;
import com.yinglan.scc.homepage.localtalent.LocalTalentActivity;
import com.yinglan.scc.homepage.localtalent.LocalTalentDetailsActivity;
import com.yinglan.scc.homepage.popularstrategy.HotStrategyActivity;
import com.yinglan.scc.loginregister.LoginActivity;
import com.yinglan.scc.trip.strategy.StrategyDetailsActivity;
import com.yinglan.scc.utils.GlideImageLoader;

import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import pub.devrel.easypermissions.EasyPermissions;

import static com.yinglan.scc.constant.NumericConstants.STATUS;
import static android.app.Activity.RESULT_OK;

/**
 * 首页
 * Created by Admin on 2017/8/10.
 */
public class HomePageFragment extends BaseFragment implements EasyPermissions.PermissionCallbacks, HomePageContract.View, BGABanner.Delegate<ImageView, AdBean>, BGABanner.Adapter<ImageView, AdBean>, AdapterView.OnItemClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {

    private MainActivity aty;

    /**
     * 地址选择
     */
    @BindView(id = R.id.tv_address, click = true)
    private TextView tv_address;

    /**
     * 客服
     */
    @BindView(id = R.id.tv_tag)
    private TextView tv_tag;

    @BindView(id = R.id.img_customerService, click = true)
    private ImageView img_customerService;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    /**
     * 轮播图
     */
    @BindView(id = R.id.banner_ad)
    private BGABanner mForegroundBanner;

    /**
     * 包车定制
     */
    @BindView(id = R.id.img_charterCustom, click = true)
    private ImageView img_charterCustom;

    /**
     * 民宿预约
     */
    @BindView(id = R.id.img_homestaySubscribe, click = true)
    private ImageView img_homestaySubscribe;

    /**
     * 商城
     */
    @BindView(id = R.id.img_memorialMall, click = true)
    private ImageView img_memorialMall;

    /**
     * 当地达人  查看全部
     */
    @BindView(id = R.id.ll_localTalent)
    private LinearLayout ll_localTalent;

    @BindView(id = R.id.tv_localTalentAll, click = true)
    private TextView tv_localTalentAll;

    @BindView(id = R.id.hlv_localTalent)
    private HorizontalListView hlv_localTalent;

    /**
     * 热门攻略  查看全部
     */
    @BindView(id = R.id.ll_popularStrategy)
    private LinearLayout ll_popularStrategy;

    @BindView(id = R.id.tv_popularStrategyAll, click = true)
    private TextView tv_popularStrategyAll;

    @BindView(id = R.id.hlv_popularStrategy)
    private HorizontalListView hlv_popularStrategy;

    /**
     * 最新动态  查看全部
     */
    @BindView(id = R.id.ll_recentNews)
    private LinearLayout ll_recentNews;

    @BindView(id = R.id.tv_recentNewsAll, click = true)
    private TextView tv_recentNewsAll;

    @BindView(id = R.id.hlv_recentNews)
    private HorizontalListView hlv_recentNews;

    public LocationClient mLocationClient = null;

    public BDAbstractLocationListener myListener = null;
//BDAbstractLocationListener为7.2版本新增的Abstract类型的监听接口，原有BDLocationListener接口暂时同步保留。具体介绍请参考后文中的说明

    private boolean isFirst = true;

    private LocalTalentHomePageViewAdapter localTalentHomePageViewAdapter;
    private PopularStrategyHomePageViewAdapter popularStrategyHomePageViewAdapter;
    private RecentNewsHomePageViewAdapter recentNewsHomePageViewAdapter;
    private Thread thread1;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_homepage, null);
    }

    @Override
    protected void initData() {
        super.initData();
        PreferenceHelper.write(aty, StringConstants.FILENAME, "selectCity", getString(R.string.allAeservationNumber));
        mPresenter = new HomePagePresenter(this);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, false);
        localTalentHomePageViewAdapter = new LocalTalentHomePageViewAdapter(aty);
        popularStrategyHomePageViewAdapter = new PopularStrategyHomePageViewAdapter(aty);
        recentNewsHomePageViewAdapter = new RecentNewsHomePageViewAdapter(aty);
        mLocationClient = new LocationClient(aty.getApplicationContext());
        myListener = new MyLocationListener();
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        initBanner();
        showLoadingDialog(aty.getString(R.string.dataLoad));
        tv_address.setText(getString(R.string.allAeservationNumber));
        hlv_localTalent.setAdapter(localTalentHomePageViewAdapter);
        // hlv_localTalent.getParent().requestDisallowInterceptTouchEvent(true);
        hlv_localTalent.setOnItemClickListener(this);
        hlv_popularStrategy.setAdapter(popularStrategyHomePageViewAdapter);
        // hlv_popularStrategy.getParent().requestDisallowInterceptTouchEvent(true);
        hlv_popularStrategy.setOnItemClickListener(this);
        hlv_recentNews.setAdapter(recentNewsHomePageViewAdapter);
        //  hlv_recentNews.getParent().requestDisallowInterceptTouchEvent(true);
        hlv_recentNews.setOnItemClickListener(this);
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数
        ((HomePageContract.Presenter) mPresenter).initLocation(aty, mLocationClient);
        ((HomePageContract.Presenter) mPresenter).getHomePage("");
        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(BuildConfig.HUANXIN_IM);
        try {
            if (conversation.getUnreadMsgCount() > 0) {
                tv_tag.setVisibility(View.GONE);
            } else {
                tv_tag.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            tv_tag.setVisibility(View.GONE);
        }
    }

    /**
     * @param v 控件监听事件
     */
    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_address:
//                Intent intent = new Intent();
//                intent.setClass(aty, NewOverseasCityActivity.class);
//                startActivityForResult(intent, STATUS);
                aty.showActivityForResult(aty, AddressSelectionActivity.class, STATUS);
                break;
            case R.id.img_customerService:
                ((HomePageContract.Presenter) mPresenter).isLogin(1);
                break;
            case R.id.img_charterCustom:
                aty.showActivity(aty, CharterCustomActivity.class);
                break;
            case R.id.img_homestaySubscribe:
                ViewInject.toast(getActivity().getString(R.string.noDevelopment));
                //  aty.showActivity(aty, HomestaySubscribeActivity.class);
                break;
            case R.id.img_memorialMall:
                Intent intent1 = new Intent(aty, MainActivity.class);
                intent1.putExtra("newChageIcon", 2);
                aty.showActivity(aty, intent1);
                break;
            case R.id.tv_localTalentAll:
                aty.showActivity(aty, LocalTalentActivity.class);
                break;
            case R.id.tv_popularStrategyAll:
                Intent intent2 = new Intent(aty, HotStrategyActivity.class);
                intent2.putExtra("city", tv_address.getText().toString());
                aty.showActivity(aty, intent2);
                break;
            case R.id.tv_recentNewsAll:
                aty.showActivity(aty, AllDynamicsActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void setPresenter(HomePageContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshingChangeHomePageFragment", false);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshingHomePageFragment", false);
            HomePageBean homePageBean = (HomePageBean) JsonUtil.getInstance().json2Obj(success, HomePageBean.class);
            processLogic(homePageBean.getResult().getAd());
            if (homePageBean.getResult().getAction() == null) {
                dismissLoadingDialog();
                return;
            }
            if (homePageBean.getResult().getAction().getLocal() == null || homePageBean.getResult().getAction().getLocal().size() == 0 || homePageBean.getResult().getAction().getLocal().isEmpty()) {
                ll_localTalent.setVisibility(View.GONE);
                hlv_localTalent.setVisibility(View.GONE);
            } else {
                ll_localTalent.setVisibility(View.VISIBLE);
                hlv_localTalent.setVisibility(View.VISIBLE);
                localTalentHomePageViewAdapter.clear();
                homePageBean.getResult().getAction().getLocal().get(homePageBean.getResult().getAction().getLocal().size() - 1).setStatusL("last");
                localTalentHomePageViewAdapter.addNewData(homePageBean.getResult().getAction().getLocal());
            }
            if (homePageBean.getResult().getAction().getHot() == null || homePageBean.getResult().getAction().getHot().size() == 0 || homePageBean.getResult().getAction().getHot().isEmpty()) {
                ll_popularStrategy.setVisibility(View.GONE);
                hlv_popularStrategy.setVisibility(View.GONE);
            } else {
                ll_popularStrategy.setVisibility(View.VISIBLE);
                hlv_popularStrategy.setVisibility(View.VISIBLE);
                popularStrategyHomePageViewAdapter.clear();
                homePageBean.getResult().getAction().getHot().get(homePageBean.getResult().getAction().getHot().size() - 1).setStatusL("last");
                popularStrategyHomePageViewAdapter.addNewData(homePageBean.getResult().getAction().getHot());
            }
            if (homePageBean.getResult().getAction().getNewX() == null || homePageBean.getResult().getAction().getNewX().size() == 0 || homePageBean.getResult().getAction().getNewX().isEmpty()) {
                ll_recentNews.setVisibility(View.GONE);
                hlv_recentNews.setVisibility(View.GONE);
            } else {
                ll_recentNews.setVisibility(View.VISIBLE);
                hlv_recentNews.setVisibility(View.VISIBLE);
                recentNewsHomePageViewAdapter.clear();
                homePageBean.getResult().getAction().getNewX().get(homePageBean.getResult().getAction().getNewX().size() - 1).setStatusL("last");
                recentNewsHomePageViewAdapter.addNewData(homePageBean.getResult().getAction().getNewX());
            }
        } else if (flag == 1) {
            tv_tag.setVisibility(View.GONE);
            aty.showActivity(aty, OverleafActivity.class);
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (flag == 1) {
            if (isLogin(msg)) {
                Intent intent = new Intent(aty, LoginActivity.class);
                // intent.putExtra("name", "GetOrderFragment");
                aty.showActivity(aty, intent);
                return;
            }
        }
        ViewInject.toast(msg);
    }

    /**
     * 初始化轮播图
     */

    public void initBanner() {
        mForegroundBanner.setAutoPlayAble(true);
        mForegroundBanner.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mForegroundBanner.setAllowUserScrollable(true);
        mForegroundBanner.setAutoPlayInterval(3000);
        // 初始化方式1：配置数据源的方式1：通过传入数据模型并结合 Adapter 的方式配置数据源。这种方式主要用于加载网络图片，以及实现少于3页时的无限轮播
        mForegroundBanner.setAdapter(this);
        mForegroundBanner.setDelegate(this);
    }

    /**
     * 广告轮播图
     */
    @SuppressWarnings("unchecked")
    private void processLogic(List<AdBean> list) {
        if (list != null && list.size() > 0) {
            mForegroundBanner.setBackground(null);
            mForegroundBanner.setData(list, null);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if (aty.getChageIcon() == 0) {
            mForegroundBanner.startAutoPlay();
            boolean isRefreshingHomePageFragment = PreferenceHelper.readBoolean(aty, StringConstants.FILENAME, "isRefreshingHomePageFragment", false);
            if (isRefreshingHomePageFragment) {
                String locationCity = PreferenceHelper.readString(aty, StringConstants.FILENAME, "selectCity", getString(R.string.allAeservationNumber));
                tv_address.setText(locationCity);
                //    showLoadingDialog(getString(R.string.dataLoad));
                thread1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (tv_address.getText().toString().equals(getString(R.string.allAeservationNumber))) {
//                        ((MainContract.Presenter)aty.mPresenter).getSystemMessage();
                            ((HomePageContract.Presenter) mPresenter).getHomePage("");
                        } else {
                            ((HomePageContract.Presenter) mPresenter).getHomePage(tv_address.getText().toString());
                        }
                    }
                });
                thread1.start();
            }
        }
    }

    @Override
    public void onChange() {
        super.onChange();
        boolean isRefreshingChangeHomePageFragment = PreferenceHelper.readBoolean(aty, StringConstants.FILENAME, "isRefreshingChangeHomePageFragment", false);
        if (isRefreshingChangeHomePageFragment) {
            String locationCity = PreferenceHelper.readString(aty, StringConstants.FILENAME, "selectCity", getString(R.string.allAeservationNumber));
            tv_address.setText(locationCity);
            showLoadingDialog(getString(R.string.dataLoad));
            if (tv_address.getText().toString().equals(getString(R.string.allAeservationNumber))) {
                ((HomePageContract.Presenter) mPresenter).getHomePage("");
            } else {
                ((HomePageContract.Presenter) mPresenter).getHomePage(tv_address.getText().toString());
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mForegroundBanner.stopAutoPlay();
    }


    @Override
    public void fillBannerItem(BGABanner banner, ImageView itemView, AdBean model, int position) {
        //   GlideImageLoader.glideOrdinaryLoader(aty, model.getAd_code(), itemView);
        GlideImageLoader.glideOrdinaryLoader(aty, model.getAd_code(), itemView, R.mipmap.placeholderfigure2);
    }

    @Override
    public void onBannerItemClick(BGABanner banner, ImageView itemView, AdBean model, int position) {
        if (StringUtils.isEmpty(model.getAd_link())) {
            return;
        }
        Intent bannerDetails = new Intent(aty, BannerDetailsActivity.class);
        bannerDetails.putExtra("url", model.getAd_link());
        bannerDetails.putExtra("title", model.getAd_name());
        aty.showActivity(aty, bannerDetails);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getId() == R.id.hlv_localTalent) {
            Intent intent = new Intent(aty, LocalTalentDetailsActivity.class);
            intent.putExtra("talent_id", localTalentHomePageViewAdapter.getItem(i).getTalent_id());
            aty.showActivity(aty, intent);
        } else if (adapterView.getId() == R.id.hlv_popularStrategy) {
            Intent intent = new Intent(aty, StrategyDetailsActivity.class);
            intent.putExtra("guide_id", popularStrategyHomePageViewAdapter.getItem(i).getGuide_id());
            aty.showActivity(aty, intent);
        } else if (adapterView.getId() == R.id.hlv_recentNews) {
            Intent intent = new Intent(aty, DynamicsDetailsActivity.class);
            intent.putExtra("act_id", recentNewsHomePageViewAdapter.getItem(i).getAct_id() + "");
            aty.showActivity(aty, intent);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == NumericConstants.LOCATION_CODE) {
            ViewInject.toast(aty.getString(R.string.locationRelatedPermission));
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endRefreshing();
        String locationCity = PreferenceHelper.readString(aty, StringConstants.FILENAME, "selectCity", getString(R.string.allAeservationNumber));
        tv_address.setText(locationCity);
        showLoadingDialog(getString(R.string.dataLoad));
        if (tv_address.getText().toString().equals(getString(R.string.allAeservationNumber))) {
            ((HomePageContract.Presenter) mPresenter).getHomePage("");
        } else {
            ((HomePageContract.Presenter) mPresenter).getHomePage(tv_address.getText().toString());
        }
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }


    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                //获取定位结果
                location.getTime();    //获取定位时间
                location.getLocationID();    //获取定位唯一ID，v7.2版本新增，用于排查定位问题
                location.getLocType();    //获取定位类型
                location.getLatitude();    //获取纬度信息
                location.getLongitude();    //获取经度信息
                location.getRadius();    //获取定位精准度
                location.getAddrStr();    //获取地址信息
                location.getCountry();    //获取国家信息
                location.getCountryCode();    //获取国家码
                location.getCity();    //获取城市信息
                location.getCityCode();    //获取城市码
                location.getDistrict();    //获取区县信息
                location.getStreet();    //获取街道信息
                location.getStreetNumber();    //获取街道码
                location.getLocationDescribe();    //获取当前位置描述信息
                location.getPoiList();    //获取当前位置周边POI信息
                location.getBuildingID();    //室内精准定位下，获取楼宇ID
                location.getBuildingName();    //室内精准定位下，获取楼宇名称
                location.getFloor();    //室内精准定位下，获取当前位置所处的楼层信息
                PreferenceHelper.write(aty, StringConstants.FILENAME, "locationCountry", location.getCountry());
                PreferenceHelper.write(aty, StringConstants.FILENAME, "locationCity", location.getCity());
                PreferenceHelper.write(aty, StringConstants.FILENAME, "location", location.getLongitude() + "," + location.getLatitude());
                Log.d("tag111", location.getCity());
                if (isFirst) {
                    if (StringUtils.isEmpty(location.getCity())) {
                        PreferenceHelper.write(aty, StringConstants.FILENAME, "locationCity", getString(R.string.allAeservationNumber));
//                        tv_address.setText(getString(R.string.allAeservationNumber));
//                        ((HomePageContract.Presenter) mPresenter).getHomePage("");
                        PreferenceHelper.write(aty, StringConstants.FILENAME, "location", "");
                    } else {
//                        tv_address.setText(location.getCity());
                        //           PreferenceHelper.write(aty, StringConstants.FILENAME, "selectCity", location.getCity());
                        //  ((HomePageContract.Presenter) mPresenter).getHomePage(location.getCity());
                    }
                }
                isFirst = false;
                if (location.getLocType() == BDLocation.TypeGpsLocation) {
                    //当前为GPS定位结果，可获取以下信息
                    location.getSpeed();    //获取当前速度，单位：公里每小时
                    location.getSatelliteNumber();    //获取当前卫星数
                    location.getAltitude();    //获取海拔高度信息，单位米
                    location.getDirection();    //获取方向信息，单位度
                    //  location.
                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {

                    //当前为网络定位结果，可获取以下信息
                    location.getOperators();    //获取运营商信息

                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {

                    //当前为网络定位结果

                } else if (location.getLocType() == BDLocation.TypeServerError) {

                    //当前网络定位失败
                    //可将定位唯一ID、IMEI、定位失败时间反馈至loc-bugs@baidu.com
                    //   ((HomePageContract.Presenter) mPresenter).getHomePage("");
                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {

                    //当前网络不通
                    //    ((HomePageContract.Presenter) mPresenter).getHomePage("");
                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {

                    //当前缺少定位依据，可能是用户没有授权，建议弹出提示框让用户开启权限
                    //可进一步参考onLocDiagnosticMessage中的错误返回码
                    //    ((HomePageContract.Presenter) mPresenter).getHomePage("");
                }
            }
        }

        /**
         * 回调定位诊断信息，开发者可以根据相关信息解决定位遇到的一些问题
         * 自动回调，相同的diagnosticType只会回调一次
         *
         * @param locType           当前定位类型
         * @param diagnosticType    诊断类型（1~9）
         * @param diagnosticMessage 具体的诊断信息释义
         */
        public void onLocDiagnosticMessage(int locType, int diagnosticType, String diagnosticMessage) {
            PreferenceHelper.write(aty, StringConstants.FILENAME, "location", "");
            PreferenceHelper.write(aty, StringConstants.FILENAME, "locationCity", getString(R.string.locateFailure));
            PreferenceHelper.write(aty, StringConstants.FILENAME, "selectCity", getString(R.string.locateFailure));
            //  ((HomePageContract.Presenter) mPresenter).getHomePage("");
            Log.d("tag111", "000000");
            if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_BETTER_OPEN_GPS) {
                //建议打开GPS
                Log.d("tag111", "1111");
            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_BETTER_OPEN_WIFI) {

                //建议打开wifi，不必连接，这样有助于提高网络定位精度！
                Log.d("tag111", "2222");
            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_CHECK_LOC_PERMISSION) {

                //定位权限受限，建议提示用户授予APP定位权限！
                Log.d("tag111", "33333");
            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_CHECK_NET) {

                //网络异常造成定位失败，建议用户确认网络状态是否异常！
                Log.d("tag111", "444444");
            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_CLOSE_FLYMODE) {

                //手机飞行模式造成定位失败，建议用户关闭飞行模式后再重试定位！
                Log.d("tag111", "455555");
            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_INSERT_SIMCARD_OR_OPEN_WIFI) {

                //无法获取任何定位依据，建议用户打开wifi或者插入sim卡重试！
                Log.d("tag111", "666666");
            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_OPEN_PHONE_LOC_SWITCH) {
                //无法获取有效定位依据，建议用户打开手机设置里的定位开关后重试！
                Log.d("tag111", "77777");
            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_SERVER_FAIL) {
                //百度定位服务端定位失败
                //建议反馈location.getLocationID()和大体定位时间到loc-bugs@baidu.com
                Log.d("tag111", "8888888");
            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_FAIL_UNKNOWN) {
                //无法获取有效定位依据，但无法确定具体原因
                //建议检查是否有安全软件屏蔽相关定位权限
                //或调用LocationClient.restart()重新启动后重试！
                Log.d("tag111", "9999999");
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocationClient.unRegisterLocationListener(myListener); //注销掉监听
        mLocationClient.stop(); //停止定位服务
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == STATUS && resultCode == RESULT_OK) {// 如果等于1
            String selectCity = data.getStringExtra("selectCity");
            int selectCityId = data.getIntExtra("selectCityId", 0);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "selectCity", selectCity);
            tv_address.setText(selectCity);
            showLoadingDialog(aty.getString(R.string.dataLoad));
            ((HomePageContract.Presenter) mPresenter).getHomePage(selectCity);
            Log.d("tag888", selectCity);
        }
    }
}
