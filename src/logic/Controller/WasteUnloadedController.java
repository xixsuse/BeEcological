package logic.Controller;


import java.util.ArrayList;

import logic.Model.WasteUnloaded;
import logic.Model.WasteUnloadedDAO;
import logic.bean.UserBean;
import logic.bean.WasteUnloadedBean;

public class WasteUnloadedController {
	
	public WasteUnloadedController() {}
	
	public void InsertWasteForAnUnload(WasteUnloadedBean wasteBean) {
		WasteUnloaded waste = new WasteUnloaded(wasteBean.getUser(), wasteBean.getCenter(), wasteBean.getDate(), 
				wasteBean.getTime(), wasteBean.getWaste(), wasteBean.getWasteQuantity());
		WasteUnloadedDAO.registerWasteForUnload(waste);
	}
	
	public void DeleteWasteForAnUnload(WasteUnloadedBean wasteBean) {
		WasteUnloaded waste = new WasteUnloaded(wasteBean.getUser(), wasteBean.getCenter(), wasteBean.getDate(), 
				wasteBean.getTime(), wasteBean.getWaste(), wasteBean.getWasteQuantity());
		WasteUnloadedDAO.deleteWasteUnloaded(waste);
	}
	
	public int NumberOfWasteForAnUnload(WasteUnloadedBean wasteBean) {
		int count;
		WasteUnloaded waste = new WasteUnloaded(wasteBean.getUser(), wasteBean.getCenter(), wasteBean.getDate(), 
				wasteBean.getTime(), wasteBean.getWaste(), wasteBean.getWasteQuantity());
		count = WasteUnloadedDAO.wasteForAnUnload(waste);
		return count;
	}
	
	public ArrayList<WasteUnloadedBean> ListUnloadByCenter(WasteUnloadedBean wasteBean) {
		ArrayList<WasteUnloadedBean> listOfUnloadBean = new ArrayList<>();
		ArrayList<WasteUnloaded> listOfUnload = WasteUnloadedDAO.listOfUnloadRegisteredByCenter(wasteBean.getCenter());
		for(WasteUnloaded waste : listOfUnload) {
			WasteUnloadedBean wasteB = new WasteUnloadedBean();
			wasteB.setUser(waste.getUser());
			wasteB.setCenter(waste.getCenter());
			wasteB.setDate(waste.getDate());
			wasteB.setTime(waste.getTime());
			wasteB.setWaste(waste.getWaste());
			wasteB.setWasteQuantity(waste.getWasteQuantity());
			wasteB.setEcoPoints(waste.getEcoPoints());
			listOfUnloadBean.add(wasteB);
		}
		return listOfUnloadBean;
	}
	
	public ArrayList<WasteUnloadedBean> ListUnloadByUser(UserBean userBean) {
		ArrayList<WasteUnloadedBean> listOfUnloadBean = new ArrayList<>();
		ArrayList<WasteUnloaded> listOfUnload = WasteUnloadedDAO.listOfUnloadRegisteredByUser(userBean.getUsername());
		for(WasteUnloaded waste : listOfUnload) {
			WasteUnloadedBean wasteB = new WasteUnloadedBean();
			wasteB.setUser(waste.getUser());
			wasteB.setCenter(waste.getCenter());
			wasteB.setDate(waste.getDate());
			wasteB.setTime(waste.getTime());
			wasteB.setWaste(waste.getWaste());
			wasteB.setWasteQuantity(waste.getWasteQuantity());
			wasteB.setEcoPoints(waste.getEcoPoints());
			listOfUnloadBean.add(wasteB);
		}
		return listOfUnloadBean;
	}
}