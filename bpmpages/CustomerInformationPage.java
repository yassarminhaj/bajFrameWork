package bpmpages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.csvreader.CsvReader;

import core.GetRandom;
import core.SeleniumWrapper;

public class CustomerInformationPage extends SeleniumWrapper {

	public static By accountCategoryList = By.name("tw#local#accountOpeningTransaction#accountCategoryCode");
	public static By accountCurrencyList = By.name("tw#local#accountOpeningTransaction#accountCurrencyCode");
	public static By accountPurposeList = By.name("tw#local#accountOpeningTransaction#purposeOfAccount");
	public static By requireAtmChechbox = By.name("BOOLEAN_FOR_tw#local#accountOpeningTransaction#requireATMFlag");

	public static By titleList = By.name("tw#local#kYCCustomerInfo#titleCodeNew");
	public static By afirstNameField = By.name("tw#local#kYCCustomerInfo#firstNameArabicNew");
	public static By afamilyNameField = By.name("tw#local#kYCCustomerInfo#familyNameArabicNew");
	public static By asecondNameField = By.name("tw#local#kYCCustomerInfo#secondNameArabicNew");
	public static By athirdNameField = By.name("tw#local#kYCCustomerInfo#thirdNameArabicNew");
	public static By efirstNameField = By.name("tw#local#kYCCustomerInfo#firstNameEnglishNew");
	public static By efamilyNameField = By.name("tw#local#kYCCustomerInfo#familyNameEnglishNew");
	public static By esecondNameField = By.name("tw#local#kYCCustomerInfo#secondNameEnglishNew");
	public static By ethirdNameField = By.name("tw#local#kYCCustomerInfo#thirdNameEnglishNew");
	public static By nationalityList = By.name("tw#local#kYCCustomerInfo#nationalityCodeNew");
	public static By placeofBirthField = By.name("tw#local#kYCCustomerInfo#placeOfBirthNew");
	public static By countryofBirthList = By.name("tw#local#kYCCustomerInfo#countryOfBirthCodeNew");
	public static By maritalStatusList = By.name("tw#local#kYCCustomerInfo#maritalStatusCodeNew");
	public static By residentialStatusList = By.name("tw#local#kYCCustomerInfo#residenceStatusCodeNew");
	public static By educationLevelList = By.name("tw#local#kYCCustomerInfo#educationLevelCodeNew");
	public static By idDatetypeRadio = By.name("tw#local#kYCCustomerInfo#iDDateTypeCodeNew");
	public static By hijriIDissuedateField = By.name("tw#local#kYCCustomerInfo#iDIssueDateHijriNew");
	public static By hijriIDexpirydateField = By.name("tw#local#kYCCustomerInfo#iDExpiryDateHijriNew");
	public static By customerAcquiredList = By.name("tw#local#kYCCustomerInfo#customerAcquiredCode");
	public static By gregorianIDissuedateField = By.id("dijit_form_DateTextBox_1");
	public static By gregorianIDexpirydateField = By.id("dijit_form_DateTextBox_2");
	public static By arStreetnameField = By.name("tw#local#kYCCustomerInfo#nationalAddressStreetName");
	public static By arDistrictnameField = By.name("tw#local#kYCCustomerInfo#nationalAddressNeighborhood");
	public static By buildingNumberField = By.name("tw#local#kYCCustomerInfo#nationalAddressBuildingNumber");
	public static By cityList = By.name("tw#local#kYCCustomerInfo#nationalAddressCity");
	public static By postalCodeField = By.name("tw#local#kYCCustomerInfo#nationalAddressPostalCodeZipCode");
	public static By additionalNumberField = By.name("tw#local#kYCCustomerInfo#nationalAddressAdditionalNumber");
	public static By enStreetnameField = By.name("tw#local#kYCCustomerInfo#nationalAddressStreetNameENG");
	public static By enDistrictNameField = By.name("tw#local#kYCCustomerInfo#nationalAddressNeighborhoodENG");
	public static By mobileNumberField = By.name("tw#local#kYCCustomerInfo#mobileNumberNew");
	public static By mailingaddSameField = By.xpath("//input[@name='BooleanChoice0']");
	public static By employmentStatusList = By.name("tw#local#kYCCustomerInfo#employmentStatusCodeNew");
	public static By employerNameList = By.name("tw#local#kYCCustomerInfo#employerNameNew");
	public static By employmentSectorList = By.name("tw#local#kYCCustomerInfo#employmentSectorNew");
	public static By departmentField = By.name("tw#local#kYCCustomerInfo#departmentNew");
	public static By positionJobList = By.name("tw#local#kYCCustomerInfo#positionNew");
	public static By addressTypeRadio = By.name("tw#local#kYCCustomerInfo#employerAddressTypeNew");
	public static By employerPoboxField = By.name("tw#local#kYCCustomerInfo#employerAddressPOBoxTypePOBoxNew");
	public static By employerPostalcodeField = By
			.name("tw#local#kYCCustomerInfo#employerAddressPOBoxTypePostalCodeNew");
	public static By employerCityField = By.name("tw#local#kYCCustomerInfo#employerAddressPOBoxTypeCityNew");
	public static By employerCountryList = By.name("tw#local#kYCCustomerInfo#employerAddressPOBoxTypeCountryNew");
	public static By cumlMnthlyincomeList = By.name("tw#local#kYCCustomerInfo#monthlyIncomeNew");
	public static By souceOfincomeList = By.name("tw#local#kYCCustomerInfo#sourceOfIncomeNew");
	public static By nextButton = By.xpath("//button[contains(text(),'Next')]");
	public static By homeCountryList = By.name("tw#local#kYCCustomerInfo#homeAddressCountryNew");
	public static By homeCityField = By.name("tw#local#kYCCustomerInfo#homeAddressCityNew");
	public static By homeStreetaddField = By.name("tw#local#kYCCustomerInfo#homeAddressStreetAddressNew");
	public static By homeMobileField = By.name("tw#local#kYCCustomerInfo#homeAddressMobileNumberNew");
	public static By positionIqamaField = By.name("tw#local#kYCCustomerInfo#professionInIqamaNew");

