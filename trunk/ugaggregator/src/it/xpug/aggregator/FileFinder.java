package it.xpug.aggregator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.xpath.XPathAPI;

public class FileFinder {
private String directory;
	
	public FileFinder(String directory) {
		this.directory=directory;
		}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String getFileContent() throws IOException {
		/*File file=new File(directory+"/fake.news");
		BufferedReader in = new BufferedReader(new FileReader(file));
		return in.readLine();*/
		return "pippo";
	}

}
