package com.taoshop.service.intf;

import com.taoshop.common.pojo.EUTreeNode;
import com.taoshop.common.pojo.TaoResult;

import java.util.List;

public interface ContentCategoryService {

    List<EUTreeNode> getCategoryList(long parentId);
    TaoResult insertContentCategory(long parentId,String name);
    TaoResult deleteContentCategory(long parentId,long Id);
    TaoResult updateContentCategory(long id,String name);
}
