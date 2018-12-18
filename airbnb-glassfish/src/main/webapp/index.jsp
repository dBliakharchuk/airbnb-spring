<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
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


	<!-- Modernizr JS -->
	<script src="js/modernizr-2.6.2.min.js"></script>
	<script type="text/javascript" src="js/accountJS.js"></script>
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->
	<%@ page import="model.*"%>
	</head>
	<body>
		<div id="fh5co-wrapper">
		<div id="fh5co-page">

			<% String emailOfLoggedUser = (String) request.getSession().getAttribute("emailOfLoggedUser"); 
				if (emailOfLoggedUser != null) {  %>
					<jsp:include page="headerLogin.jsp"/>
				<% } else { %>  
					<jsp:include page="headerLogout.jsp"/>
				<% } %>
		<script type="text/javascript">
			document.getElementById("tab-index").classList.add("active");
		</script>
		<!-- end:header-top -->
	
		<!-- Test -->
		<% 
		String msgBox = (String) request.getAttribute("msgBox");
		if (msgBox != null) { %>
		<script type="text/javascript">
			alert("<%=msgBox.toString()%>")
		</script>
		<% } %>
		
		<%String dataError = (String)request.getAttribute("");
			
					if(dataError!=null){ %>
				
						<script type="text/javascript">
							alert("<%= dataError%>")
						</script>
					<% } %>
		<!-- endTest -->
		<div class="fh5co-hero">
			<div class="fh5co-overlay"></div>
			<div class="fh5co-cover" data-stellar-background-ratio="0.5" style="background-image: url(images/cover_bg_5.jpg);">
				<div class="desc">
					<div class="container">
						<div class="row">
							<div class="col-sm-5 col-md-5">
								<!-- <a href="index.jsp" id="main-logo">Travel</a> -->
								<div class="tabulation animate-box">

								  <!-- Nav tabs -->
								   <ul class="nav nav-tabs" role="tablist">
								      <li role="presentation" class="active">
								    	   <a href="#alojamientos" aria-controls="alojamientos" role="tab" data-toggle="tab">Accommodations</a>
								      </li>
								   </ul>

								   <!-- Tab panes -->
									<div class="tab-content">
									 <div role="tabpanel" class="tab-pane active" id="hotels">
									 	<div class="row">
									 	<form action="results" method="post">
											<div class="col-xxs-12 col-xs-12 mt">
												<div class="input-field">
													<label for="from">City:</label>
													<input type="text" class="form-control" id="from-place" name="from-place" placeholder="Where do you want to go?"/>
												</div>
											</div>
											<div class="col-xxs-12 col-xs-6 mt alternate">
												<div class="input-field">
													<label for="date-start">Start:</label>
													<input type="text" class="form-control" id="date-start" name="date-start" placeholder="mm/dd/yyyy"/>
												</div>
											</div>
											<div class="col-xxs-12 col-xs-6 mt alternate">
												<div class="input-field">
													<label for="date-end">End:</label>
													<input type="text" class="form-control" id="date-end" name="date-end" placeholder="mm/dd/yyyy"/>
												</div>
											</div>
                                            
											<div class="col-sm-12 mt">
												<section>
													<label for="class">Price</label>
													<select class="cs-select cs-skin-border" id="price" name="price">
														<option value="" disabled selected>Select price range...</option>
														<option value="P1">Less than 35€</option>            
														<option value="P2">36€ - 69€</option>
														<option value="P3">70€ - 130€</option>
														<option value="P4">131€ or more</option>
													</select>
												</section>
											</div>
                                            
        									<div class="col-sm-12 mt">
												<section>
													<label for="class">Type of accommodation</label>
													<select class="cs-select cs-skin-border" id="typeOfAccom" name="typeOfAccom">
														<option value="" disabled selected>Select accommodation type...</option>
														<option value="ENTIRE_APARTMENT">Entire accommodation</option>
														<option value="PRIVATE_ROOM">Private room</option>
														<option value="SHARED_ROOM">Shared room</option>
													</select>
												</section>
											</div>
                                            
											<div class="col-xxs-12 col-xs-6 mt">
												<section>
													<label for="class">Adults:</label>
													<select class="cs-select cs-skin-border" id="adults" name="adults">
														<option value="" disabled selected>0</option>
														<option value="1">1</option>
														<option value="2">2</option>
														<option value="3">3</option>
														<option value="4">4</option>
													</select>
												</section>
											</div>
											<div class="col-xxs-12 col-xs-6 mt">
												<section>
													<label for="class">Children:</label>
													<select class="cs-select cs-skin-border" id="children" name="children">
														<option value="" disabled selected>0</option>
														<option value="1">1</option>
														<option value="2">2</option>
														<option value="3">3</option>
														<option value="4">4</option>
													</select>
												</section>
											</div>
											<div class="col-xs-12">
												<input type="submit" class="btn btn-primary btn-block" value="Search">
											</div>
											</form>
                                        </div>
				                    </div>
								 </div>


								</div>
							</div>
							<div class="desc2 animate-box">
								<div class="col-sm-7 col-sm-push-1 col-md-7 col-md-push-1">
									<h3>TIWbnb</h3>
									<h2>Plan your stay</h2>
									<h3>Choose from thousands of available accommodations</h3>
									<p>From <span class="price">30€</span> per night</p>
									<!-- <p><a class="btn btn-primary btn-lg" href="#">Get Started</a></p> -->
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
		
    </script>
        
	</body>
</html>

