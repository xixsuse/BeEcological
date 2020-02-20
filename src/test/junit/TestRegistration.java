package test.junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import error.AlreadyUsedUsernameException;
import error.EmptyFieldException;
import error.InvalidEmailException;
import error.ShortPasswordException;
import logic.Bean.UserBean;
import logic.Controller.UserController;

public class TestRegistration {
	
	UserBean userBean;
	UserController userController = new UserController();
	boolean result;
	
	@Before
	public void prepareData() {
		String name = "name";
		String surname = "surname";
		String email = "newEmail@gmail.com";	
		String phone = "3333333333";
		String username = "sixpain";
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
		result = true;
		
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
		} catch (AlreadyUsedUsernameException e) {
			message = "Username already exists";
			result = false;
		}
		assertEquals(message, true, result);
	}
	
	@After
	public void deleteRegister() {
		if(result) {
			userController.deleteAccount(userBean);
		}
	}
}