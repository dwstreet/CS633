package gui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.sun.media.sound.AlawCodec;

import application.Main;
import application.Reservation;
import application.Restaurant;
import application.User;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ManageReservationScreen implements Screen {

	private HBox top;
	private VBox left;
	private VBox center;
	private VBox right;
	private Node bottom; 

	public ManageReservationScreen(User user) {		

		top = new HBox();
		top.setAlignment(Pos.TOP_CENTER);
		top.getChildren().add(new Label("Reservations for user: " + user.getUserName()));

		center = new VBox(5);
		center.setAlignment(Pos.CENTER);

		Map<Restaurant, List<Reservation>> userReservations = new HashMap<>();

		// If you're reading this and you don't understand streams here is what this does in a nutshell.
		// For every restaurant it looks through all the reservations and checks if that reservation belongs to the user
		// and collects reservations that do belong to the user and then adds them to the total list.
		Main.getAllRestaurants().stream()
		.forEach(restaurant -> userReservations.put(restaurant, restaurant.getAllUserReservations(user)));

		
		for(Entry<Restaurant, List<Reservation>> entry : userReservations.entrySet()) {
			
			Restaurant rest = entry.getKey();
			List<Reservation> reservations = entry.getValue();
			reservations.sort(null);
			for(Reservation resv : reservations) {
				HBox box = new HBox(5);
				
				Button cancel = new Button("Cancel");
				cancel.setOnAction(event -> {
					rest.cancelReservation(resv);
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Cancel Reservation");
					alert.setContentText("This reservation was canceled.");
					alert.showAndWait();
				});
				
				box.getChildren().addAll(new Label(entry.getKey().toString() + ": " + resv.toString()), cancel);
				box.setAlignment(Pos.CENTER);
				center.getChildren().add(box);
			}
			
		}

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
