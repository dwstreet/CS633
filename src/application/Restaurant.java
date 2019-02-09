package application;

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

	public Map<Date, WorkingDay> getSchedule() {
		return schedule;
	}

	public boolean makeReservation(String partyName, int partyNumber, int year, int month, int day) {

		@SuppressWarnings("deprecation")
		Date date = new Date(year, month, day);
		
		return makeReservation(partyName, partyNumber, date);
	}

	public boolean makeReservation(String partyName, int partyNumber, Date date) {

		// if the restaurant is not open can't make a reservation... I dunno how this realistically happens in the code but lets prevent that case
		if(!schedule.containsKey(date)) {
			return false;
		}
		else {
			WorkingDay day = schedule.get(date);
			
			if(day.canBook(partyName, partyNumber)) {
				day.makeReservation(partyName, partyNumber);
				return true;
			}
			else {
				return false;
			}
		}
	}

	public boolean addWorkingDay(int year, int month, int day, int floatingSeats, int stableSeats) {

		@SuppressWarnings("deprecation")
		Date date = new Date(year, month, day);

		return addWorkingDay(date, floatingSeats, stableSeats);
	}

	public boolean addWorkingDay(Date date, int floatingSeats, int stableSeats) {

		//This information should be passed upwards to the GUI. 
		
		if(schedule.containsKey(date) || date.before(new Date())) {
			
			return false;
//			throw new RuntimeException("This date cannot be added because a working day already exists or the day "
//					+ "scheduled occurs before today.");
		}

		schedule.put(date, new WorkingDay(floatingSeats, stableSeats));
		return true;
	}
}
