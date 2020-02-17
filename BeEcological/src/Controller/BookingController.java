package Controller;



import java.util.ArrayList;

import Bean.BookingBean;
import Model.Booking;
import Model.BookingDAO;

public class BookingController {
	
	public BookingController() {}
	
	public int VerifyBooking(BookingBean bookingBean) {
		Booking booking = new Booking(bookingBean.getUser(), bookingBean.getCenter(), bookingBean.getDate(), 
				bookingBean.getTime(), bookingBean.getStatus());
		int result = BookingDAO.existingBooking(booking);
		return result;
	}
	
	public void InsertBooking(BookingBean bookingBean) {
		Booking booking = new Booking(bookingBean.getUser(), bookingBean.getCenter(), bookingBean.getDate(), 
				bookingBean.getTime(), bookingBean.getStatus());
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
			bookB.setID(book.getID());
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
			bookB.setID(book.getID());
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