package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import database.DataAccess;
import logic.ApartmentLogic;
import model.Apartment;
import model.ApartmentPK;
import model.ApartmentType;
import model.User;

/**
 * Servlet implementation class ApartmentServlet
 */
@WebServlet("/ApartmentServlet")
@MultipartConfig
public class ApartmentServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApartmentServlet()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("PostApatrment");
		PrintWriter writer = response.getWriter();
		String action = request.getParameter("action");
		String emailOfLoggedUser = (String) request.getSession().getAttribute("emailOfLoggedUser");

		if ("updateApartment".equals(action))
		{
			String email = request.getParameter("email");
			String placeName = request.getParameter("placeName");
			String cuntry = request.getParameter("cuntry");
			double price = Double.parseDouble(request.getParameter("price"));
			String type = request.getParameter("type");
			int adults_beds = Integer.parseInt(request.getParameter("adults_beds"));
			int childeren_beds = Integer.parseInt(request.getParameter("childeren_beds"));
			String description = request.getParameter("description");

			String buildingNumber = request.getParameter("building_number");
			String street = request.getParameter("street");
			String flatNumber = request.getParameter("flat_number");
			String city = request.getParameter("city");

			ApartmentPK apartmentPK = new ApartmentPK(email, buildingNumber, street, flatNumber, city);
			Apartment apartment = DataAccess.getApartmentById(apartmentPK);

			apartment.setName(placeName);
			apartment.setPrice(price);
			apartment.setType(type);
			apartment.setBedsAdult(adults_beds);
			apartment.setBedsChild(childeren_beds);
			apartment.setDescription(description);

			Boolean apartmentUpdatedStatus = ApartmentLogic.modifyApartment(apartment);

			if (!("admin".equals(emailOfLoggedUser)))
			{
				if (apartmentUpdatedStatus)
				{
					response.sendRedirect("manageProfile");
				}
			} else
			{
				int apartmentUpdateStatusInt = apartmentUpdatedStatus ? 1 : 0;
				writer.println(apartmentUpdateStatusInt);
			}

			System.out.println(email + placeName + cuntry + type + Double.toString(price)
					+ Integer.toString(childeren_beds) + Integer.toString(adults_beds) + description + buildingNumber
					+ street + flatNumber + city);
		}
		if ("addApartment".equals(action))
		{
			String email = request.getParameter("email");
			String placeName = request.getParameter("placeName");
			String cuntry = request.getParameter("country");
			double price = Double.parseDouble(request.getParameter("price"));
			String type = request.getParameter("type");
			int adults_beds = Integer.parseInt(request.getParameter("adults_beds"));
			int childeren_beds = Integer.parseInt(request.getParameter("childeren_beds"));
			String description = request.getParameter("description");

			String buildingNumber = request.getParameter("building_number");
			String street = request.getParameter("street");
			String flatNumber = request.getParameter("flat_number");
			String city = request.getParameter("city");

			Part filePart = request.getPart("apartment-picture"); // Retrieves <input type="file" name="file">
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
			InputStream fileContent = filePart.getInputStream();
			byte[] picture = IOUtils.toByteArray(fileContent);

			ApartmentPK apartmentPK = new ApartmentPK(email, buildingNumber, street, flatNumber, city);
			User host = DataAccess.getUserByEmail(email);

			
			Apartment newApartment = new Apartment(host, buildingNumber, street, flatNumber, city, adults_beds,
					childeren_beds, cuntry, description, placeName, picture, price, ApartmentType.fromString(type));
			Boolean apartmentAddedStatus = ApartmentLogic.addApartment(newApartment);
			if (apartmentAddedStatus)
			{
				response.sendRedirect("manageProfile");

			}

		} else if ("deletePlace".equals(action))
		{
			
			System.out.println("deletePlace");


			String email = request.getParameter("email");
			String buildingNumber = request.getParameter("building_number");
			String street = request.getParameter("street");
			String flatNumber = request.getParameter("flat_number");
			String city = request.getParameter("city");
			
			System.out.println(email + buildingNumber + street + flatNumber + city);


			ApartmentPK apartmentPK = new ApartmentPK(email, buildingNumber, street, flatNumber, city);
			Boolean apartmentDeletedtatus = ApartmentLogic.removeApartment(apartmentPK);

			//System.out.println(email + buildingNumber + street + flatNumber + city + apartmentDeletedtatus);

			
			int apartmentDeletedtatusInt = apartmentDeletedtatus ? 1 : 0;
			writer.println(apartmentDeletedtatusInt);
			

		}

	}

}
