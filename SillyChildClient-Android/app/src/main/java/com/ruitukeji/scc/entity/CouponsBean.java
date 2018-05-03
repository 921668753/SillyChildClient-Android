package com.ruitukeji.scc.entity;

import com.common.cklibrary.entity.BaseResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Admin on 2017/7/27.
 */

public class CouponsBean extends BaseResult<List<CouponsBean.ResultBean> > {

    public class ResultBean {
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
        private int cid;
        private int type;
        private int uid;
        private int order_id;
        private Object get_order_id;
        private int use_time;
        private String code;
        private int send_time;
        private int store_id;
        @SerializedName("status")
        private int statusX;
        private int deleted;
        private Object drv_id;
        private int model_type;
        private Object home_id;
        private String name;
        private int use_type;
        private String money;
        private long use_start_time;
        private long use_end_time;
        private String condition;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public Object getGet_order_id() {
            return get_order_id;
        }

        public void setGet_order_id(Object get_order_id) {
            this.get_order_id = get_order_id;
        }

        public int getUse_time() {
            return use_time;
        }

        public void setUse_time(int use_time) {
            this.use_time = use_time;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getSend_time() {
            return send_time;
        }

        public void setSend_time(int send_time) {
            this.send_time = send_time;
        }

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public int getStatusX() {
            return statusX;
        }

        public void setStatusX(int statusX) {
            this.statusX = statusX;
        }

        public int getDeleted() {
            return deleted;
        }

        public void setDeleted(int deleted) {
            this.deleted = deleted;
        }

        public Object getDrv_id() {
            return drv_id;
        }

        public void setDrv_id(Object drv_id) {
            this.drv_id = drv_id;
        }

        public int getModel_type() {
            return model_type;
        }

        public void setModel_type(int model_type) {
            this.model_type = model_type;
        }

        public Object getHome_id() {
            return home_id;
        }

        public void setHome_id(Object home_id) {
            this.home_id = home_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getUse_type() {
            return use_type;
        }

        public void setUse_type(int use_type) {
            this.use_type = use_type;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public long getUse_start_time() {
            return use_start_time;
        }

        public void setUse_start_time(long use_start_time) {
            this.use_start_time = use_start_time;
        }

        public long getUse_end_time() {
            return use_end_time;
        }

        public void setUse_end_time(long use_end_time) {
            this.use_end_time = use_end_time;
        }

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }
    }
}
