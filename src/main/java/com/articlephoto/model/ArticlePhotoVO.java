package com.articlephoto.model;

public class ArticlePhotoVO implements java.io.Serializable{
	private Integer article_pic_id;
	private Integer article_id;
	private byte[] article_pic;
	
	public Integer getArticle_pic_id() {
		return article_pic_id;
	}
	public void setArticle_pic_id(Integer article_pic_id) {
		this.article_pic_id = article_pic_id;
	}
	public Integer getArticle_id() {
		return article_id;
	}
	public void setArticle_id(Integer article_id) {
		this.article_id = article_id;
	}
	public byte[] getArticle_pic() {
		return article_pic;
	}
	public void setArticle_pic(byte[] article_pic) {
		this.article_pic = article_pic;
	}
	
	
}
