package com.yinglan.scc.entity.homepage.goodslist;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;


public class GoodsListBean extends BaseResult<List<GoodsListBean.DataBean>> {


    public class DataBean {
        /**
         * goods_id : 162
         * name : 真他妈的好吃
         * thumbnail : http://ovwiqces1.bkt.clouddn.com/attachment//store/18/goods/2018/5/11/17//10097266_thumbnail.jpg
         * price : 20000
         * mktprice : 100000
         * view_count : 4
         * buy_count : 1
         * store : 3
         * sn : 2838
         * big : fs:/attachment//store/18/goods/2018/5/11/17//10097266_big.jpg
         * small : fs:/attachment//store/18/goods/2018/5/11/17//10097266_small.jpg
         * original : fs:/attachment//store/18/goods/2018/5/11/17//10097266.jpg
         * commentCount : null
         * commentPercent : null
         */

        private int goods_id;
        private String name;
        private String thumbnail;
        private String price;
        private String mktprice;
        private int view_count;
        private int buy_count;
        private int store;
        private String sn;
        private String big;
        private String small;
        private String original;
        private String commentCount;
        private String commentPercent;

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getMktprice() {
            return mktprice;
        }

        public void setMktprice(String mktprice) {
            this.mktprice = mktprice;
        }

        public int getView_count() {
            return view_count;
        }

        public void setView_count(int view_count) {
            this.view_count = view_count;
        }

        public int getBuy_count() {
            return buy_count;
        }

        public void setBuy_count(int buy_count) {
            this.buy_count = buy_count;
        }

        public int getStore() {
            return store;
        }

        public void setStore(int store) {
            this.store = store;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getBig() {
            return big;
        }

        public void setBig(String big) {
            this.big = big;
        }

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getOriginal() {
            return original;
        }

        public void setOriginal(String original) {
            this.original = original;
        }

        public Object getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public String getCommentPercent() {
            return commentPercent;
        }

        public void setCommentPercent(String commentPercent) {
            this.commentPercent = commentPercent;
        }
    }
}
