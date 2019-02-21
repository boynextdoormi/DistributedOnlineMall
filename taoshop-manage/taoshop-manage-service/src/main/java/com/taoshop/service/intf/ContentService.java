package com.taoshop.service.intf;

import com.taoshop.common.pojo.EasyUIResult;
import com.taoshop.common.pojo.TaoResult;
import com.taoshop.pojo.TbContent;

public interface ContentService {
    EasyUIResult getItemList(Integer page, Integer rows, long categoryId);
    TaoResult insertContent(TbContent content);
}
