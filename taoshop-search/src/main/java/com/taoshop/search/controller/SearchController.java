package com.taoshop.search.controller;

import com.taoshop.common.pojo.TaoResult;
import com.taoshop.common.utils.ExceptionUtil;
import com.taoshop.search.pojo.SearchResult;
import com.taoshop.search.service.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/***
 * 商品查询Controller
 */
@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping(value="/query", method= RequestMethod.GET)
    @ResponseBody
    public TaoResult search(@RequestParam("q")String queryString,
                            @RequestParam(defaultValue="1")Integer page,
                            @RequestParam(defaultValue="60")Integer rows) {
        //查询条件不能为空
        if (StringUtils.isBlank(queryString)) {
            return TaoResult.build(400, "查询条件不能为空");
        }
        SearchResult searchResult = null;
        try {
            //解决get乱码
            //queryString =new String(queryString.getBytes("iso8859-1"),"utf-8");
            searchResult = searchService.search(queryString, page, rows);
        } catch (Exception e) {
            e.printStackTrace();
            return TaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return TaoResult.ok(searchResult);

    }

}
