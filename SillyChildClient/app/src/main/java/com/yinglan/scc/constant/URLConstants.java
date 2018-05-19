package com.yinglan.scc.constant;

/**
 * 用于存放url常量的类
 * Created by ruitu ck on 2016/9/14.
 */

public class URLConstants {

    /**
     * 正式服务器地址URL
     */
//    public static String SERVERURL = "http://user.api.shahaizi.shop/";
//    public static String SERVERURLBUS = "http://business.api.shahaizi.shop/";
//    public static String SERVERURLADMIN = "http://admin.shahaizi.shop/";

    /**
     * 测试服务器地址URL
     */
    public static String SERVERURL = "http://api.shahaizi.keiousoft.com/";
//    public static String SERVERURLBUS = "http://shz.api.bussiness.ruitukeji.cn:8503/";
//    public static String SERVERURLADMIN = "http://shz.admin.ruitukeji.cn:8504/";

    /**
     * 请求地址URL
     */
//    public static String APIURLFORPAY = SERVERURL + "index.php";
    public static String APIURL = SERVERURL + "api/mobile/";
//    public static String APIURL1 = SERVERURLBUS;
//    public static String APIURL2 = SERVERURLADMIN;
//    public static String APIURL3 = SERVERURL;

    /**
     * 应用配置参数
     */
    public static String APPCONFIG = APIURL + "appConfig";

    /**
     * 启动页和广告页
     */
    public static String START = APIURL + "lastApk";

    /**
     * 获取最新apk下载地址
     */
    public static String LASTAPK = APIURL + "lastApk";

    /**
     * 获取分类广告
     */
    public static String ADVCAT = APIURL + "adv/adv-cat.do";

    /**
     * 首页
     */
    public static String HOMEGOODS = APIURL + "goods/home-goods";

    /**
     * 首页----获取商品分类
     */
    public static String GOODSCATLIST = APIURL + "goodscat/list.do";

    /**
     * 首页---更多分类----商品列表
     */
    public static String GOODSLIST = APIURL + "goods/list.do";

    /**
     * 首页---更多分类----商品列表----店铺首页
     */
    public static String STOREINDEXGOODS = APIURL + "store/index-goods.do";

    /**
     * 首页---更多分类----商品列表----店铺商品
     */
    public static String STOREGOODSLIST = APIURL + "store/goods-list.do";

    /**
     * 首页---更多分类----商品列表----商品详情
     */
    public static String GOODDETAIL = APIURL + "goods/detail.do";

    /**
     * 首页---更多分类----商品列表----商品详情----商品规格
     */
    public static String GOODSSPEC = APIURL + "goods/spec.do";

    /**
     * 活动
     */
    public static String ACTIVITYGOOD = APIURL + "goods/activity-goods";

    /**
     * 得到地区的热门城市
     */
    public static String CHILDHOTCITY = APIURL + "m=Api&c=Region&a=getChildHotCity";

    /**
     * 得到国内全部城市
     */
    public static String ALLCITY = APIURL + "m=Api&c=Region&a=getAllCity";

    /**
     * 得到全部城市
     */
    public static String GETALLCITYBYCOUNTRY = APIURL + "m=Api&c=Region&a=getAllCityByCountryId";
    public static String GETALLCOUNTRYCITY = APIURL + "m=Api&c=Region&a=getAllCountryCity";

    /**
     * 得到热门城市
     */
    public static String GETHOTCITYBYCOUNTRY = APIURL + "m=Api&c=Region&a=getHotCityByCountryId";


    /**
     * 得到国外地区的首级列表
     */
    public static String INDEXCITY = APIURL + "m=Api&c=Region&a=getIndexCity";

    /**
     * 得到国外地区的子级列表
     */
    public static String CHILDCITY = APIURL + "m=Api&c=Region&a=getChildCity";

    /**
     * 得到国外地区的子级列表
     */
    public static String ALLCOUNTRY1 = APIURL + "m=Api&c=Region&a=getCountry";