	private static CustomerInformationPage CustomerInformationPage_instance = null;

	String[] randumpFirstName = GetRandom.getFromDumpX("firstName");
	String[] randumpLastName = GetRandom.getFromDumpX("lastName");
	
	// static method to create instance of Singleton class
	public static CustomerInformationPage getInstance() {
		if (CustomerInformationPage_instance == null)
			CustomerInformationPage_instance = new CustomerInformationPage();

		return CustomerInformationPage_instance;
	}

	public void clickIDDateType() {
		// click(openAccountLink);
		String type = "Hijri";
		List<WebElement> idDateType = getListOfElements(idDatetypeRadio);

		if (type.contains("Gregorian")) {
			click(idDateType.get(0));
		} else {
			click(idDateType.get(1));
		}

	}

	public void clickMailingAddressSameAsNationalAddress() {

		String val = "yes";
		List<WebElement> mailingAddress = getListOfElements(mailingaddSameField);

		if (val.equalsIgnoreCase("yes")) {
			click(mailingAddress.get(0));
		} else {
			click(mailingAddress.get(0));
		}

	}

	public void clickAddressType() {

		String type = "POBox";

		List<WebElement> addressType = getListOfElements(addressTypeRadio);

		if (type.equalsIgnoreCase("Physical")) {
			click(addressType.get(0));
		} else {
			click(addressType.get(1));
		}

	}

	public void enterArabicFirstName() {
		// enterText(afirstNameField, "غشسسسشث");
		enterText(afirstNameField, randumpFirstName[1]);
	}

	public void enterArabicFamilyName() {
		//enterText(afamilyNameField, "غشسسسشث");
		enterText(afirstNameField, randumpLastName[1]);
	}

	public void enterArabicSecondName() {
		//enterText(asecondNameField, "غشسسسشث");
		enterText(afirstNameField, randumpFirstName[1]);
	}

	public void enterArabicThirdName() {
		//enterText(athirdNameField, "غشسسسشث");
		enterText(afirstNameField, randumpLastName[1]);
	}

	public void enterEnglishFirstName() {
		//enterText(efirstNameField, GetRandom.text(6).toUpperCase());
		enterText(afirstNameField, randumpFirstName[0]);
	}

	public void enterEnglishFamilyName() {
		//enterText(efamilyNameField, GetRandom.text(5).toUpperCase());
		enterText(afirstNameField, randumpLastName[0]);
	}

