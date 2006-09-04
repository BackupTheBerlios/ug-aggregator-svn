package it.xpug.aggregator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import junit.framework.TestCase;

public class FileFinderTest extends TestCase {
	
	public class FakeReader extends java.io.Reader {

		boolean isClosed = false;
		
		public void close() throws IOException {
			isClosed = true;
		}

		public int read(char[] cbuf, int off, int len) throws IOException {
			return -1;
		}
	}

	private String tmpDir;

	private File newsDirectory;

	protected void setUp() throws Exception {
		newsDirectory = File.createTempFile("xpug", "");
		if (!newsDirectory.delete())
			throw new IOException();
		if (!newsDirectory.mkdir())
			throw new IOException();
		tmpDir = newsDirectory.getPath();
	}

	protected void tearDown() throws Exception {
		deleteDir(newsDirectory);
	}

	public void testEmptyList() throws IOException {
		FileFinder fileFinder = new FileFinder(tmpDir);
		String[] contents = fileFinder.listFilesContent();
		assertEquals(0, contents.length);
	}

	public void testList() throws IOException {
		createFile("20051203_XPUGMilano_1.txt", "someContents\nandothercontents");
		FileFinder fileFinder = new FileFinder(tmpDir);
		String[] contents = fileFinder.listFilesContent();
		assertEquals(1, contents.length);
		assertEquals("someContents\nandothercontents\n", contents[0]);
	}

	public void createFile(String fileName, String content) throws IOException {
		File file = new File(tmpDir, fileName);
		FileWriter fileWriter = new FileWriter(file);
		BufferedWriter writer = new BufferedWriter(fileWriter);
		writer.write(content);
		writer.close();
	}

	public void testList2Files() throws IOException {
		createFile("20051203_XPUGMilano_1.txt", "first");
		createFile("20051203_XPUGMilano_2.txt", "second");

		FileFinder fileFinder = new FileFinder(tmpDir);
		String[] contents = fileFinder.listFilesContent();
		assertEquals(2, contents.length);
		assertEquals("first\n",contents[0]);
		assertEquals("second\n",contents[1]);
		
	}
	
	public void testListFilter() throws IOException {
		createFile("20051203_XPUGMilano_1.txt", "first");
		createFile("file2.tmp", "second");
		createFile("file3.tmp", "third");
		FileFinder fileFinder = new FileFinder(tmpDir);
		String[] contents = fileFinder.listFilesContent();
		assertEquals(1, contents.length);
		assertEquals("first\n", contents[0]);		
	}
	
	public void testList2Filter() throws IOException {
		createFile("20051203_XPUGMilano_1.txt", "first");
		createFile("20051203_XPUGMilano_2.txt", "second");
		createFile("file2.tmp", "second");
		createFile("file3.tmp", "third");
		FileFinder fileFinder = new FileFinder(tmpDir);
		String[] contents = fileFinder.listFilesContent();
		assertEquals(2, contents.length);
		assertEquals("first\n", contents[0]);
		assertEquals("second\n", contents[1]);
	}
	
	public void testExtractGroupName() throws IOException {
		assertEquals("XPUGMilano",
				FileFinder.extractGroupName("20051203_XPUGMilano_1.txt"));
		assertEquals("XPUGRoma",
				FileFinder.extractGroupName("20051104_XPUGRoma_2.txt"));
	}
	
	public void testFindOneNews() throws IOException {
		createFile("20051203_XPUGMilano_1.txt", "Titolo news\n" + 
    			"Descrizione news\n" +
    			"2006/03/14 234412\n" + 
    			"2010/04/25\n");		
		FileFinder fileFinder = new FileFinder(tmpDir);
		News finded[] = fileFinder.listNews();
		assertEquals("Titolo news", finded[0].title());
		assertEquals("XPUGMilano",finded[0].getUserGroup());
	}

	public void testFindTwoNews() throws IOException {
		createFile("20051203_XPUGMilano_1.txt", "Titolo news\n" + 
    			"Descrizione news\n" +
    			"2006/03/14 234412\n" + 
    			"2010/04/25\n");	
		createFile("20051203_XPUGMinsk_1.txt", "Titolo news di minsk\n" + 
    			"Descrizione news\n" +
    			"2006/03/14 234412\n" + 
    			"2010/04/25\n");
		FileFinder fileFinder = new FileFinder(tmpDir);
		News[] finded = fileFinder.listNews();
		assertEquals("Titolo news", finded[0].title());
		assertEquals("XPUGMilano",finded[0].getUserGroup());
		assertEquals("Titolo news di minsk", finded[1].title());
		assertEquals("XPUGMinsk",finded[1].getUserGroup());
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
	
	public void testParametrizedFilefinder() throws Exception {
		createFile("20051203_XPUGMilano_1.txt", "first");
		FileFinder fileFinder = new FileFinder(tmpDir,"\\d{8}_\\w+_\\d+\\.txt");
		String[] contents = fileFinder.listFilesContent();
		assertEquals(1, contents.length);
		assertEquals("first\n", contents[0]);		
	}

	public void testGetFileContentsShouldReadContents()  throws Exception {
		createFile("20051203_XPUGMilano_1.txt", "first\nsecond");
		FileFinder fileFinder = new FileFinder(tmpDir,"\\d{8}_\\w+_\\d+\\.txt");
		String contents = fileFinder.getFileContent(fileFinder.listFiles()[0]);
		
		assertEquals("first\nsecond\n", contents);	
	}
	
	public void testShouldCloseStreamDammit() throws Exception {
		FakeReader fakeReader = new FakeReader();
		BufferedReader br = new BufferedReader(fakeReader);
		FileFinder.getStreamContent(br);
		assertTrue("reader not closed", fakeReader.isClosed);
	}
}
