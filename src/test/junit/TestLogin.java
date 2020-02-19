package test.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import logic.Bean.UserBean;
import logic.Controller.UserController;

class TestLogin {
	
	private UserController userController;
	
	@Test
	public void testLogin() {
		String username = "stormjack";
		String pass = "11111111";
		boolean result;
		
		UserBean userBean = new UserBean();
		userBean.setUsername(username);
		userBean.setPassword(pass);
		
		userController = new UserController();
		result = userController.Login(userBean);
		
		assertEquals(true, result);
	}		
}