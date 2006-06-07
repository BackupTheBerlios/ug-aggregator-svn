package it.xpug.aggregator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileFinder {
	private String directory;
	private String fileNamePattern;

	public FileFinder(String directory) {		
		this(directory,"\\d{8}_\\w+_\\d+\\.txt");
	}
	
	public FileFinder(String directory, String fileNamePattern) {
		this.directory = directory;
		this.fileNamePattern = fileNamePattern;
	}

	public String getFileContent(File file) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(file));
		String line;
		String fileContent = "";
		while ((line = in.readLine()) != null) {
			fileContent = fileContent.concat(line + "\n");
		}
		return fileContent;
	}

	public File[] listFiles() {
		File dir = new File(directory);
		FilenameFilter selectOnlyXpUgFile = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				Pattern p = Pattern.compile(fileNamePattern);
				Matcher m = p.matcher(name);
				return m.matches();
			}
		};
		File[] files = dir.listFiles(selectOnlyXpUgFile);
		return files;
	}

	public String[] listFilesContent() throws IOException {
		File[] files = listFiles();
		String[] fileContents = new String[files.length];
		for (int i = 0; i < files.length; i++) {
			fileContents[i] = getFileContent(files[i]);
		}
		return fileContents;
	}
}
