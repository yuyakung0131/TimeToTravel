package com.shoppinglist.model;

import java.util.List;

import com.tktlist.model.TktList;

public class ShoppingListService {
	private ShoppingListDAO_interface dao;
	
	public ShoppingListService() {
		dao=new ShoppingListDAO();
	}
	
//	public ShoppingList addShoppingListItem(Integer tkt_id, Integer member_id) {
//		ShoppingList shoppingList=new ShoppingList();
//		shoppingList.setTkt_id(tkt_id);
//		shoppingList.setMember_id(member_id);
//		dao.insert(shoppingList);
//		
//		return shoppingList;
//	}
	
	
	
	
	public ShoppingList addShoppingList(Integer tkt_id, Integer member_id,Integer tkt_amount) {
		ShoppingList shoppingList=new ShoppingList();
		shoppingList.setTkt_id(tkt_id);
		shoppingList.setMember_id(member_id);
		shoppingList.setTkt_amount(tkt_amount);
		dao.insert(shoppingList);
		
		return shoppingList;
	}
	
	public ShoppingList updateShoppingList(Integer tkt_id, Integer member_id,Integer tkt_amount) {
		ShoppingList shoppingList=new ShoppingList();
		shoppingList.setTkt_id(tkt_id);
		shoppingList.setMember_id(member_id);
		shoppingList.setTkt_amount(tkt_amount);
		dao.update(shoppingList);
		
		return shoppingList;
	}
	
	public ShoppingList getOneShoppingList(Integer tkt_id,Integer member_id) {
		return dao.findByPrimaryKey(tkt_id, member_id);
	}
	
	public List<ShoppingList>getAll(){
		return dao.getAll();
	}
	
	public void deleteShoppingList(Integer tkt_id,Integer member_id) {
		dao.delete(tkt_id, member_id);
	}

	public List<ShoppingList> getAllbyMember(Integer member_id) {
		return dao.getAllbyMember(member_id);
	}
	

}
