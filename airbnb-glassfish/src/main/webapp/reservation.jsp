<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->

<%@ page import="model.*" %>
<%@ page import=" java.util.*"%>
<%@ page import="logic.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DecimalFormat" %>

	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>TIWbnb</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Free HTML5 Template by FREEHTML5.CO" />
	<meta name="keywords" content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive" />
	<meta name="author" content="FREEHTML5.CO" />

  	<!-- Facebook and Twitter integration -->
	<meta property="og:title" content=""/>
	<meta property="og:image" content=""/>
	<meta property="og:url" content=""/>
	<meta property="og:site_name" content=""/>
	<meta property="og:description" content=""/>
	<meta name="twitter:title" content="" />
	<meta name="twitter:image" content="" />
	<meta name="twitter:url" content="" />
	<meta name="twitter:card" content="" />

	<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
	<link rel="shortcut icon" href="favicon.ico">

	<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,300' rel='stylesheet' type='text/css'>
	
	<!-- Animate.css -->
	<link rel="stylesheet" href="css/animate.css">
	<!-- Icomoon Icon Fonts-->
	<link rel="stylesheet" href="css/icomoon.css">
	<!-- Bootstrap  -->
	<link rel="stylesheet" href="css/bootstrap.css">
	<!-- Superfish -->
	<link rel="stylesheet" href="css/superfish.css">
	<!-- Magnific Popup -->
	<link rel="stylesheet" href="css/magnific-popup.css">
	<!-- Date Picker -->
	<link rel="stylesheet" href="css/bootstrap-datepicker.min.css">
	<!-- CS Select -->
	<link rel="stylesheet" href="css/cs-select.css">
	<link rel="stylesheet" href="css/cs-skin-border.css">
	
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/reservation.css" type="text/css"/>


	<!-- Modernizr JS -->
	<script src="js/modernizr-2.6.2.min.js"></script>
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->

	</head>
	<body  onload="setInitialData()">
		<div id="fh5co-wrapper">
		<div id="fh5co-page">

				<!-- start:header-top -->
			<%
			String emailOfLoggedUser = (String) request.getSession().getAttribute("emailOfLoggedUser"); 		
			if (emailOfLoggedUser != null) { 
				if (!emailOfLoggedUser.equals("admin")) {%>
				<jsp:include page="headerLogin.jsp"/> 
				<%} else { %>
				<jsp:include page="headerAdmin.jsp"/>
			<% } 
			} else {  %>
				<jsp:include page="headerLogout.jsp"/>
			<% } %>
			<script type="text/javascript">
				document.getElementById("tab-index").classList.add("active");
			</script>

			<!-- end:header-top -->
		<% 	Apartment apartment = (Apartment)request.getSession().getAttribute("selectedApartment"); 
			
			String dateStart = request.getSession().getAttribute("dateStart").toString();
			String dateEnd = request.getSession().getAttribute("dateEnd").toString();	
			
			DecimalFormat df = new DecimalFormat("0.00");%>
			
		 <div class="fh5co-hero">
			<div class="fh5co-overlay"></div>
			<div class="fh5co-cover" data-stellar-background-ratio="0.5" style="background-image: url(images/cover_bg_5.jpg);">
				<div class="desc">
					<div class="container">
							<div id="reservation-container">
								<div>
									<span id="reservation-header">Reservation</span>
								</div>
								<div id="reservation-box">
								<div class="reservation-info-field">
									<span class="reservation-field-title">Apartment:</span>
									<%= apartment.getName() %> 
								</div>
								<div class="reservation-info-field">
									<span class="reservation-field-title">Address:</span>
									<%= apartment.getId().getStreet() + " " + apartment.getId().getBuildingNumber() + "/" +apartment.getId().getFlatNumber() 
									+ ", " + apartment.getId().getCity() +", " + apartment.getCountry()%>
								</div>
								<div class="reservation-info-field">
									<span class="reservation-field-title">User:</span>
									<%= request.getSession().getAttribute("emailOfLoggedUser") %>
								</div>
								
								<% 		Date minStartDate = new Date();
										Date minEndDate = new Date(minStartDate.getYear(), minStartDate.getMonth(), minStartDate.getDate() + 1);
										
										SimpleDateFormat dateFormat = null;
										dateFormat = new SimpleDateFormat("yyyy-MM-dd");	
										%>
								
									<form action="payments" method="post">
									<div class="date-box">
										<div class="input-field">
											<span class="reservation-field-title">Start:</span>
											<input type="date" class="form-control" id="date-start-reservation" name="date-start-reservation" value="<%= dateStart %>" required
											min="<%= dateFormat.format(minStartDate) %>" onchange="isDateInputCorrect(this)"/>
										</div>
									</div>
									<div class="date-box">
										<div class="input-field">
											<span class="reservation-field-title">End:</span>
											<input type="date" class="form-control" id="date-end-reservation" name="date-end-reservation" value="<%= dateEnd %>" required
											min="<%= dateFormat.format(minEndDate) %>" onchange="isDateInputCorrect(this)"/>
										</div>
									</div>
										<div id="reservation-price" >
											<span>Price:</span>
											<span id="priceToDisplay"><%= String.format("%.2f", ApartmentLogic.countTotalPrice(dateStart, dateEnd, apartment)).replaceAll(",",".") %> </span>€
										</div>
									<div style="clear:both;"></div>
									<br/>
										<input name="apartmentPrice" id="apartmentPrice" type="text" value="<%= apartment.getPrice() %>" hidden>
										<input name="totalPrice" id="totalPrice" type="text" hidden>
                            			<input type="submit" class="btn btn-primary reservation-button" value="Pay">
                        			</form>
									<form action="accommodations" method="post">
										<input type="submit" class="btn btn-primary reservation-button" value="Cancel">
									</form>
								</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
		<!-- Login Modal -->
		<jsp:include page="loginWindow.jsp"></jsp:include>     
            
		<!-- Registro Modal -->
		<jsp:include page="registrationWindow.jsp"></jsp:include>
  
     	<jsp:include page="footer.jsp"/>

	</div>
	<!-- END fh5co-page -->

	</div>
	<!-- END fh5co-wrapper -->

                
	<!-- jQuery -->

	<script src="js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script src="js/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Waypoints -->
	<script src="js/jquery.waypoints.min.js"></script>
	<script src="js/sticky.js"></script>

	<!-- Stellar -->
	<script src="js/jquery.stellar.min.js"></script>
	<!-- Superfish -->
	<script src="js/hoverIntent.js"></script>
	<script src="js/superfish.js"></script>
	<!-- Magnific Popup -->
	<script src="js/jquery.magnific-popup.min.js"></script>
	<script src="js/magnific-popup-options.js"></script>
	<!-- Date Picker -->
	<script src="js/bootstrap-datepicker.min.js"></script>
	<!-- CS Select -->
	<script src="js/classie.js"></script>
	<script src="js/selectFx.js"></script>
	
	<!-- Main JS -->
	<script src="js/main.js"></script>
	<script>
		
		var actualDateStart = null;
		var actualDateEnd = null;
          $(document).on('click', '#Login', function () {
              $("#loginModal").modal("show");
           });
          $(document).on('click', '#Registro', function () {
              $("#RegistroModal").modal("show");
           });
        
        $(document).on('click', '#goRegistroLogin', function () {
              $("#RegistroModal").modal("hide");
              $("#loginModal").modal("show");              
        });
		function setInitialData(){
        	
        	actualDateStart = document.getElementById("date-start-reservation").value;
        	actualDateEnd = document.getElementById("date-end-reservation").value;
        	document.getElementById("totalPrice").value = document.getElementById("priceToDisplay").textContent;
        }
        function isDateInputCorrect(){
        	
        	var newDateStart = document.getElementById("date-start-reservation").value;
        	var newDateEnd = document.getElementById("date-end-reservation").value;
        	
        	if(dateCompare(newDateStart, newDateEnd)==false){
        		
        		document.getElementById("date-start-reservation").value = actualDateStart;
        		document.getElementById("date-end-reservation").value = actualDateEnd;
        		
        		alert("Incorrect dates!")
        	}
        	else{
        		
        		actualDateStart = newDateStart;
        		actualDateEnd = newDateEnd;
        		
        		var pricePerNight = document.getElementById("apartmentPrice").value;
        		var totalCost = daysBetweenTwoDates(actualDateStart, actualDateEnd) * pricePerNight;
        		document.getElementById("totalPrice").value = totalCost;
        		document.getElementById("priceToDisplay").textContent = parseFloat(Math.round(totalCost * 100) / 100).toFixed(2);
        		
        	}
        }
        function dateCompare(dateStart, dateEnd){
            return new Date(dateEnd) > new Date(dateStart);
        }
        function daysBetweenTwoDates(dateStartString, dateEndString){
        	
        	var oneDay = 24*60*60*1000; // hours*minutes*seconds*milliseconds
        	var dateStart = new Date(dateStartString);
        	var dateEnd = new Date(dateEndString);

        	var diffDays = Math.round(Math.abs((dateStart.getTime() - dateEnd.getTime())/(oneDay)));
        	
        	return diffDays;
        }
		
        </script>
        
	</body>
</html>
