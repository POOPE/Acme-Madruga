var attachmentInput = document.getElementById("attachment-input");
var hiddenAttachments = document.getElementById("hidden-attachments");
var attachmentAdd = document.getElementById("attachment-add");
var attachments = document.getElementById("attachments");
var addButton = document.getElementById("attachment-add");

// test

addButton.setAttribute("onclick", "addAttachment();");
addButton.addEventListener("click", function(event) {
	event.preventDefault();
});

function reloadAttachment() {
	var attachmentvalues = hiddenAttachments.value.split(",");
	if (attachmentvalues[0] != "") {
		for ( var i = 0; i < attachmentvalues.length; i++) {
			var container = document.createElement("div");
			container.className = "attachment";
			container.style.backgroundImage = "url('" + attachmentvalues[i] + "')";
			container.setAttribute("onclick", "removeAttachment(this);");
			attachments.appendChild(container);
		}
		updateAttachment();
	}
}

function addAttachment() {
	var url = attachmentInput.value;
	var container = document.createElement("div");
	if (url != "" && url != null) {
		container.className = "attachment";
		container.style.backgroundImage = "url('" + url + "')";
		container.setAttribute("onclick", "removeAttachment(this);");
		attachments.appendChild(container);
		updateAttachment();
	}
}

function updateAttachment() {
	var attachment = document.getElementsByClassName("attachment");
	var attachmentsString = "";
	for ( var i = 0; i < attachment.length; i++) {
		if (i < attachment.length - 1) {
			attachmentsString = attachmentsString + attachment[i].style.backgroundImage.slice(4, -1).replace(/"/g, "") + ",";
		} else {
			attachmentsString = attachmentsString + attachment[i].style.backgroundImage.slice(4, -1).replace(/"/g, "");
		}
	}
	hiddenAttachments.value = attachmentsString;
}

function removeAttachment(div) {
	div.remove();
	updateAttachment();
}

$(document).ready(function() {
	reloadAttachment();
});
