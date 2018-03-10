package com.zdl.testapp;

import java.util.List;

/**
 * Created by zdl on 2017/11/14.
 */

public class Aa {

    /**
     * regionId : 330324
     * orderListVOList : [{"orderId":14,"userNumber":8773330300005,"regionId":330324,"organizationId":26,"orderStatusId":2,"orderTypeId":0,"orderAmount":0,"deadlineTime":0,"linkMan":"订单撤销","linkPhone":"15858261319","linkAddress":"订单撤销测试接口专用订单","orderContent":[{"contentId":0,"orderId":0,"specificationId":"YSP35.5","num":1}]},{"orderId":333,"userNumber":8773330300004,"regionId":330324,"organizationId":26,"orderStatusId":1,"orderTypeId":0,"orderAmount":0,"deadlineTime":0,"linkMan":"可处理订单","linkPhone":"15858261319","linkAddress":"可处理订单","orderContent":[{"contentId":0,"orderId":0,"specificationId":"YSP35.5","num":1}]}]
     */

    private int regionId;
    private List<OrderListVOListBean> orderListVOList;

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public List<OrderListVOListBean> getOrderListVOList() {
        return orderListVOList;
    }

    public void setOrderListVOList(List<OrderListVOListBean> orderListVOList) {
        this.orderListVOList = orderListVOList;
    }

    public static class OrderListVOListBean {
        /**
         * orderId : 14
         * userNumber : 8773330300005
         * regionId : 330324
         * organizationId : 26
         * orderStatusId : 2
         * orderTypeId : 0
         * orderAmount : 0
         * deadlineTime : 0
         * linkMan : 订单撤销
         * linkPhone : 15858261319
         * linkAddress : 订单撤销测试接口专用订单
         * orderContent : [{"contentId":0,"orderId":0,"specificationId":"YSP35.5","num":1}]
         */

        private int orderId;
        private long userNumber;
        private int regionId;
        private int organizationId;
        private int orderStatusId;
        private int orderTypeId;
        private int orderAmount;
        private int deadlineTime;
        private String linkMan;
        private String linkPhone;
        private String linkAddress;
        private List<OrderContentBean> orderContent;

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public long getUserNumber() {
            return userNumber;
        }

        public void setUserNumber(long userNumber) {
            this.userNumber = userNumber;
        }

        public int getRegionId() {
            return regionId;
        }

        public void setRegionId(int regionId) {
            this.regionId = regionId;
        }

        public int getOrganizationId() {
            return organizationId;
        }

        public void setOrganizationId(int organizationId) {
            this.organizationId = organizationId;
        }

        public int getOrderStatusId() {
            return orderStatusId;
        }

        public void setOrderStatusId(int orderStatusId) {
            this.orderStatusId = orderStatusId;
        }

        public int getOrderTypeId() {
            return orderTypeId;
        }

        public void setOrderTypeId(int orderTypeId) {
            this.orderTypeId = orderTypeId;
        }

        public int getOrderAmount() {
            return orderAmount;
        }

        public void setOrderAmount(int orderAmount) {
            this.orderAmount = orderAmount;
        }

        public int getDeadlineTime() {
            return deadlineTime;
        }

        public void setDeadlineTime(int deadlineTime) {
            this.deadlineTime = deadlineTime;
        }

        public String getLinkMan() {
            return linkMan;
        }

        public void setLinkMan(String linkMan) {
            this.linkMan = linkMan;
        }

        public String getLinkPhone() {
            return linkPhone;
        }

        public void setLinkPhone(String linkPhone) {
            this.linkPhone = linkPhone;
        }

        public String getLinkAddress() {
            return linkAddress;
        }

        public void setLinkAddress(String linkAddress) {
            this.linkAddress = linkAddress;
        }

        public List<OrderContentBean> getOrderContent() {
            return orderContent;
        }

        public void setOrderContent(List<OrderContentBean> orderContent) {
            this.orderContent = orderContent;
        }

        public static class OrderContentBean {
            /**
             * contentId : 0
             * orderId : 0
             * specificationId : YSP35.5
             * num : 1
             */

            private int contentId;
            private int orderId;
            private String specificationId;
            private int num;

            public int getContentId() {
                return contentId;
            }

            public void setContentId(int contentId) {
                this.contentId = contentId;
            }

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }

            public String getSpecificationId() {
                return specificationId;
            }

            public void setSpecificationId(String specificationId) {
                this.specificationId = specificationId;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }
        }
    }
}
