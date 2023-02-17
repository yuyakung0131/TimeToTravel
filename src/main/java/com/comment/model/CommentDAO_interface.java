package com.comment.model;

import java.util.List;

import com.article.model.ArticleVO;

public interface CommentDAO_interface {
	public void insert(CommentVO commentVO);
	public void update(CommentVO commentVO );
	public void delete(Integer comment_id);
	public CommentVO getByCommentId(Integer comment_id);
	public List<CommentVO> getByArticleId(Integer article_id);
	public CommentVO getCommentByID(Integer comment_id);
	
}
