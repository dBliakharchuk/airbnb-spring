package servlet;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DataAccess;
import model.Apartment;
import model.ApartmentPK;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet("/apartmentsImages/*")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String apartmentURL = request.getPathInfo().substring(1);
		 ApartmentPK apartmentPK = ApartmentPK.fromUlr(apartmentURL);
		 Apartment apartment = DataAccess.getApartmentById(apartmentPK);
		 
		 
		
		 byte[] content = apartment.getPicture();
		 if(content.length < 1)
		 {
			 	// ******** change this *********
			    File f = new File("/Dima/Desktop/airbnb-part2/airbnb-spring/airbnb-glassfish/src/main/webapp/images/defoult-pic.jpg");
			    DataInputStream dis = new DataInputStream(new FileInputStream(f));
			    content = new byte[(int) f.length()];
			    dis.readFully(content);
			    dis.close(); 
		 }
		 
		response.setContentType(getServletContext().getMimeType(""));
		response.setContentLength(content.length);
		response.getOutputStream().write(content);
		 
       
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
