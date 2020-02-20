package logic.Controller;



import java.util.ArrayList;

import error.InexistentUsernameException;
import logic.Model.Booking;
import logic.Model.BookingDAO;
import logic.Model.UserDAO;
import logic.bean.BookingBean;

public class BookingController {
	
	public BookingController() {}
	
	public int VerifyBooking(BookingBean bookingBean) {
		Booking booking = new Booking(bookingBean.getUser(), bookingBean.getCenter(), bookingBean.getDate(), 
				bookingBean.getTime(), bookingBean.getStatus());
		int result = BookingDAO.existingBooking(booking);
		return result;
	}
	
	public void InsertBooking(BookingBean bookingBean) throws InexistentUsernameException {
		Booking booking = new Booking(bookingBean.getUser(), bookingBean.getCenter(), bookingBean.getDate(), 
				bookingBean.getTime(), bookingBean.getStatus());
		if(UserDAO.checkUsername(bookingBean.getUser())) {
			throw new InexistentUsernameException();
		}
		BookingDAO.makeBooking(booking);
	}
	
	public void ModifyBooking(BookingBean bookingBean) {
		Booking booking = new Booking(bookingBean.getUser(), bookingBean.getCenter(), bookingBean.getDate(), 
				bookingBean.getTime(), bookingBean.getStatus());
		BookingDAO.updateBooking(booking);
	}
	
	public ArrayList<BookingBean> BookingListByCenter(BookingBean bookingBean) {
		ArrayList<BookingBean> listOfBookingBean = new ArrayList<>();
		ArrayList<Booking> listOfBooking = BookingDAO.listOfBookingByCenter(bookingBean.getCenter(), bookingBean.getStatus());
		for(Booking book : listOfBooking) {
			BookingBean bookB = new BookingBean();
			bookB.setId(book.getID());
			bookB.setUser(book.getUser());
			bookB.setCenter(book.getCenter());
			bookB.setDate(book.getDate());
			bookB.setTime(book.getTime());
			bookB.setStatus(book.getStatus());
			listOfBookingBean.add(bookB);
		}
		return listOfBookingBean;
	}
	
	public ArrayList<BookingBean> BookingListByUser(BookingBean bookingBean) {
		ArrayList<BookingBean> listOfBookingBean = new ArrayList<>();
		ArrayList<Booking> listOfBooking = BookingDAO.listOfBookingByUser(bookingBean.getUser(), bookingBean.getStatus());
		for(Booking book : listOfBooking) {
			BookingBean bookB = new BookingBean();
			bookB.setId(book.getID());
			bookB.setUser(book.getUser());
			bookB.setCenter(book.getCenter());
			bookB.setDate(book.getDate());
			bookB.setTime(book.getTime());
			bookB.setStatus(book.getStatus());
			listOfBookingBean.add(bookB);
		}
		return listOfBookingBean;
	}
}