    /**
     * 接机 聊天
     */
//    public static String RECEIVEORDER = APIURL2 + "index.php/admin/CarOrder/receive_order_info/air_id/";
//    public static String IMAGE_RECEIVEORDER_URL = "http://img.shahaizi.cn/system/package_order/jieji.png";

    /**
     * 送机 聊天
     */
//    public static String SENDORDERINFO = APIURL2 + "index.php/admin/CarOrder/send_order_info/air_id/";
//    public static String IMAGE_SENDORDERINFO_URL = "http://img.shahaizi.cn/system/package_order/songji.png";

    /**
     * 单次接送 聊天
     */
    //  public static String ONCEORDER = APIURL2 + "index.php/admin/CarOrder/once_order_info/air_id/";
    public static String IMAGE_ONCEORDER_URL = "http://img.shahaizi.cn/system/package_order/dancijiesong.png";

    /**
     * 线路订单  聊天
     */
    // public static String LINEORDER = APIURL2 + "index.php/admin/CarOrder/line_order_info/air_id/";
    public static String IMAGE_LINEORDER_URL = "http://o8ugkv090.bkt.clouddn.com/hd_one.png";

    /**
     * 私人定制  聊天
     */
    //  public static String PRIVATEORDER = APIURL2 + "index.php/admin/CarOrder/private_order_add/air_id/";
    public static String IMAGE_PRIVATEORDER_URL = "http://img.shahaizi.cn/system/package_order/siren.png";

    /**
     * 按天包车游 聊天
     */
    // public static String BYDAYORDER = APIURL2 + "index.php/admin/CarOrder/byday_order_info/air_id/";
    public static String IMAGE_BYDAYORDER_URL = "http://img.shahaizi.cn/system/package_order/antian.png";

    /**
     * 搜索城市
     */
    public static String SEARCHCITY = APIURL + "m=Api&c=Region&a=searchCity";

    /**
     * 首页--当地达人列表
     */
    public static String LOCALTALENT = APIURL + "m=Api&c=LocalTalent&a=getLocalTalentList";

    /**
     * 首页--达人详情
     */
    public static String TALENTDETAILS = APIURL + "m=api&c=LocalTalent&a=getLocalTalentDetail";

    /**
     * 首页--当地达人点赞
     */
    //  public static String LOCALTALENTPRAISE = APIURL3 + "api/LocalTalent/praise";

    /**
     * 首页--热门攻略
     */
    public static String HOTSTRATEGY = APIURL + "m=Api&c=HotGuide&a=getHotGuideList";

    /**
     * 出行----地区攻略
     */
    public static String STRATEGY = APIURL + "m=Api&c=HotGuide&a=getGuideList";

    /**
     * 出行----地区选择
     */
    public static String ALLCOUNTRY = APIURL + "m=Api&c=Region&a=getAllCountry";

    /**
     * 首页--得到热门攻略详情
     * http://shz.api.user.ruitukeji.cn:8502/web/guide/detail?id=
     */
    public static String HOTGUIDEDETAIL = APIURL + "m=Api&c=HotGuide&a=getHotGuideDetail";

    /**
     * 首页--攻略详情
     * http://shz.api.user.ruitukeji.cn:8502/web/guide/detail?id=
     */
    //  public static String GUIDEDETAIL = APIURL3 + "web/guide/detail?id=";

    /**
     * 首页--攻略收藏
     * http://shz.api.user.ruitukeji.cn:8502/web/guide/detail?id=
     */
    public static String COLLECTSTRATEGY = APIURL + "m=Api&c=User&a=collectStrategy";

    /**
     * 首页--得到热门攻略详情----点赞
     */
    //  public static String STRATEGYPRAISE = APIURL3 + "api/comment/doGoodByGuide";

    /**
     * 首页--包车定制
     */
    public static String CHARTERCUSTOM = APIURL + "m=Api&c=PackLine&a=home";

