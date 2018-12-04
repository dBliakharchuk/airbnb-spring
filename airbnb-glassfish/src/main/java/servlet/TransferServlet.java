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
import logic.PaymentUtils;
import model.Apartment;
import model.ApartmentPK;
import model.Message;
import model.User;


@WebServlet(
		urlPatterns="/transfers",
		loadOnStartup=1,
		initParams={@WebInitParam(name="configuracion", value="es.uc3m.tiw")}
		)
public class TransferServlet extends HttpServlet {
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
			
			String cardNumber = request.getParameter("card-number");
			String expirationDate = request.getParameter("expiration-date");
			String cv2 = request.getParameter("cv2-code");
			
			if (PaymentUtils.isDataCorrect(16, cardNumber, 3, cv2, expirationDate)==false) {
				
        		String dataError = "Provided data is incorrect. Provide valid payment data and try again.";
        	
        		request.setAttribute("dataError", dataError);
			
        		RequestDispatcher dispatcher = request.getRequestDispatcher("/payment.jsp");
        		dispatcher.forward(request, response);
        	} else {    
        		Apartment apartment = (Apartment)request.getSession().getAttribute("selectedApartment");
        		ApartmentPK apartmentPK = apartment.getId();
        		User user = DataAccess.getUserByEmail(request.getSession().getAttribute("emailOfLoggedUser").toString());
        		String textMessage = "Hello Admin!\nThe user " + user.getName() + " " + user.getSurname() + " (" + user.getEmail() +") wants to make a reservation of '"
        				+ apartment.getName() + "' (address: " +apartmentPK.getStreet() + " " + apartmentPK.getBuildingNumber() + "/" +apartmentPK.getFlatNumber() 
						+ ", " + apartmentPK.getCity() +", " + apartment.getCountry() +"), between " + request.getSession().getAttribute("dateStart") + " and " 
        				+ request.getSession().getAttribute("dateEnd") + ". Payment was confirmed with valid credit card. You have to either accept the reservation "
        				+ "request or decline.";
        		
        		Message reservationMessage = Message.createNewMessage(user, DataAccess.getUserByEmail("admin"), textMessage);
        		
        		DataAccess.createMessage(reservationMessage);
        	}
		}
}
