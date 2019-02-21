package com.taoshop.service.intf;

import com.taoshop.common.pojo.TaoResult;
import com.taoshop.pojo.TbItemParam;

public interface ItemParamService {

    TaoResult getItemParamByCid(long cid);
    TaoResult insertItemParam(TbItemParam itemParam);
}
