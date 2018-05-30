package com.yinglan.scc.entity.homepage.goodslist.goodsdetails;

import com.common.cklibrary.entity.BaseResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GoodsDetailsBean extends BaseResult<GoodsDetailsBean.DataBean> {


    public class DataBean {
        /**
         * store_id : 5
         * comment_count : 0
         * thumbnail : http://static.b2b2cv2.javamall.com.cn/attachment//store/5/goods/2017/6/14/13//50085050_thumbnail.jpg
         * goodsLvPrices : null
         * productSpecs : [{"goods_id":133,"product_id":133,"sn":"00029","enable_store":0,"price":1298,"cost":1098,"weight":599,"specs":null}]
         * specList : []
         * goods_id : 133
         * specsvIdJson : []
         * comment_percent : 100%
         * name : ML2017夏季新款时尚优雅荷叶边捆绑系带细高跟女凉鞋
         * class : class com.enation.app.shop.core.goods.model.Product
         * favorited : false
         */

        private int store_id;
        private int comment_count;
        private String thumbnail;
        private String goodsLvPrices;
        private int goods_id;
        private String specsvIdJson;
        private String comment_percent;
        private String name;
        @SerializedName("class")
        private String classX;
        private boolean favorited;
        private List<ProductSpecsBean> productSpecs;
        private List<?> specList;

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getGoodsLvPrices() {
            return goodsLvPrices;
        }

        public void setGoodsLvPrices(String goodsLvPrices) {
            this.goodsLvPrices = goodsLvPrices;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getSpecsvIdJson() {
            return specsvIdJson;
        }

        public void setSpecsvIdJson(String specsvIdJson) {
            this.specsvIdJson = specsvIdJson;
        }

        public String getComment_percent() {
            return comment_percent;
        }

        public void setComment_percent(String comment_percent) {
            this.comment_percent = comment_percent;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getClassX() {
            return classX;
        }

        public void setClassX(String classX) {
            this.classX = classX;
        }

        public boolean isFavorited() {
            return favorited;
        }

        public void setFavorited(boolean favorited) {
            this.favorited = favorited;
        }

        public List<ProductSpecsBean> getProductSpecs() {
            return productSpecs;
        }

        public void setProductSpecs(List<ProductSpecsBean> productSpecs) {
            this.productSpecs = productSpecs;
        }

        public List<?> getSpecList() {
            return specList;
        }

        public void setSpecList(List<?> specList) {
            this.specList = specList;
        }

        public class ProductSpecsBean {
            /**
             * goods_id : 133
             * product_id : 133
             * sn : 00029
             * enable_store : 0
             * price : 1298
             * cost : 1098
             * weight : 599
             * specs : null
             */

            private int goods_id;
            private int product_id;
            private String sn;
            private int enable_store;
            private String price;
            private int cost;
            private int weight;
            private String specs;

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public int getProduct_id() {
                return product_id;
            }

            public void setProduct_id(int product_id) {
                this.product_id = product_id;
            }

            public String getSn() {
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
            }

            public int getEnable_store() {
                return enable_store;
            }

            public void setEnable_store(int enable_store) {
                this.enable_store = enable_store;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public int getCost() {
                return cost;
            }

            public void setCost(int cost) {
                this.cost = cost;
            }

            public int getWeight() {
                return weight;
            }

            public void setWeight(int weight) {
                this.weight = weight;
            }

            public String getSpecs() {
                return specs;
            }

            public void setSpecs(String specs) {
                this.specs = specs;
            }
        }
    }
}
