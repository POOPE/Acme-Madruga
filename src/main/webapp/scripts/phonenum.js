var phoneinput = document.getElementById("phoneinput");
var cc = document.getElementById("hidden-cc");
var ac = document.getElementById("hidden-ac");
var pn = document.getElementById("hidden-pn");
var form = document.getElementById("regForm");

if (cc.value || ac.value || pn.value) {
	var inputstring = "";
	if (cc.value) {
		inputstring = inputstring + "+" + cc.value + " ";
	}
	if (ac.value) {
		inputstring = inputstring + "(" + ac.value + ") ";
	}
	if (pn.value) {
		inputstring = inputstring + pn.value;
	}
	phoneinput.value = inputstring;
}

if (form.addEventListener) {
	form.addEventListener("submit", function(evt) {
		validatePhone(evt);
	}, true);
} else {
	form.attachEvent('onsubmit', function(evt) {
		validatePhone(evt);
	});
}

phoneinput.addEventListener("keyup", function() {
	formatPhone();
});

function validatePhone(evt) {
	if (checkPhone()) {
		return true;
	} else {
		evt.preventDefault();
		return false;
	}
}

function checkPhone() {
	res = true;
	if (!cc.value && ac.value && pn.value || !pn.value) {
		res = confirmPhone();
	}
	return res;
}

function confirmPhone() {
	res = false;
	var formattednumber = "";
	if (cc.value) {
		formattednumber = "+" + cc.value;
	}
	if (ac.value) {
		formattednumber = formattednumber + " (" + ac.value + ") ";
	}
	if (pn.value) {
		formattednumber = formattednumber + " " + pn.value;
	}
	var rconfirm = confirm("Phone number " + formattednumber + " does not follow allowed format. Save anyways?");
	if (rconfirm) {
		res = true;
	}
	return res;
}

function formatPhone() {
	var phoneval = phoneinput.value;
	var countrycode = "";
	var areacode = "";
	var phonenumber = "";
	if (phoneval.length > 4) {
		if (phoneval.charAt(0) == "+") {
			// cc
			var phoneparts = phoneval.split(" ");
			if (phoneparts.length > 1) {
				// split by spaces
				countrycode = phoneparts[0].replace("+", "");
				if (phoneparts.length > 2) {
					areacode = phoneparts[1].replace("(", "").replace(")", "");
					phonenumber = phoneparts[2];
				} else {
					if (phoneparts[1].indexOf('(') <= -1 && phoneparts[1].indexOf(')') <= -1) {
						phonenumber = phoneparts[1];
					}
				}
			} else {
				// split differently
				var phoneparts = phoneval.split("(");
				if (phoneparts.length > 1) {
					// separated by parenthesis
					// area code present
					var t = phoneparts[1].split(")");
					if (t.length > 1) {
						countrycode = phoneparts[0].replace("+", "");
						areacode = t[0].trim();
						phonenumber = t[1].trim();
					}
				} else {
					// no area code
					countrycode = phoneval.substring(4, 0).replace("+", "");
					phonenumber = phoneval.substring(phoneval.length, 4);
				}
			}

		} else {
			// no cc
			if (phoneval.indexOf('(') > -1 && phoneval.indexOf(')') > -1) {
				// has area code
				var phoneparts = phoneval.split(" ");
				if (phoneparts.length > 1) {
					// split by space
					areacode = phoneparts[0].replace("(", "").replace(")", "");
					phonenumber = phoneparts[1].trim();
				} else {
					// continuous
					areacode = phoneval.substring(phoneval.indexOf(')'), 0).replace("(", "");
					phonenumber = phoneval.substring(phoneval.length, 3).replace(")", "");
				}
			} else {
				// no area code
				phonenumber = phoneval.trim().replace(" ", "");
			}
		}
	}
	cc.value = countrycode;
	ac.value = areacode;
	pn.value = phonenumber;
}
