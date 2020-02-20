package logic.View;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.sun.javafx.scene.control.skin.TableHeaderRow;

import logic.Bean.UserBean;
import logic.Bean.WasteUnloadedBean;
import logic.Controller.UserController;
import logic.Controller.WasteUnloadedController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class UserProfileView implements Initializable {
	
	private ArrayList<WasteUnloadedBean> data = new ArrayList<>();
	private ObservableList<WasteUnloadedBean> unload_list = FXCollections.observableArrayList();
	String pageHome = "src/fxml/Homepage.fxml";
	
	@FXML private MenuItem userProfileItem, logoutItem;
	@FXML public MenuButton userButton;
	@FXML private Group userGroup;
	@FXML private Button homeButton, searchButton, seeBookingButton, editDataButton, changeCredButton, deleteAccountButton;
	@FXML private Text userNick, ecoPoints, name, surname, email, phoneNumber;
	
	@FXML private TableView<WasteUnloadedBean> unloadTable;
	@FXML private TableColumn<WasteUnloadedBean, String> col_center, col_date, col_time, col_waste, col_wasteQuantity, col_ecoPoints;
	
	private UserController control;
	private WasteUnloadedController control1;
	
	public void returnHomepage(ActionEvent event) {
		try {
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		    URL url = new File(pageHome).toURI().toURL();
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
			controller.welcomebackText.setText("Welcome back, "+UserBean.usbInstance.getUsbUsername());
			window.show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void doSearch(ActionEvent event) {
		try {
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		    URL url = new File("src/fxml/SearchResult.fxml").toURI().toURL();
		    FXMLLoader loader = new FXMLLoader(url);
			Parent tableViewParent = loader.load();
			Scene tableViewScene = new Scene(tableViewParent);
			window.setScene(tableViewScene);
			window.setTitle("BeEcological - Search Result");
			SearchResultView controller = (SearchResultView) loader.getController();
			controller.loginGroup.setVisible(false);
			controller.userGroup.setVisible(true);
			controller.userButton.setText(UserBean.usbInstance.getUsbUsername());
			window.show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void gotoUserBookingList(ActionEvent event) {
		try {
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		    URL url = new File("src/fxml/UserBookingList.fxml").toURI().toURL();
		    FXMLLoader loader = new FXMLLoader(url);
			Parent tableViewParent = loader.load();
			Scene tableViewScene = new Scene(tableViewParent);
			window.setScene(tableViewScene);
			window.setTitle("BeEcological - Booking List");
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
				Stage window = (Stage) userButton.getScene().getWindow();
			    URL url = new File(pageHome).toURI().toURL();
			    FXMLLoader loader = new FXMLLoader(url);
				Parent tableViewParent = loader.load();
				Scene tableViewScene = new Scene(tableViewParent);
				window.setScene(tableViewScene);
				window.setTitle("Homepage");
				HomepageView controller = (HomepageView) loader.getController();
				controller.userGroup.setVisible(false);
				control = new UserController();
				control.deleteAccount(UserBean.usbInstance);
				UserBean.usbInstance = null;
				window.show();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
		
	public void gotoUserProfile(ActionEvent event) {
		try {
			//ricavo lo stage dal menuButton, poichè il menuItem non è una sottoclasse di Node
			Stage window = (Stage) userButton.getScene().getWindow();
		    URL url = new File("src/fxml/UserProfile.fxml").toURI().toURL();
		    FXMLLoader loader = new FXMLLoader(url);
			Parent tableViewParent = loader.load();
			Scene tableViewScene = new Scene(tableViewParent);
			window.setScene(tableViewScene);
			window.setScene(tableViewScene);
			window.setTitle("BeEcological - Profile");
			System.out.println(UserBean.usbInstance.getUsbUsername());
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
			    URL url = new File(pageHome).toURI().toURL();
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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		userGroup.setVisible(true);
		control = new UserController();
		List<String> userInfo = control.userInformation(UserBean.usbInstance);
		userButton.setText(UserBean.usbInstance.getUsbUsername());
		userNick.setText(UserBean.usbInstance.getUsbUsername());
		name.setText(userInfo.get(0));
		surname.setText(userInfo.get(1));
		email.setText(userInfo.get(2));
		phoneNumber.setText(userInfo.get(3));
		ecoPoints.setText(userInfo.get(4));
		
		unload_list.removeAll(unload_list);
	    try {
	        control1 = new WasteUnloadedController();
	    	data = control1.listUnloadByUser(UserBean.usbInstance);
	        unload_list.addAll(data);
	    }
	    catch(Exception e){
	          e.printStackTrace();
	          System.out.println("Error on Building Data");            
	    }
	    //riempio le colonne tramite il corrispondente nome dell'attributo dato nella definizione della classe
		col_center.setCellValueFactory(new PropertyValueFactory<>("center"));
	    col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
		col_time.setCellValueFactory(new PropertyValueFactory<>("time"));
		col_waste.setCellValueFactory(new PropertyValueFactory<>("waste"));
		col_wasteQuantity.setCellValueFactory(new PropertyValueFactory<>("wasteQuantity"));
		col_ecoPoints.setCellValueFactory(new PropertyValueFactory<>("ecoPoints"));
		unloadTable.setItems(unload_list);
		
		unloadTable.widthProperty().addListener(new ChangeListener<Number>()
		{
			@Override
		    public void changed(ObservableValue<? extends Number> source, Number oldWidth, Number newWidth)
		    {
				TableHeaderRow header = (TableHeaderRow) unloadTable.lookup("TableHeaderRow");
		        header.reorderingProperty().addListener(new ChangeListener<Boolean>() {
		            @Override
		            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		                header.setReordering(false);
		            }
		        });
		    }
		});
	}
	
}
