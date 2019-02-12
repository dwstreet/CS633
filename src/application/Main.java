package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gui.AvailabilityScreen;
import gui.SignInScreen;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {

	private static boolean signedIn = false;
	private static BorderPane root = new BorderPane(); 

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

			SignInScreen signIn = new SignInScreen();
			AvailabilityScreen availability = new AvailabilityScreen(restaurantList);

			// Setting up sign in
			root.setCenter(signIn.getScreen());

			// Checking out availability
			root.setTop(availability.getTop());
			root.setCenter(availability.getCenter());

			stage.setTitle("Welcome");
			stage.setScene(initial);
			stage.show();
			stage.centerOnScreen();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void updateExternal(Node top, Node left, Node center, Node right, Node bottom) {

		root.setTop(top);
		root.setLeft(left);
		root.setCenter(center);
		root.setRight(right);
		root.setBottom(bottom);
	}

	static void signIn() {
		signedIn = true;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
