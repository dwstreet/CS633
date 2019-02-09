package application;
	
import gui.SignInScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	private static boolean signedIn = false; 
	
	@Override
	public void start(Stage primaryStage) {
		
		try {		
			
			BorderPane root = new BorderPane();
			
			Scene initial = new Scene(root, 400, 400);
			initial.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			SignInScreen signIn = new SignInScreen();
			
			root.setCenter(signIn.getScreen());
			
			primaryStage.setTitle("Welcome");
			primaryStage.setScene(initial);
			primaryStage.show();
			primaryStage.centerOnScreen();

			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static void signIn() {
		signedIn = true;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
