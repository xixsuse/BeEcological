package logic.View;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
//import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.sun.javafx.scene.control.skin.TableHeaderRow;

import error.InexistentUsernameException;
import logic.Bean.BookingBean;
import logic.Bean.CenterOwnerBean;
import logic.Bean.UserBean;
import logic.Controller.BookingController;
import logic.Controller.OwnerController;
import logic.Controller.UserController;
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
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;


public class ManageBookingView implements Initializable {
	
	ArrayList<BookingBean> data = new ArrayList<>();
	ObservableList<BookingBean> booking_list = FXCollections.observableArrayList();
	String invalidBooking = "Invalid booking request.";
	String okBooking = "Booking request completed.";
	String insertSuccess = "' has been completed successfully.\n\nYou can check the list of booking accepted through\n         'Homepage> History & Unloads'.";
	String bookInsert = "Booking insert for '";
	String errorData = "Error on Building Data";
	
	@FXML private Button homeButton;
	@FXML private MenuButton ownerButton;
	@FXML private MenuItem ownerProfileItem, logoutItem;
	@FXML private DatePicker dateToBook;
	@FXML private TextField userToBook, hourToBook;
	@FXML private TableView<BookingBean> tableBookingRequest;
	@FXML private TableColumn<BookingBean, String> col_user, col_date, col_time;
	
	@FXML private CenterOwnerBean owner;
	@FXML private UserBean user;
	@FXML private BookingBean booking;
	@FXML private OwnerController control;
	@FXML private UserController control1;
	@FXML private BookingController control2;

	
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
	
	public void saveBookingRequest(ActionEvent event) {
		boolean res = true;
		booking = new BookingBean();
		user = new UserBean();
		owner = new CenterOwnerBean();
		
		control = new OwnerController();
		
		owner.setCobUsername(CenterOwnerBean.getOwnerInstance("").getCobUsername());
		user.setUsbUsername(userToBook.getText());
		List<String> ownerData = control.ownerData(owner);
		String center = ownerData.get(4);
		LocalDate date = dateToBook.getValue();
		String time = hourToBook.getText();
		
		control1 = new UserController();
		
		Alert alert = new Alert(AlertType.ERROR);		
		res = control1.checkRegistration(user);
		//se true username non esiste, non posso aggiungere prenotazione
		if (res) {
			alert.setTitle(invalidBooking);
			alert.setHeaderText(null);
			alert.setContentText("The username inserted is not registered to BeEcological. Retry.");		
			alert.showAndWait();
			return;
		}
		
		if (date == null || time.isEmpty()) {
			alert.setTitle(invalidBooking);
			alert.setHeaderText(null);
			alert.setContentText("Date and time incorrectly.");		
			alert.showAndWait();
			return;
		}
		
		if (!tool.checkDate(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))) {
			alert.setTitle(invalidBooking);
			alert.setHeaderText(null);
			alert.setContentText("You cannot insert a booking request for a day in the past. Choose a correct date.");		
			alert.showAndWait();
			return;
		}
		if (!tool.checkTime(time)) {
			alert.setTitle(invalidBooking);
			alert.setHeaderText(null);
			alert.setContentText("Insert a correct time format: [HH:MM]");		
			alert.showAndWait();
			return;
		}
		
		booking.setBbUser(user.getUsbUsername());
		booking.setBbCenter(center);
		booking.setBbDate(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		booking.setBbTime(time);
		
		booking.setBbStatus("W");
		control2 = new BookingController();
		int count = control2.verifyBooking(booking);
    	if(count!=0) {
    		//esiste prenotazione, la aggiorno accettandola
    		booking.setBbStatus("A");
    		control2.modifyBooking(booking);
    		alert.setAlertType(AlertType.INFORMATION);;
    		alert.setTitle(okBooking);
    		alert.setHeaderText(null);
    		alert.setContentText(bookInsert+booking.getBbUser()+insertSuccess);		
    		alert.showAndWait();
	        booking_list.removeAll(booking_list);
		    try {
		        booking.setBbCenter(CenterOwnerBean.instance.getCobCenter());
		        booking.setBbStatus("W");
		    	data = control2.bookingListByCenter(booking); //richieste di prenotazione da accettare
		        booking_list.addAll(data);
		    }
		    catch(Exception e){
		          e.printStackTrace();
		          System.out.println(errorData);            
		    }
			tableBookingRequest.setItems(booking_list);
    		return;
    	}
		
    	booking.setBbStatus("A");
		count = control2.verifyBooking(booking);
		if (count!=0) {
			//la prenotazione gi� � stata accettata
			alert.setAlertType(AlertType.INFORMATION);;
			alert.setTitle("Booking request not valid.");
			alert.setHeaderText(null);
			alert.setContentText(bookInsert+booking.getBbUser()+"' already exists. Retry.");		
			alert.showAndWait();
			return;
		}
		//la prenotazione non esiste
		try {
			control2.insertBooking(booking);
		} catch (InexistentUsernameException e) {

		}
		alert.setAlertType(AlertType.INFORMATION);;
		alert.setTitle(okBooking);
		alert.setHeaderText(null);
		alert.setContentText(bookInsert+booking.getBbUser()+insertSuccess);		
		alert.showAndWait();
	}
	
