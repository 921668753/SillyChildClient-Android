package com.sillykid.app.entity.homepage.goodslist.goodsdetails.dialog;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class SpecificationsBean extends BaseResult<SpecificationsBean.DataBean> {


    public class DataBean {
        private List<ProductSpecsBean> productSpecs;

        public List<ProductSpecsBean> getProductSpecs() {
            return productSpecs;
        }

        public void setProductSpecs(List<ProductSpecsBean> productSpecs) {
            this.productSpecs = productSpecs;
        }

        public class ProductSpecsBean {
            /**
             * goods_id : 133
             * product_id : 391
             * sn : 00029-4
             * enable_store : 100
             * price : 100
             * specs : 黄色、XL
             * name : ML2017夏季新款时尚优雅荷叶边捆绑系带细高跟女凉鞋
             */

            private int goods_id;
            private int product_id;
            private String sn;
            private int enable_store;
            private int price;
            private String specs;
            private String name;

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

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public String getSpecs() {
                return specs;
            }

            public void setSpecs(String specs) {
                this.specs = specs;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
