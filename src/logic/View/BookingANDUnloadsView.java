package logic.View;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import com.sun.javafx.scene.control.skin.TableHeaderRow;

import logic.Controller.BookingController;
import logic.Controller.UnloadController;
import logic.Controller.WasteUnloadedController;
import logic.bean.BookingBean;
import logic.bean.CenterOwnerBean;
import logic.bean.UnloadBean;
import logic.bean.WasteUnloadedBean;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

public class BookingANDUnloadsView 
implements Initializable {
	
	private ArrayList<BookingBean> data = new ArrayList<>();
	private ArrayList<WasteUnloadedBean> data1 = new ArrayList<>();
	private ObservableList<BookingBean> booking_list = FXCollections.observableArrayList();
	private ObservableList<WasteUnloadedBean> unload_list = FXCollections.observableArrayList();
	
	@FXML private Button homeButton;
	@FXML private MenuButton ownerButton;
	@FXML private MenuItem ownerProfileItem, logoutItem;
	@FXML private TableView<BookingBean> tableBookingAccepted;
	@FXML private TableView<WasteUnloadedBean> tableRegisteredUnloads;
	@FXML private TableColumn<BookingBean, String> col_id, col_user, col_date, col_time;
	@FXML private TableColumn<WasteUnloadedBean, String> col_user1, col_date1, col_time1, col_waste, col_wasteQuantity, col_ecoPoints;

	private BookingBean book;
	private WasteUnloadedBean waste;
	private UnloadBean unload;
	private WasteUnloadedController control;
	private UnloadController control1;
	private BookingController control2;
	
	public void returnHomepage(ActionEvent event){
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
	
	public void setRowSelected(MouseEvent event) {
		waste = null;
		if (event.getButton().equals(MouseButton.PRIMARY)) {
	        int index = tableRegisteredUnloads.getSelectionModel().getSelectedIndex();
	        waste = tableRegisteredUnloads.getItems().get(index);
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Delete a registered unload");
			alert.setHeaderText(null);
			alert.setContentText("Are you sure you want to delete this unload?");
			Optional<ButtonType> result = alert.showAndWait();
			
			if (result.get() == ButtonType.OK) {
				//cancello lo scarico di un rifiuto e tolgo ecoPoints con trigger
				control = new WasteUnloadedController();
				control.DeleteWasteForAnUnload(waste);
				alert.setAlertType(AlertType.INFORMATION);
				alert.setTitle("Unload waste deleted.");
	    		alert.setHeaderText(null);
	    		alert.setContentText("Unload waste delete for '"+waste.getUser()+"' has been completed successfully.");		
	    		alert.showAndWait();
				

				int count = control.NumberOfWasteForAnUnload(waste);
				if(count==0) {
					//non ho piu niente registrato per quello scarico, lo elimino
			        unload = new UnloadBean();
					unload.setUser(waste.getUser());
					unload.setCenter(waste.getCenter());
					unload.setDate(waste.getDate());
					unload.setTime(waste.getTime());
					control1 = new UnloadController();
			        control1.DeleteAnUnload(unload);
					alert.setAlertType(AlertType.INFORMATION);
					alert.setTitle("Unload deleted.");
		    		alert.setHeaderText(null);
		    		alert.setContentText("Unload delete for '"+unload.getUser()+"' has been completed successfully.\nNo one waste already exists for this unload.");		
		    		alert.showAndWait();
				}
	    		
	    		unload_list.removeAll(unload_list);
	    	    try {
	    	        data1 = control.ListUnloadByCenter(waste);
	    	        unload_list.addAll(data1);
	    	    }
	    	    catch(Exception e){
	    	          e.printStackTrace();
	    	          System.out.println("Error on Building Data");            
	    	    }
	    	    tableRegisteredUnloads.setItems(unload_list);
			}
		}
	}
	
	public void refuseBookingAccepted(MouseEvent event) {
		BookingBean booking = null;
		if (event.getButton().equals(MouseButton.PRIMARY)) {
	        int index = tableBookingAccepted.getSelectionModel().getSelectedIndex();
	        booking = tableBookingAccepted.getItems().get(index);
	        
	        Alert alert = new Alert(AlertType.CONFIRMATION);
	        alert.setTitle("Delete booking accepted");
	        alert.setHeaderText(null);
	        alert.setContentText("Do you want to delete this booking accepted?");
	        Optional<ButtonType> result = alert.showAndWait();
	        
	        if (result.get() == ButtonType.OK){
	    		booking.setStatus("D");
	    		control2 = new BookingController();
	        	control2.ModifyBooking(booking);
	    		alert.setAlertType(AlertType.INFORMATION);
	    		alert.setTitle("Booking refuse completed.");
	    		alert.setHeaderText(null);
	    		alert.setContentText("Booking refused successfully for '"+booking.getUser()+"'.\n\nYou can check the list of booking in wait through\n         'Homepage> Manage Booking'.");		
	    		alert.showAndWait();
	        }
	        
	        book = new BookingBean();
	        book.setCenter(CenterOwnerBean.getOwnerInstance("").getCenter());
	        book.setStatus("A");
	        booking_list.removeAll(booking_list);
		    try {
		        data = control2.BookingListByCenter(book);
		        booking_list.addAll(data);
		    }
		    catch(Exception e){
		          e.printStackTrace();
		          System.out.println("Error on Building Data");            
		    }
			tableBookingAccepted.setItems(booking_list);
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
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		homeButton.setTooltip(new Tooltip("Return to BeEcological Homepage"));
		ownerButton.setText(CenterOwnerBean.getOwnerInstance("").getUsername());
		
		book = new BookingBean();
		book.setCenter(CenterOwnerBean.getOwnerInstance("").getCenter());
		book.setStatus("A");
		booking_list.removeAll(booking_list);
	    try {
	    	control2 = new BookingController();
	        data = control2.BookingListByCenter(book);
	        booking_list.addAll(data);
	    }
	    catch(Exception e){
	          e.printStackTrace();
	          System.out.println("Error on Building Data");            
	    }
	    //riempio le colonne tramite il corrispondente nome dell'attributo dato nella definizione della classe
		col_id.setCellValueFactory(new PropertyValueFactory<>("ID"));
	    col_user.setCellValueFactory(new PropertyValueFactory<>("user"));
		col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
		col_time.setCellValueFactory(new PropertyValueFactory<>("time"));
		tableBookingAccepted.setItems(booking_list);
		
		tableBookingAccepted.widthProperty().addListener(new ChangeListener<Number>()
		{
			@Override
		    public void changed(ObservableValue<? extends Number> source, Number oldWidth, Number newWidth)
		    {
				TableHeaderRow header = (TableHeaderRow) tableBookingAccepted.lookup("TableHeaderRow");
		        header.reorderingProperty().addListener(new ChangeListener<Boolean>() {
		            @Override
		            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		                header.setReordering(false);
		            }
		        });
		    }
		});
		
		waste = new WasteUnloadedBean();
		waste.setCenter(CenterOwnerBean.getOwnerInstance("").getCenter());
		unload_list.removeAll(unload_list);
	    try {
	    	control = new WasteUnloadedController();
	        data1 = control.ListUnloadByCenter(waste);
	        unload_list.addAll(data1);
	    }
	    catch(Exception e){
	          e.printStackTrace();
	          System.out.println("Error on Building Data");            
	    }
	    //riempio le colonne tramite il corrispondente nome dell'attributo dato nella definizione della classe
		col_user1.setCellValueFactory(new PropertyValueFactory<>("user"));
	    col_date1.setCellValueFactory(new PropertyValueFactory<>("date"));
		col_time1.setCellValueFactory(new PropertyValueFactory<>("time"));
		col_waste.setCellValueFactory(new PropertyValueFactory<>("waste"));
		col_wasteQuantity.setCellValueFactory(new PropertyValueFactory<>("wasteQuantity"));
		col_ecoPoints.setCellValueFactory(new PropertyValueFactory<>("ecoPoints"));
		tableRegisteredUnloads.setItems(unload_list);
		
		tableRegisteredUnloads.widthProperty().addListener(new ChangeListener<Number>()
		{
			@Override
		    public void changed(ObservableValue<? extends Number> source, Number oldWidth, Number newWidth)
		    {
				TableHeaderRow header = (TableHeaderRow) tableRegisteredUnloads.lookup("TableHeaderRow");
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
