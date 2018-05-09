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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ProductviewController implements Initializable {

	@FXML
	private TableColumn<ProductDetails, String> column1;

	@FXML
	private AnchorPane anchorpane;

	@FXML
	private TableColumn<ProductDetails, String> column4;

	@FXML
	private TableColumn<ProductDetails, String> column3;

	@FXML
	private TableColumn<ProductDetails, String> column2;

	@FXML
	private TableColumn<ProductDetails, String> column5;

	@FXML
	private TableView<ProductDetails> table;

	@FXML
	private JFXButton back;
	
    @FXML
    private JFXButton logout;

	private DBHandler handler;
	private Connection connection;
	private PreparedStatement pst;
	private ObservableList<ProductDetails> data;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		handler = new DBHandler();

		try {

			connection = handler.getConnection();
			data = FXCollections.observableArrayList();
			// Execute query and store result in a resultset
			ResultSet rs = connection.createStatement()
					.executeQuery("SELECT P.ProductID, P.ProductName, P.Count, P.Price,T.Type , Count*Price AS Income\n"
							+ "FROM Type T\n" + "INNER JOIN Product P\n" + "ON T.TypeID = P.TypeID\n" + "\n" + "\n"
							+ "\n" + "");
			while (rs.next()) {
				// get string from db,whichever way
				data.add(new ProductDetails(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6)));
			}

		} catch (SQLException ex) {
			System.err.println("Error" + ex);
		}

		// Set cell value factory to tableview.
		// NB.PropertyValue Factory must be the same with the one set in model class.
		column1.setCellValueFactory(new PropertyValueFactory<>("Productname"));
		column2.setCellValueFactory(new PropertyValueFactory<>("Count"));
		column3.setCellValueFactory(new PropertyValueFactory<>("Price"));
		column4.setCellValueFactory(new PropertyValueFactory<>("TypeID"));
		column5.setCellValueFactory(new PropertyValueFactory<>("Income"));

		// table.setItems(null);
		table.setItems(data);

	}

	@FXML
	public void goBack(ActionEvent e) throws IOException {
		anchorpane.getScene().getWindow().hide();

		Stage goback = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/HomePage.fxml"));
		Scene scene = new Scene(root);
		goback.setScene(scene);
		goback.show();
		goback.setResizable(false);

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
