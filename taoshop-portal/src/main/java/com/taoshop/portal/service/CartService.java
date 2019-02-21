package com.taoshop.portal.service;

import com.taoshop.common.pojo.TaoResult;
import com.taoshop.portal.pojo.CartItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CartService {

    TaoResult addCartItem(long itemId, int num,
                          HttpServletRequest request, HttpServletResponse response);
    List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response);
    TaoResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response);
}
