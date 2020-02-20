package test.junit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import logic.Bean.CenterBean;
import logic.Controller.CenterController;

public class TestSearch {
	
	private CenterBean centerBean;
	private CenterBean center;
	private CenterController centerController = new CenterController();
	
	@Before
	public void prepareData() {
		String centername = "Centro smaltimento Acacia";
		String phone = "0599665854";
		String address = "Via dei rovi";
		String num = "21";
		String city = "Alatri";
		String cap = "03011";
		
		center = new CenterBean();
		center.setName(centername);
		center.setCenterPhone(phone);
		center.setAddress(address +" "+ num);
		center.setCity(city);
		center.setCAP(cap);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testSearchCenter() {
		String centername = "Centro smaltimento Acacia";
		centerBean = new CenterBean();
		centerBean.setName(centername);
		
		List<CenterBean> centerList = centerController.CenterList(centerBean);
		Object[] expected = {center.getName(), center.getCenterPhone(), center.getAddress(), center.getCity(), center.getCAP(), 
				center.getAddress()};
		Object[] result = {centerList.get(0).getName(), centerList.get(0).getCenterPhone(), centerList.get(0).getAddress(), 
				centerList.get(0).getCity(), centerList.get(0).getCAP(), centerList.get(0).getAddress()};
		
		String message = "expected: "+expected+" new: "+centerList;
		assertEquals(message, expected, result);
	}
}