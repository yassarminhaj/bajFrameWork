package bpmpages;

import java.io.IOException;

import org.openqa.selenium.By;

import bpmpages.OpenAccountPage;
import bpmpages.MenuPage;
import core.SeleniumWrapper;

public class MenuPage extends SeleniumWrapper{
	
	public static By openAccountLink = By.xpath("//a[@title='Open Account Process']");
	public static By taskreceiveDateFilter = By.xpath("//div[@title='Task received date']/following-sibling::div/i[2]");
	public static By sortDescendingFilter = By.className("ui-grid-icon-sort-alt-down");
	
	private static MenuPage MenuPage_instance = null;

	// static method to create instance of Singleton class
	public static MenuPage getInstance() {
		if (MenuPage_instance == null)
			MenuPage_instance = new MenuPage();

		return MenuPage_instance;
	}
	
	
	//@Step("Scuccessfully clicked openAccountProcess")
	public void clickOpenAccountProcess() throws IOException{
		//takeScreenShot("mainScreen");
		
		clickTaskReceivedDate();
		clickSortDescending();
		click(openAccountLink);
	}
	
	public void clickTaskReceivedDate() {		
		click(taskreceiveDateFilter);

	}

	public void clickSortDescending() {

		click(sortDescendingFilter);

	}
	
	public void clickAOManagerReview() {
		// TODO Auto-generated method stub
		String xpath = "//span[contains(text(),'" + OpenAccountPage.basic + "')]";
		untilURLIsLoaded();
		By firstRecord = By.xpath(xpath);
		switchToDefault();
		hangon(20);
		click(firstRecord);
	}

}
