package core;

import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

@SuppressWarnings("deprecation")
public class GetRandom {

	private static List<String> dumpDataList = null;

	public static String text(int len) {
		return RandomStringUtils.randomAlphabetic(len);
	}

	public static String alphaNumeric(int len) {
		return RandomStringUtils.randomAlphanumeric(len);
	}

	public static String numericText(int maxLen) {
		return RandomStringUtils.randomNumeric(maxLen);
	}

	public static int numberBetween(int startLen, int endLen) {

		Random randNum = new Random();
		int theNumber = randNum.nextInt(endLen);
		if (theNumber < startLen) {
			theNumber = startLen;
		}

		return theNumber;
	}

	public static long longNumber() {

		Random rand = new Random();
		long theItem;
		theItem = rand.nextLong();

		if (theItem == 0) {
			theItem = 1;
		}

		return rand.nextLong();
	}

	public static int number(int size) {

		Random rand = new Random();
		int theItem;
		theItem = rand.nextInt(size);

		if (theItem == 0) {
			theItem = 1;
		}

		return rand.nextInt(size);
	}

	public static String textWith(int len, char[] theChars) {
		return RandomStringUtils.random(len, theChars);
	}

	public static String getFromDump(String fileName) {

		try {
			dumpDataList = SeleniumWrapper.fetchFileContect(fileName);
		} catch (Exception e) {
			System.err.print("Failed to read the file, kindly provide the right path and file name" + e.getMessage());
		}

		int randNum = numberBetween(1, dumpDataList.size());
		String randomData = dumpDataList.get(randNum);

		return randomData;
	}

	public static String[] getFromDumpX(String fileName) {

		try {
			dumpDataList = SeleniumWrapper.fetchFileContect(fileName);
		} catch (Exception e) {
			System.err.print("Failed to read the file, kindly provide the right path and file name" + e.getMessage());
		}

		int randNum = numberBetween(1, dumpDataList.size());
		String[] randomData = dumpDataList.get(randNum).split(",");

		return randomData;
	}
	
}