package com.qaclass.model;

import java.util.List;

public interface QaclassDAO_interface {
	public QaclassVO findByPrimaryKey(Integer qa_class_id);
	public List<QaclassVO>getAll();

}
