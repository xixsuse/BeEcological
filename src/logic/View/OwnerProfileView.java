package logic.view;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import logic.bean.CenterOwnerBean;
import logic.controller.OwnerController;
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


public class OwnerProfileView implements Initializable {
	@FXML private MenuItem ownerProfileItem, logoutItem;
	@FXML private MenuButton ownerButton;
	@FXML private Button homeButton, searchButton, editDataButton, changeCredButton, deleteAccountButton;
	@FXML private Text userNick, ecoPoints, name, surname, email, phoneNumber;
	
	private CenterOwnerBean owner;
	private OwnerController control;
	
	public void returnHomepage(ActionEvent event) {
		try {
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
	
	public void editData(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);		
		alert.setTitle("Edit Personal Data");
		alert.setHeaderText(null);
		alert.setContentText("Functionality not implemented.");		
		alert.showAndWait();
		return;
	}
	
	public void changeCred(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);		
		alert.setTitle("Change Login Credentials");
		alert.setHeaderText(null);
		alert.setContentText("Functionality not implemented.");		
		alert.showAndWait();
		return;
	}
	
	public void deleteAccount(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete Account");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to delete your account?");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK){
			try {
				Stage window = (Stage) ownerButton.getScene().getWindow();
			    URL url = new File("src/fxml/Homepage.fxml").toURI().toURL();
			    FXMLLoader loader = new FXMLLoader(url);
				Parent tableViewParent = loader.load();
				Scene tableViewScene = new Scene(tableViewParent);
				window.setScene(tableViewScene);
				window.setTitle("Homepage");
				HomepageView controller = (HomepageView) loader.getController();
				controller.userGroup.setVisible(false);
				control = new OwnerController();
				control.deleteAccount(CenterOwnerBean.instance);
				CenterOwnerBean.instance = null;
				window.show();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void doLogout(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to logout?");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK){
			try {
				Stage window = (Stage) ownerButton.getScene().getWindow();
			    URL url = new File("src/fxml/Homepage.fxml").toURI().toURL();
			    FXMLLoader loader = new FXMLLoader(url);
				Parent tableViewParent = loader.load();
				Scene tableViewScene = new Scene(tableViewParent);
				window.setScene(tableViewScene);
				window.setTitle("Homepage");
				HomepageView controller = (HomepageView) loader.getController();
				controller.userGroup.setVisible(false);
				controller.loginGroup.setVisible(true);
				CenterOwnerBean.instance = null;
				window.show();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else {
		    //do nothing
		}
		
	}
	
	public void gotoOwnerProfile(ActionEvent event) {
		try {
			//ricavo lo stage dal menuButton, poichè il menuItem non è una sottoclasse di Node
			Stage window = (Stage) ownerButton.getScene().getWindow();
		    URL url = new File("src/fxml/OwnerProfile.fxml").toURI().toURL();
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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		homeButton.setTooltip(new Tooltip("Return to BeEcological Homepage"));
		owner = new CenterOwnerBean();
		control = new OwnerController();
		owner.setCobUsername(CenterOwnerBean.getOwnerInstance("").getCobUsername());
		ownerButton.setText(owner.getCobUsername());
		userNick.setText(owner.getCobUsername());
		
		List<String> ownerInfo = control.ownerData(owner);

		name.setText(ownerInfo.get(0));
		surname.setText(ownerInfo.get(1));
		email.setText(ownerInfo.get(2));
		phoneNumber.setText(ownerInfo.get(3));
	}
}
