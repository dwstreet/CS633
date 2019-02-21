package gui;

import java.time.LocalDate;

import application.DayMonthYear;
import application.Main;
import application.Restaurant;
import application.Shift;
import application.WorkingDay;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class AvailabilityScreen implements Screen {

	private HBox top;
	private VBox left;
	private VBox center;
	private VBox right;
	private VBox bottom; 

	//private BorderStroke borderStroke = new BorderStroke(null, BorderStrokeStyle.SOLID, null, null);
	private ComboBox<Restaurant> restComboBox;
	private DatePicker datePicker = new DatePicker(LocalDate.now()); 

	public AvailabilityScreen() {

		//The availability screens should get passed in restaurants 

		restComboBox = new ComboBox<Restaurant>(FXCollections.observableList(Main.getAllRestaurants()));
		restComboBox.setMinWidth(200);

		restComboBox.getSelectionModel().selectedItemProperty().addListener( (observable, oldVal, newVal) -> {
			buildCenterDisplay();
			updateAvailability();
		});

		restComboBox.setValue(Main.getAllRestaurants().get(0));

		datePicker.setOnAction( event -> {
			buildCenterDisplay(); 
			updateAvailability();
		});

		top = new HBox();
		top.getChildren().addAll(restComboBox, datePicker);
		top.setAlignment(Pos.CENTER);		

		bottom = new VBox(5);
		Restaurant rest = restComboBox.getValue();
		bottom.getChildren().addAll(new Label("Tables seat: " + rest.getTableSeats() + " people"), new Label("For other custom reservations please call us at: " + rest.getPhone()));
		bottom.setAlignment(Pos.CENTER);

		left = new VBox();
		left.setAlignment(Pos.BOTTOM_LEFT);
		Button back = new Button("Back");
		back.setOnAction(event -> Main.backScreen() );
		left.getChildren().add(back);
		
	}

	private Node buildCenterDisplay() {

		center = new VBox();
		center.setAlignment(Pos.CENTER);

		int day = datePicker.getValue().getDayOfMonth();
		int month = datePicker.getValue().getMonthValue();
		int year = datePicker.getValue().getYear();

		DayMonthYear selectedDay = new DayMonthYear(day, month, year);
		WorkingDay workingDay = restComboBox.getValue().getSchedule().get(selectedDay);

		// if the restaurant doesn't have a working day for that day or that day doesn't have any shifts
		if(workingDay == null || workingDay.isClosed()) {			
			center.getChildren().add(new Label(restComboBox.getValue().getName() + " is not open on " + selectedDay.toString()));
		}
		else {
			for(Shift shift : workingDay.getShifts()) {
				HBox box = new HBox(10);
				box.setAlignment(Pos.CENTER);
				
				Button makeResv = new Button("Make Reservation");
				makeResv.setOnAction(event -> Main.goToCreateReservation(restComboBox.getValue(), selectedDay, shift));
				
				box.getChildren().addAll(new Label(shift.toString()), makeResv);
				//box.setBorder(new Border(borderStroke));
				center.getChildren().add(box);
			}		
		}

		return center;
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
		
		if(!Main.isLoggedIn()) {
			right = new VBox();
			right.setAlignment(Pos.BOTTOM_RIGHT);
			Button signIn = new Button("Sign-In");
			signIn.setOnAction(event -> Main.goToSignIn() );
			right.getChildren().add(signIn);
		}
		else {
			right = new VBox();
			right.setAlignment(Pos.BOTTOM_RIGHT);
			Button manageResv = new Button("Manage\nReservations");
			manageResv.setTextAlignment(TextAlignment.CENTER);
			manageResv.setOnAction(event -> Main.goToManageReservation() );
			right.getChildren().add(manageResv);
		}
		
		return right;
	}

	@Override
	public Node getBottom() {
		return bottom;
	}

	public void updateAvailability() {

		buildCenterDisplay();
		Main.changeScreen(this);
	}
}
