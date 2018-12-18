	package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import logic.MessageLogic;
import model.Apartment;
import model.ApartmentPK;
import model.Message;
import model.Reservation;
import model.User;


@WebServlet(
		urlPatterns="/confirmation",
		loadOnStartup=1,
		initParams={@WebInitParam(name="configuracion", value="es.uc3m.tiw")}
		)
public class BookingConfirmationServlet extends HttpServlet {
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
			
				
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			
			dispatcher.forward(request, response);

		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String confirmationStatus = (String)request.getParameter("confirmationField");
			List<Reservation> bookingReservations = (List<Reservation>)config.getServletContext().getAttribute("reservationList");
			Apartment apartment = bookingReservations.get(0).getApartment();
			ApartmentPK apartmentPK = apartment.getId(); 
			User user = DataAccess.getUserByEmail((String)request.getSession().getAttribute("emailOfLoggedUser"));
			String textMessage ="";
			
			if(confirmationStatus.equals("ok")){
				
				for(Reservation r : bookingReservations) {
            		
            		r.setReservation("Accepted");
            		
            		DataAccess.updateReservation(r);
            	}
       		
        		textMessage ="Hello User!\nThe host " 
        				+ user.getName() + " " + user.getSurname() + " (" + user.getEmail() 
        				+") accepted your reservation of '" + apartment.getName() + "' (address: " 
        				+ apartmentPK.getStreet() + " " + apartmentPK.getBuildingNumber() 
        				+ "/" +apartmentPK.getFlatNumber() + ", " + apartmentPK.getCity() 
        				+", " + apartment.getCountry() +"), between " + request.getSession().getAttribute("dateStart") 
        				+ " and " + request.getSession().getAttribute("dateEnd") 
        				+ ". We wish you a pleasant stay!";
			}
			else {
				
				for(Reservation r : bookingReservations) {
            			
            		DataAccess.removeReservation(r);
				}
				
				textMessage ="Hello User!\nThe host " 
        				+ user.getName() + " " + user.getSurname() + " (" + user.getEmail() 
        				+") declined your reservation of '" + apartment.getName() + "' (address: " 
        				+ apartmentPK.getStreet() + " " + apartmentPK.getBuildingNumber() 
        				+ "/" +apartmentPK.getFlatNumber() + ", " + apartmentPK.getCity() 
        				+", " + apartment.getCountry() +"), between " + request.getSession().getAttribute("dateStart") 
        				+ " and " + request.getSession().getAttribute("dateEnd") 
        				+ ". Try to find different apartment from thousands available in our website!";
			}
				
			Message reservationMessage = Message.createNewMessage(DataAccess.getUserByEmail("admin"), user, textMessage);
    		MessageLogic.sendMessage(reservationMessage);
    		
			response.sendRedirect("/airbnb/messages");
		}
}
