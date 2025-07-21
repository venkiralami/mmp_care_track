package org.iitwf.healthcare.mmp;

public class StringEx {

	public static void main(String[] args) {
		
		String str="{\r\n"
				+ "    \"name\": \"$name\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}";
		
		String output = str.replace("$name","Selenium");
		System.out.println(output);
	}
}
