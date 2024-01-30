package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

public class SeleniumWrapper {

	protected static String chromeDriverPath = System.getProperty("user.dir")
			+ "\\resources\\drivers\\Chromedriver\\chromedriver.exe";

	public static RemoteWebDriver rdriver;
	public static EventFiringWebDriver driver;
	public static EventListener eventListener;

	ChromeOptions objChromeOptions = new ChromeOptions();
	public static JavascriptExecutor js = (JavascriptExecutor) driver;

	Actions actions;

	public static String log4jPropertyFilePath = System.getProperty("user.dir")
			+ "\\Resources\\parameters\\log4j.properties";
	public final static Logger logger = Logger.getLogger(SeleniumWrapper.class.getName());

	public FileInputStream fis = null;
	public static final String SELECT_RANDOM = "Random";

	public static Properties prop;
	public static final String BPM_PROPERTIES_PATH = "\\resources\\parameters\\bpm.properties";

	public final int ENVIRONMENT = getEnvironment();
	private static List<String> theData = null;

	/**
	 * @Description : Will kill all the chromedriver related task from task
	 *              manager. Usefull before launching a browser as existing task
	 *              can hinder opening new thread and cause WebDriverException.
	 * @author baj80000835 : Yassar Zaman.
	 * @param :
	 *            NA
	 */
	public void killBrowserSessions() {

		try {
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
			Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
			PropertyConfigurator.configure(log4jPropertyFilePath);
		} catch (WebDriverException e) {
			logger.error("Unable to Kill browser sessions, closing the browser");
			driver.close();
			driver.quit();
		} catch (Exception e) {
			logger.error("Unable to Kill browser sessions" + e.getMessage());
			e.printStackTrace();
		}

	}

	/**
	 * @Description : Launches chrome browser and loads BPM
	 * @author baj80000835 : Yassar
	 * @param :
	 *            Na
	 **/
	public void launchApp() {

		System.setProperty("webdriver.chrome.driver", chromeDriverPath);

		objChromeOptions.addArguments("disable-infobars");
		objChromeOptions.addArguments("disable-gpu");
		objChromeOptions.addArguments("--ignore-certificate-errors");
		objChromeOptions.addArguments("test-type");

		objChromeOptions.addArguments("--js-flags=--expose-gc");
		objChromeOptions.addArguments("--enable-precise-memory-info");
		objChromeOptions.addArguments("--disable-popup-blocking");
		objChromeOptions.addArguments("--disable-default-apps");
		objChromeOptions.addArguments("--disable-web-security");

		objChromeOptions.addArguments("--disable-notifications");
		objChromeOptions.addArguments("--enable-automation");
		objChromeOptions.addArguments("--disable-save-password-bubble");
		objChromeOptions.addArguments("test-type");
		objChromeOptions.addArguments("test-type=browser");
		objChromeOptions.addArguments("--no-sandbox");
		objChromeOptions.addArguments("--disable-dev-shm-usage");

		rdriver = new ChromeDriver(objChromeOptions);

		driver = new EventFiringWebDriver(rdriver);

		eventListener = new EventListener();
		driver.register(eventListener);

		driver.manage().window().maximize();
		driver.get(dataOf("URL")[ENVIRONMENT]);

		logger.info(" Successfuly launched browser window ");

	}

	/**
	 * @Description : The properties file data gets loaded into memory once this
	 *              method is called.
	 * @author baj80000835 : Yassar Zaman
	 * @param :
	 *            path : path of the properties file.
	 **/
	public void loadData(String path) {
		prop = new Properties();
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + path);
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	//
	// @Attachment(value = "{name}", type = "image/png")
	// public static byte[] takeScreenShot(String name) throws IOException {
	// return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	// }

	private int getEnvironment() {
		loadData(BPM_PROPERTIES_PATH);

		String field = prop.getProperty("ENV");
		int env = Integer.parseInt(field);

		return env;
	}

	/**
	 * @Description : Reads the data from property file by locating its value
	 *              through its key and returns back all its values in an array.
	 * @author baj80000835 : Yassar Zaman
	 * @param :
	 *            feildName of type string has the format PageName.FieldName
	 **/
	public String[] dataOf(String fieldName) {

		String field = prop.getProperty(fieldName);
		String[] fieldList = field.split(",");

		return fieldList;
	}

	/**
	 * @Description : This method returns the inverse of Base64.encode. Ideally
	 *              used to return the readable password test given the
	 *              encrypted password text as input.
	 * @author baj80000835 : Yassar Zaman
	 * @param :
	 *            encrypt of type string has the format base64 encoded value of
	 *            the password.
	 **/
	public String decodePassword(String encrypt) {
		String decrypt;

		byte[] decodedBytes = Base64.getDecoder().decode(encrypt);
		decrypt = new String(decodedBytes);

		return decrypt;

	}

