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

    /**
     * 请求地址URL
     */
    public static String APIURL = SERVERURL + "api/mobile/";

    /**
     * 获取七牛云key-ok
     */
    public static String QINIUKEY = SERVERURL + "api/public/key/qiniu.do";

    /**
     * 应用配置参数
     */
    public static String APPCONFIG = APIURL + "appConfig";

    /**
     * 根据融云token获取头像性别昵称
     */
    public static String SYSRONGCLOUD = APIURL + "sys/rongCloud.do";

    /**
     * 获取首页信息
     */
    public static String HOMEPAGE = APIURL + "page/home-list.do";

    /**
     * 首页  活动
     */
    public static String ACTIVITYGOOD = APIURL + "goods/activity-goods.do";

    /**
     * 获取分类广告
     */
    public static String ADVCAT = APIURL + "adv/adv-cat.do";

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
    public static String STOREIMAGE = APIURL + "store/image.do";
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
     * 首页---更多分类----商品列表----商品详情---收藏商品
     */
    public static String FAVORITADD = APIURL + "favorite/add.do";

    /**
     * 首页---更多分类----商品列表----商品详情----取消收藏商品
     */
    public static String UNFAVORIT = APIURL + "favorite/unfavorite.do";

    /**
     * 首页---更多分类----商品列表----商品详情----商品规格
     */
    public static String GOODSSPEC = APIURL + "goods/spec.do";

    /**
     * 获取系统消息首页
     */
    public static String NEWLISTBUYTITLE = APIURL + "news/listByTitle.do";

    /**
     * 获取消息列表
     */
    public static String NEWTITLE = APIURL + "news/title.do";

    /**
     * 选中某条消息并设为已读
     */
    public static String NEWSELECT = APIURL + "news/select.do";


    public static String GETPRIVATEDETAIL1 = APIURL + "m=web&c=route&a=detail&air_id=";


    /**
     * 置换Token  get请求
     */
    public static String REFRESHTOKEN = APIURL + "m=Api&c=User&a=flashToken";

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
     * 获取第三方登录验证码
     */
    public static String THIRDCODE = APIURL + "member/thirdCode.do";

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
     * 用户注册
     */
    public static String REGISTER = APIURL + "member/mobile-register.do";


    /**
     * 更改密码【手机】
     */
    public static String USERRESTPWD = APIURL + "member/mobile-change-pass.do";

    /**
     * 消息-系统消息列表
     */
    public static String SYSTEMMESSAGELIST = APIURL + "m=Api&c=Message&a=getSystemMessage";

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
     * 获取钱包余额
     */
    public static String PURSEGET = APIURL + "purse/get.do";

    /**
     * 优惠券列表
     */
    public static String COUPONS = APIURL + "member/bonus-main.do";

    /**
     * 提现
     */
    public static String PURSECASH = APIURL + "purse/cash.do";

    /**
     * 银行卡列表
     */
    public static String PURSELIST = APIURL + "purse/list.do";

    /**
     * 银行卡列表
     */
    public static String PURSEBANK = APIURL + "purse/banks.do";

    /**
     * 删除银行卡
     */
    public static String PURSEREMOVE = APIURL + "purse/remove.do";

    /**
     * 设置默认银行卡
     */
    public static String PURSEDEFAULT = APIURL + "purse/default.do";

    /**
     * 添加银行卡(可添加支付宝账号)
     */
    public static String PURSEADD = APIURL + "purse/add.do";


    /**
     * 用户充值信息接口
     */
    public static String ONLINEREC = APIURL + "online/rec.do";


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

    /**
     * 确认订单
     */
    public static String CARTBALANCE = APIURL + "order/cartBalance.do";

    /**
     * 创建付款订单
     */
    public static String CREATEORDER = APIURL + "order/createOrder.do";

    /**
     * 订单支付信息接口
     */
    public static String ONLINEPAY = APIURL + "online/pay.do";

    /**
     * 获取订单信息列表
     */
    public static String ORDERLIST = APIURL + "order/list.do";

    /**
     * 取消订单
     */
    public static String ORDERCANCEL = APIURL + "order/cancel.do";

    /**
     * 确认收货
     */
    public static String ORDERCONFIRM = APIURL + "order/confirm.do";

    /**
     * 提交意见反馈
     */
    public static String ADVICEPOST = SERVERURL + "api/member/advice/post.do";
}
