package it.xpug.xpuga;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

public class LocationTest extends TestCase {
	
	private File dir;
	private String path;

	private File generateTmpDir() throws IOException
	{
	File directory = File.createTempFile("xpuga", "");
	if (!directory.delete())
		throw new IOException();
	if (!directory.mkdir())
		throw new IOException();
	return directory;
	}
	
	protected void setUp() throws Exception {
	 dir=generateTmpDir();
	 path=dir.getPath();
	}

	public void testSimpleLocation() throws Exception {
		Location location=new Location(path);
		assertTrue(location.exists());
	}
	public void testNotExistingLocation() throws Exception {
		Location location=new Location(path+"/notexistingtestlocation");
		assertFalse(location.exists());
	}
	
	public void testLocationCreation() throws Exception {
		Location location=new Location(path+"/newtestlocation");
		location.create();
		assertTrue(location.exists());
		File directory=new File(path+"/newtestlocation");
		directory.delete();
		assertFalse(location.exists());
		
	}
	
}