    /**
     * 首页--包车定制---搜索司导
     */
    public static String SEARCHDRIVER = APIURL + "m=Api&c=DriverPack&a=searchDriver";

    /**
     * 首页--包车定制---精确查找司导
     */
    public static String FINDDRIVER = APIURL + "m=Api&c=DriverPack&a=findDriver";

    /**
     * 首页--包车定制---包车列表  接送机列表
     */
    //   public static String PACKCARPRODUCT = APIURL3 + "api/packCarProduct";

    /**
     * 首页----包车定制---收藏包车产品
     */
    public static String COLLECTCHARTER = APIURL + "m=Api&c=User&a=collectPackPro";

    /**
     * 首页--包车定制---包车详情
     */
    //  public static String PACKCARPRODUCTDETAIL = APIURL3 + "web/PackCarProduct/detail?id=";

    /**
     * 首页--包车定制--- 改退|费用补偿
     */
    public static String RECHARGEDESC = APIURL + "m=Api&c=Config&a=articleInfo";

    /**
     * 首页----包车定制---车型类型列表
     */
    public static String GETCARWHERE = APIURL + "m=Api&c=Car&a=getCarWhere";

    /**
     * 首页----包车定制-----按天包车游
     */
    public static String RENTCARBYDAY = APIURL + "m=Api&c=DriverPack&a=rentCarByDay";

    /**
     * 首页----包车定制-----单次接送
     */
    public static String ONCEPICKUP = APIURL + "m=Api&c=DriverPack&a=oncePickup";

    /**
     * 首页----包车定制-----接机
     */
    public static String RECEVIVEAIRPORT = APIURL + "m=Api&c=DriverPack&a=receiveAirport";

    /**
     * 首页----包车定制-----送机
     */
    public static String SENDARIPORT = APIURL + "m=Api&c=DriverPack&a=sendAirport";

    /**
     * 首页----包车定制-----私人定制
     */
    public static String PRIVATEMAKE = APIURL + "m=Api&c=DriverPack&a=privateMake";

    /**
     * 首页----包车定制-----私人定制配置
     */
    public static String DRIVERPACKCONFIG = APIURL + "m=Api&c=DriverPack&a=getConfig";

    /**
     * 首页----包车定制-----私人定制----得到私人定制的行程详情
     */
    public static String GETPRIVATEDETAIL = APIURL + "m=Api&c=DriverPack&a=getPrivateDetail";
    public static String GETPRIVATEDETAIL1 = APIURL + "m=web&c=route&a=detail&air_id=";

    /**
     * 首页----包车定制-----私人定制----保存用户私人定制信息
     */
    public static String SAVEUSERPRIVATE = APIURL + "m=Api&c=DriverPack&a=saveUserPrivate";

    /**
     * 首页--精品路线
     */
    public static String QUALITYLINE = APIURL + "m=Api&c=PackLine&a=getQualityLine";

    /**
     * 首页--精品路线---线路详情
     */
    public static String ROUTEDETAILS1 = APIURL + "m=Api&c=PackLine&a=detail";

    /**
     * 首页--精品路线---线路详情
     */
    //   public static String ROUTEDETAILS = APIURL3 + "web/PackLine/detail?id=";

    /**
     * 首页--精品路线---收藏取消路线操作
     */
    public static String COLLECTLINE = APIURL + "m=Api&c=User&a=collectLine";

    /**
     * 首页----包车定制---精品路线---提交订单
     */
    public static String CONFIRMORDER = APIURL + "m=Api&c=PackOrder&a=createPackOrder";

    /**
     * 首页----包车定制---接送机---得到车型信息
     */
    public static String CARINFO = APIURL + "m=Api&c=Car&a=getCarInfo";

    /**
     * 首页----包车定制---接送机---得到车辆品牌列表
     */
    public static String GETCARBRAND = APIURL + "m=Api&c=Car&a=getCarBrand";

