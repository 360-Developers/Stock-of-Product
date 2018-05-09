package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import DBConnection.DBHandler;

public class SignupController implements Initializable {

	@FXML
	private JFXPasswordField password;

	@FXML
	private JFXTextField name;

	@FXML
	private ImageView progress;

	@FXML
	private AnchorPane parentPane;

	@FXML
	private JFXButton login;

	@FXML
	private JFXButton signup;

	@FXML
	private JFXTextField email;

	private Connection connection;
	private DBHandler handler;
	private PreparedStatement pst;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		progress.setVisible(false);
		name.setStyle("-fx-text-inner-color: #a0a2ab;");
		password.setStyle("-fx-text-inner-color: #a0a2ab;");
		email.setStyle("-fx-text-inner-color: #a0a2ab;");

		handler = new DBHandler();

	}

	@FXML
	public void signupAction(ActionEvent ae1) {

		progress.setVisible(true);
		PauseTransition pt = new PauseTransition();
		pt.setDuration(Duration.seconds(3));
		pt.setOnFinished(e -> {
			try {
				// Saving data to DB
				String insert = "INSERT INTO User(Username, Password, email)" + "VALUES(?,?,?)";

				connection = handler.getConnection();
				pst = connection.prepareStatement(insert);
				pst.setString(1, name.getText());
				pst.setString(2, password.getText());
				pst.setString(3, email.getText());

				pst.executeUpdate();

				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setHeaderText(null);
				alert.setContentText("Signedup Succesfully.");
				alert.getDialogPane().setPrefSize(100, 50);
				alert.show();
				signup.getScene().getWindow().hide();
				Stage login = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("/FXML/LoginMain.fxml"));
				Scene scene = new Scene(root);
				login.setScene(scene);
				login.show();
				login.setResizable(false);

			} catch (SQLException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});

		pt.play();

	}

	@FXML
	public void loginAction(ActionEvent ae2) throws IOException {

		signup.getScene().getWindow().hide();

		Stage login = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/LoginMain.fxml"));
		Scene scene = new Scene(root);
		login.setScene(scene);
		login.show();
		login.setResizable(false);
	}

}
