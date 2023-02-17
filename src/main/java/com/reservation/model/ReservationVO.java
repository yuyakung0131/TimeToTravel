package com.reservation.model;

import java.sql.Timestamp;

public class ReservationVO implements java.io.Serializable {
	private Integer reservation_id;
	private Integer room_type_id;
	private Timestamp reservation_date;
	private Integer room_type_amount;
	private Integer reservation_amount;
	private Integer dates;

	public Integer getDates() {
		return dates;
	}

	public void setDates(Integer dates) {
		this.dates = dates;
	}

	public Integer getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(Integer reservation_id) {
		this.reservation_id = reservation_id;
	}

	public Integer getRoom_type_id() {
		return room_type_id;
	}

	public void setRoom_type_id(Integer room_type_id) {
		this.room_type_id = room_type_id;
	}

	public Timestamp getReservation_date() {
		return reservation_date;
	}

	public void setReservation_date(Timestamp reservation_date) {
		this.reservation_date = reservation_date;
	}

	public Integer getRoom_type_amount() {
		return room_type_amount;
	}

	public void setRoom_type_amount(Integer room_type_amount) {
		this.room_type_amount = room_type_amount;
	}

	public Integer getReservation_amount() {
		return reservation_amount;
	}

	public void setReservation_amount(Integer reservation_amount) {
		this.reservation_amount = reservation_amount;
	}

}
