package com.yinglan.scc.entity;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

/**
 * Created by Admin on 2017/10/16.
 */

public class HxUserListBean extends BaseResult<List<HxUserListBean.ResultBean>> {


    /**
     * status : 1
     * msg : SUCCESS
     * result : [{"sellerId":1,"nickname":"横着走的螃蟹","sellerName":"","hxName":"eaa13205a322b7f11aa2d345b09cf9e1","avatar":"http://img.shahaizi.cn/3ce28e0bb34ab33ff27eece52706adf8.jpeg","countryId":7,"cityId":11348,"platStart":4,"countryName":"中国","cityName":""},{"sellerId":3,"nickname":"智慧与美貌并存白","sellerName":"","hxName":"6d7b336552de693cba9eabbfe490fe0b","avatar":"http://img.shahaizi.cn/0e4f2e64d6500fc394956dc2d54841e3.jpeg","countryId":7,"cityId":6643,"platStart":4,"countryName":"中国","cityName":""},{"sellerId":7,"nickname":"小明","sellerName":"","hxName":"d64baf9178a7f95ac20da7ebb1451ce1","avatar":"http://img.shahaizi.cn/seller_avatar.png","countryId":null,"cityId":0,"platStart":4,"countryName":"","cityName":""}]
     */

    public class ResultBean {
        /**
         * sellerId : 1
         * nickname : 横着走的螃蟹
         * sellerName :
         * hxName : eaa13205a322b7f11aa2d345b09cf9e1
         * avatar : http://img.shahaizi.cn/3ce28e0bb34ab33ff27eece52706adf8.jpeg
         * countryId : 7
         * cityId : 11348
         * platStart : 4
         * countryName : 中国
         * cityName :
         */

        private int sellerId;
        private String nickname;
        private String sellerName;
        private String hxName;
        private String avatar;
        private int countryId;
        private int cityId;
        private int platStart;
        private String countryName;
        private String cityName;

        public int getSellerId() {
            return sellerId;
        }

        public void setSellerId(int sellerId) {
            this.sellerId = sellerId;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getSellerName() {
            return sellerName;
        }

        public void setSellerName(String sellerName) {
            this.sellerName = sellerName;
        }

        public String getHxName() {
            return hxName;
        }

        public void setHxName(String hxName) {
            this.hxName = hxName;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getCountryId() {
            return countryId;
        }

        public void setCountryId(int countryId) {
            this.countryId = countryId;
        }

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public int getPlatStart() {
            return platStart;
        }

        public void setPlatStart(int platStart) {
            this.platStart = platStart;
        }

        public String getCountryName() {
            return countryName;
        }

        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }
    }
}
