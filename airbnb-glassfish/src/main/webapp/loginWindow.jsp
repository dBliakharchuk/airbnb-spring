<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
    
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
        <h1 class="h3 mb-3 font-weight-normal">Log in to continue</h1>
      </div>
      <div class="modal-body">
          <form class="form-signin" action="loginServlet" method="post">
		      <input type="text" id="loginEmail" name="inputEmail" class="form-control" placeholder="Provide your email" required autofocus>
		      <input type="password" id="loginPassword" name="inputPassword" class="form-control" placeholder="Provide your password" required>
		      <div class="checkbox mb-3">
		        <label>
		          <input type="checkbox" name="inputCheckbox" value="remember-me"> Remember me
		        </label>
		      </div>
		      <button class="btn btn-lg btn-primary btn-block" type="submit" id="IniciaSesion">Log in</button>
            </form>

      </div>

      <div class="modal-footer">
        <p class="text-center">You do not have an account?<a href="index.jsp">  Registrate</a></p>
        <p class="text-center"><a href="index.jsp"> Back </a></p>
      </div>

    </div>
  </div>
</div>