package com.tktlist.model;

import java.util.List;

public interface TktListDAO_interface {
	public void insert(TktList tktlist);
	public void update(TktList tktlist);
	public TktList findByPrimaryKey(Integer tkt_id,Integer member_id);
	public void delete(Integer tkt_id,Integer member_id);
	public List<TktList>getAll();
	public List<TktList> findByMemberID(Integer member_id);

}
