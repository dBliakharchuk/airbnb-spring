package servlet;

import java.awt.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DataAccess;
import logic.MessageLogic;
import model.Message;
import model.User;


@WebServlet(
		urlPatterns="/messages",
		loadOnStartup=1,
		initParams={@WebInitParam(name="configuracion", value="es.uc3m.tiw")}
		)
public class MessageServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ServletConfig config;
	
	
		@Override
		public void init(ServletConfig config) throws ServletException {
			this.config = config;

		}
	       


		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
			String email = (String)request.getSession().getAttribute("emailOfLoggedUser");
			if(email != null)
			{
				ArrayList<Message> messages=  new ArrayList<Message> (DataAccess.getNewestMessages(email));

				request.setAttribute("messages", messages);
				request.getRequestDispatcher("messages.jsp").forward(request, response);

			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp"); 
				dispatcher.forward(request, response); 
			}
			
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter writer = response.getWriter();
			
			String senderEmail = request.getParameter("senderEmail");
			String reciverEmail = request.getParameter("reciverEmail");
			String message = request.getParameter("message");
			
		    Date date = new Date();  
		    
		    User sender = DataAccess.getUserByEmail(senderEmail);
		    User reciver = DataAccess.getUserByEmail(reciverEmail);

		    Message messag = new Message(sender, reciver, date, message);
		    boolean  status =  MessageLogic.sendMessage(messag);
		    int statusInt = status ? 1 : 0;
			writer.println(statusInt);
			
			
//		    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
//			System.out.println(senderEmail + reciverEmail + message + formatter.format(date));
		    
		}
}
