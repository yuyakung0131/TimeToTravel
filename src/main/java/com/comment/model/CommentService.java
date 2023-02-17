package com.comment.model;

import java.util.List;

public class CommentService {
	private CommentDAO_interface dao;
	
	public CommentService() {
		dao=new CommentDAO();
	}
	public CommentVO insert(Integer article_id,Integer member_id,String comment_content) {
		CommentVO commentVO=new CommentVO();
		commentVO.setArticle_id(article_id);
		commentVO.setMember_id(member_id);
		commentVO.setComment_content(comment_content);
		dao.insert(commentVO);
		return commentVO;
	}
	public CommentVO update(String comment_content,Integer comment_id) {
		CommentVO commentVO=new CommentVO();
		commentVO.setComment_content(comment_content);
		commentVO.setComment_id(comment_id);
		dao.update(commentVO);
		return commentVO;
	}
	
	public void delete(Integer comment_id) {
		
	}
	
	public CommentVO getByCommentId(Integer comment_id) {
		return dao.getByCommentId(comment_id);
	}
	
	public List<CommentVO> getByArticleId(Integer article_id){
		return dao.getByArticleId(article_id);
	}
	
	public CommentVO getCommentByID(Integer comment_id) {
		return dao.getCommentByID(comment_id);
	}
}
