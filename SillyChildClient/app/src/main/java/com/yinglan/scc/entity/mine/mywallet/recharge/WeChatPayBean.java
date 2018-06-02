package com.yinglan.scc.entity.mine.mywallet.recharge;

import com.common.cklibrary.entity.BaseResult;

public class WeChatPayBean extends BaseResult<WeChatPayBean.DataBean> {

    public class DataBean {
        /**
         * nonce_str : 97059806033918046940037760044355
         * xml : <?xml version="1.0" encoding="UTF-8"?>
         * <p>
         * <xml>
         * <appid><![CDATA[wx54a7657de7b8a226]]></appid>
         * <body><![CDATA[订单编号:CZ15279095450970;订单金额:100.0;支付方式:2]]></body>
         * <mch_id><![CDATA[1353515902]]></mch_id>
         * <nonce_str><![CDATA[8877654914]]></nonce_str>
         * <notify_url><![CDATA[http://api.shahaizi.keiousoft.com/api/shop/CZ_wechatMobilePlugin_payment-callback/execute.do]]></notify_url>
         * <out_trade_no><![CDATA[CZ15279095450970]]></out_trade_no>
         * <sign><![CDATA[EFD318DEAFE8C3DC82F366F807607FE3]]></sign>
         * <spbill_create_ip><![CDATA[127.0.0.1]]></spbill_create_ip>
         * <total_fee>10000</total_fee>
         * <trade_type><![CDATA[APP]]></trade_type>
         * </xml>
         * <p>
         * appid : wx54a7657de7b8a226
         * result_code : SUCCESS
         * mch_id : 1353515902
         * key : 0d0fbb157a00d46f566f7275b59f3313
         * prepay_id : wx021119061078234e8994a0f23617275024
         */

        private String nonce_str;
        private String xml;
        private String appid;
        private String result_code;
        private String mch_id;
        private String key;
        private String prepay_id;

        public String getNonce_str() {
            return nonce_str;
        }

        public void setNonce_str(String nonce_str) {
            this.nonce_str = nonce_str;
        }

        public String getXml() {
            return xml;
        }

        public void setXml(String xml) {
            this.xml = xml;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getResult_code() {
            return result_code;
        }

        public void setResult_code(String result_code) {
            this.result_code = result_code;
        }

        public String getMch_id() {
            return mch_id;
        }

        public void setMch_id(String mch_id) {
            this.mch_id = mch_id;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getPrepay_id() {
            return prepay_id;
        }

        public void setPrepay_id(String prepay_id) {
            this.prepay_id = prepay_id;
        }
    }
}
