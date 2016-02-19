/**
 * Contact Modal
 */

$(function() {
	var dialog, form;
	function createContact() {
		submitForm();
	}

	function submitForm() {
		var this_js_script = $('script[src*=contactModal]');
		var propertyId = this_js_script.attr('property_id');
		var rootUrl = this_js_script.attr('root_url');
		var url = rootUrl + '/contact/'+ propertyId +'/permitting/add';
		var parameter = getFormData(propertyId);
		var jsonData = JSON.stringify(parameter);
		$.ajax({
			url : url,
			data : jsonData,
			type : "POST",
			contentType: "application/json",
	        dataType: "json",
	        success : function(data) {
				if(data.successMessage) {
					disableSaveButton('save-contact');
					$('#display-contact-message').html(data.successMessage).attr('class','msg');
					changeCancelText('cancel-contact');
					contactsAjaxCall();
				} else {
					$('#display-contact-message').html(data.errorMessage).attr('class','error');
				}
			}
		});
	}

	dialog = $("#dialog-create-contact").dialog({
		autoOpen : false,
		height : "auto",
		width : "auto",
		modal : true
	});

	form = dialog.find("form").on("submit", function(event) {
		event.preventDefault();
		createContact();
	});

	dialog.on('dialogclose', function() {
		dialogClose(form, dialog);
	});

	$(document).on("click", '#create-new-contact', function() {
		var actionButtons = [ {
			text : 'Create',
			id: 'save-contact',
			click : createContact

		}, {
			text : "Cancel",
			id: 'cancel-contact',
			click : function() {
				dialogClose(form, dialog);
			}
		} ];
		dialog.dialog('option', 'buttons', actionButtons).data();
		dialog.dialog('open');
		return false;
	});
});

function dialogClose(form, dialog) {
	form[0].reset();
	$('#display-contact-message').html('');
	$('#display-contact-message').removeClass('msg');
	$('#display-contact-message').removeClass('error');
	dialog.dialog("close");
}

function getFormData(propertyId) {
	var title = $('#contact-title');
	var name = $('#contact-name');
	var comment = $('#contact-comment');
	var parameters = {
			"propertyId": propertyId, "title" : title.val(), "name": name.val(), "comment": comment.val()
		};
	return parameters;
}

function disableSaveButton(element) {
	$('#'+element).prop('disabled', true);
	$('#'+element).css('color', 'white');
}

function changeCancelText(element) {
	$('#'+element).button('option', 'label', 'Close');
}