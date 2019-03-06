var brotherhoodInfo = document.getElementById("brotherhood-info");

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
