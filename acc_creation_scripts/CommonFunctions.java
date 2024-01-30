package acc_creation_scripts;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import bpmpages.IndexPage;
import core.SeleniumWrapper;

public class CommonFunctions {

	public static SeleniumWrapper automaton = new SeleniumWrapper();
	public IndexPage IndexPageObj = IndexPage.getInstance();

	
	
	@BeforeClass
	public static void configure(){
		automaton.killBrowserSessions();
		
	}
	
	@BeforeMethod
	public void launchBPM() {
		
		automaton.launchApp();
		IndexPageObj.enterUserName();
		IndexPageObj.enterPassword();
		IndexPageObj.clickContinue();
	}

	@AfterMethod
	public void closeBPM() {

	
		//automaton.closeBrowser();

	}

}
