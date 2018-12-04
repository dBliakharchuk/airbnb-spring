package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import javax.servlet.http.HttpSession;

import database.DataAccess;
import logic.UserLogic;
import model.User;

@WebServlet(urlPatterns = { "/loginServlet", "/registrationServlet", "/logoutServlet",
		"/updateUserServlet" }, loadOnStartup = 1, initParams = {
				@WebInitParam(name = "configuracion", value = "es.uc3m.tiw") })
public class AccountServlet extends HttpServlet
{
	private static final String LOGIN_SERVLET = "/loginServlet";
	private static final String REGISTRATION_SERVLET = "/registrationServlet";
	private static final String LOGOUT_SERVLET = "/logoutServlet";
	private static final String ADMINISTRATOR_PATH = "/airbnb/administatorUsers";
	
	private static final String UPDATE_USER_PATH = "/updateUserServlet";  

	private static final String USER_PATH = "/index.jsp";

	private static final long serialVersionUID = 1L;
	private ServletConfig config;
	private static List<User> users = new ArrayList<User>();

	private String inputEmail = "";
	private String inputName = "";
	private String inputSurname = "";
	private String inputPassword = "";
	private String inputCheckbox = "";

	private String urlPath = "";
	private HttpSession session = null;
	private String msgBox = null;

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
		msgBox = null;
		session = request.getSession();
		session.invalidate();
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		User registeredUser = null;
		User loggedUser = null;
		msgBox = null;
		urlPath = (String) request.getServletPath();
		String emailOfLoggedUser = (String) request.getSession().getAttribute("emailOfLoggedUser"); 


		if (urlPath.equals(REGISTRATION_SERVLET))
		{
			inputEmail = request.getParameter("inputEmail");
			inputName = request.getParameter("inputName");
			inputSurname = request.getParameter("inputSurname");
			inputPassword = request.getParameter("inputPassword");

			registeredUser = new User(inputEmail, inputName, inputSurname, inputPassword, "000000000000");
			boolean isRegistered = UserLogic.registerUser(registeredUser);

			if (isRegistered)
			{
				msgBox = "User was created";
			} else
			{
				msgBox = "Incorect data or email adress already exist!!!";
			}
		} else if (urlPath.equals(LOGIN_SERVLET))
		{
			inputEmail = request.getParameter("inputEmail");
			inputPassword = request.getParameter("inputPassword");
			inputCheckbox = request.getParameter("inputCheckbox");

			loggedUser = DataAccess.getUserByEmail(inputEmail);
			if (loggedUser != null)
			{
				boolean correctPassword = loggedUser.getPassword().equals(inputPassword);

				if (correctPassword)
				{
					session = request.getSession();
					session.setAttribute("emailOfLoggedUser", inputEmail);
					msgBox = null;

					if (UserLogic.isAdmin(loggedUser))
					{
						response.sendRedirect(ADMINISTRATOR_PATH);
					} else
					{
						RequestDispatcher rd = request.getRequestDispatcher(USER_PATH);
						rd.forward(request, response);
					}
				} else
				{
					msgBox = "Password is wrong!!!";
				}
			} else
			{
				msgBox = "Email is wrong!!!";
			}
		} else if (urlPath.equals(UPDATE_USER_PATH))
		{

			PrintWriter writer = response.getWriter();
			String action = request.getParameter("action");

			if ("updateUser".equals(action))
			{
				int status = 0;
				
				String email = request.getParameter("email");
				String name = request.getParameter("name");
				String surname = request.getParameter("surname");
				String phone = request.getParameter("phoneNumber");
						
				User user = DataAccess.getUserByEmail(email);
				if(user != null)
				{
					user.setName(name);
					user.setSurname(surname);
					user.setPhone(phone);
					status =  UserLogic.modifyUser(user) ? 1 : 0;

				}
				
				if("admin".equals(emailOfLoggedUser))
				{
					writer.println(status);
				}else 
				{
				
					response.sendRedirect("manageProfile");
				}
		
				

			} else if ("changePassword".equals(action))
			{
				String password = request.getParameter("password");
				String email = request.getParameter("email");
				Boolean passwordUpdateStatus = UserLogic.changeUserPassword(email, password);
				
				int passwordUpdateStatusInt = passwordUpdateStatus ? 1 : 0;
				writer.println(passwordUpdateStatusInt);
				
				

			} else if ("deleteUser".equals(action))
			{
				String email = request.getParameter("email");
				Boolean userDeletedStatus = UserLogic.removeUser(email);
				
				int deleteUserStatusInt = userDeletedStatus ? 1 : 0;
				
				if("admin".equals(emailOfLoggedUser))
				{
					writer.println(deleteUserStatusInt);

				}else 
				{
					if(userDeletedStatus)
						response.sendRedirect("logoutServlet");
				}
				
			}
			
			
		} else
		{
			msgBox = "Wrong Servlet";
		}

		if (msgBox != null)
		{
			request.setAttribute("msgBox", msgBox);
			RequestDispatcher rd = request.getRequestDispatcher(USER_PATH);
			rd.forward(request, response);
		}
	}
}