	/**
	 * @Description : Switches to the iframe given the index number. When an
	 *              element is present under an iFrame then the driver needs to
	 *              be switches to that frame to be able to interact with that
	 *              element.
	 * @author baj80000835 : Yassar Zaman
	 * @param :
	 *            frameNumber of type int - pass the frame index or the number.
	 **/
	public void switchToFrame(int frameNumber) {

		driver.switchTo().defaultContent();
		driver.switchTo().frame(frameNumber);
		logger.info("Successfully switched to iFrame " + frameNumber);

	}

	/**
	 * @Description : Brings the driver forcus on the default frame. When you
	 *              switch a frame to access an elemeny and later move on to a
	 *              different page the driver context is still fixed on the
	 *              previous frame its gets reset when this method is called.
	 * @author baj80000835 : Yassar Zaman
	 * @param :
	 *            NA
	 **/
	public void switchToDefault() {

		driver.switchTo().defaultContent();
		logger.error("Successfully switched to default iFrame");

	}

	/**
	 * @Description : Clicks an element on the screen by locating through BY
	 *              class object.
	 * @author baj80000835 : Yassar Zaman
	 * @param :
	 *            ele - containing BY locator.
	 **/
	public void click(By ele) {

		driver.findElement(ele).click();

		logger.info("Successfully clicked the element " + ele.toString());

	}

	/**
	 * @Description : Clicks an element on the screen by locating through
	 *              WebElement class object.
	 * @author baj80000835 : Yassar Zaman
	 * @param :
	 *            ele - containing WebElement.
	 **/
	public void click(WebElement ele) {

		ele.click();

		logger.info("Successfully clicked the element " + ele.toString());
	}

	/**
	 * @Description : Keys in the text provided by locating through By class
	 *              object.
	 * @author baj80000835 : Yassar Zaman
	 * @param :
	 *            ele contains the By locator, txt contains the value to be
	 *            entered
	 **/
	public void enterText(By ele, String txt) {

		// driver.findElement(ele).clear();
		driver.findElement(ele).sendKeys(txt);

		logger.info("Successfully entered the text " + txt + " in the element " + ele.toString());

	}

	/**
	 * @Description : This mentod is used when entering password field or other
	 *              encripted field where the decrypted text is not to be
	 *              displayed in the logs.
	 * @author baj80000835 : Yassar Zaman
	 * @param :
	 *            ele contains the By locator, txt contains the encrypted value
	 *            to be entered
	 **/
	public void enterEncriptedText(By ele, String txt) {

		// driver.findElement(ele).clear();
		driver.findElement(ele).sendKeys(decodePassword(txt));

		logger.info("Successfully entered the text " + txt + " in the element " + ele.toString());

	}

	/**
	 * @Description : Selects the value provided from dropdown list box through
	 *              By class object
	 * @author baj80000835 : Yassar Zaman
	 * @param :
	 *            ele contains the By locator, txt contains the value to be
	 *            selected
	 **/
	public void select(By ele, String txt) {

		Select selBox = new Select(driver.findElement(ele));
		if (txt.equals(SELECT_RANDOM)) {
			List<WebElement> options = selBox.getOptions();
			Random rand = new Random();
			int size = options.size();
			int list = rand.nextInt(size);
			if (list == 0)
				list = 1;
			options.get(list).click();
		} else {
			selBox.selectByVisibleText(txt);
		}

		logger.info("Successfully selected the dropdown value " + txt + " in the element " + ele.toString());

	}

	/**
	 * @Description : Selects the value provided from dropdown list box through
	 *              By class object having given with a partial text.
	 * @author baj80000835 : Yassar Zaman
	 * @param :
	 *            ele contains the By locator, txt contains the partial value to
	 *            be used for selecting an option.
	 **/
	public void selectX(By ele, String txt) {

		Select selBox = new Select(driver.findElement(ele));
		List<WebElement> allItems = selBox.getOptions();
		int count = 0;
		for (WebElement item : allItems) {
			count++;
			String vv = item.getText().toString();
			if (vv.contains(txt)) {
				selBox.selectByIndex(count - 1);
			}
		}
		logger.info("Successfully selected the dropdown value " + txt + " in the element " + ele.toString());

	}

	/**
	 * @Description : Returns a list of webelements which carry a common
	 *              locator. For instance you have a set of radio buttons with a
	 *              similar property to locate. Ex : Gender label may have 2
	 *              radio's 1)male 2)female and locating gender will return the
	 *              list of items which can later be used to select.
	 * @author baj80000835 : Yassar Zaman
	 * @param :
	 *            ele to By class object locator
	 **/
	public List<WebElement> getListOfElements(By ele) {

		List<WebElement> checkedElements = null;

		checkedElements = driver.findElements(ele);
		logger.info("Sucessfully located the elements " + ele.toString());

		return checkedElements;

	}

