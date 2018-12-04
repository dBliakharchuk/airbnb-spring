package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class ReservationFilter
 */
@WebFilter("/reservations")
public class ReservationFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ReservationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpRequest = (HttpServletRequest) request;
    	HttpServletResponse httpResponse = (HttpServletResponse) response;
    	HttpSession session = httpRequest.getSession();
		
		if(httpRequest.getSession().getAttribute("selectedApartment")!=null){
		
        	if (httpRequest.getSession().getAttribute("emailOfLoggedUser")==null) {
			
        		String loginError = "You need to be logged in to make a reservation. Login and try again, or register in case you don't have an account yet.";
        	
        		httpRequest.setAttribute("loginError", loginError);
			
        		RequestDispatcher dispatcher = request.getRequestDispatcher("/accommodation.jsp");
			
        		dispatcher.forward(request, response);
        	} else {    
        	
        		chain.doFilter(request, response);
        	}
		}
		else {
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			
    		dispatcher.forward(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
