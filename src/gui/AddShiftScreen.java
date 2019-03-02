package gui;

import java.util.List;
import java.util.regex.Pattern;

import application.DayTime;
import application.Main;
import application.MealTime;
import application.Shift;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class AddShiftScreen implements Screen {

	private VBox top;
	private VBox left;
	private GridPane center;
	private VBox right;
	private VBox bottom;

	public AddShiftScreen(List<Shift> shifts) {

		TextField startHour = new TextField();
		startHour.setTextFormatter(new TextFormatter<>( text -> Pattern.compile("\\d*").matcher(text.getText()).matches() ? text : null));

		TextField startMinutes = new TextField();
		startMinutes.setTextFormatter(new TextFormatter<>( text -> Pattern.compile("\\d*").matcher(text.getText()).matches() ? text : null));

		TextField endHour = new TextField();
		endHour.setTextFormatter(new TextFormatter<>( text -> Pattern.compile("\\d*").matcher(text.getText()).matches() ? text : null));

		TextField endMinutes = new TextField();
		endMinutes.setTextFormatter(new TextFormatter<>( text -> Pattern.compile("\\d*").matcher(text.getText()).matches() ? text : null));

		TextField numFloating = new TextField();
		numFloating.setTextFormatter(new TextFormatter<>( text -> Pattern.compile("\\d*").matcher(text.getText()).matches() ? text : null));

		TextField numTables = new TextField();
		numTables.setTextFormatter(new TextFormatter<>( text -> Pattern.compile("\\d*").matcher(text.getText()).matches() ? text : null));

		center = new GridPane();
		center.add(new Label("Start time "), 0, 0);
		center.add(startHour, 1, 0);
		center.add(new Label(":"), 2, 0);
		center.add(startMinutes, 3, 0);

		center.add(new Label("End time "), 0, 1);
		center.add(endHour, 1, 1);
		center.add(new Label(":"), 2, 0);
		center.add(endMinutes, 3, 1);

		center.add(new Label("Floating seats "), 0, 2);
		center.add(numFloating, 1, 2);

		center.add(new Label("Number of Tables "), 0, 3);
		center.add(numTables, 1, 3);
		center.setAlignment(Pos.CENTER);

		Button accept = new Button("Accept");
		accept.setOnAction( event -> {

			String startHourStr = startHour.getText();
			String startMinutesStr = startMinutes.getText();
			String endHourStr = endHour.getText();
			String endMinutesStr = endMinutes.getText();
			String numFloatingStr = numFloating.getText();
			String numTablesStr = numTables.getText();

			if(startHourStr.isEmpty() || startMinutesStr.isEmpty() || endHourStr.isEmpty() || 
					endMinutesStr.isEmpty() || numFloatingStr.isEmpty() || numTablesStr.isEmpty()) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Bad Shift");
				alert.setContentText("Cannot have any empty values");
				alert.showAndWait();
			}
			else {

				int startHr = Integer.parseInt(startHourStr);
				int startMin = Integer.parseInt(startMinutesStr);
				int endHr = Integer.parseInt(endHourStr);
				int endMin = Integer.parseInt(endMinutesStr);
				int numFloats = Integer.parseInt(numFloatingStr);
				int numTbls = Integer.parseInt(numTablesStr);

				try {
					DayTime startTime = new DayTime(startHr, startMin);
					DayTime endTime = new DayTime(endHr, endMin);

					shifts.add(new Shift(new MealTime(startTime, endTime), numFloats, numTbls));
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Success");
					alert.setContentText("Shift added.");
					alert.showAndWait();

					ManageRestaurantsScreen screen = (ManageRestaurantsScreen) Main.backScreen();
					screen.buildCenterDisplay();				
					screen.update();
				}
				catch(IllegalStateException ise) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Bad Time");
					alert.setContentText("The time supplied was not valid.  Hours are 0-23, minutes are 0-59");
					alert.showAndWait();
				}
			}

		});

		bottom = new VBox();
		bottom.setAlignment(Pos.TOP_CENTER);
		bottom.getChildren().add(accept);

		left = new VBox();
		left.setAlignment(Pos.BOTTOM_LEFT);
		Button back = new Button("Back");
		back.setOnAction(event -> Main.backScreen() );
		left.getChildren().add(back);
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
