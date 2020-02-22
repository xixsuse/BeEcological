package logic.View;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;

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
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class HomeOwnerView implements Initializable {
	
	@FXML private Button homeButton;
	@FXML private MenuButton ownerButton;
	@FXML private MenuItem ownerProfileItem, logoutItem;
	@FXML private Button registerUnloadButton,manageBookingButton,historyANDUnloadsButton,manageInformationButton,changePhotoButton;
	@FXML private Text centerName, centerAddress, ownerMail, ownerPhone, centerPhone;
	@FXML private ImageView centerImageView;
	
	@FXML private CenterOwnerBean owner;
	@FXML private OwnerController control;

	
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

	public void registerUnload(ActionEvent event) {
		try {
		    Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		    URL url = new File("src/fxml/RegisterUnload.fxml").toURI().toURL();
		    FXMLLoader loader = new FXMLLoader(url);
			Parent tableViewParent = loader.load();
			Scene tableViewScene = new Scene(tableViewParent);
			window.setScene(tableViewScene);
			window.setTitle("BeEcological for Managers - Register unload");
			window.show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void manageBooking(ActionEvent event) {
		try {
		    Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		    URL url = new File("src/fxml/ManageBooking.fxml").toURI().toURL();
		    FXMLLoader loader = new FXMLLoader(url);
			Parent tableViewParent = loader.load();
			Scene tableViewScene = new Scene(tableViewParent);
			window.setScene(tableViewScene);
			window.setTitle("BeEcological for Managers - Manage booking");
			window.show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void manageInformation(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);		
		alert.setTitle("Manage Information");
		alert.setHeaderText(null);
		alert.setContentText("Functionality not implemented.");		
		alert.showAndWait();
		return;
	}
	
	public void historyANDUnloads(ActionEvent event) {
		try {
		    Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		    URL url = new File("src/fxml/BookingANDUnloads.fxml").toURI().toURL();
		    FXMLLoader loader = new FXMLLoader(url);
			Parent tableViewParent = loader.load();
			Scene tableViewScene = new Scene(tableViewParent);
			window.setScene(tableViewScene);
			window.setTitle("BeEcological for Managers - Booking & Unloads");
			window.show();
		}catch(Exception e){
			e.printStackTrace();
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
		window.setTitle("BeEcological - Profile");
		window.show();
	}catch(Exception e){
		e.printStackTrace();
	}
}
	
	public void doLogout(ActionEvent event){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to logout?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			try {
			    Stage window = (Stage) (ownerButton.getScene().getWindow());
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
	
	public void changePhoto(ActionEvent event) {
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(//
			       new FileChooser.ExtensionFilter("JPG", "*.jpg"), 
			       new FileChooser.ExtensionFilter("PNG", "*.png"),
		new FileChooser.ExtensionFilter("All Files", "*.*"));
		
		fileChooser.setTitle("Choose a photo for your center");
		Stage window = (Stage) changePhotoButton.getScene().getWindow();
		File source = fileChooser.showOpenDialog(window);
		String destFileName = CenterOwnerBean.getOwnerInstance("").getCobCenter()+".jpg";
		File dest = new File ("src/jpeg/"+destFileName);
		try {
		    FileUtils.copyFile(source, dest);
		} catch (IOException e) {
		    e.printStackTrace();
		}
		returnHomepage(event);
	}
		

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		homeButton.setTooltip(new Tooltip("Return to BeEcological Homepage"));
		registerUnloadButton.setTooltip(new Tooltip("Register the data of an unload, inserting, date, time,\n type and quantity of waste."));
		manageBookingButton.setTooltip((new Tooltip("Add a new unload booking, or confirm\nan existing booking request")));
		historyANDUnloadsButton.setTooltip((new Tooltip("See a list of all the accepted booking request\nand all the registered unloads.")));
		manageInformationButton.setTooltip(new Tooltip("Edit or update your center information"));
		
		owner = new CenterOwnerBean();
		control = new OwnerController();
		owner.setCobUsername(CenterOwnerBean.getOwnerInstance("").getCobUsername());
		ownerButton.setText(owner.getCobUsername());
		
		List<String> ownerInfo = control.ownerData(owner);
		centerName.setText(ownerInfo.get(4));
		centerAddress.setText(ownerInfo.get(7)+" "+ownerInfo.get(9));
		ownerMail.setText(ownerInfo.get(2));
		ownerPhone.setText(ownerInfo.get(3));
		centerPhone.setText(ownerInfo.get(5));
		
		String centerName = ownerInfo.get(4);
		CenterOwnerBean.getOwnerInstance("").setCobCenter(centerName);
		File sourcePhoto = new File("src/jpeg/"+centerName+".jpg");
		if(sourcePhoto.exists() && !sourcePhoto.isDirectory()) { 
			Image image = new Image(sourcePhoto.toURI().toString());
	        centerImageView.setImage(image);
		}
		else {
			File defaultFile = new File("src/jpeg/default.jpg");
			Image image2 = new Image(defaultFile.toURI().toString());
	        centerImageView.setImage(image2);
		}
	}
	
}
