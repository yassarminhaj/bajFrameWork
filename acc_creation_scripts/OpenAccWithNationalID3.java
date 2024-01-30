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

public class OpenAccWithNationalID3 {

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
	
	//@Description("Creates a customer using ID")
	@Test
	public void test() {

		testdata = TestDataManager.readCsv("Hussam");
		
		//automaton.killBrowserSessions();
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
