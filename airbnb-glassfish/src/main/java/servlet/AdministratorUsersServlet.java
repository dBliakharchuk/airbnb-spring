package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DataAccess;
import model.User;

@WebServlet(urlPatterns = "/administatorUsers", loadOnStartup = 1, initParams = {
		@WebInitParam(name = "configuracion", value = "es.uc3m.tiw") })
public class AdministratorUsersServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ServletConfig config;
	private List<User> users;

	private Boolean userUpdated = false;
	
		@Override
		public void init(ServletConfig config) throws ServletException {
			this.config = config;

		}
	       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String emailOfLoggedUser = (String) request.getSession().getAttribute("emailOfLoggedUser"); 
		if (emailOfLoggedUser != null && emailOfLoggedUser.equals("admin")) {	
					users = new ArrayList<User>(DataAccess.getAllUsers());
					request.setAttribute("users", users);
					request.getRequestDispatcher("administatorUsers.jsp").forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp"); 
			dispatcher.forward(request, response); 
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		

		


	}

}