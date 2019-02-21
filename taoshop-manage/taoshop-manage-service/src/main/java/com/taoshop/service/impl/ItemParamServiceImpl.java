package com.taoshop.service.impl;

import com.taoshop.common.pojo.TaoResult;
import com.taoshop.mapper.TbItemParamMapper;
import com.taoshop.pojo.TbItemParam;
import com.taoshop.pojo.TbItemParamExample;
import com.taoshop.service.intf.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    private TbItemParamMapper itemParamMapper;

    @Override
    public TaoResult getItemParamByCid(long cid) {
        TbItemParamExample example =new TbItemParamExample();
        TbItemParamExample.Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(cid);
        List<TbItemParam> list= itemParamMapper.selectByExampleWithBLOBs(example);
        //判断结果
        if (list !=null&&list.size()>0){
            return TaoResult.ok(list.get(0));
        }
        return TaoResult.ok();
    }

    @Override
    public TaoResult insertItemParam(TbItemParam itemParam) {
        itemParam.setCreated(new Date());
        itemParam.setUpdated(new Date());
        itemParamMapper.insert(itemParam);
        return TaoResult.ok();
    }
}
