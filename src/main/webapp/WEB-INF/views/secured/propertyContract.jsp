<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<html>
<head>
<title></title>
<script>
$(function() {
	// run the currently selected effect
	function runEffect(x) {
		// get effect type from
		var selectedEffect = $("#" + x).val();
		if (selectedEffect === "hide") {
			hideBar();
		} else if (selectedEffect === "show") {
			showBar();
		}
	}
	;

	function hideBar() {
		// run the effect        
		$("#effect").hide('slide', 1000);
		setTimeout(function() {
			removeDiv();
		}, 1001);
		function removeDiv() {
			// Variables
			var objMain = $('#main');
			objMain.removeClass('use-sidebar');
		}
	}
	;

	function showBar() {
		// run the effect
		// Variables
		var objMain = $('#main');
		objMain.addClass('use-sidebar');
		setTimeout(function() {
			addDiv();
		}, 1);
		function addDiv() {
			$("#effect").show('slide', 500);
		}

	}
	;

	// set effect from select menu value
	$('.buttonList').on('click', '.ui-state-default', function() {
		runEffect($(this).attr("id"));
	});
});
</script>

<script>
function showSideBarMenu(){
	 $.ajax({
		    id: <%=request.getAttribute("propertyOID")%>,
		    url: '<%=request.getContextPath()%>/projects/menuNavigation',
					statusCode : {
						200 : function(data) {
							$('#menubox').html(data);
						}
					}
		});
	}
$(document).ready(function() {	
	showSideBarMenu();
});
</script>
<script>
$( function() {
	$("#moveToContract").on("click", function(e){
    	e.preventDefault();
    	$('#contractForm').attr('action', '<%=request.getContextPath()%>'
    	    	+'/properties/moveContract/${requestScope.propertyOID}/${propertyView.propertyContract.id}')[0].submit();
						});
	});
</script>
<script type="text/javascript"
	note_url='<%=request.getContextPath()%>/note'
	obj_type='<%=request.getAttribute("objectType")%>'
	obj_id='<%=request.getAttribute("propertyOID")%>'
	page_id='edit_property_contract'
	user_id='<%=request.getAttribute("username")%>'
	src="<%=request.getContextPath()%>/resources/js/notes.js"></script>

<script type="text/javascript" 
	property_id='<%=request.getAttribute("propertyOID")%>'
	loi_url='<%=request.getContextPath()%>'
	src="<%=request.getContextPath()%>/resources/js/loiModal.js"></script>
	
<script type="text/javascript" 
    property_id='<%=request.getAttribute("propertyOID")%>'
    loi_url='<%=request.getContextPath()%>'
    src="<%=request.getContextPath()%>/resources/js/leaseModal.js"></script>
    
<script type="text/javascript" 
    property_id='<%=request.getAttribute("propertyOID")%>'
    purchase_url='<%=request.getContextPath()%>'
    src="<%=request.getContextPath()%>/resources/js/purchaseModal.js"></script>
    
<script type="text/javascript" 
	root_url='<%=request.getContextPath()%>'
    src="<%=request.getContextPath()%>/resources/js/contract.js"></script>
	
