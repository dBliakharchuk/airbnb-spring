// change id in message jsp

window.onload = function() {
	$("#conversation-view").hide();
};

function conversationSelected(email, selectedUser) {

	console.log("partner " + selectedUser);
	console.log("email " + email);
	
	
	$("#conversation-email")[0].innerHTML = selectedUser;
	var data = $.get("http://localhost:8082/messages", {
		email : email,
		selectedUser : selectedUser,
		// crossDomain: true,
		dataType : 'jsonp'
	}).done(function() {
		var result = [];
		result = data.responseJSON;;
			for (var i = 0; i < result.length; i++) {
			
			
			var div = document.createElement('div');
			if(String(result[i].sender.email) == String(email)){
				div.className = "convesations-message message-send";

			}
			else{
				div.className = "convesations-message message-recived";
				var patter = /\#\d{5,10}/;
				var message = result[i].message;
				var isUnread = result[i].isUnread;
				
				if(patter.test(message))
				{
					console.log()
					var confirmationStatus;
					if (confirm("Do you confirm the booking request? Press 'ok' to accept or 'cancel' to decline")) {
					  confirmationStatus = "ok";
					} 
					else {
						 confirmationStatus = "cancel";
						document.getElementById("confirmationField").innerHTML = confirmationStatus;
						}
					document.getElementById("hiddenForm").submit();//your code here

				}
				
				
			}
				
			div.innerHTML = result[i].message;
			
			var divDate = document.createElement('div');
			divDate.className = "date";
			
			var dateString = result[i].date;
			var dateParts = dateString.split("T");
			var dateComponet =  dateParts[0];
			dateComponet.replace("-", ".");
			var hourComponent = dateParts[1];
			hourComponent =  hourComponent.substring(0, 8);
			
			var finalDateString = dateComponet + "  " + hourComponent;
			
			divDate.innerHTML = finalDateString;
			
			div.appendChild(divDate);
			$("#convesations-messages")[0].appendChild(div);
			$("#conversation-view").show();
			
		}

	});


	

}

function hideConvesationViwe() {
	

	
	var myNode = document.getElementById("convesations-messages");
	while (myNode.firstChild) {
	    myNode.removeChild(myNode.firstChild);
	}
//	
//	$("div.convesations-message message-send").remove();
//	$("div.convesations-message message-recived").remove();
//
//	
//	[].forEach.call(document.querySelectorAll('.convesations-messages'),function(e){
//		  e.parentNode.removeChild(e);
//		});
//    
    $("#conversation-view").hide();

}

     