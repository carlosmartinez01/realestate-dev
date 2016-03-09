/**
 * Architect Modal Drawings
 */

$(function() {
	var dialog, form;
	function updateDrawings() {
		submitForm();
	}

	function submitForm() {
		var this_js_script = $('script[src*=architectModal]');
		var preconstructionId = this_js_script.attr('preconstruction_id');
		var rootUrl = this_js_script.attr('root_url');
		var url = rootUrl + '/projects/'+ preconstructionId +'/architect/save';
		var parameter = getFormData(preconstructionId);
		var jsonData = JSON.stringify(parameter);
		$.ajax({
			url : url,
			data : jsonData,
			type : "POST",
			contentType: "application/json",
	        dataType: "json",
	        success : function(data) {
				if(data.successMessage) {
					disableSaveButton('save-drawings');
					$('#display-drawing-message').html(data.successMessage).attr('class','msg');
					changeCancelText('cancel-drawings');
					getDrawingsData();
				} else {
					$('#display-drawing-message').html(data.errorMessage).attr('class','error');
				}
			}
		});
	}

	dialog = $("#dialog-drawing-form").dialog({
		autoOpen : false,
		height : "auto",
		width : "auto",
		modal : true
	});

	form = dialog.find("form").on("submit", function(event) {
		event.preventDefault();
		updateDrawings();
	});

	dialog.on('dialogclose', function() {
		dialogClose(form, dialog);
	});

	$(document).on("click", '#open-drawings', function() {
		var actionButtons = [ {
			text : 'Save',
			id: 'save-drawings',
			click : updateDrawings

		}, {
			text : "Cancel",
			id: 'cancel-drawings',
			click : function() {
				dialogClose(form, dialog);
			}
		} ];
		//var element = $(this).attr("id");
		//var position = element.indexOf("-");
		//var elementID = element.substr(position+1, element.length);
		populateForm();
		dialog.dialog('option', 'buttons', actionButtons).data();
		dialog.dialog('open');
		return false;
	});
});

function dialogClose(form, dialog) {
	form[0].reset();
	$('#display-drawing-message').html('');
	$('#display-drawing-message').removeClass('msg');
	$('#display-drawing-message').removeClass('error');
	dialog.dialog("close");
}

function getFormData(preconstructionId) {
	var idCounter = 1;
	var temp;
	var arrayDrawingParent = [];
	$('.form-checkbox').each(function () {
		var drawingParent = {id:'', preConstructionId:preconstructionId, type:'', completed:'', drawingDetails:''};
		var arrayDrawingChild = [];
		var element = $(this).attr("id");
		var position = element.indexOf("-");
		var elementID = element.substr(position+1, element.length);
		if(notNull(temp) !== elementID) {
			temp = elementID;
			idCounter = 1;
		}
		var type = element.substr(0, position);
		drawingParent.id = elementID;
		drawingParent.completed = checkboxReturnVal($('#'+type+'-'+elementID));
		drawingParent.type = type;
		$('.form-dates-'+elementID).each(function () {
			var drawingChild = {id:'', architectDrawingId:'', drawingDate:''};
			var sub_element = $(this).attr("id");
			var sub_position = sub_element.indexOf("-");
			var sub_elementID = isNewDateAdded(sub_element.substr(sub_position+1, sub_element.length));
			drawingChild.id = sub_elementID;
			drawingChild.architectDrawingId = elementID;
			drawingChild.drawingDate = $('#date-'+elementID+'-'+isSubIdNull(sub_elementID, idCounter)).val();
			arrayDrawingChild.push(drawingChild);
			idCounter +=1;
		});
		drawingParent.drawingDetails = arrayDrawingChild;
		arrayDrawingParent.push(drawingParent);
	});
	var parameters = {
			"drawings": '', "dateReceived": $('#date-received').val(),
	};
	parameters.drawings = arrayDrawingParent;
	
	return parameters;
}

