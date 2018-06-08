package com.sillykid.app.entity.mine.myorder.goodorder.aftersalesdetails;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class AfterSalesDetailsBean extends BaseResult<AfterSalesDetailsBean.DataBean> {


    public class DataBean {
        /**
         * tradestatus : 6
         * alltotal_pay : 100
         * store_name : 时尚女装/搭配
         * remark : 退货
         * apply_alltotal : 1346
         * reason :
         * ordersn : DD152811678692-1
         * order_create_time : 1528116786
         * goodsNames : ["Bzbz新款平底防滑沙滩鞋厚底凉拖鞋女夏时尚韩版外穿",""]
         */

        private int tradestatus;
        private String alltotal_pay;
        private String store_name;
        private String remark;
        private String apply_alltotal;
        private String reason;
        private String ordersn;
        private String order_create_time;
        private List<String> goodsNames;

        public int getTradestatus() {
            return tradestatus;
        }

        public void setTradestatus(int tradestatus) {
            this.tradestatus = tradestatus;
        }

        public String getAlltotal_pay() {
            return alltotal_pay;
        }

        public void setAlltotal_pay(String alltotal_pay) {
            this.alltotal_pay = alltotal_pay;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getApply_alltotal() {
            return apply_alltotal;
        }

        public void setApply_alltotal(String apply_alltotal) {
            this.apply_alltotal = apply_alltotal;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getOrdersn() {
            return ordersn;
        }

        public void setOrdersn(String ordersn) {
            this.ordersn = ordersn;
        }

        public String getOrder_create_time() {
            return order_create_time;
        }

        public void setOrder_create_time(String order_create_time) {
            this.order_create_time = order_create_time;
        }

        public List<String> getGoodsNames() {
            return goodsNames;
        }

        public void setGoodsNames(List<String> goodsNames) {
            this.goodsNames = goodsNames;
        }
    }
}
