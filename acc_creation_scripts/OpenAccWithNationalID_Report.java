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
import core.Reporter;
import core.SeleniumWrapper;
import core.TestDataManager;

public class OpenAccWithNationalID_Report {

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

		testdata = TestDataManager.readCsv("test");
		automaton.killBrowserSessions();
		csvFileWriter = TestDataManager.CreateNewCsv("results");

		Reporter.startRecording();

		int i = 0;
		
		try {
			while (testdata.readRecord()) {

				i = i + 1;

				try {
					Reporter.startReporting("Customer Creation with ID" + i);
					Reporter.setTestCase("Gets NationalID and created a customer", "Yasser", "TestData");

					automaton.launchApp();

					IndexPageObj.enterUserName();
					IndexPageObj.enterPassword();
					IndexPageObj.clickContinue();

					Reporter.screenInfo("Logged in to create the customer number " + i);

					MenuPageObj.clickOpenAccountProcess();
					OpenAccountPageObj.enterAccountBranchCode();
					OpenAccountPageObj.clickOkbutton();
					OpenAccountPageObj.clickOpenNewAccount();
					OpenAccountPageObj.selectCustomerClassification("Saudi");
					OpenAccountPageObj.selectCustomerGender();
					OpenAccountPageObj.clickDobType(testdata);
					OpenAccountPageObj.enterDateOfBirth(testdata);
					OpenAccountPageObj.clickCustomerPersona();
					
					CustomerPersonaPageObj.clickNormal();
					CustomerPersonaPageObj.clickOk();

					OpenAccountPageObj.selectIDType("National ID Card");
					OpenAccountPageObj.enterIDNumber(testdata);
					OpenAccountPageObj.selectAccountModel();
					OpenAccountPageObj.selectAddAttorney();
					OpenAccountPageObj.enterMobileNumber(testdata);

					OpenAccountPageObj.clickOkbutton();
					OpenAccountPageObj.clickEnterNewCustomerInformation();

					Reporter.info("Successfully entered basic details");

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
					
					CustomerInformationPageObj.clickMailingAddressSameAsNationalAddress();
					
					// below are hard coded to avoid compliance approval
					CustomerInformationPageObj.selectEmploymentStatus("Full-TimeEmployed");
					CustomerInformationPageObj.selectEmployerName("Samad");
					CustomerInformationPageObj.selectEmploymentSector("PrivateSector");
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

					Reporter.info("Successfully entered Customer Information details");

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

					Reporter.screenInfo("Successfully entered all details");

					OpenAccountPageObj.clickSubmit();
					OpenAccountPageObj.printBasicNumber();

					Reporter.info("Record Submitted needs Manager Approval");					

					MenuPageObj.clickAOManagerReview();
					AuthorizerPageObj.clickApprove();

					OpenAccountPageObj.storeBasicNumber();

					Reporter.pass("Customer " + i + " created successfully");

					automaton.closeBrowser();
					System.out.println("Looped " + i + " times");

				} catch (Exception e) {
					System.out.println("Exception occured for record "+ i);
					
					Reporter.fail("Failed due to "+ e.getMessage());
					automaton.closeBrowser();
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@AfterMethod
	public void closeBPM() {

		Reporter.endReporting();
		Reporter.stopRecording();
		csvFileWriter.close();

	}

}
