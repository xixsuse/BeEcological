package logic.view;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.sun.javafx.scene.control.skin.TableHeaderRow;

import logic.bean.BookingBean;
import logic.bean.UserBean;
import logic.controller.BookingController;
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
import javafx.scene.control.Tooltip;
import javafx.scene.text.Text;
import javafx.stage.Stage;


@SuppressWarnings({ "unused" })
public class UserBookingListView implements Initializable {
	
	String errorData = "Error on Building Data";
	String tableHeader = "TableHeaderRow";
	String centerString = "center";
	
	private ArrayList<BookingBean> data = new ArrayList<>();
	private ObservableList<BookingBean> booking_wait_list = FXCollections.observableArrayList();
	private ObservableList<BookingBean> booking_accept_list = FXCollections.observableArrayList();
	private ObservableList<BookingBean> booking_refuse_list = FXCollections.observableArrayList();
	
	@FXML private MenuItem userProfileItem, logoutItem;
	@FXML private MenuButton userButton;
	@FXML private Group userGroup;
	@FXML private Button homeButton, searchButton, seeBookingButton, editDataButton, changeLoginButton, deleteAccountButton;
	@FXML private Text userNick, ecoPoints, name, surname, email, phoneNumber;
	@FXML private TableView<BookingBean> refusedTable, acceptedTable, waitingTable;
	@FXML private TableColumn<BookingBean, String> col_center, col_date, col_time, col_center1, col_date1, col_time1, col_center2, col_date2, col_time2;
	
	private BookingBean booking;
	private BookingController control;
	
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
			controller.userButton.setText(UserBean.getUserInstance("").getUsbUsername());
			controller.circleUserGroup.setVisible(true);
			controller.circleOwnerGroup.setVisible(false);
			controller.welcomebackText.setText("Welcome back, "+UserBean.getUserInstance("").getUsbUsername());
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
			//ricavo lo stage dal menuButton, poichè il menuItem non è una sottoclasse di Node
			Stage window = (Stage) userButton.getScene().getWindow();
		    URL url = new File("src/fxml/UserProfile.fxml").toURI().toURL();
		    FXMLLoader loader = new FXMLLoader(url);
			Parent tableViewParent = loader.load();
			Scene tableViewScene = new Scene(tableViewParent);
			window.setScene(tableViewScene);
			window.setScene(tableViewScene);
			window.setTitle("BeEcological - Profile");
			System.out.println(UserBean.getUserInstance("").getUsbUsername());
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
	
	

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		homeButton.setTooltip(new Tooltip("Return to BeEcological Homepage"));
		userGroup.setVisible(true);
		booking = new BookingBean();
		booking.setBbUser(UserBean.usbInstance.getUsbUsername());
		booking.setBbStatus("W");
		userNick.setText(booking.getBbUser());
		userButton.setText(booking.getBbUser());
		
		booking_wait_list.removeAll(booking_wait_list);
	    try {
			control = new BookingController();
	    	data = control.bookingListByUser(booking); //richieste di prenotazione in attesa
	        booking_wait_list.addAll(data);
	    }
	    catch(Exception e){
	          e.printStackTrace();
	          System.out.println(errorData);            
	    }
	    //riempio le colonne tramite il corrispondente nome dell'attributo dato nella definizione della classe
	    col_center.setCellValueFactory(new PropertyValueFactory<>(centerString));
		col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
		col_time.setCellValueFactory(new PropertyValueFactory<>("time"));
		waitingTable.setItems(booking_wait_list);
		
		//blocco dello spostamento delle colonne della tabella
		waitingTable.widthProperty().addListener(new ChangeListener<Number>()
		{
			@Override
		    public void changed(ObservableValue<? extends Number> source, Number oldWidth, Number newWidth)
		    {
				TableHeaderRow header = (TableHeaderRow) waitingTable.lookup(tableHeader);
		        header.reorderingProperty().addListener(new ChangeListener<Boolean>() {
		            @Override
		            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		                header.setReordering(false);
		            }
		        });
		    }
		});
		
		booking_accept_list.removeAll(booking_accept_list);
	    try {
	    	booking.setBbStatus("A");
	        data = control.bookingListByUser(booking);	//richieste di prenotazione accettate
	        booking_accept_list.addAll(data);
	    }
	    catch(Exception e){
	          e.printStackTrace();
	          System.out.println(errorData);            
	    }
	    //riempio le colonne tramite il corrispondente nome dell'attributo dato nella definizione della classe
	    col_center1.setCellValueFactory(new PropertyValueFactory<>(centerString));
		col_date1.setCellValueFactory(new PropertyValueFactory<>("date"));
		col_time1.setCellValueFactory(new PropertyValueFactory<>("time"));
		acceptedTable.setItems(booking_accept_list);
		
		//blocco dello spostamento delle colonne della tabella
		acceptedTable.widthProperty().addListener(new ChangeListener<Number>()
		{
			@Override
		    public void changed(ObservableValue<? extends Number> source, Number oldWidth, Number newWidth)
		    {
				TableHeaderRow header = (TableHeaderRow) acceptedTable.lookup(tableHeader);
		        header.reorderingProperty().addListener(new ChangeListener<Boolean>() {
		            @Override
		            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		                header.setReordering(false);
		            }
		        });
		    }
		});

		booking_refuse_list.removeAll(booking_refuse_list);
	    try {
	    	booking.setBbStatus("D");
	    	data = control.bookingListByUser(booking);
	        booking_refuse_list.addAll(data);
	    }
	    catch(Exception e){
	          e.printStackTrace();
	          System.out.println(errorData);            
	    }
	    //riempio le colonne tramite il corrispondente nome dell'attributo dato nella definizione della classe
	    col_center2.setCellValueFactory(new PropertyValueFactory<>(centerString));
		col_date2.setCellValueFactory(new PropertyValueFactory<>("date"));
		col_time2.setCellValueFactory(new PropertyValueFactory<>("time"));
		refusedTable.setItems(booking_refuse_list);
		
		//blocco dello spostamento delle colonne della tabella
		refusedTable.widthProperty().addListener(new ChangeListener<Number>()
		{
			@Override
		    public void changed(ObservableValue<? extends Number> source, Number oldWidth, Number newWidth)
		    {
				TableHeaderRow header = (TableHeaderRow) refusedTable.lookup(tableHeader);
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
