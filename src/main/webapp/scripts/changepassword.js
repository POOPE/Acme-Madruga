var passStatus = false;
function syncCheck(response) {
	if (response.responseText == 1) {
		$('#oldpassstatus').removeClass('fa-times');
		$('#oldpassstatus').addClass('fa-check');
		$('#oldpassstatus').css('color', '#219929');
		passStatus = true;
	} else {
		$('#oldpassstatus').addClass('fa-times');
		$('#oldpassstatus').removeClass('fa-check');
		$('#oldpassstatus').css('color', 'red');
		passStatus = false;
	}
}

function checkuser(string) {
	$.ajax({
		url : 'user/check.do',
		type : 'GET',
		data : {
			pass : string
		},
		complete : syncCheck

	});
}

var passinput = document.getElementsByName("oldpass")[0];
var confirminput = document.getElementsByName("confirmpass")[0];
var newinput = document.getElementsByName("newpass")[0];
passinput.addEventListener("keyup", function(e) {
	var pass = passinput.value;
	checkuser(pass);
});

confirminput.addEventListener("keyup", function() {
	comparePass();
});

function comparePass() {
	var confirmpass = confirminput.value;
	var newpass = newinput.value;
	if (confirmpass.localeCompare(newpass) == 0) {
		$('#newpassstatus').removeClass('fa-times');
		$('#newpassstatus').addClass('fa-check');
		$('#newpassstatus').css('color', '#219929');
		return true;
	} else {
		$('#newpassstatus').addClass('fa-times');
		$('#newpassstatus').removeClass('fa-check');
		$('#newpassstatus').css('color', 'red');
		return false;
	}
}

var form = document.getElementById("passform");
if (form.addEventListener) {
	form.addEventListener("submit", function(evt) {
		validateForm(evt);
	}, true);
} else {
	form.attachEvent('onsubmit', function(evt) {
		validateForm(evt);
	});
}

function validateForm(evt) {
	checkuser(passinput.value);
	if (passStatus && comparePass()) {
		document.getElementById("passval").value = newinput.value;
		return true;
	} else {
		evt.preventDefault();
		alert("Check fields!");
		return false;
	}
}
