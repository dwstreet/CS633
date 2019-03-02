package gui;

import application.Main;
import application.User;
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

public class SignInScreen implements Screen {
	
	private VBox top;
	private VBox left;
	private VBox center;
	private VBox right;
	private VBox bottom;

	public SignInScreen() {
		
		TextField username = new TextField();
		PasswordField password = new PasswordField();
		username.setMinWidth(200);
		password.setMinWidth(200);
		
		Button guest = new Button("Continue as Guest");
		Button signIn = new Button("Sign In");

		guest.setOnAction( event -> Main.goToAvailability());
		signIn.setOnAction( event -> 
			{ 

				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Log-in Result");
				String user = username.getText();
				String pass = password.getText();
				
				User userToCheck = Main.getAllUsers().get(user);
				if(doSignIn(userToCheck, pass)) {
					// log-in
					alert.setContentText("Log-in Success!");
					alert.showAndWait();
					Main.setLoggedIn(true, userToCheck);
					
					// If the sign-in was reached from another screen (e.g. create reservation)
					// then go back to that screen otherwise go to availability.
					if(Main.stackHasSeveralItems()) {
						Main.backScreen();
					}
					else {
						Main.goToAvailability();
					}
				}
				else {
					// display error
					alert.setContentText("Wrong Username or password!");
					alert.showAndWait();
				}
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
	
	private boolean doSignIn(User user, String pass) {

		return user.authenticate(pass);
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
