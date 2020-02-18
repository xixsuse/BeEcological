package Controller;

import java.util.List;

import Bean.CenterOwnerBean;
import Bean.UnloadBean;
import Model.CenterOwner;
import Model.CenterOwnerDAO;
import Model.Unload;
import Model.UnloadDAO;

public class UnloadController {
	
	public UnloadController() {}
	
	public void InsertAnUnload(UnloadBean unloadBean) {
		Unload unload = new Unload(unloadBean.getUser(), unloadBean.getCenter(), unloadBean.getDate(), unloadBean.getTime());
		UnloadDAO.saveUnload(unload);
	}
	
	public void DeleteAnUnload(UnloadBean unloadBean) {
		Unload unload = new Unload(unloadBean.getUser(), unloadBean.getCenter(), unloadBean.getDate(), unloadBean.getTime());
		UnloadDAO.deleteUnload(unload);
	}
	
	public List<String> OwnerData(CenterOwnerBean ownerBean) {
		CenterOwner owner = new CenterOwner(ownerBean.getUsername());
		List<String> result = CenterOwnerDAO.ownerInfo(owner);
		return result;
	}
}