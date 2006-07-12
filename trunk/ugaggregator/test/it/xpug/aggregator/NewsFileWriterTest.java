package it.xpug.aggregator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import junit.framework.TestCase;

public class NewsFileWriterTest extends TestCase {

	protected void setUp() throws Exception {
		System.setProperty(NewsFileWriter.IT_XPUG_AGGREGATOR_NEWS_DIR, InitServlet.DEFAULT_NEWSDB_DIR);
	}
	
	protected void tearDown() throws Exception {
		new File("pistacchio").delete();
	}
	
	public void testFilenameIsCorrect() throws Exception {
		News news = NewsBuilder.withInsertionDate("2006/03/18 234412");
		NewsFileWriter newsFileWriter = new NewsFileWriter(news);
		File actual = newsFileWriter.write();
		//(AAAAMMGGhhmmss)_(nome xpug).txt
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
	
	public void testShouldCreateDirIfItDoesNotExist() throws InterruptedException {
		File pistacchio=new File("pistacchio");//.delete();
		deleteDir(pistacchio);
		
		//Thread.sleep(1000);
		assertFalse(new File("pistacchio").exists());
		
		System.setProperty(NewsFileWriter.IT_XPUG_AGGREGATOR_NEWS_DIR, "pistacchio");
		News news = NewsBuilder.withInsertionDate("2006/03/18 234412");
		new NewsFileWriter(news);
		
		assertTrue(new File("pistacchio").exists());
		assertTrue(new File("pistacchio").isDirectory());
	}
	
	private void deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				deleteDir(new File(dir, children[i]));
			}
		}
		dir.delete();
	}
}