    /**
     * 首页----包车定制---接送机---得到车辆列表
     */
    public static String GETCARLIST = APIURL + "m=Api&c=Car&a=getCarList";


    /**
     * 首页--包车定制--全部司导
     */
    public static String ALLCOMPANYGUIDE = APIURL + "m=Api&c=DriverPack&a=getAllDriver";

    /**
     * 首页--包车定制--全部司导--司导详情
     */
    public static String COMPANYGUIDEDETAILS = APIURL + "m=Api&c=DriverPack&a=getDriverDetail";
    // public static String COMPANYGUIDEDETAILS1 = APIURL3 + "web/driver/detail?id=";
    //  public static String COMPANYGUIDEDETAILS2 = APIURL3 + "web/driver/";


    /**
     * 首页----全部动态
     */
    //   public static String ALLDYNAMICS = APIURL3 + "Api/Dynamic";

    /**
     * 首页-----动态详情
     */
    public static String GETDYNAMICDETAIL = APIURL + "m=Api&c=User&a=getDynamicDetail";

    /**
     * 首页-----动态详情-----关注
     */
    public static String ATTENTION = APIURL + "m=Api&c=UserInfo&a=attention";

    /**
     * 首页-----动态详情-----点赞
     */
    //  public static String PRAISEDYNAMICS = APIURL3 + "api/comment/newActionTags";

    /**
     * 首页-----动态详情-----收藏
     */
    public static String COLLECTDYNAMIC = APIURL + "m=Api&c=User&a=collectDynamic";

    /**
     * 首页-----动态详情-----最新动态评论(回复)
     */
    //  public static String NEWACTIONCOMMENT = APIURL3 + "api/comment/newActionComment";

    /**
     * 首页-----动态详情-----对评论进行点赞
     */
    //  public static String DOGOODBYCOMMENT = APIURL3 + "api/comment/doGoodByComment";

    /**
     * 首页-----动态详情-----得到动态评论
     */
    //  public static String DYNAMICSCOMMENTARIES = APIURL3 + "api/comment/getAllComment";

    /**
     * 出行
     */
    public static String TRIP = APIURL + "m=Api&c=Going&a=home";

    /**
     * 上传头像
     */
    public static String UPLOADAVATAR = APIURL + "user/uploadAvatar";

    /**
     * 上传图片
     */
    public static String UPLOADQFCTIMG = APIURL + "m=Api&c=File&a=uploadImg";

    /**
     * 置换Token  get请求
     */
    public static String REFRESHTOKEN = APIURL + "m=Api&c=User&a=flashToken";

    /**
     * 获取环信单个用户信息  get请求
     */
    public static String getHxSingleUser = "http://shz.api.bussiness.ruitukeji.cn:8503//index.php?m=Api&c=User&a=getHxSingleUser";

    /**
     * 登录
     */
    public static String USERLOGIN = APIURL + "member/login.do";

    /**
     * 获取会员登录状态
     */
    public static String ISLOGIN = APIURL + "member/islogin.do";

    /**
     * 退出登录
     */
    public static String LOGOUT = APIURL + "member/logout.do";

    /**
     * 第三方登录
     */
    public static String USERTHIRDLOGIN = APIURL + "m=Api&c=User&a=thirdLogin";

    /**
     * 短信验证码【手机号注册】
     * 验证码类型 reg=注册 resetpwd=找回密码 login=登陆 bind=绑定手机号.
     */
    public static String SENDREGISTER = APIURL + "member/send-register-code.do";

    /**
     * 短信验证码【找回、修改密码】
     * 验证码类型 reg=注册 resetpwd=找回密码 login=登陆 bind=绑定手机号.
     */
    public static String SENDFINFDCODE = APIURL + "member/send-find-code.do";

    /**
     * 发送邮箱验证码
     * 验证码类型 reg=注册 resetpwd=找回密码 login=登陆 bind=绑定邮箱.
     */
    public static String SENDEMAILCAPTCHA = APIURL + "m=Api&c=BaseMessage&a=sendMailCaptcha";

