package worker;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	private Stage primaryStage;
	private static BorderPane mainLayout;
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage=primaryStage;
		this.primaryStage.setTitle("Employer enter");
		showMainview();
		showMainItems();

	}



	private void showMainview() throws IOException {
		FXMLLoader loader=new FXMLLoader();	
		loader.setLocation(Main.class.getResource("view/MainView.fxml"));
		mainLayout=loader.load();
		Scene scene =new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	public static void showMainItems() throws IOException {
		FXMLLoader loader=new FXMLLoader();	
		loader.setLocation(Main.class.getResource("view/MainItem.fxml"));
		BorderPane mainItem =loader.load();
		mainLayout.setCenter(mainItem);
	}

	public static void enterNew() throws IOException{
	FXMLLoader loader=new FXMLLoader();	
	loader.setLocation(Main.class.getResource("signin/loginner.fxml"));
	BorderPane bert= loader.load();
	mainLayout.setCenter(bert);
}
	public static void main(String[] args) {
		launch(args);
	}
}
