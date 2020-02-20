package logic.Controller;

import java.util.List;

import logic.Bean.CenterOwnerBean;
import logic.Model.CenterOwner;
import logic.Model.CenterOwnerDAO;

public class OwnerController {
	
	public boolean login(CenterOwnerBean ownerBean) {
		CenterOwner owner = new CenterOwner(ownerBean.getCobUsername());
		owner.setCoPassword(ownerBean.getCobPassword());
		return CenterOwnerDAO.verifyLogin(owner);
	}
	
	public List<String> ownerData(CenterOwnerBean ownerBean) {
		CenterOwner owner = new CenterOwner(ownerBean.getCobUsername());
		return CenterOwnerDAO.ownerInfo(owner);
	}
	
	public void deleteAccount(CenterOwnerBean ownerBean) {
		CenterOwner owner = new CenterOwner(ownerBean.getCobUsername());
		CenterOwnerDAO.deleteOwnerAccount(owner.getCoUsername());
	}
}