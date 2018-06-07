package com.sillykid.app.entity.mine.myorder.goodorder.aftersalesdetails;

import com.common.cklibrary.entity.BaseResult;

public class ServiceDetailsBean extends BaseResult<ServiceDetailsBean.DataBean> {

    public class DataBean {
        /**
         * result : [{"orderId":205,"paymoney":0,"sn":"DD152812329579-1","orderItems":[{"image":"http://static.b2b2cv2.javamall.com.cn/attachment//store/1/goods/2017/6/14/14//59186456_thumbnail.jpg","item_id":80,"snapshot_id":21,"num":1,"goods_id":157,"gainedpoint":0,"ship_num":0,"unit":"","price":2250,"product_id":157,"cat_id":164,"name":"正品女表时尚手表真皮镶钻石英表","goods_type":0,"sn":"00051","state":0,"fields":{},"order_id":205}],"itemsCount":1,"status":1},{"orderId":203,"paymoney":0,"sn":"DD152812321571-1","orderItems":[{"image":"http://static.b2b2cv2.javamall.com.cn/attachment//store/1/goods/2017/6/14/14//59186456_thumbnail.jpg","item_id":79,"snapshot_id":21,"num":1,"goods_id":157,"gainedpoint":0,"ship_num":0,"unit":"","price":2250,"product_id":157,"cat_id":164,"name":"正品女表时尚手表真皮镶钻石英表","goods_type":0,"sn":"00051","state":0,"fields":{},"order_id":203}],"itemsCount":1,"status":1}]
         * pageSize : 20
         * totalPageCount : 1
         * draw : 0
         * totalCount : 2
         * currentPageNo : 1
         */

        private MemberBean member;
        private StoreBean store;
        private SHZBean SHZ;

        public MemberBean getMember() {
            return member;
        }

        public void setMember(MemberBean member) {
            this.member = member;
        }

        public StoreBean getStore() {
            return store;
        }

        public void setStore(StoreBean store) {
            this.store = store;
        }

        public SHZBean getSHZ() {
            return SHZ;
        }

        public void setSHZ(SHZBean SHZ) {
            this.SHZ = SHZ;
        }

        public class MemberBean {
            /**
             * code : 11
             * cfg_value : 不喜欢
             */

            private String member_id;
            private String remark;
            private String reason;
            private String regtime;
            private String apply_alltotal;
            private String face;

            public String getMember_id() {
                return member_id;
            }

            public void setMember_id(String member_id) {
                this.member_id = member_id;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

            public String getRegtime() {
                return regtime;
            }

            public void setRegtime(String regtime) {
                this.regtime = regtime;
            }

            public String getApply_alltotal() {
                return apply_alltotal;
            }

            public void setApply_alltotal(String apply_alltotal) {
                this.apply_alltotal = apply_alltotal;
            }

            public String getFace() {
                return face;
            }

            public void setFace(String face) {
                this.face = face;
            }
        }

        public class StoreBean {
            /**
             * code : 11
             * cfg_value : 不喜欢
             */

            private String create_status;
            private String create_time;
            private String create_remark;
            private String store_logo;

            public String getCreate_status() {
                return create_status;
            }

            public void setCreate_status(String create_status) {
                this.create_status = create_status;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getCreate_remark() {
                return create_remark;
            }

            public void setCreate_remark(String create_remark) {
                this.create_remark = create_remark;
            }

            public String getStore_logo() {
                return store_logo;
            }

            public void setStore_logo(String store_logo) {
                this.store_logo = store_logo;
            }
        }

        public class SHZBean {
            /**
             * code : 11
             * cfg_value : 不喜欢
             */

            private int refund_status;
            private String refund_time;
            private String refund_remark;
            private String store_logo;

            public int getRefund_status() {
                return refund_status;
            }

            public void setRefund_status(int refund_status) {
                this.refund_status = refund_status;
            }

            public String getRefund_time() {
                return refund_time;
            }

            public void setRefund_time(String refund_time) {
                this.refund_time = refund_time;
            }

            public String getRefund_remark() {
                return refund_remark;
            }

            public void setRefund_remark(String refund_remark) {
                this.refund_remark = refund_remark;
            }

            public String getStore_logo() {
                return store_logo;
            }

            public void setStore_logo(String store_logo) {
                this.store_logo = store_logo;
            }
        }

    }


}
