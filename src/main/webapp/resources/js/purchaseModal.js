/**
 * Property Purchase Modal
 */

$(function() {
	var dialog, form, v_effectiveDate = $("#p_effectiveDate"), v_price = $("#p_price"), v_diligenceTerm = $("#p_ddterm"), v_deadline = $("#p_ddDeadLineValue"), v_closingTerm = $("#p_closingTerm"), v_closingDate = $("#p_closingDate");
	var allFields = $([]).add(v_effectiveDate).add(v_price).add(v_diligenceTerm).add(v_deadline).add(v_closingTerm).add(v_closingDate), tips = $(".purchaseValidateTips");

	function updateTips(t) {
		tips.text(t).addClass("ui-state-highlight");
		setTimeout(function() {
			tips.removeClass("ui-state-highlight", 1500);
		}, 500);
	}

	function savePurchase() {
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
		var v_ids = $('#dialogPurchase-form').data("v_ids");
		var v_url = $('#dialogPurchase-form').data("v_url");
		var v_extensions = $('.purchaseExtensionClass').not('.purchaseExistingExtension').length;
		var v_action = $('#dialogPurchase-form').data("v_action");
		var loiId_ps = v_ids.indexOf("-");
		var loiId_value = v_ids.substr(0, loiId_ps);
		var extensions = getPurchaseExtensions(loiId_value, v_extensions);
		var this_js_script = $('script[src*=purchaseModal]');
		var propertyId = this_js_script.attr('property_id');
		var v_lease_url = '';
		if (v_action.toUpperCase().indexOf('ACCEPT') > -1) {
			v_lease_url = v_url + '/propertyPurchase/accept/purchase/' + propertyId;
		} else {
			v_lease_url = v_url + '/propertyPurchase/save/purchase/' + propertyId;
		}
		var parameter = {"purchase":{"id":loiId_value,"propertyId":propertyId,"effectiveDate":v_effectiveDate.val(),"price":v_price.val(), "dueDiligenceTerm":v_diligenceTerm.val(), "deadLineDate":v_deadline.text(),
			"closingTerm":v_closingTerm.val(), "closingDate":v_closingDate.text()},
				"extensions":extensions};
		var jsonData = JSON.stringify(parameter);
		$.ajax({
			url : v_lease_url,
			data : jsonData,
			type : "POST",
			contentType: "application/json",
	        dataType: "json",
	        success : function(data) {
				if(data.successMessage) {
					$('#displayPurchaseMsg').html(data.successMessage).attr('class','msg');
				} else {
					$('#displayPurchaseMsg').html(data.errorMessage).attr('class','error');
				}
				showPurchaseBox(v_url, propertyId);
			}
		});
	}

	dialog = $("#dialogPurchase-form").dialog({
		autoOpen : false,
		height : "auto",
		width : "auto",
		modal : true
	});

	form = dialog.find("form").on("submit", function(event) {
		event.preventDefault();
		savePurchase();
	});

	dialog.on('dialogclose', function() {
		form[0].reset();
		allFields.removeClass("ui-state-error");
		$('#p_addExtensionDiv').html('');
		$('#p_existingExtensionDiv').html('');
		$('#displayPurchaseMsg').html('');
		$('#displayPurchaseMsg').removeClass('msg');
		$('#displayPurchaseMsg').removeClass('error');
		dialog.dialog("close");
	});

	$(document).on("click", '.clssPurchase', function() {
		var elementID = $(this).attr("id");
		var buttonAction = $("#" + elementID).text();
		var myButtons = [ {
			text : changeBttnAction(buttonAction),
			click : savePurchase

		}, {
			text : "Cancel",
			click : function() {
				form[0].reset();
				allFields.removeClass("ui-state-error");
				$('#displayPurchaseMsg').removeClass('msg');
				$('#displayPurchaseMsg').removeClass('error');
				$('#displayPurchaseMsg').html('');
				$('#p_addExtensionDiv').html('');
				$('#p_existingExtensionDiv').html('');
				dialog.dialog("close");
			}
		} ];
		var v_effectiveDate = $("#p_effectiveDate");
		calculatePurchaseDeadLine(v_effectiveDate.val());
		populatePurchaseForm(elementID);
		var data = dialog.dialog('option', 'buttons', myButtons).data();
		data.v_ids = elementID;
		var this_js_script = $('script[src*=purchaseModal]');
		data.v_url = this_js_script.attr('purchase_url');
		data.v_action = changeBttnAction(buttonAction);
		dialog.dialog('open');
		return false;
	});
	$('#p_ddterm').keyup(function() {
		calculatePurchaseDeadLine($("#p_effectiveDate").val(), Number($('#p_ddterm').val()) + calculatePurchaseExtensionDays($('.purchaseExtensionClass').length));
	});
});

$(function() {
    $( "#p_effectiveDate" ).datepicker({
    	 onSelect: function() {
    		 calculatePurchaseDeadLine($(this).datepicker('getDate'), Number($('#p_ddterm').val()) + calculatePurchaseExtensionDays($('.purchaseExtensionClass').length));
    	 }
    });
});

function getPurchaseExtensions(purchaseId, extensions) {
	var records = [];
	var elementId = 'purchaseExtension-';
	var elementIndex = $('.purchaseExtensionClass.purchaseExistingExtension').length;
	elementIndex +=1;
	for(var i = 0; i < extensions; i++) {
		records[i] = {"propertyPurchaseId":purchaseId, "extensionDays":$('#'+elementId+elementIndex).val()};
		elementIndex += 1;
	}
	return records;
}

