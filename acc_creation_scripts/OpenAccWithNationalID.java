package acc_creation_scripts;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import bpmpages.AuthorizerPage;
import bpmpages.CustomerInformationPage;
import bpmpages.CustomerPersonaPage;
import bpmpages.FeaturesRequiredPage;
import bpmpages.MailingDetailsPage;
import bpmpages.IndexPage;
import bpmpages.MenuPage;
import bpmpages.OpenAccountPage;
import core.SeleniumWrapper;
import core.TestDataManager;

public class OpenAccWithNationalID {

	SeleniumWrapper automaton = new SeleniumWrapper();
	public IndexPage IndexPageObj = IndexPage.getInstance();
	public MenuPage MenuPageObj = MenuPage.getInstance();
	public OpenAccountPage OpenAccountPageObj = OpenAccountPage.getInstance();
	public CustomerPersonaPage CustomerPersonaPageObj = CustomerPersonaPage.getInstance();
	public CustomerInformationPage CustomerInformationPageObj = CustomerInformationPage.getInstance();
	public MailingDetailsPage MailingDetailsPageObj = MailingDetailsPage.getInstance();
	public FeaturesRequiredPage FeaturesRequiredPageObj = FeaturesRequiredPage.getInstance();
	public AuthorizerPage AuthorizerPageObj = AuthorizerPage.getInstance();

	public static CsvReader testdata = null;
	public static CsvWriter csvFileWriter;
	
	@Test
	public void test() {

		testdata = TestDataManager.readCsv("Hussam");
		automaton.killBrowserSessions();
		csvFileWriter = TestDataManager.CreateNewCsv("results");
		
		int i = 0;
		try {
			while (testdata.readRecord()) {

				i = i + 1;
				automaton.launchApp();
				IndexPageObj.enterUserName();
				IndexPageObj.enterPassword();
				IndexPageObj.clickContinue();
				
				MenuPageObj.clickOpenAccountProcess();
				OpenAccountPageObj.enterAccountBranchCode();
				OpenAccountPageObj.clickOkbutton();
				OpenAccountPageObj.clickOpenNewAccount();
				OpenAccountPageObj.selectCustomerClassification("Saudi");
				OpenAccountPageObj.selectCustomerGender();
				OpenAccountPageObj.clickDobType(testdata);
				OpenAccountPageObj.enterDateOfBirth(testdata);
				OpenAccountPageObj.clickCustomerPersona();

				// CustomerPersonaPageObj.uncheckElements();
				CustomerPersonaPageObj.clickNormal();
				CustomerPersonaPageObj.clickOk();

				OpenAccountPageObj.selectIDType("National ID Card");
				OpenAccountPageObj.enterIDNumber(testdata);
				OpenAccountPageObj.selectAccountModel();
				OpenAccountPageObj.selectAddAttorney();
				OpenAccountPageObj.enterMobileNumber(testdata);

				OpenAccountPageObj.clickOkbutton();
				OpenAccountPageObj.clickEnterNewCustomerInformation();

				CustomerInformationPageObj.enterArabicFirstName();
				CustomerInformationPageObj.enterArabicFamilyName();
				CustomerInformationPageObj.enterArabicSecondName();
				CustomerInformationPageObj.enterArabicThirdName();
				CustomerInformationPageObj.enterEnglishFirstName();
				CustomerInformationPageObj.enterEnglishFamilyName();
				CustomerInformationPageObj.enterEnglishSecondName();
				CustomerInformationPageObj.enterEnglishThirdName();
				CustomerInformationPageObj.selectNationality(testdata);
				CustomerInformationPageObj.enterPlaceOfBirth();
				CustomerInformationPageObj.selectCountryOfBirth();
				CustomerInformationPageObj.selectMaritialStatus();
				CustomerInformationPageObj.selectMaritialStatus();
				CustomerInformationPageObj.selectResidentialStatus();
				CustomerInformationPageObj.selectEducationLevel();
				CustomerInformationPageObj.clickIDDateType();
				CustomerInformationPageObj.enterIDIssueDateHijri();
				CustomerInformationPageObj.enterIdExpiryDatehijri();
				CustomerInformationPageObj.selectCustomerAcquired();
				CustomerInformationPageObj.enterStreetNameInArabic();
				CustomerInformationPageObj.enterDistrictnameInArabic();
				CustomerInformationPageObj.enterBuildingNumber();
				CustomerInformationPageObj.selectCity();
				CustomerInformationPageObj.enterPostalCode();
				CustomerInformationPageObj.enterAdditionalNumber();
				CustomerInformationPageObj.enterStreetNameInEnglish();
				CustomerInformationPageObj.enterDistrictNameInEnglish();
				// CustomerInformationPageObj.enterMobileNumber();
				CustomerInformationPageObj.clickMailingAddressSameAsNationalAddress();
				// below are hard coded to avoid compliance approval
				CustomerInformationPageObj.selectEmploymentStatus("Full-Time Employed"); 
				CustomerInformationPageObj.selectEmployerName("Samad");
				CustomerInformationPageObj.selectEmploymentSector("Private Sector");
				CustomerInformationPageObj.enterDepartment();
				CustomerInformationPageObj.selectPositionJob("Cashier");
				CustomerInformationPageObj.clickAddressType();
				CustomerInformationPageObj.enterEmployerPOBox();
				CustomerInformationPageObj.enterEmployerPostalCode();
				CustomerInformationPageObj.enterEmployerCity();
				CustomerInformationPageObj.selectEmployerCountry();
				CustomerInformationPageObj.selectCumulativeMonthlyIncome();
				CustomerInformationPageObj.selectSourceOfIncome();
				CustomerInformationPageObj.clickNext();

				MailingDetailsPageObj.clickAreYouTaxResidentOusideSaudi();
				MailingDetailsPageObj.clickMissingCustomerDoc();
				MailingDetailsPageObj.clickDoYouHaveAnyOtherPOBox();
				MailingDetailsPageObj.clickMissingCustomerInfo();
				MailingDetailsPageObj.clickSelfCertificationFormSignedByCustomer();
				MailingDetailsPageObj.clickDocumentProvidedIsReliable();
				MailingDetailsPageObj.clickOkButton();

				FeaturesRequiredPageObj.clickWiretransfer();
				FeaturesRequiredPageObj.clickCreditInstrument();
				FeaturesRequiredPageObj.clickProceedButton();

				OpenAccountPageObj.selectAccountPurpose();
				OpenAccountPageObj.readCustomerBasicNumber();
				OpenAccountPageObj.clickSubmit();
				OpenAccountPageObj.printBasicNumber();
				
//				if (i == 1) {
//					OpenAccountPageObj.clickSubmitRequestForAmlApproval();
//				}
//				
				MenuPageObj.clickAOManagerReview();
				AuthorizerPageObj.clickApprove();

				OpenAccountPageObj.storeBasicNumber();

				automaton.closeBrowser();
				System.out.println("Looped " + i + " times");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@AfterMethod
	public void closeBPM() {

	
		csvFileWriter.close();

	}

}
