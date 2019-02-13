package application;

import java.util.Date;

public class DayMonthYear implements Comparable<DayMonthYear>{

	private int day;
	private int month;
	private int year;

	public static DayMonthYear today() {
		
		Date today = new Date();
		return new DayMonthYear(today.getDay(), today.getMonth(), today.getYear());
	}
	
	public DayMonthYear(int day, int month, int year) {
		
		if(day > 31 || day < 0 || month > 12 || month < 0 || year > 2020 || year < 0) {
			throw new IllegalStateException("Date is out of reasonable range.");
		}
		
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	public int getDay() {
		return day;
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getYear() {
		return year;
	}
	
	public boolean before(DayMonthYear other) {

		return this.compareTo(other) < 0;
	}
	
	public boolean afterOrSame(DayMonthYear other) {
		return !before(other);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		if(month < 10) {
			sb.append("0");
			sb.append(month);
			sb.append("/");
		}
		else {
			sb.append(month);
			sb.append("/");
		}
		
		if(day < 10) {
			sb.append("0");
			sb.append(day);
			sb.append("/");
		}
		else {
			sb.append(day);
			sb.append("/");
		}
		
		// Yes I assume the year occurs after 999...
		sb.append(year);
		
		return sb.toString();
	}
	
	@Override
	public int compareTo(DayMonthYear otherDMY) {
		
		if(this.year > otherDMY.year) {
			return 1;
		}
		else if(this.year < otherDMY.year) {
			return -1;
		}
		else if(this.month > otherDMY.month) {
			return 1;
		}
		else if(this.month < otherDMY.month) {
			return -1;
		}
		else if(this.day > otherDMY.day) {
			return 1;
		}
		else if(this.day < otherDMY.day) {
			return -1;
		}
		else return 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(this == obj) return true;							
		
		return compareTo((DayMonthYear)obj) == 0;
	}
	
	@Override
	public int hashCode() {
		
		int hashSeed = 113;
		hashSeed *= year;
		hashSeed -= 17;
		
		hashSeed *= month;
		hashSeed -= 23;
		
		hashSeed *= day;
		hashSeed -= 43;
		
		return hashSeed;
	}
}
