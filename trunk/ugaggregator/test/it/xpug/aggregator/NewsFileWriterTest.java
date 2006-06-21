package it.xpug.aggregator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import junit.framework.TestCase;

public class NewsFileWriterTest extends TestCase {

	public void testFilenameIsCorrect() throws Exception {
		//(AAAAMMGGhhmmss)_(nome xpug).txt
		File actual = new NewsFileWriter(NewsBuilder.withInsertionDate("2006/03/18 234412")).write();
		assertEquals("20060318-234412_XPUGMI.txt", actual.getName());
	}
	
	public void testFileContent() throws Exception {
		News newsToWrite = NewsBuilder.withInsertionDate("2006/03/18 234412");
		File actual = new NewsFileWriter(newsToWrite).write();
		BufferedReader buffer = new BufferedReader(new FileReader(actual));
		String str;
		StringBuffer strBuffer = new StringBuffer();
        while ((str = buffer.readLine()) != null) {
            strBuffer.append(str);
        }
        buffer.close();
        
        assertEquals(newsToWrite.title()+newsToWrite.description()+
        		"20060318-234412"+"20060712-000000", strBuffer.toString());
		
	}
}
