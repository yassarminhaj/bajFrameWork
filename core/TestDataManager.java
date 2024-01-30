package core;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class TestDataManager {

	public static CsvWriter csvDataWriter = null;

	public static CsvReader readCsv(String filename) {
		CsvReader csvData = null;
		try {
			csvData = new CsvReader(System.getProperty("user.dir") + "\\resources\\testdata\\" + filename + ".csv");

			csvData.readHeaders();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return csvData;

	}
	
	/*
	 * While requiring to read data from a given path within the project
	 * */
	public static CsvReader readCsv(String path,String filename) {
		CsvReader csvData = null;
		try {
			csvData = new CsvReader(System.getProperty("user.dir") + path + filename + ".csv");

			csvData.readHeaders();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return csvData;

	}

	/*
	 * The reason for returning the csvWriter is to enable us write to different
	 * csv file, we can use the writeCsv overloaded function to write to the
	 * intended file
	 */
	public static CsvWriter CreateNewCsv(String filename) {

		csvDataWriter = new CsvWriter(System.getProperty("user.dir") + "\\resources\\testoutput\\" + filename + ".csv");

		return csvDataWriter;
	}

	/* This method is to be used if only one csv file is opened for writing */
	public static void writeCsv(String string) {
		try {
			csvDataWriter.write(string);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void movePointerToNextLine() {
		try {
			csvDataWriter.endRecord();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
