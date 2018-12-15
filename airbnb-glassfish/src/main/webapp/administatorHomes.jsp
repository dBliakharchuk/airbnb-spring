
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->




<%@ page import="model.*"%>
<%@ page import=" java.util.*"%>



<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>TIWbnb</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Free HTML5 Template by FREEHTML5.CO" />
<meta name="keywords"
	content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive" />
<meta name="author" content="FREEHTML5.CO" />

<!-- Facebook and Twitter integration -->
<meta property="og:title" content="" />
<meta property="og:image" content="" />
<meta property="og:url" content="" />
<meta property="og:site-name" content="" />
<meta property="og:description" content="" />
<meta name="twitter:title" content="" />
<meta name="twitter:image" content="" />
<meta name="twitter:url" content="" />
<meta name="twitter:card" content="" />

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
<link rel="shortcut icon" href="favicon.ico">

<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,300'
	rel='stylesheet' type='text/css'>

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
<link rel="stylesheet" href="css/administrator.css">


<!-- Modernizr JS -->
<script src="js/modernizr-2.6.2.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
<script type="text/javascript" src="js/administrator.js"></script>

<!-- FOR IE9 below -->
<!--[if lt IE 9]>
		<script src="js/respond.min.js"></script>
		<![endif]-->

</head>
<body>
</head>
<body>
	<div id="fh5co-wrapper">
		<div id="fh5co-page">
			
			<%
			String emailOfLoggedUser = (String) request.getSession().getAttribute("emailOfLoggedUser"); 		
			if (emailOfLoggedUser != null && emailOfLoggedUser.equals("admin")) {  %>
				<jsp:include page="headerAdmin.jsp"/>
			<% } else { 
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp"); 
				dispatcher.forward(request, response); 
			}%>
			<script type="text/javascript">
				document.getElementById("tab-admin-homes").classList.add("active");
			</script>

			<div class="fh5co-hero">
				<div class="fh5co-overlay"></div>
				<div class="fh5co-cover" data-stellar-background-ratio="0.5"
					style="background-image: url(images/cover_bg_5.jpg);">
					<div class="desc">
						<div class="container" id="places-panel">
							<div id="left-panel" class="panel-containter">
								<div class="place-cel">
									<div class="place-email-field">Host Email</div>
									<div class="place-name-field">Apartment Name</div>


								</div>
								<%
									ArrayList<Apartment> apartments = (ArrayList<Apartment>) request.getAttribute("apartments");
									String cellId = "place-cel-";
									if(apartments != null)
									{	
										for (int i = 0; i < apartments.size(); i++)
										{
									%>
										<div class="place-cel" id=<%=cellId + i%>
											onclick="selectApartmentCell(this.id, <%=apartments.size()%>)">
											<div class="place-email-field">
												<!-- attribute host in the apartment = null -->
												<%-- <%=apartments.get(i).getHost().getEmail()%> --%>
												<%=apartments.get(i).getId().getHost()%>
											</div>
											<div class="place-name-field">
												<%=apartments.get(i).getName()%>
											</div>
											<div class="place-country-field">
												<%=apartments.get(i).getCountry()%>
											</div>
											<div class="place-price-field">
												<%=apartments.get(i).getPrice()%>
											</div>
											<div class="place-type-field">
												<%=apartments.get(i).getType()%>
											</div>
											<div class="place-adults-beds-field">
												<%=apartments.get(i).getBedsAdult()%>
											</div>
											<div class="place-children-beds-field">
												<%=apartments.get(i).getBedsChild()%>
											</div>
											<div class="place-description-field">
												<%=apartments.get(i).getDescription()%>
											</div>
											<div class="place-building-number-field">
												<%=apartments.get(i).getId().getBuildingNumber()%>
											</div>
											<div class="place-street-field">
												<%=apartments.get(i).getId().getStreet()%>
											</div>
											<div class="place-flat-number-field">
												<%=apartments.get(i).getId().getFlatNumber()%>
											</div>
											<div class="place-city-field">
												<%=apartments.get(i).getId().getCity()%>
											</div>		
										</div>
									<%
										}
									}
									%>


							</div>
							<div id="midle-panel" class="panel-containter">
								<div class="eidit-apartment-form">
									<label for="name">Apartment Name</label> 
									<input type="text" name="name" id="apartment-name" value=""> 
									<label for="user-email">Host Email</label> 
									<input type="email"  name="user-email" id="user-email" value="" readonly>
									<input type="hidden" name="country" id="apartment-country" value=""> 
									 <label for="price-per-day">Price Per Day</label>
									 <input type="number" name="price-per-day" id="apartment-price" value="">
									 <label for="type-of-apartment">Type Of Apartment</label> 
									 <input type="radio" id="type-entire" name="type" value="ENTIRE"  required>Entire Apartment<br>
									 <input type="radio" id="type-private" name="type" value="PRIVATE">Private Room<br>
									 <input type="radio" id="type-shared" name="type" value="SHARED"> Shared Room 
									 <label for="beds-adult">Adults Beds</label> 
									 <input type="number" name="beds-adult" id="apartment-adults-beds" value=""> 
									 <label for="beds-child">Children Beds</label> 
									 <input type="number" name="beds-child" id="apartment-child-beds"value="">
									 <label for="description">Description</label>

									<textarea id="place-description" name="description"> </textarea>
									<button type="button" class="btn btn-success"
										id="save-place-button" onclick="updateApartmentData()">Save</button>
								</div>
							</div>


							<div id="right-panel" class="panel-containter">

								<button type="button" class="btn btn-success"
									onclick="messageUser()">Message Host</button>
								<button type="button" class="btn btn-danger"
									onclick="deleteApartment()">Delete Apartment</button>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>



</body>
</html>
