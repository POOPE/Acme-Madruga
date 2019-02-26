var phoneinput = document.getElementById("phoneinput");
var cc = document.getElementById("hidden-cc");
var ac = document.getElementById("hidden-ac");
var pn = document.getElementById("hidden-pn");
var matchpattern = false;

if (form.addEventListener) {
	form.addEventListener("submit", function(evt) {
		validateForm(evt);
	}, true);
} else {
	form.attachEvent('onsubmit', function(evt) {
		validateForm(evt);
	});
}

phoneinput.addEventListener("keyup", function() {
	checkPhone();
});

function checkPhone() {
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

function validateForm(evt) {
	checkuser(passinput.value);
	if (comparePass() && matchpattern) {
		return true;
	} else {
		evt.preventDefault();
		$("#form").effect("shake");
		return false;
	}
}
