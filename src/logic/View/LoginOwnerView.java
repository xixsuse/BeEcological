package logic.View;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import logic.Bean.CenterOwnerBean;
import logic.Controller.OwnerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;


public class LoginOwnerView implements Initializable {
	
	@FXML private Button toUserLoginButton;
	@FXML private Button homeButton;
	@FXML private Button toHomeOwnerButton;
	@FXML private TextField loginUsername,textName,textSurname,textEmailAddress,textPhoneNumber,textUsername,textPassword,textConfirmPassword;
	@FXML private TextField textCenterName,textCity,textAddress,textN,textCAP,textCenterPhone;
	@FXML private PasswordField loginPassword;
	
	@FXML private CenterOwnerBean owner;
	@FXML private OwnerController control;
		
	public void returnHomepage(ActionEvent event) {
		try {
		    Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		    URL url = new File("src/fxml/Homepage.fxml").toURI().toURL();
		    FXMLLoader loader = new FXMLLoader(url);
			Parent tableViewParent = loader.load();
			Scene tableViewScene = new Scene(tableViewParent);
			window.setScene(tableViewScene);
			window.setTitle("Homepage");
			window.show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void toUserLogin(ActionEvent event) {
		try {

			URL url = new File("src/fxml/LoginUser.fxml").toURI().toURL();
			Parent tableViewParent = FXMLLoader.load(url);
			Scene tableViewScene = new Scene(tableViewParent);
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			
			window.setScene(tableViewScene);
			window.setTitle("Login");
			window.show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void loginOwner(ActionEvent event) {
		boolean ok;
		String username = loginUsername.getText();
		String password = loginPassword.getText();
		
		owner = CenterOwnerBean.getOwnerInstance(username);
		owner.setCobPassword(password);
		
		control = new OwnerController();
		ok = control.login(owner);
		Alert alert = new Alert(AlertType.ERROR);
		if (!ok) {
			CenterOwnerBean.instance = null;
			alert.setTitle("Login failed");
			alert.setHeaderText(null);
			alert.setContentText("Owner not registered: incorrect username or password.\nRetry or register!");
			alert.showAndWait();
			return;
		}	

		try {
			alert.setAlertType(AlertType.INFORMATION);
			alert.setTitle("Welcome Back!");
			alert.setHeaderText(null);
			alert.setContentText("Owner verified, login completed.");
			alert.showAndWait();
		    Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		    URL url = new File("src/fxml/HomeOwner.fxml").toURI().toURL();
		    FXMLLoader loader = new FXMLLoader(url);
			Parent tableViewParent = loader.load();
			Scene tableViewScene = new Scene(tableViewParent);
			window.setScene(tableViewScene);
			window.setTitle("BeEcological for Managers - Home");
			window.show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		homeButton.setTooltip(new Tooltip("Return to BeEcological Homepage"));
	}
	
}
