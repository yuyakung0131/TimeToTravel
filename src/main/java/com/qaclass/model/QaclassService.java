package com.qaclass.model;

import java.util.List;

public class QaclassService {
	private QaclassDAO_interface dao;
	
	public QaclassService() {
		dao=new QaclassDAO();
	}
	
	public QaclassVO getOneQaclassVO(Integer qa_class_id) {
		return dao.findByPrimaryKey(qa_class_id);
	}
	
	public List<QaclassVO>getAll(){
		return dao.getAll();
	}
	
}
