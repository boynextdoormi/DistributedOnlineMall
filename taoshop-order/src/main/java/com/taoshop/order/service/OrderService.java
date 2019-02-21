package com.taoshop.order.service;

import com.taoshop.common.pojo.TaoResult;
import com.taoshop.pojo.TbOrder;
import com.taoshop.pojo.TbOrderItem;
import com.taoshop.pojo.TbOrderShipping;

import java.util.List;

public interface OrderService {
    TaoResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping);
}
