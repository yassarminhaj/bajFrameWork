package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;

public class TestDataReaderOld {

	public static List<String[]> readFile(String path) {
		
		File myFile = new File(System.getProperty("user.dir") + path);
		FileReader fileReader;
		CSVReader csvReader;
		List<String[]> testData = null;
		
		try {
			
			fileReader = new FileReader(myFile);
			csvReader = new CSVReader(fileReader);
			testData = csvReader.readAll();
			
		} catch (FileNotFoundException e) {

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testData;
	}
	
	
	@DataProvider(name = "fetchTestData")
	public Iterator<String[]> implementDataProvider(){
		
		List<String[]> data = readFile("/resources/testdata/NationalID.csv");
		Iterator<String[]> theData = data.iterator();
		
		theData.next(); 
		
		return theData;		
	}

	@Test(dataProvider = "fetchTestData")
	public void runTest(List<String> td){
		
		int count = td.size();
		
		for(int i=0; i < count; i++){
			System.out.println(td.get(i));;
		}
	}
}
