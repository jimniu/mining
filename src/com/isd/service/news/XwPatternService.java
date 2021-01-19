package com.isd.service.news;

import java.util.List;

import com.isd.entity.news.XwPattern;

public interface XwPatternService {
	public XwPattern findById(Integer id);
	public List<XwPattern> list(Integer siteid);
}
