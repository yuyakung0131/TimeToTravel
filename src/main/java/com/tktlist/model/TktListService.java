package com.tktlist.model;

import java.util.List;

public class TktListService {
	
	private TktListDAO_interface dao;
	
	public TktListService() {
		dao=new TktListDAO();
	}
	
	public TktList addTktList(Integer tkt_id, Integer member_id) {
		TktList tktlist=new TktList();
		tktlist.setTkt_id(tkt_id);
		tktlist.setMember_id(member_id);
		dao.insert(tktlist);
		
		return tktlist;
	}
	
	public TktList updateTktList(Integer tkt_id, Integer member_id) {
		TktList tktlist=new TktList();
		tktlist.setTkt_id(tkt_id);
		tktlist.setMember_id(member_id);
		dao.update(tktlist);
		
		return tktlist;
	}
	
	public TktList getOneTktList(Integer tkt_id,Integer member_id) {
		return dao.findByPrimaryKey(tkt_id, member_id);
	}
	
	public List<TktList>getAll(){
		return dao.getAll();
	}
	
	public void deleteTktList(Integer tkt_id,Integer member_id) {
		dao.delete(tkt_id, member_id);
	}
	
	public List<TktList>findByMemberID(Integer member_id){
		return dao.findByMemberID(member_id);
	}

}
