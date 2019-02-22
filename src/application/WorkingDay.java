package application;

import java.util.ArrayList;
import java.util.List;

public class WorkingDay {

	private DayMonthYear dmy;
	private List<Shift> shifts;
	private List<Reservation> reservations;
	private int regularTableSeats;

	public WorkingDay(int regularTableSeats) {

		dmy = DayMonthYear.today();
		shifts = new ArrayList<>();
		reservations = new ArrayList<>();
		this.regularTableSeats = regularTableSeats;
	}
	
	public WorkingDay(DayMonthYear dmy, List<Shift> mealTimes, List<Reservation> reservations, int regularTableSeats) {
		
		this.dmy = dmy;
		this.shifts = mealTimes;
		this.reservations = reservations;
		this.regularTableSeats = regularTableSeats;
	}

	public void makeShift(DayTime startTime, DayTime endTime, int floatingSeats, int numTables) {
		shifts.add(new Shift(new MealTime(startTime, endTime), floatingSeats, numTables));
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
	
	public DayMonthYear getDayMonthYear() {
		return dmy;
	}

	// This may be used by other screens
	public List<Reservation> getReservations() {

		return reservations;
	}

	// This may be used by other screens
	public int getFloatingSeats(Shift time) {
		return time.getFloatingSeats();
	}

	// This may be used by other screens
	public int getAvailableTables(Shift time) {
		return time.getNumTables();
	}

	public boolean canBook(User user, String partyName, int partyOf, DayTime seatTime) {
		
		Shift shift = findShift(seatTime);
		
		// Ensure the party size is small enough and not already booked
		return partyOf <= (shift.getFloatingSeats() + regularTableSeats) && !reservations.contains(new Reservation(user, partyName, partyOf, dmy, seatTime, ""));
	}
	
	public void makeReservation(User user, String partyName, int partyNumber, DayTime seatTime, String notes) {

		Shift shift = findShift(seatTime);
		shift.bookSeats(partyNumber - regularTableSeats);
		reservations.add(new Reservation(user, partyName, partyNumber, dmy, seatTime, notes));
	}
	
	public void removeReservation(Reservation resv) {
		
		reservations.remove(resv);
		Shift shift = findShift(resv.getSeatTime());
		shift.clearSeats(resv.getPartyNumber() - regularTableSeats);
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
