package com.articlephoto.model;

import java.sql.Connection;
import java.util.List;

public interface ArticlePhotoDAO_interface {
	public void insert(ArticlePhotoVO articlephotoVO);
	public void insertByArticle(ArticlePhotoVO articlephotoVO,java.sql.Connection con);
	public void update(ArticlePhotoVO articlephotoVO);
	public void deleteByArticleId(Integer article_id);
	public void deleteByPicId(Integer article_pic_id);
	public ArticlePhotoVO getByPicId(Integer article_pic_id);
	public List<ArticlePhotoVO> getByArticleId(Integer article_id);
	public List<ArticlePhotoVO> getAll();
}
