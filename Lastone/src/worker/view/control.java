package worker.view;

import java.io.IOException;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import worker.Main;

public class control {
	@FXML
	private TextField id;
	@FXML
	private PasswordField password;
	@FXML
	private Main main;
	@FXML
	private void finder() throws IOException {
		main.enterNew();
	}
	
	
	@FXML
	private void showthem() {
		
		String tr=id.getText();
		String rt=password.getText();
		System.out.println(tr);
		System.out.println(rt);
	}

}
