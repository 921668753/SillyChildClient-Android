package com.yinglan.scc.entity.main;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

/**
 * Created by Admin on 2017/9/29.
 */

public class ActivitiesBean extends BaseResult<ActivitiesBean.ResultBean> {


    public static class ResultBean {
        /**
         * p : 1
         * pageSize : 20
         * totalRows : 1
         * totalPages : 1
         * list : [{"id":1000,"publishTime":1506679144,"price":"0.01","title":"包车标题（手动添加）","imgs":["http://gimg1.bitautoimg.com/ResourceFiles/0/3/406/20170712111916756.jpg","http://www.sinaimg.cn/qc/photo_auto/photopng/08/02/1470990802.png"],"publishTimeFmt":"2017.09.29","priceFmt":"¥0.01"}]
         */
        private List<AdBean> ad;
        private int p;
        private int pageSize;
        private int totalRows;
        private int totalPages;

        public List<AdBean> getAd() {
            return ad;
        }

        public void setAd(List<AdBean> ad) {
            this.ad = ad;
        }

        private List<ListBean> list;

        public int getP() {
            return p;
        }

        public void setP(int p) {
            this.p = p;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalRows() {
            return totalRows;
        }

        public void setTotalRows(int totalRows) {
            this.totalRows = totalRows;
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
             * id : 1000
             * publishTime : 1506679144
             * price : 0.01
             * title : 包车标题（手动添加）
             * imgs : ["http://gimg1.bitautoimg.com/ResourceFiles/0/3/406/20170712111916756.jpg","http://www.sinaimg.cn/qc/photo_auto/photopng/08/02/1470990802.png"]
             * publishTimeFmt : 2017.09.29
             * priceFmt : ¥0.01
             */

            private int id;
            private int publishTime;
            private int type;
            private String price;
            private String title;
            private String publishTimeFmt;
            private String priceFmt;
            private String imgs;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(int publishTime) {
                this.publishTime = publishTime;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPublishTimeFmt() {
                return publishTimeFmt;
            }

            public void setPublishTimeFmt(String publishTimeFmt) {
                this.publishTimeFmt = publishTimeFmt;
            }

            public String getPriceFmt() {
                return priceFmt;
            }

            public void setPriceFmt(String priceFmt) {
                this.priceFmt = priceFmt;
            }

            public String getImgs() {
                return imgs;
            }

            public void setImgs(String imgs) {
                this.imgs = imgs;
            }
        }

        public class AdBean {
            /**
             * ad_link :
             * ad_name : 首页轮播1
             * ad_code : http://img.shahaizi.cn/9227e201709251846138984.png
             */

            private String ad_link;
            private String ad_name;
            private String ad_code;

            public String getAd_link() {
                return ad_link;
            }

            public void setAd_link(String ad_link) {
                this.ad_link = ad_link;
            }

            public String getAd_name() {
                return ad_name;
            }

            public void setAd_name(String ad_name) {
                this.ad_name = ad_name;
            }

            public String getAd_code() {
                return ad_code;
            }

            public void setAd_code(String ad_code) {
                this.ad_code = ad_code;
            }
        }
    }
}




