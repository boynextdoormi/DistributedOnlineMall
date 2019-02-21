package com.taoshop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taoshop.common.pojo.EasyUIResult;
import com.taoshop.common.pojo.TaoResult;
import com.taoshop.common.utils.IDUtils;
import com.taoshop.mapper.TbItemDescMapper;
import com.taoshop.mapper.TbItemMapper;
import com.taoshop.mapper.TbItemParamItemMapper;
import com.taoshop.pojo.TbItem;
import com.taoshop.pojo.TbItemDesc;
import com.taoshop.pojo.TbItemExample;
import com.taoshop.pojo.TbItemParamItem;
import com.taoshop.service.intf.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;
    @Autowired
    private TbItemDescMapper itemDescMapper;
    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    /***
     * 根据商品ID查询商品
     */
    @Override
    public TbItem getItemById(long itemId) {
        TbItemExample example =new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId);
        List<TbItem> list = itemMapper.selectByExample(example);
        if (list !=null && list.size()>0){
            TbItem item = list.get(0);
            return item;
        }
        return null;
    }

    /***
     * 查询所有商品并返回分页信息
     */
    @Override
    public EasyUIResult getItemList(Integer page, Integer rows) {
        TbItemExample example = new TbItemExample();
        //设置分页
        PageHelper.startPage(page, rows);
        List<TbItem> list = itemMapper.selectByExample(example);
        //取分页信息
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        EasyUIResult result = new EasyUIResult();
        result.setTotal(total);
        result.setRows(list);

        return result;
    }

    /***
     * 添加商品及描述
     */
    @Override
    public TaoResult creatItem(TbItem tbItem,String desc,String itemParam) throws Exception{
        //item补全
        Long itemId = IDUtils.genItemId();
        tbItem.setId(itemId);
        //商品状态 1正常 2下架 3删除
        tbItem.setStatus((byte) 1);
        tbItem.setCreated(new Date());
        tbItem.setUpdated(new Date());
        //插入到数据库
        itemMapper.insert(tbItem);
        //添加商品描述（大文本单独用一个表保存）
        TaoResult result= insertItemDesc(itemId,desc);
        if (result.getStatus()!=200){
            throw new Exception();
        }
        result= insertItemParamItem(itemId,itemParam);
        if (result.getStatus()!=200){
            throw new Exception();
        }
        return TaoResult.ok();
    }


    /***
     * 添加商品描述
     * @param desc
     * @return
     */
    private TaoResult insertItemDesc(Long itemId,String desc){
        TbItemDesc itemDesc=new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        itemDescMapper.insert(itemDesc);
        return TaoResult.ok();
    }

    /***
     * 添加规格参数
     * @param itemId
     * @param itemParam
     * @return
     */
    private TaoResult insertItemParamItem(Long itemId,String itemParam){
        //创建一个POJO
        TbItemParamItem itemParamItem =new TbItemParamItem();
        itemParamItem.setItemId(itemId);
        itemParamItem.setParamData(itemParam);
        itemParamItem.setCreated(new Date());
        itemParamItem.setUpdated(new Date());
        itemParamItemMapper.insert(itemParamItem);
        return TaoResult.ok();

    }

}
