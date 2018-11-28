//**************** user profile changes ***************

function changePassword() {

	var selectedUserEmail = $("#user-email").val();

	if (selectedUserEmail != null && selectedUserEmail != "") {
		var password = prompt("Please enter new password");

		if (password != null && password != "") {
			// to do get
			$.post("updateUserServlet", {
				action : "changePassword",
				password : password,
				email : selectedUserEmail
			}).done(function(status) {
				if (status == 1)
					alert("Password updated successfully");
				else if (status == 0)
					alert("Incorrect Password");
				else
					alert("Unknow error");
			});

		}else {
			alert("Enter new password");
		}

	} else {
		alert("User not selected");
	}

}

function deleteAccount(){

	var selectedUserEmail = $("#user-email").val().trim();

	if (selectedUserEmail != null && selectedUserEmail != "") {

		if (confirm("Do you want to delete your acount?")) {
			$.post("updateUserServlet", {
				action : "deleteUser",
				email : selectedUserEmail
			});

		}

	} else {
		alert("User not selected");
	}

}


function deleteApartment() {

	
	var building_number = $("#building-nr").val();
	var street = $("#street").val();
	var flat_number = $("#flat-nr").val();
	var city = $("#city").val();
	
	var email = $("#email").val();

	

	

		if (confirm("Do you want to delete this apartment?")) {
			$.post("/airbnb/ApartmentServlet", {
				action : "deletePlace",
				email : email,
				building_number : building_number,
				street : street,
				flat_number : flat_number,
				city : city,
			}).done(function(status) {
				if (status == 1) {
					if (alert("Apartment deleteed successfully")) {
					} else
						window.location.reload();
				} else if (status == 0)
					alert("Incorrect Data");
				else
					alert("Unknow error");
			});
		}

	

}