package it.xpug.xpuga;

import java.io.File;
import java.io.IOException;

public class Location {

	private String path;

	public Location(String path) {
		this.path=path;
	}

	public boolean exists() {
		File directory=new File(path);
		boolean exists=directory.exists();
		boolean isDirectory=directory.isDirectory();
		return (exists && isDirectory);
	}

	public void create() throws IOException {
		File directory=new File(path);
		directory.delete();
		directory.mkdirs();
	}

}
