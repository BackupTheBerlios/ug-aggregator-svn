package it.xpug.aggregator;

import java.io.File;

import junit.framework.TestCase;

public class NewsFileWriterTest extends TestCase {

	public void testFilenameIsCorrect() throws Exception {
		//(AAAAMMGG di inserimento)_(nome xpug)_(count).txt
		File actual = new NewsFileWriter(NewsBuilder.withInsertionDate("2006/03/18")).write();
		assertEquals("20060318_XUPMI_1.txt", actual.getName());
	}
}
