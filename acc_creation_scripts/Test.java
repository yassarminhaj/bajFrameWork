package acc_creation_scripts;

import core.GetRandom;

public class Test {
	
	@org.testng.annotations.Test
	public void test(){
		String theval = GetRandom.getFromDump("firstName");
		
		System.out.println(theval);
	}

}
