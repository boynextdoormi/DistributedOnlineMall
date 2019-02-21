package com.taoshop.portal.service;

import com.taoshop.common.pojo.TaoResult;
import com.taoshop.common.utils.HttpClientUtil;
import com.taoshop.common.utils.JsonUtils;
import com.taoshop.portal.pojo.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Value("${ORDER_BASE_URL}")
    private String ORDER_BASE_URL;
    @Value("${ORDER_CREATE_URL}")
    private String ORDER_CREATE_URL;


    @Override
    public String createOrder(Order order) {
        //调用order之前补全用户信息
        //调用tao-order的服务提交订单。
        String json = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_CREATE_URL, JsonUtils.objectToJson(order));
        //把json转换成taoResult
        TaoResult taoResult = TaoResult.format(json);
        if (taoResult.getStatus() == 200) {
            Object orderId = taoResult.getData();
            return orderId.toString();
        }
        return "";
    }

}
