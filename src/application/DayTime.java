package application;

public class DayTime implements Comparable<DayTime> {
	
	private int hour;
	private int minute;
	
	public static DayTime makeMidnight() {
		return new DayTime(0, 0);
	}
	
	public DayTime(int hour, int minute) {
		if(hour < 0 || hour > 24) {
			throw new IllegalStateException("Illegal hour supplied, was: " + hour);
		}
		if(minute < 0 || minute > 60) {
			throw new IllegalStateException("Illegal minute supplied, was: " + minute);
		}
		
		this.hour = hour;
		this.minute = minute;
	}
	
	public int hoursFrom(DayTime otherTime) {
		return this.hour - otherTime.hour;
	}
	
	public int minutesFrom(DayTime otherTime) {
		
		return ((this.hour * 60) + this.minute) - ((otherTime.hour * 60) + otherTime.minute);
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(hour % 12);
		sb.append(":");
		sb.append(minute);
		if(hour > 12) {
			sb.append("PM");
		}
		else {
			sb.append("AM");
		}
		
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(this == obj) return true;							
		
		return compareTo((DayTime)obj) == 0;
	}
	
	@Override
	public int hashCode() {
		
		int hashSeed = 173;
		hashSeed *= hour;
		hashSeed -= 41;
		
		hashSeed *= minute;
		hashSeed -= 13;
		
		return hashSeed;
	}

	@Override
	public int compareTo(DayTime otherTime) {
		
		if(this.hour > otherTime.hour) {
			return 1;
		}
		else if(this.hour < otherTime.hour) {
			return -1;
		}
		else if(this.minute > otherTime.minute) {
			return 1;
		}
		else if(this.minute < otherTime.minute) {
			return -1;
		}
		else return 0;
	}
}