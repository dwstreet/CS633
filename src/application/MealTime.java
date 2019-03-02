package application;

public class MealTime {
	
	private DayTime startTime;
	private DayTime endTime;
	
	public MealTime(DayTime startTime, DayTime endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public DayTime getStartTime() {
		return startTime;
	}
	
	public DayTime getEndTime() {
		return endTime;
	}
	
	@Override
	public String toString() {
		return startTime + "-" + endTime;
	}
}