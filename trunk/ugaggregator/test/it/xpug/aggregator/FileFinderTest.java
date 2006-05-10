package it.xpug.aggregator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import junit.framework.TestCase;

public class FileFinderTest extends TestCase {
	private String tmpDir;
	private String filename;
	private File newsDirectory;
	
	protected void setUp() throws Exception {
		filename = "fake.news";
		
		newsDirectory=new File(System.getProperty("java.io.tmpdir"), 
				String.valueOf(System.currentTimeMillis()));
		newsDirectory.mkdir();
		tmpDir=newsDirectory.getPath();
		Thread.sleep(10);
		//System.out.println(tmpDir);
		/*
		File file=new File(newsDirectory.getPath(),filename);
		FileWriter fileWriter = new FileWriter(file);
		BufferedWriter writer=new BufferedWriter(fileWriter);
		writer.write("pippo");
		writer.close();*/
	}
	
	/*protected void tearDown() throws Exception {
	    File[] files= newsDirectory.listFiles();
	    
	}*/
	
	/*public void testSimple() throws Exception {
		FileFinder fileFinder=new FileFinder(tmpDir);
		String fileContent=fileFinder.getFileContent();
		assertEquals("pippo",fileContent);
	}	*/
	
	public void testEmptyList() {
		FileFinder fileFinder=new FileFinder(tmpDir);
		File[] files = fileFinder.listFiles();
		assertEquals(0, files.length);
	}
	
	public void testList() throws IOException {
		File file=new File(tmpDir,filename);
		FileWriter fileWriter = new FileWriter(file);
		BufferedWriter writer=new BufferedWriter(fileWriter);
		writer.write("pippo");
		writer.close();
		
		FileFinder fileFinder=new FileFinder(tmpDir);
		File[] files = fileFinder.listFiles();
		assertEquals(1, files.length);
	}
	
	public void createFile(String fileName) throws IOException
	{
	File file=new File(tmpDir,fileName);
	FileWriter fileWriter = new FileWriter(file);
	BufferedWriter writer=new BufferedWriter(fileWriter);
	writer.write("pippo");
	writer.close();
	}
	public void testList2Files() throws IOException {
		createFile("file1.news");
		createFile("file2.news");
		
		FileFinder fileFinder=new FileFinder(tmpDir);
		File[] files = fileFinder.listFiles();
		assertEquals(2, files.length);
	}
	
	
}
