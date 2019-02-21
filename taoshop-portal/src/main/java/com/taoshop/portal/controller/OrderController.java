package com.taoshop.portal.controller;

import com.taoshop.common.utils.ExceptionUtil;
import com.taoshop.pojo.TbUser;
import com.taoshop.portal.pojo.CartItem;
import com.taoshop.portal.pojo.Order;
import com.taoshop.portal.service.CartService;
import com.taoshop.portal.service.OrderService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/***
 * 订单Controller
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;

    //展示购物车物品
    @RequestMapping("/order-cart")
    public String showOrderCart(HttpServletRequest request, HttpServletResponse response, Model model) {
        //取购物车商品列表
        List<CartItem> list = cartService.getCartItemList(request, response);
        //传递给页面
        model.addAttribute("cartList", list);

        return "order-cart";
    }

    @RequestMapping("/create")
    public String createOrder(Order order, Model model,HttpServletRequest request) {
        try {
            //从request中取用户对象
            TbUser user = (TbUser) request.getAttribute("user");
            //补全用户信息
            order.setUserId(user.getId());
            order.setBuyerNick(user.getUsername());

            String orderId = orderService.createOrder(order);
            model.addAttribute("orderId", orderId);
            model.addAttribute("payment", order.getPayment());
            model.addAttribute("date", new DateTime().plusDays(3).toString("yyyy-MM-dd"));
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message","生成订单出错，请稍后再试试!");
            return "error/exception";
        }
    }
}