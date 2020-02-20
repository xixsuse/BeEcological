package logic.view;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import com.sun.javafx.scene.control.skin.TableHeaderRow;

import logic.bean.BookingBean;
import logic.bean.CenterOwnerBean;
import logic.bean.UnloadBean;
import logic.bean.UserBean;
import logic.bean.WasteUnloadedBean;
import logic.controller.BookingController;
import logic.controller.UnloadController;
import logic.controller.UserController;
import logic.controller.WasteUnloadedController;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;


public class RegisterUnloadView implements Initializable {
	
	private ArrayList<BookingBean> data = new ArrayList<>();
	private ArrayList<TextField> data1 = new ArrayList<>();
	private ArrayList<CheckBox> data2 = new ArrayList<>();
	private ObservableList<BookingBean> booking_list = FXCollections.observableArrayList();
	private ObservableList<TextField> wasteQuantity_list = FXCollections.observableArrayList();
	private ObservableList<CheckBox> waste_list = FXCollections.observableArrayList();
	
	private int bookingID;
	private String user_selected, date_selected, hour_selected;
	
	@FXML private Button homeButton, confirmButton;
	@FXML private MenuButton ownerButton;
	@FXML private MenuItem ownerProfileItem, logoutItem;
	@FXML private CheckBox b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14;
	@FXML private TextField f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f11,f12,f13,f14, text_user, text_hour;
	@FXML private TableView<BookingBean> tableBookingAccepted;
	@FXML private TableColumn<BookingBean, String> col_id, col_user, col_date, col_time;
	@FXML private DatePicker text_date;
	
	private UserBean user;
	private UnloadBean unload;
	private WasteUnloadedBean wasteUnloaded;
	private BookingBean booking;
	private UserController control;
	private UnloadController control1;
	private WasteUnloadedController control2;
	private BookingController control3;
	
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
	
	public void getID(ActionEvent event) {
		CheckBox chk = (CheckBox) event.getSource();
		String id = chk.getId();
		switch(id) {
		case "b1":
			textDisabler(event,b1,f1);
		case "b2":
			textDisabler(event,b2,f2);
		case "b3":
			textDisabler(event,b3,f3);
		case "b4":
			textDisabler(event,b4,f4);
		case "b5":
			textDisabler(event,b5,f5);
		case "b6":
			textDisabler(event,b6,f6);
		case "b7":
			textDisabler(event,b7,f7);
		case "b8":
			textDisabler(event,b8,f8);
		case "b9":
			textDisabler(event,b9,f9);
		case "b10":
			textDisabler(event,b10,f10);
		case "b11":
			textDisabler(event,b11,f11);
		case "b12":
			textDisabler(event,b12,f12);
		case "b13":
			textDisabler(event,b13,f13);
		case "b14":
			textDisabler(event,b14,f14);
		}
	}
	
	public void textDisabler(ActionEvent theEvent,CheckBox theBox, TextField theField) {
		if (theBox.isSelected()) {
			theField.setDisable(false);
		}
		else {
			theField.setDisable(true);
			theField.clear();
		}
	}
	
	public boolean checkTime(String hour){
	    try {
	        LocalTime.parse(hour);
	        System.out.println("Valid time string: " + hour);
	        return true;
	    }
	    catch (DateTimeParseException | NullPointerException e) {
	        System.out.println("Invalid time string: " + hour);
	        return false;
	    }
	}
	
