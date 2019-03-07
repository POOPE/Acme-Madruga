var brotherhoodInfo = document.getElementById("brotherhood-info");
var form = document.getElementById("regForm");
var termsConditions = document.getElementById("terms-conditions");

if (form.addEventListener) {
	form.addEventListener("submit", function(evt) {
		validatePass(evt);
	}, true);
} else {
	form.attachEvent('onsubmit', function(evt) {
		validatePass(evt);
	});
}

function validatePass(evt) {
	if (termsConditions.checked) {
		return true;
	} else {
		evt.preventDefault();
		alert("Accept the terms and conditions!");
		return false;
	}
}

function checkBrotherhood() {
	var val = document.getElementById("role").value;
	if (val == "BROTHERHOOD") {
		brotherhoodInfo.style.display = "block";
	} else {
		brotherhoodInfo.style.display = "none";
	}
}

$(document).ready(function() {
	checkBrotherhood();
});