    /**
     * 更改邮箱
     * 验证码类型 reg=注册 resetpwd=找回密码 login=登陆 bind=绑定邮箱.
     */
    public static String CHANGEEMAIL = APIURL + "m=Api&c=User&a=changeBindMail";

    /**
     * 绑定邮箱
     * 验证码类型 reg=注册 resetpwd=找回密码 login=登陆 bind=绑定邮箱.
     */
    public static String BINDEMAIL = APIURL + "m=Api&c=User&a=BindMail";

    /**
     * 绑定手机号
     * 验证码类型 reg=注册 resetpwd=找回密码 login=登陆 bind=绑定邮箱.
     */
    //  public static String BINDPHONE = APIURL3 + "Api/User/thirdBindPhone";


    /**
     * 用户注册
     */
    public static String REGISTER = APIURL + "member/mobile-register.do";

    /**
     * 得到国家区号
     */
    public static String COUNTRYNUMBER = APIURL + "m=Api&c=Config&a=getCountryNumber";

    /**
     * 更改密码【手机】
     */
    public static String USERRESTPWD = APIURL + "member/mobile-change-pass.do";

    public static String FORTGRTBYMAIL = APIURL + "m=Api&c=User&a=forgetPasswordByMail";

    /**
     * 物流定位轨迹搜素
     */
    public static String LOGISTICSPOSITIONING = "http://yuntuapi.amap.com/datamanage/data/list";

    /**
     * 消息-系统消息列表
     */
    public static String SYSTEMMESSAGELIST = APIURL + "m=Api&c=Message&a=getSystemMessage";

    /**
     * 消息-系统消息详情
     */
    public static String SYSTEMMESSAGEDETAIL = APIURL + "m=Api&c=Message&a=getSystemMessageInfo";

    /**
     * 消息-设置系统消息已读
     */
    public static String READMESSAGE = APIURL + "m=Api&c=Message&a=readMessage";

    /**
     * 消息-得到进行中订单关联的环信用户列表
     */
    //   public static String HXUSERLIST = APIURL3 + "Api/Message/getHxUserList";

    /**
     * 获取用户信息
     */
    public static String USERINFO = APIURL + "member/info.do";

    /**
     * 会员资料保存
     */
    public static String SAVEINFO = APIURL + "member/save.do";

    /**
     * 获取收货地址列表
     */
    public static String ADDRESSLIST = APIURL + "address/list.do";

    /**
     * 设置默认收货地址
     */
    public static String DEFAULTADDRESS = APIURL + "address/set-default.do";

    /**
     * 删除收货地址
     */
    public static String DELETEADDRESS = APIURL + "address/delete.do";

    /**
     * 获取详细收货地址
     */
    public static String ADDRESS = APIURL + "address/get.do";

    /**
     * 根据父id获取地址列表
     */
    public static String REGIONLIST = APIURL + "address/region-list.do";

    /**
     * 编辑收货地址
     */
    public static String EDITADDRESS = APIURL + "address/edit.do";

    /**
     * 添加认收货地址
     */
    public static String ADDADDRESS = APIURL + "address/add.do";

    /**
     * 获取收藏商品列表
     */
    public static String FAVORITEGOODLIST = APIURL + "favorite/list.do";

    /**
     * 取消收藏
     */
    public static String UNFAVORITEGOOD = APIURL + "favorite/unfavorite.do";

    /**
     * 添加到购物车
     */
    public static String AGGCARTGOOD = APIURL + "cart/add.do";

    /**
     * 获取购物车商品列表
     */
    public static String CARTLIST = APIURL + "cart/list.do";

    /**
     * 删除购物车中的某项
     */
    public static String CARTDELETE = APIURL + "cart/delete.do";

    /**
     * 更新购物车某项商品数量
     */
    public static String CARTUPDATE = APIURL + "cart/update.do";


}
