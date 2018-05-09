package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import DBConnection.DBHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import Controllers.HomepageController;

public class SearchpageController implements Initializable {

	@FXML
	private Label income;

	@FXML
	private Label price;

	@FXML
	private Label name;

	@FXML
	private Label count;

	@FXML
	private Label type;

	private HomepageController search;

	@FXML
	private JFXButton logout;

	private DBHandler handler;
	private Connection connection;
	private PreparedStatement pst;

	private ObservableList<Object> data;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		handler = new DBHandler();
		connection = handler.getConnection();
		data = FXCollections.observableArrayList();
		//search.SearchText();


		try {

			connection = handler.getConnection();
			data = FXCollections.observableArrayList();
			// Execute query and store result in a resultset
			
			ResultSet rs = connection.createStatement()
					.executeQuery("SELECT P.ProductName, P.Count, P.Price, T.Type,Count*Price AS Income\n" + 
							"FROM Type T\n" + 
							"INNER JOIN Product P\n" + 
							"ON T.TypeID = P.TypeID\n" + 
							"WHERE ProductName= ? OR T.Type = ?");
			while (rs.next()) {
				// get string from db,whichever way

				data.addAll(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
			}

		} catch (SQLException ex) {
			System.err.println("Error" + ex);
		}

		//System.out.println(search.SearchText());
		int i = 0;
		while(i<data.size()) {
			System.out.println(data.get(i));
			i++;
		}

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
