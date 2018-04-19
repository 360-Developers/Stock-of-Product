package worker.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import worker.Main;

public class Mainviewcontrol {

private Main main;
@FXML
private void turnBack() throws IOException {
	main.showMainItems();
}

}
