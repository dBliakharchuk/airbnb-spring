function reply(id){
	
	var selectedUserEmail = $("#email" + id).val();
	var logeInUserEmail = $("#userEmail").val();

	
	
	var message = prompt("Please enter your message");

	if (selectedUserEmail != null && selectedUserEmail != "") {
		if (message  != null && message  != "") {
			
			$.post("messages", {
				senderEmail : logeInUserEmail,
				reciverEmail : selectedUserEmail,
				message : message
			}).done(function(status) {
				if (status == 1) {
					if (alert("Message send")) {
					} else
						window.location.reload();
				} else if (status == 0)
					alert("Servlet error");
				else
					alert("Unknow error");
			});
		
				
		}else {
			alert("Pls enter message");
		}
	}else {
		alert("User not selected");
	}
	
	
	

	
	
}

function showMessage()
{
}