package logic.Controller;

import java.util.List;

import logic.Bean.CenterOwnerBean;
import logic.Bean.UnloadBean;
import logic.Model.CenterOwner;
import logic.Model.CenterOwnerDAO;
import logic.Model.Unload;
import logic.Model.UnloadDAO;

public class UnloadController {
	
	public void insertAnUnload(UnloadBean unloadBean) {
		Unload unload = new Unload(unloadBean.getUbUser(), unloadBean.getUbCenter(), unloadBean.getUbDate(), unloadBean.getUbTime());
		UnloadDAO.saveUnload(unload);
	}
	
	public void deleteAnUnload(UnloadBean unloadBean) {
		Unload unload = new Unload(unloadBean.getUbUser(), unloadBean.getUbCenter(), unloadBean.getUbDate(), unloadBean.getUbTime());
		UnloadDAO.deleteUnload(unload);
	}
	
	public List<String> ownerData(CenterOwnerBean ownerBean) {
		CenterOwner owner = new CenterOwner(ownerBean.getCobUsername());
		return CenterOwnerDAO.ownerInfo(owner);
	}
}