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
	$(function() {
		$('#loader-gif').hide();
		$('#success-msg').hide();
		$('#error-msg').hide();
		tasksAjaxCall();
		$("#tabs").tabs(
				{					
					active: 0,
					activate : function(event, ui) {
						var actived = $('#tabs').tabs('option', 'active');
						var tabSelected = $("#tabs ul>li").eq(actived).attr("id");
						if(tabSelected === 'tasks') {
							$('#save-completed').show();
							tasksAjaxCall();					
						} else if(tabSelected === 'contacts') {
							$('#save-completed').hide();
							contactsAjaxCall();
						}
					}
				}

		);
	});

function isNull(evalString) {
	if(evalString === null) {
		return '';
	}
	return evalString;
}

function isCompleteCheckbox(value) {
	if(value) {
		if(value === 1) {
			return 'checked'
		}
	}
	return '';
}

function tasksAjaxCall() {
	$.ajax({
		url : '<%=request.getContextPath()%>/task/<%=request.getAttribute("propertyOID")%>/permitting/get/tasks',
		success : function(data) {
			var len = data.length;
			var taskHtml = '';
			taskHtml = taskHtml + '<div class="create-task-tbl">';
			taskHtml = taskHtml + '<div class="create-task-row">';
			taskHtml = taskHtml + '<div class="create-task-cell">';
			taskHtml = taskHtml + '<a href="#" id="create-new"><i class="fa fa-plus fa-lg"></i>&nbsp;Create Task</a>';
			taskHtml = taskHtml + '</div></div></div>';
			taskHtml = taskHtml + '<div style="clear: both;">&nbsp;</div>';
			taskHtml = taskHtml
			+ '<div id="taskHeader" class="taskContactTable">';
			taskHtml = taskHtml
			+ '<div class="taskContactRow">';
			taskHtml = taskHtml
			+ '<div class="taskContactColumn taskContactHeader">';
			taskHtml = taskHtml
			+ 'Description';
			taskHtml = taskHtml
			+ '</div>';
			taskHtml = taskHtml
			+ '<div class="taskContactColumn taskContactHeader">';
			taskHtml = taskHtml
			+ 'Due Date';
			taskHtml = taskHtml
			+ '</div>';
			taskHtml = taskHtml
			+ '<div class="taskContactColumn taskContactHeader">';
			taskHtml = taskHtml
			+ 'Assigned By';
			taskHtml = taskHtml
			+ '</div>';
			taskHtml = taskHtml
			+ '<div class="taskContactColumn taskContactHeader">';
			taskHtml = taskHtml
			+ 'Assigned To';
			taskHtml = taskHtml
			+ '</div>';
			taskHtml = taskHtml
			+ '<div class="taskContactColumn taskContactHeader">';
			taskHtml = taskHtml
			+ 'Complete?';
			taskHtml = taskHtml
			+ '</div></div></div>';									
			for (var i = 0; i < len; i++) {
				taskHtml = taskHtml
						+ '<div class="taskContactTable">';
				taskHtml = taskHtml + '<div class="taskContactRow">';
				taskHtml = taskHtml
						+ '<div class="taskContactColumn">';
				taskHtml = taskHtml
						+ isNull(data[i].description);
				taskHtml = taskHtml + '</div>';
				taskHtml = taskHtml
						+ '<div class="taskContactColumn">';
				taskHtml = taskHtml
						+ data[i].dueDate;
				taskHtml = taskHtml + '</div>';
				taskHtml = taskHtml
						+ '<div class="taskContactColumn">';
				taskHtml = taskHtml
						+ data[i].assignedBy;
				taskHtml = taskHtml + '</div>';
				taskHtml = taskHtml
						+ '<div class="taskContactColumn">';
				taskHtml = taskHtml
						+ isNull(data[i].assignedTo);
				taskHtml = taskHtml + '</div>';
				taskHtml = taskHtml
						+ '<div id="column-task-'+ data[i].id +'" class="taskContactColumn">';
				taskHtml = taskHtml
						+ '&nbsp; <input type="checkbox" id="complete-task-'+ i +'" name=isComplete value="1" '+ isCompleteCheckbox(data[i].isComplete) +'>';
				taskHtml = taskHtml
						+ '<input type="hidden" id="task-'+ data[i].id +'" value="'+ data[i].id +'">';
				taskHtml = taskHtml + '</div>';
				taskHtml = taskHtml + '</div></div>';										
			}
			$('#tabs-1').html(taskHtml);
		}, error: function() {
				$('#tabs-1').html('Error while processing the request, please try again later!');
			}
	});
	$('#tabs-2').html('');
}

