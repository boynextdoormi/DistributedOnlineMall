package com.taoshop.order.pojo;

import com.taoshop.pojo.TbOrder;
import com.taoshop.pojo.TbOrderItem;
import com.taoshop.pojo.TbOrderShipping;

import java.util.List;

public class Order extends TbOrder {

    private List<TbOrderItem> orderItems;
    private TbOrderShipping orderShipping;
    public List<TbOrderItem> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(List<TbOrderItem> orderItems) {
        this.orderItems = orderItems;
    }
    public TbOrderShipping getOrderShipping() {
        return orderShipping;
    }
    public void setOrderShipping(TbOrderShipping orderShipping) {
        this.orderShipping = orderShipping;
    }


}