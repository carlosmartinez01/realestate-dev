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
	$("#moveToContract").on("click", function(e){;
    	e.preventDefault();
    	$('#researchForm').attr('action', '<%=request.getContextPath()%>/properties/moveResearch')[0].submit();
	});
});
</script>
<script>
$( function() {
	$("#add-property").on("click", function(e){;
    	e.preventDefault();
    	$('#researchForm').attr('action', '<%=request.getContextPath()%>/properties/addResearch')[0].submit();
	});
});
</script>
<script type="text/javascript"
	note_url='<%=request.getContextPath()%>/note'
	obj_type='<%=request.getAttribute("objectType")%>'
	obj_id='<%=request.getAttribute("propertyOID")%>'
	page_id='edit_property_research'
	user_id='<%=request.getAttribute("username")%>'
	src="<%=request.getContextPath()%>/resources/js/notes.js"></script>
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
					<div class="post">
						<div class="bg1">
							<div class="bg2">
								<div class="bg3">
									<h2 class="title">Property Research</h2>
									<c:if test="${not empty messageForm}">
										<div class="msg">${messageForm}</div>
									</c:if>
									<div class="entry">
										<br>
										<spring:url
											value="/properties/updateResearch"
											var="updatePropertyUrl" />
										<form:form method="POST" action="${updatePropertyUrl}"
											modelAttribute="propertyView" id="researchForm">
											<div class="objListDetails">
												<div class="tblRowDetails">
													<div class="tblColDetails">Property Name</div>
													<div class="tblColDetails">
														<form:input path="name" type="text" cssClass="genericBox" />
													</div>
													<div class="tblColDetailsErrorDetails">
														<form:errors path="name" cssClass="errorValMsg"
															element="div" />
													</div>
												</div>
												<div class="tblRowDetails">
													<div class="tblColDetails">Address</div>
													<div class="tblColDetails">
														<form:textarea path="address" rows="2" cols="30" />
													</div>
												</div>
												<div class="tblRowDetails">
													<div class="tblColDetails">City</div>
													<div class="tblColDetails">
														<form:input path="city" type="text" cssClass="genericBox" />
													</div>
												</div>
												<div class="tblRowDetails">
													<div class="tblColDetails">State</div>
													<div class="tblColDetails">
														<form:select path="state" >
   														<form:option value="NONE" label="--- Select ---"/>
   														<form:options items="${applicationScope.usStatesLst}" />
														</form:select>
													</div>
													<div class="tblColDetailsErrorDetails">
														<form:errors path="state" cssClass="errorValMsg"
															element="div" />
													</div>
												</div>
												<div class="tblRowDetails">
													<div class="tblColDetails">Zip Code</div>
													<div class="tblColDetails">
														<form:input path="zipCode" type="text"
															cssClass="genericBox" />
													</div>
												</div>
												<div class="tblRowDetails">
													<div class="tblColDetails">Preliminary Construction
														Budget</div>
													<div class="tblColDetails">
														<form:input path="budget" type="text"
															cssClass="genericBox" />
													</div>
												</div>
												<div class="tblRowDetails">
													<div class="tblColDetails">Projected Gallons</div>
													<div class="tblColDetails">
														<form:input path="projectedGallons" type="text"
															cssClass="genericBox" />
													</div>
												</div>
												<div class="tblRowDetails">
													<div class="tblColDetails">Projected Margin</div>
													<div class="tblColDetails">
														<form:input path="projectedMargin" type="text"
															cssClass="genericBox" />
													</div>
												</div>
												<div class="tblRowDetails">
													<div class="tblColDetails">Projected Store Sales</div>
													<div class="tblColDetails">
														<form:input path="projectedStoreSales" type="text"
															cssClass="genericBox" />
													</div>
												</div>
											</div>
											<br>
											<c:if test="${action == 'Add'}">
												<input class="bttnAction" type="button" id="add-property"
												value="Add">
											</c:if>
											<c:if test="${action != 'Add'}">
											<input class="bttnAction" type="submit"
												value="Update">
											<input class="bttnAction" type="button" id="moveToContract"
												value="Move property to contract" />											
											</c:if>
											<form:hidden path="status"/>
											<form:hidden path="id"/>
										</form:form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<c:if test="${action != 'Add'}">
					<div style="clear: both;">&nbsp;</div>
					<div style="clear: both;">&nbsp;</div>
					<div class="divNoteContainer">
						<div class="noteTitle">Notes</div>
						<div class="notesDiv">
							<div class="notesContainer" id="axNotesContainer"></div>
							<div id="axNoteBox"></div>
						</div>
					</div>
				</c:if>
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