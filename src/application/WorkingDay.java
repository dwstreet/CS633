package application;

import java.util.ArrayList;
import java.util.List;

public class WorkingDay {

	private int floatingSeats;
	private int stableSeats;
	private List<Reservation> reservations;

	public WorkingDay(int floatingSeats, int stableSeats) {

		this.floatingSeats = floatingSeats;
		this.stableSeats = stableSeats;
		reservations = new ArrayList<>();
	}

	public void makeReservation(String partyName, int partyNumber) {
		
		reservations.add(new Reservation(partyName, partyNumber));		
	}

	// This may be used by other screens
	public List<Reservation> getReservations(int year, int month, int day) {

		return reservations;
	}
	
	// This may be used by other screens
	public int getFloatingSeats() {
		return floatingSeats;
	}
	
	// This may be used by other screens
	public int getStableSeats() {
		return stableSeats;
	}

	public boolean canBook(String partyName, int partyOf) {

		// Ensure the party size is small enough and not already booked
		return partyOf < (floatingSeats + stableSeats) && !reservations.contains(new Reservation(partyName, partyOf));
	}
}
