package com.yinglan.scc.entity.mine.myshoppingcart;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class MyShoppingCartBean  extends BaseResult<MyShoppingCartBean.ResultBean> {

    public class ResultBean {
        /**
         * list : [{"id":706,"type":"0","push_type":"all","title":"今天","summary":"载天下网络科技（苏州）有限公司（以下简称...","isRead":0,"pushTime":"2017-08-26  11:27"}]
         * page : 1
         * pageSize : 20
         * dataTotal : 1
         * pageTotal : 1
         * unreadnum : 1
         */

        private int page;
        private int pageSize;
        private int dataTotal;
        private int pageTotal;
        private int unreadnum;
        private List<ListBean> list;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getDataTotal() {
            return dataTotal;
        }

        public void setDataTotal(int dataTotal) {
            this.dataTotal = dataTotal;
        }

        public int getPageTotal() {
            return pageTotal;
        }

        public void setPageTotal(int pageTotal) {
            this.pageTotal = pageTotal;
        }

        public int getUnreadnum() {
            return unreadnum;
        }

        public void setUnreadnum(int unreadnum) {
            this.unreadnum = unreadnum;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public class ListBean {
            /**
             * id : 291
             * type : 0
             * push_type : single
             * title : 认证信息审核通过
             * summary : 的认证信息审核通过...
             * isRead : 0
             * pushTime : 2017-12-13  19:16
             * order_code : 2017123098545215
             * dr_name : 王大富司机
             * order_id : 291
             */

            private int id;
            private String type;
            private String push_type;
            private String title;
            private String summary;
            private int isRead;
            private int isSelected;
            private int isEdit;
            private String pushTime;
            private String order_code;
            private String dr_name;
            private int order_id;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getPush_type() {
                return push_type;
            }

            public void setPush_type(String push_type) {
                this.push_type = push_type;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public int getIsRead() {
                return isRead;
            }

            public void setIsRead(int isRead) {
                this.isRead = isRead;
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

            public String getPushTime() {
                return pushTime;
            }

            public void setPushTime(String pushTime) {
                this.pushTime = pushTime;
            }

            public String getOrder_code() {
                return order_code;
            }

            public void setOrder_code(String order_code) {
                this.order_code = order_code;
            }

            public String getDr_name() {
                return dr_name;
            }

            public void setDr_name(String dr_name) {
                this.dr_name = dr_name;
            }

            public int getOrder_id() {
                return order_id;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
            }
        }
    }

}
