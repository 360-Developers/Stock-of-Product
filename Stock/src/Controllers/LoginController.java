package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.*;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import DBConnection.DBHandler;

public class LoginController implements Initializable {

	@FXML
	private JFXCheckBox remember;

	@FXML
	private JFXPasswordField password;

	@FXML
	private ImageView progress;

	@FXML
	private JFXButton login;

	@FXML
	private JFXButton forgot;

	@FXML
	private JFXButton signup;

	@FXML
	private JFXTextField username;

	private DBHandler handler;
	private Connection connection;
	private PreparedStatement pst;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		progress.setVisible(false);
		username.setStyle("-fx-text-inner-color: #a0a2ab;");
		password.setStyle("-fx-text-inner-color: #a0a2ab;");

		handler = new DBHandler();

	}

	@FXML
	public void loginAction(ActionEvent e) throws IOException {

		progress.setVisible(true);
		PauseTransition pt = new PauseTransition();
		pt.setDuration(Duration.seconds(3));
		pt.setOnFinished(ev -> {

			try {
				// Retrieve data from DB
				connection = handler.getConnection();
				String q1 = "SELECT * FROM User WHERE Username = ? AND Password = ?";
				pst = connection.prepareStatement(q1);
				pst.setString(1, username.getText());
				pst.setString(2, password.getText());
				ResultSet rs = pst.executeQuery();

				int count = 0;

				while (rs.next()) {
					count = count + 1;
				}

				if (count == 1) {


					login.getScene().getWindow().hide();

					Stage Home = new Stage();
					Parent root;
					try {
						root = FXMLLoader.load(getClass().getResource("/FXML/HomePage.fxml"));
						Scene scene = new Scene(root);
						Home.setScene(scene);
						Home.show();
						Home.setResizable(false);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

				else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setContentText("Username or password is not correct.");
					alert.show();
					progress.setVisible(false);

				}

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			finally {
				try {
					connection.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		pt.play();


	}

	@FXML
	public void signUp(ActionEvent e1) throws IOException {

		login.getScene().getWindow().hide();

		Stage signup = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/SignUp.fxml"));
		Scene scene = new Scene(root);
		signup.setScene(scene);
		signup.show();
		signup.setResizable(false);

	}

}
