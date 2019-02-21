package com.taoshop.service.intf;

import com.taoshop.pojo.TbItemCat;

import java.util.List;

public interface ItemCatService {

    List<TbItemCat> getItemCatList(Long parentId);
}
