package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import gui.AddShiftScreen;
import gui.AvailabilityScreen;
import gui.CreateReservationScreen;
import gui.ManageReservationScreen;
import gui.ManageRestaurantsScreen;
import gui.Screen;
import gui.SignInScreen;
import gui.UpdateShiftScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {

	private static boolean loggedIn = false;
	private static User loggedInUser = null;
	private static Map<String, User> allUsers = new HashMap<>();
	private static List<Restaurant> restaurantList = new ArrayList<>();
	private static BorderPane root = new BorderPane();
	private static Stack<Screen> screenStack = new Stack<>();

	private static SignInScreen signIn;
	private static AvailabilityScreen availability;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {

		try {		

			buildAppData();

			Scene initial = new Scene(root, 600, 600);

			// not actually used
			// initial.getStylesheets().add(getClass().getResource("../gui/application.css").toExternalForm());

			// Setting up sign in
			signIn = new SignInScreen();
			availability = new AvailabilityScreen();

			screenStack.push(signIn);
			changeScreen(signIn);

			stage.setTitle("Welcome");
			stage.setScene(initial);
			stage.show();
			stage.centerOnScreen();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void buildAppData() {
		
		// Add users
		allUsers.put("Admin", new User("Admin", "admin", true));
		allUsers.put("User", new User("User", "123", false));
		allUsers.put("Dan", new User("Dan", "pass", false));
		allUsers.put("Ricardo", new User("Ricardo", "ricardo", false));
		allUsers.put("Jamie", new User("Jamie", "jamie", false));
		allUsers.put("Anthony", new User("Anthony", "anthony", false));
		
		// Restaurants
		Restaurant burgerJoint = new Restaurant("Burger Joint", "123-456-7890",  new HashMap<>(), 4);
		Restaurant titosTaco = new Restaurant("Tito's Tacos", "098-765-4321",  new HashMap<>(), 2);
		Restaurant superSubs = new Restaurant("Super Subs", "555-666-7890",  new HashMap<>(), 3);

		restaurantList.add(burgerJoint);
		restaurantList.add(titosTaco);
		restaurantList.add(superSubs);
		
		// Days
		DayMonthYear marchThird = new DayMonthYear(3, 3, 2019);
		DayMonthYear marchFourth = new DayMonthYear(4, 3, 2019);
		DayMonthYear marchFifth = new DayMonthYear(5, 3, 2019);
		DayMonthYear marchSixth = new DayMonthYear(6, 3, 2019);
		DayMonthYear marchSeventh = new DayMonthYear(7, 3, 2019);

		// Burger Shifts & Reservations
		List<Shift> burgerShifts3 = new ArrayList<>();
		burgerShifts3.add(new Shift(new MealTime(new DayTime(18, 0), new DayTime(18, 30)), 5, 5));
		burgerShifts3.add(new Shift(new MealTime(new DayTime(18, 30), new DayTime(19, 0)), 5, 5));
		burgerShifts3.add(new Shift(new MealTime(new DayTime(19, 0), new DayTime(19, 30)), 5, 5));	
		
		List<Shift> burgerShifts4 = new ArrayList<>();
		burgerShifts4.add(new Shift(new MealTime(new DayTime(18, 0), new DayTime(18, 30)), 5, 5));
		burgerShifts4.add(new Shift(new MealTime(new DayTime(18, 30), new DayTime(19, 0)), 5, 5));
		burgerShifts4.add(new Shift(new MealTime(new DayTime(19, 0), new DayTime(19, 30)), 5, 5));	
		
		List<Shift> burgerShifts5 = new ArrayList<>();
		burgerShifts5.add(new Shift(new MealTime(new DayTime(18, 0), new DayTime(18, 30)), 5, 5));
		burgerShifts5.add(new Shift(new MealTime(new DayTime(18, 30), new DayTime(19, 0)), 5, 5));
		burgerShifts5.add(new Shift(new MealTime(new DayTime(19, 0), new DayTime(19, 30)), 5, 5));	
		
		List<Shift> burgerShifts6 = new ArrayList<>();
		burgerShifts6.add(new Shift(new MealTime(new DayTime(18, 0), new DayTime(18, 30)), 5, 5));
		burgerShifts6.add(new Shift(new MealTime(new DayTime(18, 30), new DayTime(19, 0)), 5, 5));
		burgerShifts6.add(new Shift(new MealTime(new DayTime(19, 0), new DayTime(19, 30)), 5, 5));	
		
		List<Shift> burgerShifts7 = new ArrayList<>();
		burgerShifts7.add(new Shift(new MealTime(new DayTime(18, 0), new DayTime(18, 30)), 5, 5));
		burgerShifts7.add(new Shift(new MealTime(new DayTime(18, 30), new DayTime(19, 0)), 5, 5));
		burgerShifts7.add(new Shift(new MealTime(new DayTime(19, 0), new DayTime(19, 30)), 5, 5));		
		
		burgerJoint.addWorkingDay(marchThird, burgerShifts3, new ArrayList<>());
		burgerJoint.addWorkingDay(marchFourth, burgerShifts4, new ArrayList<>());
		burgerJoint.addWorkingDay(marchFifth, burgerShifts5, new ArrayList<>());
		burgerJoint.addWorkingDay(marchSixth, burgerShifts6, new ArrayList<>());
		burgerJoint.addWorkingDay(marchSeventh, burgerShifts7, new ArrayList<>());
	
		burgerJoint.makeReservation(allUsers.get("Dan"), "Dan", 3, marchThird, new DayTime(18, 0), "");
		
		// Taco Shifts & Reservations
		List<Shift> tacoShifts3 = new ArrayList<>();
		tacoShifts3.add(new Shift(new MealTime(new DayTime(12, 0), new DayTime(12, 30)), 6, 3));
		tacoShifts3.add(new Shift(new MealTime(new DayTime(12, 30), new DayTime(13, 0)), 6, 3));
		tacoShifts3.add(new Shift(new MealTime(new DayTime(13, 0), new DayTime(13, 30)), 6, 3));
		tacoShifts3.add(new Shift(new MealTime(new DayTime(13, 30), new DayTime(14, 0)), 6, 3));
		tacoShifts3.add(new Shift(new MealTime(new DayTime(14, 0), new DayTime(14, 30)), 6, 3));	
		
		List<Shift> tacoShifts4 = new ArrayList<>();
		tacoShifts4.add(new Shift(new MealTime(new DayTime(12, 0), new DayTime(12, 30)), 6, 3));
		tacoShifts4.add(new Shift(new MealTime(new DayTime(12, 30), new DayTime(13, 0)), 6, 3));
		tacoShifts4.add(new Shift(new MealTime(new DayTime(13, 0), new DayTime(13, 30)), 6, 3));
		tacoShifts4.add(new Shift(new MealTime(new DayTime(13, 30), new DayTime(14, 0)), 6, 3));
		tacoShifts4.add(new Shift(new MealTime(new DayTime(14, 0), new DayTime(14, 30)), 6, 3));
		
		List<Shift> tacoShifts5 = new ArrayList<>();
		tacoShifts5.add(new Shift(new MealTime(new DayTime(12, 0), new DayTime(12, 30)), 6, 3));
		tacoShifts5.add(new Shift(new MealTime(new DayTime(12, 30), new DayTime(13, 0)), 6, 3));
		tacoShifts5.add(new Shift(new MealTime(new DayTime(13, 0), new DayTime(13, 30)), 6, 3));
		tacoShifts5.add(new Shift(new MealTime(new DayTime(13, 30), new DayTime(14, 0)), 6, 3));
		tacoShifts5.add(new Shift(new MealTime(new DayTime(14, 0), new DayTime(14, 30)), 6, 3));	
		
		List<Shift> tacoShifts6 = new ArrayList<>();
		tacoShifts6.add(new Shift(new MealTime(new DayTime(12, 0), new DayTime(12, 30)), 6, 3));
		tacoShifts6.add(new Shift(new MealTime(new DayTime(12, 30), new DayTime(13, 0)), 6, 3));
		tacoShifts6.add(new Shift(new MealTime(new DayTime(13, 0), new DayTime(13, 30)), 6, 3));
		tacoShifts6.add(new Shift(new MealTime(new DayTime(13, 30), new DayTime(14, 0)), 6, 3));
		tacoShifts6.add(new Shift(new MealTime(new DayTime(14, 0), new DayTime(14, 30)), 6, 3));	
		
		List<Shift> tacoShifts7 = new ArrayList<>();
		tacoShifts7.add(new Shift(new MealTime(new DayTime(12, 0), new DayTime(12, 30)), 6, 3));
		tacoShifts7.add(new Shift(new MealTime(new DayTime(12, 30), new DayTime(13, 0)), 6, 3));
		tacoShifts7.add(new Shift(new MealTime(new DayTime(13, 0), new DayTime(13, 30)), 6, 3));
		tacoShifts7.add(new Shift(new MealTime(new DayTime(13, 30), new DayTime(14, 0)), 6, 3));
		tacoShifts7.add(new Shift(new MealTime(new DayTime(14, 0), new DayTime(14, 30)), 6, 3));		
		
		titosTaco.addWorkingDay(marchThird, tacoShifts3, new ArrayList<>());
		titosTaco.addWorkingDay(marchFourth, tacoShifts4, new ArrayList<>());
		titosTaco.addWorkingDay(marchFifth, tacoShifts5, new ArrayList<>());
		titosTaco.addWorkingDay(marchSixth, tacoShifts6, new ArrayList<>());
		titosTaco.addWorkingDay(marchSeventh, tacoShifts7, new ArrayList<>());
	
		titosTaco.makeReservation(allUsers.get("User"), "User", 6, marchFourth, new DayTime(13, 0), "");
		titosTaco.makeReservation(allUsers.get("Ricardo"), "Ricardo", 2, marchFifth, new DayTime(13, 30), "");
		
		// Sub Shifts & Reservations
		List<Shift> subShift3 = new ArrayList<>();
		subShift3.add(new Shift(new MealTime(new DayTime(12, 0), new DayTime(13, 0)), 2, 8));
		subShift3.add(new Shift(new MealTime(new DayTime(13, 0), new DayTime(14, 0)), 2, 8));
		subShift3.add(new Shift(new MealTime(new DayTime(14, 0), new DayTime(15, 0)), 2, 8));
		subShift3.add(new Shift(new MealTime(new DayTime(15, 0), new DayTime(16, 0)), 2, 8));
		subShift3.add(new Shift(new MealTime(new DayTime(16, 0), new DayTime(17, 0)), 2, 8));
		
		List<Shift> subShift4 = new ArrayList<>();
		subShift4.add(new Shift(new MealTime(new DayTime(12, 0), new DayTime(13, 0)), 2, 8));
		subShift4.add(new Shift(new MealTime(new DayTime(13, 0), new DayTime(14, 0)), 2, 8));
		subShift4.add(new Shift(new MealTime(new DayTime(14, 0), new DayTime(15, 0)), 2, 8));
		subShift4.add(new Shift(new MealTime(new DayTime(15, 0), new DayTime(16, 0)), 2, 8));
		subShift4.add(new Shift(new MealTime(new DayTime(16, 0), new DayTime(17, 0)), 2, 8));
		
		List<Shift> subShift5 = new ArrayList<>();
		subShift5.add(new Shift(new MealTime(new DayTime(12, 0), new DayTime(13, 0)), 2, 8));
		subShift5.add(new Shift(new MealTime(new DayTime(13, 0), new DayTime(14, 0)), 2, 8));
		subShift5.add(new Shift(new MealTime(new DayTime(14, 0), new DayTime(15, 0)), 2, 8));
		subShift5.add(new Shift(new MealTime(new DayTime(15, 0), new DayTime(16, 0)), 2, 8));
		subShift5.add(new Shift(new MealTime(new DayTime(16, 0), new DayTime(17, 0)), 2, 8));
		
		List<Shift> subShift6 = new ArrayList<>();
		subShift6.add(new Shift(new MealTime(new DayTime(12, 0), new DayTime(13, 0)), 2, 8));
		subShift6.add(new Shift(new MealTime(new DayTime(13, 0), new DayTime(14, 0)), 2, 8));
		subShift6.add(new Shift(new MealTime(new DayTime(14, 0), new DayTime(15, 0)), 2, 8));
		subShift6.add(new Shift(new MealTime(new DayTime(15, 0), new DayTime(16, 0)), 2, 8));
		subShift6.add(new Shift(new MealTime(new DayTime(16, 0), new DayTime(17, 0)), 2, 8));
		
		List<Shift> subShift7 = new ArrayList<>();
		subShift7.add(new Shift(new MealTime(new DayTime(12, 0), new DayTime(13, 0)), 2, 8));
		subShift7.add(new Shift(new MealTime(new DayTime(13, 0), new DayTime(14, 0)), 2, 8));
		subShift7.add(new Shift(new MealTime(new DayTime(14, 0), new DayTime(15, 0)), 2, 8));
		subShift7.add(new Shift(new MealTime(new DayTime(15, 0), new DayTime(16, 0)), 2, 8));
		subShift7.add(new Shift(new MealTime(new DayTime(16, 0), new DayTime(17, 0)), 2, 8));
		
		superSubs.addWorkingDay(marchThird, subShift3, new ArrayList<>());
		superSubs.addWorkingDay(marchFourth, subShift4, new ArrayList<>());
		superSubs.addWorkingDay(marchFifth, subShift5, new ArrayList<>());
		superSubs.addWorkingDay(marchSixth, subShift6, new ArrayList<>());
		superSubs.addWorkingDay(marchSeventh, subShift7, new ArrayList<>());
	
		superSubs.makeReservation(allUsers.get("Jamie"), "Jamie", 12, marchSixth, new DayTime(14, 0), "");
		superSubs.makeReservation(allUsers.get("Anthony"), "Anthony", 5, marchSeventh, new DayTime(15, 0), "");
	}

	public static void changeScreen(Screen screen) {

		// If the screen is not the currently displayed screen
		// no the != here is not a bug, literal memory address compare
		if(!screenStack.isEmpty() && screenStack.peek() != screen) {
			screenStack.push(screen);
		}

		root.setTop(screen.getTop());
		root.setLeft(screen.getLeft());
		root.setCenter(screen.getCenter());
		root.setRight(screen.getRight());
		root.setBottom(screen.getBottom());
	}

	public static void goToSignIn() {
		changeScreen(signIn);
	}

	public static void goToAvailability() {
		availability.updateAvailability();
		changeScreen(availability);
	}

	public static void goToManageReservation() {
		changeScreen(new ManageReservationScreen(loggedInUser));
	}

	public static void goToManageRestaurants(Restaurant rest) {
		changeScreen(new ManageRestaurantsScreen(rest));
	}

	public static void goToCreateReservation(Restaurant rest, DayMonthYear dmy, Shift shift) {
		changeScreen(new CreateReservationScreen(rest, dmy, shift));
	}

	public static void goToUpdateShiftScreen(Shift shift) {
		changeScreen(new UpdateShiftScreen(shift));
	}

	public static void goToAddShiftScreen(List<Shift> shifts) {
		changeScreen(new AddShiftScreen(shifts));		
	}

	public static boolean stackHasSeveralItems() {
		return screenStack.size() > 1;
	}

	public static User getLoggedInUser() {
		return loggedInUser;
	}

	public static Map<String, User> getAllUsers() {
		return allUsers;
	}

	public static Screen backScreen() {
		if(screenStack.size() > 1) {
			screenStack.pop();
			Screen screen = screenStack.peek();
			changeScreen(screenStack.peek());
			return screen;
		}
		else {
			return null;
		}
	}

	public static void setLoggedIn(boolean logdIn, User user) {
		loggedIn = logdIn;
		loggedInUser = user;
	}

	public static boolean isLoggedIn() {
		return loggedIn;
	}

	public static List<Restaurant> getAllRestaurants() {
		return restaurantList;
	}

	public static void clearScreenStack() {
		screenStack.clear();
		screenStack.push(signIn);
	}

}
