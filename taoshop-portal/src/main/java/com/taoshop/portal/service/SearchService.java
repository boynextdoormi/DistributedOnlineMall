package com.taoshop.portal.service;

import com.taoshop.portal.pojo.SearchResult;

public interface SearchService {
    SearchResult search(String queryString, int page);
}