function contactsAjaxCall() {
	$.ajax({
		url : '<%=request.getContextPath()%>/contact/<%=request.getAttribute("propertyOID")%>/permitting/get/contacts',
		success : function(data) {
			var len = data.length;
			var contactHtml = '';
			contactHtml = contactHtml + '<div class="create-contact-tbl">';
			contactHtml = contactHtml + '<div class="create-contact-row">';
			contactHtml = contactHtml + '<div class="create-contact-cell">';
			contactHtml = contactHtml + '<a href="#" id="create-new-contact"><i class="fa fa-plus fa-lg"></i>&nbsp;Create Contact</a>';
			contactHtml = contactHtml + '</div></div></div>';
			contactHtml = contactHtml + '<div style="clear: both;">&nbsp;</div>';
			contactHtml = contactHtml
			+ '<div id="taskHeader" class="taskContactTable">';
			contactHtml = contactHtml
			+ '<div class="taskContactRow">';
			contactHtml = contactHtml
			+ '<div class="taskContactColumn taskContactHeader">';
			contactHtml = contactHtml
			+ 'Title';
			contactHtml = contactHtml
			+ '</div>';
			contactHtml = contactHtml
			+ '<div class="taskContactColumn taskContactHeader">';
			contactHtml = contactHtml
			+ 'Name';
			contactHtml = contactHtml
			+ '</div>';
			contactHtml = contactHtml
			+ '<div class="taskContactColumn taskContactHeader">';
			contactHtml = contactHtml
			+ 'Comment';
			contactHtml = contactHtml
			+ '</div>';			
			contactHtml = contactHtml
			+ '</div></div></div>';									
			for (var i = 0; i < len; i++) {
				contactHtml = contactHtml
						+ '<div class="taskContactTable">';
				contactHtml = contactHtml + '<div class="taskContactRow">';
				contactHtml = contactHtml
						+ '<div class="taskContactColumn">';
				contactHtml = contactHtml
						+ isNull(data[i].title);
				contactHtml = contactHtml
				+ '<input type="hidden" id="contact-'+ data[i].id +'" value="'+ data[i].id +'">';
				contactHtml = contactHtml + '</div>';
				contactHtml = contactHtml
						+ '<div class="taskContactColumn">';
				contactHtml = contactHtml
						+ data[i].name;
				contactHtml = contactHtml + '</div>';
				contactHtml = contactHtml
						+ '<div class="taskContactColumn">';
				contactHtml = contactHtml
						+ data[i].comment;
				contactHtml = contactHtml + '</div>';
				contactHtml = contactHtml + '</div>';
				contactHtml = contactHtml + '</div>';										
			}
			$('#tabs-2').html(contactHtml);
		}, error: function() {
				$('#tabs-2').html('Error while processing the request, please try again later!');
			}
	});
	$('#tabs-1').html('');
}
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
					<h2 class="title">Edit Property - Permitting &#38; Licensing</h2>
					<div class="post">
						<div class="bg1">
							<div class="bg2">
								<div class="bg3">
									<div id="success-msg" class="msg"></div>
									<div id="error-msg" class="error"></div>																		
									<div class="entry">
									<div id="dialog-create-task" title="Create Task">
											<p class="purchaseValidateTips"></p>
											<div id="display-task-message"></div>
											<form>
												<fieldset>
												<input style="height:0px; top:-1000px; position:absolute" type="text" value="">		
														<label for="task-description">Description</label> <input
														type="text" name="task-description" id="task-description" value="" size="30"
														class="text ui-widget-content ui-corner-all">
														<br>											
														<label for="task-due-date">Due Date</label> <input
														type="text" name="task-due-date" id="task-due-date" value="" readonly="readonly" size="10"
														class="text ui-widget-content ui-corner-all">
														<br>														
														<label for="task-assignedBy">Assigned by</label>
														<input type="text" name="task-assignedBy" id="task-assignedBy" value=""
														class="text ui-widget-content ui-corner-all">
														<br>
														<label for="task-assignedTo">Assigned to</label>
														<select id="task-assignedTo" name="task-assignedTo">
														<c:forEach var="usersLst" items="${users}">
															<option value="${usersLst }">${usersLst }</option>
														</c:forEach>
														</select>
														<br>																														
														<input type="submit" tabindex="-1"
														style="position: absolute; top: -1000px">														
												</fieldset>
											</form>
										</div>
										
										<div id="dialog-create-contact" title="Create Contact">
											<div id="display-contact-message"></div>
											<form>
												<fieldset>
												<input style="height:0px; top:-1000px; position:absolute" type="text" value="">		
														<label for="contact-title">Title</label> <input
														type="text" name="contact-title" id="contact-title" value="" size="21"
														class="text ui-widget-content ui-corner-all">
														<br>											
														<label for="contact-name">Name</label> <input
														type="text" name="contact-name" id="contact-name" value="" size="20"
														class="text ui-widget-content ui-corner-all">
														<br>														
														<label for="contact-comment">Comment</label>
														<br>
														<textarea rows="7" cols="17" name="contact-comment" id="contact-comment" value=""
														class="text ui-widget-content ui-corner-all"></textarea>
														<br>																																								
														<input type="submit" tabindex="-1"
														style="position: absolute; top: -1000px">														
												</fieldset>
											</form>
										</div>
										
										<div id="tabs">
											<ul>
												<li id="tasks"><a href="#tabs-1">Outstanding Assignments/Task</a></li>
												<li id="contacts"><a href="#tabs-2">Contacts</a></li>
											</ul>
											<div id="loader-gif">
											<div style="clear: both;">&nbsp;</div>
											<i class="fa fa-spinner fa-spin fa-lg"></i>
										</div>																			
											<div id="tabs-1">
												<p>Create Tasks here</p>
											</div>
											<div id="tabs-2">
												<p>Create Contacts here</p>
											</div>
										</div>
										<button class="bttnAction" id="save-completed">Save</button>
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