package bpmpages;

import org.openqa.selenium.By;

import core.SeleniumWrapper;

public class IndexPage extends SeleniumWrapper {

	public static By userNameField = By.id("username");
	public static By passwordField = By.id("password");
	public static By continueButton = By.xpath("//a[@class='button ok']");
	
	private static IndexPage IndexPage_instance = null;

	// static method to create instance of Singleton class
	public static IndexPage getInstance() {
		if (IndexPage_instance == null)
			IndexPage_instance = new IndexPage();

		return IndexPage_instance;
	}
	
	public void enterUserName(){
		enterText(userNameField, dataOf("USER_NAME")[ENVIRONMENT]);
	}
	
	public void enterPassword(){
		enterEncriptedText(passwordField, (dataOf("PASSWORD")[ENVIRONMENT]));
	}
	
	public void clickContinue(){
		click(continueButton);
		untilURLIsLoaded();
	}
	
}
