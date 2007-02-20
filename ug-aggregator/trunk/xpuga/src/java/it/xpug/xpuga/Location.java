package it.xpug.xpuga;

import java.io.File;
import java.io.IOException;

public class Location {

	private String path;
	private String context;

	public Location(String context, String path) {
		this.path = path;
		this.context = context;
	}

	public boolean exists() {
		File directory = new File(getAbsolutePath());
		boolean exists = directory.exists();
		boolean isDirectory = directory.isDirectory();
		return exists && isDirectory;
	}

	public void create() throws IOException {
		File directory = new File(getAbsolutePath());
		directory.mkdirs();
	}

	public String getAbsolutePath() {
		if (new File(path).isAbsolute()) return path;
		return context + File.separator + path;
	}

	public String getPath() {
		return path;
	}

}
