package essap.sample.helloworld;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import essap.sample.lib.ServletBase;

public class HelloWorldServlet extends ServletBase {

	void doHelloWorld(Writer writer) throws IOException {
		writer.write("<html><head><title>Essap Sample Application</title></head><body>Hello world!</body></html>");
	}

	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		arg1.setContentType("text/html");
		//doHelloWorld(arg1.getWriter());
		dispatch("/jsp/helloworld/HelloWorld.jsp", arg0, arg1);
	}

	
}
