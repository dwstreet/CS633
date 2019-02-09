package gui;

import java.util.ArrayList;
import java.util.List;

import application.Restaurant;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;

public class AvailabilityScreen {

	private HBox top;
	
	public AvailabilityScreen() {
		
		List<Restaurant> restaurantList = new ArrayList<>();
		
		//This is where reading of a file of restaurants would be helpful. 
		
		
		///////////////////////////////////////////////////////////////////
		
		ComboBox<Restaurant> restaurants = new ComboBox<Restaurant>(FXCollections.observableList(restaurantList));
		restaurants.setMinWidth(200);
		DatePicker datePicker = new DatePicker();
		
		// use this stuff later... just left here as reference on how to get the information out of the date picker.
//		int day = datePicker.getValue().getDayOfMonth();
//		int month = datePicker.getValue().getMonthValue();
//		int year = datePicker.getValue().getYear();
		
		top = new HBox();
		top.getChildren().addAll(restaurants, datePicker);
		top.setAlignment(Pos.CENTER);
	}
			
	public Node getTop() {
		return top;
	}
}
