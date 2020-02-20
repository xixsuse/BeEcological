package logic.view;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import error.AlreadyUsedUsernameException;
import error.EmptyFieldException;
import error.InexistentUsernameException;
import error.InvalidEmailException;
import error.ShortPasswordException;
import logic.bean.UserBean;
import logic.controller.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;


public class LoginUserView implements Initializable {
	
	@FXML private Button toOwnerLoginButton;
	@FXML private Button homeButton;
	@FXML private Button signInButton;
	@FXML private TextField textName;
	@FXML private TextField textSurname;
	@FXML private TextField textEmailAddress;
	@FXML private TextField textPhoneNumber;
	@FXML private TextField textUsername;
	@FXML private TextField textPassword;
	@FXML private TextField textConfirmPassword;
	@FXML private Button loginButton;
	@FXML private TextField loginUsername;
	@FXML private TextField loginPassword;
	
    private UserController control;
    private UserBean user;
	
	public void returnHomepage(ActionEvent event) {
		try {
			URL url = new File("src/fxml/Homepage.fxml").toURI().toURL();
			Parent tableViewParent = FXMLLoader.load(url);
			Scene tableViewScene = new Scene(tableViewParent);
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			
			window.setScene(tableViewScene);
			window.setTitle("Homepage");
			window.show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void toOwnerLogin(ActionEvent event) {
		try {
			URL url = new File("src/fxml/LoginOwner.fxml").toURI().toURL();
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
	
	public void loginUser(ActionEvent event) throws InexistentUsernameException, EmptyFieldException {
		boolean ok;
		String username = loginUsername.getText();
		String password = loginPassword.getText();
		
		user = UserBean.getUserInstance(username);
		user.setUsbPassword(password);
		
		control = new UserController();
		ok = control.login(user);
		Alert alert = new Alert(AlertType.ERROR);
		if (!ok) {
			UserBean.usbInstance = null;
			alert.setTitle("Login failed");
			alert.setHeaderText(null);
			alert.setContentText("User not registered: incorrect username or password.\nRetry or register!");
			alert.showAndWait();
			return;
		}
		try {
			alert.setAlertType(AlertType.INFORMATION);
			alert.setTitle("Welcome Back!");
			alert.setHeaderText(null);
			alert.setContentText("User verified, login completed.");
			alert.showAndWait();
			
		    Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		    URL url = new File("src/fxml/Homepage.fxml").toURI().toURL();
		    FXMLLoader loader = new FXMLLoader(url);
			Parent tableViewParent = loader.load();
			HomepageView controller = (HomepageView) loader.getController();
			Scene tableViewScene = new Scene(tableViewParent);
			window.setScene(tableViewScene);
			window.setTitle("BeEcological for Users - Home");
			controller.loginGroup.setVisible(false);
			controller.userGroup.setVisible(true);
			controller.circleUserGroup.setVisible(true);
			controller.circleOwnerGroup.setVisible(false);
			controller.userButton.setText(UserBean.getUserInstance(username).getUsbUsername());
			controller.welcomebackText.setText("Welcome back, "+UserBean.getUserInstance("").getUsbUsername());
			window.show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void registerUser(ActionEvent event) throws EmptyFieldException, ShortPasswordException, InvalidEmailException, AlreadyUsedUsernameException {
		boolean ok = true;
		String name = textName.getText();
		String surname = textSurname.getText();
		String emailAddress = textEmailAddress.getText();
		String phoneNumber = textPhoneNumber.getText();
		String username = textUsername.getText();
		String password = textPassword.getText();
		String confirmPassword = textConfirmPassword.getText();
		
		user = new UserBean();
		user.setUsbUsername(username);
		control = new UserController();
		
		ok = control.checkRegistration(user);
		Alert alert = new Alert(AlertType.ERROR);
		if (name.isEmpty() || surname.isEmpty()) {
			alert.setTitle("Registration Failed - Invalid name");
			alert.setHeaderText(null);
			alert.setContentText("Name and surname cannot be empty.");
			alert.showAndWait();
			return;
		}
		
		if (!emailAddress.contains("@gmail.com") && !emailAddress.contains("@yahoo.com") &&
			!emailAddress.contains("@libero.it") && !emailAddress.contains("@hotmail.it") &&
			!emailAddress.contains("@outlook.com") && !emailAddress.contains("@mail.com") &&
			!emailAddress.contains("@virgilio.it") && !emailAddress.contains("@email.it"))	
		{
			alert.setTitle("Registration Failed - Invalid email");
			alert.setHeaderText(null);
			alert.setContentText("Insert a valid email address.");
			alert.showAndWait();
			return;
		}
		
		if (username.isEmpty()) {
			alert.setTitle("Registration Failed - Missing username");
			alert.setHeaderText(null);
			alert.setContentText("Insert an username.");
			alert.showAndWait();
			return;
		}
		
		if (!ok) {
			alert.setTitle("Registration Failed - Username already existing");
			alert.setHeaderText(null);
			alert.setContentText("Choose another username available.");
			alert.showAndWait();
			return;
		}
		
		if (phoneNumber.isEmpty() || phoneNumber.matches("[0-9]+") == false) {
			alert.setTitle("Registration Failed - Invalid phone number");
			alert.setHeaderText(null);
			alert.setContentText("Insert a valid phone number.");
			alert.showAndWait();
			return;
		}
		
		if (password.isEmpty() || (password.length() < 8)) {
			alert.setTitle("Registration Failed - Password too weak");
			alert.setHeaderText(null);
			alert.setContentText("Choose a password with at least 8 characters.");
			alert.showAndWait();
			return;
		}
		
		if (!password.equalsIgnoreCase(confirmPassword)) {
			alert.setTitle("Registration Failed - Wrong password confirmation");
			alert.setHeaderText(null);
			alert.setContentText("The two passwords you entered not match, try again.");
			alert.showAndWait();
			return;
		}
		user.setUsbName(name);
		user.setUsbSurname(surname);
		user.setUsbEmail(emailAddress);
		user.setUsbPhone(phoneNumber);
		user.setUsbPassword(password);
		user.setEcopoints(0);
		
		control.saveRegistration(user);
		
		try {
			alert.setAlertType(AlertType.INFORMATION);
			alert.setTitle("Welcome to BeEcological!");
			alert.setHeaderText(null);
			alert.setContentText("Registration completed, you will be redirected to the beecological homepage.");
			alert.showAndWait();
		    Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		    URL url = new File("src/fxml/Homepage.fxml").toURI().toURL();
		    FXMLLoader loader = new FXMLLoader(url);
			Parent tableViewParent = loader.load();
			Scene tableViewScene = new Scene(tableViewParent);
			window.setScene(tableViewScene);
			HomepageView controller = (HomepageView) loader.getController();
			controller.loginGroup.setVisible(false);
			controller.userGroup.setVisible(true);
			controller.userButton.setText(UserBean.getUserInstance(username).getUsbUsername());
			controller.circleUserGroup.setVisible(true);
			controller.circleOwnerGroup.setVisible(false);
			controller.welcomebackText.setText("Welcome back, "+UserBean.getUserInstance("").getUsbUsername());
			window.setTitle("BeEcological - Home");
			window.show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loginUsername.requestFocus();
		homeButton.setTooltip(new Tooltip("Return to BeEcological Homepage"));
	}
	
}
