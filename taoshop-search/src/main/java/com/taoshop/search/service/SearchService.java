package com.taoshop.search.service;

import com.taoshop.search.pojo.SearchResult;

public interface SearchService {
    SearchResult search(String queryString, int page, int rows) throws Exception;
}
