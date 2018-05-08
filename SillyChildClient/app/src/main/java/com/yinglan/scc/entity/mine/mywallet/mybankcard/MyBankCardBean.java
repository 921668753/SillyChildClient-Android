package com.yinglan.scc.entity.mine.mywallet.mybankcard;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

/**
 * Created by Administrator on 2018/1/10.
 */

public class MyBankCardBean extends BaseResult<List<MyBankCardBean.ResultBean>> {


    public class ResultBean {
        /**
         * id : 6
         * card_holder_man : 持卡人
         * bank_card : 0144
         * bank : 中国农业银行
         */

        private int id;
        private String card_holder_man;
        private String bank_card;
        private String bank;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCard_holder_man() {
            return card_holder_man;
        }

        public void setCard_holder_man(String card_holder_man) {
            this.card_holder_man = card_holder_man;
        }

        public String getBank_card() {
            return bank_card;
        }

        public void setBank_card(String bank_card) {
            this.bank_card = bank_card;
        }

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }
    }
}
