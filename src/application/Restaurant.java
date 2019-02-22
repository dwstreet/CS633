package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Restaurant {

	private String name;
	private String phone;
	private Map<DayMonthYear, WorkingDay> schedule;
	private int tableSeats;

	public Restaurant(String name, String phone, Map<DayMonthYear, WorkingDay> schedule, int tableSeats) {

		this.name = name;
		this.phone = phone;
		this.schedule = schedule;
		this.tableSeats = tableSeats;
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

	public int getTableSeats() {
		return tableSeats;
	}
	
	public boolean makeReservation(User user, String partyName, int partyNumber, int year, int month, int day, DayTime mealTime, String notes) {

		DayMonthYear date = new DayMonthYear(year, month, day);
		
		return makeReservation(user, partyName, partyNumber, date, mealTime, notes);
	}

	public boolean makeReservation(User user, String partyName, int partyNumber, DayMonthYear date, DayTime mealTime, String notes) {

		// if the restaurant is not open can't make a reservation... I dunno how this realistically happens in the code but lets prevent that case
		if(!schedule.containsKey(date)) {
			return false;
		}
		else {
			WorkingDay day = schedule.get(date);
			
			if(day.canBook(user, partyName, partyNumber, mealTime)) {
				day.makeReservation(user, partyName, partyNumber, mealTime, notes);
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	public void cancelReservation(Reservation resv) {
		
		WorkingDay workDay = schedule.get(resv.getDayMonthYear());
		workDay.removeReservation(resv);
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

		schedule.put(date, new WorkingDay(date, shifts, reservations, tableSeats));
		return true;
	}
	
	public List<Reservation> getAllReservations() {

		List<Reservation> reservations = new ArrayList<>();
		for(Entry<DayMonthYear, WorkingDay> entry : schedule.entrySet()) {
			reservations.addAll(entry.getValue().getReservations());
		}
		
		return reservations;
	}
	
	public List<Reservation> getAllUserReservations(User user) {

		return getAllReservations().stream().filter(reservation -> reservation.getUser().equals(user)).collect(Collectors.toList());
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
