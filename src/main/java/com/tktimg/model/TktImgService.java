package com.tktimg.model;

import java.util.List;

public class TktImgService {
	
	private TktImgDAO_interface dao;
	
	public TktImgService() {
		dao=new TktImgDAO();
	}
	
	public TktImg addTktImg(byte[]tkt_img,Integer tkt_id) {
		
		TktImg tktimg = new TktImg();
		tktimg.setTkt_id(tkt_id);
		tktimg.setTkt_img(tkt_img);
		Integer tkt_img_id = dao.insert(tktimg);
		tktimg.setTkt_img_id(tkt_img_id);
		
		return tktimg;
	}
	
	public TktImg updateTktImg(Integer tkt_img_id, byte[]tkt_img, Integer tkt_id) {
		
		TktImg tktimg = new TktImg();
		tktimg.setTkt_img_id(tkt_img_id);
		tktimg.setTkt_id(tkt_id);
		tktimg.setTkt_img(tkt_img);
		
		dao.update(tktimg);
		return tktimg;
	}
	
	public TktImg getOneTktImg(Integer tkt_img_id) {
		return dao.findByPrimaryKey(tkt_img_id);
	}
	
	public void deleteTktImg(Integer tkt_img_id) {
		dao.delete(tkt_img_id);
	}
	
	public List<TktImg>getAll(){
		return dao.getAll();
	}
	
	public List<TktImg>findByTktId(Integer tkt_id){
		return dao.findByTktId(tkt_id);
	}

}
