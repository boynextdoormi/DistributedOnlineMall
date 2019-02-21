package com.taoshop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taoshop.common.pojo.EasyUIResult;
import com.taoshop.common.pojo.TaoResult;
import com.taoshop.common.utils.HttpClientUtil;
import com.taoshop.mapper.TbContentMapper;
import com.taoshop.pojo.TbContent;
import com.taoshop.pojo.TbContentExample;
import com.taoshop.service.intf.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${REST_CONTENT_SYNC_URL}")
    private String REST_CONTENT_SYNC_URL;

    /***
     * 根据ID查询内容并返回EasyUIResult分页对象
     */
    @Override
    public EasyUIResult getItemList(Integer page, Integer rows, long categoryId) {
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        //设置分页
        PageHelper.startPage(page, rows);
        List<TbContent> list = contentMapper.selectByExample(example);
        //取分页信息
        PageInfo<TbContent> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        EasyUIResult result = new EasyUIResult();
        result.setTotal(total);
        result.setRows(list);

        return result;
    }

    /***
     * 新增内容
     * @param content
     * @return
     */
    @Override
    public TaoResult insertContent(TbContent content) {
        content.setCreated(new Date());
        content.setUpdated(new Date());
        contentMapper.insert(content);

        //添加缓存同步逻辑
        try {
            HttpClientUtil.doGet(REST_BASE_URL+REST_CONTENT_SYNC_URL+content.getCategoryId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return TaoResult.ok();
    }
}
