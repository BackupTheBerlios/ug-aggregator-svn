package essap.sample.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import essap.sample.lib.PersistenceException;
import essap.sample.lib.ServletBase;
import essap.sample.models.User;
import essap.sample.models.UserDao;

public class UserManagerServlet extends ServletBase {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dispatch("/jsp/usermanager/new.jsp", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User(request.getParameterMap());
		try {
			UserDao.save(user);
		} catch (PersistenceException e) {
			throw new ServletException(e);
		}
	}
	

}
