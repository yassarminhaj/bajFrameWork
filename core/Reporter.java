package core;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;

public class Reporter extends SeleniumWrapper {

	public static ExtentReports extentReport;
	public static ExtentTest exTest;
	public static String extentReportPath = System.getProperty("user.dir") + "\\extentReports\\";
	public static String extentConfigFilePath = System.getProperty("user.dir")
			+ "\\resources\\parameters\\extent-config.xml";
	public static ATUTestRecorder recorder;

	
	static {
		try {
			extentReport = new ExtentReports(extentReportPath
					+ new SimpleDateFormat("dd_mm_yyyy_hh_mm_ss").format(Calendar.getInstance().getTime()) + ".html",
					false);
			extentReport.loadConfig(new File(extentConfigFilePath));
			extentReport.addSystemInfo("Tester", "Yassar");
		} catch (Exception e1) {
			logger.error("Unable to start recording due to exception " + e1);
		}
	}
	
	public static void startRecording() {
		try {
			recorder = new ATUTestRecorder(System.getProperty("user.dir") + "\\recording\\", generateFileName(), false);
			recorder.start();
		} catch (ATUTestRecorderException e) {
			e.printStackTrace();
		}
	}

	public static void startReporting(String TCName) {
		try {			

			exTest = extentReport.startTest(TCName);

			info("Application ready to execute '" + TCName + "' Test Case");
			logger.info("Started executing " + TCName);

		} catch (Exception e) {
			logger.error("Error occured in start reporting due to exception " + e);
		}

	}

	public static void setTestCase(String description, String author, String categ) {
		exTest.setDescription(description);
		exTest.assignAuthor(author);
		exTest.assignCategory(categ);
	}

	public static void pass(String txt) {
		try {
			String screenshot = CaptureScreen();
			String image = exTest.addScreenCapture(screenshot);
			exTest.log(LogStatus.PASS, image);
			exTest.log(LogStatus.PASS, txt);
			extentReport.endTest(exTest);
		} catch (Exception e) {
			logger.error("Error occured in pass test due to exception " + e);
		}
	}

	public static void fail(String txt) {
		try {
			String screenshot = CaptureScreen();
			String image = exTest.addScreenCapture(screenshot);
			exTest.log(LogStatus.FAIL, image);
			exTest.log(LogStatus.FAIL, txt);
			extentReport.endTest(exTest);

		} catch (Exception e) {
			logger.error("Error occured in fail test due to exception " + e);
		}
	}

	public static void exception(String txt) {
		try {
			exTest.log(LogStatus.ERROR, txt);
		} catch (Exception e) {
			logger.error("Error occured while reporting an exception due to " + e);
		}
	}

	public static void info(String info) {
		exTest.log(LogStatus.INFO, info);
	}

	public static void screenInfo(String info) {
		hangon(3);
		String screenshot = CaptureScreen();
		String image = exTest.addScreenCapture(screenshot);
		exTest.log(LogStatus.INFO, info, image);
	}

	public static void endReporting() {
		try {
			extentReport.endTest(exTest);
			extentReport.flush();
		} catch (Exception e) {
			logger.error("Error occured in end reporting due to exception " + e);
		}
	}

	public static void stopRecording() {
		try {
			recorder.stop();
		} catch (Exception e) {
			logger.error("Error encountered while stoping the recording due to " + e);
		}
	}

	public static String generateFileName() {

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String destFileName = formater.format(calendar.getTime());

		return destFileName;
	}
}
