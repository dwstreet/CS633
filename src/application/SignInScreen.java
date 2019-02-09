package application;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class SignInScreen {
	
	private VBox center;

	public SignInScreen() {
		
		TextField username = new TextField();
		PasswordField password = new PasswordField();
		username.setMinWidth(200);
		password.setMinWidth(200);
		
		Button guest = new Button("Continue as Guest");
		Button signIn = new Button("Sign In");

		guest.setOnAction( event -> System.out.println("Guest Pressed") /* move on as guest */);
		signIn.setOnAction( event -> 
			{ 

				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Log-in Result");
				if(doSignin(username.getText(), password.getText())) {
					// log-in
					alert.setContentText("Log-in Success!");
				}
				else {
					// display error
					alert.setContentText("Wrong Username or password!");
				}
			
				alert.showAndWait();
			}
		);
		
		guest.setMinWidth(150);
		signIn.setMinWidth(100);
		
		GridPane signInBox = new GridPane();
		signInBox.add(new Label("Username:"), 0, 0);
		signInBox.add(new Label("Password:"), 0, 1);
		signInBox.add(username, 1, 0);
		signInBox.add(password, 1, 1);
		
		
		VBox buttonBox = new VBox(5);
		buttonBox.getChildren().addAll(signIn, guest);
		buttonBox.setAlignment(Pos.CENTER);
		signInBox.setAlignment(Pos.CENTER);
		signInBox.addRow(2, buttonBox);
		
		center = new VBox(5);
		center.getChildren().addAll(signInBox, buttonBox);
		center.setAlignment(Pos.CENTER);	
	}
	
	public Node getScreen() {
		return center;
	}
	
	private boolean doSignin(String user, String pass) {
		
		
		return user.equals("Dan") && pass.equals("pass");
	}
}
