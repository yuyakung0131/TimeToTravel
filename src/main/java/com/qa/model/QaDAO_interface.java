package com.qa.model;

import java.util.List;

public interface QaDAO_interface {
	
	public void insert(QaVO qavo);
	public void update(QaVO qavo);
	public QaVO findByPrimaryKey(Integer qa_no);
	public void delete(Integer qa_no);
	public List<QaVO>getAll();
	public List<QaVO>findByState(Byte qa_state);
	public List<QaVO>findByType(Integer qa_class_id);
	

}
