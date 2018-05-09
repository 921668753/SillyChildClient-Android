package com.yinglan.scc.entity.mine.mywallet.mybankcard;

import com.common.cklibrary.entity.BaseResult;
import com.contrarywind.interfaces.IPickerViewData;


import java.util.List;

/**
 * Created by Administrator on 2018/1/10.
 */

public class BankBean extends BaseResult<List<BankBean.ResultBean>> {


    public class ResultBean implements IPickerViewData {
        /**
         * id : 1
         * bank : 中国人民银行
         */

        private int id;
        private String bank;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        @Override
        public String getPickerViewText() {
            return bank;
        }
    }
}
