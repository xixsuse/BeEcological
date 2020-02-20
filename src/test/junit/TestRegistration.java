package test.junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import error.EmptyFieldException;
import error.InvalidEmailException;
import error.ShortPasswordException;
import logic.Bean.UserBean;
import logic.Controller.UserController;

public class TestRegistration {
	
	UserBean userBean;
	UserController userController = new UserController();
	
	@Before
	public void prepareData() {
		String name = "name";
		String surname = "surname";
		String email = "newEmail@gmail.com";	
		String phone = "3333333333";
		String username = "newUsername";
		String password = "newPassword";
		userBean = new UserBean();
		userBean.setUsername(username);
		userBean.setName(name);
		userBean.setSurname(surname);
		userBean.setEmailAddress(email);
		userBean.setPhoneNumber(phone);
		userBean.setPassword(password);
	}
	
	@Test
	public void testRegistration() {
		String message = "";
		boolean expected = true;
		boolean result = userController.CheckRegistration(userBean);
		
		try {
			userController.SaveRegistration(userBean);
		} catch (EmptyFieldException e){
			message = "Empty field";
			result = false;
		} catch (ShortPasswordException e) {
			message = "Password too short";
			result = false;
		} catch (InvalidEmailException e) {
			message = "Invalid email";
			result = false;
		}
		assertEquals(message, expected, result);
	}
	
	@After
	public void deleteRegister() {
		userController.deleteAccount(userBean);
	}
}