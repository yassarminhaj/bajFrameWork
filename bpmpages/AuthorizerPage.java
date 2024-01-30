package bpmpages;

import org.openqa.selenium.By;

import bpmpages.AuthorizerPage;
import core.SeleniumWrapper;

public class AuthorizerPage extends SeleniumWrapper{

	public static By approveButton = By.xpath("//button[contains(text(),'Approve')]");
	public static By rejectButton = By.name("ButtonGroup0#Button1");

	// static variable single_instance of type Singleton
	private static AuthorizerPage AuthorizerPage_instance = null;

	// static method to create instance of Singleton class
	public static AuthorizerPage getInstance() {
		if (AuthorizerPage_instance == null)
			AuthorizerPage_instance = new AuthorizerPage();

		return AuthorizerPage_instance;
	}

	public void clickApprove() {
		switchToFrame(2);
		hangon(10);
		click(approveButton);
	}
	
	
}
