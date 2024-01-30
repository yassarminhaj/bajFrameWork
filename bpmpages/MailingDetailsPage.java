package bpmpages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import bpmpages.MailingDetailsPage;
import core.SeleniumWrapper;

public class MailingDetailsPage extends SeleniumWrapper{

	public static By taxresidentOtherthansaudiRadio = By.name("tw#local#fatcaCRS#taxResidentOtherCountry");
	public static By otherpoboxMailingRadio = By.name("tw#local#fatcaCRS#mailingPoBoxAddress");
	public static By missingCustomerdocRadio = By.name("tw#local#fatcaCRS#missingCustomerDocuments");
	public static By missingCustomerinfoRadio = By.name("tw#local#fatcaCRS#missingCustomerInformation");
	public static By selfverFormsignedRadio = By.name("tw#local#fatcaCRS#formSignedByCustomer");
	public static By isdocReliableRadio = By.name("tw#local#fatcaCRS#providedInfoReliable");
	public static By okButton = By.xpath("//button[text()='OK']");	
	

	private static MailingDetailsPage MailingDetailsPage_instance = null;

	// static method to create instance of Singleton class
	public static MailingDetailsPage getInstance() {
		if (MailingDetailsPage_instance == null)
			MailingDetailsPage_instance = new MailingDetailsPage();

		return MailingDetailsPage_instance;
	}
	
	
	public void clickAreYouTaxResidentOusideSaudi() {
		String val = "No";
		hangon(5);
		List<WebElement> getList = getListOfElements(taxresidentOtherthansaudiRadio);

		if (val.equalsIgnoreCase("yes")) {
			click(getList.get(0));
		} else {
			click(getList.get(1));
		}
	}

	public void clickDoYouHaveAnyOtherPOBox() {

		String val = "No";		
		List<WebElement> getList = getListOfElements(otherpoboxMailingRadio);

		if (val.equalsIgnoreCase("yes")) {
			click(getList.get(0));
		} else {
			click(getList.get(1));
		}
	}

	public void clickMissingCustomerDoc() {

		String val = "No";

		List<WebElement> getList = getListOfElements(missingCustomerdocRadio);

		if (val.equalsIgnoreCase("yes")) {
			click(getList.get(0));
		} else {
			click(getList.get(1));
		}

	}

	public void clickMissingCustomerInfo() {

		String val = "No";

		List<WebElement> getList = getListOfElements(missingCustomerinfoRadio);

		if (val.equalsIgnoreCase("yes")) {
			click(getList.get(0));
		} else {
			click(getList.get(1));
		}

	}

	public void clickSelfCertificationFormSignedByCustomer() {

		String val = "No";

		List<WebElement> getList = getListOfElements(selfverFormsignedRadio);

		if (val.equalsIgnoreCase("yes")) {
			click(getList.get(0));
		} else {
			click(getList.get(1));
		}

	}

	public void clickDocumentProvidedIsReliable() {

		String val = "No";

		List<WebElement> getList = getListOfElements(isdocReliableRadio);

		if (val.equalsIgnoreCase("yes")) {
			click(getList.get(0));
		} else {
			click(getList.get(1));
		}

	}

	public void clickOkButton() {

		click(okButton);
	}
	
}
