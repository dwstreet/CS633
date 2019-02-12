package gui;

import java.time.LocalDate;
import java.util.List;

import application.DayMonthYear;
import application.Main;
import application.Restaurant;
import application.Shift;
import application.WorkingDay;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AvailabilityScreen {

	private HBox top;
	private VBox center;
	private BorderStroke borderStroke = new BorderStroke(null, BorderStrokeStyle.SOLID, null, null);
	private ComboBox<Restaurant> restComboBox;
	private DatePicker datePicker = new DatePicker(LocalDate.now()); 
	
	public AvailabilityScreen(List<Restaurant> restaurants) {
		
		//The availability screens should get passed in restaurants 
		
		restComboBox = new ComboBox<Restaurant>(FXCollections.observableList(restaurants));
		restComboBox.setMinWidth(200);
		
		restComboBox.getSelectionModel().selectedItemProperty().addListener( (observable, oldVal, newVal) -> {
			buildCenterDisplay();
			updateAvailability();
		});
		
		restComboBox.setValue(restaurants.get(0));
		
		datePicker.setOnAction( event -> {
			buildCenterDisplay(); 
			updateAvailability();
		});
		
		top = new HBox();
		top.getChildren().addAll(restComboBox, datePicker);
		top.setAlignment(Pos.CENTER);		
		
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
			for(Shift s : workingDay.getShifts()) {
				HBox box = new HBox();
				box.setAlignment(Pos.CENTER);
				box.getChildren().addAll(new Label(s.toString()));
				box.setBorder(new Border(borderStroke));
				center.getChildren().add(box);
			}		
		}
		
		return center;
	}
			
	public Node getTop() {
		return top;
	}
	
	public Node getCenter() {
		return center;
	}
	
	public void updateAvailability() {
		
		Main.updateExternal(getTop(), null, buildCenterDisplay(), null, null);
	}
}
