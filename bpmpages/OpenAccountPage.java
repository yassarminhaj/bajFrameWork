package bpmpages;

import java.io.IOException;

import org.openqa.selenium.By;

import com.csvreader.CsvReader;

import core.SeleniumWrapper;
import core.TestDataManager;

public class OpenAccountPage extends SeleniumWrapper {

	public static By acbranchCodeField = By.name("tw#local#AOEmployeeInfo#SelectedCCNumber");
	public static By okButton = By.xpath("(//button[contains(text(),'OK')])[1]");
	public static By openNewAccRadio = By.xpath("//label[contains(text(),'Open New Account')]");
	public static By acbranchNumField = By.name("tw#local#AOEmployeeInfo#SelectedCCNumber");
	public static By custClassificationList = By.name("tw#local#firstCustomerRecord#customerClassification");
	public static By genderList = By.name("tw#local#firstCustomerRecord#genderCodeNew");
	public static By dobTypeRadioG = By.xpath("//label[contains(text(),'Gregorian')]");
	public static By dobTypeRadio = By.xpath("//label[contains(text(),'Hijri')]");
	public static By dobDatefldG = By.xpath("//div[@class = 'dijitReset dijitInputField dijitInputContainer']/input");
	public static By dobDatefld = By.name("tw#local#firstCustomerRecord#dateOfBirthHijriNew");
	public static By custPersonaButton = By.xpath("//button[text()='Select Customer Persona']");
	public static By idTypeList = By.name("tw#local#firstCustomerRecord#iDTypeCodeNew");
	public static By idNumberField = By.name("tw#local#firstCustomerRecord#iDNumberNew");
	public static By accountModelList = By.name("tw#local#accountOpeningTransaction#accountModelCode");
	public static By addAttornyList = By.name("tw#local#accountOpeningTransaction#addAttorney");
	public static By mainCustinfoButton = By.xpath("//button[contains(text(),'Enter Main Customer Information')]");
	public static By submitButton = By.xpath("//button[contains(text(),'Submit')]");
	public static By submitRequestForAmlApprovalButton = By.xpath("//button[contains(text(),'AML Approval')]");
	public static By accountPurposeList = By.name("tw#local#accountOpeningTransaction#purposeOfAccount");
	public static By customerBasicLable = By.name("tw#local#firstCustomerRecord#customerBasicNumber");
	public static By mobileNumberField = By.name("tw#local#firstCustomerRecord#mobileNumberNew");

	public static String basic;

	private static OpenAccountPage OpenAccountPage_instance = null;

	// static method to create instance of Singleton class
	public static OpenAccountPage getInstance() {
		if (OpenAccountPage_instance == null)
			OpenAccountPage_instance = new OpenAccountPage();

		return OpenAccountPage_instance;
	}

	//@Step("Successfully entered the account branch code")
	public void enterAccountBranchCode() {
		switchToFrame(2);
		hangon(3);
		enterText(acbranchCodeField, "0104");
	}

	public void clickOkbutton() {
		click(okButton);
	}

	public void clickOpenNewAccount() {
		click(openNewAccRadio);
	}

	public void selectCustomerClassification(String custClassification) {

		selectX(custClassificationList, custClassification);
	}

	public void selectCustomerGender() {
		select(genderList, SELECT_RANDOM);
	}

	public void clickDobType(CsvReader testdata) {

		// String dobType = "H";
		try {
			if (testdata.get("DobType").equals("G")) {
				click(dobTypeRadioG);
			} else {
				click(dobTypeRadio);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void enterDateOfBirth(CsvReader testdata) {

		try {
			if (testdata.get("DobType").equals("G")) {
				enterText(dobDatefldG, testdata.get("DateOfBirth"));
			} else {
				enterText(dobDatefld, testdata.get("DateOfBirth"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void clickCustomerPersona() {
		click(custPersonaButton);
	}

	public void selectIDType(String idType) {
		selectX(idTypeList, idType);
	}

	public void enterIDNumber(CsvReader testdata) {

		try {

			enterText(idNumberField, testdata.get("NationalID"));
			TestDataManager.writeCsv(testdata.get("NationalID"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void selectAccountModel() {
		select(accountModelList, "Individual Account");
	}

	public void selectAddAttorney() {
		select(addAttornyList, "No");
	}

	public void clickEnterNewCustomerInformation() {
		hangon(3);
		click(mainCustinfoButton);
	}

	public void clickSubmit() {
		hangon(2);
		click(submitButton);
	}

	public void clickSubmitRequestForAmlApproval() {
		hangon(2);
		click(submitRequestForAmlApprovalButton);
	}

	public void selectAccountPurpose() {
		select(accountPurposeList, SELECT_RANDOM);// "Saving ���� �����"
	}

	public void readCustomerBasicNumber() {
		basic = getAttribute(customerBasicLable, "value");
	}

	public void storeBasicNumber() {
		TestDataManager.movePointerToNextLine();
		hangon(5);
		untilURLIsLoaded();
	}

	public void printBasicNumber() {
		TestDataManager.writeCsv(basic);
	}

	/*
	 * this field was added recently by sama to validate mobile with al-elm
	 * database
	 */
	public void enterMobileNumber(CsvReader testdata) {

		try {
			enterText(mobileNumberField, testdata.get("MobileNumber"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
