/**
 * LOI Property Modal
 */

$(function() {
	var dialog, form, v_price = $("#loiPrice"), allFields = $([]).add(v_price), tips = $(".validateTips");

	function updateTips(t) {
		tips.text(t).addClass("ui-state-highlight");
		setTimeout(function() {
			tips.removeClass("ui-state-highlight", 1500);
		}, 500);
	}

	function savePrice() {
		var valid = true;
		allFields.removeClass("ui-state-error");
		valid = true;

		if (valid) {
			submitForm();
		}
		return valid;
	}

	function submitForm() {
		allFields.removeClass("ui-state-error");
		var v_ids = $('#dialogLoi-form').data("v_ids");
		var v_url = $('#dialogLoi-form').data("v_url");
		var v_action = $('#dialogLoi-form').data("v_action");
		var loiId_ps = v_ids.indexOf("-");
		var loiId_value = v_ids.substr(0, loiId_ps);
		var v_loi_url = '';
		var propertyId = v_ids.substr(loiId_ps + 1, v_ids.length - 1);
		if (v_action.toUpperCase().indexOf('ACCEPT') > -1) {
			v_loi_url = v_url + '/propertyLOI/accept/price/' + propertyId + '/';
		} else {
			v_loi_url = v_url + '/propertyLOI/save/price/';
		}
		var parameters = {
			r_price : v_price.val()
		};
		$.ajax({
			url : v_loi_url + loiId_value,
			data : parameters,
			type : "POST",
			success : function(data) {
				if(data.successMessage) {
					$('#displayLOIMsg').html(data.successMessage).attr('class','msg');
				} else {
					$('#displayLOIMsg').html(data.errorMessage).attr('class','error');
				}
				showLOIBox(v_url, propertyId);
			}
		});
	}

	dialog = $("#dialogLoi-form").dialog({
		autoOpen : false,
		height : "auto",
		width : "auto",
		modal : true
	});

	form = dialog.find("form").on("submit", function(event) {
		event.preventDefault();
		savePrice();
	});

	dialog.on('dialogclose', function() {
		form[0].reset();
		allFields.removeClass("ui-state-error");
		$('#displayLOIMsg').removeClass('msg');
		$('#displayLOIMsg').removeClass('error');
		$('#displayLOIMsg').html('');
		dialog.dialog("close");
	});

	$(document).on("click", '.clssLOI', function() {
		var elementID = $(this).attr("id");
		var buttonAction = $("#" + elementID).text();
		var elementValue = $("#" + elementID).val();
		var myButtons = [ {
			text : changeBttnAction(buttonAction),
			click : savePrice

		}, {
			text : "Cancel",
			click : function() {
				form[0].reset();
				allFields.removeClass("ui-state-error");
				$('#displayMsg').removeClass('msg');
				$('#displayMsg').removeClass('error');
				$('#displayMsg').html('');
				dialog.dialog("close");
			}
		} ];
		$('#loiPrice').attr("value", elementValue);
		var data = dialog.dialog('option', 'buttons', myButtons).data();
		data.v_ids = elementID;
		var this_js_script = $('script[src*=loiModal]');
		data.v_url = this_js_script.attr('loi_url');
		data.v_action = changeBttnAction(buttonAction);
		dialog.dialog('open');
		return false;
	});
});

$(document).on("click", '#loiUploadFile', function(e) {
	e.preventDefault();
	var this_js_script = $('script[src*=loiModal]');
	var root_url = this_js_script.attr('loi_url');
	var property_Id = this_js_script.attr('property_id');
	var fd = new FormData();
	fd.append( 'file', $( '#loiFile' )[0].files[0] );
	$.ajax({
		url : root_url + '/filemanager/property/'+ property_Id +'/loi/file/upload',
		data : fd,
		type : "POST",
		processData: false,
		contentType: false,
        dataType: "json",
		success : function() {
			showLOIBox(root_url, property_Id);
		}
	});
});

function checkLength(o, n, min, max) {
	if (o.val().length > max || o.val().length < min) {
		o.addClass("ui-state-error");
		updateTips("Length of " + n + " must be between " + min + " and " + max
				+ ".");
		return false;
	} else {
		return true;
	}
}

function checkRegexp(o, regexp, n) {
	if (!(regexp.test(o.val()))) {
		o.addClass("ui-state-error");
		updateTips(n);
		return false;
	} else {
		return true;
	}
}

function checkMatch(p, c, n) {
	if (p.val() === c.val()) {
		return true;
	} else {
		p.addClass("ui-state-error");
		updateTips(n);
		return false;
	}
}

function changeBttnAction(str_btn) {
	if (str_btn === 'View') {
		return 'Save';
	} else if (str_btn === 'Accept') {
		return 'Accept';
	} else {
		return str_btn;
	}
}

function showLOIBox(loi_url, property_Id) {
	$.ajax({
		url : loi_url + '/propertyLOI/showLOI/' + property_Id,
		type : "POST",
		statusCode : {
			200 : function(data) {
				$('#loiContainer').html(data);
			}
		}
	});
}

$(document).ready(function() {
	var this_js_script = $('script[src*=loiModal]');
	var property_Id = this_js_script.attr('property_id');
	var loi_url = this_js_script.attr('loi_url');
	showLOIBox(loi_url, property_Id);
});