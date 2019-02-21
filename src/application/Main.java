package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import gui.AvailabilityScreen;
import gui.CreateReservationScreen;
import gui.Screen;
import gui.SignInScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {

	private static boolean loggedIn = false;
	private static BorderPane root = new BorderPane();
	private static Stack<Screen> screenStack = new Stack<>();
	
	private static SignInScreen signIn;
	private static AvailabilityScreen availability;
	private static CreateReservationScreen resvCreate;

	@Override
	public void start(Stage stage) {

		try {		

			//
			// build restaurantList information

			//////////////////////////////////////////
			// This is a temporary impl so I can test out gui stuff.
			Map<DayMonthYear, WorkingDay> schedule = new HashMap<>();

			DayMonthYear tomorrow = new DayMonthYear(13, 2, 2019);
			WorkingDay exampleDay = new WorkingDay();
			exampleDay.makeShift(new DayTime(18, 0), new DayTime(19, 30), 5, 10);
			exampleDay.makeReservation("Dan", 3, new DayTime(18, 0));

			schedule.put(tomorrow, exampleDay);
			Restaurant rest = new Restaurant("Burger Joint", "123-456-7890", schedule);
			List<Restaurant> restaurantList = new ArrayList<>();
			restaurantList.add(rest);
			/////////////////////////////////////////////

			Scene initial = new Scene(root, 400, 400);
			initial.getStylesheets().add(getClass().getResource("../gui/application.css").toExternalForm());

			// Setting up sign in
			signIn = new SignInScreen();

//			// Checking out availability
			availability = new AvailabilityScreen(restaurantList, loggedIn);
//			
//			// Checking out CreateReservation
			resvCreate = new CreateReservationScreen(rest, tomorrow, exampleDay.getShifts().get(0), loggedIn);
			

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
		root.setBottom(screen.getLeft());
	}
	
	public static void goToSignIn() {
		changeScreen(signIn);
	}
	
	public static void goToAvailability() {
		changeScreen(availability);
	}
	
	public static void goToCreateReservation() {
		changeScreen(resvCreate);
	}
	
	
	public static void goToManageReservation() {
		changeScreen(null);
	}
	
	public static void goToManageRestaurants() {
		changeScreen(null);
	}
	
	public static void backScreen() {
		if(!screenStack.isEmpty()) {
			screenStack.pop();
			changeScreen(screenStack.peek());
		}
	}
	
	public static void isLoggedIn(boolean logdIn) {
		loggedIn = logdIn;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
