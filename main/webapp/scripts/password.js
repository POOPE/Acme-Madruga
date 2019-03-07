var passinput = document.getElementById("password");
var confirminput = document.getElementById("comparepass");
var form = document.getElementById("regForm");
var matchpattern = false;

if (form.addEventListener) {
	form.addEventListener("submit", function(evt) {
		validatePass(evt);
	}, true);
} else {
	form.attachEvent('onsubmit', function(evt) {
		validatePass(evt);
	});
}

passinput.addEventListener("keyup", function() {
	checkRegex();
});

confirminput.addEventListener("keyup", function() {
	comparePass();
});

function checkRegex() {
	var pass = passinput.value;
	if (pass.length > 3 && pass.length < 33 && /^[a-zA-Z0-9_]*$/.test(pass)) {
		matchpattern = true;
		$('#passstatus').removeClass('fa-times');
		$('#passstatus').addClass('fa-check');
		$('#passstatus').css('color', '#219929');
	} else {
		matchpattern = false;
		$('#passstatus').addClass('fa-times');
		$('#passstatus').removeClass('fa-check');
		$('#passstatus').css('color', 'red');
	}
	return matchpattern;

}

function validatePass(evt) {
	if (comparePass() && matchpattern) {
		return true;
	} else {
		evt.preventDefault();
		alert("Passwords don't match!");
		return false;
	}
}

function comparePass() {
	var confirmpass = confirminput.value;
	var pass = passinput.value;
	if (confirmpass.localeCompare(pass) == 0 && matchpattern) {
		$('#confirmpassstatus').removeClass('fa-times');
		$('#confirmpassstatus').addClass('fa-check');
		$('#confirmpassstatus').css('color', '#219929');
		return true;
	} else {
		$('#confirmpassstatus').addClass('fa-times');
		$('#confirmpassstatus').removeClass('fa-check');
		$('#confirmpassstatus').css('color', 'red');
		return false;
	}
}
