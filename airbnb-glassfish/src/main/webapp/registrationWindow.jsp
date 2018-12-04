<div class="modal fade" id="RegistroModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content">
	    
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	        <h1 class="h3 mb-3 font-weight-normal">Enter your data</h1>
	      </div>
	      <div class="modal-body">
	           <form class="form-registro" action="registrationServlet" method="post">
			      <input type="email" id="inputEmail" name="inputEmail" class="form-control" placeholder="Email" required autofocus>
			      <input type="name" id="inputName" class="form-control" name="inputName" placeholder="Name" required>
			      <input type="surname" id="inputSurname" class="form-control" name="inputSurname" placeholder="Surname" required>              
			      <input type="password" id="inputPassword" class="form-control" name="inputPassword" placeholder="Set a password" required>
			      <button class="btn btn-lg btn-primary btn-block" type="submit" id="Registrate">Registrate</button>
	    		</form>
	      </div>
	
	      <div class="modal-footer">
	        <p class="text-center">Do you already have a TIWbnb account?<a href="#" id="goRegistroLogin">  Log in</a></p>
	      </div>
	
	    </div>
	  </div>
	  </div>