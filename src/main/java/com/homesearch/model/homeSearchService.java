package com.homesearch.model;

import java.util.List;

import javax.print.event.PrintJobAttributeEvent;


public class homeSearchService {

	private homeSearchDAO dao;
	
	public homeSearchService() {
		dao = new homeSearchDAO();
	}
	public List<homeSearchVO> selectByTitle(){
		return dao.getFuzzySearch();
	}

	
}
