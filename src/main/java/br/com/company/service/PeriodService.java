package br.com.company.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PeriodService {

	/**
	 * VAR String
	 */
	private String format = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 
	 */
	private SimpleDateFormat simpleDateFormat;

	/**
	 * 
	 */
	private Calendar calendar;

	/**
	 * 
	 */
	public PeriodService() {
		this.make();
	}

	/**
	 * @param String
	 *            format
	 */
	public PeriodService(String format) {
		this.format = format;
		this.make();
	}

	/**
	 * @param String
	 *            format
	 * @return
	 */
	public PeriodService setFormat(String format) {
		this.format = format;
		this.make();
		return this;
	}

	/**
	 * Create
	 */
	private void make() {
		this.simpleDateFormat = new SimpleDateFormat(this.format);
		this.calendar = Calendar.getInstance();
	}

	/**
	 * @return String
	 */
	public String getTimeNow() {
		return this.simpleDateFormat.format(new Date());
	}

	/**
	 * @param int
	 *            hour
	 * @return String
	 */
	public String getTimeByHour(int hour) {
		this.calendar.setTime(new Date());
		this.calendar.add(Calendar.HOUR_OF_DAY, hour);
		return simpleDateFormat.format(this.calendar.getTime());
	}

	/**
	 * @param int
	 *            day
	 * @return String
	 */
	public String getTimeByDay(int day) {
		this.calendar.setTime(new Date());
		this.calendar.add(Calendar.DAY_OF_MONTH, day);
		return simpleDateFormat.format(this.calendar.getTime());
	}

	/**
	 * @param int
	 *            month
	 * @return String
	 */
	public String getTimeByMonth(int month) {
		this.calendar.setTime(new Date());
		this.calendar.add(Calendar.MONTH, month);
		return simpleDateFormat.format(this.calendar.getTime());
	}

	/**
	 * 
	 * @param fromHour
	 * @param toHour
	 * @return
	 */
	public boolean isChangeDay(int fromHour, int toHour) {
		return (Integer.valueOf(this.setFormat("HH").getTimeByHour(fromHour)) > Integer
				.valueOf(this.setFormat("HH").getTimeByHour(toHour)));
	}

}
