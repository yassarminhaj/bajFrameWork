package acc_creation_scripts;

import org.testng.annotations.Test;

import core.GetRandom;

public class TestClas {
	
	@Test
	public void dumpreader(){
		String val = GetRandom.getFromDump("firstName");
		System.out.println(val);
		
		String val1 = GetRandom.getFromDump("lastName");
		System.out.println(val1);
		
		String[] vala = GetRandom.getFromDumpX("firstName");
		System.out.println(vala[0]);
		System.out.println(vala[1]);
		
		String[] vala1 = GetRandom.getFromDumpX("lastName");
		System.out.println(vala1[0]);
		System.out.println(vala1[1]);
		
	}

}
