package com.zm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

//格式转换类
public class ConvertUtil {

	/* 将LocalDate转换成Date */
	public static Date localDate2Date(LocalDate localDate) {
		if (null == localDate) {
			return null;
		}

		ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
		return Date.from(zonedDateTime.toInstant());
	}

	/* 将Date转换成LocalDate */
	public static LocalDate date2LocalDate(Date date) {
		if (null == date) {
			return null;
		}
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	/*
	 * sql.Date---》util.Date 自动转换
	 * 
	 * sql.Date与util.Date的转换 util.Date---》sql.Date 方法转换 
	 */
	public static java.sql.Date util2sql(Date date) {
		long time = date.getTime();
		return new java.sql.Date(time);
	}
	
	/*
	 * util.Date与String 之间的转换
	 * */
	public static Date str2Date(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date parse=null;
		try {
			parse = sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parse;
	}
	
	   public static String date2Str(Date date){
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        return sdf.format(date);
	    }

	
	/*计算当前日期N天之后的日期*/
	public static String datePlus(Date date,int days) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		Calendar c = Calendar.getInstance();  
		c.setTime(date);  
		c.add(Calendar.DATE, days);  
		String addDate = sdf.format(c.getTime());
	    return addDate;
	}

}
