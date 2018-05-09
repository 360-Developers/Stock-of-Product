package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.*;
import java.sql.ResultSet;
import DBConnection.DBHandler;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AddproductController implements Initializable {

	@FXML
	private JFXTextField pName;

	@FXML
	private JFXTextField price;

	@FXML
	private JFXTextField count;

	@FXML
	private JFXButton back;

	@FXML
	private JFXButton addproduct;

	@FXML
	private JFXComboBox<?> type;

	@FXML
	private AnchorPane anchorp;
	
	@FXML
	private Label lab;

    @FXML
    private JFXButton logout;

	private DBHandler handler;
	private Connection connection;
	private PreparedStatement pst;
	final ObservableList options = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		
		handler = new DBHandler();
		type.setStyle("-fx-background-color: #41B6FA;");
		fillCombo();
		type.setItems(options);
		pName.setStyle("-fx-text-inner-color: #a0a2ab;");
		price.setStyle("-fx-text-inner-color: #a0a2ab;");
		count.setStyle("-fx-text-inner-color: #a0a2ab;");

	}

	private void fillCombo() {

		try {
			connection = handler.getConnection();
			String q2 = "SELECT Type FROM Stock.Type";
			pst = connection.prepareStatement(q2);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				options.add(rs.getString("Type"));
			}
			rs.close();
			pst.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@FXML
	public void goBack(ActionEvent e) throws IOException {

		back.getScene().getWindow().hide();

		Stage Back = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/HomePage.fxml"));
		Scene scene = new Scene(root);
		Back.setScene(scene);
		Back.show();
		Back.setResizable(false);

	}

	@FXML
	public void AddProductAction(ActionEvent e) {

		int c = 0;
		if (type.getValue().equals("Fruit")) {
			c = 1;
		} else if (type.getValue().equals("Vegetable")) {
			c = 2;
		} else if (type.getValue().equals("Milk and Milk Product")) {
			c = 3;
		} else if (type.getValue().equals("Drinks")) {
			c = 4;
		} else if (type.getValue().equals("Snack")) {
			c = 5;
		} else if (type.getValue().equals("Personal Care")) {
			c = 6;
		} else if (type.getValue().equals("Meat and Meat Product")) {
			c = 7;
		} else if (type.getValue().equals("Cleaning Product")) {
			c = 8;
		}

		try {
			// Saving data to DB
			String insert = "INSERT INTO Product(ProductName, Count, Price, TypeID)" + "VALUES(?,?,?,?)";

			connection = handler.getConnection();
			pst = connection.prepareStatement(insert);
			pst.setString(1, pName.getText());
			pst.setString(2, count.getText());
			pst.setString(3, price.getText());
			pst.setLong(4, c);

			pst.executeUpdate();
			
			lab.setText("Product added to the system.");

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setContentText("Product Added to the System Succesfully.");
		alert.getDialogPane().setPrefSize(100, 50);
		alert.show();

	}
	
	@FXML
	public void logoutAction(ActionEvent ae) throws IOException {
		logout.getScene().getWindow().hide();

		Stage exit = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/LoginMain.fxml"));
		Scene scene = new Scene(root);
		exit.setScene(scene);
		exit.show();
		
	}

}
