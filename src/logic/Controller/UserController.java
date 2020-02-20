package logic.Controller;

import java.util.List;

import error.AlreadyUsedUsernameException;
import error.EmptyFieldException;
import error.InexistentUsernameException;
import error.InvalidEmailException;
import error.ShortPasswordException;
import logic.Model.User;
import logic.Model.UserDAO;
import logic.bean.UserBean;

public class UserController {
	
	public UserController() {}
	
	public boolean Login(UserBean userBean) throws InexistentUsernameException, EmptyFieldException {
		User user = new User(userBean.getUsername());
		user.setPassword(userBean.getPassword());
		if(userBean.getUsername().length() == 0 || userBean.getPassword().length() == 0) {
			throw new EmptyFieldException();
		}
		if(UserDAO.checkUsername(userBean.getUsername())) {
			throw new InexistentUsernameException();
		}
		boolean result = UserDAO.verifyLogin(user);
		return result;
	}
	
	public boolean CheckRegistration(UserBean userBean) {
		boolean result = UserDAO.checkUsername(userBean.getUsername());
		return result;
	}
	
	public void SaveRegistration(UserBean userBean) throws EmptyFieldException, ShortPasswordException, InvalidEmailException, AlreadyUsedUsernameException {
		User user = new User(userBean.getName(), userBean.getSurname(), userBean.getEmailAddress(), userBean.getPhoneNumber(), 
				userBean.getUsername(), userBean.getPassword(), userBean.getEcopoints());
		if(userBean.getPassword().length() == 0) {
			throw new EmptyFieldException();
		}
		if(userBean.getPassword().length() < 8) {
			throw new ShortPasswordException();
		}
		if(!userBean.getEmailAddress().contains("@")) {
			throw new InvalidEmailException();
		}
		if(!UserDAO.checkUsername(userBean.getUsername())) {
			throw new AlreadyUsedUsernameException();
		}
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
