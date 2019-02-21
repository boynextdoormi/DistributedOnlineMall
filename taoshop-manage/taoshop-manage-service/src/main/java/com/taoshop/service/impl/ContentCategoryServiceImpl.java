package com.taoshop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taoshop.common.pojo.EUTreeNode;
import com.taoshop.common.pojo.EasyUIResult;
import com.taoshop.common.pojo.TaoResult;
import com.taoshop.mapper.TbContentCategoryMapper;
import com.taoshop.pojo.TbContentCategory;
import com.taoshop.pojo.TbContentCategoryExample;
import com.taoshop.service.intf.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    /***
     * 根据父节点查出所有内容分类信息，并返回EUTreeNode对象
     */
    @Override
    public List<EUTreeNode> getCategoryList(long parentId) {
        TbContentCategoryExample example =new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
        List<EUTreeNode> resultList =new ArrayList<>();
        for (TbContentCategory category:list){
            EUTreeNode node =new EUTreeNode();
            node.setId(category.getId());
            node.setText(category.getName());
            node.setState(category.getIsParent()?"closed":"open");
            resultList.add(node);
        }
        return resultList;
    }

    /***
     * 添加节点
     * @param parentId
     * @param name
     * @return
     */
    @Override
    public TaoResult insertContentCategory(long parentId, String name) {
        //创建一个pojo
        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setName(name);
        contentCategory.setIsParent(false);
        //'状态。可选值:1(正常),2(删除)',
        contentCategory.setStatus(1);
        contentCategory.setParentId(parentId);
        contentCategory.setSortOrder(1);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());
        //添加记录
        contentCategoryMapper.insert(contentCategory);
        //查看父节点的isParent列是否为true，如果不是true改成true
        TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
        //判断是否为true
        if(!parentCat.getIsParent()) {
            parentCat.setIsParent(true);
            //更新父节点
            contentCategoryMapper.updateByPrimaryKey(parentCat);
        }
        //返回结果
        return TaoResult.ok(contentCategory);
    }

    /***
     * 删除节点
     * @param parentId
     * @param id
     * @return
     */
    @Override
    public TaoResult deleteContentCategory(long parentId, long id) {
        //删除id对应的记录
        TbContentCategoryExample example =new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        contentCategoryMapper.deleteByExample(example);
        //需要判断parentid对应的记录下是否有子节点。
        //如果没有子节点。需要把parentid对应的记录的isparent改成false。
        TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
        //取父节点下的所有子节点，判断是否为空
        List<EUTreeNode> childlist = getCategoryList(parentId);
        if(childlist.size()==0) {
            parentCat.setIsParent(false);
            //更新父节点
            contentCategoryMapper.updateByPrimaryKey(parentCat);
        }
        //返回结果
        return TaoResult.ok();
    }

    /***
     * 根据内容ID修改名称
     * @param id
     * @param name
     * @return
     */
    @Override
    public TaoResult updateContentCategory(long id, String name) {
        TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
        contentCategory.setName(name);
        contentCategoryMapper.updateByPrimaryKey(contentCategory);
        return TaoResult.ok();
    }


}
