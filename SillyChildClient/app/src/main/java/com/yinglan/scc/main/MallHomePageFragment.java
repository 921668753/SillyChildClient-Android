package com.yinglan.scc.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.common.cklibrary.utils.myview.ChildListView;
import com.kymjs.common.Log;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.yinglan.scc.R;
import com.yinglan.scc.adapter.homepage.BoutiqueLineViewAdapter;
import com.yinglan.scc.adapter.homepage.HotRegionViewAdapter;
import com.yinglan.scc.adapter.main.MallHomePageGoodAdapter;
import com.yinglan.scc.constant.NumericConstants;
import com.yinglan.scc.entity.HomePageBean;
import com.yinglan.scc.entity.HomePageBean.ResultBean.AdBean;
import com.yinglan.scc.homepage.BannerDetailsActivity;
import com.yinglan.scc.homepage.goodslist.GoodsListActivity;
import com.yinglan.scc.homepage.goodslist.SpacesItemDecoration;
import com.yinglan.scc.homepage.goodslist.goodsdetails.GoodsDetailsActivity;
import com.yinglan.scc.homepage.moreclassification.MoreClassificationActivity;
import com.yinglan.scc.loginregister.LoginActivity;
import com.yinglan.scc.utils.DisplayUtil;
import com.yinglan.scc.utils.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 商城首页
 * Created by Admin on 2017/8/10.
 */
public class MallHomePageFragment extends BaseFragment implements EasyPermissions.PermissionCallbacks, HomePageContract.View, BGABanner.Delegate<ImageView, AdBean>, BGABanner.Adapter<ImageView, AdBean>,  BGARefreshLayout.BGARefreshLayoutDelegate , MallHomePageGoodAdapter.GoodsListItemOnClickListener, PullLoadMoreRecyclerView.PullLoadMoreListener{

    private MainActivity aty;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    /**
     * 轮播图
     */
    @BindView(id = R.id.banner_ad)
    private BGABanner mForegroundBanner;

    /**
     * 护肤彩妆
     */
    @BindView(id = R.id.ll_cosmetics, click = true)
    private LinearLayout ll_cosmetics;

    /**
     * 个人护理
     */
    @BindView(id = R.id.ll_personalCare, click = true)
    private LinearLayout ll_personalCare;

    /**
     * 母婴
     */
    @BindView(id = R.id.ll_maternalAndInfant, click = true)
    private LinearLayout ll_maternalAndInfant;

    /**
     * 包包配饰
     */
    @BindView(id = R.id.ll_bagAccessories, click = true)
    private LinearLayout ll_bagAccessories;

    /**
     * 服装鞋帽
     */
    @BindView(id = R.id.ll_clothingAndShoes, click = true)
    private LinearLayout ll_clothingAndShoes;

    /**
     * 家电数码
     */
    @BindView(id = R.id.ll_homeApplianceDigital, click = true)
    private LinearLayout ll_homeApplianceDigital;

    /**
     * 家居
     */
    @BindView(id = R.id.ll_household, click = true)
    private LinearLayout ll_household;

    /**
     * 美颜保健
     */
    @BindView(id = R.id.ll_beautyCare, click = true)
    private LinearLayout ll_beautyCare;

    /**
     * 美食
     */
    @BindView(id = R.id.ll_food, click = true)
    private LinearLayout ll_food;

    /**
     * 更多
     */
    @BindView(id = R.id.ll_more, click = true)
    private LinearLayout ll_more;

    /**
     * 商品列表
     */

    @BindView(id = R.id.pullLoadMoreRecyclerView)
    private PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;

    public LocationClient mLocationClient = null;

    public BDAbstractLocationListener myListener = null;
//BDAbstractLocationListener为7.2版本新增的Abstract类型的监听接口，原有BDLocationListener接口暂时同步保留。具体介绍请参考后文中的说明

    private boolean isFirst = true;
    private List listbean;
    private SpacesItemDecoration spacesItemDecoration = null;

