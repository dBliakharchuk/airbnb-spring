
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
	<%@ page import="model.*" %>
	
	
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
	<link rel="stylesheet" href="css/manageProfile.css">


	<!-- Modernizr JS -->
	<script src="js/modernizr-2.6.2.min.js"></script>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
	<script type="text/javascript" src="js/manageProfile.js"></script>
	
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->

	</head>
	<body>
		<div id="fh5co-wrapper">
		<div id="fh5co-page">

		<!-- start:header-top -->
			<%
			String emailOfLoggedUser = (String) request.getSession().getAttribute("emailOfLoggedUser"); 		
			if (emailOfLoggedUser != null) { %>
			<jsp:include page="headerLogin.jsp"/> 
			<% } else { 
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp"); 
				dispatcher.forward(request, response); 
			}%>
			<script type="text/javascript">
				document.getElementById("tab-profile").classList.add("active");
			</script>

			<!-- end:header-top -->

		<!-- end:header-top -->

		<div id="fh5co-tours" class="fh5co-section-gray">
			<div class="container" id="add-new-house-container">
				<h1>Edit Your Apartment</h1>
				<form class="row-bottom-padded-md animate-box" id="add-new-house-form" action="ApartmentServlet"  method="POST" >
				<% Apartment apartment = (Apartment)request.getSession().getAttribute("selectedApartment"); 
					if(apartment != null)
					{%>
					<h2>Address</h2>
					<label for="country">Country</label>
					<input type="text" name="country" value="<%=apartment.getCountry() %>" required readonly>
					<label for="city">City</label>
					<input type="text" name="city" id="city" value="<%=apartment.getCity() %>" required readonly>
					<label for="street">Street</label>
					<input type="text" name="street" id="street" value="<%=apartment.getStreet() %>" required readonly>
					<label for="building_number" >Building Number</label>
					<input type="text" name="building_number" id="building-nr" value="<%=apartment.getBuildingNumber() %>" required readonly>
					<label for="flat_number">FlatNumber</label>
					<input type="text" name="flat_number" id="flat-nr" value="<%=apartment.getFlatNumber() %>" required readonly>
					
					<h2 id="apartament-info-heding">Apartment Informations</h2>
					<label for="placeName">Apartment Name</label>
					<input type="text" name="placeName" value="<%=apartment.getName() %>" required >
	
					<label for="price">Price Per Day</label>
					<input type="number" name="price" value="<%=apartment.getPrice() %>" required>
					<label for="type">Type Of Apartment - <%=apartment.getType().toString() %></label> 
					<input type="radio" name="type" value="ENTIRE"  required>Entire Apartment<br>
					<input type="radio" name="type" value="PRIVATE">Private Room<br>
					<input type="radio" name="type" value="SHARED"> Shared Room
					
					
				
					<label for="adults_beds">Adults Beds</label>
					<input type="number" name="adults_beds" value="<%=apartment.getBedsAdult() %>" required>
					<label for="childeren_beds">Children Beds</label>
					<input type="number" name="childeren_beds" value="<%=apartment.getBedsChild() %>" required>
					<label for="description">Description</label>
					<textarea id="place-description" name="description" required><%=apartment.getDescription() %></textarea>
					
					<input type="hidden" name="action" value="updateApartment">
					<input type="hidden" name="email" id="email" value="<%= emailOfLoggedUser %>" readonly required>
					
					
					<input type="submit" class="btn btn-success" id="save-home-button" value = "Save">
					<button type="button" class="btn btn-warning" id="cancel-home-button" onclick="window.location.href = 'manageProfile'">Cancel</button>
					<button type="button" class="btn btn-danger" id="delete-apartment-button" onclick="deleteApartment()">Delete Apartment</button>
				<% } %>
				</form>

			</div>
		</div>

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

    </script>

	</body>
</html>
