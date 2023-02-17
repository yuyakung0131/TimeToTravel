package com.reservation.model;

import java.sql.Timestamp;
import java.util.*;

public interface ReservationDAO_interface {
	public void insert(ReservationVO reservationVO);
	
	public void insertEmptyReservation(ReservationVO reservationVO);

	public void delete(Integer reservation_id);

	public ReservationVO findPrimaryKey(Integer reservation_id);
	
	public ReservationVO getByRoomTypeByStartDate(Integer room_type_id,Timestamp start_date);
	
	public ReservationVO getDates(Timestamp end_date,Timestamp start_date);
	
	public void deleteReservationDate(Integer room_type_id ,Timestamp Start_date);

	public List<ReservationVO> getAll();
}