<script type="text/javascript">
$(function() {
	var property_id='<%=request.getAttribute("propertyOID")%>';
	var contractTypeSelection='<%=request.getAttribute("CONTRACT_SELECTED")%>';
	if(contractTypeSelection==='lease') {
		var lease_url = '<%=request.getContextPath()%>';
		showLeaseBox(lease_url, property_id);
	}
	if(contractTypeSelection==='purchase') {
		var purchase_url = '<%=request.getContextPath()%>';
		showPurchaseBox(purchase_url, property_id);
	}
	$('.leaseRadioBtn, .purchaseRadioBtn').change(function() {
		var isLease = $( '.leaseRadioBtn' ).prop('checked');
		if(isLease) {
			var lease_url = '<%=request.getContextPath()%>';
			$('#purchaseContainer').html('');
			showLeaseBox(lease_url, property_id);
		}

		var isPurchase = $( '.purchaseRadioBtn' ).prop('checked');
		if(isPurchase) {
			var purchase_url = '<%=request.getContextPath()%>';
			$('#leaseContainer').html('');
			showPurchaseBox(purchase_url, property_id);
		}
	});
});
</script>
</head>
<body>
	<div class="buttonList">
		<button id="buttonHide" value="hide" type="button" title="HIDE MENU"
			class="ui-state-default ui-corner-all">&lt;&lt;</button>
		<button id="buttonShow" value="show" type="button" title="SHOW MENU"
			class="ui-state-default ui-corner-all">&gt;&gt;</button>
	</div>
	<div style="clear: both;">&nbsp;</div>

	<div class="use-sidebar sidebar-at-left" id="main">
		<div id="boxContent">
			<div id="page">
				<div id="content">
					<h2 class="title">Property Contract</h2>
					<div class="post">
						<div class="bg1">
							<div class="bg2">
								<div class="bg3">
									<c:if test="${not empty messageForm}">
										<div class="msg">${messageForm}</div>
									</c:if>
									<div class="entry">
										<div id="dialogLoi-form" title="LOI Acceptances">
											<p class="validateTips"></p>
											<div id="displayLOIMsg">${messageForm}</div>
											<form>
												<fieldset>
													<label for="loiPrice">Enter Price</label> <input
														type="text" name="loiPrice" id="loiPrice" value=""
														class="text ui-widget-content ui-corner-all">
													<input type="submit" tabindex="-1"
														style="position: absolute; top: -1000px">
												</fieldset>
											</form>
										</div>
										<div id="dialogLease-form" title="Lease Edit">
											<p class="leaseValidateTips"></p>
											<div id="displayLeaseMsg"></div>
											<form>
												<fieldset>
													<label for="leaseLandlord">Landlord</label> <input
														type="text" name="leaseLandlord" id="leaseLandlord" value=""
														class="text ui-widget-content ui-corner-all"><br>
														<label for="leaseAmount">Lease Amount $</label> <input
														type="text" name="leaseAmount" id="leaseAmount" value=""
														class="text ui-widget-content ui-corner-all">
														<br>
														<label for="leaseRent">Rent Commencement Date</label> <input
														type="text" name="leaseRent" id="leaseRent" value="" readonly="readonly"
														class="text ui-widget-content ui-corner-all">
														<br>
														<label for="leaseDDTerm">Due Dilligence Term</label> <input
														type="text" name="leaseDDTerm" id="leaseDDTerm" value="" size="4"
														class="text ui-widget-content ui-corner-all"> days
														<br>
														<div id="extensionGroup">
														<div id="leaseExistingExtensionDiv"></div>
														<div id="leaseAddExtensionDiv"></div>
														</div>
														<label id="addLeaseExt" for="addLeaseExt">(+) Extension</label>
														<br>
														<label id="ddDeadline" for="ddDeadLineValue">DD Deadline - </label>
														<div id="ddDeadLineValue"></div>
														<label for="initialTerm">Initial Term</label>
														<input type="text" name="initialTerm" id="initialTerm" value=""
														class="text ui-widget-content ui-corner-all">														
														<br>
														<label id="extensionOption" for="extensionOption" class="tooltip">
														Extension Option</label>
														<input type="text" name="extensionOption" id="extensionOptionNbrExt" value="" size="3" title="# of extensions"
														class="text ui-widget-content ui-corner-all">														
														<input type="text" name="extensionOption" id="extensionOptionLength" value="" size="3" title="length of years"
														class="text ui-widget-content ui-corner-all">
														<br>
														<label for="rofr">ROFR</label>
														<input type="checkbox" id="rofr" value="Yes" size="3">
														<label for="rofr">ROFO</label>
														<input type="checkbox" id="rofo" value="Yes"
														class="text ui-widget-content ui-corner-all">
														<br>
														<label for="purchaseOption">Purchase Option</label>
														<input type="checkbox" name="purchaseOption" id="purchaseOption" value="Yes"
														class="text ui-widget-content ui-corner-all"> after
														<input type="text" name="purchaseOptionAfter" id="purchaseOptionAfter" value="" size=5
														class="text ui-widget-content ui-corner-all">
														<input type="submit" tabindex="-1"
														style="position: absolute; top: -1000px">														
												</fieldset>
											</form>
										</div>
										<div id="dialogPurchase-form" title="Offer Edit">
											<p class="purchaseValidateTips"></p>
											<div id="displayPurchaseMsg"></div>
											<form>
												<fieldset>
												<input style="height:0px; top:-1000px; position:absolute" type="text" value="">		
														<label for="p_effectiveDate">Effective Date</label> <input
														type="text" name="p_effectiveDate" id="p_effectiveDate" value="" readonly="readonly"
														class="text ui-widget-content ui-corner-all">
														<br>											
														<label for="p_price">Enter Price $</label> <input
														type="text" name="p_price" id="p_price" value=""
														class="text ui-widget-content ui-corner-all">
														<br>														
														<label for="p_ddterm">Due Dilligence Term</label> <input
														type="text" name="p_ddterm" id="p_ddterm" value="" size="4"
														class="text ui-widget-content ui-corner-all"> days
														<br>																	
														<div id="p_extensionGroup">
														<div id="p_existingExtensionDiv"></div>
														<div id="p_addExtensionDiv"></div>
														</div>
														<label id="p_addExt" for="p_addExt">(+) Extension</label>
														<br>
														<label id="p_ddDeadline" for="p_ddDeadLineValue">DD Deadline - </label>
														<div id="p_ddDeadLineValue"></div>
														<br>
														<label for="p_closingTerm">Closing Term </label>
														<input type="text" name="p_closingTerm" id="p_closingTerm" value="" size="4"
														class="text ui-widget-content ui-corner-all"> days
														<br>			
														<label for="p_closingDate">Closing Date - </label>
														<div id="p_closingDate"></div>
														<br>																														
														<input type="submit" tabindex="-1"
														style="position: absolute; top: -1000px">														
												</fieldset>
											</form>
										</div>
										<br>
										<spring:url
											value="/properties/saveContract/${requestScope.propertyOID}/${propertyView.propertyContract.id}"
											var="addOrUpdateProperty" />
										<form:form method="POST" action="${addOrUpdateProperty}"
											modelAttribute="propertyView" id="contractForm">
											<div class="tblRowContract contractTypeRadio">													
													<div class="tblColContract contractNoneTypeGroup">
													&nbsp;														
													</div>
													<div class="tblColContract contractTypeGroup">
														<form:radiobutton path="propertyContract.contractType" value="lease" cssClass="leaseRadioBtn" />
														Lease
													</div>
													<div class="tblColContract contractTypeGroup">
														<form:radiobutton path="propertyContract.contractType" value="purchase" cssClass="purchaseRadioBtn" />
														Purchase
													</div>
												</div>
											<div class="tblMainContract">												
												<div class="tblRowContract">
													<div class="tblColContract">
														<div id="loiContainer"></div>
													</div>

													<div class="tblColContract">
														<div id="leaseContainer"></div>
														<div id="purchaseContainer"></div>
													</div>
												</div>												
											</div>
											<div style="clear: both;">&nbsp;</div>
											<div style="clear: both;">&nbsp;</div>
											<div class=tblContractTask>
											<div class="tblRowContract">
													<div class="tblColContract">
														Yes
													</div>
													<div class="tblColContract">
														No
													</div>
													<div class="tblColContract">
														N/A
													</div>
													<div class="tblColContract">
														Contact Tasks
													</div>
													<div class="tblColContract">
														Dates
													</div>
												</div>
												<div class="tblRowContract">
													<div class="tblColContract">
														<form:radiobutton path="propertyContract.emDeposited" value="yes" />
													</div>
													<div class="tblColContract">
														<form:radiobutton path="propertyContract.emDeposited" value="no" />
													</div>
													<div class="tblColContract">
														<form:radiobutton path="propertyContract.emDeposited" value="n/a" />
													</div>
													<div class="tblColContract">
														Ernest Money Deposited/<br>
														Ordered Preliminary Report
													</div>
													<div class="tblColContract">
														<form:input path="propertyContract.emDepositedDate" id="c_emDepositedDate" cssClass="contractDates" readonly="true" />
													</div>
												</div>
												<div class="tblRowContract">
													<div class="tblColContract">
														<form:radiobutton path="propertyContract.reCommitteeSignOff" value="yes" />
													</div>
													<div class="tblColContract">
														<form:radiobutton path="propertyContract.reCommitteeSignOff" value="no" />
													</div>
													<div class="tblColContract">
														<form:radiobutton path="propertyContract.reCommitteeSignOff" value="n/a" />
													</div>
													<div class="tblColContract">
														Real Estate Committee Sign Off Sheet
													</div>
													<div class="tblColContract">
														<form:input path="propertyContract.reCommitteeSODate" id="c_reCommitteeSODate" cssClass="contractDates" readonly="true" /><br>
													</div>
													<div class="tblColContract">
														<input type="file" id="recsoFile" value="Choose File" />
														<button id="recsoUploadFile">Upload</button>
														<span id="recsoFilename"><c:out value="${propertyView.propertyContract.reCommitteeSOFileName.name}"></c:out></span>
													</div>
												</div>
												<div class="tblRowContract">
													<div class="tblColContract">
														<form:radiobutton path="propertyContract.geoTechOrdered" value="yes" />
													</div>
													<div class="tblColContract">
														<form:radiobutton path="propertyContract.geoTechOrdered" value="no" />
													</div>
													<div class="tblColContract">
														<form:radiobutton path="propertyContract.geoTechOrdered" value="n/a" />
													</div>
													<div class="tblColContract">
														GeoTech Ordered
													</div>
													<div class="tblColContract">
														<form:input path="propertyContract.geoTechOrderedCompany" /><br>
													</div>
												</div>
												<div class="tblRowContract">
													<div class="tblColContract">
														<form:radiobutton path="propertyContract.escrowHolder" value="yes" />
													</div>
													<div class="tblColContract">
														<form:radiobutton path="propertyContract.escrowHolder" value="no" />
													</div>
													<div class="tblColContract">
														<form:radiobutton path="propertyContract.escrowHolder" value="n/a" />
													</div>
													<div class="tblColContract">
														Escrow Holder
													</div>
													<div class="tblColContract">
														<form:input path="propertyContract.escrowHolderName" /><br>
													</div>
												</div>
												<div class="tblRowContract">
													<div class="tblColContract">
														<form:radiobutton path="propertyContract.titleCommitment" value="yes" />
													</div>
													<div class="tblColContract">
														<form:radiobutton path="propertyContract.titleCommitment" value="no" />
													</div>
													<div class="tblColContract">
														<form:radiobutton path="propertyContract.titleCommitment" value="n/a" />
													</div>
													<div class="tblColContract">
														title Commitment
													</div>
													<div class="tblColContract">
														<input type="file" id="titleCommFile" value="Choose File" />
														<button id="titleCommUploadFile">Upload</button>
														<span id="titleCommFilename"><c:out value="${propertyView.propertyContract.titleCommitmentFileName.name}"></c:out></span>
													</div>
												</div>
												<div class="tblRowContract">
													<div class="tblColContract">
														<form:radiobutton path="propertyContract.titlePolicy" value="yes" />
													</div>
													<div class="tblColContract">
														<form:radiobutton path="propertyContract.titlePolicy" value="no" />
													</div>
													<div class="tblColContract">
														<form:radiobutton path="propertyContract.titlePolicy" value="n/a" />
													</div>
													<div class="tblColContract">
														Title Policy
													</div>
													<div class="tblColContract">
														<input type="file" id="titlePolicyFile" value="Choose File" />
														<button id="titlePolicyUploadFile">Upload</button>
														<span id="titlePolicyFilename"><c:out value="${propertyView.propertyContract.titlePolicyFileName.name}"></c:out></span>
													</div>
												</div>
												<div class="tblRowContract">
													<div class="tblColContract">
														<form:radiobutton path="propertyContract.settlement" value="yes" />
													</div>
													<div class="tblColContract">
														<form:radiobutton path="propertyContract.settlement" value="no" />
													</div>
													<div class="tblColContract">
														<form:radiobutton path="propertyContract.settlement" value="n/a" />
													</div>
													<div class="tblColContract">
														Settlement
													</div>
													<div class="tblColContract">
														$<form:input path="propertyContract.settlementQty" /><br>
													</div>
													<div class="tblColContract">
														<input type="file" id="settlementFile" value="Choose File" />
														<button id="settlementUploadFile">Upload</button>
														<span id="settlementFilename"><c:out value="${propertyView.propertyContract.settlementFileName.name}"></c:out></span>
													</div>
												</div>
											</div>
											<br>											
											<input type="hidden" value="${requestScope.propertyOID}"
												name="propertyId" />
											<input type="hidden" value="${propertyView.property.status}"
												name="currentStatus" />
											<input type="hidden" value="${propertyView.propertyContract.id}"
												name="contractId" id="contractId" />
											<input class="bttnAction" type="submit"
												value="Save">
											<input class="bttnAction" type="button" id="moveToContract"
												value="Move property to Land Use Permitting" />
										</form:form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div style="clear: both;">&nbsp;</div>
					<div style="clear: both;">&nbsp;</div>
					<div class="divNoteContainer">
						<div class="noteTitle">Notes</div>
						<div class="notesDiv">
							<div class="notesContainer" id="axNotesContainer"></div>
							<div id="axNoteBox"></div>
					</div>
				</div>
				<div style="clear: both;">&nbsp;</div>
			</div>
		</div>

		<div id="sidebar">
			<div id="menubox"></div>
			<%-- 			<jsp:include page="${request.contextPath}/projects/menuNavigation"></jsp:include> --%>
		</div>

	</div>
	<div style="clear: both;">&nbsp;</div>
</body>
</html>