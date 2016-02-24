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

<script type="text/javascript"
	note_url='<%=request.getContextPath()%>/note'
	obj_type='<%=request.getAttribute("objectType")%>'
	obj_id='<%=request.getAttribute("propertyOID")%>'
	page_id='edit_property_permitting_task'
	user_id='<%=request.getAttribute("username")%>'
	src="<%=request.getContextPath()%>/resources/js/notes.js"></script>
	
<script type="text/javascript"
	root_url='<%=request.getContextPath()%>'
	property_id='<%=request.getAttribute("propertyOID")%>'
	user_logged='<%=request.getAttribute("userFullName")%>'
	src="<%=request.getContextPath()%>/resources/js/taskModal.js"></script>
	
<script type="text/javascript"
	root_url='<%=request.getContextPath()%>'
	property_id='<%=request.getAttribute("propertyOID")%>'
	src="<%=request.getContextPath()%>/resources/js/contactModal.js"></script>

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
								<spring:url
											value="/projects/${requestScope.projectId}/preconstruction/save"
											var="savePreconstruction" />
									<form:form modelAttribute="preConstructionForm" action="${savePreconstruction }" method="POST">
										<div class="normal-table">
										<c:forEach var="preConstructionLst" varStatus="constructionIndex" items="${preConstructionForm.preConstruction }">
											<div class="normal-row">
												<div class="normal-column">
													<form:hidden path="preConstruction[${constructionIndex.index }].id" />
													<form:checkbox path="preConstruction[${constructionIndex.index }].checked" value="yes" />
												</div>
												<div class="normal-column cell-padding">									
													<form:hidden path="preConstruction[${constructionIndex.index }].constructionDocumentType" />
													<c:out value="${preConstructionLst.constructionDocumentType }"></c:out>
												</div>
												<div class="normal-column" cell-padding>
													<form:input path="preConstruction[${constructionIndex.index }].contactName"/>
												</div>	
												<c:set var="constructionDetails" value="preConstruction[${constructionIndex.index }].details" scope="request"></c:set>											
												<c:forEach var="detail" varStatus="detailIndex" items="${preConstructionLst.details }">
													<div class="normal-row">
														<div class="normal-column cell-padding">
															<form:hidden path="preConstruction[${constructionIndex.index }].details[${detailIndex.index }].id" />
															<form:input path="preConstruction[${constructionIndex.index }].details[${detailIndex.index }].dateReceived"/>
														</div>
														<div class="normal-column cell-padding">
															<form:input path="preConstruction[${constructionIndex.index }].details[${detailIndex.index }].filename.name"/>
														</div>
													</div>
												</c:forEach>																							
											</div>
										</c:forEach>
										</div>
										<div style="clear: both;">&nbsp;</div>
											<input class="bttnAction" type="submit" value="Save">
									</form:form>
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