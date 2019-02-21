package com.taoshop.rest.service;

import com.taoshop.common.pojo.TaoResult;

public interface ItemService {
    TaoResult getItemBaseInfo(long itemId);
    TaoResult getItemDesc(long itemId);
    TaoResult getItemParam(long itemId);
}
