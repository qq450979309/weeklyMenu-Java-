package com.service;

import java.util.List;

import com.entity.Search;

public interface SearchService {
	
	public boolean recordKeyword(String keyword, int userId);

	public List<Search> getKeywords();
	
}
