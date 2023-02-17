package com.tktimg.model;

import java.util.List;
import java.util.Set;


public interface TktImgDAO_interface {
	
	public Integer insert(TktImg tktimg);
	public void update(TktImg tktimg);
	public void delete(Integer tkt_img_id);
	public TktImg findByPrimaryKey(Integer tkt_img_id);
	public List<TktImg>getAll();
	public Set<TktImg>getAllOneImage();
	public List<TktImg>findByTktId(Integer tkt_id);

	
}
