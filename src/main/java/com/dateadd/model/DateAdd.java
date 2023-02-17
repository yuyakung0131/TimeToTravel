package com.dateadd.model;

import java.sql.Timestamp;
import java.util.Calendar;

public class DateAdd {
    public static Timestamp addDays(Timestamp date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);// w ww.  j ava  2  s  .co m
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return new Timestamp(cal.getTime().getTime());

    }
    
    public static void main(String[] args) {
		DateAdd main=new DateAdd();
		Timestamp rs=Timestamp.valueOf("2020-01-01 00:00:00");
		for(int i=0;i<=2;i++) {
			System.out.println(rs);
		 rs=main.addDays(rs,1);
		
		}
	}
}
