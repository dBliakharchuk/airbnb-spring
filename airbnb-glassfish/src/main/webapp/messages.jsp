<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->


<%@ page import="model.*" %>
<%@ page import=" java.util.*"%>
<%@ page import="logic.*" %>
<%@ page import="database.*" %>
<%@ page import="java.text.*" %>
<%@ page import = "java.util.regex.Matcher" %>
<%@ page import = "java.util.regex.Pattern" %>



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

	<link rel="stylesheet" href="css/messaging.css">
	<link rel="stylesheet" href="css/message.css">
	

	<!-- Modernizr JS -->
	<script src="js/modernizr-2.6.2.min.js"></script>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
	<script type="text/javascript" src="js/sendMessages.js"></script>
	<script type="text/javascript" src="js/message.js"></script>
	
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->

	</head>
	<body>
		<div id="fh5co-wrapper">
		<div id="fh5co-page">

			<%
			String emailOfLoggedUser = (String) request.getSession().getAttribute("emailOfLoggedUser"); 		
			if (emailOfLoggedUser != null) { 
				if (!emailOfLoggedUser.equals("admin")) {%>
				<jsp:include page="headerLogin.jsp"/> 
				<%} else { %>
				<jsp:include page="headerAdmin.jsp"/>
			<% } 
			} else { 
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp"); 
				dispatcher.forward(request, response); 
			}%>
			<script type="text/javascript">
				document.getElementById("tab-messages").classList.add("active");
			</script>

		<!-- end:header-top -->
     <div id="message-container" class="fh5co-section-gray">	
		<div class="container">
			<div class="row">
				<div class="col-md-8 col-md-offset-2 text-center heading-section animate-box">
						<h3>Messages</h3>
						<p>These are the messages you have received</p>
				</div>
			</div>
			
			<div class="row row-bottom-padded-md">			
				<div class="col-md-8 col-md-offset-2">
					<div class="panel panel-default">
						<div class="panel-body">
							<div class="pull-right">
								<div class="btn-group">
									<button type="button" class="btn btn-success btn-filter" data-target="leido">Read</button>
									<button type="button" class="btn btn-warning btn-filter" data-target="no-leido">Unread</button>
									<button type="button" class="btn btn-default btn-filter" data-target="all">All</button>
								</div>
							</div>
							<input type="hidden" id="userEmail" value= <%=emailOfLoggedUser	%> >
							
							<div class="table-container">
								<table class="table table-filter">
									<tbody>
										  
										<%  ArrayList<Message> messages = (ArrayList<Message>) request.getAttribute("messages");
											if(messages != null)
											{	
												int counter = 0;
												boolean isUnread;
												String messageClass;
												String messageId;
												String senderId;
												String converationPartner;

												for (Message message : messages)
												{
													isUnread = message.getIsUnread();
													if(isUnread)
														messageClass = "no-leido";
													else
														messageClass = "leido";
													
													messageId = Integer.toString(counter);
													senderId = "email" + Integer.toString(counter);
													
													if(message.getSender().getEmail().equals(emailOfLoggedUser))
														converationPartner = message.getReceiver().getEmail();
													else
														converationPartner = message.getSender().getEmail();
										%>
										 
										<tr data-status=<%= messageClass%> class=<%= messageClass%> id=<%= messageId%> onclick="conversationSelected('<%= emailOfLoggedUser	%>','<%= converationPartner	%>')">
											<td>
												<a href="javascript:;" class="star">
													<i class="glyphicon glyphicon-star"></i>
												</a>
											</td>
											<td>
												<div class="media">
												<input type="hidden" id=<%= senderId%> value= <%= converationPartner	%> >
													<h4 class="title" >
																<%= converationPartner	%>
													</h4>
												</div>
											</td>                                        
											<td>      
													<div class="media" onclick="showMessage()">
														<p class="summary"><%= message.getMessage()	%></p>
														<p class="meta"><%= message.getDate()%></p>                                          
													</div>
													<button type="button" class="btn btn-success" onclick="reply(<%= messageId%>)" style="float:right; margin-top: -30px; margin-right: 20px;">Reply</button>

											</td>
										</tr>   
										
										
										<%			counter++;
										
										
										
												}
												}%>
										
										                                
									</tbody>
								</table>
								<form name="hiddenForm" id="hiddenForm" action="confirmation" method="post">
									<input name="confirmationField" id="confirmationField" type="text" hidden>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="conversation-view">
			<div class="conversation-header">
				<div class="conversation-email" id="conversation-email">
					
				</div>
				<button class="hide-conversation-button"type="button" name="button" onclick="hideConvesationViwe()">X</button>
			</div>
			<div class="convesations-messages" id="convesations-messages">
		
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
     	<script type="text/javascript">
	        $(document).ready(function () {
				$('.star').on('click', function () {
			      $(this).toggleClass('star-checked');
			    });
			
			
			    $('.btn-filter').on('click', function () {
			      var $target = $(this).data('target');
			      if ($target != 'all') {
			        $('.table tr').css('display', 'none');
			        $('.table tr[data-status="' + $target + '"]').fadeIn('slow');
			      } else {
			        $('.table tr').css('display', 'none').fadeIn('slow');
			      }
			    });
			
			 });
		</script>
        
	</body>
</html>
