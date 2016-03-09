/**
 * Property Contract utils
 */

$(function() {
    $( ".contractDates" ).datepicker({
    	changeMonth: true,
        changeYear: true
    });
});

$(document).on("click", '#recsoUploadFile', function(e) {
	e.preventDefault();
	var this_js_script = $('script[src*=contract]');
	var root_url = this_js_script.attr('root_url');
	var contract_id = $('#contractId').val();
	var fd = new FormData();
	fd.append( 'file', $( '#recsoFile' )[0].files[0] );
	$.ajax({
		url : root_url + '/filemanager/property/recso/' + contract_id + '/upload',
		data : fd,
		type : "POST",
		processData: false,
		contentType: false,
        dataType: "json",
		success : function(data) {
			if(data.successMessage) {
				$('#recsoFilename').html(data.filename);
			}
		}
	});
});

$(document).on("click", '#titleCommUploadFile', function(e) {
	e.preventDefault();
	var this_js_script = $('script[src*=contract]');
	var root_url = this_js_script.attr('root_url');
	var contract_id = $('#contractId').val();
	var fd = new FormData();
	fd.append( 'file', $( '#titleCommFile' )[0].files[0] );
	$.ajax({
		url : root_url + '/filemanager/property/titleCommitment/' + contract_id + '/upload',
		data : fd,
		type : "POST",
		processData: false,
		contentType: false,
        dataType: "json",
		success : function(data) {
			if(data.successMessage) {
				$('#titleCommFilename').html(data.filename);
			}
		}
	});
});

$(document).on("click", '#titlePolicyUploadFile', function(e) {
	e.preventDefault();
	var this_js_script = $('script[src*=contract]');
	var root_url = this_js_script.attr('root_url');
	var contract_id = $('#contractId').val();
	var fd = new FormData();
	fd.append( 'file', $( '#titlePolicyFile' )[0].files[0] );
	$.ajax({
		url : root_url + '/filemanager/property/titlePolicy/' + contract_id + '/upload',
		data : fd,
		type : "POST",
		processData: false,
		contentType: false,
        dataType: "json",
		success : function(data) {
			if(data.successMessage) {
				$('#titlePolicyFilename').html(data.filename);
			}
		}
	});
});

$(document).on("click", '#settlementUploadFile', function(e) {
	e.preventDefault();
	var this_js_script = $('script[src*=contract]');
	var root_url = this_js_script.attr('root_url');
	var contract_id = $('#contractId').val();
	var fd = new FormData();
	fd.append( 'file', $( '#settlementFile' )[0].files[0] );
	$.ajax({
		url : root_url + '/filemanager/property/settlement/' + contract_id + '/upload',
		data : fd,
		type : "POST",
		processData: false,
		contentType: false,
        dataType: "json",
		success : function(data) {
			if(data.successMessage) {
				$('#settlementFilename').html(data.filename);
			}
		}
	});
});