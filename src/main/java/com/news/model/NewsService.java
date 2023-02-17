package com.news.model;

import java.sql.Timestamp;
import java.util.List;

public class NewsService {
private NewsDAO_interface ndao;

public NewsService() {
	ndao =new NewsDAO();
		
}
public NewsVO addNews(String news_title,String news_content,byte[] news_pic) {
	//上面要補byte[] news_pic
	NewsVO newsVO =new NewsVO();
	
	newsVO.setNews_title(news_title);
	newsVO.setNews_content(news_content);
	newsVO.setNews_pic(news_pic);
	ndao.insert(newsVO);
	
	return newsVO;
}
public NewsVO updateNews(Integer news_no,String news_title,String news_content,byte[] news_pic
		) {
	//byte[] news_pic 要補圖
	
	NewsVO newsVO =new NewsVO();
	
	newsVO.setNews_no(news_no);
	newsVO.setNews_title(news_title);
	newsVO.setNews_content(news_content);
	newsVO.setNews_pic(news_pic);
	
	ndao.update(newsVO);
	
	return newsVO;
}
public List<NewsVO> getAll(){
	return ndao.getAll();
}

public NewsVO getOneNews(Integer news_no) {
	return ndao.findByPrimaryKey(news_no);
}
public void deleteNews(Integer news_no) {
	ndao.delete(news_no);
}


}
