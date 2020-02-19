package logic.Controller;

import java.util.ArrayList;

import logic.Bean.CenterBean;
import logic.Bean.CenterOwnerBean;
import logic.Model.Center;
import logic.Model.CenterDAO;
import logic.Model.CenterOwner;

public class CenterController {
	
	public CenterController() {}
	
	public ArrayList<CenterBean> CenterList(CenterBean centerBean) {
		ArrayList<CenterBean> listOfCenterBean = new ArrayList<>();
		ArrayList<Center> listOfCenter = CenterDAO.verifyCenter(centerBean.getName());
		for(Center center : listOfCenter) {
			CenterBean centerB = new CenterBean();
			centerB.setName(center.getName());
			centerB.setCity(center.getCity());
			centerB.setCAP(center.getCAP());
			centerB.setAddress(center.getAddress());
			centerB.setNum(center.getNum());
			centerB.setCenterPhone(center.getCenterPhone());
			listOfCenterBean.add(centerB);
		}
		return listOfCenterBean;
	}
	
	public CenterOwnerBean OwnerOfTheSelectedCenter(CenterBean centerBean) {
		CenterOwner owner;
		CenterOwnerBean ownerBean = new CenterOwnerBean();
		Center center = new Center(centerBean.getName());
		owner = CenterDAO.ownerOfTheCenter(center);
		ownerBean.setCenter(owner.getCenter());
		ownerBean.setEmailAddress(owner.getEmailAddress());
		ownerBean.setName(owner.getName());
		ownerBean.setPassword(owner.getPassword());
		ownerBean.setPhoneNumber(owner.getPhoneNumber());
		ownerBean.setSurname(owner.getSurname());
		ownerBean.setUsername(owner.getUsername());
		return ownerBean;
	}
}