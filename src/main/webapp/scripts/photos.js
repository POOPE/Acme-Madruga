var attachmentInput = document.getElementById("attachment-input");
var hiddenAttachments = document.getElementById("hidden-attachments");
var attachmentAdd = document.getElementById("attachment-add");
var attachments = document.getElementById("attachments");
var addButton = document.getElementById("add");

addButton.setAttribute("onclick", "addAttachment();");

function addAttachment() {
	var url = attachmentInput.value;
	var container = document.createElement("div");
	container.className = "attachment";
	container.backgroundImage = "url('" + url + "')";
	container.setAttribute("onclick", "removeAttachment(this);");
	attachments.appendChild(container);
	updateAttachment();
}

function updateAttachment() {
	var attachment = document.getElementsByClass("attachment");
	hiddenAttachments = "";
	for ( var i = 0; i < attachment.length; i++) {
		if (i < attachment.length - 1) {
			hiddenAttachments = hiddenAttachments + attachment[i].style.backgroundImage + ",";
		} else {
			hiddenAttachments = hiddenAttachments + attachment[i].style.backgroundImage;
		}
	}
}

function removeAttachment(div) {
	div.remove();
	updateAttachment();
}
