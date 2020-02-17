package Controller;

import java.util.List;

import Bean.CenterOwnerBean;
import Model.CenterOwner;
import Model.CenterOwnerDAO;

public class OwnerController {
	
	public OwnerController() {}
	
	public boolean Login(CenterOwnerBean ownerBean) {
		CenterOwner owner = new CenterOwner(ownerBean.getUsername());
		owner.setPassword(ownerBean.getPassword());
		boolean result = CenterOwnerDAO.verifyLogin(owner);
		return result;
	}
	
	public List<String> OwnerData(CenterOwnerBean ownerBean) {
		CenterOwner owner = new CenterOwner(ownerBean.getUsername());
		List<String> result = CenterOwnerDAO.ownerInfo(owner);
		return result;
	}
	
	public void deleteAccount(CenterOwnerBean ownerBean) {
		CenterOwner owner = new CenterOwner(ownerBean.getUsername());
		CenterOwnerDAO.deleteOwnerAccount(owner.getUsername());
	}
}