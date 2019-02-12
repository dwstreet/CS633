package application;

public class Shift implements Comparable<Shift> {

	private MealTime mealTime;
	private int floatingSeats;
	private int stableSeats;
	
	public Shift(MealTime timespan, int floatingSeats, int stableSeats) {
		this.mealTime = timespan;
		this.floatingSeats = floatingSeats;
		this.stableSeats = stableSeats;
	}
	
	public MealTime getMealTime() {
		return mealTime;
	}
	
	public int getFloatingSeats() {
		return floatingSeats;
	}

	public int getStableSeats() {
		return stableSeats;
	}
	
	public void bookSeats(int floating, int stable) {
		floatingSeats -= floating;
		stableSeats -= stable;
	}

	@Override
	public int compareTo(Shift o) {
		
		return mealTime.getStartTime().compareTo(o.mealTime.getStartTime());
	}

	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(mealTime);
		sb.append("  ");
		sb.append(floatingSeats + stableSeats);
		sb.append(" open seats");
		
		return sb.toString();
	}
}
