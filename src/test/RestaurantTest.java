package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import application.DayMonthYear;
import application.DayTime;
import application.MealTime;
import application.Reservation;
import application.Restaurant;
import application.Shift;
import application.User;
import application.WorkingDay;

public class RestaurantTest {

	List<Reservation> reservations = new ArrayList<>();
	Reservation reservation1, reservation2;
	Restaurant rest=null;
	User testUser1;
	User testUser2;
	DayMonthYear day;
	DayTime resvTime = new DayTime(18, 0);
	Shift nightShift;
	
	@Before
	public void setup(){
		day = new DayMonthYear(3, 3, 2019);
		
		List<Shift> shifts = new ArrayList<>();
		Map<DayMonthYear, WorkingDay> schedule = new HashMap<>();
		nightShift =new Shift(new MealTime(new DayTime(18, 0), new DayTime(19, 30)), 5, 5);
		shifts.add(nightShift);
		testUser1 = new User("Jamie", "pass", true);
		testUser2 = new User("Ricardo", "pass", true);
		reservation1 = new Reservation(testUser1, "Jamie", 2, day, new DayTime(18, 0),"");
		reservation2 = new Reservation(testUser2, "Ricardo", 4, day, new DayTime(20, 0), "");
		reservations.add(reservation1);
		reservations.add(reservation2);

		rest = new Restaurant("Pizza Shop", "123-456-7890", schedule, 5);
		rest.addWorkingDay(day, shifts, reservations);
	}
	
	@Test
	public void testGetName() {
		assertEquals(rest.getName(),"Pizza Shop");;
	}

	@Test
	public void testGetPhone() {
		assertEquals(rest.getPhone(),"123-456-7890");
	}

	@Test
	public void testGetSchedule() {
		Map<DayMonthYear, WorkingDay> sched = rest.getSchedule();
		WorkingDay workDay = sched.get(day);
		assertEquals(workDay.getReservations().size(),2);
		assertEquals(workDay.getAvailableTables(nightShift),5);
	}

	@Test
	public void testGetTableSeats() {
		assertEquals(rest.getTableSeats(),5);
	}

	@Test
	public void testCancelReservation() {
		rest.cancelReservation(reservation1);
		assertEquals(rest.getAllReservations().size(),1);
		rest.makeReservation(testUser1, "Jamie", 2, day, new DayTime(18, 0),"");
	}


	@Test
	public void testGetAllUserReservations() {
		List<Reservation> reservations = rest.getAllUserReservations(testUser1);
		Reservation firstResv = reservations.get(0);
		assertEquals(firstResv.getPartyName(),"Jamie");
		assertEquals(firstResv.getPartyNumber(),2);
		assertEquals(firstResv.getDayMonthYear(),day);
		assertEquals(firstResv.getSeatTime(),resvTime);

	}

}
