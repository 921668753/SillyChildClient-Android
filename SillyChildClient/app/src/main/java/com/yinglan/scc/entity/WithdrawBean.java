package com.yinglan.scc.entity;

import com.common.cklibrary.entity.BaseResult;

/**
 * Created by Admin on 2017/7/27.
 */

public class WithdrawBean extends BaseResult<WithdrawBean.ResultBean > {


    public static class ResultBean {
        /**
         * amount : 1.00
         * balance : 5.00
         */

        private String amount;
        private String balance;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }
    }
}
