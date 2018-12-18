package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DataAccess;
import model.ApartmentPK;


@WebServlet(
		urlPatterns="/accommodations",
		loadOnStartup=1,
		initParams={@WebInitParam(name="configuracion", value="es.uc3m.tiw")}
		)
public class AccommodationServlet extends HttpServlet {
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
			
			RequestDispatcher dispatcher = null;
			
			if(request.getSession().getAttribute("selectedApartment")==null) {
				
				dispatcher = request.getRequestDispatcher("/index.jsp");
			
				dispatcher.forward(request, response);
			}
			else {
				
				dispatcher = request.getRequestDispatcher("/accommodation.jsp");
				
				dispatcher.forward(request, response);
			}

		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			if(request.getParameter("apartmentBuildingNumber") != null) {	
				request.getSession().setAttribute("selectedApartment", DataAccess.getApartmentById(new ApartmentPK(request.getParameter("apartmentHost"),
						request.getParameter("apartmentBuildingNumber"), request.getParameter("apartmentStreet"), request.getParameter("apartmentFlatNumber"), 
						request.getParameter("apartmentCity"))));
			}
					
			RequestDispatcher dispatcher = request.getRequestDispatcher("/accommodation.jsp");
			
			dispatcher.forward(request, response);
			
		}


}

