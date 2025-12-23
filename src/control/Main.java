package control;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

import javafx.application.Application;

public class Main extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/view/MainView.fxml")

		);

		stage.setScene(new Scene(root));
		stage.setTitle("Cittadella");

		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
