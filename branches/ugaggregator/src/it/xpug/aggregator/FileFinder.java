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

	public FileFinder() {		
		this(System.getProperty(NewsFileWriter.IT_XPUG_AGGREGATOR_NEWS_DIR),"\\d{8}_\\w+_\\d+\\.txt");
	}
	
	public FileFinder(String directory) {		
		this(directory, "\\d{8}_\\w+_\\d+\\.txt");
	}
	
	public FileFinder(String directory, String fileNamePattern) {
		this.directory = directory;
		this.fileNamePattern = fileNamePattern;
	}

	public String getFileContent(File file) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(file));
		return getStreamContent(in);
	}

	static String getStreamContent(BufferedReader in) throws IOException {
		String line;
		String fileContent = "";
		while ((line = in.readLine()) != null) {
			fileContent = fileContent.concat(line + "\n");
		}
		in.close();
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

	public static String extractGroupName(String string) {
		Pattern p = Pattern.compile("\\d{8}_(\\w+)_\\d+\\.txt");
		Matcher m = p.matcher(string);
		if (m.matches())
			return m.group(1);
		return "";
	}

	public News[] listNews() throws IOException {
		File[] files=listFiles();
		String[] filescontent=listFilesContent();
		News[] news=new News[files.length];
		for (int i=0;i<files.length;i++)
		{
			news[i]=new News(filescontent[i],extractGroupName(files[i].getName()));
		}
		return news;
	}
}
