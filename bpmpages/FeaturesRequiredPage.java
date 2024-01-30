package bpmpages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import bpmpages.FeaturesRequiredPage;
import core.SeleniumWrapper;

public class FeaturesRequiredPage extends SeleniumWrapper{

	public static By wireTransferRadio = By
			.name("tw#local#HRAccountsInfo#productstheCustomerisInterestedIn#wireTransfers");
	public static By creditInstrumentRadio = By
			.name("tw#local#HRAccountsInfo#productstheCustomerisInterestedIn#creditInstruments");
	public static By proceedButton = By.xpath("//button[contains(text(),'Proceed')]");
	
	private static FeaturesRequiredPage FeaturesRequiredPage_instance = null;

	// static method to create instance of Singleton class
	public static FeaturesRequiredPage getInstance() {
		if (FeaturesRequiredPage_instance == null)
			FeaturesRequiredPage_instance = new FeaturesRequiredPage();

		return FeaturesRequiredPage_instance;
	}

	public void clickWiretransfer() {

		String val = "yes";
		hangon(5);
		List<WebElement> getList = getListOfElements(wireTransferRadio);

		if (val.equalsIgnoreCase("yes")) {
			click(getList.get(0));
		} else {
			click(getList.get(1));
		}

	}

	public void clickCreditInstrument() {

		String val = "yes";

		List<WebElement> getList = getListOfElements(creditInstrumentRadio);

		if (val.equalsIgnoreCase("yes")) {
			click(getList.get(0));
		} else {
			click(getList.get(1));
		}

	}

	public void clickProceedButton() {

		click(proceedButton);
	}
	
}
