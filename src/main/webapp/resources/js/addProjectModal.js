/**
 * Project Modal
 */

$(function() {
	var dialog, form;

	function createProject() {
		submitForm();
	}

	function submitForm() {
		var this_js_script = $('script[src*=addProjectModal]');
		var propertyId = this_js_script.attr('property_id');
		var rootUrl = this_js_script.attr('root_url');
		var url = rootUrl + '/projects/add/'+ propertyId;
		var parameter = getTaskFormData(propertyId);
		var jsonData = JSON.stringify(parameter);
		$.ajax({
			url : url,
			data : jsonData,
			type : "POST",
			contentType: "application/json",
	        dataType: "json",
	        success : function(data) {
				if(data.successMessage) {
					disableSaveButton('save-project');
					$('#display-add-message').html(data.successMessage).attr('class','msg');
					changeCancelText('cancel-project');
					getProjects();
				} else {
					$('#display-add-message').html(data.errorMessage).attr('class','error');
				}
			}, error: function() {
				$('#display-add-message').html('Error while processing the request, please try again later!').attr('class','error');
			}
		});
	}

	dialog = $("#dialog-add-project").dialog({
		autoOpen : false,
		height : "auto",
		width : "auto",
		modal : true
	});

	form = dialog.find("form").on("submit", function(event) {
		event.preventDefault();
		createProject();
	});

	dialog.on('dialogclose', function() {
		dialogProjectClose(form, dialog);
	});

	$(document).on("click", '#create-new', function() {
		var actionButtons = [ {
			text : 'Create',
			id: 'save-project',
			click : createProject

		}, {
			text : "Cancel",
			id: 'cancel-project',
			click : function() {
				dialogProjectClose(form, dialog);
			}
		} ];
		dialog.dialog('option', 'buttons', actionButtons).data();
		dialog.dialog('open');
		return false;
	});
});

$(function() {
	$('#div-custom-type').hide();
	$('#project-type').on('change', function() {
		if($(this).val().toLowerCase() === 'other') {
			$('#div-custom-type').show('slide', 500);
		} else {
			$('#div-custom-type').hide('slide', 500);
		}
	});
});

function dialogProjectClose(form, dialog) {
	form[0].reset();
	$('#display-add-message').html('');
	$('#display-add-message').removeClass('msg');
	$('#display-add-message').removeClass('error');
	dialog.dialog("close");
}

function disableSaveButton(element) {
	$('#'+element).prop('disabled', true);
	$('#'+element).css('color', 'white');
}

function changeCancelText(element) {
	$('#'+element).button('option', 'label', 'Close');
}

function getTaskFormData(propertyId) {
	var type = $('#project-type');
	var customName = $('#project-custom-type');
	var phase = $('#project-phase');
	var parameters = {
			"property" : {"id":propertyId}, "projectType": type.val(), "projectName": type.val(), "status": 0
		};
	if(type.val().toLowerCase() === 'other') {
		parameters.projectName = customName.val();
		parameters.projectType = customName.val();
	}
	return parameters;
}