package com.taoshop.service.intf;

import com.taoshop.common.pojo.EasyUIResult;
import com.taoshop.common.pojo.TaoResult;
import com.taoshop.pojo.TbItem;

public interface ItemService {

    TbItem getItemById(long itemId);
    EasyUIResult getItemList(Integer page, Integer rows);
    TaoResult creatItem(TbItem tbItem,String desc,String itemParam) throws Exception;

}
