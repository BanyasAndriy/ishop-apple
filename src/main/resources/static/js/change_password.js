 $(function() {
	$(".btn-signin").click(function() {
		var passcode_input = document.querySelector(".pass");
		var password_confirm = document.querySelector(".confir");


	if (passcode_input.value != password_confirm.value) {
		password_confirm.setCustomValidity("Your passwords don't match.");

	}  else{

		password_confirm.setCustomValidity("");



	}
}); 


	$(".confir , .pass").keyup(function() {
var passcode_input = document.querySelector(".pass");
var password_confirm = document.querySelector(".confir");
password_confirm.setCustomValidity("");

 
});


	});





 




