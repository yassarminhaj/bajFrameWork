package core;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EventListener implements WebDriverEventListener {

	public final static Logger logger = Logger.getLogger(SeleniumWrapper.class.getName());
	public WebDriverWait waiting;

	SeleniumWrapper automaton = new SeleniumWrapper();
	
	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {

	}

	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		logger.info("Automateon is looking for " + by.toString() + "");
	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		element.clear();
		logger.info("Cleared the field " + element.toString() + "");
	}

	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
		logger.info("Waiting for " + element.toString() + " to be clickable");
		waiting = new WebDriverWait(driver, 600);
		waiting.until(ExpectedConditions.elementToBeClickable(element));
	}

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
		waiting = new WebDriverWait(driver, 600);
		waiting.equals(ExpectedConditions.jsReturnsValue("return document.readyState").equals("complete"));
		logger.info("The element " + element.toString() + " was clicked successfully");
	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		logger.info("The element " + element.toString() + " was entered with data");
	}

	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		logger.info("Positioning " + by.toString() + " to view on screen");
		int y = driver.findElement(by).getLocation().getY();
		((JavascriptExecutor) driver).executeScript("scroll(0," + y + ")");
	}

	@Override
	public void onException(Throwable throwable, WebDriver driver) {

		logger.warn("Entered the exception block");

		if (throwable instanceof TimeoutException) {
			logger.error("TimeoutException");
		} else if (throwable instanceof NoSuchElementException) {
				logger.error("Unable to interact with the element, switch the frame or change the locator");
				logger.warn("NoSuchElementException received");
		} else if (throwable instanceof UnhandledAlertException) {
			logger.error("UnhandledAlertException");
			driver.switchTo().alert().dismiss();
		} else if (throwable instanceof NoAlertPresentException) {
			logger.error("NoAlertPresentException");
		} else if (throwable instanceof ElementClickInterceptedException) {
			logger.warn("ElementClickInterceptedException");
		} else if (throwable instanceof StaleElementReferenceException) {
			logger.warn("StaleElementReferenceException");
		} else if (throwable instanceof NoSuchSessionException) {
			logger.error("Lost connectivity with the driver , received NoSuchSessionException");
		} else if (throwable instanceof WebDriverException) {
			logger.error("Lost connectivity with the browser , WebDriverException");
		} else if (throwable instanceof Exception) {
			logger.error("Exception");
		} else {
			logger.error("Unknown error. The exception is not handled" + throwable.getMessage());
		}
		
		//throw new RuntimeException();
	}

	@Override
	public void afterAlertAccept(WebDriver arg0) {

	}

	@Override
	public void afterAlertDismiss(WebDriver arg0) {

	}

	@Override
	public void afterNavigateBack(WebDriver arg0) {

	}

	@Override
	public void afterNavigateForward(WebDriver arg0) {

	}

	@Override
	public void afterNavigateRefresh(WebDriver arg0) {

	}

	@Override
	public void afterNavigateTo(String arg0, WebDriver arg1) {

	}

	@Override
	public void afterScript(String arg0, WebDriver arg1) {

	}

	@Override
	public void beforeAlertAccept(WebDriver arg0) {

	}

	@Override
	public void beforeAlertDismiss(WebDriver arg0) {

	}

	@Override
	public void beforeNavigateBack(WebDriver arg0) {

	}

	@Override
	public void beforeNavigateForward(WebDriver arg0) {

	}

	@Override
	public void beforeNavigateRefresh(WebDriver arg0) {

	}

	@Override
	public void beforeScript(String arg0, WebDriver arg1) {

	}

	@Override
	public <X> void afterGetScreenshotAs(OutputType<X> arg0, X arg1) {

	}

	@Override
	public void afterGetText(WebElement arg0, WebDriver arg1, String arg2) {

	}

	@Override
	public void afterSwitchToWindow(String arg0, WebDriver arg1) {

	}

	@Override
	public <X> void beforeGetScreenshotAs(OutputType<X> arg0) {

	}

	@Override
	public void beforeGetText(WebElement arg0, WebDriver arg1) {

	}

	@Override
	public void beforeSwitchToWindow(String arg0, WebDriver arg1) {

	}

}
