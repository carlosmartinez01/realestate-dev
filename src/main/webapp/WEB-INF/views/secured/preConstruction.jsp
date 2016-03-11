<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

<script type="text/javascript"
	note_url='<%=request.getContextPath()%>/note'
	obj_type='<%=request.getAttribute("objectType")%>'
	obj_id='<%=request.getAttribute("projectOID")%>'
	page_id='edit_project_preconstruction'
	user_id='<%=request.getAttribute("username")%>'
	src="<%=request.getContextPath()%>/resources/js/notes.js"></script>
	
<script type="text/javascript"
	root_url='<%=request.getContextPath()%>'
	src="<%=request.getContextPath()%>/resources/js/preConstruction.js"></script>
	
<script type="text/javascript"
	root_url='<%=request.getContextPath()%>'
	preconstruction_id='${preConstructionForm.id }'
	src="<%=request.getContextPath()%>/resources/js/architectModal.js"></script>

<script type="text/javascript">
$(function() {
	$('#loader-gif').hide();
	$('#success-msg').hide();
	$('#error-msg').hide();
	$('#server-msg').hide();
	$('#general-msg').delay(2100).fadeToggle(800, 'linear');
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
					<h2 class="title">Edit Project - Construction</h2>
					<div class="post">
						<div class="bg1">
							<div class="bg2">
								<div class="bg3">
								<div id="success-msg" class="msg"></div>
								<div id="error-msg" class="error"></div>
								<div id="server-msg" class="error"></div>
								<c:if test="${not empty messageForm}">
										<div id=general-msg class="msg">${messageForm}</div>
								</c:if>
								<spring:url
											value="/projects/${requestScope.projectOID}/preconstruction/save"
											var="savePreconstruction" />
									<form:form modelAttribute="preConstructionForm" action="${savePreconstruction }" method="POST">
									<form:hidden path="id" />
									<form:hidden path="project" />
										<div class="normal-table">
										<c:forEach var="types" varStatus="typesIndex" items="${preConstructionForm.details }">
											<div class="normal-row">
												<div class="normal-column">
													<form:hidden path="details[${typesIndex.index }].id" />
													<form:checkbox path="details[${typesIndex.index }].checked" value="yes" />
												</div>
												<div class="normal-column cell-padding">									
													<form:hidden path="details[${typesIndex.index }].constructionDocumentType" />
													<c:out value="${types.constructionDocumentType }"></c:out>
												</div>
												<div class="normal-column cell-padding">
													<form:input path="details[${typesIndex.index }].contactName"/>
												</div>
												<input type="hidden" id="outter-${types.id }" value="${typesIndex.index }">
												<c:forEach var="detail" varStatus="detailIndex" items="${types.typeDetails }">
													<input type="hidden" id="inner-${types.id }" value="${fn:length(types.typeDetails)}">
													<div class="normal-row">
														<div class="normal-column cell-padding">
															<form:hidden path="details[${typesIndex.index }].typeDetails[${detailIndex.index }].id" />
															<form:input cssClass="construction-dates" id="date-${typesIndex.index}-${detailIndex.index }" size="12" path="details[${typesIndex.index }].typeDetails[${detailIndex.index }].dateReceived" readonly="true"/>
														</div>
														<form:errors path="details[${typesIndex.index }].typeDetails[${detailIndex.index }].dateReceived" cssClass="errorValMsg"
																	element="div" />
														<div class="normal-column cell-padding">
															<input type="file" id="file-${detail.id }" value="Choose File" />
															<button id="button-${detail.id }" class="button-upload">Upload</button>
															<span id="filename-${detail.id }" ><a id="file-link-${detail.id }" class="files-link" href="${detail.filename.absolutePath}"><c:out value="${detail.filename.name}"></c:out></a></span>
															<form:hidden path="details[${typesIndex.index }].typeDetails[${detailIndex.index }].filename.id" id="fileId-${detail.id}" />
														</div>
													</div>
												</c:forEach>												
												<div id="add-new-elements-${types.id }"></div>
												<div class="normal-column">
													<i id="${types.id }" class="fa fa-plus-circle fa-lg add-button-array"></i>
												</div>																				
											</div>
										</c:forEach>
										</div>
										<div style="clear: both;">&nbsp;</div>
										<div class="separator-table">
											<div class="normal-row">
												<div class="separator-column">
													<div id="horizontal-separator"></div>
												</div>
											</div>
										</div>
										<div style="clear: both;">&nbsp;</div>
										Architect Drawing Progress&nbsp;<button id="open-drawings" class="bttnAction" value="Save">View</button>
										<div class="preconstruction-fixed-table">
											<div style="clear: both;">&nbsp;</div>
											<div class="normal-row">
												<div class="normal-column cell-padding">
													<form:checkbox path="readyForPickUp" value="yes" />
													Building Permit Ready for Pickup Date
												</div>
												<div class="normal-column cell-padding">
													Date Received
													<form:input id="date-received" path="dateReceived" cssClass="construction-dates" size="12" readonly="yes" />
												</div>
												<div class="normal-column cell-padding">
													<input type="file" id="file-permit" value="Choose File" />
													<button id="button-${preConstructionForm.id }" class="permit-upload">Upload</button>
													<span id="filename-permit"><a id="file-link-permit" class="files-link" href="${preConstructionForm.permitFilename.absolutePath}"><c:out value="${preConstructionForm.permitFilename.name}"></c:out></a></span>
													<form:hidden path="permitFilename.id" id="fileId-permit" />
												</div>
											</div>
											<div class="normal-row">
												<div class="normal-column cell-padding">
													Permit Fee &#36;
													<form:input path="permitFee"/>
												</div>
											</div>
										</div>
										<div style="clear: both;">&nbsp;</div>
											<input class="bttnAction" type="submit" value="Save">
									</form:form>
									<div id="dialog-drawing-form" title="Architect Drawing Progress">
										<div id="display-drawing-message"></div>
										<form>
											<fieldset>
												<div class="modal-fixed-table">
												<c:forEach var="drawing" varStatus="drawingIndex" items="${preConstructionForm.drawings }">
												<input type="hidden" id="parent-${drawing.id }" value="${drawing.id }">
													<div class="modal-row">
														<div class="modal-column cell-padding">
															<input type="checkbox" id="${drawing.type }-${drawing.id }" class="text ui-widget-content ui-corner-all form-checkbox" value="Yes">
															<input type="hidden" id="check-${drawing.type }-${drawing.id }" value="${drawing.completed }" class="hidden-checkbox">
															<label for="${drawing.type }-${drawing.id }"><c:out value="${drawing.type }"></c:out></label>
														</div>
														<div id="parent-${drawing.type }-${drawing.id }">
														<c:forEach var="drawingDetail" varStatus="detailIndex" items="${drawing.drawingDetails }">														
															<div class="modal-row">
																<div class="modal-column cell-padding">
																		<input type="hidden" id="child-${drawing.id }" value="${fn:length(drawing.drawingDetails)}">
																		<input type="hidden" id="drawing_details-${drawingDetail.id }" class="form-dates-${drawing.id }">
																		<input type="text" size="12" id="date-${drawing.id }-${drawingDetail.id}" readonly="readonly" class="construction-dates dates-group-${drawing.id }"
																		 value="${drawingDetail.drawingDate }">
																</div>
															</div>
														</c:forEach>
														<div id="add-dates-elements-${drawing.id }"></div>
														</div>
														<div class="modal-column">
															<i id="${drawing.id }" class="fa fa-calendar-plus-o fa-lg add-dates-array"></i>
														</div>
													</div>
													<br>
												</c:forEach>																																					
												</div>
													<input type="submit" tabindex="-1"
													style="position: absolute; top: -1000px">														
											</fieldset>
										</form>
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
		</div>

	</div>
	<div style="clear: both;">&nbsp;</div>
</body>
</html>