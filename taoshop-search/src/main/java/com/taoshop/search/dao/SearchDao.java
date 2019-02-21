package com.taoshop.search.dao;

import com.taoshop.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;

public interface SearchDao {
    SearchResult search(SolrQuery query)throws Exception;
}