	/**
	 * @Description : This returns the attribute value of a particular element.
	 *              When reading hidden fields the value is not returned with
	 *              getText() in which case we can read their attributes.
	 * @author baj80000835 : Yassar Zaman.
	 * @param :
	 *            ele to locate an element using By class obj, attrbName is the
	 *            dom attribute to be fetched.
	 **/
	public String getAttribute(By ele, String attrbName) {

		String val = null;

		hangon(2);
		val = driver.findElement(ele).getAttribute("value");
		logger.info("Sucessfully retrieved the attribute " + attrbName + " from the elements " + ele.toString());

		return val;
	}

	/**
	 * @Description : Returns the current page title.
	 * @author baj80000835 : Yassar Zaman
	 * @param :
	 *            NA
	 **/
	public String getPageTitle() {
		return driver.getTitle();
	}

	/**
	 * @Description : Hover overs the element.
	 * @author baj80000835 : Yassar Zaman
	 * @param :
	 *            ele : By class object
	 **/
	public void hover(By ele) {
		actions = new Actions(driver);
		actions.moveToElement(driver.findElement(ele)).perform();
		logger.info("Sucessfully hovered on " + ele);
	}

	/**
	 * @Description : Presses the ESC button.
	 * @author baj80000835 : Yassar Zaman
	 * @param :
	 *            NA
	 **/
	public void pressEscape(int count) {

		for (int i = 0; i <= count; i++) {

			hangon(2);
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).build().perform();
			logger.info("Sucessfully pressed the ESC Button ");

		}
	}

	/**
	 * @Description : Presses the Enter button.
	 * @author baj80000835 : Yassar Zaman
	 * @param :
	 *            NA
	 **/
	public void pressEnter(int count) {

		for (int i = 0; i <= count; i++) {

			hangon(2);
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ENTER).build().perform();
			logger.info("Sucessfully pressed the ENTER Button ");

		}
	}

	/**
	 * @Description : Helps in delaying the execution for the specified number
	 *              of seconds.
	 * @author baj80000835 : Yassar Zaman
	 * @param :
	 *            timeinSeconds is a integer specifying the number of seconds
	 *            execution is to be delayed.
	 **/
	public static void hangon(int timeinSeconds) {

		try {
			logger.info("Waiting for " + 1000 * timeinSeconds + " seconds");
			Thread.sleep(timeinSeconds * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @Description : Closes the browser and quits the browser session.
	 * @author baj80000835 : Yassar Zaman
	 * @param :
	 *            NA
	 **/
	public void closeBrowser() {
		driver.close();
		driver.quit();
	}

	// need to add a keyword as input param, dashboard

	public static void untilURLIsLoaded() {
		try {
			logger.info("Waiting for Dashboard to show up");
			Wait<WebDriver> waiting = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(60))
					.pollingEvery(Duration.ofSeconds(3)).ignoring(ElementClickInterceptedException.class)
					.ignoring(StaleElementReferenceException.class);
			waiting.until(ExpectedConditions.urlContains("dashboards"));
			logger.info("Dashboard is visible");
		} catch (Exception e) {
			logger.info("Exception in untilDashboardIsLoaded " + e.getMessage());
		}
	}

	public static String CaptureScreen() {

		String DestFileDir = null;
		try {
			logger.info("In ScreenCapture");
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			String destFileName = formater.format(calendar.getTime()) + GetRandom.longNumber();

			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			logger.info("Successfully took screen shot");

			DestFileDir = System.getProperty("user.dir") + "\\extentReports\\screenshots\\" + destFileName + ".png";

			Files.copy(scrFile.toPath(), new File(DestFileDir).toPath());
			logger.info("Successfully saved screen shot to " + DestFileDir);
		} catch (IOException e) {
			logger.error("Unable to capture the screen shot");
			logger.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DestFileDir;
	}

	/**
	 * @Description : Use this method to read a .txt file.
	 * @author baj80000835 : Yassar Zaman
	 * @param :
	 *            NA
	 **/
	public static List<String> fetchFileContect(String fileName) {

		try {
			Path thePath = Paths.get(System.getProperty("user.dir") + "\\resources\\dump\\" + fileName + ".txt");
			theData = Files.readAllLines(thePath);
		} catch (Exception e) {
			logger.error("Failed to read the file, kindly provide the right path and file name" + e.getMessage());
		}

		return theData;
	}
}
