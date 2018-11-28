
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->


<!-- import -->

<%@ page import="model.*" %>
<%@ page import=" java.util.*"%>



<head>
	<meta charset="UTF-8">
	<title>TIWbnb Administator</title>
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
				document.getElementById("tab-admin-users").classList.add("active");
			</script>
			<!-- Test -->
			<% String msgBox = (String) request.getAttribute("msgBox");
				if (msgBox != null) { %>
				<script type="text/javascript">
					alert("<%=msgBox.toString()%>")
				</script>
			<% } %>
			<!-- endTest -->
			
			<div class="fh5co-hero">
				<div class="fh5co-overlay"></div>
				<div class="fh5co-cover" data-stellar-background-ratio="0.5" style="background-image: url(images/cover_bg_5.jpg);">
					<div class="desc">
		
						 <div class="container" id="users-panel">
						 	<div id="left-panel" class="panel-containter">
								<div class="user-cel">
									<div class="user-email-field">
											Email
									</div>
									<div class="user-name-field">
											Name
									</div>
									<div class="user-surname-field">
											Surname
									</div>
									<div class="user-phone-field">
											Phone
									</div>
								</div>
								
								
							

								<%

									ArrayList<User> users = (ArrayList<User>)request.getAttribute("users");
									String cellId = "user-cel-";
									if(users != null)
									{
										for (int i = 0; i < users.size(); i++)
										{  %>
											<div class="user-cel"  id=<%=cellId + i %> onclick="selectUserCell(this.id, <%=users.size() %>)">
												<div class="user-email-field">
													<%= users.get(i).getEmail() %>
												</div>
												<div class="user-name-field">
													<%= users.get(i).getName() %>
												</div>
												<div class="user-surname-field">
													<%= users.get(i).getSurname() %>
												</div>
												<div class="user-phone-field">
													<%= users.get(i).getPhone() %>
												</div>
											</div>
										
							

								<% 		} 
									}		%>





						 	</div>
							<div id="midle-panel" class="panel-containter">
								<form class="edit-user-form">
									<label for="email">Email</label>
									<input type="text" id="user-email" name="email" value="" readonly>
									<label for="name">Name</label>
									<input type="text" id="user-name" name="name" value="" >
									<label for="name" > Surname</label>
									<input type="text" id="user-surname" name="surname" value="" >
									<label for="phone" > Phone Number</label>
									<input type="number" id="user-phone-number" name="phone-number" value="" >
									<input type="hidden" id="action-name" name="action" value="updateUser" >
									<input type="button" class="btn btn-success" id="save-user-button" value="save" onclick="updateUserData();">
								</form>
							</div>
							<div id="right-panel" class="panel-containter">

								<button type="button" class="btn btn-success" onclick="messageUser()">Message User</button>
								<button type="button" class="btn btn-warning" onclick="changePassword()">Change Password</button>
								<button type="button" class="btn btn-danger"  onclick="deleteUser()">Delete User</button>

						 	</div>
						 </div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- **************** pop ups **************** -->





</body>
</html>
