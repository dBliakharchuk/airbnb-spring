package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DataAccess;
import model.Apartment;
import model.User;

@WebServlet(urlPatterns = "/manageProfile", loadOnStartup = 1, initParams = {
		@WebInitParam(name = "configuracion", value = "es.uc3m.tiw") })
public class ManageProfileServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ServletConfig config;

	@Override
	public void init(ServletConfig config) throws ServletException
	{
		this.config = config;

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String email = (String)request.getSession().getAttribute("emailOfLoggedUser");
		if(email != null)
		{
			User user = DataAccess.getUserByEmail(email);
			request.setAttribute("user", user);
		
			ArrayList<Apartment> userApartments =  new ArrayList<Apartment>(DataAccess.getApartmentByHost(email));
			request.setAttribute("userApartments", userApartments);
		}
		
		request.getRequestDispatcher("manageProfile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	
		
	}

}