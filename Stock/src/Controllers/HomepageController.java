package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.*;

import DBConnection.DBHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HomepageController implements Initializable {

	@FXML
	private AnchorPane anchorpane;

	@FXML
	private JFXTextField search_txt;

	@FXML
	private JFXButton showproducts;

	@FXML
	private JFXButton addproduct;

	@FXML
	private JFXButton search_btn;
	
    @FXML
    private JFXButton logout;

	private DBHandler handler;
	private Connection connection;
	private PreparedStatement pst;
	
	private ObservableList<Object> data = FXCollections.observableArrayList();
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		search_txt.setStyle("-fx-text-inner-color: #a0a2ab;");
		handler = new DBHandler();

	}

	@FXML
	public void addProduct(ActionEvent ae) throws IOException {
		addproduct.getScene().getWindow().hide();

		Stage AddProduct = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/AddProduct.fxml"));
		Scene scene = new Scene(root);
		AddProduct.setScene(scene);
		AddProduct.show();
		AddProduct.setResizable(false);
	}

	@FXML
	public void ProductList(ActionEvent ae2) throws IOException{

		try {
			connection = handler.getConnection();
			String q2 = "SELECT Type FROM Stock.Type";
			pst = connection.prepareStatement(q2);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				data.add(rs.getString("Type"));
			}
			rs.close();
			pst.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		addproduct.getScene().getWindow().hide();

		Stage Product = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/ProductView.fxml"));
		Scene scene = new Scene(root);
		Product.setScene(scene);
		Product.show();
		Product.setResizable(false);
		
	}
	
/*	public ObservableList<Object> SearchQuery() throws SQLException {
		connection = handler.getConnection();
		String query = "SELECT P.ProductName, T.Type\n" + 
				"FROM Type T\n" + 
				"INNER JOIN Product P\n" + 
				"ON T.TypeID = P.TypeID\n" + 
				"WHERE ProductName='?' OR T.Type = '?'\n" + 
				"\n" + 
				"\n" + 
				"\n" + 
				"";
		
		pst = connection.prepareStatement(query);
		pst.setString(1, search_txt.getText());
		pst.setString(2, search_txt.getText());
		ResultSet rs = pst.executeQuery();
		
		return rs;
		
	}*/
	
	public String SearchText() {
		return	search_txt.getText();
	}
	
	@FXML
	public void SearchAction(ActionEvent ae3) throws SQLException, IOException {
		connection = handler.getConnection();
		String query = "SELECT P.ProductName, T.Type\n" + 
				"FROM Type T\n" + 
				"INNER JOIN Product P\n" + 
				"ON T.TypeID = P.TypeID\n" + 
				"WHERE ProductName=? OR T.Type = ?\n" + 
				"\n" + 
				"\n" + 
				"\n" + 
				"";
		
		pst = connection.prepareStatement(query);
		pst.setString(1, search_txt.getText());
		pst.setString(2, search_txt.getText());
		ResultSet rs = pst.executeQuery();
		
		Stage search = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/SearchPage.fxml"));
		Scene scene = new Scene(root);
		search.setScene(scene);
		search.show();
		search.setResizable(false);
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
