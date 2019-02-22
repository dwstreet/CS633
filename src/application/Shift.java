package application;

public class Shift implements Comparable<Shift> {

	private MealTime mealTime;
	private int floatingSeats;
	private int numTables;

	public Shift(MealTime timespan, int floatingSeats, int numTables) {
		this.mealTime = timespan;
		this.floatingSeats = floatingSeats;
		this.numTables = numTables;
	}

	public MealTime getMealTime() {
		return mealTime;
	}

	public int getFloatingSeats() {
		return floatingSeats;
	}

	public int getNumTables() {
		return numTables;
	}
	
	public boolean modifySeats(int floatingSeats, int numTables) {
		
		// they will pass in negatives to subtract
		if(this.floatingSeats + floatingSeats < 0 || this.numTables + numTables < 0) {
			return false;
		}
		else {
			this.floatingSeats += floatingSeats;
			this.numTables += numTables;
			return true;
		}
	}

	public void bookSeats(int overFlowSeats) {
		if(overFlowSeats > 0) {
			floatingSeats -= overFlowSeats;
		}
		numTables -= 1;
	}
	
	public void clearSeats(int overFlowSeats) {
		if(overFlowSeats > 0) {
			floatingSeats += overFlowSeats;
		}
		numTables += 1;
	}

	@Override
	public int compareTo(Shift o) {

		return mealTime.getStartTime().compareTo(o.mealTime.getStartTime());
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(mealTime)
		.append("  ")
		.append(numTables)
		.append(" open tables ")
		.append(floatingSeats)
		.append(" floating seats");

		return sb.toString();
	}
}
