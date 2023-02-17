package com.article.model;

import java.util.List;

import com.articlephoto.model.ArticlePhotoVO;

public interface ArticleDAO_interface {
	public void insert(ArticleVO articleVO);
	public void insertWithPhoto(ArticleVO articleVO , ArticlePhotoVO articlePhotoVO);
	public void delete(Integer article_id);
	public void update(ArticleVO articleVO);
	public ArticleVO getArticleId(Integer article_id);
	public List<ArticleVO> getAll();
	public List<ArticleVO> getAllMember(Integer member_id);
	public List<ArticleVO> getArticleTitle(String article_title);
	public List<ArticleVO> getArticleContent(String article_content);
}
