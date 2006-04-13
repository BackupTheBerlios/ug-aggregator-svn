package it.xpug.aggregator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import junit.framework.TestCase;

public class FileFinderTest extends TestCase {

	String tmpDir="./prova";
	//c:\\"+System.currentTimeMillis();
	
	/*protected void setUp() throws Exception {
	    
		File file=new File(tmpDir);
		file.mkdir();
		File file2=new File("c:\\fake.news");
		BufferedWriter out = new BufferedWriter(new FileWriter(file));
		out.write("pippo");
		out.flush();
		out.close();
	}*/
	public void testSimple() throws Exception {
		FileFinder fileFinder=new FileFinder(tmpDir);
		String fileContent=fileFinder.getFileContent();
		assertEquals("pippo",fileContent);
	}

}
