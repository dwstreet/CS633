package application;

import java.util.List;
import java.util.Map;

public class Restaurant {

	private String name;
	private String phone;
	private Map<DayMonthYear, WorkingDay> schedule;

	public Restaurant(String name, String phone, Map<DayMonthYear, WorkingDay> schedule) {

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

	public Map<DayMonthYear, WorkingDay> getSchedule() {
		return schedule;
	}

	public boolean makeReservation(String partyName, int partyNumber, int year, int month, int day, DayTime mealTime) {

		DayMonthYear date = new DayMonthYear(year, month, day);
		
		return makeReservation(partyName, partyNumber, date, mealTime);
	}

	public boolean makeReservation(String partyName, int partyNumber, DayMonthYear date, DayTime mealTime) {

		// if the restaurant is not open can't make a reservation... I dunno how this realistically happens in the code but lets prevent that case
		if(!schedule.containsKey(date)) {
			return false;
		}
		else {
			WorkingDay day = schedule.get(date);
			
			if(day.canBook(partyName, partyNumber, mealTime)) {
				day.makeReservation(partyName, partyNumber, mealTime);
				return true;
			}
			else {
				return false;
			}
		}
	}

	public boolean addWorkingDay(int year, int month, int day, List<Shift> shifts, List<Reservation> reservations) {

		return addWorkingDay(new DayMonthYear(day, month, year), shifts, reservations);
	}

	public boolean addWorkingDay(DayMonthYear date, List<Shift> shifts, List<Reservation> reservations) {

		//This information should be passed upwards to the GUI. 
		
		if(schedule.containsKey(date) || date.before(DayMonthYear.today())) {
			
			return false;
//			throw new RuntimeException("This date cannot be added because a working day already exists or the day "
//					+ "scheduled occurs before today.");
		}

		schedule.put(date, new WorkingDay(shifts, reservations));
		return true;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
