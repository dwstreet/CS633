package gui;

import application.DayMonthYear;
import application.Restaurant;
import application.Shift;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class CreateReservationScreen implements Screen{

	private VBox top;
	private VBox left; 
	private GridPane center;
	private VBox right = null;
	private VBox bottom = null;
	
	public CreateReservationScreen(Restaurant restaurant, DayMonthYear dmy, Shift shift, boolean loggedIn) {
		
		top = new VBox();
		top.setPadding(new Insets(20));
		left = new VBox();
		center = new GridPane();
		
		top.setAlignment(Pos.CENTER);
		left.setAlignment(Pos.BOTTOM_LEFT);
		center.setAlignment(Pos.CENTER);
		
		top.getChildren().addAll(new Label(restaurant.getName() + " - " + dmy.toString() + " " + shift.getMealTime()));
		
		center.add(new Label("Party Name: "), 0, 0);
		center.add(new TextField(), 1, 0);
		
		center.add(new Label("Number of People: "), 0, 1);
		center.add(new TextField(), 1, 1);
		
		center.add(new Label("Notes: "), 0, 2);
		center.add(new TextField(), 1, 2);
		
		Button reservationButton = new Button("Confirm Reservation");
		reservationButton.setAlignment(Pos.CENTER);
		center.add(reservationButton, 1, 3, 2, 1);
		
		//restaurant.makeReservation(partyName, partyNumber, date, mealTime)
		
		left.getChildren().add(new Button("Back"));
	}
	
	@Override
	public Node getTop() {
		return top;
	}

	@Override
	public Node getLeft() {
		return left;
	}

	@Override
	public Node getCenter() {
		return center;
	}

	@Override
	public Node getRight() {
		return right;
	}

	@Override
	public Node getBottom() {
		return bottom;
	}
}
