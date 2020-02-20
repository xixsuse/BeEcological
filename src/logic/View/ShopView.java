package logic.View;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import logic.Bean.UserBean;
import logic.Controller.UserController;
import javafx.application.Platform;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class ShopView implements Initializable {
	@FXML private MenuItem userProfileItem, logoutItem;
	@FXML private MenuButton userButton;
	@FXML private Button homeButton, searchButton;
	@FXML private Button am05,am10,am25,za05,za10,gp05,gp10,ap10,rm10,ms10,fe10,ps05,xb05,nx10;
	@FXML private Text ecoPoints;
	public long start,end;
	public boolean isBuying = false;
	public Alert alert;
	
	private UserController control;
	
	public void showTimeoutAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setAlertType(AlertType.ERROR);
		alert.setTitle("Timeout expired");
		alert.setHeaderText(null);
		alert.setContentText("Buying timeout expired, retry!");		
		alert.show();
	}

	 public class TimeoutThread extends Thread { //thread which control if the booking request is made within 2 minutes
		 
		    public void run(){
				while (System.currentTimeMillis() < end) {
				}
				if (isBuying) {
				Platform.runLater(new Runnable(){ //javaFX thread to modify GUI. Useful to show alerts. a classic java thread can't do this.
				    public void run(){
				    	isBuying = false;
				    	alert.close();
				    	showTimeoutAlert();
				    	}
					});
				}
		    } 	
	 }
	
	public void returnHomepage(ActionEvent event) {
		try {
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		    URL url = new File("src/fxml/Homepage.fxml").toURI().toURL();
		    FXMLLoader loader = new FXMLLoader(url);
			Parent tableViewParent = loader.load();
			Scene tableViewScene = new Scene(tableViewParent);
			window.setScene(tableViewScene);
			window.setTitle("BeEcological - Home");
			HomepageView controller = (HomepageView) loader.getController();
			controller.loginGroup.setVisible(false);
			controller.userGroup.setVisible(true);
			controller.userButton.setText(UserBean.usbInstance.getUsbUsername());
			controller.circleUserGroup.setVisible(true);
			controller.circleOwnerGroup.setVisible(false);
			window.show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void doLogout(ActionEvent event) {
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to logout?");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK){
			try {
				Stage window = (Stage) userButton.getScene().getWindow();
			    URL url = new File("src/fxml/Homepage.fxml").toURI().toURL();
			    FXMLLoader loader = new FXMLLoader(url);
				Parent tableViewParent = loader.load();
				Scene tableViewScene = new Scene(tableViewParent);
				window.setScene(tableViewScene);
				window.setTitle("Homepage");
				HomepageView controller = (HomepageView) loader.getController();
				controller.userGroup.setVisible(false);
				UserBean.usbInstance = null;
				window.show();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else {
		    //do nothing
		}

	}
	
	public void gotoUserProfile(ActionEvent event) {
		try {
			Stage window = (Stage) userButton.getScene().getWindow();
		    URL url = new File("src/fxml/UserProfile.fxml").toURI().toURL();
		    FXMLLoader loader = new FXMLLoader(url);
			Parent tableViewParent = loader.load();
			Scene tableViewScene = new Scene(tableViewParent);
			window.setScene(tableViewScene);
			window.setScene(tableViewScene);
			window.setTitle("BeEcological - Profile");
			window.show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void gotoShop(ActionEvent event) {
		try {
			//ricavo lo stage dal menuButton, poichè il menuItem non è una sottoclasse di Node
			Stage window = (Stage) userButton.getScene().getWindow();
		    URL url = new File("src/fxml/Shop.fxml").toURI().toURL();
		    FXMLLoader loader = new FXMLLoader(url);
			Parent tableViewParent = loader.load();
			Scene tableViewScene = new Scene(tableViewParent);
			window.setScene(tableViewScene);
			window.setTitle("BeEcological - Shop");
			window.show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void buyItem(ActionEvent event) {
		Button clicked=(Button) event.getSource();
		String id = clicked.getId();
		switch(id) {
		case "am05":
			updateEcoPoints(event,100);
			return;
		case "am10":
			updateEcoPoints(event,200);
			return;
		case "am25":
			updateEcoPoints(event,450);
			return;
		case "za05":
			updateEcoPoints(event,100);
			return;
		case "za10":
			updateEcoPoints(event,200);
			return;
		case "gp05":
			updateEcoPoints(event,100);
			return;
		case "gp10":
			updateEcoPoints(event,200);
			return;
		case "ap10":
			updateEcoPoints(event,220);
			return;
		case "rm10":
			updateEcoPoints(event,180);
			return;
		case "ms10":
			updateEcoPoints(event,210);
			return;
		case "fe10":
			updateEcoPoints(event,180);
			return;
		case "ps05":
			updateEcoPoints(event,200);
			return;
		case "xb05":
			updateEcoPoints(event,200);
			return;
		case "nx10":
			updateEcoPoints(event,220);
			return;
		default:
			return;
		}
	}
	
public void updateEcoPoints(ActionEvent event,int cost) {
	int oldEcoPoints = Integer.parseInt(ecoPoints.getText());
	alert = new Alert(AlertType.ERROR);
	if (oldEcoPoints-cost<0) {
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText("You don't have enough ecoPoints to purchase the selected item");		
		alert.showAndWait();
		isBuying = false;
		return;
	}
	isBuying = true;
	start = System.currentTimeMillis();
	end = start + 10*1000; // 60 seconds * 1000 ms/sec
	TimeoutThread timeout = new TimeoutThread();
	timeout.start();
	alert.setAlertType(AlertType.CONFIRMATION);
	alert.setTitle("Confirm");
	alert.setHeaderText("Do you want to purchase this item?");
	alert.setContentText("Current ecoPoints:     "+oldEcoPoints+"\nNew ecoPoints:          "+Integer.toString(oldEcoPoints-cost));

	Optional<ButtonType> result = alert.showAndWait();
	if (result.get() == ButtonType.OK){
		int newEcoPoints = oldEcoPoints - cost;
		UserBean.usbInstance.setEcopoints(newEcoPoints);
		control = new UserController();
		control.updateEcoPoints(UserBean.getUserInstance(""));
		isBuying = false;
		gotoShop(event);
	}
	
}
	

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		homeButton.setTooltip(new Tooltip("Return to BeEcological Homepage"));
		control = new UserController();
		List<String> userInfo = control.userInformation(UserBean.usbInstance);
		userButton.setText(UserBean.usbInstance.getUsbUsername());
		ecoPoints.setText(userInfo.get(4));
	}
}
