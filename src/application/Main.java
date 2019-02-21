package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import gui.AvailabilityScreen;
import gui.CreateReservationScreen;
import gui.ManageReservationScreen;
import gui.Screen;
import gui.SignInScreen;
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

	@Override
	public void start(Stage stage) {

		try {		

			//
			// build restaurantList information

			//////////////////////////////////////////
			// This is a temporary impl so I can test out gui stuff.
			allUsers.put("Dan", new User("Dan", "pass"));
			Map<DayMonthYear, WorkingDay> schedule = new HashMap<>();

			DayMonthYear day = new DayMonthYear(21, 2, 2019);
			
			List<Shift> shifts = new ArrayList<>();
			shifts.add(new Shift(new MealTime(new DayTime(18, 0), new DayTime(19, 30)), 5, 5));
			List<Reservation> reservations = new ArrayList<>();
			reservations.add(new Reservation(allUsers.get("Dan"), "Dan", 3, day, new DayTime(18, 0), ""));

			Restaurant rest = new Restaurant("Burger Joint", "123-456-7890", schedule, 4);
			rest.addWorkingDay(day, shifts, reservations);
			restaurantList.add(rest);
			/////////////////////////////////////////////

			Scene initial = new Scene(root, 600, 600);
			initial.getStylesheets().add(getClass().getResource("../gui/application.css").toExternalForm());

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
	
	public static void goToManageRestaurants() {
		changeScreen(null);
	}
	
	public static void goToCreateReservation(Restaurant rest, DayMonthYear dmy, Shift shift) {
		changeScreen(new CreateReservationScreen(rest, dmy, shift));
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
	
	public static void backScreen() {
		if(!screenStack.isEmpty()) {
			screenStack.pop();
			changeScreen(screenStack.peek());
		}
	}
	
	public static void setLoggedIn(boolean logdIn, User user) {
		loggedIn = logdIn;
		loggedInUser = user;
	}
	
	public static boolean isLoggedIn() {
		return loggedIn;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static List<Restaurant> getAllRestaurants() {
		return restaurantList;
	}
}
