package it.xpug.aggregator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileFinder {
	private String directory;

	public FileFinder(String directory) {
		this.directory = directory;
	}

	public String getFileContent(File file) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(file));
		String line;
		String fileContent="";
		while ((line=in.readLine())!=null)
		{
			fileContent=fileContent.concat(line+"\n");
		}
		return fileContent;
	}

	public File[] listFiles() {
		File dir = new File(directory);
//TODO da terminare e passare come argomento a listFiles		
//		FilenameFilter selectOnlyXpUgFile = new FilenameFilter() {
//			public boolean accept(File dir, String name) {
//				 Pattern p = Pattern.compile("\\d{8}_\\w+_\\d+\\.txt");
//				 Matcher m = p.matcher(name);
//				 return m.matches(); 
//			}
//		};
		
		File[] files = dir.listFiles();
	
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