	public void enterEnglishSecondName() {
		//enterText(esecondNameField, GetRandom.text(6).toUpperCase());
		enterText(afirstNameField, randumpFirstName[0]);
	}

	public void enterEnglishThirdName() {
		//enterText(ethirdNameField, GetRandom.text(4).toUpperCase());
		enterText(afirstNameField, randumpLastName[0]);
	}

	public void selectNationality(CsvReader testdata) {
		try {
			selectX(nationalityList, testdata.get("Nationality"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void enterPlaceOfBirth() {
		enterText(placeofBirthField, "India");
	}

	public void selectCountryOfBirth() {
		select(countryofBirthList, SELECT_RANDOM);// "India �������������"
	}

	public void selectMaritialStatus() {
		select(maritalStatusList, SELECT_RANDOM); // "Married �����"
	}

	public void selectResidentialStatus() {
		select(residentialStatusList, SELECT_RANDOM); // "Rented ����"
	}

	public void selectEducationLevel() {
		select(educationLevelList, "Masters ماجستير"); // "Masters �������"
	}

	public void enterIDIssueDateHijri() {
		enterText(hijriIDissuedateField, "12-12-1429");
	}

	public void enterIdExpiryDatehijri() {
		enterText(hijriIDexpirydateField, "12-12-1446");
	}

	public void selectCustomerAcquired() {
		selectX(customerAcquiredList, "Self Introduced");
	}

	public void enterStreetNameInArabic() {
		enterText(arStreetnameField, "تليتش شسا ايش");
	}

	public void enterDistrictnameInArabic() {
		enterText(arDistrictnameField, "ىشاشيش");
	}

	public void enterBuildingNumber() {
		enterText(buildingNumberField, "1234");
	}

	public void selectCity() {
		select(cityList, SELECT_RANDOM); // "Jeddah ���"
	}

	public void enterPostalCode() {
		enterText(postalCodeField, "23523");
	}

	public void enterAdditionalNumber() {
		enterText(additionalNumberField, "232");
	}

	public void enterStreetNameInEnglish() {
		enterText(enStreetnameField, "Khulasat az zahr");
	}

	public void enterDistrictNameInEnglish() {
		enterText(enDistrictNameField, "An Nahda");
	}

	/*
	 * after mobile verification RFS this field goes in the openaccount page
	 * public void enterMobileNumber() { enterText(mobileNumberField,
	 * "0580792431"); }
	 */
	
	public void selectEmploymentStatus(String employmentStatus) {
		selectX(employmentStatusList, employmentStatus);// Full-Time
															// Employed ����
															// ����� ����
	}

	public void selectEmployerName(String employerName) {
		selectX(employerNameList, employerName);
	}

	public void selectEmploymentSector(String employmentSector) {
		selectX(employmentSectorList, employmentSector);// Private Sector ������
														// �����
	}

	public void enterDepartment() {
		enterText(departmentField, "Management");
	}

	public void selectPositionJob(String positionJob) {
		selectX(positionJobList, positionJob);// SELECT_RANDOM Pilot/����
	}

	public void enterEmployerPOBox() {
		enterText(employerPoboxField, "123456");
	}

	public void enterEmployerPostalCode() {
		enterText(employerPostalcodeField, "23523");
	}

	public void enterEmployerCity() {
		enterText(employerCityField, "Jeddah");
	}

	public void selectEmployerCountry() {
		select(employerCountryList, SELECT_RANDOM);// "Saudi Arabia Saudi
													// Arabia"
	}

	public void selectCumulativeMonthlyIncome() {
		select(cumlMnthlyincomeList, SELECT_RANDOM); // "10000 to < 15000"
	}

	public void selectSourceOfIncome() {
		selectX(souceOfincomeList, "Salary / Bonuses");// Salary / Bonuses �����
														// ��������
	}

	public void clickNext() {
		click(nextButton);
	}

	public void selectHomeCountry() {
		selectX(homeCountryList, "India");
	}

	public void enterHomeCity() {
		enterText(homeCityField, "Chennai");
	}

	public void enterHomeStreetAddress() {
		enterText(homeStreetaddField, "abc street holbourn");
	}

	public void enterHomeMobileNumber() {
		enterText(homeMobileField, "9884157812");
	}

	public void enterPositionInIqama() {
		enterText(positionIqamaField, "Manager");
	}

}
