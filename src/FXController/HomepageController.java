package FXController;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import Bean.UserBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class HomepageController implements Initializable {
	
	ObservableList<String> list = FXCollections.observableArrayList();
	
	@FXML private Label circleOwnerText;
	@FXML private ComboBox<String> hourBooking;
	@FXML private MenuItem userProfileItem, logoutItem;
	@FXML public MenuButton userButton;
	@FXML private Button homeButton, loginButton, searchButton, shopButton, circleOwnerButton, circleUserButton;
	@FXML public Group loginGroup, userGroup, circleOwnerGroup, circleUserGroup;
	@FXML private TextField searchBar;
	@FXML public Text welcomebackText;
	
	public void returnHomepage(ActionEvent event) {
		try {
		    Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		    URL url = new File("src/FXView/Homepage.fxml").toURI().toURL();
		    FXMLLoader loader = new FXMLLoader(url);
			Parent tableViewParent = loader.load();
			Scene tableViewScene = new Scene(tableViewParent);
			window.setScene(tableViewScene);
			window.setTitle("Homepage");
			
			HomepageController controller = (HomepageController) loader.getController();
			if(UserBean.instance != null) {
				controller.loginGroup.setVisible(false);
				controller.userGroup.setVisible(true);
				controller.circleUserGroup.setVisible(true);
				controller.circleOwnerGroup.setVisible(false);
				controller.userButton.setText(UserBean.getUserInstance("").getUsername());
				controller.welcomebackText.setText("Welcome back, "+UserBean.getUserInstance("").getUsername());
			}
			window.show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void gotoRequest(ActionEvent event) throws IOException {
		URL url = new File("src/FXView/UserBookingList.fxml").toURI().toURL();
		Parent tableViewParent = FXMLLoader.load(url);
		Scene tableViewScene = new Scene(tableViewParent);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.setTitle("BeEcological - User list");
		window.show();
	}
	
	public void doSearch(ActionEvent event) {
		tool.string = searchBar.getText();
		try {
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		    URL url = new File("src/FXView/SearchResult.fxml").toURI().toURL();
		    FXMLLoader loader = new FXMLLoader(url);
			Parent tableViewParent = loader.load();
			Scene tableViewScene = new Scene(tableViewParent);
			window.setScene(tableViewScene);
			window.setTitle("BeEcological - Search Result");
			
			SearchResultController controller = (SearchResultController) loader.getController();
			if(UserBean.instance != null) {
				controller.loginGroup.setVisible(false);
				controller.userGroup.setVisible(true);
				controller.userButton.setText(UserBean.instance.getUsername());
			}
			else {
				controller.userGroup.setVisible(false);
				controller.loginGroup.setVisible(true);
			}
			controller.textSearched.setText(tool.string); //setta il testo del risultato come quello cercato
			window.show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void toUserLogin(ActionEvent event) {
		try {
			URL url = new File("src/FXView/LoginUser.fxml").toURI().toURL();
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
	
	public void gotoUserProfile(ActionEvent event) {
		try {
			//ricavo lo stage dal menuButton, poich� il menuItem non � una sottoclasse di Node
			Stage window = (Stage) userButton.getScene().getWindow();
		    URL url = new File("src/FXView/UserProfile.fxml").toURI().toURL();
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
	
	public void doLogout(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to logout?");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK){
			try {
				Stage window = (Stage) userButton.getScene().getWindow();
			    URL url = new File("src/FXView/Homepage.fxml").toURI().toURL();
			    FXMLLoader loader = new FXMLLoader(url);
				Parent tableViewParent = loader.load();
				Scene tableViewScene = new Scene(tableViewParent);
				window.setScene(tableViewScene);
				window.setTitle("Homepage");
				HomepageController controller = (HomepageController) loader.getController();
				controller.userGroup.setVisible(false);
				controller.loginGroup.setVisible(true);
				UserBean.instance = null;
				window.show();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else {
		    //do nothing
		}	
	}

	public void gotoShop(ActionEvent event) {
		try {
			//ricavo lo stage dal menuButton, poich� il menuItem non � una sottoclasse di Node
			Stage window = (Stage) userButton.getScene().getWindow();
		    URL url = new File("src/FXView/Shop.fxml").toURI().toURL();
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
	
	public void gotoOwnerLogin(ActionEvent event) {
		try {
			URL url = new File("src/FXView/LoginOwner.fxml").toURI().toURL();
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
	

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		homeButton.setTooltip(new Tooltip("Return to BeEcological Homepage"));
		searchBar.setFont(Font.font("Calibri Light", FontWeight.NORMAL, 15));
		userGroup.setVisible(false);
		circleUserGroup.setVisible(false);
	}
	
}
