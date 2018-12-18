package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DataAccess;
import database.HttpClientBank;
import logic.DateUtils;
import logic.MessageLogic;
import logic.PaymentUtils;
import model.Apartment;
import model.ApartmentPK;
import model.Message;
import model.PaymentInfo;
import model.Reservation;
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
			String expiration = request.getParameter("expiration-date");
			String cv2 = request.getParameter("cv2-code");
			double totalPrice = (double)request.getSession().getAttribute("totalPrice");
			
			PaymentInfo payment = null;
			
			Pattern numberPattern = Pattern.compile("\\D");
			Pattern expirationPattern = Pattern.compile("\\d{2}/\\d{4}");
			
            if(!numberPattern.matcher(cardNumber).find() && !numberPattern.matcher(cv2).find()
            	&& expirationPattern.matcher(expiration).find() && expiration.length() == 7) {
            	
            	Date tempDate = null;
        		
        		try {
        			tempDate = new Date(new SimpleDateFormat("MM/yyyy").parse(expiration).getTime());
        		} 
        		catch (ParseException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        		Date expirationDate = new Date(tempDate.getYear(), tempDate.getMonth() + 1, 1);
            	
            	payment = new PaymentInfo(cardNumber, cv2, expirationDate, totalPrice);
            }
            
            Apartment apartment = (Apartment)request.getSession().getAttribute("selectedApartment");
            
            List<Date> bookingDays = DateUtils.getDatesBetween((Date)request.getSession().getAttribute("dateStart"), 
            		(Date)request.getSession().getAttribute("dateEnd"));
            
            List<Reservation> bookingReservations = new ArrayList<Reservation>();
            Reservation reservation = null;
            User user = DataAccess.getUserByEmail((String)request.getSession().getAttribute("emailOfLoggedUser"));
            
            for(Date date : bookingDays) {
            	
            	reservation = new Reservation(user, apartment, date, "pending");
            	
            	bookingReservations.add(reservation);
            }       
            
            if(!HttpClientBank.isPaymentInfoValid(payment)) {

            		String dataError = "Provided data is incorrect. Provide valid payment data and try again.";
                	
            		request.setAttribute("dataError", dataError);
    			
            		RequestDispatcher dispatcher = request.getRequestDispatcher("/payment.jsp");
            		dispatcher.forward(request, response);
            	
            }
            else {
            	
            	ApartmentPK apartmentPK = apartment.getId();        		
        		String textMessage ="#" + bookingReservations.get(0).hashCode() + "\n\nHello Host!\nThe user " 
        				+ user.getName() + " " + user.getSurname() + " (" + user.getEmail() 
        				+") wants to make a reservation of '" + apartment.getName() + "' (address: " 
        				+ apartmentPK.getStreet() + " " + apartmentPK.getBuildingNumber() 
        				+ "/" +apartmentPK.getFlatNumber() + ", " + apartmentPK.getCity() 
        				+", " + apartment.getCountry() +"), between " + request.getSession().getAttribute("dateStart") 
        				+ " and " + request.getSession().getAttribute("dateEnd") 
        				+ ". Payment was confirmed with valid credit card. You have to either accept the reservation "
        				+ "request or decline.";
        		
        		Message reservationMessage = Message.createNewMessage(user, apartment.getHost(), textMessage);
        		MessageLogic.sendMessage(reservationMessage);
        		
            	
            	for(Reservation r : bookingReservations) {
            		
            		DataAccess.createReservation(r);
            		
            	}
            		config.getServletContext().setAttribute("reservationList", bookingReservations);
            		request.getSession().setAttribute("confirmationMessage", "Your booking request is pednding, " +
        				"wait for the confirmation message from the host");
            		            	
        		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
    			dispatcher.forward(request, response);
            }
		}
	}
