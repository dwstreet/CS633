package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import application.DayMonthYear;
import application.DayTime;
import application.MealTime;
import application.Reservation;
import application.Restaurant;
import application.Shift;
import application.User;
import application.WorkingDay;

public class ReservationTest {

	List<Reservation> reservations = new ArrayList<>();
	Reservation reservation1, reservation2;
	Restaurant rest=null;
	
	@Before
	public void setup(){
		DayMonthYear day = new DayMonthYear(3, 3, 2019);

		User testUser1 = new User("Jamie", "pass", false);
		User testUser2 = new User("Ricardo", "pass", false);
		reservation1 = new Reservation(testUser1, "Jamie", 2, day, new DayTime(18, 0),"");
		reservation2 = new Reservation(testUser2, "Ricardo", 4, day, new DayTime(20, 0), "");
		reservations.add(reservation1);
		reservations.add(reservation2);
	}
	
	@Test
	public void testGetUser() {
		User user1 = reservation1.getUser();
		assertEquals(user1.getUserName(),"Jamie");
		assertEquals(user1.getIsAdmin(),false);
	}

	@Test
	public void testGetPartyName() {
		assertEquals(reservation2.getPartyName(),"Ricardo");
	}

	@Test
	public void testGetPartyNumber() {
		assertEquals(reservation1.getPartyNumber(),2);
		assertEquals(reservation2.getPartyNumber(),4);
	}

	@Test
	public void testGetDayMonthYear() {
		DayMonthYear resvDate = reservation1.getDayMonthYear();
		assertEquals(resvDate.getDay(),3);
		assertEquals(resvDate.getMonth(),3);
		assertEquals(resvDate.getYear(),2019);
	}

	@Test
	public void testGetSeatTime() {
		DayTime seatTime = reservation1.getSeatTime();
		DayTime actualSeatTime = new DayTime(18, 0);
		assertEquals(seatTime,actualSeatTime);
	}

}
