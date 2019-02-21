package com.taoshop.portal.controller;

import com.taoshop.common.pojo.TaoResult;
import com.taoshop.portal.pojo.CartItem;
import com.taoshop.portal.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    //添加商品到购物车
    @RequestMapping("/add/{itemId}")
    public String addCartItem(@PathVariable Long itemId,
                              @RequestParam(defaultValue="1")Integer num,
                              HttpServletRequest request, HttpServletResponse response) {
        TaoResult result = cartService.addCartItem(itemId, num, request, response);
        return "redirect:/cart/success.html";
    }
    //重定向到成功页面，防止刷新导致购物车重复添加商品
    @RequestMapping("/success")
    public String showSuccess(){
        return "cartSuccess";
    }

    //展示购物车页面
    @RequestMapping("/cart")
    public String showCart(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<CartItem> list = cartService.getCartItemList(request, response);
        model.addAttribute("cartList", list);
        return "cart";
    }

    //删除商品
    @RequestMapping("/delete/{itemId}")
    public String deleteCartItem(@PathVariable Long itemId, HttpServletRequest request, HttpServletResponse response) {
        cartService.deleteCartItem(itemId, request, response);
        return "redirect:/cart/cart.html";
    }

}
