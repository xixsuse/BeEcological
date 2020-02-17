package Controller;

import java.util.List;

import Bean.UserBean;
import Model.User;
import Model.UserDAO;

public class UserController {
	
	public UserController() {}
	
	public boolean Login(UserBean userBean) {
		User user = new User(userBean.getUsername());
		user.setPassword(userBean.getPassword());
		boolean result = UserDAO.verifyLogin(user);
		return result;
	}
	
	public boolean CheckRegistration(UserBean userBean) {
		boolean result = UserDAO.checkUsername(userBean.getUsername());
		return result;
	}
	
	public void SaveRegistration(UserBean userBean) {
		User user = new User(userBean.getName(), userBean.getSurname(), userBean.getEmailAddress(), userBean.getPhoneNumber(), 
				userBean.getUsername(), userBean.getPassword(), userBean.getEcopoints());
		UserDAO.saveUser(user);
	}
	
	public List<String> UserInformation(UserBean userBean) {
		User user = new User(userBean.getUsername());
		List<String> result = UserDAO.userInfo(user);
		return result;
	}

	public void UpdateEcoPoints(UserBean userBean) {
		User user = new User(userBean.getUsername());
		user.setEcopoints(userBean.getEcopoints());
		UserDAO.updateUserEcoPoints(user.getUsername(), user.getEcopoints());
	}
	
	public void deleteAccount(UserBean userBean) {
		User user = new User(userBean.getUsername());
		UserDAO.deleteUserAccount(user.getUsername());
	}
}
