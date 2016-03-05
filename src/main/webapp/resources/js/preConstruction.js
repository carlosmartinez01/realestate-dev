/**
* PreConstruction js file
**/

$(function() {
	var idCounter = 0;
	var temp;
	$(document).on('click', '.add-button-array', function() {
		var element = $(this).attr("id");
		if(notNull(temp) !== element) {
			temp = element;
			idCounter = 0;
		}
		var outter = $('#outter-'+element).val();
		var inner = $('#inner-'+element).val();
		inner = Number(notNull(inner)) + Number(idCounter);
		var inputHtml = '';
		inputHtml = inputHtml + '<div class="normal-row">';
		inputHtml = inputHtml + '<div class="normal-column cell-padding">';
		inputHtml = inputHtml +'<input type="input" value="" size="12" class="construction-dates" id="date-'+notNull(outter)+'-'+inner+'" name="details['+notNull(outter)+'].typeDetails['+inner+'].dateReceived" readonly="readonly">';
		inputHtml = inputHtml + '</div>';
		inputHtml = inputHtml + '</div>';
		idCounter +=1;
		$('#add-new-elements-'+element).append(inputHtml);
	});
});

function notNull(number) {
	if(isNaN(number)) {
		return 0;
	}
	return number;
}

$(document).on("click", '.button-upload', function(e) {
	e.preventDefault();
	var element = $(this).attr("id");
	var position = element.indexOf("-");
	var elementID = element.substr(position+1, element.length);
	var this_js_script = $('script[src*=preConstruction]');
	var root_url = this_js_script.attr('root_url');
	var url = root_url + '/filemanager/preconstruction/'+ elementID +'/file/upload';
	var item = 'file-'+elementID;
	var out = 'filename-'+elementID;
	var fileId = 'fileId-'+elementID;
	uploadFile(url, item, out, fileId, elementID);
});

function uploadFile(url, fileItem, out, fileId, id) {
	var fd = new FormData();
	fd.append( 'file', $( '#'+fileItem )[0].files[0] );
	$.ajax({
		url : url,
		data : fd,
		type : "POST",
		processData: false,
		contentType: false,
        dataType: "json",
		success : function(data) {
			var fileLinkHtml = '<a id="file-link-'+id+'" class="files-link" href="'+data.absolutePath+'">'+data.name+'</a>';
			console.log(fileLinkHtml);
			if(data.successMessage) {
				$('#loader-gif').fadeToggle('slow', 'linear');
	        	$('#success-msg').fadeToggle('slow', 'linear', function() {
	        		$('#success-msg').html(data.successMessage);
	        	});
	        	$('#success-msg').delay(2100).slideUp(800, 'linear');
	        	$('#'+out).html(fileLinkHtml);
				$('#'+fileId).val(data.id);
			}
			if(data.errorMessage) {
				$('#error-msg').fadeToggle('slow', 'linear', function() {
	        		$('#error-msg').html(data.errorMessage);
	        	});
				$('#error-msg').delay(2100).fadeToggle(800, 'linear');
			}
		}, error: function(data) {
			$('#server-msg').fadeToggle('slow', 'linear', function() {
				$('#server-msg').html('Error while processing the request, please try again later!');
        	});
			$('#server-msg').delay(2100).fadeToggle(800, 'linear');			
		}
	});
}

$(document).on("focus", '.construction-dates', function() {
	if( $(this).hasClass('hasDatepicker') === false )  {
		$(this).datepicker({
	    	changeMonth: true,
	        changeYear: true
	    });
	}
});

$(document).on("click", '.permit-upload', function(e) {
	e.preventDefault();
	var element = $(this).attr("id");
	var position = element.indexOf("-");
	var elementID = element.substr(position+1, element.length);
	var this_js_script = $('script[src*=preConstruction]');
	var root_url = this_js_script.attr('root_url');
	var url = root_url + '/filemanager/preconstruction/'+ elementID +'/permit/file/upload';
	var item = 'file-permit';
	var out = 'filename-permit';
	var fileId = 'fileId-permit';
	uploadFile(url, item, out, fileId, elementID);
});