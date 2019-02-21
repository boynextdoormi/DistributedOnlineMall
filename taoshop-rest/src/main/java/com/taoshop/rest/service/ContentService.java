package com.taoshop.rest.service;

import com.taoshop.pojo.TbContent;

import java.util.List;

public interface ContentService {

    List<TbContent> getContentList(long contentCid);
}
