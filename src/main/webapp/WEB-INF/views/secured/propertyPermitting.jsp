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
	$("#moveToTasks").on("click", function(e){
    	e.preventDefault();
    	$('#permittingForm').attr('action', '<%=request.getContextPath()%>'+ '/properties/movePermittingLand/${requestScope.propertyOID}/${permittingView.permitting.id}')[0].submit();
						});
	});
</script>
<script type="text/javascript"
	note_url='<%=request.getContextPath()%>/note'
	obj_type='<%=request.getAttribute("objectType")%>'
	obj_id='<%=request.getAttribute("propertyOID")%>' page_id='edit_property_permitting'
	user_id='<%=request.getAttribute("username")%>'
	src="<%=request.getContextPath()%>/resources/js/notes.js"></script>

<script type="text/javascript" 
	root_url='<%=request.getContextPath()%>'
	src="<%=request.getContextPath()%>/resources/js/permitting.js">
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
					<h2 class="title">Permitting &#38; Licensing</h2>
					<div class="post">
						<div class="bg1">
							<div class="bg2">
								<div class="bg3">
									<c:if test="${not empty messageForm}">
										<div class="msg">${messageForm}</div>
									</c:if>
									<div class="entry">
										<br>
										<spring:url
											value="/properties/savePermitting/${requestScope.propertyOID}/${permittingView.permitting.id}"
											var="addOrUpdateProperty" />
										<form:form method="POST" action="${addOrUpdateProperty}"
											modelAttribute="permittingView" id="permittingForm">
											<div class=permittingFirstSection>
												<div class="tblRowContract">
													<div class="tblColPermitting">
														<form:input path="permitting.turnOverPCDate"
															cssClass="permittingDates" readonly="true" size="10" />
														&nbsp;Est. Turn Over to Pre-Construction
													</div>
												</div>
												<div class="tblRowContract">
													<div class="tblColPermitting">
														<form:checkbox path="permitting.approvalToMoveVP" value="yes" />
														&nbsp;VP Approval To Move Ahead
													</div>
												</div>
												<div class="tblRowContract">
													<div class="tblColPermitting">
														<form:input path="permitting.civilPlansDate"
															cssClass="permittingDates" readonly="true" size="10" />
														&nbsp;Civil Plans
													</div>
												</div>
												<div class="tblRowContract">
													<div class="tblColPermitting">
														<form:input path="permitting.buildingPlansDate"
															cssClass="permittingDates" readonly="true" size="10" />
														&nbsp;Building Plans
													</div>
												</div>
												<div class="tblRowContract">
													<div class="tblColPermitting">
														<form:input path="permitting.permittingAssignedTo" size="10" />
														&nbsp;Permitting Assigned To
													</div>
												</div>
											</div>
											<br>
											<div class=permittingFirstSection>
												<div class="tblRowContract radioHdrFormatt">
													<div class="tblColPermitting">Yes</div>
													<div class="tblColPermitting">No</div>
													<div class="tblColPermitting">N/A</div>
													<div class="tblColPermitting">Commitment Tasks</div>
													<div class="tblColPermitting">Files / Dates</div>
												</div>
												<div class="tblRowContract">
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.titleCommitmentReviewed" value="yes" />
													</div>
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.titleCommitmentReviewed" value="no" />
													</div>
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.titleCommitmentReviewed" value="n/a" />
													</div>
													<div class="tblColPermitting">Title Commitment Reviewed</div>
													<div class="tblColPermitting">
														<form:input path="permitting.titleCommitmentDate"
															cssClass="permittingDates"
															readonly="true" size="10" />
														<br>
													</div>
													<div class="tblColPermitting">
														<form:input
															path="permitting.commitmentAssignTaskTo" size="10" />
													</div>
													<div class="tblColPermitting">
														<input type="file" id="tittleCommitmentFile" class="permittingUploadFile" value="Choose File" />
														<button id="tittleCommitmentUpload">Upload</button>
														<span id="tittleCommitmentFilename"><c:out value="${permittingView.permitting.commitmentFileName.name}"></c:out></span>
													</div>
												</div>
												<div class="tblRowContract">
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.titleExceptionLetterPrepared" value="yes" />
													</div>
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.titleExceptionLetterPrepared" value="no" />
													</div>
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.titleExceptionLetterPrepared" value="n/a" />
													</div>
													<div class="tblColPermitting">Title Exception Letter Prepared</div>													
													<div class="tblColPermitting">
														<form:input
															path="permitting.exceptionAssignTaskTo" size="10" />
													</div>
													<div class="tblColPermitting">
														<input type="file" id="tittleExceptionFile" value="Choose File" />
														<button id="tittleExceptionUpload">Upload</button>
														<span id="tittleExceptionFilename"><c:out value="${permittingView.permitting.uploadLetterFileName.name}"></c:out></span>
													</div>
												</div>
												<div class="tblRowContract radioHdrFormatt">
													<div class="tblColPermitting">Yes</div>
													<div class="tblColPermitting">No</div>
													<div class="tblColPermitting">N/A</div>
													<div class="tblColPermitting">Survey Tasks</div>
													<div class="tblColPermitting">Files / Dates</div>
												</div>
												<div class="tblRowContract">
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.surveyOrdered" value="yes" />
													</div>
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.surveyOrdered" value="no" />
													</div>
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.surveyOrdered" value="n/a" />
													</div>
													<div class="tblColPermitting">Survey Ordered</div>
													<div class="tblColPermitting">
														<form:input path="permitting.surveyDateOrdered"
															cssClass="permittingDates"
															readonly="true" size="10" />
														<br>
													</div>
													<div class="tblColPermitting">
														<form:input
															path="permitting.surveyContact" size="10" />
													</div>													
												</div>
												<div class="tblRowContract">
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.surveyReceived" value="yes" />
													</div>
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.surveyReceived" value="no" />
													</div>
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.surveyReceived" value="n/a" />
													</div>
													<div class="tblColPermitting">Survey Received</div>
													<div class="tblColPermitting">
														<form:input path="permitting.surveyDateReceived"
															cssClass="permittingDates"
															readonly="true" size="10" />
														<br>
													</div>
													<div class="tblColPermitting">
														<input type="file" id="surveyReceivedFile" value="Choose File" />
														<button id="surveyReceivedUpload">Upload</button>
														<span id="surveyReceivedFilename"><c:out value="${permittingView.permitting.surveyFileName.name}"></c:out></span>
													</div>
												</div>
												<div class="tblRowContract">
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.preSitePlanReceived" value="yes" />
													</div>
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.preSitePlanReceived" value="no" />
													</div>
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.preSitePlanReceived" value="n/a" />
													</div>
													<div class="tblColPermitting">Preliminary Site Plan Determination</div>
													<div class="tblColPermitting">
														<form:input path="permitting.preSitePlanDateReceived"
															cssClass="permittingDates"
															readonly="true" size="10" />
														<br>
													</div>
													<div class="tblColPermitting">
														<input type="file" id="sitePlanFile" value="Choose File" />
														<button id="sitePlanUpload">Upload</button>
														<span id="sitePlanFilename"><c:out value="${permittingView.permitting.preSitePlanReceivedFileName.name}"></c:out></span>
													</div>
												</div>
												<div class="tblRowContract">
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.floodPlanDetermination" value="yes" />
													</div>
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.floodPlanDetermination" value="no" />
													</div>
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.floodPlanDetermination" value="n/a" />
													</div>
													<div class="tblColPermitting">Flood Plan Determination</div>
													<div class="tblColPermitting">
														<form:input path="permitting.floodPlanDateDetermined"
															cssClass="permittingDates"
															readonly="true" size="10" />
														<br>
													</div>										
												</div>
												<div class="tblRowContract">
													<div class="tblColPermitting">
														<div style="clear: both;">&nbsp;</div>
													</div>
													<div class="tblColPermitting">
														<div style="clear: both;">&nbsp;</div>
													</div>
													<div class="tblColPermitting">
														<div style="clear: both;">&nbsp;</div>
													</div>	
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.propertyFloodPlain" value="yes" /><div class="meetingsFont">Yes this property is in a flood plain</div>
														<form:radiobutton
															path="permitting.propertyFloodPlain" value="no" /><div class="meetingsFont">No this property is NOT in a flood plain</div>
													</div>													
													<div class="tblColPermitting">
														<div class="meetingsFont">Source</div>
														<form:textarea
															path="permitting.floodSource" cols="10" rows="4" />
													</div>										
												</div>
												<div class="tblRowContract radioHdrFormatt">
													<div class="tblColPermitting">Yes</div>
													<div class="tblColPermitting">No</div>
													<div class="tblColPermitting">N/A</div>
													<div class="tblColPermitting">Geotechnical Tasks</div>
													<div class="tblColPermitting">Files / Dates</div>
												</div>
												<div class="tblRowContract">
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.geotechnicalReportOrdered" value="yes" />
													</div>
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.geotechnicalReportOrdered" value="no" />
													</div>
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.geotechnicalReportOrdered" value="n/a" />
													</div>
													<div class="tblColPermitting">Geotechnical Report Ordered</div>
													<div class="tblColPermitting">
														<form:input path="permitting.geotechnicalDateReportOrdered"
															cssClass="permittingDates"
															readonly="true" size="10" />
														<br>
													</div>
													<div class="tblColPermitting">
														<form:input
															path="permitting.geotechnicalEngineer" size="10" />
													</div>													
												</div>
												<div class="tblRowContract">
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.geotechnicalReportReceived" value="yes" />
													</div>
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.geotechnicalReportReceived" value="no" />
													</div>
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.geotechnicalReportReceived" value="n/a" />
													</div>
													<div class="tblColPermitting">Geotechnical Report Received</div>
													<div class="tblColPermitting">
														<form:input path="permitting.geotechnicalReportDateReceived"
															cssClass="permittingDates"
															readonly="true" size="10" />
														<br>
													</div>
													<div class="tblColPermitting">
														<input type="file" id="geotechnicalFile" value="Choose File" />
														<button id="geotechnicalUpload">Upload</button>
														<span id="geotechnicalFilename"><c:out value="${permittingView.permitting.geotechnicalFileName.name}"></c:out></span>
													</div>
												</div>
												<div class="tblRowContract radioHdrFormatt">
													<div class="tblColPermitting">Yes</div>
													<div class="tblColPermitting">No</div>
													<div class="tblColPermitting">N/A</div>
													<div class="tblColPermitting">Access Permit Tasks</div>
													<div class="tblColPermitting">Files / Dates</div>
												</div>
												<div class="tblRowContract">
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.departmentTransportation" value="yes" />
													</div>
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.departmentTransportation" value="no" />
													</div>
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.departmentTransportation" value="n/a" />
													</div>
													<div class="tblColPermitting">Department Transportation</div>
													<div class="tblColPermitting">
														<form:input path="permitting.departmentTransportationDateEngaged"
															cssClass="permittingDates"
															readonly="true" size="10" />
														<br>
													</div>													
												</div>
												<div class="tblRowContract">
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.trafficStudy" value="yes" />
													</div>
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.trafficStudy" value="no" />
													</div>
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.trafficStudy" value="n/a" />
													</div>
													<div class="tblColPermitting">Traffic Study</div>
													<div class="tblColPermitting">
														<form:input path="permitting.trafficStudyDateEngaged"
															cssClass="permittingDates"
															readonly="true" size="10" />
														<br>
													</div>
													<div class="tblColPermitting">
														<input type="file" id="trafficStudyFile" value="Choose File" />
														<button id="trafficStudyUpload">Upload</button>
														<span id="trafficStudyFilename"><c:out value="${permittingView.permitting.trafficStudyFileName.name}"></c:out></span>
													</div>
												</div>
												<div class="tblRowContract radioHdrFormatt">
													<div class="tblColPermitting">Yes</div>
													<div class="tblColPermitting">No</div>
													<div class="tblColPermitting">N/A</div>
													<div class="tblColPermitting">Engineering Tasks</div>
													<div class="tblColPermitting">Files / Dates</div>
												</div>
												<div class="tblRowContract">
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.civilEngineerAssigned" value="yes" />
													</div>
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.civilEngineerAssigned" value="no" />
													</div>
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.civilEngineerAssigned" value="n/a" />
													</div>
													<div class="tblColPermitting">Civil Engineer Assigned</div>
													<div class="tblColPermitting">
														<form:input path="permitting.civilEngineerDateEngaged"
															cssClass="permittingDates"
															readonly="true" size="10" />
														<br>
													</div>	
													<div class="tblColPermitting">
														<form:input
															path="permitting.civilEngineerContact" size="10" />
													</div>												
												</div>
												<div class="tblRowContract">
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.civilEngineerApprovedToBegin" value="yes" />
													</div>
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.civilEngineerApprovedToBegin" value="no" />
													</div>
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.civilEngineerApprovedToBegin" value="n/a" />
													</div>
													<div class="tblColPermitting">Civil Engineer Approved to Begin</div>
													<div class="tblColPermitting">
														<form:textarea
															path="permitting.civilEngineerMessage" cols="10" rows="4" />
													</div>													
												</div>
												<div class="tblRowContract">
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.civilEngineerDrawingsReceived" value="yes" />
													</div>
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.civilEngineerDrawingsReceived" value="no" />
													</div>
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.civilEngineerDrawingsReceived" value="n/a" />
													</div>
													<div class="tblColPermitting">Civil Engineering Drawings Recevied</div>
													<div class="tblColPermitting">
														<form:input path="permitting.civilEngineerDrawingsDateReceived"
															cssClass="permittingDates"
															readonly="true" size="10" />
														<br>
													</div>																							
												</div>
												<div class="tblRowContract">
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.gradingAndDrainagePlan" value="yes" />
													</div>
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.gradingAndDrainagePlan" value="no" />
													</div>
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.gradingAndDrainagePlan" value="n/a" />
													</div>
													<div class="tblColPermitting">Grading and drainage plan approved by director of construction</div>
													<div class="tblColPermitting">
														<form:input path="permitting.gradingAndDrainagePlanDateApproved"
															cssClass="permittingDates"
															readonly="true" size="10" />
														<br>
													</div>
													<div class="tblColPermitting">
														<form:input
															path="permitting.gradingAndDrainageAssignTaskTo" size="10" />
													</div>													
												</div>
												<div class="tblRowContract radioHdrFormatt">
													<div class="tblColPermitting">Yes</div>
													<div class="tblColPermitting">No</div>
													<div class="tblColPermitting">N/A</div>
													<div class="tblColPermitting">Architect Tasks</div>
													<div class="tblColPermitting">Files / Dates</div>
													<div class="tblColPermitting"></div>
												</div>
												<div class="tblRowContract">
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.architectAssigned" value="yes" />
													</div>
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.architectAssigned" value="no" />
													</div>
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.architectAssigned" value="n/a" />
													</div>
													<div class="tblColPermitting">Architect Assigned</div>
													<div class="tblColPermitting">
														<form:input path="permitting.architectDateEngaged"
															cssClass="permittingDates"
															readonly="true" size="10" />
														<br>
													</div>
													<div class="tblColPermitting">
														<form:input
															path="permitting.architectName" size="10" />
													</div>													
												</div>
												<div class="tblRowContract">
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.architectApprovedToBegin" value="yes" />
													</div>
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.architectApprovedToBegin" value="no" />
													</div>
													<div class="tblColPermitting">
														<form:radiobutton
															path="permitting.architectApprovedToBegin" value="n/a" />
													</div>
													<div class="tblColPermitting">Architect Approved to Begin</div>
													<div class="tblColPermitting">
														<form:textarea
															path="permitting.architectApprovedMessage" cols="10" rows="4" />
													</div>
												</div>
											</div>
											<br>
											<div class="meetingsContainer">
												<div class="meetingRow">
													<div class="meetingCol meetingCheckboxes meetingsFont">
														Meetings and Approvals &#45; Show Meetings:
													</div>
													<div class="meetingCol meetingsFont">
														<input type="checkbox" id="checkbox-1" class="meetingCheckBoxArray">1
													</div>
													<div class="meetingCol meetingsFont">
														<input type="checkbox" id="checkbox-2" class="meetingCheckBoxArray">2
													</div>
													<div class="meetingCol meetingsFont">
														<input type="checkbox" id="checkbox-3" class="meetingCheckBoxArray">3
													</div>
													<div class="meetingCol meetingsFont">
														<input type="checkbox" id="checkbox-4" class="meetingCheckBoxArray">4
													</div>
													<div class="meetingCol meetingsFont">
														<input type="checkbox" id="checkbox-5" class="meetingCheckBoxArray">5
													</div>
													<div class="meetingCol meetingsFont">
														<input type="checkbox" id="checkbox-6" class="meetingCheckBoxArray">6
													</div>
													<div class="meetingCol meetingsFont">
														<input type="checkbox" id="checkbox-7" class="meetingCheckBoxArray">7
													</div>
													<div class="meetingCol meetingsFont">
														<input type="checkbox" id="checkbox-8" class="meetingCheckBoxArray">8
													</div>
													<div class="meetingCol meetingsFont">
														<input type="checkbox" id="checkbox-9" class="meetingCheckBoxArray">9
													</div>
												</div>												
											</div>
											<div class="meetingsContainer">
												<div class="meetingRow">
													<div class="meeetingImportantMsg meetingsFont">
														Meetings marked as N&#47;A will not show up after you save your changes.  To get them back, click on one of the boxes above. 
													</div>
												</div>
											</div>
											<c:forEach var="meetingsLst" varStatus="meeting" items="${permittingView.meetings }">
												<div id="meetingsBox-${meetingsLst.meetingId}">
													<div class="meetingsContainer">
														<div class="meetingRow">
															<div class="meetingsDetails meetingsFont meetingPaddings">
																Yes</div>
															<div class="meetingsDetails meetingsFont meetingPaddings">
																No</div>
															<div class="meetingsDetails meetingsFont meetingPaddings">
																N/A</div>
															<div class="meetingsName meetingsFont meetingPaddings">
																<form:input path="meetings[${meeting.index}].meetingName" size="40"/>
															</div>
															<div class="tblColDetailsErrorDetails">
																<form:errors path="meetings[${meeting.index}].meetingName" cssClass="errorValMsg"
																	element="div" />
															</div>
														</div>
														<c:set value="${0}" var="dynamicId" />
														<div class="meetingRow">
															<div class="meetingsDetails meetingsFont">
																<form:radiobutton path="meetings[${meeting.index}].meeting" id="${meetingsLst.meetingId}-radio-${dynamicId + 1}" value="yes" cssClass="displayMeeting-${meetingsLst.meetingId}" />
															</div>
															<div class="meetingsDetails meetingsFont">
																<form:radiobutton path="meetings[${meeting.index}].meeting" id="${meetingsLst.meetingId}-radio-${dynamicId + 2}" value="no" cssClass="displayMeeting-${meetingsLst.meetingId}" />
															</div>
															<div class="meetingsDetails meetingsFont">
																<form:radiobutton path="meetings[${meeting.index}].meeting" id="${meetingsLst.meetingId}-radio-${dynamicId + 3}" value="n/a" cssClass="displayMeeting-${meetingsLst.meetingId}" />
															</div>
														</div>
													</div>
													<div class="meetingsContainer">
														<div class="meetingRow">
															<div
																class="meetingsDetails meetingsFont meetingEndRow meetingPaddings">
																Submitting Deadline:<br>
																<form:input path="meetings[${meeting.index}].meetingSubDeadline"
																	cssClass="permittingDates" readonly="true" size="7" />
																<br> Date of Meeting:<br>
																<form:input path="meetings[${meeting.index}].meetingDate"
																	cssClass="permittingDates" readonly="true" size="7" />
																<br> Approval Date:<br>
																<form:input path="meetings[${meeting.index}].meetingApprovalDate"
																	cssClass="permittingDates" readonly="true" size="7" />
															</div>
															<div class="meetingsNotes meetingsFont meetingEndRow">
																Notes: <br>
																<form:textarea path="meetings[${meeting.index}].meetingNotes" cols="25" rows="5"/>
															</div>
														</div>
													</div>
												</div>
											</c:forEach>										
											<div style="clear: both;">&nbsp;</div>
											<input class="bttnAction" type="submit" value="Save">
											<input class="bttnAction" type="button" id="moveToTasks"
												value="Move property to Permitting Tasks" />
											<input type="hidden" value="${permittingView.permitting.id }" id="permittingID" />
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