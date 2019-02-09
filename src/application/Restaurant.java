package application;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class Restaurant {

	private String name;
	private String phone;
	private Map<Date, WorkingDay> schedule;
	
	
	public Restaurant(String name, String phone, int floatingSeats, int stableSeats, Map<Date, WorkingDay> schedule) {
		
		this.name = name;
		this.phone = phone;
		this.schedule = schedule;
	}
		
	public String getName() {
		return name;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public Map<Date, WorkingDay> getSchedule(){
		return schedule;
	}
	
	public Reservation makeReservation() {
		
		return new Reservation(0, 0, 0);
	}
	
	private class Reservation {
		
		private WorkingDay date;
		private int floatingSeats;
		private int stableSeats;
		
		Reservation(int year, int month, int day) {
			
			
			
		}
		
		public int getFloatingSeats() {
			return floatingSeats;
		}
		
		public int getStableSeats() {
			return stableSeats;
		}
		
		public boolean canBook(int partyOf) {
			
			return partyOf < (floatingSeats + stableSeats);
		}
		
		public void bookParty(Reservation resv, int partyOf) {
			
		}
	}
	
	private static class WorkingDay {
		
		private ArrayList<Reservation> reservations;
		
		@SuppressWarnings("deprecation")
		private WorkingDay() {
			reservations = new ArrayList<Reservation>();
		}
		
		static ArrayList<Reservation> getReservations(int year, int month, int day) {
			
				
			
			
			return null;
		}
	}
}
