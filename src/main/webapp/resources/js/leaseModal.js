/**
 * Lease Property Modal
 */

$(function() {
	var dialog, form, v_landlord = $("#leaseLandlord"), v_leaseAmount = $("#leaseAmount"), v_rentDate = $("#leaseRent"), v_deadline = $("#ddDeadLineValue"), v_diligenceTerm = $("#leaseDDTerm"),
	v_initTerm = $("#initialTerm"), v_extensionNbr = $("#extensionOptionNbrExt"), v_extensionYears = $("#extensionOptionLength"), v_rofr = $("#rofr"), v_rofo = $("#rofo"),
	v_purchaseOption = $("#purchaseOption"), v_purchaseOptionAfter = $("#purchaseOptionAfter");
	var allFields = $([]).add(v_landlord).add(v_leaseAmount).add(v_rentDate).add(v_deadline).add(v_diligenceTerm).add(v_initTerm).add(v_extensionNbr).add(v_extensionYears)
	.add(v_rofr).add(v_rofo).add(v_purchaseOption).add(v_purchaseOptionAfter), tips = $(".leaseValidateTips");

	function updateTips(t) {
		tips.text(t).addClass("ui-state-highlight");
		setTimeout(function() {
			tips.removeClass("ui-state-highlight", 1500);
		}, 500);
	}

	function saveLease() {
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
		var v_ids = $('#dialogLease-form').data("v_ids");
		var v_url = $('#dialogLease-form').data("v_url");
		var v_extensions = $('.leaseExtensionClass').not('.existingExtension').length;
		var v_action = $('#dialogLease-form').data("v_action");
		var loiId_ps = v_ids.indexOf("-");
		var loiId_value = v_ids.substr(0, loiId_ps);
		var extensions = getExtensions(loiId_value, v_extensions);
		var this_js_script = $('script[src*=leaseModal]');
		var propertyId = this_js_script.attr('property_id');
		var rofr_value = checkboxReturnVal(v_rofr);
		var rofo_value = checkboxReturnVal(v_rofo);
		var purchase_value = checkboxReturnVal(v_purchaseOption);
		var v_lease_url = '';
		if (v_action.toUpperCase().indexOf('ACCEPT') > -1) {
			v_lease_url = v_url + '/propertyLease/accept/lease/' + propertyId;
		} else {
			v_lease_url = v_url + '/propertyLease/save/lease/' + propertyId;
		}
		var parameter = {"lease":{"id":loiId_value,"propertyId":propertyId,"landlord":v_landlord.val(),"rentCommencementDate":v_rentDate.val(), "leaseAmount":v_leaseAmount.val(), "deadLineDate":v_deadline.text(),
				"dueDiligenceTerm":v_diligenceTerm.val(), "initialTerm":v_initTerm.val(), "rofr":rofr_value, "rofo":rofo_value, "purchaseOption":purchase_value,
				"purchaseOptionAfter":v_purchaseOptionAfter.val(), "numberOfExtensions":v_extensionNbr.val(), "extensionLengthOfYears":v_extensionYears.val()}, "extension":extensions};
		var jsonData = JSON.stringify(parameter);
		$.ajax({
			url : v_lease_url,
			data : jsonData,
			type : "POST",
			contentType: "application/json",
	        dataType: "json",
	        success : function(data) {
				if(data.successMessage) {
					$('#displayLeaseMsg').html(data.successMessage).attr('class','msg');
				} else {
					$('#displayLeaseMsg').html(data.errorMessage).attr('class','error');
				}
				showLeaseBox(v_url, propertyId);
			}
		});
	}

	dialog = $("#dialogLease-form").dialog({
		autoOpen : false,
		height : "auto",
		width : "auto",
		modal : true
	});

	form = dialog.find("form").on("submit", function(event) {
		event.preventDefault();
		saveLease();
	});

	dialog.on('dialogclose', function() {
		form[0].reset();
		allFields.removeClass("ui-state-error");
		$('#leaseAddExtensionDiv').html('');
		$('#leaseExistingExtensionDiv').html('');
		$('#displayLeaseMsg').html('');
		$('#displayLeaseMsg').removeClass('msg');
		$('#displayLeaseMsg').removeClass('error');
		dialog.dialog("close");
	});

	$(document).on("click", '.clssLease', function() {
		var elementID = $(this).attr("id");
		var buttonAction = $("#" + elementID).text();
		var myButtons = [ {
			text : changeBttnAction(buttonAction),
			click : saveLease

		}, {
			text : "Cancel",
			click : function() {
				form[0].reset();
				allFields.removeClass("ui-state-error");
				$('#displayMsg').removeClass('msg');
				$('#displayMsg').removeClass('error');
				$('#displayMsg').html('');
				$('#leaseAddExtensionDiv').html('');
				$('#leaseExistingExtensionDiv').html('');
				dialog.dialog("close");
			}
		} ];
		var v_rentDate = $("#leaseRent");
		calculateDeadLine(v_rentDate.val());
		populateForm(elementID);
		var data = dialog.dialog('option', 'buttons', myButtons).data();
		data.v_ids = elementID;
		var this_js_script = $('script[src*=leaseModal]');
		data.v_url = this_js_script.attr('loi_url');
		data.v_action = changeBttnAction(buttonAction);
		dialog.dialog('open');
		return false;
	});
	$('#leaseDDTerm').keyup(function() {
		calculateDeadLine($("#leaseRent").val(), Number($('#leaseDDTerm').val()) + calculateExtensionDays($('.leaseExtensionClass').length));
	});
});

$(function() {
    $( "#leaseRent" ).datepicker({
    	 onSelect: function() {
    		 calculateDeadLine($("#leaseRent").val(), Number($('#leaseDDTerm').val()) + calculateExtensionDays($('.leaseExtensionClass').length));
    	 }
    });
});

