package com.article.model;

import java.util.List;

import com.articlephoto.model.ArticlePhotoVO;

public class ArticleService {
	private ArticleDAO_interface dao;

	public ArticleService() {
		dao = new ArticleDAO();
	}

	public ArticleVO insert(Integer member_id, String article_title, String article_content) {
		ArticleVO articleVO = new ArticleVO();
		articleVO.setMember_id(member_id);
		articleVO.setArticle_title(article_title);
		articleVO.setArticle_content(article_content);
		dao.insert(articleVO);
		return articleVO;
	}

	public ArticleVO insertWithPhoto(Integer member_id, String article_title, String article_content , byte[] article_pic) {
		ArticleVO articleVO = new ArticleVO();
		ArticlePhotoVO articlePhotoVO = new ArticlePhotoVO();
		articleVO.setMember_id(member_id);
		articleVO.setArticle_title(article_title);
		articleVO.setArticle_content(article_content);
		articlePhotoVO.setArticle_pic(article_pic);
		dao.insertWithPhoto(articleVO, articlePhotoVO);
		return articleVO;
	}
	
	public ArticleVO update(String article_title, String article_content, Integer article_id) {
		ArticleVO articleVO = new ArticleVO();
		articleVO.setArticle_title(article_title);
		articleVO.setArticle_content(article_content);
		articleVO.setArticle_id(article_id);
		dao.update(articleVO);
		return articleVO;
	}

	public void delete(Integer article_id) {
		dao.delete(article_id);
	}

	public ArticleVO getArticleId(Integer article_id) {
		return dao.getArticleId(article_id);
	}
	
	public List<ArticleVO> getAll() {
		return dao.getAll();
	}

	public List<ArticleVO> getMember(Integer member_id) {
		return dao.getAllMember(member_id);
	}

	public List<ArticleVO> getAticleTitle(String article_title) {
		return dao.getArticleTitle(article_title);
	}

	public List<ArticleVO> getArticleContent(String article_content) {
		return dao.getArticleContent(article_content);
	}

}
