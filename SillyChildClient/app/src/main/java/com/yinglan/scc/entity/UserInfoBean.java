package com.yinglan.scc.entity;

import com.common.cklibrary.entity.BaseResult;

import java.io.Serializable;

/**
 * 用户信息
 * Created by Administrator on 2017/9/7.
 */

public class UserInfoBean extends BaseResult<UserInfoBean.ResultBean> implements Serializable {

    public class ResultBean implements Serializable{
    /**
     * user_id: 146,
     *"email": "",
     *"password": "90600d68b0f56d90c4c34284d8dfd138",
     *"sex": 0,
     *"birthday": 0,
     * "user_money": "0.00",
     * user_money_fmt;//用户余额，带符号
     * "frozen_money": "0.00",
     * "distribut_money": "0.00",
     * "pay_points": "0.0000",
     *"address_id": 0,
     *"reg_time": 1504596640,
     *"last_login": 1504602255,
     *"last_ip": "",
     *"qq": "",
     * "mobile": "18451847701",
     * "mobile_validated": 1,
     * "oauth": "",
     *"openid": null,
     *"unionid": null,
     *"head_pic": null,
     *"province": 0,
     * "city": 0,
     * "district": 0,
     * "email_validated": 0,
     *"nickname": "18451847701",
     *"level": 1,
     *"discount": "1.00",
     *"total_amount": "0.00",
     *"is_lock": 0,
     *"is_distribut": 1,
     *"first_leader": 0,
     *"second_leader": 0,
     *"third_leader": 0,
     *"fourth_leader": null,
     *"fifth_leader": null,
     *"sixth_leader": null,
     *"seventh_leader": null,
     *"token": "a279c833cebe5fb963ccba311e27c394",
     *"address": null,
     *"pay_passwd": null,
     *"pre_pay_points": "0.0000",
     *"optional": "0.0000",
     *"vipid": 0,
     *"paypoint": "0.00",
     *"coupon_count": 0,
     *"collect_count": 0,
     *"focus_count": 0,
     *"visit_count": 0,
     *"return_count": 0,
     * "waitPay": 0,
     * "waitSend": 0,
     * "waitReceive": 0,
     *"order_count": 0,
     *"message_count": 0,
     *"comment_count": 0,
     * "uncomment_count": 0,
     *"serve_comment_count": 0,
     * "cart_goods_num": 0
     * isAttention : 0:未关注;1:已关注
     *
     */
        private String apply_code;//邀请码
        private int user_id;//用户id
        private String email;//用户邮箱
        private String password;//用户密码
        private int sex;//用户性别 0保密 1男  2女
        private long birthday;//用户生日
        private String user_money;//用户余额
        private String user_money_fmt;//用户余额，带符号
        private String frozen_money;//冻结金额
        private String distribut_money;//累积分佣金额
        private String pay_points;//消费积分（忽略）
        private int address_id;//（忽略）
        private long reg_time; //注册时间
        private long last_login;//最后登录时间
        private String last_ip;
        private String qq;//qq
        private String countroy_code;//手机区号
        private String mobile;//手机
        private int mobile_validated;//手机是否验证过了  1验证过了
        private String oauth;//第三方来源 wx weibo alipay
        private String openid;//第三方唯一标示
        private String unionid;
        private String head_pic;//头像
        private String country;//国家
        private String province;//省
        private String city;//市
        private String district;//区
        private int email_validated;//邮箱是否验证过了
        private String nickname;//用户名
        private int level;//级别
        private double discount;//会员折扣，默认1不享受
        private double total_amount;//消费累计额度
        private int is_lock;//是否被锁定冻结（忽略）
        private int is_distribut;
        private int first_leader;
        private int second_leader;
        private int third_leader;
        private int fourth_leader;
        private int fifth_leader;
        private int sixth_leader;
        private int seventh_leader;
        private String token;
        private String address;
        private String pay_passwd;
        private String pre_pay_points;
        private String optional;
        private int vipid;
        private double paypoint;
        private int coupon_count;//优惠券数量
        private int collect_count;//收藏数量
        private int focus_count;
        private int visit_count;
        private int return_count;
        private int waitPay;
        private int waitSend;
        private int waitReceive;
        private int order_count;
        private int message_count;
        private int comment_count;
        private int uncomment_count;
        private int serve_comment_count;
        private int cart_goods_num;
        private String personalized_signature;//个性签名
        private String shz_code;//傻孩子号
        private String shz_update;//傻孩子号更新次数
        private String fans_num;//粉丝数
        private String attention_num;//关注
        private String good_num;//被赞数
        private String collection_num;//被收藏数
        private String hx_user_name;//环信名字
        private int isAttention;//环信名字

