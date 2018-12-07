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
		result = data.responseJSON;
		
		for (var i = 0; i < result.length; i++) {
			
			
			var div = document.createElement('div');
			if(String(result[i].sender.email) == String(email))
				div.className = "convesations-message message-send";
			else
				div.className = "convesations-message message-recived";
			div.innerHTML = result[i].message;
			
			var divDate = document.createElement('div');
			divDate.className = "date";
			divDate.innerHTML = result[i].date;
			
			div.appendChild(divDate);
			$("#conversation-view")[0].appendChild(div);
			$("#conversation-view").show();
			
		}

	});

	

}

function hideConvesationViwe() {
	
	//to do!!!!!!!!!!!!
//	var paras = document.getElementsByClassName('convesations-messages');
//
//	while(paras[0]) {
//	    paras[0].parentNode.removeChild(paras[0]);
//	}​
//	
//	
//	[].forEach.call(document.querySelectorAll('.convesations-messages'),function(e){
//		  e.parentNode.removeChild(e);
//		});
    
    $("#conversation-view").hide();

}
