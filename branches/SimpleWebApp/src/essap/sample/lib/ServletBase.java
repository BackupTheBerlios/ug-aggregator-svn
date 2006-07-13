package essap.sample.lib;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ServletBase extends HttpServlet {

	public ServletBase() {
		super();
	}

	protected void dispatch(String url, HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(arg0, arg1);
	}

}