<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<html>
<head>
<title></title>
<script type="text/javascript" 
root_url='<%=request.getContextPath()%>'
    src="<%=request.getContextPath()%>/resources/js/fileService.js"></script>

<style>
.accordion-expand-holder {
	margin: 10px 0;
}

.accordion-expand-holder .open, .accordion-expand-holder .close {
	margin: 0 10px 0 0;
}

.sidebar-at-left #sidebar {
	margin-right: 1%;
}

.sidebar-at-right #sidebar {
	margin-left: 1%;
}

.sidebar-at-left #boxContent, .use-sidebar.sidebar-at-right #sidebar,
	.sidebar-at-right #separator {
	float: right;
}

.sidebar-at-right #boxContent, .use-sidebar.sidebar-at-left #sidebar,
	.sidebar-at-left #separator {
	float: left;
}

.use-sidebar #boxContent {
	width: 72%;
}

.use-sidebar #sidebar {
	display: block;
	width: 23%;
}

#separator {
	background-color: #EEE;
	border: 1px solid #CCC;
	display: block;
	outline: none;
	width: 1%;
}

.use-sidebar #separator {
	border-color: #FFF;
}
</style>

<script>
	$(function() {
		if(typeof(Storage) !== "undefined") {
	        if (sessionStorage.getItem("hide") === "true") {
				setTimeout(function() {
					removeDiv();
				}, 5);
				function removeDiv() {
					// Variables
					var objMain = $('#main');
					objMain.removeClass('use-sidebar');
				}
	        } else {
	        	$("#effect").show('slide', 500);
	        }
	    }
		
		// run the currently selected effect
		function runEffect(x) {
			// get effect type from
			var selectedEffect = $("#" + x).val();
			if (selectedEffect === "hide") {
				sessionStorage.setItem("hide", true);
				hideBar();
			} else if (selectedEffect === "show") {
				sessionStorage.setItem("hide", false);
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
	//Accordion - Expand All #01
	$(function() {
		$("#accordion").accordion({
			collapsible : true,
			active : false
		});
		var icons = $("#accordion").accordion("option", "icons");
		$('.open')
				.click(
						function() {
							$('.ui-accordion-header')
									.removeClass('ui-corner-all')
									.addClass(
											'ui-accordion-header-active ui-state-active ui-corner-top')
									.attr({
										'aria-selected' : 'true',
										'tabindex' : '0'
									});
							$('.ui-accordion-header-icon').removeClass(
									icons.header)
									.addClass(icons.headerSelected);
							$('.ui-accordion-content').addClass(
									'ui-accordion-content-active').attr({
								'aria-expanded' : 'true',
								'aria-hidden' : 'false'
							}).show();
							$(this).attr("disabled", "disabled");
							$('.close').removeAttr("disabled");
						});
		$('.close')
				.click(
						function() {
							$('.ui-accordion-header')
									.removeClass(
											'ui-accordion-header-active ui-state-active ui-corner-top')
									.addClass('ui-corner-all').attr({
										'aria-selected' : 'false',
										'tabindex' : '-1'
									});
							$('.ui-accordion-header-icon').removeClass(
									icons.headerSelected)
									.addClass(icons.header);
							$('.ui-accordion-content').removeClass(
									'ui-accordion-content-active').attr({
								'aria-expanded' : 'false',
								'aria-hidden' : 'true'
							}).hide();
							$(this).attr("disabled", "disabled");
							$('.open').removeAttr("disabled");
						});
		$('.ui-accordion-header').click(function() {
			$('.open').removeAttr("disabled");
			$('.close').removeAttr("disabled");

		});
	});
</script>
<script type="text/javascript"
	root_url='<%=request.getContextPath()%>'
	property_id='<%=request.getAttribute("propertyOID")%>'
	src="<%=request.getContextPath()%>/resources/js/addProjectModal.js"></script>
	
<script>
$(function() {
	getProjects();
});

function isNull(evalString) {
	if(evalString === null) {
		return '';
	}
	return evalString;
}

function getProjects() {
$.ajax({
	url : '<%=request.getContextPath()%>/projects/<%=request.getAttribute("propertyOID")%>/getAll',
	success : function(data) {
		var len = data.length;
		var html = '';
		for (var i = 0; i < len; i++) {
			html = html + '<div class="normal-table">';	
			html = html + '<div class="normal-row">';
			html = html + '<div class="normal-column">';
			html = html + '<a href="<%=request.getContextPath()%>/projects/' + isNull(data[i].id) +'/getAll" id="project-link" class="link-formatter">';
			html = html
					+ isNull(data[i].projectName);
			html = html + '</a>';
			html = html + '</div>';
			html = html + '</div>';
			html = html + '</div>';
			html = html + '<div style="clear: both;">&nbsp;</div>';					
		}
		$('#projects-related').html(html);
	}, error: function() {
			$('#projects-related').html('Error while processing the request, please try again later!');
		}
});
}
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
					<div class="post">
						<div class="bg1">
							<div class="bg2">
								<div class="bg3">
									<div class="entry">
										<div class="objListDetails">
											<div class="tblRow">
												<div class="tblColDetails">
													<div class="tblRowDetails">
														<div class="tblColDetails">Property Name</div>
														<div class="tblColDetails">
															<c:out value="${PROPERTY_SUMMARY.name}" />
														</div>
													</div>
													<div class="tblRowDetails">
														<div class="tblColDetails">Store Number</div>
														<div class="tblColDetails">
															<c:out value="${PROPERTY_SUMMARY.storeNumber}" />
														</div>
													</div>
													<div class="tblRowDetails">
														<div class="tblColDetails">Store Phone</div>
														<div class="tblColDetails">
															<c:out value="${PROPERTY_SUMMARY.storePhone}" />
														</div>
													</div>
													<div class="tblRowDetails">
														<div class="tblColDetails">Store Fax</div>
														<div class="tblColDetails">
															<c:out value="${PROPERTY_SUMMARY.storeFax}" />
														</div>
													</div>
													<div class="tblRowDetails">
														<div class="tblColDetails">Address</div>
														<div class="tblColDetails">
															<c:out value="${PROPERTY_SUMMARY.address}" />
														</div>
													</div>
													<div class="tblRowDetails">
														<div class="tblColDetails">City</div>
														<div class="tblColDetails">
															<c:out value="${PROPERTY_SUMMARY.city}" />
														</div>
													</div>
													<div class="tblRowDetails">
														<div class="tblColDetails">State</div>
														<div class="tblColDetails">
															<c:out value="${PROPERTY_SUMMARY.state}" />
														</div>
													</div>
												</div>
												<div class="tblColDetails">
												<div class="tblRowDetails">
													<div class="tblRowDetails">
														<span id="propertyPicture"><img id="filename" alt="" src="${PROPERTY_SUMMARY.pictureFileName.absolutePath}"></span>
													</div>
												</div>
													<div class="tblRowDetails">
														<div class="tblColDetails">
															<input type="file" id="fileToBeUpload" value="Choose File" />
															<button id="fileBttn" value="/filemanager/property/picture/${PROPERTY_SUMMARY.id}/upload" class="uploadFile">Upload</button>
														</div>
													</div>
												</div>
											</div>
										</div>
										<br>
										<div class="normal-table">
											<div class="normal-row">
												<div class="normal-column header-tbl gray-header-tbl">
													Project(s)
												</div>
												<div class="normal-column header-tbl gray-header-tbl">
												</div>
												<div class="normal-column header-tbl gray-header-tbl">
												</div>
												<div class="normal-column header-tbl gray-header-tbl">
													<a href="#" id="create-new"><i class="fa fa-plus fa-lg"></i>&nbsp;Add Project</a>
												</div>
											</div>
										</div>
										<div id="dialog-add-project" title="Add a New Project">
											<div id="display-add-message"></div>
											<form>
												<fieldset>
												<input style="height:0px; top:-1000px; position:absolute" type="text" value="">														
														<select id="project-type" name="project-type">
															<option value="">--- Select ---</option>
														<c:forEach var="types" items="${applicationScope.projectTypes}">
															<option value="${types }">${types }</option>
														</c:forEach>
														</select>
														<div style="clear: both;">&nbsp;</div>
														<div id="div-custom-type">
														<input style="height:0px; top:-1000px; position:absolute" type="text" value="">		
														<label for="project-custom-type">Name this project</label> <input
														type="text" name="project-custom-type" id="project-custom-type" value="Enter" size="30"
														class="text ui-widget-content ui-corner-all">
														</div>
														<div style="clear: both;">&nbsp;</div>
														<label for="project-phase">Select start phase </label>
														<select id="project-phase" name="project-phase">
														<c:forEach var="phases" items="${applicationScope.projectPhases}">
															<option value="${phases }">${phases }</option>
														</c:forEach>
														</select>
														<br>																								
														<input type="submit" tabindex="-1"
														style="position: absolute; top: -1000px">														
												</fieldset>
											</form>
										</div>
										<br>
										<div id="projects-related"></div>									
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div id="sidebar">
			<jsp:include page="${request.contextPath}/projects/menuNavigation"></jsp:include>
		</div>

	</div>

	<div style="clear: both;">&nbsp;</div>
</body>
</html>