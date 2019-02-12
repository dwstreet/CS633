package application;
	
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gui.AvailabilityScreen;
import gui.SignInScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	private static boolean signedIn = false;
	private static List<Restaurant> restaurantList = new ArrayList<>();
	
	@Override
	public void start(Stage primaryStage) {
		
		try {		
	
			//
			// build restaurantList information
			
			//////////////////////////////////////////
			// This is a temporary impl so I can test out gui stuff.
			Map<Date, WorkingDay> schedule = new HashMap<>();
			Date now = new Date();
			
			Date tomorrow = new Date(now.getYear(), now.getMonth(), now.getDay());
			WorkingDay exampleDay = new WorkingDay();
			exampleDay.makeShift(new DayTime(18, 0), new DayTime(19, 30), 5, 10);
			exampleDay.makeReservation("Dan", 3, new DayTime(18, 0));
			
			schedule.put(tomorrow, exampleDay);
			Restaurant rest = new Restaurant("Burger Joint", "123-456-7890", schedule);
			restaurantList.add(rest);
			/////////////////////////////////////////////
			
			BorderPane root = new BorderPane();
			
			Scene initial = new Scene(root, 400, 400);
			initial.getStylesheets().add(getClass().getResource("../gui/application.css").toExternalForm());
			
			SignInScreen signIn = new SignInScreen();
			AvailabilityScreen availability = new AvailabilityScreen();
			
			// Setting up sign in
			root.setCenter(signIn.getScreen());
			
			
			// Checking out availability
			root.setTop(availability.getTop());
			root.setCenter(availability.getCenter());
			
			primaryStage.setTitle("Welcome");
			primaryStage.setScene(initial);
			primaryStage.show();
			primaryStage.centerOnScreen();			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static void signIn() {
		signedIn = true;
	}
	
	public static List<Restaurant> getRestaurants() {
		return restaurantList;
	}
	
	public static void addRestaurant(Restaurant r) {
		restaurantList.add(r);
	}
	
	public static boolean removeRestaurant(Restaurant r) {
		return restaurantList.remove(r);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
