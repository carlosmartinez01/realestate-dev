/**
 * Task Modal
 */

$(function() {
	var dialog, form;

	function createTask() {
		submitForm();
	}

	function submitForm() {
		var this_js_script = $('script[src*=taskModal]');
		var propertyId = this_js_script.attr('property_id');
		var rootUrl = this_js_script.attr('root_url');
		var url = rootUrl + '/task/'+ propertyId +'/permitting/add';
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
					disableSaveButton('save-task');
					$('#display-task-message').html(data.successMessage).attr('class','msg');
					changeCancelText('cancel-task');
					tasksAjaxCall();
				} else {
					$('#display-task-message').html(data.errorMessage).attr('class','error');
				}
			}
		});
	}

	dialog = $("#dialog-create-task").dialog({
		autoOpen : false,
		height : "auto",
		width : "auto",
		modal : true
	});

	form = dialog.find("form").on("submit", function(event) {
		event.preventDefault();
		createTask();
	});

	dialog.on('dialogclose', function() {
		dialogTaskClose(form, dialog);
	});

	$(document).on("click", '#create-new', function() {
		var actionButtons = [ {
			text : 'Create',
			id: 'save-task',
			click : createTask

		}, {
			text : "Cancel",
			id: 'cancel-task',
			click : function() {
				dialogTaskClose(form, dialog);
			}
		} ];
		populateForm();
		dialog.dialog('option', 'buttons', actionButtons).data();
		dialog.dialog('open');
		return false;
	});
});

function populateForm() {
	var this_js_script = $('script[src*=taskModal]');
	var currentUser = this_js_script.attr('user_logged');
	$('#task-assignedBy').attr("value", currentUser);
}

function dialogTaskClose(form, dialog) {
	form[0].reset();
	$('#display-task-message').html('');
	$('#display-task-message').removeClass('msg');
	$('#display-task-message').removeClass('error');
	dialog.dialog("close");
}

function getTaskFormData(propertyId) {
	var description = $('#task-description');
	var dueDate = $('#task-due-date');
	var assignedBy = $('#task-assignedBy');
	var assignedTo = $('#task-assignedTo');
	var parameters = {
			"propertyId": propertyId, "description" : description.val(), "dueDate": dueDate.val(), "assignedBy": assignedBy.val(), "assignedTo": assignedTo.val()
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

$(function() {
	var completedTaskId = [];
	var incompletedTaskId = [];
	$('#save-completed').on('click', function() {
		$('input[type=checkbox]').each(function () {
			var divId = $(this).closest('div').attr('id');
     	   	var elementId = $('#'+divId +'>input[type=hidden').attr("id");
	           if (this.checked) {
	        	   completedTaskId.push($('#'+elementId).val());
	           } else {
	        	   incompletedTaskId.push($('#'+elementId).val());
	           }
		});
		markTaskSubmit(completedTaskId, incompletedTaskId);
		completedTaskId = [];
		incompletedTaskId = [];
	});
});

function markTaskSubmit(completeTasks, incompleteTasks) {
	$('#loader-gif').fadeToggle('fast', 'linear');
	var this_js_script = $('script[src*=taskModal]');
	var rootUrl = this_js_script.attr('root_url');
	var url = rootUrl + '/task/permitting/update';
	var parameter = {"completedTasksId": completeTasks, "incompletedTasksId": incompleteTasks};
	var jsonData = JSON.stringify(parameter);
	$.ajax({
		url : url,
		data : jsonData,
		type : "POST",
		contentType: "application/json",
        dataType: "json",
        success : function(data) {
        	$('#loader-gif').fadeToggle('slow', 'linear');
        	$('#success-msg').fadeToggle('slow', 'linear', function() {
        		$('#success-msg').html(data.successMessage);
        	});
        	$('#success-msg').delay(2100).slideUp(800, 'linear');
		}, error: function() {
			$('#tabs-1').html('Error while processing the request, please try again later!');
			$('#error-msg').fadeToggle('slow', 'linear', function() {
        		$('#error-msg').html(data.errorMessage);
        	});
			$('#error-msg').delay(2100).fadeToggle(800, 'linear');
		}
	});
}

$(function() {
    $( "#task-due-date" ).datepicker({
    	 changeMonth: true,
         changeYear: true
    });
});
