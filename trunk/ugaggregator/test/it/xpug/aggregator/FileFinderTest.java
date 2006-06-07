package it.xpug.aggregator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import junit.framework.TestCase;

public class FileFinderTest extends TestCase {
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

	protected void tearDown() throws Exception {
		deleteDir(newsDirectory);
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

}
