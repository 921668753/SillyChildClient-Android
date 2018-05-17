package com.yinglan.scc.entity.mine.deliveryaddress;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class DeliveryAddressBean extends BaseResult<DeliveryAddressBean.DataBean> {


    public class DataBean {
        private List<AddressListBean> addressList;

        public List<AddressListBean> getAddressList() {
            return addressList;
        }

        public void setAddressList(List<AddressListBean> addressList) {
            this.addressList = addressList;
        }

        public class AddressListBean {
            /**
             * addr_id : 1
             * member_id : 27
             * name : food
             * country : null
             * province_id : 130000
             * city_id : 131000
             * region_id : 131082
             * town_id : 131082450
             * province : 河北省
             * city : 廊坊市
             * region : 三河市
             * town : 燕郊经济技术开发区
             * addr : 维多利亚D座22层2333
             * zip : null
             * tel : null
             * mobile : 17188888888
             * def_addr : 1
             * remark : null
             * isDel : 0
             * shipAddressName : 公司
             * addressToBeEdit : null
             */

            private int addr_id;
            private int member_id;
            private String name;
            private String country;
            private int province_id;
            private int city_id;
            private int region_id;
            private int town_id;
            private String province;
            private String city;
            private String region;
            private String town;
            private String addr;
            private String zip;
            private String tel;
            private String mobile;
            private int def_addr;
            private String remark;
            private int isDel;
            private String shipAddressName;
            private String addressToBeEdit;

            public int getAddr_id() {
                return addr_id;
            }

            public void setAddr_id(int addr_id) {
                this.addr_id = addr_id;
            }

            public int getMember_id() {
                return member_id;
            }

            public void setMember_id(int member_id) {
                this.member_id = member_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public int getProvince_id() {
                return province_id;
            }

            public void setProvince_id(int province_id) {
                this.province_id = province_id;
            }

            public int getCity_id() {
                return city_id;
            }

            public void setCity_id(int city_id) {
                this.city_id = city_id;
            }

            public int getRegion_id() {
                return region_id;
            }

            public void setRegion_id(int region_id) {
                this.region_id = region_id;
            }

            public int getTown_id() {
                return town_id;
            }

            public void setTown_id(int town_id) {
                this.town_id = town_id;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }

            public String getTown() {
                return town;
            }

            public void setTown(String town) {
                this.town = town;
            }

            public String getAddr() {
                return addr;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }

            public String getZip() {
                return zip;
            }

            public void setZip(String zip) {
                this.zip = zip;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public int getDef_addr() {
                return def_addr;
            }

            public void setDef_addr(int def_addr) {
                this.def_addr = def_addr;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getIsDel() {
                return isDel;
            }

            public void setIsDel(int isDel) {
                this.isDel = isDel;
            }

            public String getShipAddressName() {
                return shipAddressName;
            }

            public void setShipAddressName(String shipAddressName) {
                this.shipAddressName = shipAddressName;
            }

            public String getAddressToBeEdit() {
                return addressToBeEdit;
            }

            public void setAddressToBeEdit(String addressToBeEdit) {
                this.addressToBeEdit = addressToBeEdit;
            }
        }
    }
}
