package com.reservation.model;

import java.sql.Timestamp;
import java.util.List;

public class ReservationService {
 private ReservationDAO_interface dao;
 public ReservationService() {
	 dao=new ReservationDAO();
 }
 public ReservationVO insert(Integer room_type_id,Timestamp reservation_date,Integer room_type_amount,Integer reservation_amount) {
	 ReservationVO reservationVO=new ReservationVO();
	 reservationVO.setRoom_type_id(room_type_id);
	 reservationVO.setReservation_date(reservation_date);
	 reservationVO.setRoom_type_amount(room_type_amount);
	 reservationVO.setReservation_amount(reservation_amount);
	 dao.insert(reservationVO);
	 return reservationVO;
 }
 
 public ReservationVO insertEmptyReservation(Integer room_type_id,Timestamp reservation_date,Integer room_type_amount) {
	 ReservationVO reservationVO=new ReservationVO();
	 reservationVO.setRoom_type_id(room_type_id);
	 reservationVO.setReservation_date(reservation_date);
	 reservationVO.setRoom_type_amount(room_type_amount);
	 dao.insertEmptyReservation(reservationVO);
	 return reservationVO;
 }
 
 public ReservationVO getByRoomTypeByStartDate(Integer room_type_id,Timestamp start_date) {
	  return dao.getByRoomTypeByStartDate(room_type_id, start_date);
	 }
 
 public void deleteReservationDate(Integer room_type_id,Timestamp start_date) {
	 dao.deleteReservationDate(room_type_id, start_date);
 }
 
 public void delete(Integer reservation_id) {
	 dao.delete(reservation_id);
 }
 public ReservationVO getOneReservation(Integer reservation_id) {
	 return dao.findPrimaryKey(reservation_id);
 }
 public List<ReservationVO> getAll(){
	 return dao.getAll();
 }
 
 public ReservationVO getDates(Timestamp end_date,Timestamp start_date) {
	 return dao.getDates( end_date,start_date);
 }
}