function checkboxReturnVal(element) {
	if((element).is(':checked')) {
		return 'Yes';
	}
	return 'No';
}

function getExtensions(leaseId, extensions) {
	var records = [];
	var elementId = 'leaseExtension-';
	var elementIndex = $('.leaseExtensionClass.existingExtension').length;
	elementIndex +=1;
	for(var i = 0; i < extensions; i++) {
		records[i] = {"propertyLeaseId":leaseId, "extensionDays":$('#'+elementId+elementIndex).val()};
		elementIndex += 1;
	}
	return records;
}

$(function() {
	$( "#addLeaseExt" ).on("click", function() {
		$('#leaseAddExtensionDiv').append(function() {
			var current_number = $('.leaseExtensionClass').length;
			var part_index = current_number + 1;
			var html = "";
			html = "<label for='leaseExtension-"+ part_index +"'>Extension</label> ";
			html = html + " <input type='text' name='leaseExtension-"+ part_index +"' id='leaseExtension-"+ part_index +"' value='' size='4'";
			html = html + "class='leaseExtensionClass text ui-widget-content ui-corner-all'>";
			html = html + "<br>";
			return html;
		});
	});
});

$(function() {
	$('#extensionGroup').on('keyup', '.leaseExtensionClass' , function() {
		calculateDeadLine($("#leaseRent").val(), Number($('#leaseDDTerm').val()) + calculateExtensionDays($('.leaseExtensionClass').length));
	});
});

function calculateExtensionDays(numberOfExtensions) {
	var days = 0;
	if(numberOfExtensions || numberOfExtensions > 0) {
		var extensions = Number(numberOfExtensions);
		var elementId = 'leaseExtension-';
		for(var i = 1; i <= extensions; i++) {
			days = Number(days) + Number($('#'+elementId+i).val());
		}
	}
	return days;
}

function counter(i, val) {
	return +val+1;
}

function calculateDeadLine(v_date, v_days) {
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
	$('#ddDeadLineValue').html($.formatDateTime('mm/dd/yy', new Date(deadline)));
}

function populateForm(element) {
	var leaseId_ps = element.indexOf("-");
	var leaseId_value = element.substr(0, leaseId_ps);
	var landlord = leaseId_value + "-landlord";
	$('#leaseLandlord').attr("value", $("#"+landlord).val());
	var leaseAmount = leaseId_value + "-leaseAmount";
	$('#leaseAmount').attr("value", $("#"+leaseAmount).val());
	var rentDate = leaseId_value + "-rent";
	$('#leaseRent').attr("value", $("#"+rentDate).val());
	var deadline = leaseId_value + "-ddeadline";
	calculateDeadLine($("#"+deadline).val());
	var dueDiligenceTerm = leaseId_value + "-diligenceTerm";
	$('#leaseDDTerm').attr("value", $("#"+dueDiligenceTerm).val());
	var iniTerm = leaseId_value + "-iniTerm";
	$('#initialTerm').attr("value", $("#"+iniTerm).val());
	var numberOfExt = leaseId_value + "-numberOfExtensions";
	$('#extensionOptionNbrExt').attr("value", $("#"+numberOfExt).val());
	var lengthOfYearsExt = leaseId_value + "-lengthOfYearsExt";
	$('#extensionOptionLength').attr("value", $("#"+lengthOfYearsExt).val());
	populateCheckbox(leaseId_value + "-rofr", 'rofr');
	populateCheckbox(leaseId_value + "-rofo", 'rofo');
	populateCheckbox(leaseId_value + "-purchaseOption", 'purchaseOption');
	var purchaseOptionAfter = leaseId_value + "-purchaseOptionAfter";
	$('#purchaseOptionAfter').attr("value", $("#"+purchaseOptionAfter).val());
	populateExtensions(element);
}

function populateCheckbox(element, checkboxId) {
	if($('#'+element).val() === 'Yes') {
		$('#'+checkboxId).prop('checked', true);
	}
}

function populateExtensions(element) {
	var loiId_ps = element.indexOf("-");
	var loiId_value = element.substr(0, loiId_ps);
	var leaseExtClass = loiId_value+'-extDaysVal';
	$('.'+leaseExtClass).each(function( i, value ) {
		i += 1;
		var days = value.value;
		var html = '';
		html = "<label for='leaseExtension-"+ i +"'>Extension</label> ";
		html = html + " <input type='text' name='leaseExtension-"+ i +"' id='leaseExtension-"+ i +"' value='"+ days +"' size='4' readonly='readonly'";
		html = html + "class='leaseExtensionClass existingExtension text ui-widget-content ui-corner-all'>";
		html = html + "<br>";
		$('#leaseExistingExtensionDiv').append(html);
	});
}

$(document).on("click", '#leaseUploadFile', function(e) {
	e.preventDefault();
	var this_js_script = $('script[src*=leaseModal]');
	var root_url = this_js_script.attr('loi_url');
	var property_Id = this_js_script.attr('property_id');
	var fd = new FormData();
	fd.append( 'file', $( '#leaseFile' )[0].files[0] );
	$.ajax({
		url : root_url + '/filemanager/property/'+ property_Id +'/lease/file/upload',
		data : fd,
		type : "POST",
		processData: false,
		contentType: false,
        dataType: "json",
		success : function() {
			showLeaseBox(root_url, property_Id);
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

function showLeaseBox(loi_url, property_Id) {
	$.ajax({
		url : loi_url + '/propertyLease/showLease/' + property_Id,
		type : "POST",
		statusCode : {
			200 : function(data) {
				$('#leaseContainer').html(data);
			}
		}
	});
}