package com.sillykid.app.entity.homepage.goodslist.goodsdetails.dialog;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class SpecificationsBean extends BaseResult<List<SpecificationsBean.DataBean>> {


    public class DataBean {

        private int spec_id;

        private SpecValueIdsBean specValueIds;

        public int getSpec_id() {
            return spec_id;
        }

        public void setSpec_id(int spec_id) {
            this.spec_id = spec_id;
        }

        public SpecValueIdsBean getSpecValueIds() {
            return specValueIds;
        }

        public void setSpecValueIds(SpecValueIdsBean specValueIds) {
            this.specValueIds = specValueIds;
        }

        public class SpecValueIdsBean {
            /**
             * seller_id : 1
             * head_pic : http://img.shahaizi.cn/seller_avatar.png
             * nickname : 18796020192
             * drv_code : 20171020-1
             * province : 江苏省
             * city : 苏州市
             * plat_start : 4
             * star : 5
             * line :
             */

            private int spec_value_id;
            private String spec_value;

            public int getSpec_value_id() {
                return spec_value_id;
            }

            public void setSpec_value_id(int spec_value_id) {
                this.spec_value_id = spec_value_id;
            }

            public String getSpec_value() {
                return spec_value;
            }

            public void setSpec_value(String spec_value) {
                this.spec_value = spec_value;
            }
        }
    }
}
