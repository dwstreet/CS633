package gui;

import java.awt.Event;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import application.Main;
import application.Restaurant;
import application.WorkingDay;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AvailabilityScreen {

	private HBox top;
	private VBox center;
	private DatePicker datePicker; 
	
	public AvailabilityScreen() {
		
		//This is where reading of a file of restaurants would be helpful. 
		
		
		///////////////////////////////////////////////////////////////////
		
		datePicker = new DatePicker();
		datePicker.setValue(LocalDate.now());
		ComboBox<Restaurant> restaurants = new ComboBox<Restaurant>(FXCollections.observableList(Main.getRestaurants()));
		restaurants.setMinWidth(200);
		
		restaurants.getSelectionModel().selectedItemProperty().addListener( 
				
			new ChangeListener<Restaurant>() {

				@Override
				public void changed(ObservableValue<? extends Restaurant> observable, Restaurant oldValue,
						Restaurant newValue) {
					buildCenterDisplay(newValue);
				}
				
			}
		);
		
		restaurants.setValue(Main.getRestaurants().get(0));
		
		// buildCenterDisplay(restaurants.getValue());
		
		// use this stuff later... just left here as reference on how to get the information out of the date picker.
//		int day = datePicker.getValue().getDayOfMonth();
//		int month = datePicker.getValue().getMonthValue();
//		int year = datePicker.getValue().getYear();
		
		top = new HBox();
		top.getChildren().addAll(restaurants, datePicker);
		top.setAlignment(Pos.CENTER);		
		
	}
	
	private void buildCenterDisplay(Restaurant rest) {
		
		center = new VBox();
		
		int day = datePicker.getValue().getDayOfMonth();
		int month = datePicker.getValue().getMonthValue();
		int year = datePicker.getValue().getYear();
		Date selectedDay = new Date(year, month, day);
		
		List<WorkingDay> display = new ArrayList<>();
		for(Entry<Date, WorkingDay> workday : rest.getSchedule().entrySet()) {			
			// If the date has not yet passed add it to display
			if(workday.getKey().after(selectedDay)) {
				display.add(workday.getValue());
			}
		}
		
		// only include 7 dates from selected date
		for(int i = 0; i < 7;) {
			WorkingDay workday = display.get(i);
			//workday.canBook("", 1);
		}
		
	}
			
	public Node getTop() {
		return top;
	}
	
	public Node getCenter() {
		return center;
	}
	
	public void updateAvailability() {
		//not yet implemented
	}
}
