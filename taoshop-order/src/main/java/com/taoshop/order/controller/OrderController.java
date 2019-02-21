package com.taoshop.order.controller;

import com.taoshop.common.pojo.TaoResult;
import com.taoshop.common.utils.ExceptionUtil;
import com.taoshop.order.pojo.Order;
import com.taoshop.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/***
 * 订单Controller
 */
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public TaoResult createOrder(@RequestBody Order order) {
        try {
            TaoResult result = orderService.createOrder(order, order.getOrderItems(), order.getOrderShipping());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
}