	public void confirmBookingRequest(MouseEvent event) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
	        int index = tableBookingRequest.getSelectionModel().getSelectedIndex();
	        BookingBean booking = tableBookingRequest.getItems().get(index);
	        
	        control2 = new BookingController();
	        
	        Alert alert = new Alert(AlertType.CONFIRMATION);
	        alert.setTitle("Validate booking request");
	        alert.setHeaderText(null);
	        alert.setContentText("Do you want to confirm this booking?");

	        ButtonType buttonTypeYes = new ButtonType("Yes");
	        ButtonType buttonTypeNo = new ButtonType("No");
	        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

	        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo, buttonTypeCancel);

	        Optional<ButtonType> result = alert.showAndWait();
	        Alert alert1 = new Alert(AlertType.INFORMATION);
	        if (result.get() == buttonTypeYes){
	    		booking.setBbStatus("A");
	        	control2.modifyBooking(booking);
	    		alert1.setTitle(okBooking);
	    		alert1.setHeaderText(null);
	    		alert1.setContentText(bookInsert+booking.getBbUser()+insertSuccess);		
	    		alert1.showAndWait();
	        } else if (result.get() == buttonTypeNo) {
	            booking.setBbStatus("D");
	        	control2.modifyBooking(booking);
	    		alert1.setTitle("Booking request refused.");
	    		alert1.setHeaderText(null);
	    		alert1.setContentText("Booking refused for '"+booking.getBbUser()+"' has been completed successfully");		
	    		alert1.showAndWait();
	        } else {
	            //do nothing
	        }
	        booking_list.removeAll(booking_list);
		    try {
		        booking.setBbCenter(CenterOwnerBean.instance.getCobCenter());
		        booking.setBbStatus("W");
		    	data = control2.bookingListByCenter(booking); //richieste di prenotazione da accettare
		        booking_list.addAll(data);
		    }
		    catch(Exception e){
		          e.printStackTrace();
		          System.out.println(errorData);            
		    }
			tableBookingRequest.setItems(booking_list);
		}
	}
	
	public void gotoOwnerProfile(ActionEvent event) {
		try {
			//ricavo lo stage dal menuButton, poich� il menuItem non � una sottoclasse di Node
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
		try {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Logout");
			alert.setHeaderText(null);
			alert.setContentText("Are you sure you want to logout?");
			Optional<ButtonType> result = alert.showAndWait();
			
			if (result.get() == ButtonType.OK){
				System.out.println("diomerda");
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
				window.show();
			} else {
			    //do nothing
			}

		}catch(Exception e){
			e.printStackTrace();
		} 
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		homeButton.setTooltip(new Tooltip("Return to BeEcological Homepage"));
		ownerButton.setText(CenterOwnerBean.getOwnerInstance("").getCobUsername());
		booking_list.removeAll(booking_list);
		try {
	    	booking = new BookingBean();
	    	control2 = new BookingController();
	    	booking.setBbCenter(CenterOwnerBean.getOwnerInstance("").getCobCenter());
	    	booking.setBbStatus("W");
	        control2 = new BookingController();
	    	data = control2.bookingListByCenter(booking); //richieste di prenotazione da accettare
	        booking_list.addAll(data);
	    }
	    catch(Exception e){
	          e.printStackTrace();
	          System.out.println(errorData);            
	    }
	    //riempio le colonne tramite il corrispondente nome dell'attributo dato nella definizione della classe
	    col_user.setCellValueFactory(new PropertyValueFactory<>("user"));
		col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
		col_time.setCellValueFactory(new PropertyValueFactory<>("time"));
		tableBookingRequest.setItems(booking_list);
		
		//blocco dello spostamento delle colonne della tabella
		tableBookingRequest.widthProperty().addListener(new ChangeListener<Number>()
		{
			@Override
		    public void changed(ObservableValue<? extends Number> source, Number oldWidth, Number newWidth)
		    {
				TableHeaderRow header = (TableHeaderRow) tableBookingRequest.lookup("TableHeaderRow");
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
