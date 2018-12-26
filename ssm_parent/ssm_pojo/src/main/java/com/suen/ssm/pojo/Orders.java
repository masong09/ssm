package com.suen.ssm.pojo;

/**
 * 订单表
 */
public class Orders {
        private Long id;                                //序列号，主键自动增长
        private String orderNum;              //订单编号
        private String orderTime;              //订单时间
        private Integer peopleCount;       //出行人数
        private String orderDesc;              //订单描述
        private Integer payType;               //支付方式（0：支付宝  1：微信  2：其他）
        private Integer orderStatus;         //订单状态（0：未支付  1：已支付）
        private Product product;               //产品id

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getOrderNum() {
                return orderNum;
        }

        public void setOrderNum(String orderNum) {
                this.orderNum = orderNum;
        }

        public String getOrderTime() {
                return orderTime;
        }

        public void setOrderTime(String orderTime) {
                this.orderTime = orderTime;
        }

        public Integer getPeopleCount() {
                return peopleCount;
        }

        public void setPeopleCount(Integer peopleCount) {
                this.peopleCount = peopleCount;
        }

        public String getOrderDesc() {
                return orderDesc;
        }

        public void setOrderDesc(String orderDesc) {
                this.orderDesc = orderDesc;
        }

        public Integer getPayType() {
                return payType;
        }

        public void setPayType(Integer payType) {
                this.payType = payType;
        }

        public Integer getOrderStatus() {
                return orderStatus;
        }

        public void setOrderStatus(Integer orderStatus) {
                this.orderStatus = orderStatus;
        }

        public Product getProduct() {
                return product;
        }

        public void setProduct(Product product) {
                this.product = product;
        }

        @Override
        public String toString() {
                return "Orders{" +
                        "id=" + id +
                        ", orderNum='" + orderNum + '\'' +
                        ", orderTime='" + orderTime + '\'' +
                        ", peopleCount=" + peopleCount +
                        ", orderDesc='" + orderDesc + '\'' +
                        ", payType=" + payType +
                        ", orderStatus=" + orderStatus +
                        ", product=" + product +
                        '}';
        }
}
