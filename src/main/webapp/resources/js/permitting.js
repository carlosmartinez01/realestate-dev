/**
 * Property Permitting utils
 */

$(function() {
    $( ".permittingDates" ).datepicker({
    	changeMonth: true,
        changeYear: true
    });
});

$(document).on("click", '.meetingCheckBoxArray', function() {
	var element = $(this).attr("id");
	var position = element.indexOf("-");
	var elementID = element.substr(position+1, element.length);
	if($('#'+element ).prop('checked')) {
		showMeeting(elementID);
	} else {
		hideMeeting(elementID);
	}
});

function hideMeeting(element) {
	var elementID = "meetingsBox-"+element;
	var objMain = $('#'+elementID);
	$(objMain).hide('blind', 1000);
}

function showMeeting(element) {
	var elementID = "meetingsBox-"+element;
	var objMain = $('#'+elementID);
	$(objMain).show('blind', 1000);
}

$(function() {
	$('.meetingCheckBoxArray').each(function() {
		var element = $(this).attr("id");
		var position = element.indexOf("-");
		var elementID = element.substr(position+1, element.length);
		var meetingElement = 'displayMeeting-' + elementID;
		$('.'+meetingElement).each(function() {
			var subElement = $(this).attr("id");
			if($('#'+subElement).prop('checked') && $('#'+subElement).val() === 'n/a') {
				hideMeeting(elementID);
				uncheckMeeting(elementID);
			} else {
				checkMeeting(elementID);
			}
		});
	});
});

function checkMeeting(elementID) {
	$('#checkbox-'+elementID).prop('checked', true);
}

function uncheckMeeting(elementID) {
	$('#checkbox-'+elementID).prop('checked', false);
}

$(document).on("click", '#tittleCommitmentUpload', function(e) {
	e.preventDefault();
	var this_js_script = $('script[src*=permitting]');
	var root_url = this_js_script.attr('root_url');
	var permittingId = $('#permittingID').val();
	var url = root_url + '/filemanager/permitting/commitment/'+ permittingId +'/upload';
	var item = 'tittleCommitmentFile';
	var out = 'tittleCommitmentFilename';
	uploadFile(url, permittingId, item, out);
});

$(document).on("click", '#tittleExceptionUpload', function(e) {
	e.preventDefault();
	var this_js_script = $('script[src*=permitting]');
	var root_url = this_js_script.attr('root_url');
	var permittingId = $('#permittingID').val();
	var url = root_url + '/filemanager/permitting/exception/'+ permittingId +'/upload';
	var item = 'tittleExceptionFile';
	var out = 'tittleExceptionFilename';
	uploadFile(url, permittingId, item, out);
});

$(document).on("click", '#surveyReceivedUpload', function(e) {
	e.preventDefault();
	var this_js_script = $('script[src*=permitting]');
	var root_url = this_js_script.attr('root_url');
	var permittingId = $('#permittingID').val();
	var url = root_url + '/filemanager/permitting/survey/'+ permittingId +'/upload';
	var item = 'surveyReceivedFile';
	var out = 'surveyReceivedFilename';
	uploadFile(url, permittingId, item, out);
});

$(document).on("click", '#sitePlanUpload', function(e) {
	e.preventDefault();
	var this_js_script = $('script[src*=permitting]');
	var root_url = this_js_script.attr('root_url');
	var permittingId = $('#permittingID').val();
	var url = root_url + '/filemanager/permitting/sitePlan/'+ permittingId +'/upload';
	var item = 'sitePlanFile';
	var out = 'sitePlanFilename';
	uploadFile(url, permittingId, item, out);
});

$(document).on("click", '#geotechnicalUpload', function(e) {
	e.preventDefault();
	var this_js_script = $('script[src*=permitting]');
	var root_url = this_js_script.attr('root_url');
	var permittingId = $('#permittingID').val();
	var url = root_url + '/filemanager/permitting/geotechnical/'+ permittingId +'/upload';
	var item = 'geotechnicalFile';
	var out = 'geotechnicalFilename';
	uploadFile(url, permittingId, item, out);
});

$(document).on("click", '#trafficStudyUpload', function(e) {
	e.preventDefault();
	var this_js_script = $('script[src*=permitting]');
	var root_url = this_js_script.attr('root_url');
	var permittingId = $('#permittingID').val();
	var url = root_url + '/filemanager/permitting/traffic/'+ permittingId +'/upload';
	var item = 'trafficStudyFile';
	var out = 'trafficStudyFilename';
	uploadFile(url, permittingId, item, out);
});

function uploadFile(url, permittingId, fileItem, out) {
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
			if(data.successMessage) {
				$('#'+out).html(data.filename);
			}
		}
	});
}