function isSubIdNull(elementId, surrogateId) {
	if(elementId === '') {
		return surrogateId;	
	}
	
	return elementId;
}

function isNewDateAdded(str_id) {
	if(str_id.toUpperCase() === 'NOID') {
		return '';
	}
	
	return str_id;
}

function disableSaveButton(element) {
	$('#'+element).prop('disabled', true);
	$('#'+element).css('color', 'white');
}

function changeCancelText(element) {
	$('#'+element).button('option', 'label', 'Close');
}

function populateForm() {
	$('.hidden-checkbox').each(function () {
		var element = $(this).attr("id");
		var position = element.indexOf("-");
		var elementID = element.substr(position+1, element.length);
		populateCheckbox($(this).val(), elementID);
	});
}

function populateCheckbox(element, checkboxId) {
	if(element.toUpperCase() === 'YES') {
		$('#'+checkboxId).prop('checked', true);
	}
}

$(function() {
	var idCounter = 1;
	var temp;
	$(document).on('click', '.add-dates-array', function() {
		var element = $(this).attr("id");
		if(notNull(temp) !== element) {
			temp = element;
			idCounter = 1;
		}
		var currentCounter = notNull($('.dates-group-'+element).length);
		currentCounter +=1;
		var parent = notNull($('#parent-'+element).val());
		var inputHtml = '';
		inputHtml = inputHtml + '<div class="normal-row">';
		inputHtml = inputHtml + '<div class="modal-column cell-padding">';
		inputHtml = inputHtml + '<input type="hidden" id="child-'+notNull(parent)+'" value="'+currentCounter+'">';
		inputHtml = inputHtml + '<input type="hidden" id="drawing_details-noId" class="form-dates-'+notNull(parent)+'">';
		inputHtml = inputHtml + '<input type="text" size="12" id="date-'+notNull(parent)+'-'+currentCounter+'" readonly="readonly" class="construction-dates dates-group-'+notNull(parent)+'" value="">';
		inputHtml = inputHtml + '</div>';
		inputHtml = inputHtml + '</div>';
		idCounter +=1;
		$('#add-dates-elements-'+element).append(inputHtml);
	});
});

function checkboxReturnVal(element) {
	if((element).is(':checked')) {
		return 'Yes';
	}
	return 'No';
}

function getDrawingsData() {
	var this_js_script = $('script[src*=architectModal]');
	var preconstructionId = this_js_script.attr('preconstruction_id');
	var rootUrl = this_js_script.attr('root_url');
	var url = rootUrl + '/projects/'+ preconstructionId +'/get/drawings';
	$.ajax({
		url : url,
		type : "POST",
		contentType: "application/json",
        dataType: "json",
        success : function(data) {
        	for(i = 0; i < data.length; i++) {
        		var datesHtml = '';
        		for(j = 0; j < data[i].drawingDetails.length; j++) {
        			datesHtml = datesHtml + '<div class="normal-row">';
        			datesHtml = datesHtml + '<div class="modal-column cell-padding">';
        			datesHtml = datesHtml + '<input type="hidden" id="child-'+notNull(data[i].id)+'" value="'+data[i].drawingDetails.length+'">';
        			datesHtml = datesHtml + '<input type="hidden" id="drawing_details-'+data[i].drawingDetails[j].id+'" class="form-dates-'+notNull(data[i].id)+'">';
        			datesHtml = datesHtml + '<input type="text" size="12" id="date-'+notNull(data[i].id)+'-'+data[i].drawingDetails[j].id+'" readonly="readonly" class="construction-dates dates-group-'+notNull(data[i].id)+'" value="'+data[i].drawingDetails[j].drawingDate+'">';
        			datesHtml = datesHtml + '</div>';
        			datesHtml = datesHtml + '</div>';
        		}
        		datesHtml = datesHtml + '<div id="add-dates-elements-'+data[i].id+'"></div>';
        		$('#parent-'+data[i].type+'-'+data[i].id).html(datesHtml);
        	}
		}
	});
}