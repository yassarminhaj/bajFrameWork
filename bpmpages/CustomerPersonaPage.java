package bpmpages;

import org.openqa.selenium.By;

import core.SeleniumWrapper;


public class CustomerPersonaPage extends SeleniumWrapper{

	public static By clickNormalCheckbox = By.id("tw#local#personaListIN#0#name"); 
	public static By clickOkButton = By.xpath("//button[text()='OK']");
	public static By clearFieldsLocator = By.name("TBL_SELECTION_LIST_tw#local#personaListIN");
	
	private static CustomerPersonaPage CustomerPersonaPage_instance = null;

	// static method to create instance of Singleton class
	public static CustomerPersonaPage getInstance() {
		if (CustomerPersonaPage_instance == null)
			CustomerPersonaPage_instance = new CustomerPersonaPage();

		return CustomerPersonaPage_instance;
	}	
	
	public void clickNormal() {
		// TODO Auto-generated method stub
		click(clickNormalCheckbox);
	}

	public void clickOk() {
		// TODO Auto-generated method stub
		click(clickOkButton);
	}
	
	
	
}
