package com.news.model;

import java.util.List;


public interface NewsDAO_interface {
	public void insert(NewsVO newsVO); // 插值
	public void update(NewsVO newsVO);
	public void delete(Integer news_no);
	public NewsVO findByPrimaryKey(Integer news_no);
	public List<NewsVO> getAll(); // 拿全部之值並印在Java上
}