    private MallHomePageGoodAdapter mallHomePageGoodAdapter = null;


    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_mall, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new HomePagePresenter(this);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, false);
        mLocationClient = new LocationClient(aty.getApplicationContext());
        myListener = new MyLocationListener();
        mPullLoadMoreRecyclerView.setRefreshing(false);
        mPullLoadMoreRecyclerView.setStaggeredGridLayout(2);
        listbean = new ArrayList<>();
        spacesItemDecoration = new SpacesItemDecoration(5, 10);
        mallHomePageGoodAdapter = new MallHomePageGoodAdapter(aty, listbean, this);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        initBanner();
        mPullLoadMoreRecyclerView.setAdapter(mallHomePageGoodAdapter);
        //设置item之间的间隔
        mPullLoadMoreRecyclerView.addItemDecoration(spacesItemDecoration);
        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数
        ((HomePagePresenter) mPresenter).initLocation(aty, mLocationClient);
        showLoadingDialog(aty.getString(R.string.dataLoad));
        ((HomePagePresenter) mPresenter).getHomePage("");
        //   EMConversation conversation = EMClient.getInstance().chatManager().getConversation(BuildConfig.HUANXIN_IM);
        //    try {
        //    if (conversation.getUnreadMsgCount() > 0) {
        //   tv_tag.setVisibility(View.GONE);
        //     } else {
        //    tv_tag.setVisibility(View.GONE);
        //     }
        //   } catch (Exception e) {
        // tv_tag.setVisibility(View.GONE);
        //    }
    }

    /**
     * @param v 控件监听事件
     */
    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_cosmetics:
                Intent cosmeticsIntent = new Intent(aty, GoodsListActivity.class);
                cosmeticsIntent.putExtra("classification", "");
                aty.showActivity(aty, cosmeticsIntent);
                break;
            case R.id.ll_personalCare:
                Intent personalCareIntent = new Intent(aty, GoodsListActivity.class);
                personalCareIntent.putExtra("classification", "");
                aty.showActivity(aty, personalCareIntent);
                break;
            case R.id.ll_maternalAndInfant:
                Intent maternalAndInfantIntent = new Intent(aty, GoodsListActivity.class);
                maternalAndInfantIntent.putExtra("classification", "");
                aty.showActivity(aty, maternalAndInfantIntent);
                break;
            case R.id.ll_bagAccessories:
                Intent bagAccessories = new Intent(aty, GoodsListActivity.class);
                bagAccessories.putExtra("classification", "");
                aty.showActivity(aty, bagAccessories);
                break;
            case R.id.ll_clothingAndShoes:
                Intent clothingAndShoesIntent = new Intent(aty, GoodsListActivity.class);
                clothingAndShoesIntent.putExtra("classification", "");
                aty.showActivity(aty, clothingAndShoesIntent);
                break;
            case R.id.ll_homeApplianceDigital:
                Intent homeApplianceDigitalIntent = new Intent(aty, GoodsListActivity.class);
                homeApplianceDigitalIntent.putExtra("classification", "");
                aty.showActivity(aty, homeApplianceDigitalIntent);
                break;
            case R.id.ll_household:
                Intent householdIntent = new Intent(aty, GoodsListActivity.class);
                householdIntent.putExtra("classification", "");
                aty.showActivity(aty, householdIntent);
                break;
            case R.id.ll_beautyCare:
                Intent beautyCareIntent = new Intent(aty, GoodsListActivity.class);
                beautyCareIntent.putExtra("classification", "");
                aty.showActivity(aty, beautyCareIntent);
                break;
            case R.id.ll_food:
                Intent foodIntent = new Intent(aty, GoodsListActivity.class);
                foodIntent.putExtra("classification", "");
                aty.showActivity(aty, foodIntent);
                break;
            case R.id.ll_more:
                aty.showActivity(aty, MoreClassificationActivity.class);
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
//            if (homePageBean.getResult().getAction().getLocal() == null || homePageBean.getResult().getAction().getLocal().size() == 0 || homePageBean.getResult().getAction().getLocal().isEmpty()) {
//            } else {
//                hotRegionViewAdapter.clear();
//                homePageBean.getResult().getAction().getLocal().get(homePageBean.getResult().getAction().getLocal().size() - 1).setStatusL("last");
//                hotRegionViewAdapter.addNewData(homePageBean.getResult().getAction().getLocal());
//            }
//            if (homePageBean.getResult().getAction().getHot() == null || homePageBean.getResult().getAction().getHot().size() == 0 || homePageBean.getResult().getAction().getHot().isEmpty()) {
//                clv_boutiqueLine.setVisibility(View.GONE);
//            } else {
//                clv_boutiqueLine.setVisibility(View.VISIBLE);
//                boutiqueLineViewAdapter.clear();
//                boutiqueLineViewAdapter.addNewData(homePageBean.getResult().getAction().getHot());
//            }
        } else if (flag == 1) {
            dismissLoadingDialog();
//            tv_tag.setVisibility(View.GONE);
//            aty.showActivity(aty, OverleafActivity.class);
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
            if (list.size() == 1) {
                mForegroundBanner.setAutoPlayAble(false);
                mForegroundBanner.setAllowUserScrollable(false);
            } else {
                mForegroundBanner.setAutoPlayAble(true);
                mForegroundBanner.setAllowUserScrollable(true);
            }
            mForegroundBanner.setBackground(null);
            mForegroundBanner.setData(list, null);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if (aty.getChageIcon() == 0) {
            mForegroundBanner.startAutoPlay();
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        if (aty.getChageIcon() == 0) {
            mForegroundBanner.stopAutoPlay();
        }
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
        String locationCity = PreferenceHelper.readString(aty, StringConstants.FILENAME, "selectCity", getString(R.string.allAeservationNumber));
        showLoadingDialog(getString(R.string.dataLoad));
        //   if (tv_address.getText().toString().equals(getString(R.string.allAeservationNumber))) {
        ((HomePageContract.Presenter) mPresenter).getHomePage("");
//        } else {
//            ((HomePageContract.Presenter) mPresenter).getHomePage(tv_address.getText().toString());
//        }
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void goodsListOnItemClick(View view, int postion) {
        Intent intent = new Intent(aty, GoodsDetailsActivity.class);
        // intent.putExtra("good_id", listbean.get(postion));
        aty.showActivity(aty, intent);
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
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocationClient.unRegisterLocationListener(myListener); //注销掉监听
        mLocationClient.stop(); //停止定位服务
    }
}
