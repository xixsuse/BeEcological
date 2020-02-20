package test.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import error.InexistentUsernameException;
import logic.Bean.BookingBean;
import logic.Controller.BookingController;

public class TestRegistration {
	
	@Test
	public void testRegistration() {
		String message = "";
		String username = "incorrectUser";
		String center = "la vecchia mola";
		String date = "2020/03/15";
		String time = "11:00";
		String status = "W"; //request booking (in wait to be accepted)
		int count;
		boolean result;
		
		BookingBean bookingBean = new BookingBean();
		bookingBean.setUser(username);
		bookingBean.setCenter(center);
		bookingBean.setDate(date);
		bookingBean.setTime(time);
		bookingBean.setStatus(status);
		
		BookingController bookingController = new BookingController();
		
		try {
			bookingController.InsertBooking(bookingBean);
		} catch (InexistentUsernameException e) {
			message = "Username not valid";
		}
		
		count = bookingController.VerifyBooking(bookingBean);
		if (count==0) {
			//book invalid
			result = false;
		}
		else {
			//book insert
			result = true;
		}
		
		assertEquals(message, true, result);
	}
}