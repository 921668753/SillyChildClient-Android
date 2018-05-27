package com.yinglan.scc.entity.mine.mywallet.coupons;

import com.common.cklibrary.entity.BaseResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Admin on 2017/7/27.
 */

public class CouponsBean extends BaseResult<List<CouponsBean.DataBean>> {

    public class DataBean {
        /**
         * id : 63
         * cid : 25
         * type : 0         发放类型 0下单赠送 1 按用户发放 2 免费领取 3 线下发放  优惠券获取方式 4 充值 5分享 6其他
         * uid : 60
         * order_id : 0
         * get_order_id : null
         * use_time : 0
         * code :
         * send_time : 1477566074
         * store_id : 1
         * status : 0
         * deleted : 0
         * drv_id : null
         * model_type : 0     //0|1|2    0  1  2    0|1   0|2  1|2
         * home_id : null
         * name : TPshop100元券
         * use_type : 0
         * money : 100.00
         * use_start_time : 1477497600
         * use_end_time : 1536768000
         * condition : 899.00
         */

        private int id;
        private String type_money;
        private String type_name;
        private String min_goods_amount;
        private String uname;
        private String used_time;
        private String effective_start;
        private String effective_end;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType_money() {
            return type_money;
        }

        public void setType_money(String type_money) {
            this.type_money = type_money;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getMin_goods_amount() {
            return min_goods_amount;
        }

        public void setMin_goods_amount(String min_goods_amount) {
            this.min_goods_amount = min_goods_amount;
        }

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public String getUsed_time() {
            return used_time;
        }

        public void setUsed_time(String used_time) {
            this.used_time = used_time;
        }

        public String getEffective_start() {
            return effective_start;
        }

        public void setEffective_start(String effective_start) {
            this.effective_start = effective_start;
        }

        public String getEffective_end() {
            return effective_end;
        }

        public void setEffective_end(String effective_end) {
            this.effective_end = effective_end;
        }
    }
}
