package gui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import application.DayMonthYear;
import application.Main;
import application.Restaurant;
import application.Shift;
import application.WorkingDay;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ManageRestaurantsScreen implements Screen {

	private VBox top;
	private VBox left;
	private VBox center;
	private Node right;
	private VBox bottom;

	private Restaurant rest;
	private DatePicker datePicker = new DatePicker(LocalDate.now()); 

	public ManageRestaurantsScreen(Restaurant rest) {

		this.rest = rest;

		datePicker.setOnAction( event -> {
			buildCenterDisplay(); 
			update();
		});

		top = new VBox();
		top.getChildren().addAll(new Label(rest.getName()), datePicker);
		top.setAlignment(Pos.CENTER);

		buildCenterDisplay();		

		bottom = new VBox(2);

		left = new VBox();
		left.setAlignment(Pos.BOTTOM_LEFT);
		Button back = new Button("Back");
		back.setOnAction(event -> Main.backScreen() );
		left.getChildren().add(back);
	}

	public Restaurant getRestaurant() {
		return rest;
	}

	public void buildCenterDisplay() {

		Button addShift = new Button("Add shift");
		addShift.setAlignment(Pos.CENTER);
		addShift.setOnAction( event -> {
			Main.goToAddShiftScreen(new ArrayList<>());
		});

		Button addWorkingDay = new Button("Add working day");
		addWorkingDay.setOnAction( event -> {
			//todo
		});
		
		center = new VBox(5);
		center.setAlignment(Pos.CENTER);
		center.getChildren().add(addShift);

		int day = datePicker.getValue().getDayOfMonth();
		int month = datePicker.getValue().getMonthValue();
		int year = datePicker.getValue().getYear();

		DayMonthYear selectedDay = new DayMonthYear(day, month, year);
		WorkingDay workDay = rest.getSchedule().get(selectedDay);

		if(workDay != null) {

			List<Shift> shifts = workDay.getShifts();
			shifts.sort(null);
			
			addShift.setOnAction( event -> {
				Main.goToAddShiftScreen(shifts);
			});

			for(Shift shift : shifts) {
				HBox box = new HBox(2);
				box.setAlignment(Pos.CENTER);

				Button updateShift = new Button("Update");
				updateShift.setOnAction( event -> {
					Main.goToUpdateShiftScreen(shift);
				});

				box.getChildren().addAll(new Label(shift.toString()), updateShift);
				center.getChildren().add(box);
			}
		}
		else {
			center.getChildren().add(new Label("There are no shifts on this date."));
		}
	}

	public void update() {

		Main.changeScreen(this);
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
