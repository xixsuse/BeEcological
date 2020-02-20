package logic.Controller;

import java.util.ArrayList;

import logic.Bean.CenterBean;
import logic.Bean.CenterOwnerBean;
import logic.Model.Center;
import logic.Model.CenterDAO;
import logic.Model.CenterOwner;

public class CenterController {
	
	public ArrayList<CenterBean> centerList(CenterBean centerBean) {
		ArrayList<CenterBean> listOfCenterBean = new ArrayList<>();
		ArrayList<Center> listOfCenter = CenterDAO.verifyCenter(centerBean.getCbName());
		for(Center center : listOfCenter) {
			CenterBean centerB = new CenterBean();
			centerB.setCbName(center.getcName());
			centerB.setCbCity(center.getcCity());
			centerB.setCbCap(center.getcCap());
			centerB.setCbAddress(center.getcAddress());
			centerB.setCbNum(center.getcNum());
			centerB.setCbPhone(center.getcPhone());
			listOfCenterBean.add(centerB);
		}
		return listOfCenterBean;
	}
	
	public CenterOwnerBean ownerOfTheSelectedCenter(CenterBean centerBean) {
		CenterOwner owner;
		CenterOwnerBean ownerBean = new CenterOwnerBean();
		Center center = new Center(centerBean.getCbName());
		owner = CenterDAO.ownerOfTheCenter(center);
		ownerBean.setCenter(owner.getCenter());
		ownerBean.setCobEmail(owner.getCoEmail());
		ownerBean.setCobName(owner.getCoName());
		ownerBean.setCobPassword(owner.getCoPassword());
		ownerBean.setCobPhone(owner.getCoPhone());
		ownerBean.setCobSurname(owner.getCoSurname());
		ownerBean.setCobUsername(owner.getCoUsername());
		return ownerBean;
	}
}