package com.taoshop.portal.service;

import com.taoshop.pojo.TbItem;
import com.taoshop.portal.pojo.ItemInfo;

public interface ItemService {
    ItemInfo getItemById(Long itemId);
    String getItemDescById(Long itemId);
    String getItemParam(Long itemId);
}
