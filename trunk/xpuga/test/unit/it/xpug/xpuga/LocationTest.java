package it.xpug.xpuga;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

public class LocationTest extends TestCase {

	private String absolutePath;

	protected void setUp() throws Exception {
		absolutePath = generateTmpDir().getPath();
	}

	private File generateTmpDir() throws IOException {
		File directory = File.createTempFile("xpuga", "");
		if (!directory.delete())
			throw new IOException();
		if (!directory.mkdir())
			throw new IOException();
		return directory;
	}

	public void testExistingLocation() throws Exception {
		Location location = new Location("/context-ignored", absolutePath);
		assertTrue(location.exists());
	}

	public void testNotExistingLocation() throws Exception {
		Location location = new Location("/context-ignored", absolutePath + "/notexistingtestlocation");
		assertFalse(location.exists());
	}

	public void testLocationCreation() throws Exception {
		Location location = new Location("/context-ignored", absolutePath + "/newtestlocation");
		location.create();
		assertTrue(location.exists());
		File directory = new File(absolutePath + "/newtestlocation");
		directory.delete();
		assertFalse(location.exists());
	}
	
	public void testRelativePathname() throws Exception {
		Location location = new Location(absolutePath, "pippo");
		assertEquals(absolutePath + "/" + "pippo", location.getAbsolutePath());
		assertEquals("pippo", location.getPath());
	}
	
	public void testExistingRelativePathname() throws Exception {
		String expectedPath = absolutePath + "/" + "pippo";
		new File(expectedPath).mkdirs();
		
		Location location = new Location(absolutePath, "pippo");
		assertEquals(expectedPath, location.getAbsolutePath());
		assertTrue("testExistingRelativePathname", location.exists());
	}
	
	public void testCreateRelativePathname() throws Exception {
		String expectedPath = absolutePath + "/" + "pippo";
		
		Location location = new Location(absolutePath, "pippo");
		assertFalse("prima si accorge che non c'e'", location.exists());
		location.create();
		assertTrue("dopo si accorge che c'e'", location.exists());
		assertTrue("e in effetti c'e'", new File(expectedPath).isDirectory());
	}
	
	public void testAbsolutePathname() throws Exception {
		Location location = new Location("/context-should-be-ignored", absolutePath);
		assertEquals(absolutePath, location.getAbsolutePath());
		assertEquals(absolutePath, location.getPath());
		assertTrue(location.exists());
	}	
}
