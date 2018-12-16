function contactHost(host, user)
{

	var message = prompt("Please enter your message");

	
		if (message  != null && message  != "") {
			$.post("messages", {
				senderEmail : user,
				reciverEmail : host,
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
	
	

}