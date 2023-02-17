package com.commentreport.model;

import java.sql.Timestamp;
import java.util.List;

public class CommentReportService {
	/* initial implement */
	private CommentReportDAO_interface dao;

	/* initial constructor */
	public CommentReportService() {
		dao = new CommentReportDAO();
	}

	/* initial method */
	/* get all service */
	public List<CommentReportVO> getAll() {
		return dao.getALL();
	}

	/* get one to update */
	public CommentReportVO transCommentReport(Integer comment_report_id) {
		return dao.findBYID(comment_report_id);
	}

	/* update report data */
	public CommentReportVO updateCommentReport(Byte comment_reportprocess_state, String comment_reportprocess_content,
			Timestamp comment_reportprocess_time, Integer emp_id, Integer comment_id) {
		CommentReportVO commentReportVO = new CommentReportVO();
		commentReportVO.setComment_reportprocess_state(comment_reportprocess_state);
		commentReportVO.setComment_reportprocess_content(comment_reportprocess_content);
		commentReportVO.setComment_reportprocess_time(comment_reportprocess_time);
		commentReportVO.setEmp_id(emp_id);
		commentReportVO.setComment_id(comment_id);
		dao.update(commentReportVO);
		return commentReportVO;
	}

	/* get comment_process_data data */
	public CommentReportVO getCommentProcessState(Integer comment_id) {
		return dao.findStateByID(comment_id);
	}

	/* insert comment report service */
	public CommentReportVO addCommentReport(Integer comment_id, Integer member_id, Byte comment_report_reason,
			Byte comment_reportprocess_state) {
		CommentReportVO commentReportVO = new CommentReportVO();
		commentReportVO.setComment_id(comment_id);
		commentReportVO.setMember_id(member_id);
		commentReportVO.setComment_report_reason(comment_report_reason);
		commentReportVO.setComment_reportprocess_state(comment_reportprocess_state);
		dao.insert(commentReportVO);
		return commentReportVO;
	}
}
