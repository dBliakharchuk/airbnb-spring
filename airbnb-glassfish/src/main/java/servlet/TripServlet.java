package servlet;

import java.io.IOException;
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
import logic.TripLogic;
import model.ApartmentPK;
import model.Trip;


@WebServlet(
		urlPatterns="/tripServlet",
		loadOnStartup=1,
		initParams={@WebInitParam(name="configuracion", value="es.uc3m.tiw")}
		)
public class TripServlet extends HttpServlet {
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
			
			String emailOfLoggedUser = (String) request.getSession().getAttribute("emailOfLoggedUser"); 
			if (emailOfLoggedUser != null) {
				List<Trip> tripsList = TripLogic.getUserTrips(DataAccess.getUserByEmail(emailOfLoggedUser));
				request.setAttribute("userTrips", tripsList);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/trips.jsp"); 
				dispatcher.forward(request, response); 
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp"); 
				dispatcher.forward(request, response); 
			}
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			request.getSession().setAttribute("selectedApartment", DataAccess.getApartmentById(new ApartmentPK(request.getParameter("apartmentHost"),
			request.getParameter("apartmentBuildingNumber"), request.getParameter("apartmentStreet"), request.getParameter("apartmentFlatNumber"), 
			request.getParameter("apartmentCity"))));
					
			RequestDispatcher dispatcher = request.getRequestDispatcher("/tripsMoreInf.jsp");
			dispatcher.forward(request, response); 
			
		}


}
