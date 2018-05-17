package com.yinglan.scc.entity.mine.myshoppingcart;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class MyShoppingCartBean extends BaseResult<MyShoppingCartBean.DataBean> {


    public class DataBean {
        /**
         * total : 394
         * count : 2
         * storelist : [{"storeprice":{"goodsPrice":394,"orderPrice":394,"shippingPrice":0,"needPayMoney":394,"discountPrice":0,"weight":2100,"point":0,"discountItem":{},"actDiscount":0,"gift_id":0,"bonus_id":0,"is_free_ship":0,"act_free_ship":0,"exchange_point":null,"activity_point":null,"credit_pay":null},"store_id":17,"goodslist":[{"id":1,"product_id":407,"goods_id":401,"name":"菲诗小铺（THE FACE SHOP）金盏花舒缓补水系列","mktprice":58,"price":58,"coupPrice":58,"subtotal":348,"num":6,"limitnum":null,"image_default":"http://static.b2b2cv2.javamall.com.cn/attachment//store/17/goods/2016/5/30/10//02365691_thumbnail.jpg","point":0,"itemtype":0,"sn":"201605300001","addon":null,"specs":null,"catid":45,"others":{},"exchange":null,"unit":null,"goods_type":0,"pmtList":null,"weight":300,"activity_id":null,"is_check":1,"snapshot_id":null,"store_id":17,"store_name":"化妆品店铺","goods_transfee_charge":1},{"id":2,"product_id":408,"goods_id":402,"name":"菲诗小铺（THE FACE SHOP）大米调理保湿系列爽肤水","mktprice":46,"price":46,"coupPrice":46,"subtotal":46,"num":1,"limitnum":null,"image_default":"http://static.b2b2cv2.javamall.com.cn/attachment//store/17/goods/2016/5/30/10//20467387_thumbnail.jpg","point":0,"itemtype":0,"sn":"201605300002","addon":null,"specs":null,"catid":42,"others":{},"exchange":null,"unit":null,"goods_type":0,"pmtList":null,"weight":300,"activity_id":null,"is_check":1,"snapshot_id":null,"store_id":17,"store_name":"化妆品店铺","goods_transfee_charge":1}],"store_name":"化妆品店铺"}]
         */

        private int total;
        private int count;
        private List<StorelistBean> storelist;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<StorelistBean> getStorelist() {
            return storelist;
        }

        public void setStorelist(List<StorelistBean> storelist) {
            this.storelist = storelist;
        }

        public class StorelistBean {
            /**
             * storeprice : {"goodsPrice":394,"orderPrice":394,"shippingPrice":0,"needPayMoney":394,"discountPrice":0,"weight":2100,"point":0,"discountItem":{},"actDiscount":0,"gift_id":0,"bonus_id":0,"is_free_ship":0,"act_free_ship":0,"exchange_point":null,"activity_point":null,"credit_pay":null}
             * store_id : 17
             * goodslist : [{"id":1,"product_id":407,"goods_id":401,"name":"菲诗小铺（THE FACE SHOP）金盏花舒缓补水系列","mktprice":58,"price":58,"coupPrice":58,"subtotal":348,"num":6,"limitnum":null,"image_default":"http://static.b2b2cv2.javamall.com.cn/attachment//store/17/goods/2016/5/30/10//02365691_thumbnail.jpg","point":0,"itemtype":0,"sn":"201605300001","addon":null,"specs":null,"catid":45,"others":{},"exchange":null,"unit":null,"goods_type":0,"pmtList":null,"weight":300,"activity_id":null,"is_check":1,"snapshot_id":null,"store_id":17,"store_name":"化妆品店铺","goods_transfee_charge":1},{"id":2,"product_id":408,"goods_id":402,"name":"菲诗小铺（THE FACE SHOP）大米调理保湿系列爽肤水","mktprice":46,"price":46,"coupPrice":46,"subtotal":46,"num":1,"limitnum":null,"image_default":"http://static.b2b2cv2.javamall.com.cn/attachment//store/17/goods/2016/5/30/10//20467387_thumbnail.jpg","point":0,"itemtype":0,"sn":"201605300002","addon":null,"specs":null,"catid":42,"others":{},"exchange":null,"unit":null,"goods_type":0,"pmtList":null,"weight":300,"activity_id":null,"is_check":1,"snapshot_id":null,"store_id":17,"store_name":"化妆品店铺","goods_transfee_charge":1}]
             * store_name : 化妆品店铺
             */

            private StorepriceBean storeprice;
            private int store_id;
            private String store_name;
            private List<GoodslistBean> goodslist;

            public StorepriceBean getStoreprice() {
                return storeprice;
            }

            public void setStoreprice(StorepriceBean storeprice) {
                this.storeprice = storeprice;
            }

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public List<GoodslistBean> getGoodslist() {
                return goodslist;
            }

            public void setGoodslist(List<GoodslistBean> goodslist) {
                this.goodslist = goodslist;
            }

            public class StorepriceBean {
                /**
                 * goodsPrice : 394
                 * orderPrice : 394
                 * shippingPrice : 0
                 * needPayMoney : 394
                 * discountPrice : 0
                 * weight : 2100
                 * point : 0
                 * discountItem : {}
                 * actDiscount : 0
                 * gift_id : 0
                 * bonus_id : 0
                 * is_free_ship : 0
                 * act_free_ship : 0
                 * exchange_point : null
                 * activity_point : null
                 * credit_pay : null
                 */

                private int goodsPrice;
                private int orderPrice;
                private int shippingPrice;
                private int needPayMoney;
                private int discountPrice;
                private int weight;
                private int point;
                private DiscountItemBean discountItem;
                private int actDiscount;
                private int gift_id;
                private int bonus_id;
                private int is_free_ship;
                private int act_free_ship;
                private Object exchange_point;
                private Object activity_point;
                private Object credit_pay;

                public int getGoodsPrice() {
                    return goodsPrice;
                }

                public void setGoodsPrice(int goodsPrice) {
                    this.goodsPrice = goodsPrice;
                }

                public int getOrderPrice() {
                    return orderPrice;
                }

                public void setOrderPrice(int orderPrice) {
                    this.orderPrice = orderPrice;
                }

                public int getShippingPrice() {
                    return shippingPrice;
                }

                public void setShippingPrice(int shippingPrice) {
                    this.shippingPrice = shippingPrice;
                }

                public int getNeedPayMoney() {
                    return needPayMoney;
                }

                public void setNeedPayMoney(int needPayMoney) {
                    this.needPayMoney = needPayMoney;
                }

                public int getDiscountPrice() {
                    return discountPrice;
                }

                public void setDiscountPrice(int discountPrice) {
                    this.discountPrice = discountPrice;
                }

                public int getWeight() {
                    return weight;
                }

                public void setWeight(int weight) {
                    this.weight = weight;
                }

                public int getPoint() {
                    return point;
                }

                public void setPoint(int point) {
                    this.point = point;
                }

                public DiscountItemBean getDiscountItem() {
                    return discountItem;
                }

                public void setDiscountItem(DiscountItemBean discountItem) {
                    this.discountItem = discountItem;
                }

                public int getActDiscount() {
                    return actDiscount;
                }

                public void setActDiscount(int actDiscount) {
                    this.actDiscount = actDiscount;
                }

                public int getGift_id() {
                    return gift_id;
                }

                public void setGift_id(int gift_id) {
                    this.gift_id = gift_id;
                }

                public int getBonus_id() {
                    return bonus_id;
                }

                public void setBonus_id(int bonus_id) {
                    this.bonus_id = bonus_id;
                }

                public int getIs_free_ship() {
                    return is_free_ship;
                }

                public void setIs_free_ship(int is_free_ship) {
                    this.is_free_ship = is_free_ship;
                }

                public int getAct_free_ship() {
                    return act_free_ship;
                }

                public void setAct_free_ship(int act_free_ship) {
                    this.act_free_ship = act_free_ship;
                }

                public Object getExchange_point() {
                    return exchange_point;
                }

                public void setExchange_point(Object exchange_point) {
                    this.exchange_point = exchange_point;
                }

                public Object getActivity_point() {
                    return activity_point;
                }

                public void setActivity_point(Object activity_point) {
                    this.activity_point = activity_point;
                }

                public Object getCredit_pay() {
                    return credit_pay;
                }

                public void setCredit_pay(Object credit_pay) {
                    this.credit_pay = credit_pay;
                }

                public class DiscountItemBean {
                }
            }

            public class GoodslistBean {
                /**
                 * id : 1
                 * product_id : 407
                 * goods_id : 401
                 * name : 菲诗小铺（THE FACE SHOP）金盏花舒缓补水系列
                 * mktprice : 58
                 * price : 58
                 * coupPrice : 58
                 * subtotal : 348
                 * num : 6
                 * limitnum : null
                 * image_default : http://static.b2b2cv2.javamall.com.cn/attachment//store/17/goods/2016/5/30/10//02365691_thumbnail.jpg
                 * point : 0
                 * itemtype : 0
                 * sn : 201605300001
                 * addon : null
                 * specs : null
                 * catid : 45
                 * others : {}
                 * exchange : null
                 * unit : null
                 * goods_type : 0
                 * pmtList : null
                 * weight : 300
                 * activity_id : null
                 * is_check : 1
                 * snapshot_id : null
                 * store_id : 17
                 * store_name : 化妆品店铺
                 * goods_transfee_charge : 1
                 */

                private int id;
                private int product_id;
                private int goods_id;
                private String name;
                private int mktprice;
                private String price;
                private int coupPrice;
                private int subtotal;
                private int num;
                private Object limitnum;
                private String image_default;
                private int point;
                private int itemtype;
                private String sn;
                private Object addon;
                private Object specs;
                private int catid;
                private OthersBean others;
                private Object exchange;
                private Object unit;
                private int goods_type;
                private Object pmtList;
                private int weight;
                private Object activity_id;
                private int is_check;
                private Object snapshot_id;
                private int store_id;
                private String store_name;
                private int goods_transfee_charge;
                private int isSelected;
                private int isEdit;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getProduct_id() {
                    return product_id;
                }

                public void setProduct_id(int product_id) {
                    this.product_id = product_id;
                }

                public int getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(int goods_id) {
                    this.goods_id = goods_id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getMktprice() {
                    return mktprice;
                }

                public void setMktprice(int mktprice) {
                    this.mktprice = mktprice;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public int getCoupPrice() {
                    return coupPrice;
                }

                public void setCoupPrice(int coupPrice) {
                    this.coupPrice = coupPrice;
                }

                public int getSubtotal() {
                    return subtotal;
                }

                public void setSubtotal(int subtotal) {
                    this.subtotal = subtotal;
                }

                public int getNum() {
                    return num;
                }

                public void setNum(int num) {
                    this.num = num;
                }

                public Object getLimitnum() {
                    return limitnum;
                }

                public void setLimitnum(Object limitnum) {
                    this.limitnum = limitnum;
                }

                public String getImage_default() {
                    return image_default;
                }

                public void setImage_default(String image_default) {
                    this.image_default = image_default;
                }

                public int getPoint() {
                    return point;
                }

                public void setPoint(int point) {
                    this.point = point;
                }

                public int getItemtype() {
                    return itemtype;
                }

                public void setItemtype(int itemtype) {
                    this.itemtype = itemtype;
                }

                public String getSn() {
                    return sn;
                }

                public void setSn(String sn) {
                    this.sn = sn;
                }

                public Object getAddon() {
                    return addon;
                }

                public void setAddon(Object addon) {
                    this.addon = addon;
                }

                public Object getSpecs() {
                    return specs;
                }

                public void setSpecs(Object specs) {
                    this.specs = specs;
                }

                public int getCatid() {
                    return catid;
                }

                public void setCatid(int catid) {
                    this.catid = catid;
                }

                public OthersBean getOthers() {
                    return others;
                }

                public void setOthers(OthersBean others) {
                    this.others = others;
                }

                public Object getExchange() {
                    return exchange;
                }

                public void setExchange(Object exchange) {
                    this.exchange = exchange;
                }

                public Object getUnit() {
                    return unit;
                }

                public void setUnit(Object unit) {
                    this.unit = unit;
                }

                public int getGoods_type() {
                    return goods_type;
                }

                public void setGoods_type(int goods_type) {
                    this.goods_type = goods_type;
                }

                public Object getPmtList() {
                    return pmtList;
                }

                public void setPmtList(Object pmtList) {
                    this.pmtList = pmtList;
                }

                public int getWeight() {
                    return weight;
                }

                public void setWeight(int weight) {
                    this.weight = weight;
                }

                public Object getActivity_id() {
                    return activity_id;
                }

                public void setActivity_id(Object activity_id) {
                    this.activity_id = activity_id;
                }

                public int getIs_check() {
                    return is_check;
                }

                public void setIs_check(int is_check) {
                    this.is_check = is_check;
                }

                public Object getSnapshot_id() {
                    return snapshot_id;
                }

                public void setSnapshot_id(Object snapshot_id) {
                    this.snapshot_id = snapshot_id;
                }

                public int getStore_id() {
                    return store_id;
                }

                public void setStore_id(int store_id) {
                    this.store_id = store_id;
                }

                public String getStore_name() {
                    return store_name;
                }

                public void setStore_name(String store_name) {
                    this.store_name = store_name;
                }

                public int getGoods_transfee_charge() {
                    return goods_transfee_charge;
                }

                public void setGoods_transfee_charge(int goods_transfee_charge) {
                    this.goods_transfee_charge = goods_transfee_charge;
                }

                public int getIsSelected() {
                    return isSelected;
                }

                public void setIsSelected(int isSelected) {
                    this.isSelected = isSelected;
                }

                public int getIsEdit() {
                    return isEdit;
                }

                public void setIsEdit(int isEdit) {
                    this.isEdit = isEdit;
                }

                public class OthersBean {
                }
            }
        }
    }
}
