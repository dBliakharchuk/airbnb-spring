package servlet;

import java.io.IOException;
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

import logic.ApartmentLogic;
import model.Apartment;
import model.ApartmentType;


@WebServlet(
		urlPatterns="/results",
		loadOnStartup=1,
		initParams={@WebInitParam(name="configuracion", value="es.uc3m.tiw")}
		)
public class ResultServlet extends HttpServlet {
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
			
			if(request.getSession().getAttribute("resultApartments")==null) {
			
				dispatcher = request.getRequestDispatcher("/index.jsp");
			
				dispatcher.forward(request, response);
			}
			else {
				
				dispatcher = request.getRequestDispatcher("/results.jsp");
				
				dispatcher.forward(request, response);
			}
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String fromPlace = request.getParameter("from-place").replaceAll("\\s","").toLowerCase();
			
			String dateStartString = request.getParameter("date-start");
			String dateEndString = request.getParameter("date-end");
			Date dateStart = null;
			Date dateEnd = null;
			if(dateStartString.equals("")==false) {
				try {
					dateStart = new Date ((new SimpleDateFormat("MM/dd/yyyy")).parse(dateStartString).getTime());
				} 
				catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(dateEndString.equals("")==false) {
				try {
					dateEnd = new Date((new SimpleDateFormat("MM/dd/yyyy")).parse(dateEndString).getTime());
				} 
				catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if((dateStart == null || dateEnd == null) || dateStart.before(dateEnd)==false){
				
				dateStart = new Date();
				dateEnd = new Date(dateStart.getYear(), dateStart.getMonth(), dateStart.getDate() + 7);
			}
			
			SimpleDateFormat dateFormat = null;
			dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			request.getSession().setAttribute("dateStart", dateFormat.format(dateStart));
			request.getSession().setAttribute("dateEnd", dateFormat.format(dateEnd));
			
			String price = request.getParameter("price");
			
			ApartmentType typeOfAccom = null;
			
			if(request.getParameter("typeOfAccom")!=null){
				
				typeOfAccom = ApartmentType.fromString(request.getParameter("typeOfAccom"));
			}
			
			Integer adults = null;
			Integer children = null;
			
			if(request.getParameter("adults")!=null){
					
				adults = new Integer(request.getParameter("adults"));
			}
			if(request.getParameter("children")!=null){
				
				children = new Integer(request.getParameter("children"));
			}
			
			List<Apartment> resultApartmentsList = ApartmentLogic.search(fromPlace, price, typeOfAccom, adults, children, dateStart, dateEnd);
		
			request.getSession().setAttribute("resultApartments", resultApartmentsList);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/results.jsp");
			dispatcher.forward(request, response);
			
//			response.setContentType("text/html");
//			PrintWriter out = response.getWriter();
//			out.println("<html>");
//			out.println("<head>");
//			out.println("</head>");						
//			out.println("<body>");
//			out.println("<h1> today not true date  is:"+new Date(118,11,31)+"</h1>");
//			out.println("<h1> today not true date  is:"+tempDate.getMonth()+"</h1>");
//			out.println("<h1> temp date is:"+tempDate+"</h1>");
//			out.println("<h1> other temp date is:"+otherTempDate+"</h1>");
//			out.println("<h1> Your fromPlace  is:"+DateUtils.isExpirationDateCorrect("12/2018")+"</h1>");
//			out.println("</body>");				
//			out.println("</html>");		
//			out.flush();
//			out.close();
		}		
	}