	public void setRowSelected(MouseEvent event) {
		BookingBean booking = null;
		if (event.getButton().equals(MouseButton.PRIMARY)) {
	        int index = tableBookingAccepted.getSelectionModel().getSelectedIndex();
	        booking = tableBookingAccepted.getItems().get(index);
			text_user.setText(booking.getBbUser());
	        text_hour.setText(booking.getBbTime());
			text_date.setValue(LocalDate.parse(booking.getBbDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			
			bookingID = booking.getBbId();
			user_selected = booking.getBbUser();
			hour_selected = booking.getBbTime();
			date_selected = booking.getBbDate();
		}
	}

	public void confirmRegistrationUnload(ActionEvent event) {
		boolean res;
		user = new UserBean();
		control = new UserController();
		user.setUsbUsername(text_user.getText());
		LocalDate date = text_date.getValue();
		String time = text_hour.getText();
		waste_list.removeAll(waste_list);
		wasteQuantity_list.removeAll(wasteQuantity_list);
		
		Alert alert = new Alert(AlertType.ERROR);		
		res = control.checkRegistration(user);
		//se true username non esiste, non posso aggiungere prenotazione
		if (res) {
			alert.setTitle("Invalid unload registration.");
			alert.setHeaderText(null);
			alert.setContentText("The username inserted is not registered to BeEcological. Retry.");		
			alert.showAndWait();
			return;
		}
		
		if (date == null || time.isEmpty()) {
			alert.setTitle("Invalid unload registration.");
			alert.setHeaderText(null);
			alert.setContentText("Date and time incorrectly.");		
			alert.showAndWait();
			return;
		}
		
		if (!checkTime(time)) {
			alert.setTitle("Invalid unload registration.");
			alert.setHeaderText(null);
			alert.setContentText("Insert a correct time format: [HH:MM]");		
			alert.showAndWait();
			return;
		}
		
		wasteQuantity_list.addAll(f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f11,f12,f13,f14);
		waste_list.addAll(b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14);
		data1.addAll(wasteQuantity_list);
		data2.addAll(waste_list);
		
		unload = new UnloadBean();
		control1 = new UnloadController();
		unload.setUbUser(user.getUsbUsername());
		unload.setUbCenter(CenterOwnerBean.instance.getCenter());
		unload.setUbDate(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		unload.setUbTime(time);
		control1.insertAnUnload(unload);
		
		int i, quantity;
		String waste;
		boolean check = false;
		
		for(i=0;i<14;i++) {
			if(!data1.get(i).getText().isEmpty()) {
				waste = data2.get(i).getText();
				quantity = Integer.parseInt(data1.get(i).getText());
				wasteUnloaded = new WasteUnloadedBean();
				control2 = new WasteUnloadedController();
				wasteUnloaded.setWbUser(user.getUsbUsername());
				wasteUnloaded.setWbCenter(CenterOwnerBean.instance.getCenter());
				wasteUnloaded.setWbDate(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				wasteUnloaded.setWbTime(time);
				wasteUnloaded.setWbWaste(waste);
				wasteUnloaded.setWbWasteQuantity(quantity);
				control2.insertWasteForAnUnload(wasteUnloaded);
				check = true;
			}
		}
		//se check rimane false vuol dire che non ho messo alcun rifiuto per lo scarico, quindi elimino l'unload registrato
		if (!check) {
			control1.deleteAnUnload(unload);
			alert.setTitle("Invalid unload registration.");
			alert.setHeaderText(null);
			alert.setContentText("Cannot register an unload without any waste. Retry.");		
			alert.showAndWait();
			return;
		}
		alert.setAlertType(AlertType.INFORMATION);
		alert.setTitle("Unload registration complete.");
		alert.setHeaderText(null);
		alert.setContentText("Unload registration for '"+text_user.getText()+"' has been completed successfully.");		
		alert.showAndWait();

		//se era una prenotazione presente nella griglia la salvo come registrata
		if(user.getUsbUsername().equals(user_selected) && date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).equals(date_selected) && time.equals(hour_selected )) {
			booking = new BookingBean();
			control3 = new BookingController();
			booking.setBbId(bookingID);
			booking.setBbStatus("R");
			control3.modifyBooking(booking);
			
			booking_list.removeAll(booking_list);
		    try {
		    	booking.setBbCenter(CenterOwnerBean.instance.getCenter());
		    	booking.setBbStatus("A");
		    	data = control3.bookingListByCenter(booking);	//prenotazioni accettate dal gestore
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
		}
		text_user.clear();
		text_hour.clear();
		text_date.setValue(null);
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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		homeButton.setTooltip(new Tooltip("Return to BeEcological Homepage"));
		ownerButton.setText(CenterOwnerBean.getOwnerInstance("").getCobUsername());
		booking_list.removeAll(booking_list);
	    try {
	    	booking = new BookingBean();
	    	control3 = new BookingController();
	    	booking.setBbCenter(CenterOwnerBean.instance.getCenter());
	    	booking.setBbStatus("A");
	    	data = control3.bookingListByCenter(booking);	//prenotazioni accettate dal gestore
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
	}
	
}
