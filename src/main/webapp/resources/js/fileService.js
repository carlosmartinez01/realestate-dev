/**
 * Upload a file and returns its absolute path
 */

$(document).on("click", '.uploadFile', function(e) {
	e.preventDefault();
	var fileController = $('.uploadFile').val();
	var this_js_script = $('script[src*=fileService]');
	var root_url = this_js_script.attr('root_url');
	var fd = new FormData();
	fd.append( 'file', $( '#fileToBeUpload' )[0].files[0] );
	$.ajax({
		url : root_url + fileController,
		data : fd,
		type : "POST",
		processData: false,
		contentType: false,
        dataType: "json",
		success : function(data) {
			if(data.successMessage) {
				$('#filename').attr('src', data.filename);
			}
		}
	});
});