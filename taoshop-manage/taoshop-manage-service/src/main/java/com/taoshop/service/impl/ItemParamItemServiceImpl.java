package com.taoshop.service.impl;

import com.taoshop.common.utils.JsonUtils;
import com.taoshop.mapper.TbItemParamItemMapper;
import com.taoshop.pojo.TbItemParamItem;
import com.taoshop.pojo.TbItemParamItemExample;
import com.taoshop.service.intf.ItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {

    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    /***
     * 直接通过ID查询规格参数的模板并返回HTML
     * @param itemId
     * @return
     */
    @Override
    public String getItemParamByItemId(Long itemId) {
        //根据商品ID查询规格参数
        TbItemParamItemExample example =new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> list= itemParamItemMapper.selectByExampleWithBLOBs(example);
        //判断结果
        if (list ==null || list.size()==0){
            return "";
        }
        //取规格信息
        TbItemParamItem itemParamItem =list.get(0);
        String paramData = itemParamItem.getParamData();
        //生成HTML
        // 把规格参数json数据转换成java对象
        List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
        StringBuffer sb = new StringBuffer();
        sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">\n");
        sb.append("    <tbody>\n");
        for(Map m1:jsonList) {
            sb.append("        <tr>\n");
            sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+m1.get("group")+"</th>\n");
            sb.append("        </tr>\n");
            List<Map> list2 = (List<Map>) m1.get("params");
            for(Map m2:list2) {
                sb.append("        <tr>\n");
                sb.append("            <td class=\"tdTitle\">"+m2.get("k")+"</td>\n");
                sb.append("            <td>"+m2.get("v")+"</td>\n");
                sb.append("        </tr>\n");
            }
        }
        sb.append("    </tbody>\n");
        sb.append("</table>");
        return sb.toString();
    }
}
