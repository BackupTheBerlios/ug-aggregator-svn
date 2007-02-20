package it.xpug.aggregator;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertNewsServlet extends HttpServlet{

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException 
	{
		File tempFile = File.createTempFile("xpug", "");
		//this.getServletContext().getContext("newsDir");
		
		String n = req.getParameter("title")+"\n";
		n += req.getParameter("content")+"\n";
		n += req.getParameter("expirationDate")+" 221010\n";
		n += "20061011";
		
		News news = new News(n, "XPUGMI");		
		
		System.setProperty("it.xpug.aggregator.newsDir", tempFile.getPath());
		
		NewsFileWriter fw = new NewsFileWriter(news);
		fw.write();
		
		
		// Hack: waiting for the other guys to finish their cool jsp
		writeResponse(res, tempFile);
	}

	private void writeResponse(HttpServletResponse res, File tempFile) throws IOException {
		FileFinder fileFinder = new FileFinder(tempFile.getPath());
		String[] fileContents = fileFinder.listFilesContent();
		PrintWriter writer = res.getWriter();
		for (int i = 0; i < fileContents.length; i++) {
			writer.append(fileContents[i]);
		}
		writer.flush();
	}
	
}
