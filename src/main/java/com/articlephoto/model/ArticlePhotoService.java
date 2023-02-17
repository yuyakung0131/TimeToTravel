package com.articlephoto.model;

import java.util.List;

public class ArticlePhotoService {
	private ArticlePhotoDAO_interface dao;

	public ArticlePhotoService() {
		dao = new ArticlePhotoDAO();
	}

	public ArticlePhotoVO insert(Integer article_id, byte[] article_pic) {
		ArticlePhotoVO articlePhotoVO = new ArticlePhotoVO();
		articlePhotoVO.setArticle_id(article_id);
		articlePhotoVO.setArticle_pic(article_pic);
		dao.insert(articlePhotoVO);
		return articlePhotoVO;
	}

	public ArticlePhotoVO update(byte[] article_pic, Integer article_pic_id) {
		ArticlePhotoVO articlePhotoVO = new ArticlePhotoVO();
		articlePhotoVO.setArticle_pic(article_pic);
		articlePhotoVO.setArticle_id(article_pic_id);
		dao.update(articlePhotoVO);
		return articlePhotoVO;
	}

	public void deleteByArticleId(Integer article_id) {
		dao.deleteByArticleId(article_id);
	}

	public void deleteByPicId(Integer article_pic_id) {
		dao.deleteByPicId(article_pic_id);
	}

	public ArticlePhotoVO getByPicId(Integer article_pic_id) {
		return dao.getByPicId(article_pic_id);
	}

	public List<ArticlePhotoVO> getByArticleId(Integer article_id) {
		return dao.getByArticleId(article_id);
	}

	public List<ArticlePhotoVO> getAll() {
		return dao.getAll();
	}

}
