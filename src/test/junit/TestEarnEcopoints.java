package test.junit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import logic.Bean.UserBean;
import logic.Bean.WasteUnloadedBean;
import logic.Controller.UserController;
import logic.Controller.WasteUnloadedController;

public class TestEarnEcopoints {
	
	private UserBean userBean;
	private WasteUnloadedBean wasteBean;
	private UserController userController = new UserController();
	private WasteUnloadedController wasteController = new WasteUnloadedController();
	
	@Before
	public void prepareData() {
		String username = "sixpain";
		String center = "la vecchia mola";
		String date = "2020-01-02";
		String time = "15:53";
		String waste = "Plastic packaging";
		int wasteQuantity = 5;	//kg of waste unloaded
		
		userBean = new UserBean();
		wasteBean = new WasteUnloadedBean();
		
		userBean.setUsername(username);
		wasteBean.setUser(username);
		wasteBean.setCenter(center);
		wasteBean.setDate(date);
		wasteBean.setTime(time);
		wasteBean.setWaste(waste);
		wasteBean.setWasteQuantity(wasteQuantity);
	}
	
	@Test
	public void testAddEcopoints() {
		int oldPoints, newPoints;
		int pointsObtained = 5*3; //3 is the value_per_kg of plastic packaging

		List<String> info = userController.UserInformation(userBean);
		oldPoints = Integer.parseInt(info.get(4));
		int expected = oldPoints + pointsObtained;
		
		wasteController.InsertWasteForAnUnload(wasteBean);
		info = userController.UserInformation(userBean);
		newPoints = Integer.parseInt(info.get(4));
		
		String message = "expected: "+expected+" new: "+newPoints;
		assertEquals(message, expected, newPoints);
	}
	
	@After
	public void deleteUnload() {
		wasteController.DeleteWasteForAnUnload(wasteBean);
	}
}