package gui;

import java.util.regex.Pattern;

import application.Main;
import application.Shift;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class UpdateShiftScreen implements Screen {
	
	private VBox top;
	private VBox left;
	private GridPane center;
	private VBox right;
	private VBox bottom;
	
	public UpdateShiftScreen(Shift shift) {
		
		top = new VBox();
		top.setAlignment(Pos.CENTER);
		top.getChildren().addAll(new Label(shift.toString()), new Label("Use negative numbers to subtract, positive to add."));
		
		center = new GridPane();
		center.setAlignment(Pos.CENTER);
		
		TextField modifyFloating = new TextField();
		modifyFloating.setTextFormatter(new TextFormatter<>( text -> Pattern.compile("\\d*").matcher(text.getText()).matches() ? text : null));
		TextField modifyTables = new TextField();
		modifyTables.setTextFormatter(new TextFormatter<>( text -> Pattern.compile("\\d*").matcher(text.getText()).matches() ? text : null));
		
		CheckBox negateFloating = new CheckBox();
		center.add(new Label("Negate:"), 0, 0);
		center.add(negateFloating, 1, 0);
		center.add(new Label("Modify floating seats: "), 2, 0);
		center.add(modifyFloating, 3, 0);
		
		CheckBox negateTables = new CheckBox();
		center.add(new Label("Negate:"), 0, 1);
		center.add(negateTables, 1, 1);
		center.add(new Label("Modify tables: "), 2, 1);
		center.add(modifyTables, 3, 1);
		
		bottom = new VBox();
		bottom.setAlignment(Pos.TOP_CENTER);
		
		Button accept = new Button("Accept");
		accept.setAlignment(Pos.CENTER);
		accept.setOnAction(event -> {
			
			String floatingSeatsStr = modifyFloating.getText();
			String numTablesStr = modifyTables.getText();
			
			int floatingSeats = floatingSeatsStr.isEmpty() ? 0 : Integer.parseInt(floatingSeatsStr);
			int numTables = numTablesStr.isEmpty() ? 0 : Integer.parseInt(numTablesStr);
			
			floatingSeats = negateFloating.selectedProperty().getValue() ? floatingSeats * -1 : floatingSeats;
			numTables = negateTables.selectedProperty().getValue() ? numTables * -1 : numTables;
			
			if(shift.modifySeats(floatingSeats, numTables)) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Modify Seats");
				alert.setContentText("Seats modified.");
				alert.showAndWait();
				
				ManageRestaurantsScreen screen = (ManageRestaurantsScreen) Main.backScreen();
				screen.buildCenterDisplay();				
				screen.update();
			}
			else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Modify Seats");
				alert.setContentText("Seats unable to be modified in that way.");
				alert.showAndWait();
			}
		});
		
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
