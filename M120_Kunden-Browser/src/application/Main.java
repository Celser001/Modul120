package application;
	
import application.view.MainViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/MainView.fxml"));
			Parent rootLayout = loader.load();
			Scene scene = new Scene(rootLayout);
			MainViewController mainController = loader.getController();
			mainController.setPrimaryStage(primaryStage);
			mainController.loadData();
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