        public String getApply_code() {
            return apply_code;
        }

        public void setApply_code(String apply_code) {
            this.apply_code = apply_code;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public long getBirthday() {
            return birthday;
        }

        public void setBirthday(long birthday) {
            this.birthday = birthday;
        }

        public String getUser_money() {
            return user_money;
        }

        public void setUser_money(String user_money) {
            this.user_money = user_money;
        }

        public String getUser_money_fmt() {
            return user_money_fmt;
        }

        public void setUser_money_fmt(String user_money_fmt) {
            this.user_money_fmt = user_money_fmt;
        }

        public String getFrozen_money() {
            return frozen_money;
        }

        public void setFrozen_money(String frozen_money) {
            this.frozen_money = frozen_money;
        }

        public String getDistribut_money() {
            return distribut_money;
        }

        public void setDistribut_money(String distribut_money) {
            this.distribut_money = distribut_money;
        }

        public String getPay_points() {
            return pay_points;
        }

        public void setPay_points(String pay_points) {
            this.pay_points = pay_points;
        }

        public int getAddress_id() {
            return address_id;
        }

        public void setAddress_id(int address_id) {
            this.address_id = address_id;
        }

        public long getReg_time() {
            return reg_time;
        }

        public void setReg_time(long reg_time) {
            this.reg_time = reg_time;
        }

        public long getLast_login() {
            return last_login;
        }

        public void setLast_login(long last_login) {
            this.last_login = last_login;
        }

        public String getLast_ip() {
            return last_ip;
        }

        public void setLast_ip(String last_ip) {
            this.last_ip = last_ip;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getCountroy_code() {
            return countroy_code;
        }

        public void setCountroy_code(String countroy_code) {
            this.countroy_code = countroy_code;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getMobile_validated() {
            return mobile_validated;
        }

        public void setMobile_validated(int mobile_validated) {
            this.mobile_validated = mobile_validated;
        }

        public String getOauth() {
            return oauth;
        }

        public void setOauth(String oauth) {
            this.oauth = oauth;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getUnionid() {
            return unionid;
        }

        public void setUnionid(String unionid) {
            this.unionid = unionid;
        }

        public String getHead_pic() {
            return head_pic;
        }

        public void setHead_pic(String head_pic) {
            this.head_pic = head_pic;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public int getEmail_validated() {
            return email_validated;
        }

        public void setEmail_validated(int email_validated) {
            this.email_validated = email_validated;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public double getDiscount() {
            return discount;
        }

        public void setDiscount(double discount) {
            this.discount = discount;
        }

        public double getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(double total_amount) {
            this.total_amount = total_amount;
        }

        public int getIs_lock() {
            return is_lock;
        }

        public void setIs_lock(int is_lock) {
            this.is_lock = is_lock;
        }

        public int getIs_distribut() {
            return is_distribut;
        }

        public void setIs_distribut(int is_distribut) {
            this.is_distribut = is_distribut;
        }

        public int getFirst_leader() {
            return first_leader;
        }

        public void setFirst_leader(int first_leader) {
            this.first_leader = first_leader;
        }

        public int getSecond_leader() {
            return second_leader;
        }

        public void setSecond_leader(int second_leader) {
            this.second_leader = second_leader;
        }

        public int getThird_leader() {
            return third_leader;
        }

        public void setThird_leader(int third_leader) {
            this.third_leader = third_leader;
        }

        public int getFourth_leader() {
            return fourth_leader;
        }

        public void setFourth_leader(int fourth_leader) {
            this.fourth_leader = fourth_leader;
        }

        public int getFifth_leader() {
            return fifth_leader;
        }

        public void setFifth_leader(int fifth_leader) {
            this.fifth_leader = fifth_leader;
        }

        public int getSixth_leader() {
            return sixth_leader;
        }

        public void setSixth_leader(int sixth_leader) {
            this.sixth_leader = sixth_leader;
        }

        public int getSeventh_leader() {
            return seventh_leader;
        }

        public void setSeventh_leader(int seventh_leader) {
            this.seventh_leader = seventh_leader;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPay_passwd() {
            return pay_passwd;
        }

        public void setPay_passwd(String pay_passwd) {
            this.pay_passwd = pay_passwd;
        }

        public String getPre_pay_points() {
            return pre_pay_points;
        }

        public void setPre_pay_points(String pre_pay_points) {
            this.pre_pay_points = pre_pay_points;
        }

        public String getOptional() {
            return optional;
        }

        public void setOptional(String optional) {
            this.optional = optional;
        }

        public int getVipid() {
            return vipid;
        }

        public void setVipid(int vipid) {
            this.vipid = vipid;
        }

        public double getPaypoint() {
            return paypoint;
        }

        public void setPaypoint(double paypoint) {
            this.paypoint = paypoint;
        }

        public int getCoupon_count() {
            return coupon_count;
        }

        public void setCoupon_count(int coupon_count) {
            this.coupon_count = coupon_count;
        }

        public int getCollect_count() {
            return collect_count;
        }

        public void setCollect_count(int collect_count) {
            this.collect_count = collect_count;
        }

        public int getFocus_count() {
            return focus_count;
        }

        public void setFocus_count(int focus_count) {
            this.focus_count = focus_count;
        }

        public int getVisit_count() {
            return visit_count;
        }

        public void setVisit_count(int visit_count) {
            this.visit_count = visit_count;
        }

        public int getReturn_count() {
            return return_count;
        }

        public void setReturn_count(int return_count) {
            this.return_count = return_count;
        }

        public int getWaitPay() {
            return waitPay;
        }

        public void setWaitPay(int waitPay) {
            this.waitPay = waitPay;
        }

        public int getWaitSend() {
            return waitSend;
        }

        public void setWaitSend(int waitSend) {
            this.waitSend = waitSend;
        }

        public int getWaitReceive() {
            return waitReceive;
        }

        public void setWaitReceive(int waitReceive) {
            this.waitReceive = waitReceive;
        }

        public int getOrder_count() {
            return order_count;
        }

        public void setOrder_count(int order_count) {
            this.order_count = order_count;
        }

        public int getMessage_count() {
            return message_count;
        }

        public void setMessage_count(int message_count) {
            this.message_count = message_count;
        }

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public int getUncomment_count() {
            return uncomment_count;
        }

        public void setUncomment_count(int uncomment_count) {
            this.uncomment_count = uncomment_count;
        }

        public int getServe_comment_count() {
            return serve_comment_count;
        }

        public void setServe_comment_count(int serve_comment_count) {
            this.serve_comment_count = serve_comment_count;
        }

        public int getCart_goods_num() {
            return cart_goods_num;
        }

        public void setCart_goods_num(int cart_goods_num) {
            this.cart_goods_num = cart_goods_num;
        }

        public String getPersonalized_signature() {
            return personalized_signature;
        }

        public void setPersonalized_signature(String personalized_signature) {
            this.personalized_signature = personalized_signature;
        }

        public String getShz_code() {
            return shz_code;
        }

        public void setShz_code(String shz_code) {
            this.shz_code = shz_code;
        }

        public String getShz_update() {
            return shz_update;
        }

        public void setShz_update(String shz_update) {
            this.shz_update = shz_update;
        }

        public String getFans_num() {
            return fans_num;
        }

        public void setFans_num(String fans_num) {
            this.fans_num = fans_num;
        }

        public String getAttention_num() {
            return attention_num;
        }

        public void setAttention_num(String attention_num) {
            this.attention_num = attention_num;
        }

        public String getGood_num() {
            return good_num;
        }

        public void setGood_num(String good_num) {
            this.good_num = good_num;
        }

        public String getCollection_num() {
            return collection_num;
        }

        public void setCollection_num(String collection_num) {
            this.collection_num = collection_num;
        }

        public String getHx_user_name() {
            return hx_user_name;
        }

        public void setHx_user_name(String hx_user_name) {
            this.hx_user_name = hx_user_name;
        }

        public int getIsAttention() {
            return isAttention;
        }

        public void setIsAttention(int isAttention) {
            this.isAttention = isAttention;
        }
    }

}
