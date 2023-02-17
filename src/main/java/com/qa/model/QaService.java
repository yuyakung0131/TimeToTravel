package com.qa.model;

import java.util.List;

public class QaService {
	
	private QaDAO_interface dao;
	
	public QaService() {
		dao=new QaDAO();
	}
	
	public QaVO addQaVO(Integer qa_show_no, String question, 
			String answer, Byte qa_state, Integer qa_class_id) {
		
		QaVO qavo=new QaVO();
		
		qavo.setQa_show_no(qa_show_no);
		qavo.setQuestion(question);
		qavo.setAnswer(answer);
		qavo.setQa_state(qa_state);
		qavo.setQa_class_id(qa_class_id);
		
		
		dao.insert(qavo);
		return qavo;
		
	}
	
	
	public QaVO updateQaVO(Integer qa_no,Integer qa_show_no, String question, 
			String answer, Byte qa_state, Integer qa_class_id) {
		
		QaVO qavo=new QaVO();
		
		qavo.setQa_no(qa_no);
		qavo.setQa_show_no(qa_show_no);
		qavo.setQuestion(question);
		qavo.setAnswer(answer);
		qavo.setQa_state(qa_state);
		qavo.setQa_class_id(qa_class_id);
		
		dao.update(qavo);
		return qavo;
		
	}
	
	public void deleteQaVO(Integer qa_no) {
		dao.delete(qa_no);
	}

	public QaVO getOneQaVO(Integer qa_no) {
		return dao.findByPrimaryKey(qa_no);
	}
	public List<QaVO>getAll(){
		return dao.getAll();
	}
	
	public List<QaVO> findByType(Integer qa_class_id) {
		return dao.findByType(qa_class_id);
	}
	
	public List<QaVO> findByState(Byte qa_state) {
		return dao.findByState(qa_state);
	}
}
