package com.sillykid.app.entity.mine.myorder.goodorder.aftersalesdetails;

import com.common.cklibrary.entity.BaseResult;

public class AfterSalesDetailsBean extends BaseResult<AfterSalesDetailsBean.DataBean> {


    public class DataBean {
        /**
         * result : [{"orderId":205,"paymoney":0,"sn":"DD152812329579-1","orderItems":[{"image":"http://static.b2b2cv2.javamall.com.cn/attachment//store/1/goods/2017/6/14/14//59186456_thumbnail.jpg","item_id":80,"snapshot_id":21,"num":1,"goods_id":157,"gainedpoint":0,"ship_num":0,"unit":"","price":2250,"product_id":157,"cat_id":164,"name":"正品女表时尚手表真皮镶钻石英表","goods_type":0,"sn":"00051","state":0,"fields":{},"order_id":205}],"itemsCount":1,"status":1},{"orderId":203,"paymoney":0,"sn":"DD152812321571-1","orderItems":[{"image":"http://static.b2b2cv2.javamall.com.cn/attachment//store/1/goods/2017/6/14/14//59186456_thumbnail.jpg","item_id":79,"snapshot_id":21,"num":1,"goods_id":157,"gainedpoint":0,"ship_num":0,"unit":"","price":2250,"product_id":157,"cat_id":164,"name":"正品女表时尚手表真皮镶钻石英表","goods_type":0,"sn":"00051","state":0,"fields":{},"order_id":203}],"itemsCount":1,"status":1}]
         * pageSize : 20
         * totalPageCount : 1
         * draw : 0
         * totalCount : 2
         * currentPageNo : 1
         */

        private int tradestatus;
        private String alltotal_pay;
        private String store_name;
        private String remark;
        private String apply_alltotal;
        private String reason;
        private String ordersn;
        private String order_create_time;
        private String goodsNames;

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

        public String getGoodsNames() {
            return goodsNames;
        }

        public void setGoodsNames(String goodsNames) {
            this.goodsNames = goodsNames;
        }
    }

}
