package com.sillykid.app.entity.mine.mywallet.accountdetails;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

/**
 * Created by Admin on 2017/7/27.
 */

public class AccountDetailsBean extends BaseResult<AccountDetailsBean.ResultBean > {


    public static class ResultBean {
        /**
         * p : 1
         * totalPages : 5
         * list : [{"changeMoney":"+0.01","id":299,"orderSn":"RC632017091618575865858","remark":"充值","timeFmt":"2017.09.16","timeStamp":1505559504,"type":1,"typeName":"充值","userBalance":"6.05"},{"changeMoney":"+0.01","id":298,"orderSn":"RC632017091616350873728","remark":"充值","timeFmt":"2017.09.16","timeStamp":1505555947,"type":1,"typeName":"充值","userBalance":"0.00"}]
         */

        private int p;
        private int totalPages;
        private List<ListBean> list;

        public int getP() {
            return p;
        }

        public void setP(int p) {
            this.p = p;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * changeMoney : +0.01
             * id : 299
             * orderSn : RC632017091618575865858
             * remark : 充值
             * timeFmt : 2017.09.16
             * timeStamp : 1505559504
             * type : 1
             * typeName : 充值
             * userBalance : 6.05
             */

            private String changeMoney;
            private int id;
            private String orderSn;
            private String remark;
            private String timeFmt;
            private int timeStamp;
            private int type;
            private String typeName;
            private String userBalance;

            public String getChangeMoney() {
                return changeMoney;
            }

            public void setChangeMoney(String changeMoney) {
                this.changeMoney = changeMoney;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getOrderSn() {
                return orderSn;
            }

            public void setOrderSn(String orderSn) {
                this.orderSn = orderSn;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getTimeFmt() {
                return timeFmt;
            }

            public void setTimeFmt(String timeFmt) {
                this.timeFmt = timeFmt;
            }

            public int getTimeStamp() {
                return timeStamp;
            }

            public void setTimeStamp(int timeStamp) {
                this.timeStamp = timeStamp;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getTypeName() {
                return typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName;
            }

            public String getUserBalance() {
                return userBalance;
            }

            public void setUserBalance(String userBalance) {
                this.userBalance = userBalance;
            }
        }
    }
}