$(function() {
	$( "#p_addExt" ).on("click", function() {
		$('#p_addExtensionDiv').append(function() {
			var current_number = $('.purchaseExtensionClass').length;
			var part_index = current_number + 1;
			var html = "";
			html = "<label for='purchaseExtension-"+ part_index +"'>Extension</label> ";
			html = html + " <input type='text' name='purchaseExtension-"+ part_index +"' id='purchaseExtension-"+ part_index +"' value='' size='4'";
			html = html + "class='purchaseExtensionClass text ui-widget-content ui-corner-all'>";
			html = html + "<br>";
			return html;
		});
	});
});

$(function() {
	$('#p_closingTerm').keyup(function() {
		calculatePurchaseClosingDate($('#p_ddDeadLineValue').text(), $('#p_closingTerm').val());
	});
});

$(function() {
	$('#p_extensionGroup').on('keyup', '.purchaseExtensionClass' , function() {
		calculatePurchaseDeadLine($("#p_effectiveDate").val(), Number($('#p_ddterm').val()) + calculatePurchaseExtensionDays($('.purchaseExtensionClass').length));
	});
});

function calculatePurchaseExtensionDays(numberOfExtensions) {
	var days = 0;
	if(numberOfExtensions || numberOfExtensions > 0) {
		var extensions = Number(numberOfExtensions);
		var elementId = 'purchaseExtension-';
		for(var i = 1; i <= extensions; i++) {
			days = Number(days) + Number($('#'+elementId+i).val());
		}
	}
	return days;
}

function counter(i, val) {
	return +val+1;
}

function calculatePurchaseDeadLine(v_date, v_days) {
	var deadline = '';
	if(v_date) {
		var newDate = new Date(v_date);
		if(v_days) {
			newDate.setDate(newDate.getDate() + Number(v_days));
		}
		deadline = newDate;
	}
	if(isNaN(deadline) || deadline === '') {
		deadline = '01/01/00';
	}
	$('#p_ddDeadLineValue').html($.formatDateTime('mm/dd/yy', new Date(deadline)));
	calculatePurchaseClosingDate($('#p_ddDeadLineValue').text(), $('#p_closingTerm').val());
}

function calculatePurchaseClosingDate(v_date, v_days) {
	var closingDate = '';
	if(v_date) {
		var newDate = new Date(v_date);
		if(v_days) {
			newDate.setDate(newDate.getDate() + Number(v_days));
		}
		closingDate = newDate;
	}
	if(isNaN(closingDate) || closingDate === '') {
		closingDate = '01/01/00';
	}
	$('#p_closingDate').html($.formatDateTime('mm/dd/yy', new Date(closingDate)));
}

function populatePurchaseForm(element) {
	var purchaseId_ps = element.indexOf("-");
	var purchaseId_value = element.substr(0, purchaseId_ps);
	var effectiveDate = purchaseId_value + "-effectiveDate";
	$('#p_effectiveDate').attr("value", $("#"+effectiveDate).val());
	var price = purchaseId_value + "-pprice";
	$('#p_price').attr("value", $("#"+price).val());
	var ddTerm = purchaseId_value + "-pddterm";
	$('#p_ddterm').attr("value", $("#"+ddTerm).val());
	var closingTerm = purchaseId_value + "-closingTerm";
	$('#p_closingTerm').attr("value", $("#"+closingTerm).val());
	var deadline = purchaseId_value + "-pddeadline";
	calculatePurchaseDeadLine($("#"+deadline).val());
	populatePurchaseExtensions(element);
}

function populatePurchaseExtensions(element) {
	var purchaseId_ps = element.indexOf("-");
	var purchaseId_value = element.substr(0, purchaseId_ps);
	var purchaseExtClass = purchaseId_value+'-pextDaysVal';
	$('.'+purchaseExtClass).each(function( i, value ) {
		i += 1;
		var days = value.value;
		var html = '';
		html = "<label for='purchaseExtension-"+ i +"'>Extension</label> ";
		html = html + " <input type='text' name='purchaseExtension-"+ i +"' id='purchaseExtension-"+ i +"' value='"+ days +"' size='4' readonly='readonly'";
		html = html + "class='purchaseExtensionClass purchaseExistingExtension text ui-widget-content ui-corner-all'>";
		html = html + "<br>";
		$('#p_existingExtensionDiv').append(html);
	});
}

$(document).on("click", '#purchaseUploadFile', function(e) {
	e.preventDefault();
	var this_js_script = $('script[src*=purchaseModal]');
	var root_url = this_js_script.attr('purchase_url');
	var property_Id = this_js_script.attr('property_id');
	var fd = new FormData();
	fd.append( 'file', $( '#purchaseFile' )[0].files[0] );
	$.ajax({
		url : root_url + '/filemanager/property/'+ property_Id +'/purchase/file/upload',
		data : fd,
		type : "POST",
		processData: false,
		contentType: false,
        dataType: "json",
		success : function() {
			showPurchaseBox(root_url, property_Id);
		}
	});
});

function updateTips(t) {
	tips.text(t).addClass("ui-state-highlight");
	setTimeout(function() {
		tips.removeClass("ui-state-highlight", 1500);
	}, 500);
}

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

function showPurchaseBox(purchase_url, property_Id) {
	$.ajax({
		url : purchase_url + '/propertyPurchase/showPurchase/' + property_Id,
		type : "POST",
		statusCode : {
			200 : function(data) {
				$('#purchaseContainer').html(data);
			}
		}
	});
}