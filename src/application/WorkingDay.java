package application;

import java.util.ArrayList;
import java.util.List;

public class WorkingDay {

	private List<Shift> shifts;
	private List<Reservation> reservations;

	public WorkingDay() {

		shifts = new ArrayList<>();
		reservations = new ArrayList<>();
	}
	
	public WorkingDay(List<Shift> mealTimes, List<Reservation> reservations) {
		
		this.shifts = mealTimes;
		this.reservations = reservations;
	}

	public void makeShift(DayTime startTime, DayTime endTime, int floatingSeats, int stableSeats) {
		shifts.add(new Shift(new MealTime(startTime, endTime), floatingSeats, stableSeats));
	}
	
	public boolean isClosed() {
		return shifts.isEmpty();
	}
	
	public List<Shift> getShifts() {
		return shifts;
	}
	
	public boolean hasShifts() {
		return !shifts.isEmpty();
	}
	
	public void makeReservation(String partyName, int partyNumber, DayTime seatTime) {

		reservations.add(new Reservation(partyName, partyNumber, seatTime));		
	}

	// This may be used by other screens
	public List<Reservation> getReservations(int year, int month, int day) {

		return reservations;
	}

	// This may be used by other screens
	public int getFloatingSeats(Shift time) {
		return time.getFloatingSeats();
	}

	// This may be used by other screens
	public int getStableSeats(Shift time) {
		return time.getStableSeats();
	}

	public boolean canBook(String partyName, int partyOf, DayTime seatTime) {
		
		Shift shift = findShift(seatTime);

		// Ensure the party size is small enough and not already booked
		return partyOf < (shift.getFloatingSeats() + shift.getStableSeats()) && !reservations.contains(new Reservation(partyName, partyOf, seatTime));
	}
	
	private Shift findShift(DayTime seatTime) {
		
		// pre-sort to avoid odd issues
		// this is generally efficient because a mostly sorted list completes on average extremely quickly
		// compared to general unsorted case.
		shifts.sort(null);
		
		Shift shift = null;
		for(Shift s : shifts) {
			
			DayTime startTime = s.getMealTime().getStartTime();
			// if the shift starts on after the seat time
			if(startTime.compareTo(seatTime) >= 0) {
				// if the time is no more that 15 minutes afterwards
				if(startTime.minutesFrom(seatTime) <= 15) {
					shift = s;
				}
			}
		}
		
		if(shift != null) {
			return shift;
		}
		else {
			// no available shift nearby return empty
			return new Shift(new MealTime(DayTime.makeMidnight(), DayTime.makeMidnight()), 0, 0);
		}
	}
}
